package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class NetworkThrottleMixin {

    private static long lastChunkPacket = 0;

    @Inject(method = "onChunkData", at = @At("HEAD"), cancellable = true)
    private void optibest_throttleChunkPackets(ChunkDataS2CPacket packet, CallbackInfo ci) {
        if (!OptiBestConfig.networkThrottle) return;

        long now = System.currentTimeMillis();
        // Chunk paketlerini 16ms'de bir işle
        if (now - lastChunkPacket < 16) {
            ci.cancel();
        } else {
            lastChunkPacket = now;
        }
    }
}
