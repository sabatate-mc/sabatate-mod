package net.oritoitsuki.sabatatemod.resource

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloader
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import net.oritoitsuki.sabatatemod.SabatateMod
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

object ResourceListener {
    fun registerReloadListener() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
            .registerReloadListener(object : IdentifiableResourceReloadListener {
                override fun reload(
                    synchronizer: ResourceReloader.Synchronizer,
                    manager: ResourceManager,
                    prepareProfiler: Profiler,
                    applyProfiler: Profiler,
                    prepareExecutor: Executor,
                    applyExecutor: Executor
                ): CompletableFuture<Void> {
                    return ModCache.reload(synchronizer, manager, prepareProfiler, applyProfiler, prepareExecutor, applyExecutor)
                }

                override fun getFabricId(): Identifier {
                    return Identifier(SabatateMod.MOD_ID, "modmodels")
                }
            })
    }
}