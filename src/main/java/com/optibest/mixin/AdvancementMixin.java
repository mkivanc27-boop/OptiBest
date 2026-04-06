package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.client.network.ClientAdvancementManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientAdvancementManager.class)
public class AdvancementMixin {

    @Inject(method = "onAdvancements", at = @At("HEAD"), cancellable = true)
    private void optibest_skipAdvancements(CallbackInfo ci) {
        if (OptiBestConfig.advancementDisable) {
            ci.cancel();
        }
    }
}
