package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(ChatHud.class)
public class HudRendererMixin {

    private static long lastRender = 0;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void optibest_throttleChatRender(CallbackInfo ci) {
        if (!OptiBestConfig.hudOptimization) return;

        long now = System.currentTimeMillis();
        // Chat render'ı 50ms'de bir yap (20fps yeterli chat için)
        if (now - lastRender < 50) {
            ci.cancel();
        } else {
            lastRender = now;
        }
    }
}
