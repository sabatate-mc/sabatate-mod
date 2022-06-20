package net.oritoitsuki.sabatatemod.mixinimpl

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.util.Identifier
import net.oritoitsuki.sabatatemod.SabatateMod

object ItemEntityMixinImpl {
    private var sabatateManjuRecipe: Recipe<*>? = null

    fun onPlayerCollision(playerInventory: PlayerInventory, stack: ItemStack): Boolean {
        if (stack.item != SabatateMod.SABATATE_TOKEN_ITEM) return playerInventory.insertStack(stack)

        if (sabatateManjuRecipe == null) {
            val recipe = playerInventory.player.world.recipeManager.get(Identifier("sabatatemod", "sabatate_manju"))
            if (recipe.isEmpty) return playerInventory.insertStack(stack)
            sabatateManjuRecipe = recipe.get()
        }

        sabatateManjuRecipe?.let {
            playerInventory.player.unlockRecipes(arrayListOf(it))
        }

        return playerInventory.insertStack(stack)
    }
}