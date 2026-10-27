package com.ts.carplay.adapter.audio;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IAudioAdapter {
    public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK = 1;
    public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK = 4;
    public static final int AUDIO_FOCUS_CARPLAY_AUXOUT = 6;
    public static final int AUDIO_FOCUS_CARPLAY_AUXOUT_DUCK = 8;
    public static final int AUDIO_FOCUS_CARPLAY_MEDIA = 0;
    public static final int AUDIO_FOCUS_CARPLAY_RINGTONE = 5;
    public static final int AUDIO_FOCUS_CARPLAY_TEL = 3;
    public static final int AUDIO_FOCUS_CARPLAY_TMP_MEDIA = 7;
    public static final int AUDIO_FOCUS_CARPLAY_VR = 2;
    public static final int PRIORITY_LEVEL_CARPLAY_AUDIO = 1;
    public static final int PRIORITY_LEVEL_CARPLAY_AUXOUT = 7;
    public static final int PRIORITY_LEVEL_CARPLAY_NAVI = 4;
    public static final int PRIORITY_LEVEL_CARPLAY_PHONE = 3;
    public static final int PRIORITY_LEVEL_CARPLAY_RINGTONE = 8;
    public static final int PRIORITY_LEVEL_CARPLAY_SIRI = 2;
    public static final int PRIORITY_LEVEL_CARPLAY_TEMP_AUDIO = 9;
    public static final int PRIORITY_LEVEL_HIGH = 5;
    public static final int PRIORITY_LEVEL_HIGHEST = 6;
    public static final int PRIORITY_LEVEL_NORMAL = 0;

    boolean abandonAudioFocus(int i);

    void init(Context context);

    void registerListener(IAudioAdapterListener iAudioAdapterListener);

    boolean requestAudioFocus(int i);

    void unregisterListener(IAudioAdapterListener iAudioAdapterListener);

    static String toString(int type) {
        if (type == 0) {
            return "AUDIO_FOCUS_CARPLAY_MEDIA";
        }
        if (type == 1) {
            return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK";
        }
        if (type == 2) {
            return "AUDIO_FOCUS_CARPLAY_VR";
        }
        if (type == 3) {
            return "AUDIO_FOCUS_CARPLAY_TEL";
        }
        if (type == 4) {
            return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK";
        }
        if (type == 5) {
            return "AUDIO_FOCUS_CARPLAY_RINGTONE";
        }
        if (type == 6) {
            return "AUDIO_FOCUS_CARPLAY_AUXOUT";
        }
        if (type == 7) {
            return "AUDIO_FOCUS_CARPLAY_TMP_MEDIA";
        }
        if (type == 8) {
            return "AUDIO_FOCUS_CARPLAY_AUXOUT_DUCK";
        }
        return "AUDIO_FOCUS_CARPLAY_UNKNOWN";
    }

    default void duckAudio(int durationMsec, int db) {
    }

    default void unduckAudio(int durationMsec) {
    }

    default boolean isBtMediaAudioFocus() {
        return false;
    }

    public interface IAudioAdapterListener {
        default void onAudioFocusChanged(int priority) {
        }

        default void onAudioFocusChanged(int priority, String borrowId) {
        }
    }
}
