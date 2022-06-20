package net.oritoitsuki.sabatatemod.mixins

import net.minecraft.entity.ItemEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.EntityDamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeManager
import net.minecraft.util.Identifier
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import net.oritoitsuki.sabatatemod.SabatateMod
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.Redirect
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(PlayerEntity::class)
class SabatateManjuPlayerEntityMixin {
    @Inject(at = [At("TAIL")], method = ["eatFood"])
    private fun onEatFood(world: World, stack: ItemStack, callbackInfoReturnable: CallbackInfoReturnable<ItemStack>) {
        val self = this as PlayerEntity
        val source = EntityDamageSource("sabatate_manju", self)
        self.damage(source, 3.4e38F)
    }

    @Inject(at = [At("TAIL")], method = ["damage"])
    private fun onDeath(source: DamageSource, amount: Float, callbackInfoReturnable: CallbackInfoReturnable<Boolean>) {
        if (source.name != "sabatate_manju") return
        val self = this as PlayerEntity
        if (self.world.isClient) return
        val pos = self.pos
        self.world.createExplosion(null, pos.x, pos.y + 1.5F, pos.z, 2F, Explosion.DestructionType.BREAK)
    }
}

@Mixin(ItemEntity::class)
class SabatateManjuItemEntityMixin {
    private var sabatateManjuRecipe: Recipe<*>? = null

    @Redirect(at = At("INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(Lnet/minecraft/item/ItemStack;)Z"), method = ["onPlayerCollision"])
    private fun onPlayerCollision(playerInventory: PlayerInventory, stack: ItemStack): Boolean {
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