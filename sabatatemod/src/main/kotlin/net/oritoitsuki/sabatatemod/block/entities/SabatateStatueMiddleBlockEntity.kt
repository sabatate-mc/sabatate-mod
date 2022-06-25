package net.oritoitsuki.sabatatemod.block.entities

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.Items
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueMiddle

class SabatateStatueMiddleBlockEntity(pos: BlockPos, state: BlockState)
    : BlockEntity(ModBlockEntities.SABATATE_STATUE_MIDDLE, pos, state) {
    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SabatateStatueMiddleBlockEntity) {
            if (!world.isClient) {
                var topBlockEntity: SabatateStatueTopBlockEntity? = null
                if (world.getBlockEntity(pos.up(1)) is SabatateStatueTopBlockEntity) {
                    topBlockEntity = world.getBlockEntity(pos.up(1)) as SabatateStatueTopBlockEntity
                } else if (world.getBlockEntity(pos.up(2)) is SabatateStatueTopBlockEntity) {
                    topBlockEntity = world.getBlockEntity(pos.up(2)) as SabatateStatueTopBlockEntity
                }

                if (topBlockEntity == null) {
                    if (state.get(SabatateStatueMiddle.TORCH)) {
                        world.setBlockState(pos, state.with(SabatateStatueMiddle.TORCH, false), Block.NOTIFY_ALL)
                    }
                } else {
                    if (topBlockEntity.inventory[0].isOf(Items.TORCH)) {
                        if (!state.get(SabatateStatueMiddle.TORCH)) {
                            world.setBlockState(pos, state.with(SabatateStatueMiddle.TORCH, true), Block.NOTIFY_ALL)
                        }
                    } else {
                        if (state.get(SabatateStatueMiddle.TORCH)) {
                            world.setBlockState(pos, state.with(SabatateStatueMiddle.TORCH, false), Block.NOTIFY_ALL)
                        }
                    }
                }
            }
        }
    }

    private var previousTorch = false
}