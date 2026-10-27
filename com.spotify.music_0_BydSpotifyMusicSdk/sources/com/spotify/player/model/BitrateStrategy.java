package com.spotify.player.model;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BitrateStrategy {
    public static final BitrateStrategy c;
    public static final BitrateStrategy e;
    public static final BitrateStrategy k;
    public static final BitrateStrategy l;
    public static final BitrateStrategy m;
    public static final BitrateStrategy n;
    public static final /* synthetic */ BitrateStrategy[] o;

    static {
        BitrateStrategy bitrateStrategy = new BitrateStrategy("UNKNOWN", 0);
        c = bitrateStrategy;
        BitrateStrategy bitrateStrategy2 = new BitrateStrategy("BEST_MATCHING", 1);
        e = bitrateStrategy2;
        BitrateStrategy bitrateStrategy3 = new BitrateStrategy("BACKEND_ADVISED", 2);
        k = bitrateStrategy3;
        BitrateStrategy bitrateStrategy4 = new BitrateStrategy("OFFLINED_FILE", 3);
        l = bitrateStrategy4;
        BitrateStrategy bitrateStrategy5 = new BitrateStrategy("CACHED_FILE", 4);
        m = bitrateStrategy5;
        BitrateStrategy bitrateStrategy6 = new BitrateStrategy("LOCAL_FILE", 5);
        n = bitrateStrategy6;
        o = new BitrateStrategy[]{bitrateStrategy, bitrateStrategy2, bitrateStrategy3, bitrateStrategy4, bitrateStrategy5, bitrateStrategy6};
    }

    public static BitrateStrategy valueOf(String str) {
        return (BitrateStrategy) Enum.valueOf(BitrateStrategy.class, str);
    }

    public static BitrateStrategy[] values() {
        return (BitrateStrategy[]) o.clone();
    }
}
