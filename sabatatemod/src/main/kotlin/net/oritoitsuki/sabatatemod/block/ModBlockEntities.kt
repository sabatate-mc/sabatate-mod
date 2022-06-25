package net.oritoitsuki.sabatatemod.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueMiddleBlockEntity
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueTopBlockEntity

object ModBlockEntities {
    val SABATATE_STATUE_TOP: BlockEntityType<SabatateStatueTopBlockEntity> = FabricBlockEntityTypeBuilder
        .create(::SabatateStatueTopBlockEntity, ModBlocks.SABATATE_STATUE_TOP).build(null)
    val SABATATE_STATUE_MIDDLE: BlockEntityType<SabatateStatueMiddleBlockEntity> = FabricBlockEntityTypeBuilder
        .create(::SabatateStatueMiddleBlockEntity, ModBlocks.SABATATE_STATUE_MIDDLE).build(null)

    private fun <T: BlockEntity> registerBlockEntity(name: String, blockEntity: BlockEntityType<T>): BlockEntityType<T> {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(SabatateMod.MOD_ID, name), blockEntity)
    }

    fun registerBlockEntities() {
        SabatateMod.LOGGER.debug("Registering Mod Block Entities for " + SabatateMod.MOD_ID)

        // SABATATE_STATUE_TOP
        registerBlockEntity("sabatate_statue_top", SABATATE_STATUE_TOP)
        // SABATATE_STATUE_MIDDLE
        registerBlockEntity("sabatate_statue_middle", SABATATE_STATUE_MIDDLE)
    }
}