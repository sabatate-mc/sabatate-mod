package net.oritoitsuki.sabatatemod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundEvents
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.oritoitsuki.sabatatemod.block.ModBlocks
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueMiddle
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueTop
import kotlin.random.Random

object SabatateChisel : Item(
    FabricItemSettings()
        .group(ModItemGroup.ITEM_GROUP)
        .maxDamage(64)
) {

    @Override
    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        if (context == null) return ActionResult.SUCCESS
        val pos = context.blockPos
        val world = context.world
        val player = context.player
        val blockTop = world.getBlockState(pos)
        val blockMiddle = world.getBlockState(pos.down(1))
        val blockBottom = world.getBlockState(pos.down(2))

        if (blockTop.block == Blocks.STONE && blockMiddle.block == Blocks.STONE && blockBottom.block == Blocks.STONE) {
            if (!world.isClient) {
                val direction = context.playerFacing.opposite
                world.setBlockState(pos, ModBlocks.SABATATE_STATUE_TOP.defaultState.with(Properties.HORIZONTAL_FACING, direction).with(SabatateStatueTop.TORCH, false))
                world.setBlockState(pos.down(1), ModBlocks.SABATATE_STATUE_MIDDLE.defaultState.with(Properties.HORIZONTAL_FACING, direction).with(SabatateStatueMiddle.TORCH, false))
                world.setBlockState(pos.down(2), ModBlocks.SABATATE_STATUE_MIDDLE.defaultState.with(Properties.HORIZONTAL_FACING, direction).with(SabatateStatueMiddle.TORCH, false))
                context.stack.damage(3, player) { it?.sendToolBreakStatus(player?.activeHand)}
            }
            player?.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 1.5F, Random.nextFloat() * 50F)
        }

        return ActionResult.SUCCESS
    }
}