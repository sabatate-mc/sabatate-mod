package net.oritoitsuki.sabatatemod.block

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.block.renderer.SabatateStatueTopRenderer

@Environment(EnvType.CLIENT)
object ModBlockRenderers {
    class Factory<T: BlockEntity>(private val factory: () -> BlockEntityRenderer<T>): BlockEntityRendererFactory<T> {
        override fun create(ctx: BlockEntityRendererFactory.Context?): BlockEntityRenderer<T> {
            return factory()
        }
    }

    fun registerModBlockRenderers() {
        SabatateMod.LOGGER.debug("Registering Mod Block Renderers for " + SabatateMod.MOD_ID)

        // SABATATE_STATUE_TOP
        BlockEntityRendererRegistry.register(ModBlockEntities.SABATATE_STATUE_TOP, Factory{ SabatateStatueTopRenderer })
    }
}