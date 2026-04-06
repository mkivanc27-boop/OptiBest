package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.StatisticsS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class StatisticsMixin {

    @Inject(method = "onStatistics", at = @At("HEAD"), cancellable = true)
    private void optibest_skipStatistics(StatisticsS2CPacket packet, CallbackInfo ci) {
        if (OptiBestConfig.statisticsDisable) {
            ci.cancel();
        }
    }
}
