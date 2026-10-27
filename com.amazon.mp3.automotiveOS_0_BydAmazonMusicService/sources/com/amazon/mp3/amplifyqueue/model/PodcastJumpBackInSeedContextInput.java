package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastJumpBackInSeedContextInput {
    private final String episodeId;
    private final MetricsContextInput metricsContext;
    private final String podcastId;

    public interface BuildStep {
    }

    public static class Builder implements PodcastIdStep, EpisodeIdStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
    }

    public interface MetricsContextStep {
    }

    public interface PodcastIdStep {
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PodcastJumpBackInSeedContextInput.class != obj.getClass()) {
            return false;
        }
        PodcastJumpBackInSeedContextInput podcastJumpBackInSeedContextInput = (PodcastJumpBackInSeedContextInput) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastJumpBackInSeedContextInput.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastJumpBackInSeedContextInput.getEpisodeId()) && ObjectsCompat.equals(getMetricsContext(), podcastJumpBackInSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId() + getMetricsContext()).hashCode();
    }
}
