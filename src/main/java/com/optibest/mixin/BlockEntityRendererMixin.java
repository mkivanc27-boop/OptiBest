package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderer.class)
public interface BlockEntityRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void optibest_cullBlockEntities(BlockEntity blockEntity, float tickDelta,
            MatrixStack matrices, VertexConsumerProvider vertexConsumers,
            int light, int overlay, CallbackInfo ci) {
        if (!OptiBestConfig.blockEntityCulling) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        Vec3d playerPos = client.player.getPos();
        Vec3d bePos = Vec3d.ofCenter(blockEntity.getPos());
        if (playerPos.squaredDistanceTo(bePos) > 24 * 24) ci.cancel();
    }
}
