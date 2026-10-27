package com.spotify.automotive.ubi;

/* JADX INFO: loaded from: classes.dex */
public enum LoggerPlaybackError {
    PLAYBACK_PERMANENT_ERROR("permanent"),
    PLAYBACK_TEMPORARY_ERROR("temporary"),
    NETWORK_ERROR("network"),
    VOICE_FAILURE("voice-failure"),
    PREMIUM_IS_REQUIRED("premium-is-required"),
    EMPTY_METADATA("empty-metadata");

    private final String id;

    LoggerPlaybackError(String str) {
        this.id = str;
    }

    public final String a() {
        return this.id;
    }
}
