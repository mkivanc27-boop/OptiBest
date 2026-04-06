package com.optibest.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class OptiBestConfigScreen extends Screen {

    private final Screen parent;

    public OptiBestConfigScreen(Screen parent) {
        super(Text.literal("§6OptiBest §7- Ayarlar"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int startY = 50;
        int spacing = 24;
        int btnWidth = 300;
        int centerX = this.width / 2 - btnWidth / 2;

        // Entity Culling
        addToggleButton(centerX, startY, btnWidth, "Entity Culling",
                OptiBestConfig.entityCulling, val -> OptiBestConfig.entityCulling = val);

        // Fog
        addToggleButton(centerX, startY + spacing, btnWidth, "Fog Kapalı",
                OptiBestConfig.fogDisabled, val -> OptiBestConfig.fogDisabled = val);

        // Entity Shadows
        addToggleButton(centerX, startY + spacing * 2, btnWidth, "Entity Gölgeleri Kapalı",
                OptiBestConfig.entityShadowsDisabled, val -> OptiBestConfig.entityShadowsDisabled = val);

        // Clouds
        addToggleButton(centerX, startY + spacing * 3, btnWidth, "Bulutlar Kapalı",
                OptiBestConfig.cloudRenderOff, val -> OptiBestConfig.cloudRenderOff = val);

        // Particle Limit
        addToggleButton(centerX, startY + spacing * 4, btnWidth, "Partikül Limiti (Max 10)",
                OptiBestConfig.particleLimit, val -> OptiBestConfig.particleLimit = val);

        // Chunk Throttling
        addToggleButton(centerX, startY + spacing * 5, btnWidth, "Chunk Güncelleme Sınırlama",
                OptiBestConfig.chunkThrottling, val -> OptiBestConfig.chunkThrottling = val);

        // Fast Graphics
        addToggleButton(centerX, startY + spacing * 6, btnWidth, "Hızlı Grafik Modu",
                OptiBestConfig.fastGraphics, val -> OptiBestConfig.fastGraphics = val);

        // Smooth Lighting
        addToggleButton(centerX, startY + spacing * 7, btnWidth, "Smooth Lighting Kapalı",
                OptiBestConfig.smoothLightingOff, val -> OptiBestConfig.smoothLightingOff = val);

        // Entity Tick Opt
        addToggleButton(centerX, startY + spacing * 8, btnWidth, "Entity Tick Optimizasyonu",
                OptiBestConfig.entityTickOptimization, val -> OptiBestConfig.entityTickOptimization = val);

        // Geri
        this.addDrawableChild(ButtonWidget.builder(Text.literal("§cGeri"), btn -> {
            assert this.client != null;
            this.client.setScreen(parent);
        }).dimensions(this.width / 2 - 100, this.height - 30, 200, 20).build());
    }

    private void addToggleButton(int x, int y, int width, String label, boolean currentValue,
                                  java.util.function.Consumer<Boolean> setter) {
        String prefix = currentValue ? "§a[AÇIK] " : "§c[KAPALI] ";
        ButtonWidget[] ref = new ButtonWidget[1];
        ref[0] = ButtonWidget.builder(Text.literal(prefix + label), btn -> {
            boolean newVal = !currentValue;
            setter.accept(newVal);
            String newPrefix = newVal ? "§a[AÇIK] " : "§c[KAPALI] ";
            btn.setMessage(Text.literal(newPrefix + label));
        }).dimensions(x, y, width, 20).build();
        this.addDrawableChild(ref[0]);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal("§6§lOptiBest Client §r§7- FPS Ayarları"),
                this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        assert this.client != null;
        this.client.setScreen(parent);
    }
                              }
