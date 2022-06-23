package net.oritoitsuki.sabatatemod.block.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material

object SabatateTokenBlock : Block(FabricBlockSettings.of(Material.METAL).strength(2F).requiresTool())