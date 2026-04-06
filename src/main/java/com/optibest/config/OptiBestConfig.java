package com.optibest.config;

public class OptiBestConfig {

    // === RENDER OPTİMİZASYONLARI ===
    public static boolean entityCulling = true;        // Görünmez entity'leri render etme
    public static boolean frustumCulling = true;       // Kamera dışını render etme
    public static boolean fogDisabled = true;          // Fog kapat
    public static boolean entityShadowsDisabled = true;// Entity gölgeleri kapat
    public static boolean cloudRenderOff = true;       // Bulutları kapat

    // === CHUNK OPTİMİZASYONLARI ===
    public static boolean chunkThrottling = true;      // Chunk güncelleme sınırlama
    public static boolean smoothLightingOff = false;   // Smooth lighting kapat (opsiyonel)

    // === PARTİKÜL OPTİMİZASYONLARI ===
    public static boolean particleLimit = true;        // Partikül sınırı
    public static int maxParticles = 10;               // Max partikül sayısı

    // === GENEL AYARLAR ===
    public static boolean unlimitedFps = true;         // FPS sınırı kaldır
    public static int targetViewDistance = 4;          // Sabit render mesafesi
    public static boolean fastGraphics = true;         // FAST grafik modu

    // === LİTHİUM BENZERİ ===
    public static boolean entityTickOptimization = true;  // Uzak entity tick'leri atla
    public static boolean chunkCacheOptimization = true;  // Chunk cache optimizasyonu

    // === FERRİTE CORE BENZERİ ===
    public static boolean memoryOptimization
