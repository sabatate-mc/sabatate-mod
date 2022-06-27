package net.oritoitsuki.sabatatemod.mixinimpl

import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.EntityDamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object PlayerEntityMixinImpl {
    fun onEatFood(self: PlayerEntity, world: World, stack: ItemStack, callbackInfoReturnable: CallbackInfoReturnable<ItemStack>) {
        if (self.world.isClient) return
        if (stack.registryEntry.key.get().value.namespace != "sabatatemod" ||
            stack.registryEntry.key.get().value.path != "sabatate_manju") return
        val source = EntityDamageSource("sabatate_manju", self)
        self.damage(source, 3.4e38F)
    }

    fun onDeath(self: PlayerEntity, source: DamageSource, amount: Float, callbackInfoReturnable: CallbackInfoReturnable<Boolean>) {
        if (source.name != "sabatate_manju") return
        if (self.world.isClient) return
        val pos = self.pos
        self.world.createExplosion(null, pos.x, pos.y + 1.5F, pos.z, 2F, Explosion.DestructionType.BREAK)
    }
}