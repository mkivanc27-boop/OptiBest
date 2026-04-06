package com.optibest.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.util.function.Consumer;

public class OptiBestConfigScreen extends Screen {

    private final Screen parent;
    private int scrollOffset = 0;
    private static final int ITEM_HEIGHT = 24;

    public OptiBestConfigScreen(Screen parent) {
        super(Text.literal("OptiBest - Ayarlar"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int btnWidth = 300;
        int centerX = this.width / 2 - btnWidth / 2;
        int y = 45 - scrollOffset;

        y = addToggle(centerX, y, btnWidth, "Entity Culling",
                OptiBestConfig.entityCulling, v -> OptiBestConfig.entityCulling = v);
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
        y = addToggle(centerX, y, btnWidth, "Achievement Sistemi Kapalı",
                OptiBestConfig.advancementDisable, v -> OptiBestConfig.advancementDisable = v);
        y = addToggle(centerX, y, btnWidth, "İstatistik Takibi Kapalı",
                OptiBestConfig.statisticsDisable, v -> OptiBestConfig.statisticsDisable = v);
        y = addToggle(centerX, y, btnWidth, "Item Render Cache",
                OptiBestConfig.itemRenderCache, v -> OptiBestConfig.itemRenderCache = v);
        y = addToggle(centerX, y, btnWidth, "Network Throttle",
                OptiBestConfig.networkThrottle, v -> OptiBestConfig.networkThrottle = v);

        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("§cGeri"), btn -> {
                assert this.client != null;
                this.client.setScreen(parent);
            }).dimensions(this.width / 2 - 100, this.height - 28, 200, 20).build()
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
        ).dimensions(x, y, width, 20).build());
        return y + ITEM_HEIGHT;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal("§6§lOptiBest §r§7FPS Ayarları"),
                this.width / 2, 15, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        assert this.client != null;
        this.client.setScreen(parent);
    }
}
