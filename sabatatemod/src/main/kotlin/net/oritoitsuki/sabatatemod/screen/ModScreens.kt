package net.oritoitsuki.sabatatemod.screen

import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.screenhandler.ModScreenHandlers

object ModScreens {
    fun registerScreens() {
        SabatateMod.LOGGER.debug("Registering Screens for " + SabatateMod.MOD_ID)

        HandledScreens.register(ModScreenHandlers.SABATATE_STATUE_TOP_SCREEN_HANDLER, ::SabatateStatueTopScreen)
    }
}