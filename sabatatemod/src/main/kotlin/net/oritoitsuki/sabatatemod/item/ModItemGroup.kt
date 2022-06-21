package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ModItemGroup {
    val ITEM_GROUP: ItemGroup = FabricItemGroupBuilder.build(Identifier("sabatatemod", "item")) {
        ItemStack(
            ModItems.SABATATE_TOKEN_ITEM
        )
    }
}