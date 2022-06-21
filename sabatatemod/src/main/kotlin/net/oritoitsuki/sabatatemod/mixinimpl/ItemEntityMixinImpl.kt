package net.oritoitsuki.sabatatemod.mixinimpl

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.util.Identifier
import net.oritoitsuki.sabatatemod.item.ModItems

object ItemEntityMixinImpl {
    private var sabatateManjuRecipe: Recipe<*>? = null
    private var sabatateBlockRecipe: Recipe<*>? = null

    fun onPlayerCollision(playerInventory: PlayerInventory, stack: ItemStack): Boolean {
        val player = playerInventory.player
        val recipeManager = player.world.recipeManager

        if (stack.item == ModItems.SABATATE_TOKEN_ITEM) {
            if (sabatateManjuRecipe == null) {
                sabatateManjuRecipe = recipeManager
                    .get(Identifier("sabatatemod", "sabatate_manju")).get()
            }

            if (sabatateBlockRecipe == null) {
                sabatateBlockRecipe = recipeManager
                    .get(Identifier("sabatatemod", "sabatate_token_block")).get()
            }

            player.unlockRecipes(arrayListOf(sabatateManjuRecipe, sabatateBlockRecipe))
        }

        return playerInventory.insertStack(stack)
    }
}