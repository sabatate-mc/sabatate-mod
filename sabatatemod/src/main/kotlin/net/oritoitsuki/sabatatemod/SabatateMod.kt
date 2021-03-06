package net.oritoitsuki.sabatatemod

import net.fabricmc.api.ModInitializer
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.ModBlocks
import net.oritoitsuki.sabatatemod.item.ModItems
import net.oritoitsuki.sabatatemod.screenhandler.ModScreenHandlers
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("UNUSED")
object SabatateMod: ModInitializer {
    const val MOD_ID = "sabatatemod"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        ModItems.registerModItems()
        ModBlocks.registerModBlocks()
        ModBlockEntities.registerBlockEntities()
        ModScreenHandlers.registerScreenHandler()
    }
}
