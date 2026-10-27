package com.spotify.automotive.playerapi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ShuffleMode {
    public static final ShuffleMode c;
    public static final ShuffleMode e;
    public static final ShuffleMode k;
    public static final /* synthetic */ ShuffleMode[] l;

    static {
        ShuffleMode shuffleMode = new ShuffleMode("OFF", 0);
        c = shuffleMode;
        ShuffleMode shuffleMode2 = new ShuffleMode("ON", 1);
        e = shuffleMode2;
        ShuffleMode shuffleMode3 = new ShuffleMode("SMART", 2);
        k = shuffleMode3;
        l = new ShuffleMode[]{shuffleMode, shuffleMode2, shuffleMode3};
    }

    public static ShuffleMode valueOf(String str) {
        return (ShuffleMode) Enum.valueOf(ShuffleMode.class, str);
    }

    public static ShuffleMode[] values() {
        return (ShuffleMode[]) l.clone();
    }
}
