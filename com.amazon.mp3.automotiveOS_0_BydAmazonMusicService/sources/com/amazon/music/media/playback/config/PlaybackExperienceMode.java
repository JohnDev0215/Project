package com.amazon.music.media.playback.config;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackExperienceMode {
    STANDARD_MODE("standardMode"),
    CAR_MODE("carMode");

    private String label;

    PlaybackExperienceMode(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }
}
