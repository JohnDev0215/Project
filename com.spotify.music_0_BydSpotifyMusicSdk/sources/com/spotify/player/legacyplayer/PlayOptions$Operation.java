package com.spotify.player.legacyplayer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PlayOptions$Operation {
    public static final PlayOptions$Operation c;
    public static final PlayOptions$Operation e;
    public static final PlayOptions$Operation k;
    public static final /* synthetic */ PlayOptions$Operation[] l;

    static {
        PlayOptions$Operation playOptions$Operation = new PlayOptions$Operation("REPLACE", 0);
        c = playOptions$Operation;
        PlayOptions$Operation playOptions$Operation2 = new PlayOptions$Operation("ENQUEUE", 1);
        e = playOptions$Operation2;
        PlayOptions$Operation playOptions$Operation3 = new PlayOptions$Operation("PUSH", 2);
        k = playOptions$Operation3;
        l = new PlayOptions$Operation[]{playOptions$Operation, playOptions$Operation2, playOptions$Operation3};
    }

    public static PlayOptions$Operation valueOf(String str) {
        return (PlayOptions$Operation) Enum.valueOf(PlayOptions$Operation.class, str);
    }

    public static PlayOptions$Operation[] values() {
        return (PlayOptions$Operation[]) l.clone();
    }
}
