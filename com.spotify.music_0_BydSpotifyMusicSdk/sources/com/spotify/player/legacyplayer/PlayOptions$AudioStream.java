package com.spotify.player.legacyplayer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PlayOptions$AudioStream {
    public static final PlayOptions$AudioStream c;
    public static final PlayOptions$AudioStream e;
    public static final /* synthetic */ PlayOptions$AudioStream[] k;

    static {
        PlayOptions$AudioStream playOptions$AudioStream = new PlayOptions$AudioStream("DEFAULT", 0);
        c = playOptions$AudioStream;
        PlayOptions$AudioStream playOptions$AudioStream2 = new PlayOptions$AudioStream("ALARM", 1);
        e = playOptions$AudioStream2;
        k = new PlayOptions$AudioStream[]{playOptions$AudioStream, playOptions$AudioStream2};
    }

    public static PlayOptions$AudioStream valueOf(String str) {
        return (PlayOptions$AudioStream) Enum.valueOf(PlayOptions$AudioStream.class, str);
    }

    public static PlayOptions$AudioStream[] values() {
        return (PlayOptions$AudioStream[]) k.clone();
    }
}
