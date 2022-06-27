package net.oritoitsuki.sabatatemod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.oritoitsuki.sabatatemod.block.ModBlockRenderers
import net.oritoitsuki.sabatatemod.resource.ResourceListener
import net.oritoitsuki.sabatatemod.screen.ModScreens

@Environment(EnvType.CLIENT)
object SabatateModClient: ClientModInitializer {
    override fun onInitializeClient() {
        ModScreens.registerScreens()
        ModBlockRenderers.registerModBlockRenderers()
        ResourceListener.registerReloadListener()
    }
}