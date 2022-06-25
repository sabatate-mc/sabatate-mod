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
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueMiddleBlockEntity

class SabatateStatueMiddle :
    BlockWithEntity(FabricBlockSettings.of(Material.STONE).strength(4F).resistance(15F).requiresTool().luminance {
    if (it.get(SabatateStatueTop.TORCH)) 15 else 0
}) {
    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
        val TORCH: BooleanProperty = BooleanProperty.of("torch")
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
        builder.add(TORCH)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(SabatateStatueTop.FACING, ctx?.playerFacing?.opposite)
    }

    override fun getCullingShape(state: BlockState?, world: BlockView?, pos: BlockPos?): VoxelShape {
        return VoxelShapes.empty()
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
