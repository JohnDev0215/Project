package com.spotify.player.model.command.options;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PrefetchLevel {
    public static final PrefetchLevel c;
    public static final PrefetchLevel e;
    public static final /* synthetic */ PrefetchLevel[] k;

    static {
        PrefetchLevel prefetchLevel = new PrefetchLevel("NONE", 0);
        c = prefetchLevel;
        PrefetchLevel prefetchLevel2 = new PrefetchLevel("MEDIA", 1);
        e = prefetchLevel2;
        k = new PrefetchLevel[]{prefetchLevel, prefetchLevel2};
    }

    public static PrefetchLevel valueOf(String str) {
        return (PrefetchLevel) Enum.valueOf(PrefetchLevel.class, str);
    }

    public static PrefetchLevel[] values() {
        return (PrefetchLevel[]) k.clone();
    }
}
