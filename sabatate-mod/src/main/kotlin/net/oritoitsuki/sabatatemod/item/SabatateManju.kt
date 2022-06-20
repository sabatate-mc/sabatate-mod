package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.SabatateMod

object SabatateManju : Item(
    FabricItemSettings()
        .group(SabatateMod.ITEM_GROUP)
        .maxCount(64)
        .food(FoodComponent.Builder().hunger(4).saturationModifier(6F).alwaysEdible().snack().build())
) {

    @Override
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        tooltip?.add(Text.translatable("item.sabatatemod.sabatate_manju.tooltip-1"))
        tooltip?.add(Text.translatable("item.sabatatemod.sabatate_manju.tooltip-2"))
        tooltip?.add(Text.translatable("item.sabatatemod.sabatate_manju.tooltip-3"))
    }
}