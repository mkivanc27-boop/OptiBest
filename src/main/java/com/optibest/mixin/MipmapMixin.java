package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SpriteAtlasTexture.class)
public class MipmapMixin {

    @ModifyArg(method = "upload", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/texture/MipmapHelper;getMipmapLevelsCount(II)I"),
            index = 0)
    private int optibest_reduceMipmaps(int original) {
        if (!OptiBestConfig.reducedMipmaps) return original;
        return 0;
    }
}
