package com.spotify.player.model;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AudioStream {
    public static final AudioStream c;
    public static final AudioStream e;
    public static final /* synthetic */ AudioStream[] k;

    static {
        AudioStream audioStream = new AudioStream("DEFAULT", 0);
        c = audioStream;
        AudioStream audioStream2 = new AudioStream("ALARM", 1);
        e = audioStream2;
        k = new AudioStream[]{audioStream, audioStream2};
    }

    public static AudioStream valueOf(String str) {
        return (AudioStream) Enum.valueOf(AudioStream.class, str);
    }

    public static AudioStream[] values() {
        return (AudioStream[]) k.clone();
    }
}
