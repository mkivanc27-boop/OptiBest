package com.optibest;

import com.optibest.config.OptiBestConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.ParticlesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptiBestClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("optibest");

    @Override
    public void onInitializeClient() {
        LOGGER.info("§6[OptiBest] §aYüklendi! Tüm optimizasyonlar aktif.");

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.options == null) return;

            // FPS sınırsız
            if (OptiBestConfig.unlimitedFps) {
                client.options.getMaxFps().setValue(260);
            }

            // Render mesafesi sabit 4
            client.options.getViewDistance().setValue(OptiBestConfig.targetViewDistance);

            // Bulutlar
            if (OptiBestConfig.cloudRenderOff) {
                client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
            }

            // Entity gölgeleri
            if (OptiBestConfig.entityShadowsDisabled) {
                client.options.getEntityShadows().setValue(false);
            }

            // Grafik modu
            if (OptiBestConfig.fastGraphics) {
                client.options.getGraphicsMode().setValue(GraphicsMode.FAST);
            }

            // Smooth lighting
            if (OptiBestConfig.smoothLightingOff) {
                client.options.getAo().setValue(false);
            }

            // Partikül modu
            if (OptiBestConfig.particleLimit) {
                client.options.getParticles().setValue(ParticlesMode.MINIMAL);
            }
        });
    }
}
