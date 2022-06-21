package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.SabatateMod

object ModItems {
    val SABATATE_TOKEN_ITEM = SabatateTokenItem
    private val SABATATE_MANJU_ITEM = SabatateManju

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(SabatateMod.MOD_ID, name), item)
    }

    fun registerModItems() {
        SabatateMod.LOGGER.debug("Registering Mod Items for " + SabatateMod.MOD_ID)

        // SABATATE_TOKEN_ITEM
        registerItem("sabatate_token", SABATATE_TOKEN_ITEM)
        FuelRegistry.INSTANCE.add(SABATATE_TOKEN_ITEM, 1600)

        // SABATATE_MANJU_ITEM
        registerItem("sabatate_manju", SABATATE_MANJU_ITEM)
    }
}