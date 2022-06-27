package net.oritoitsuki.sabatatemod.screenhandler

import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.registry.Registry
import net.oritoitsuki.sabatatemod.SabatateMod

object ModScreenHandlers {
    val SABATATE_STATUE_TOP_SCREEN_HANDLER: ScreenHandlerType<SabatateStatueTopScreenHandler> =
        register("sabatate_statue_top") { syncId, inv -> SabatateStatueTopScreenHandler(syncId, inv) }

    private fun <T : ScreenHandler> register(id: String, factory: ScreenHandlerType.Factory<T>): ScreenHandlerType<T> {
        return Registry.register(Registry.SCREEN_HANDLER, id, ScreenHandlerType(factory)) as ScreenHandlerType<T>
    }

    fun registerScreenHandler() {
        SabatateMod.LOGGER.debug("Registering Mod Screen Handlers for " + SabatateMod.MOD_ID)
    }
}