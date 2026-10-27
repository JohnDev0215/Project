package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class TrackQualityEntry {
    final PlayerAudioQuality Quality;
    final String URI;

    public TrackQualityEntry(PlayerAudioQuality playerAudioQuality, String str) {
        this.Quality = playerAudioQuality;
        this.URI = str;
    }

    public PlayerAudioQuality getQuality() {
        return this.Quality;
    }

    public String getURI() {
        return this.URI;
    }

    public String toString() {
        return "TrackQualityEntry{Quality=" + this.Quality + ",URI=" + this.URI + "}";
    }
}
