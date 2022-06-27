package net.oritoitsuki.sabatatemod.block.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueMiddleBlockEntity

class SabatateStatueMiddle :
    BlockWithEntity(FabricBlockSettings.of(Material.STONE).strength(4F).resistance(15F).requiresTool().luminance {
    if (it.get(TORCH_OFF)) 0 else 15
}) {
    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
        val TORCH_OFF: BooleanProperty = BooleanProperty.of("torch_off")

        private fun makeEastShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.3125, 0.9375, 1.0, 0.6875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 1.0, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 1.0, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.8125, 0.8125, 1.0, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 1.0, 0.3125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.6875, 0.90625, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 1.0, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 1.0, 0.90625))
            return shape
        }

        private fun makeNorthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.0, 0.0625, 0.6875, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 1.0, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 1.0, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.1875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0, 0.1875, 0.875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.3125, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0, 0.09375, 0.75, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 1.0, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 1.0, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 1.0, 0.75))
            return shape
        }

        private fun makeSouthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.0, 0.875, 0.6875, 1.0, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 1.0, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0, 0.1875, 0.875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.1875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0, 0.875, 0.75, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.3125, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 1.0, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 1.0, 0.375))
            return shape
        }

        private fun makeWestShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.3125, 0.125, 1.0, 0.6875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 1.0, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 1.0, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.875, 1.0, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.8125, 0.8125, 1.0, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 1.0, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.6875, 0.125, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 1.0, 0.3125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 1.0, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 1.0, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 1.0, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 1.0, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 1.0, 0.125))
            return shape
        }

        val SHAPE_EAST = makeEastShape()
        val SHAPE_NORTH = makeNorthShape()
        val SHAPE_SOUTH = makeSouthShape()
        val SHAPE_WEST = makeWestShape()
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
        builder.add(TORCH_OFF)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(FACING, ctx?.playerFacing?.opposite)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos?,
        context: ShapeContext
    ): VoxelShape {
        return when(state.get(FACING)) {
            Direction.EAST -> SHAPE_EAST
            Direction.NORTH -> SHAPE_NORTH
            Direction.SOUTH -> SHAPE_SOUTH
            Direction.WEST -> SHAPE_WEST
            else -> SHAPE_NORTH
        }
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return SabatateStatueMiddleBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, ModBlockEntities.SABATATE_STATUE_MIDDLE, SabatateStatueMiddleBlockEntity::tick)
    }
}
