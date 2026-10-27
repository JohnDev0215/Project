package com.amazon.alexa.voice.core.processor.superbowl.context;

/* JADX INFO: loaded from: classes2.dex */
public enum AudioPlayerState {
    IDLE("IDLE"),
    PLAYING("PLAYING"),
    PAUSED("PAUSED"),
    BUFFER_UNDERRUN("BUFFER_UNDERRUN"),
    FINISHED("FINISHED"),
    STOPPED("STOPPED"),
    UNKNOWN("UNKNOWN");

    private final String id;

    AudioPlayerState(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public static AudioPlayerState parse(String str) {
        for (AudioPlayerState audioPlayerState : values()) {
            if (audioPlayerState.id.equalsIgnoreCase(str)) {
                return audioPlayerState;
            }
        }
        return UNKNOWN;
    }
}
