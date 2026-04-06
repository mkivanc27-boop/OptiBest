package com.optibest.mixin;

import com.optibest.config.OptiBestConfig;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    private static final Map<String, BakedModel> modelCache = new HashMap<>();

    @Inject(method = "getModel", at = @At("HEAD"), cancellable = true)
    private void optibest_cacheItemModels(ItemStack stack, World world, CallbackInfoReturnable<BakedModel> cir) {
        if (!OptiBestConfig.itemRenderCache) return;

        String key = stack.getItem().toString() + stack.getCount();
        BakedModel cached = modelCache.get(key);
        if (cached != null) {
            cir.setReturnValue(cached);
        }
    }

    @Inject(method = "getModel", at = @At("RETURN"))
    private void optibest_saveItemModelCache(ItemStack stack, World world, CallbackInfoReturnable<BakedModel> cir) {
        if (!OptiBestConfig.itemRenderCache) return;

        String key = stack.getItem().toString() + stack.getCount();
        if (!modelCache.containsKey(key) && cir.getReturnValue() != null) {
            if (modelCache.size() > 256) modelCache.clear();
            modelCache.put(key, cir.getReturnValue());
        }
    }
}
