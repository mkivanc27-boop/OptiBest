package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void optibest_disableFog(Camera camera, BackgroundRenderer.FogType fogType,
            FogParameters fogParameters, float viewDistance,
            boolean thickenFog, float tickDelta, CallbackInfo ci) {
        if (OptiBestConfig.fogDisabled) {
            ci.cancel();
        }
    }
}
