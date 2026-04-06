package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityShadowMixin {

    @Inject(method = "renderShadow", at = @At("HEAD"), cancellable = true)
    private static void optibest_noShadow(CallbackInfo ci) {
        if (OptiBestConfig.noEntityShadow) ci.cancel();
    }
}
