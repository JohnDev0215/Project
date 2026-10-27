package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastJumpBackInSeedInput {
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
        if (obj == null || PodcastJumpBackInSeedInput.class != obj.getClass()) {
            return false;
        }
        PodcastJumpBackInSeedInput podcastJumpBackInSeedInput = (PodcastJumpBackInSeedInput) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastJumpBackInSeedInput.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastJumpBackInSeedInput.getEpisodeId());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId()).hashCode();
    }
}
