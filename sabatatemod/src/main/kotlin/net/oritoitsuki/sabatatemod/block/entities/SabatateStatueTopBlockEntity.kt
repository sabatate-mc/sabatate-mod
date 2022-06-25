package net.oritoitsuki.sabatatemod.block.entities

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueTop
import net.oritoitsuki.sabatatemod.screenhandler.SabatateStatueTopScreenHandler
import net.oritoitsuki.sabatatemod.item.ModItems
import kotlin.random.Random

class SabatateStatueTopBlockEntity(pos: BlockPos?, state: BlockState?)
    : BlockEntity(ModBlockEntities.SABATATE_STATUE_TOP, pos, state),
        NamedScreenHandlerFactory, Inventory
{
    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SabatateStatueTopBlockEntity) {
            if (!world.isClient) {
                if (blockEntity.inventory[0].isOf(Items.TORCH)) {
                    if (!state.get(SabatateStatueTop.TORCH)) {
                        world.setBlockState(pos, state.with(SabatateStatueTop.TORCH, true), Block.NOTIFY_ALL)
                    }
                } else {
                    if (state.get(SabatateStatueTop.TORCH)) {
                        world.setBlockState(pos, state.with(SabatateStatueTop.TORCH, false), Block.NOTIFY_ALL)
                    }
                }
            }

            blockEntity.timer--
            if (blockEntity.timer != 0) return

            for (player in world.players) {
                val distance0 = pos.getSquaredDistance(player.pos)
                val distance1 = pos.down(1).getSquaredDistance(player.pos)
                val distance2 = pos.down(2).getSquaredDistance(player.pos)
                if (distance0 < 9F || distance1 < 9F || distance2 < 9F) {
                    if (player.isSneaking) {
                        if (world.isClient) {
                            player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.3F, 100F)
                        } else {
                            player.inventory.insertStack(ItemStack(ModItems.SABATATE_TOKEN, Random.nextInt(1, 3)))
                        }
                    }
                }
            }

            blockEntity.timer = 30
        }
    }

    private var timer = 50
    val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(1, ItemStack.EMPTY)

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return SabatateStatueTopScreenHandler(syncId, inv, this)
    }

    override fun getDisplayName(): Text {
        return Text.translatable("inventory.sabatatemod.sabatate_statue_top")
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        println(inventory.isEmpty())
        Inventories.writeNbt(nbt, inventory)
    }

    override fun size(): Int {
        return inventory.size
    }

    override fun isEmpty(): Boolean {
        return inventory.isEmpty()
    }

    override fun getStack(slot: Int): ItemStack {
        return inventory[slot]
    }

    override fun removeStack(slot: Int, amount: Int): ItemStack {
        val result = Inventories.splitStack(inventory, slot, amount)
        if (!result.isEmpty) {
            markDirty()
        }
        return result
    }

    override fun removeStack(slot: Int): ItemStack {
        return Inventories.removeStack(inventory, slot)
    }

    override fun setStack(slot: Int, stack: ItemStack) {
        inventory[slot] = stack
        if (stack.count > maxCountPerStack) {
            stack.count = maxCountPerStack
        }
    }

    override fun canPlayerUse(player: PlayerEntity?): Boolean {
        return true
    }

    override fun clear() {
        inventory.clear()
    }
}