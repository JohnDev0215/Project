package com.spotify.player.model;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BitrateLevel {
    public static final BitrateLevel c;
    public static final BitrateLevel e;
    public static final BitrateLevel k;
    public static final BitrateLevel l;
    public static final BitrateLevel m;
    public static final BitrateLevel n;
    public static final BitrateLevel o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ BitrateLevel[] f60p;

    static {
        BitrateLevel bitrateLevel = new BitrateLevel("UNKNOWN", 0);
        c = bitrateLevel;
        BitrateLevel bitrateLevel2 = new BitrateLevel("LOW", 1);
        e = bitrateLevel2;
        BitrateLevel bitrateLevel3 = new BitrateLevel("NORMAL", 2);
        k = bitrateLevel3;
        BitrateLevel bitrateLevel4 = new BitrateLevel("HIGH", 3);
        l = bitrateLevel4;
        BitrateLevel bitrateLevel5 = new BitrateLevel("VERY_HIGH", 4);
        m = bitrateLevel5;
        BitrateLevel bitrateLevel6 = new BitrateLevel("HIFI", 5);
        n = bitrateLevel6;
        BitrateLevel bitrateLevel7 = new BitrateLevel("HIFI_24", 6);
        o = bitrateLevel7;
        f60p = new BitrateLevel[]{bitrateLevel, bitrateLevel2, bitrateLevel3, bitrateLevel4, bitrateLevel5, bitrateLevel6, bitrateLevel7};
    }

    public static BitrateLevel valueOf(String str) {
        return (BitrateLevel) Enum.valueOf(BitrateLevel.class, str);
    }

    public static BitrateLevel[] values() {
        return (BitrateLevel[]) f60p.clone();
    }
}
