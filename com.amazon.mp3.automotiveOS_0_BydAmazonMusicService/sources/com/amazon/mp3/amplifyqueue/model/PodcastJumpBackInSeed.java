package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastJumpBackInSeed {
    private final String episodeId;
    private final String podcastId;

    public interface BuildStep {
    }

    public static class Builder implements PodcastIdStep, EpisodeIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
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
        if (obj == null || PodcastJumpBackInSeed.class != obj.getClass()) {
            return false;
        }
        PodcastJumpBackInSeed podcastJumpBackInSeed = (PodcastJumpBackInSeed) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastJumpBackInSeed.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastJumpBackInSeed.getEpisodeId());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId()).hashCode();
    }
}
