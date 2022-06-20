package net.oritoitsuki.sabatatemod.mixins;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.oritoitsuki.sabatatemod.mixinimpl.PlayerEntityMixinImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("TAIL"), method = "eatFood")
    private void onEatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
        var playerEntity = (PlayerEntity) (Object) this;
        PlayerEntityMixinImpl.INSTANCE.onEatFood(playerEntity, world, stack, callbackInfoReturnable);
    }

    @Inject(at = @At("TAIL"), method = "damage")
    private void onDeath(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        var playerEntity = (PlayerEntity) (Object) this;
        PlayerEntityMixinImpl.INSTANCE.onDeath(playerEntity, source, amount, callbackInfoReturnable);
    }
}
