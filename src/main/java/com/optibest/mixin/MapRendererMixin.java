package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.render.MapRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MapRenderer.class)
public class MapRendererMixin {

    @Inject(method = "updateTexture", at = @At("HEAD"), cancellable = true)
    private void optibest_skipMapUpdate(CallbackInfo ci) {
        if (OptiBestConfig.mapRenderOff) ci.cancel();
    }
}
