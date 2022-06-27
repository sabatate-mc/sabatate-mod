package net.oritoitsuki.sabatatemod.model

import dev.romainguy.kotlin.math.Float2
import dev.romainguy.kotlin.math.Float3

class ModModel {
    val textures = mutableMapOf<String, String>()
    val quads = mutableListOf<Quad>()

    class Quad(val v0: Vert, val v1: Vert, val v2: Vert, val v3: Vert,
               val rotationAngle: Float = 0F, val rotationAxis: String = "x",
               val origin: Float3 = Float3())
    class Vert(val pos: Float3, val uv: Float2, val texture: String)
}