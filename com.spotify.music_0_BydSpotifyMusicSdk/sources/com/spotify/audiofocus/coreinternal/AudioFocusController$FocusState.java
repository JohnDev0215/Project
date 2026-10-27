package com.spotify.audiofocus.coreinternal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class AudioFocusController$FocusState {
    public static final AudioFocusController$FocusState c;
    public static final AudioFocusController$FocusState e;
    public static final AudioFocusController$FocusState k;
    public static final /* synthetic */ AudioFocusController$FocusState[] l;

    static {
        AudioFocusController$FocusState audioFocusController$FocusState = new AudioFocusController$FocusState("NONE", 0);
        c = audioFocusController$FocusState;
        AudioFocusController$FocusState audioFocusController$FocusState2 = new AudioFocusController$FocusState("GAINED", 1);
        e = audioFocusController$FocusState2;
        AudioFocusController$FocusState audioFocusController$FocusState3 = new AudioFocusController$FocusState("TRANSIENT_LOSS", 2);
        k = audioFocusController$FocusState3;
        l = new AudioFocusController$FocusState[]{audioFocusController$FocusState, audioFocusController$FocusState2, audioFocusController$FocusState3};
    }

    public static AudioFocusController$FocusState valueOf(String str) {
        return (AudioFocusController$FocusState) Enum.valueOf(AudioFocusController$FocusState.class, str);
    }

    public static AudioFocusController$FocusState[] values() {
        return (AudioFocusController$FocusState[]) l.clone();
    }
}
