package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastShowSeed {
    private final String episodeId;
    private final String podcastId;

    public interface BuildStep {
    }

    public static class Builder implements PodcastIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface PodcastIdStep {
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PodcastShowSeed.class != obj.getClass()) {
            return false;
        }
        PodcastShowSeed podcastShowSeed = (PodcastShowSeed) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastShowSeed.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastShowSeed.getEpisodeId());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId()).hashCode();
    }
}
