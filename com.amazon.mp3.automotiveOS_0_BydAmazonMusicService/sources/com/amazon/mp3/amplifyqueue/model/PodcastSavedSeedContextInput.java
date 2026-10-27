package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastSavedSeedContextInput {
    private final String episodeId;
    private final MetricsContextInput metricsContext;

    public interface BuildStep {
    }

    public static class Builder implements EpisodeIdStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
    }

    public interface MetricsContextStep {
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
        if (obj == null || PodcastSavedSeedContextInput.class != obj.getClass()) {
            return false;
        }
        PodcastSavedSeedContextInput podcastSavedSeedContextInput = (PodcastSavedSeedContextInput) obj;
        return ObjectsCompat.equals(getEpisodeId(), podcastSavedSeedContextInput.getEpisodeId()) && ObjectsCompat.equals(getMetricsContext(), podcastSavedSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getEpisodeId() + getMetricsContext()).hashCode();
    }
}
