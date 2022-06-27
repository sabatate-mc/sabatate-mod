package net.oritoitsuki.sabatatemod.resource

import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloader
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.model.ModModel
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

object ModCache {
    var models: MutableMap<Identifier, ModModel> = Collections.emptyMap()

    private fun <T> loadResources(executor: Executor, resourceManager: ResourceManager,
                                  type: String, loader: (Identifier) -> T, map: MutableMap<Identifier, T>): CompletableFuture<Void> {
        return CompletableFuture
            .supplyAsync({ resourceManager.findResources(type) {filename ->
                filename.namespace == SabatateMod.MOD_ID && filename.toString().endsWith(".json")
            } }, executor)
            .thenApplyAsync(lambda@{ resources ->
                val tasks = HashMap<Identifier, CompletableFuture<T>>()
                for (resource in resources.keys) {
                    val existing = tasks.put(resource, CompletableFuture.supplyAsync({loader(resource)}, executor))
                    if (existing != null) {
                        SabatateMod.LOGGER.error("Duplicate resource for $resource")
                        existing.cancel(false)
                    }
                }
                return@lambda tasks
            }, executor)
            .thenAcceptAsync({ tasks ->
                for (entry in tasks.entries) {
                    map[entry.key] = entry.value.join()
                }
            }, executor)
    }

    fun reload(stage: ResourceReloader.Synchronizer, resourceManager: ResourceManager,
               preparationsProfiler: Profiler, reloadProfiler: Profiler, backgroundExecutor: Executor,
               gameExecutor: Executor): CompletableFuture<Void> {
        val models = mutableMapOf<Identifier, ModModel>()
        return CompletableFuture
            .allOf(
                loadResources(backgroundExecutor, resourceManager, "modmodels",
                    { resource -> ModelLoader.loadModel(resourceManager, resource)}, models)
            )
            .thenCompose(stage::whenPrepared)
            .thenAcceptAsync({ this.models = models }, gameExecutor)
    }
}