package net.oritoitsuki.sabatatemod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.item.SabatateTokenItem

@Suppress("UNUSED")
object SabatateMod: ModInitializer {
    private const val MOD_ID = "sabatatemod"

    val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier("sabatatemod", "item")) {
        ItemStack(
            SABATATE_TOKEN_ITEM
        )
    }

    private val SABATATE_TOKEN_ITEM = SabatateTokenItem

    override fun onInitialize() {
        Registry.register(Registry.ITEM, Identifier("sabatatemod", "sabatate_token"), SABATATE_TOKEN_ITEM)
        FuelRegistry.INSTANCE.add(SABATATE_TOKEN_ITEM, 50)
    }
}
