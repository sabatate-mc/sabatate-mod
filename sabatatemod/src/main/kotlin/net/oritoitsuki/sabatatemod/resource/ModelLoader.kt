package net.oritoitsuki.sabatatemod.resource

import dev.romainguy.kotlin.math.Float2
import dev.romainguy.kotlin.math.Float3
import dev.romainguy.kotlin.math.Float4
import kotlinx.serialization.json.*
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.oritoitsuki.sabatatemod.model.ModModel

object ModelLoader {
    fun loadModel(resourceManager: ResourceManager, identifier: Identifier): ModModel {
        val inputStream = resourceManager.getResourceOrThrow(identifier).inputStream
        val json = Json.decodeFromStream<JsonObject>(inputStream)

        val model = ModModel()

        val textures = json["textures"]
        if (textures != null) {
            for (entry in textures.jsonObject.entries) {
                val index = entry.key
                val tex = entry.value.jsonPrimitive.content
                model.textures[index] = tex
            }
        }

        val elements = json["elements"]
        if (elements != null) {
            for (element in elements.jsonArray) {
                val obj = element.jsonObject

                var from = Float3()
                var to = Float3()
                var rotationAngle = 0F
                var rotationAxis = "x"
                var origin = Float3()
                var north = Float4() to ""
                var south = Float4() to ""
                var east = Float4() to ""
                var west = Float4() to ""
                var up = Float4() to ""
                var down = Float4() to ""

                val fromJson = obj["from"]
                if (fromJson != null) {
                    val arr = fromJson.jsonArray
                    from = Float3(arr[0].jsonPrimitive.float, arr[1].jsonPrimitive.float, arr[2].jsonPrimitive.float) / 16F
                }

                val toJson = obj["to"]
                if (toJson != null) {
                    val arr = toJson.jsonArray
                    to = Float3(arr[0].jsonPrimitive.float, arr[1].jsonPrimitive.float, arr[2].jsonPrimitive.float) / 16F
                }

                val rotationJson = obj["rotation"]
                if (rotationJson != null) {
                    val obj = rotationJson.jsonObject

                    val angleJson = obj["angle"]
                    val axisJson = obj["axis"]
                    val originJson = obj["origin"]
                    if (angleJson != null && axisJson != null && originJson != null) {
                        rotationAngle = angleJson.jsonPrimitive.float
                        rotationAxis = axisJson.jsonPrimitive.content
                        val arr = originJson.jsonArray
                        origin = Float3(arr[0].jsonPrimitive.float, arr[1].jsonPrimitive.float, arr[2].jsonPrimitive.float) / 16F
                    }
                }

                val facesJson = obj["faces"]
                if (facesJson != null) {
                    val obj = facesJson.jsonObject

                    val northJson = obj["north"]
                    if (northJson != null) {
                        val obj = northJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            north = uv to tex
                        }
                    }

                    val southJson = obj["south"]
                    if (southJson != null) {
                        val obj = southJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            south = uv to tex
                        }
                    }

                    val eastJson = obj["east"]
                    if (eastJson != null) {
                        val obj = eastJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            east = uv to tex
                        }
                    }

                    val westJson = obj["west"]
                    if (westJson != null) {
                        val obj = westJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            west = uv to tex
                        }
                    }

                    val upJson = obj["up"]
                    if (upJson != null) {
                        val obj = upJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            up = uv to tex
                        }
                    }

                    val downJson = obj["down"]
                    if (downJson != null) {
                        val obj = downJson.jsonObject
                        val uvJson = obj["uv"]
                        val textureJson = obj["texture"]
                        if (uvJson != null && textureJson != null) {
                            val arr = uvJson.jsonArray
                            val uv = Float4(
                                arr[0].jsonPrimitive.float,
                                arr[1].jsonPrimitive.float,
                                arr[2].jsonPrimitive.float,
                                arr[3].jsonPrimitive.float,
                            ) / 16F
                            val tex = textureJson.jsonPrimitive.content.trimStart('#')
                            down = uv to tex
                        }
                    }
                }

                val northV0 = ModModel.Vert(
                    Float3(from.x, from.y, from.z),
                    Float2(north.first.z, north.first.w),
                    north.second
                )
                val northV1 = ModModel.Vert(
                    Float3(from.x, to.y, from.z),
                    Float2(north.first.z, north.first.y),
                    north.second
                )
                val northV2 = ModModel.Vert(
                    Float3(to.x, to.y, from.z),
                    Float2(north.first.x, north.first.y),
                    north.second
                )
                val northV3 = ModModel.Vert(
                    Float3(to.x, from.y, from.z),
                    Float2(north.first.x, north.first.w),
                    north.second
                )
                val northFace = ModModel.Quad(northV0, northV1, northV2, northV3, rotationAngle, rotationAxis, origin)

                val southV0 = ModModel.Vert(
                    Float3(from.x, from.y, to.z),
                    Float2(south.first.x, south.first.w),
                    south.second
                )
                val southV1 = ModModel.Vert(
                    Float3(to.x, from.y, to.z),
                    Float2(south.first.z, south.first.w),
                    south.second
                )
                val southV2 = ModModel.Vert(
                    Float3(to.x, to.y, to.z),
                    Float2(south.first.z, south.first.y),
                    south.second
                )
                val southV3 = ModModel.Vert(
                    Float3(from.x, to.y, to.z),
                    Float2(south.first.x, south.first.y),
                    south.second
                )
                val southFace = ModModel.Quad(southV0, southV1, southV2, southV3, rotationAngle, rotationAxis, origin)

                val eastV0 = ModModel.Vert(
                    Float3(to.x, from.y, from.z),
                    Float2(east.first.z, east.first.w),
                    east.second
                )
                val eastV1 = ModModel.Vert(
                    Float3(to.x, to.y, from.z),
                    Float2(east.first.z, east.first.y),
                    east.second
                )
                val eastV2 = ModModel.Vert(
                    Float3(to.x, to.y, to.z),
                    Float2(east.first.x, east.first.y),
                    east.second
                )
                val eastV3 = ModModel.Vert(
                    Float3(to.x, from.y, to.z),
                    Float2(east.first.x, east.first.w),
                    east.second
                )
                val eastFace = ModModel.Quad(eastV0, eastV1, eastV2, eastV3, rotationAngle, rotationAxis, origin)

                val westV0 = ModModel.Vert(
                    Float3(from.x, from.y, from.z),
                    Float2(west.first.x, west.first.w),
                    west.second
                )
                val westV1 = ModModel.Vert(
                    Float3(from.x, from.y, to.z),
                    Float2(west.first.z, west.first.w),
                    west.second
                )
                val westV2 = ModModel.Vert(
                    Float3(from.x, to.y, to.z),
                    Float2(west.first.z, west.first.y),
                    west.second
                )
                val westV3 = ModModel.Vert(
                    Float3(from.x, to.y, from.z),
                    Float2(west.first.x, west.first.y),
                    west.second
                )
                val westFace = ModModel.Quad(westV0, westV1, westV2, westV3, rotationAngle, rotationAxis, origin)

                val upV0 = ModModel.Vert(
                    Float3(from.x, from.y, from.z),
                    Float2(up.first.y, up.first.x),
                    up.second
                )
                val upV1 = ModModel.Vert(
                    Float3(to.x, from.y, from.z),
                    Float2(up.first.w, up.first.x),
                    up.second
                )
                val upV2 = ModModel.Vert(
                    Float3(to.x, from.y, to.z),
                    Float2(up.first.w, up.first.z),
                    up.second
                )
                val upV3 = ModModel.Vert(
                    Float3(from.x, from.y, to.z),
                    Float2(up.first.y, up.first.z),
                    up.second
                )
                val upFace = ModModel.Quad(upV0, upV1, upV2, upV3, rotationAngle, rotationAxis, origin)

                val downV0 = ModModel.Vert(
                    Float3(from.x, to.y, from.z),
                    Float2(down.first.y, down.first.x),
                    down.second
                )
                val downV1 = ModModel.Vert(
                    Float3(from.x, to.y, to.z),
                    Float2(down.first.y, down.first.z),
                    down.second
                )
                val downV2 = ModModel.Vert(
                    Float3(to.x, to.y, to.z),
                    Float2(down.first.w, down.first.z),
                    down.second
                )
                val downV3 = ModModel.Vert(
                    Float3(to.x, to.y, from.z),
                    Float2(down.first.w, down.first.x),
                    down.second
                )
                val downFace = ModModel.Quad(downV0, downV1, downV2, downV3, rotationAngle, rotationAxis, origin)

                model.quads.add(northFace)
                model.quads.add(southFace)
                model.quads.add(eastFace)
                model.quads.add(westFace)
                model.quads.add(upFace)
                model.quads.add(downFace)
            }
        }

        return model
    }
}