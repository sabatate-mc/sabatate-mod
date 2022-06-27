package net.oritoitsuki.sabatatemod.block.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueTopBlockEntity

class SabatateStatueTop :
    BlockWithEntity(FabricBlockSettings.of(Material.STONE).strength(4F).resistance(15F).requiresTool().luminance {
        if (it.get(TORCH)) 15 else 0
    }) {
    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
        val TORCH: BooleanProperty = BooleanProperty.of("torch")

        private fun makeEastShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999999, 0.0, 0.31249999999999994, 0.9374999999999999, 0.328125, 0.6875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.8749999999999999,
                    0.328125,
                    0.39062499999999994,
                    0.9374999999999999,
                    0.359375,
                    0.609375
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.3749999999999999,
                    0.0,
                    0.06249999999999989,
                    0.6249999999999999,
                    0.75,
                    0.12499999999999989
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.3749999999999999, 0.0, 0.875, 0.6249999999999999, 0.75, 0.9375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.06249999999999989, 0.0, 0.37499999999999994, 0.12499999999999989, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999989, 0.0, 0.18749999999999994, 0.8749999999999999, 0.75, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.1874999999999999,
                    0.0,
                    0.12499999999999994,
                    0.8124999999999999,
                    0.75,
                    0.18749999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1874999999999999, 0.0, 0.8125, 0.8124999999999999, 0.75, 0.875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999999, 0.0, 0.37499999999999994, 0.9218749999999999, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.8749999999999999,
                    0.0,
                    0.24999999999999994,
                    0.9062499999999999,
                    0.75,
                    0.37499999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999999, 0.0, 0.625, 0.9062499999999999, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.09374999999999989,
                    0.0,
                    0.24999999999999994,
                    0.12499999999999989,
                    0.75,
                    0.37499999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999989, 0.0, 0.625, 0.12499999999999989, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.6249999999999999,
                    0.0,
                    0.09374999999999994,
                    0.7499999999999999,
                    0.75,
                    0.12499999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.2499999999999999,
                    0.0,
                    0.09374999999999994,
                    0.3749999999999999,
                    0.75,
                    0.12499999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6249999999999999, 0.0, 0.875, 0.7499999999999999, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.2499999999999999, 0.0, 0.875, 0.3749999999999999, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1874999999999999, 0.75, 0.24999999999999994, 0.8124999999999999, 0.8125, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.2499999999999999,
                    0.75,
                    0.18749999999999994,
                    0.7499999999999999,
                    0.8125,
                    0.24999999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.2499999999999999, 0.75, 0.75, 0.7499999999999999, 0.8125, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.75, 0.37499999999999994, 0.8437499999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1562499999999999, 0.75, 0.37499999999999994, 0.1874999999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.3749999999999999,
                    0.75,
                    0.15624999999999994,
                    0.6249999999999999,
                    0.8125,
                    0.18749999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.3749999999999999, 0.75, 0.8125, 0.6249999999999999, 0.8125, 0.84375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.75, 0.37499999999999994, 0.8437499999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.9062499999999999, 0.375, 0.43749999999999994, 0.9687499999999999, 0.4375, 0.5625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.8124999999999999,
                    0.4375,
                    0.21874999999999994,
                    0.9374999999999999,
                    0.5625,
                    0.42187499999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.7656249999999999,
                    0.4375,
                    0.14062499999999994,
                    0.8906249999999999,
                    0.5625,
                    0.24999999999999994
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.4375, 0.578125, 0.9374999999999999, 0.5625, 0.78125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.7656249999999999, 0.4375, 0.71875, 0.8906249999999999, 0.5625, 0.828125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.125, 0.12499999999999994, 1.25, 0.25, 0.31249999999999994)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.8124999999999999,
                    0.0625,
                    0.12499999999999994,
                    0.9374999999999999,
                    0.125,
                    0.31249999999999994
                )
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8124999999999999, 0.125, 0.6875, 1.25, 0.25, 0.875))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.0625, 0.6875, 0.9374999999999999, 0.125, 0.875)
            )

            return shape
        }

        private fun makeNorthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.31249999999999983, 0.0, 0.0625, 0.6874999999999998, 0.328125, 0.125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.39062499999999983, 0.328125, 0.0625, 0.6093749999999998, 0.359375, 0.125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.06249999999999978, 0.0, 0.375, 0.12499999999999978, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999998, 0.0, 0.375, 0.9374999999999998, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.37499999999999983, 0.0, 0.875, 0.6249999999999998, 0.75, 0.9375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.18749999999999983, 0.0, 0.125, 0.8124999999999998, 0.75, 0.875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999983, 0.0, 0.1875, 0.18749999999999983, 0.75, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999998, 0.0, 0.1875, 0.8749999999999998, 0.75, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.37499999999999983, 0.0, 0.078125, 0.6249999999999998, 0.75, 0.125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.24999999999999983, 0.0, 0.09375, 0.37499999999999983, 0.75, 0.125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6249999999999998, 0.0, 0.09375, 0.7499999999999998, 0.75, 0.125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.24999999999999983, 0.0, 0.875, 0.37499999999999983, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6249999999999998, 0.0, 0.875, 0.7499999999999998, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999983, 0.0, 0.25, 0.12499999999999983, 0.75, 0.375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999983, 0.0, 0.625, 0.12499999999999983, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999998, 0.0, 0.25, 0.9062499999999998, 0.75, 0.375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999998, 0.0, 0.625, 0.9062499999999998, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.24999999999999983, 0.75, 0.1875, 0.7499999999999998, 0.8125, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.18749999999999983, 0.75, 0.25, 0.24999999999999983, 0.8125, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.7499999999999998, 0.75, 0.25, 0.8124999999999998, 0.8125, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.37499999999999983, 0.75, 0.15625, 0.6249999999999998, 0.8125, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.37499999999999983, 0.75, 0.8125, 0.6249999999999998, 0.8125, 0.84375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.15624999999999983, 0.75, 0.375, 0.18749999999999983, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999998, 0.75, 0.375, 0.8437499999999998, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.37499999999999983, 0.75, 0.15625, 0.6249999999999998, 0.8125, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.43749999999999983, 0.375, 0.03125, 0.5624999999999998, 0.4375, 0.09375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.21874999999999983, 0.4375, 0.0625, 0.42187499999999983, 0.5625, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.14062499999999983, 0.4375, 0.109375, 0.24999999999999983, 0.5625, 0.234375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.5781249999999998, 0.4375, 0.0625, 0.7812499999999998, 0.5625, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.7187499999999998, 0.4375, 0.109375, 0.8281249999999998, 0.5625, 0.234375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999983, 0.125, -0.25, 0.31249999999999983, 0.25, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999983, 0.0625, 0.0625, 0.31249999999999983, 0.125, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6874999999999998, 0.125, -0.25, 0.8749999999999998, 0.25, 0.1875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6874999999999998, 0.0625, 0.0625, 0.8749999999999998, 0.125, 0.1875)
            )

            return shape
        }

        private fun makeSouthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape =
                VoxelShapes.union(shape, VoxelShapes.cuboid(0.31249999999999994, 0.0, 0.875, 0.6875, 0.328125, 0.9375))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.39062499999999994, 0.328125, 0.875, 0.609375, 0.359375, 0.9375)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 0.75, 0.625))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.062499999999999944, 0.0, 0.375, 0.12499999999999994, 0.75, 0.625)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.37499999999999994, 0.0, 0.0625, 0.625, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.18749999999999994, 0.0, 0.125, 0.8125, 0.75, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0, 0.1875, 0.875, 0.75, 0.8125))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999994, 0.0, 0.1875, 0.18749999999999994, 0.75, 0.8125)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.37499999999999994, 0.0, 0.875, 0.625, 0.75, 0.921875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 0.75, 0.90625))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.24999999999999994, 0.0, 0.875, 0.37499999999999994, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 0.75, 0.125))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.24999999999999994, 0.0, 0.09375, 0.37499999999999994, 0.75, 0.125)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 0.75, 0.375))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999994, 0.0, 0.625, 0.12499999999999994, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999994, 0.0, 0.25, 0.12499999999999994, 0.75, 0.375)
            )
            shape =
                VoxelShapes.union(shape, VoxelShapes.cuboid(0.24999999999999994, 0.75, 0.1875, 0.75, 0.8125, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.75, 0.25, 0.8125, 0.8125, 0.75))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.18749999999999994, 0.75, 0.25, 0.24999999999999994, 0.8125, 0.75)
            )
            shape =
                VoxelShapes.union(shape, VoxelShapes.cuboid(0.37499999999999994, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape =
                VoxelShapes.union(shape, VoxelShapes.cuboid(0.37499999999999994, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.15624999999999994, 0.75, 0.375, 0.18749999999999994, 0.8125, 0.625)
            )
            shape =
                VoxelShapes.union(shape, VoxelShapes.cuboid(0.37499999999999994, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.43749999999999994, 0.375, 0.90625, 0.5625, 0.4375, 0.96875)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.4375, 0.8125, 0.78125, 0.5625, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.4375, 0.765625, 0.859375, 0.5625, 0.890625))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.21874999999999994, 0.4375, 0.8125, 0.42187499999999994, 0.5625, 0.9375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.17187499999999994, 0.4375, 0.765625, 0.28124999999999994, 0.5625, 0.890625)
            )
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.125, 0.8125, 0.875, 0.25, 1.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0625, 0.8125, 0.875, 0.125, 0.9375))
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999994, 0.125, 0.8125, 0.31249999999999994, 0.25, 1.25)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999994, 0.0625, 0.8125, 0.31249999999999994, 0.125, 0.9375)
            )

            return shape
        }

        private fun makeWestShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.06249999999999989, 0.0, 0.31250000000000006, 0.12499999999999989, 0.328125, 0.6875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.06249999999999989,
                    0.328125,
                    0.39062500000000006,
                    0.12499999999999989,
                    0.359375,
                    0.609375
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.3749999999999999,
                    0.0,
                    0.8750000000000001,
                    0.6249999999999999,
                    0.75,
                    0.9375000000000001
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.3749999999999999,
                    0.0,
                    0.06250000000000006,
                    0.6249999999999999,
                    0.75,
                    0.12500000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999999, 0.0, 0.37500000000000006, 0.9374999999999999, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.12499999999999989, 0.0, 0.18750000000000006, 0.8749999999999999, 0.75, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1874999999999999, 0.0, 0.8125, 0.8124999999999999, 0.75, 0.875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.1874999999999999,
                    0.0,
                    0.12500000000000006,
                    0.8124999999999999,
                    0.75,
                    0.18750000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.07812499999999989, 0.0, 0.37500000000000006, 0.12499999999999989, 0.75, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.09374999999999989, 0.0, 0.625, 0.12499999999999989, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.09374999999999989,
                    0.0,
                    0.25000000000000006,
                    0.12499999999999989,
                    0.75,
                    0.37500000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8749999999999999, 0.0, 0.625, 0.9062499999999999, 0.75, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.8749999999999999,
                    0.0,
                    0.25000000000000006,
                    0.9062499999999999,
                    0.75,
                    0.37500000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.2499999999999999, 0.0, 0.875, 0.3749999999999999, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.6249999999999999, 0.0, 0.875, 0.7499999999999999, 0.75, 0.90625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.2499999999999999,
                    0.0,
                    0.09375000000000006,
                    0.3749999999999999,
                    0.75,
                    0.12500000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.6249999999999999,
                    0.0,
                    0.09375000000000006,
                    0.7499999999999999,
                    0.75,
                    0.12500000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1874999999999999, 0.75, 0.25000000000000006, 0.8124999999999999, 0.8125, 0.75)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.2499999999999999, 0.75, 0.75, 0.7499999999999999, 0.8125, 0.8125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.2499999999999999,
                    0.75,
                    0.18750000000000006,
                    0.7499999999999999,
                    0.8125,
                    0.25000000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1562499999999999, 0.75, 0.37500000000000006, 0.1874999999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.8124999999999999, 0.75, 0.37500000000000006, 0.8437499999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.3749999999999999, 0.75, 0.8125, 0.6249999999999999, 0.8125, 0.84375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.3749999999999999,
                    0.75,
                    0.15625000000000006,
                    0.6249999999999999,
                    0.8125,
                    0.18750000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.1562499999999999, 0.75, 0.37500000000000006, 0.1874999999999999, 0.8125, 0.625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.03124999999999989, 0.375, 0.43750000000000006, 0.09374999999999989, 0.4375, 0.5625)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.06249999999999989, 0.4375, 0.578125, 0.1874999999999999, 0.5625, 0.78125)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.10937499999999989, 0.4375, 0.75, 0.2343749999999999, 0.5625, 0.859375)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.06249999999999989,
                    0.4375,
                    0.21875000000000006,
                    0.1874999999999999,
                    0.5625,
                    0.42187500000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.10937499999999989,
                    0.4375,
                    0.17187500000000006,
                    0.2343749999999999,
                    0.5625,
                    0.28125000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(-0.2500000000000001, 0.125, 0.6875, 0.1874999999999999, 0.25, 0.875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(0.06249999999999989, 0.0625, 0.6875, 0.1874999999999999, 0.125, 0.875)
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    -0.2500000000000001,
                    0.125,
                    0.12500000000000006,
                    0.1874999999999999,
                    0.25,
                    0.31250000000000006
                )
            )
            shape = VoxelShapes.union(
                shape,
                VoxelShapes.cuboid(
                    0.06249999999999989,
                    0.0625,
                    0.12500000000000006,
                    0.1874999999999999,
                    0.125,
                    0.31250000000000006
                )
            )

            return shape
        }

        val SHAPE_EAST = makeEastShape()
        val SHAPE_NORTH = makeNorthShape()
        val SHAPE_SOUTH = makeSouthShape()
        val SHAPE_WEST = makeWestShape()
    }


    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
        builder.add(TORCH)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(FACING, ctx?.playerFacing?.opposite)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
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

    override fun getRenderType(state: BlockState?): BlockRenderType? {
        return BlockRenderType.MODEL
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return SabatateStatueTopBlockEntity(pos, state)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        moved: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is SabatateStatueTopBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, ModBlockEntities.SABATATE_STATUE_TOP, SabatateStatueTopBlockEntity::tick)
    }
}