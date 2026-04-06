package com.optibest.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.util.function.Consumer;

public class OptiBestConfigScreen extends Screen {

    private final Screen parent;
    private double scrollOffset = 0;
    private int totalHeight = 0;
    private static final int ITEM_HEIGHT = 28;
    private static final int TOP_PADDING = 45;

    public OptiBestConfigScreen(Screen parent) {
        super(Text.literal("OptiBest - Ayarlar"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.clearChildren();
        int btnWidth = Math.min(300, this.width - 20);
        int centerX = this.width / 2 - btnWidth / 2;
        int y = TOP_PADDING - (int) scrollOffset;

        y = addToggle(centerX, y, btnWidth, "Entity Culling",
                OptiBestConfig.entityCulling, v -> OptiBestConfig.entityCulling = v);
        y = addToggle(centerX, y, btnWidth, "Frustum Culling",
                OptiBestConfig.frustumCulling, v -> OptiBestConfig.frustumCulling = v);
        y = addToggle(centerX, y, btnWidth, "Fog Kapalı",
                OptiBestConfig.fogDisabled, v -> OptiBestConfig.fogDisabled = v);
        y = addToggle(centerX, y, btnWidth, "Entity Gölgeleri Kapalı",
                OptiBestConfig.entityShadowsDisabled, v -> OptiBestConfig.entityShadowsDisabled = v);
        y = addToggle(centerX, y, btnWidth, "Bulutlar Kapalı",
                OptiBestConfig.cloudRenderOff, v -> OptiBestConfig.cloudRenderOff = v);
        y = addToggle(centerX, y, btnWidth, "Gökyüzü Render Kapalı",
                OptiBestConfig.skyRenderOff, v -> OptiBestConfig.skyRenderOff = v);
        y = addToggle(centerX, y, btnWidth, "Hava Durumu Kapalı",
                OptiBestConfig.weatherRenderOff, v -> OptiBestConfig.weatherRenderOff = v);
        y = addToggle(centerX, y, btnWidth, "Partikül Limiti",
                OptiBestConfig.particleLimit, v -> OptiBestConfig.particleLimit = v);
        y = addToggle(centerX, y, btnWidth, "Chunk Güncelleme Sınırlama",
                OptiBestConfig.chunkThrottling, v -> OptiBestConfig.chunkThrottling = v);
        y = addToggle(centerX, y, btnWidth, "Chunk Cache Optimizasyonu",
                OptiBestConfig.chunkCacheOptimization, v -> OptiBestConfig.chunkCacheOptimization = v);
        y = addToggle(centerX, y, btnWidth, "Hızlı Grafik Modu",
                OptiBestConfig.fastGraphics, v -> OptiBestConfig.fastGraphics = v);
        y = addToggle(centerX, y, btnWidth, "Smooth Lighting Kapalı",
                OptiBestConfig.smoothLightingOff, v -> OptiBestConfig.smoothLightingOff = v);
        y = addToggle(centerX, y, btnWidth, "Entity Tick Optimizasyonu",
                OptiBestConfig.entityTickOptimization, v -> OptiBestConfig.entityTickOptimization = v);
        y = addToggle(centerX, y, btnWidth, "Block Entity Culling",
                OptiBestConfig.blockEntityCulling, v -> OptiBestConfig.blockEntityCulling = v);
        y = addToggle(centerX, y, btnWidth, "Biome Renk Geçişi Kapalı",
                OptiBestConfig.biomeBlendsOff, v -> OptiBestConfig.biomeBlendsOff = v);
        y = addToggle(centerX, y, btnWidth, "Bellek Optimizasyonu",
                OptiBestConfig.memoryOptimization, v -> OptiBestConfig.memoryOptimization = v);
        y = addToggle(centerX, y, btnWidth, "Ses Motoru Sınırlama",
                OptiBestConfig.soundThrottle, v -> OptiBestConfig.soundThrottle = v);
        y = addToggle(centerX, y, btnWidth, "HUD Optimizasyonu",
                OptiBestConfig.hudOptimization, v -> OptiBestConfig.hudOptimization = v);
        y = addToggle(centerX, y, btnWidth, "Item Render Cache",
                OptiBestConfig.itemRenderCache, v -> OptiBestConfig.itemRenderCache = v);
        y = addToggle(centerX, y, btnWidth, "Harita Render Kapalı",
                OptiBestConfig.mapRenderOff, v -> OptiBestConfig.mapRenderOff = v);
        y = addToggle(centerX, y, btnWidth, "Entity Gölgesi Kapalı",
                OptiBestConfig.noEntityShadow, v -> OptiBestConfig.noEntityShadow = v);
        y = addToggle(centerX, y, btnWidth, "Düşük Mipmap",
                OptiBestConfig.reducedMipmaps, v -> OptiBestConfig.reducedMipmaps = v);
        y = addToggle(centerX, y, btnWidth, "Uzak Block Entity Culling",
                OptiBestConfig.skipOffscreenBlockEntities, v -> OptiBestConfig.skipOffscreenBlockEntities = v);

        totalHeight = y + (int) scrollOffset - TOP_PADDING;

        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("§cGeri"), btn -> {
                assert this.client != null;
                this.client.setScreen(parent);
            }).dimensions(this.width / 2 - 60, this.height - 26, 120, 20).build()
        );
    }

    private int addToggle(int x, int y, int width, String label,
                           boolean current, Consumer<Boolean> setter) {
        final boolean[] state = {current};
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal((state[0] ? "§a[AÇIK] " : "§c[KAPALI] ") + label),
            b -> {
                state[0] = !state[0];
                setter.accept(state[0]);
                b.setMessage(Text.literal((state[0] ? "§a[AÇIK] " : "§c[KAPALI] ") + label));
            }
        ).dimensions(x, y, width, 22).build());
        return y + ITEM_HEIGHT;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int maxScroll = Math.max(0, totalHeight - (this.height - TOP_PADDING - 35));
        scrollOffset = Math.max(0, Math.min(scrollOffset - verticalAmount * 15, maxScroll));
        this.clearChildren();
        this.init();
        return true;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal("§6§lOptiBest §r§7FPS Ayarları"),
                this.width / 2, 8, 0xFFFFFF);

        int maxScroll = Math.max(1, totalHeight - (this.height - TOP_PADDING - 35));
        if (maxScroll > 0) {
            int trackH = this.height - TOP_PADDING - 35;
            int thumbH = Math.max(20, trackH * trackH / (totalHeight + trackH));
            int thumbY = TOP_PADDING + (int)((trackH - thumbH) * scrollOffset / maxScroll);
            context.fill(this.width - 6, TOP_PADDING, this.width - 2, TOP_PADDING + trackH, 0x44FFFFFF);
            context.fill(this.width - 6, thumbY, this.width - 2, thumbY + thumbH, 0xAAFFFFFF);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        assert this.client != null;
        this.client.setScreen(parent);
    }
}
