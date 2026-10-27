package com.spotify.player.bindings.options;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RepeatMode {
    public static final RepeatMode c;
    public static final RepeatMode e;
    public static final RepeatMode k;
    public static final /* synthetic */ RepeatMode[] l;

    static {
        RepeatMode repeatMode = new RepeatMode("NONE", 0);
        c = repeatMode;
        RepeatMode repeatMode2 = new RepeatMode("CONTEXT", 1);
        e = repeatMode2;
        RepeatMode repeatMode3 = new RepeatMode("TRACK", 2);
        k = repeatMode3;
        l = new RepeatMode[]{repeatMode, repeatMode2, repeatMode3};
    }

    public static RepeatMode valueOf(String str) {
        return (RepeatMode) Enum.valueOf(RepeatMode.class, str);
    }

    public static RepeatMode[] values() {
        return (RepeatMode[]) l.clone();
    }
}
