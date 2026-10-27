package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastShowSeedContextInput {
    private final String episodeId;
    private final MetricsContextInput metricsContext;
    private final String podcastId;

    public interface BuildStep {
    }

    public static class Builder implements PodcastIdStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
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
        if (obj == null || PodcastShowSeedContextInput.class != obj.getClass()) {
            return false;
        }
        PodcastShowSeedContextInput podcastShowSeedContextInput = (PodcastShowSeedContextInput) obj;
        return ObjectsCompat.equals(getPodcastId(), podcastShowSeedContextInput.getPodcastId()) && ObjectsCompat.equals(getEpisodeId(), podcastShowSeedContextInput.getEpisodeId()) && ObjectsCompat.equals(getMetricsContext(), podcastShowSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getPodcastId() + getEpisodeId() + getMetricsContext()).hashCode();
    }
}
