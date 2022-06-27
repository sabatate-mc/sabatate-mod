package net.oritoitsuki.sabatatemod.block.renderer

import dev.romainguy.kotlin.math.*
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.*
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3f
import net.oritoitsuki.sabatatemod.SabatateMod
import net.oritoitsuki.sabatatemod.block.entities.SabatateStatueTopBlockEntity
import net.oritoitsuki.sabatatemod.model.ModModel
import net.oritoitsuki.sabatatemod.resource.ModCache
import kotlin.math.atan2

@Environment(EnvType.CLIENT)
object SabatateStatueTopRenderer: BlockEntityRenderer<SabatateStatueTopBlockEntity> {
    private fun renderQuad(vertices: VertexConsumer, matrices: MatrixStack, quad: ModModel.Quad, lightLevel: Int) {
        matrices.push()
        matrices.translate(quad.origin.x.toDouble(), quad.origin.y.toDouble(), quad.origin.z.toDouble())
        when (quad.rotationAxis) {
            "x" -> matrices.multiply(Vec3f.POSITIVE_X.getRadialQuaternion(radians(quad.rotationAngle)))
            "y" -> matrices.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion(radians(quad.rotationAngle)))
            "z" -> matrices.multiply(Vec3f.POSITIVE_Z.getRadialQuaternion(radians(quad.rotationAngle)))
        }
        matrices.translate(-quad.origin.x.toDouble(), -quad.origin.y.toDouble(), -quad.origin.z.toDouble())

        val entry = matrices.peek()
        val m = entry.positionMatrix
        val normal = entry.normalMatrix
        val n = normalize(cross(quad.v1.pos - quad.v0.pos, quad.v3.pos - quad.v0.pos))

        vertices
            .vertex(m, quad.v0.pos.x, quad.v0.pos.y, quad.v0.pos.z)
            .color(1F, 1F, 1F, 1F)
            .texture(quad.v0.uv.x, quad.v0.uv.y)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(lightLevel)
            .normal(normal, n.x, n.y, n.z)
            .next()
        vertices
            .vertex(m, quad.v1.pos.x, quad.v1.pos.y, quad.v1.pos.z)
            .color(1F, 1F, 1F, 1F)
            .texture(quad.v1.uv.x, quad.v1.uv.y)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(lightLevel)
            .normal(normal, n.x, n.y, n.z)
            .next()
        vertices
            .vertex(m, quad.v2.pos.x, quad.v2.pos.y, quad.v2.pos.z)
            .color(1F, 1F, 1F, 1F)
            .texture(quad.v2.uv.x, quad.v2.uv.y)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(lightLevel)
            .normal(normal, n.x, n.y, n.z)
            .next()
        vertices
            .vertex(m, quad.v3.pos.x, quad.v3.pos.y, quad.v3.pos.z)
            .color(1F, 1F, 1F, 1F)
            .texture(quad.v3.uv.x, quad.v3.uv.y)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(lightLevel)
            .normal(normal, n.x, n.y, n.z)
            .next()

        matrices.pop()
    }

    override fun render(
        entity: SabatateStatueTopBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        matrices.push()
        matrices.translate(0.5, 0.0, 0.5)
        matrices.multiply(Vec3f.NEGATIVE_Y.getRadialQuaternion(radians(entity.rotateY)))
        matrices.translate(-0.5, 0.0, -0.5)

        if (entity.torch) {
            val direction = normalize(Float4(entity.relativePlayerPos)).xyz
            matrices.push()
            matrices.translate(0.5, 0.5, 0.5)
            val rotateY = when(entity.facing) {
                Direction.NORTH -> atan2(-direction.x, -direction.z)
                Direction.EAST -> atan2(-direction.z, direction.x)
                Direction.SOUTH -> atan2(direction.x, direction.z)
                Direction.WEST -> atan2(direction.z, -direction.x)
                else -> atan2(-direction.x, -direction.z)
            }
            val rotateX = atan2(direction.y, length(Float2(direction.x, direction.z)))
            val rotateYClamped = clamp(rotateY / 1.8F, radians(-16F), radians(16F))
            val rotateXClamped = clamp(rotateX / 2.0F, radians(-7F), radians(7F))
            matrices.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion(rotateYClamped))
            matrices.multiply(Vec3f.POSITIVE_X.getRadialQuaternion(rotateXClamped))
            matrices.translate(-0.5, -0.5, -0.5)

            val leftEye = ModCache.models[Identifier(SabatateMod.MOD_ID, "modmodels/models/sabatate_statue_top_left_eye.json")]
            if (leftEye != null) {
                for (quad in leftEye.quads) {
                    val texture = Identifier(leftEye.textures[quad.v0.texture] + ".png")
                    val layer = RenderLayer.getEntityCutout(texture)
                    val vertices = vertexConsumers.getBuffer(layer)
                    MinecraftClient.getInstance().textureManager.bindTexture(texture)
                    renderQuad(vertices, matrices, quad, light)
                }
            }

            val rightEye = ModCache.models[Identifier(SabatateMod.MOD_ID, "modmodels/models/sabatate_statue_top_right_eye.json")]
            if (rightEye != null) {
                for (quad in rightEye.quads) {
                    val texture = Identifier(rightEye.textures[quad.v0.texture] + ".png")
                    val layer = RenderLayer.getEntityCutout(texture)
                    val vertices = vertexConsumers.getBuffer(layer)
                    MinecraftClient.getInstance().textureManager.bindTexture(texture)
                    renderQuad(vertices, matrices, quad, light)
                }
            }

            matrices.pop()
        }

        matrices.pop()
    }
}