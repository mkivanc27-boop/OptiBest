package com.optibest.config;

public class OptiBestConfig {

    // === RENDER ===
    public static boolean entityCulling = true;
    public static boolean frustumCulling = true;
    public static boolean fogDisabled = true;
    public static boolean entityShadowsDisabled = true;
    public static boolean cloudRenderOff = true;
    public static boolean skyRenderOff = true;
    public static boolean weatherRenderOff = true;

    // === CHUNK ===
    public static boolean chunkThrottling = true;
    public static boolean smoothLightingOff = false;
    public static boolean chunkCacheOptimization = true;

    // === PARTİKÜL ===
    public static boolean particleLimit = true;
    public static int maxParticles = 10;

    // === BLOCK ENTITY ===
    public static boolean blockEntityCulling = true;

    // === BİOME ===
    public static boolean biomeBlendsOff = true;

    // === GENEL ===
    public static boolean unlimitedFps = true;
    public static int targetViewDistance = 4;
    public static boolean fastGraphics = true;

    // === ENTİTY ===
    public static boolean entityTickOptimization = true;

    // === BELLEK ===
    public static boolean memoryOptimization = true;

    // === YENİ AYARLAR ===
    public static boolean soundThrottle = true;
    public static boolean hudOptimization = true;
    public static boolean itemRenderCache = true;
    public static boolean mapRenderOff = true;
    public static boolean noEntityShadow = true;
    public static boolean reducedMipmaps = true;
    public static boolean skipOffscreenBlockEntities = true;
}
