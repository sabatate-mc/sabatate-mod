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
import kotlin.random.Random

object SabatateTokenItem : Item(
    FabricItemSettings()
        .group(ModItemGroup.ITEM_GROUP)
        .maxCount(64)
) {
    @Override
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (Random.nextFloat() < 0.95) {
            user?.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1F, Random.nextFloat() * 50F)
        } else {
            user?.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1F, 15F)
        }
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
