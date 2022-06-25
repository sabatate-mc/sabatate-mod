package net.oritoitsuki.sabatatemod.screenhandler

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType

class SabatateStatueTopScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory = SimpleInventory(1)
):
    ScreenHandler(ModScreenHandlers.SABATATE_STATUE_TOP_SCREEN_HANDLER, syncId) {

    init {
        checkSize(inventory, 1)
        inventory.onOpen(playerInventory.player)

        println(inventory is SimpleInventory)

        // Our Inventory
        addSlot(Slot(inventory, 0, 8 + 18 * 4, 20))

        // player inventory
        for (row in 0 until 3) {
            for (col in 0 until 9) {
                addSlot(Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 18 * 3 + row * 18 - 3))
            }
        }

        // player hot bar
        for (col in 0 until 9) {
            addSlot(Slot(playerInventory, col, 8 + col * 18, 109))
        }
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    override fun transferSlot(player: PlayerEntity, index: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot = slots[index]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (index < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }

            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }

        return newStack
    }

    override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
        if (slotIndex == 0) {
            // さばたて石像のスタックをクリックしたとき
            when(actionType) {
                SlotActionType.PICKUP, SlotActionType.PICKUP_ALL -> {
                    // ピックアップ（現在のマウスのスタックと交換）の場合
                    if (cursorStack.registryEntry.key.get().value.namespace == "minecraft" &&
                        cursorStack.registryEntry.key.get().value.path == "torch") {
                        // カーソルがトーチの場合、石像のスタックが空だったら、
                        // カーソルのスタックから一つ減らして、石像のスタックに一つトーチを置く
                        val stack = getSlot(slotIndex).stack
                        if (stack.isEmpty) {
                            val torch = cursorStack.split(1)
                            inventory.setStack(0, torch)
                            inventory.markDirty()
                        }
                    } else if (cursorStack.isEmpty) {
                        // カーソルが空の場合、カーソルにトーチを渡して、石像のスタックからトーチを取り除く
                        cursorStack = inventory.getStack(0)
                        inventory.clear()
                        inventory.markDirty()
                    } else {
                        // それ以外の場合、操作をキャンセルする
                        return
                    }
                }
                SlotActionType.QUICK_MOVE -> {
                    // クイックムーブの場合、実行する
                    super.onSlotClick(slotIndex, button, actionType, player)
                    return
                }
            }
        } else if (slotIndex > 0) {
            // playerのinventoryのスタックをクリックしたとき
            when (actionType) {
                SlotActionType.QUICK_MOVE -> {
                    // クイックムーブの場合、スタックがトーチのときだけ実行
                    val stack = getSlot(slotIndex).stack
                    if (stack.registryEntry.key.get().value.namespace == "minecraft" &&
                        stack.registryEntry.key.get().value.path == "torch") {
                        // 石像のスタックに一つトーチを置く
                        val statueStack = inventory.getStack(0)
                        if (statueStack.isEmpty) {
                            val torch = stack.split(1)
                            inventory.setStack(0, torch)
                            inventory.markDirty()
                        }
                    }
                    return
                }
                else -> {
                    // それ以外のアクションの場合は実行する
                    super.onSlotClick(slotIndex, button, actionType, player)
                }
            }
        } else {
            // network用
            super.onSlotClick(slotIndex, button, actionType, player)
        }
    }
}