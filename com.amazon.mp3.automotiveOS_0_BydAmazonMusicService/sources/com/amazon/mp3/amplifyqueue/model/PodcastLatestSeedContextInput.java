package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastLatestSeedContextInput {
    private final String episodeId;
    private final List<String> latestEpisodeIds;
    private final MetricsContextInput metricsContext;
    private final String nextToken;

    public interface BuildStep {
    }

    public static class Builder implements EpisodeIdStep, LatestEpisodeIdsStep, NextTokenStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
    }

    public interface LatestEpisodeIdsStep {
    }

    public interface MetricsContextStep {
    }

    public interface NextTokenStep {
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public List<String> getLatestEpisodeIds() {
        return this.latestEpisodeIds;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PodcastLatestSeedContextInput.class != obj.getClass()) {
            return false;
        }
        PodcastLatestSeedContextInput podcastLatestSeedContextInput = (PodcastLatestSeedContextInput) obj;
        return ObjectsCompat.equals(getEpisodeId(), podcastLatestSeedContextInput.getEpisodeId()) && ObjectsCompat.equals(getLatestEpisodeIds(), podcastLatestSeedContextInput.getLatestEpisodeIds()) && ObjectsCompat.equals(getNextToken(), podcastLatestSeedContextInput.getNextToken()) && ObjectsCompat.equals(getMetricsContext(), podcastLatestSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getEpisodeId() + getLatestEpisodeIds() + getNextToken() + getMetricsContext()).hashCode();
    }
}
