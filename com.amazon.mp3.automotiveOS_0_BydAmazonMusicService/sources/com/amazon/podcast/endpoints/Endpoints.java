package com.amazon.podcast.endpoints;

import com.amazon.podcast.Podcast;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class Endpoints {
    private static final Map<String, Region> map;

    static {
        HashMap map2 = new HashMap();
        map = map2;
        Region region = Region.NA;
        map2.put("ATVPDKIKX0DER", region);
        map2.put("ART4WZ8MWBX2Y", region);
        Region region2 = Region.EU;
        map2.put("A1F83G8C2ARO7P", region2);
        map2.put("A1PA6795UKMFR9", region2);
        map2.put("A13V1IB3VIYZZH", region2);
        map2.put("APJ6JRA9NG5V4", region2);
        map2.put("A1RKKUPIHCS9HS", region2);
        map2.put("A3K6Y4MI8GDYMT", region2);
        Region region3 = Region.FE;
        map2.put("A1VC38T7YXB528", region3);
        map2.put("A15PK738MTQHSO", region3);
    }

    public static Endpoint browse(final String obfuscatedId) {
        Region regionLookup = lookup(obfuscatedId);
        return new Endpoint(String.format(isOverriding() ? getBrowseGammaUrl(regionLookup) : "https://music.amazon.com/%s/api/podcast/browse/visual", regionLookup), "com.amazon.dmpbrowsevisualservice.skills.DMPBrowseVisualService.BootstrapSkill");
    }

    public static Endpoint library(final String obfuscatedId) {
        Region regionLookup = lookup(obfuscatedId);
        return new Endpoint(String.format(isOverriding() ? getLibraryGammaUrl(regionLookup) : "https://music.amazon.com/%s/api/podcast/library/visual", regionLookup), "com.amazon.dmplibraryvisualservice.skills.DMPLibraryVisualService.BootstrapSkill");
    }

    public static Endpoint playback(final String obfuscatedId) {
        Region regionLookup = lookup(obfuscatedId);
        return new Endpoint(String.format(isOverriding() ? getPlaybackGammaUrl(regionLookup) : "https://music.amazon.com/%s/api/podcast/playback/visual", regionLookup), "com.amazon.dmpplaybackvisualservice.skills.DMPPlaybackVisualService.BootstrapSkill");
    }

    private static Region lookup(final String obfuscatedId) {
        Region region = map.get(obfuscatedId);
        return region != null ? region : Region.NA;
    }

    private static boolean isOverriding() {
        return Podcast.getNetworkSettingsProvider().isOverridingEndpoint();
    }

    private static String getBrowseGammaUrl(final Region region) {
        return Region.EU.equals(region) ? "https://music-uk-dub.dub.proxy.amazon.com/EU/api/podcast/browse/visual" : Region.FE.equals(region) ? "https://music-jp-pdx.pdx.proxy.amazon.com/FE/api/podcast/browse/visual" : "https://music-gamma.amazon.com/NA/api/podcast/browse/visual";
    }

    private static String getLibraryGammaUrl(final Region region) {
        return Region.EU.equals(region) ? "https://music-uk-dub.dub.proxy.amazon.com/EU/api/podcast/library/visual" : Region.FE.equals(region) ? "https://music-jp-pdx.pdx.proxy.amazon.com/FE/api/podcast/library/visual" : "https://music-gamma.amazon.com/NA/api/podcast/library/visual";
    }

    private static String getPlaybackGammaUrl(final Region region) {
        return Region.EU.equals(region) ? "https://music-uk-dub.dub.proxy.amazon.com/EU/api/podcast/playback/visual" : Region.FE.equals(region) ? "https://music-jp-pdx.pdx.proxy.amazon.com/FE/api/podcast/browse/visual" : "https://music-gamma.amazon.com/NA/api/podcast/playback/visual";
    }
}
