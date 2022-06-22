package net.oritoitsuki.sabatatemod.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

object SabatateStatueTop : HorizontalFacingBlock(FabricBlockSettings.of(Material.STONE).strength(4F).resistance(15F).requiresTool()) {
    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(Properties.HORIZONTAL_FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(Properties.HORIZONTAL_FACING, ctx?.playerFacing?.opposite)
    }

    override fun getCullingShape(state: BlockState?, world: BlockView?, pos: BlockPos?): VoxelShape {
        return VoxelShapes.empty()
    }
}