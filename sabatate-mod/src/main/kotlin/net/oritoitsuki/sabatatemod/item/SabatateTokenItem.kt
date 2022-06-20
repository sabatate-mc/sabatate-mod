package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.SabatateMod

object SabatateTokenItem : Item(FabricItemSettings().group(SabatateMod.ITEM_GROUP).maxCount(16)) {
    @Override
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        user?.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 15.0F)
        return TypedActionResult.success(user?.getStackInHand(hand))
    }

    @Override
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        tooltip?.add(Text.translatable("item.sabatatemod.sabatate_token.tooltip"))
    }
}
