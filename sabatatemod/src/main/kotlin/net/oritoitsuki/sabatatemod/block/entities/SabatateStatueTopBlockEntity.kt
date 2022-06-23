package net.oritoitsuki.sabatatemod.block.entities

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.item.ModItems
import kotlin.random.Random

class SabatateStatueTopBlockEntity(pos: BlockPos?, state: BlockState?)
    : BlockEntity(ModBlockEntities.SABATATE_STATUE_TOP, pos, state)
{
    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SabatateStatueTopBlockEntity) {
            blockEntity.timer--
            if (blockEntity.timer != 0) return

            for (player in world.players) {
                val distance0 = pos.getSquaredDistance(player.pos)
                val distance1 = pos.down(1).getSquaredDistance(player.pos)
                val distance2 = pos.down(2).getSquaredDistance(player.pos)
                if (distance0 < 9F || distance1 < 9F || distance2 < 9F) {
                    if (player.isSneaking) {
                        if (world.isClient) {
                            player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.3F, 100F)
                        } else {
                            player.inventory.insertStack(ItemStack(ModItems.SABATATE_TOKEN, Random.nextInt(1, 3)))
                        }
                    }
                }
            }

            blockEntity.timer = 30
        }
    }

    private var timer = 50
}