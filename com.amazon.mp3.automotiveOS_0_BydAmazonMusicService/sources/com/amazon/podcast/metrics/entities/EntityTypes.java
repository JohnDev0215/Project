package com.amazon.podcast.metrics.entities;

/* JADX INFO: loaded from: classes5.dex */
public enum EntityTypes {
    PODCAST_SHOW("PODCAST_SHOW"),
    PODCAST_EPISODE("PODCAST_EPISODE"),
    PODCAST_BITE("PODCAST_BITE");

    private final String value;

    EntityTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
