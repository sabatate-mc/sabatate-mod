package net.oritoitsuki.sabatatemod.block.entities

import dev.romainguy.kotlin.math.*
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.oritoitsuki.sabatatemod.block.ModBlockEntities
import net.oritoitsuki.sabatatemod.block.blocks.SabatateStatueTop
import net.oritoitsuki.sabatatemod.item.ModItems
import net.oritoitsuki.sabatatemod.screenhandler.SabatateStatueTopScreenHandler
import net.oritoitsuki.sabatatemod.utils.ImplementedInventory
import kotlin.random.Random

class SabatateStatueTopBlockEntity(pos: BlockPos?, state: BlockState?)
    : BlockEntity(ModBlockEntities.SABATATE_STATUE_TOP, pos, state),
        NamedScreenHandlerFactory, ImplementedInventory
{
    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SabatateStatueTopBlockEntity) {
            // Animation
            if (world.isClient) {
                blockEntity.facing = state.get(SabatateStatueTop.FACING)

                when(blockEntity.facing) {
                    Direction.NORTH -> blockEntity.rotateY = 0F
                    Direction.SOUTH -> blockEntity.rotateY = 180F
                    Direction.WEST -> blockEntity.rotateY = 270F
                    Direction.EAST -> blockEntity.rotateY = 90F
                    else -> blockEntity.rotateY = 0F
                }

                val player = MinecraftClient.getInstance().player!!
                val relativePosition = Float3(
                    player.pos.x.toFloat() - pos.x.toFloat(),
                    player.pos.y.toFloat() - pos.y.toFloat() + 1.75F,
                    player.pos.z.toFloat() - pos.z.toFloat(),
                )
                blockEntity.relativePlayerPos = relativePosition
            }

            // Torch
            if (!world.isClient) {
                blockEntity.torch = blockEntity.inventory[0].isOf(Items.TORCH)
                if (blockEntity.torch) {
                    if (!state.get(SabatateStatueTop.TORCH)) {
                        world.setBlockState(pos, state.with(SabatateStatueTop.TORCH, true), Block.NOTIFY_ALL)
                    }
                } else {
                    if (state.get(SabatateStatueTop.TORCH)) {
                        world.setBlockState(pos, state.with(SabatateStatueTop.TORCH, false), Block.NOTIFY_ALL)
                    }
                }
            }

            // Add token
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
    var torch = false
    var rotateY = 0F
    var facing = Direction.NORTH
    var relativePlayerPos = Float3()

    override fun getItems(): DefaultedList<ItemStack> {
        return inventory
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return SabatateStatueTopScreenHandler(syncId, inv, this)
    }

    override fun getDisplayName(): Text {
        return Text.translatable("inventory.sabatatemod.sabatate_statue_top")
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
        torch = nbt.getBoolean("sabatate_statue_top_torch")
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        nbt.putBoolean("sabatate_statue_top_torch", torch)
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }
}