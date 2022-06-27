package net.oritoitsuki.sabatatemod.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueMiddle
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueTop
import net.oritoitsuki.sabatatemod.block.blocks.SabatateTokenBlock
import net.oritoitsuki.sabatatemod.item.ModItemGroup

object ModBlocks {
    private val SABATATE_TOKEN_BLOCK = SabatateTokenBlock
    val SABATATE_STATUE_TOP = SabatateStatueTop()
    val SABATATE_STATUE_MIDDLE = SabatateStatueMiddle()

    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registry.BLOCK, Identifier(SabatateMod.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item {
        return Registry.register(Registry.ITEM, Identifier(SabatateMod.MOD_ID, name),
            BlockItem(block, FabricItemSettings().group(ModItemGroup.ITEM_GROUP))
        )
    }

    fun registerModBlocks() {
        SabatateMod.LOGGER.debug("Registering Mod Blocks for " + SabatateMod.MOD_ID)

        // SABATATE_TOKEN_BLOCK
        registerBlock("sabatate_token_block", SABATATE_TOKEN_BLOCK)
        FuelRegistry.INSTANCE.add(SABATATE_TOKEN_BLOCK, 16000)

        // SABATATE_STATUE
        registerBlock("sabatate_statue_top", SABATATE_STATUE_TOP)
        registerBlock("sabatate_statue_middle", SABATATE_STATUE_MIDDLE)
    }
}