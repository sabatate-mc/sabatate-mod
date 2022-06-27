package net.oritoitsuki.sabatatemod.screenhandler.slot

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.slot.Slot

class SabatateStatueSlot(inventory: Inventory, index: Int, x: Int, y: Int): Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack): Boolean {
        return stack.isOf(Items.TORCH)
    }

    override fun getMaxItemCount(): Int {
        return 1
    }

    override fun getMaxItemCount(stack: ItemStack): Int {
        return 1
    }
}