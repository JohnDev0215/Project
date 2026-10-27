package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastShowSeedInput {
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
        if (obj == null || PodcastShowSeedInput.class != obj.getClass()) {
            return false;
        }
        PodcastShowSeedInput podcastShowSeedInput = (PodcastShowSeedInput) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastShowSeedInput.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastShowSeedInput.getEpisodeId());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId()).hashCode();
    }
}
