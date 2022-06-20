package net.oritoitsuki.sabatatemod.mixins;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.oritoitsuki.sabatatemod.mixinimpl.ItemEntityMixinImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Redirect(method = "onPlayerCollision", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean onPlayerCollision(PlayerInventory playerInventory, ItemStack stack) {
        return ItemEntityMixinImpl.INSTANCE.onPlayerCollision(playerInventory, stack);
    }
}
