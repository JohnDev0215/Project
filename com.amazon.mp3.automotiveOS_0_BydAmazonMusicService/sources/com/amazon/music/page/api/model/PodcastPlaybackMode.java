package com.amazon.music.page.api.model;

import UpsellInterface.v1_0.PlaybackMode;

/* JADX INFO: loaded from: classes4.dex */
public enum PodcastPlaybackMode {
    FULL_ACCESS(PlaybackMode.FULL_ACCESS),
    NONE(PlaybackMode.NONE);

    private String value;

    PodcastPlaybackMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static PodcastPlaybackMode fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (PodcastPlaybackMode podcastPlaybackMode : values()) {
            if (podcastPlaybackMode.toString().equals(str)) {
                return podcastPlaybackMode;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
