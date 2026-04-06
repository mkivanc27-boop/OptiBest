package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderDispatcher.class)
public class TileEntityCullMixin {

    @Inject(method = "render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            at = @At("HEAD"), cancellable = true)
    private <E extends BlockEntity> void optibest_cullBlockEntities(
            E entity, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider provider, CallbackInfo ci) {

        if (!OptiBestConfig.skipOffscreenBlockEntities) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        Vec3d playerPos = client.player.getPos();
        Vec3d entityPos = Vec3d.ofCenter(entity.getPos());

        if (playerPos.squaredDistanceTo(entityPos) > 32 * 32) {
            ci.cancel();
        }
    }
}
