package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.SabatateMod

object ModItems {
    val SABATATE_TOKEN = SabatateTokenItem
    private val SABATATE_MANJU = SabatateManju
    private val SABATATE_CHISEL = SabatateChisel

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(SabatateMod.MOD_ID, name), item)
    }

    fun registerModItems() {
        SabatateMod.LOGGER.debug("Registering Mod Items for " + SabatateMod.MOD_ID)

        // SABATATE_TOKEN_ITEM
        registerItem("sabatate_token", SABATATE_TOKEN)
        FuelRegistry.INSTANCE.add(SABATATE_TOKEN, 1600)

        // SABATATE_MANJU_ITEM
        registerItem("sabatate_manju", SABATATE_MANJU)

        // SABATATE_CHISEL
        registerItem("sabatate_chisel", SABATATE_CHISEL)
    }
}