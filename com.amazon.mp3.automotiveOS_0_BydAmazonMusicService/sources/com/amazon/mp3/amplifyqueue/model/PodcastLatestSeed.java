package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastLatestSeed {
    private final String episodeId;
    private final List<String> latestEpisodeIds;
    private final String nextToken;

    public interface BuildStep {
    }

    public static class Builder implements EpisodeIdStep, LatestEpisodeIdsStep, NextTokenStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
    }

    public interface LatestEpisodeIdsStep {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PodcastLatestSeed.class != obj.getClass()) {
            return false;
        }
        PodcastLatestSeed podcastLatestSeed = (PodcastLatestSeed) obj;
        return ObjectsCompat.equals(getEpisodeId(), podcastLatestSeed.getEpisodeId()) && ObjectsCompat.equals(getLatestEpisodeIds(), podcastLatestSeed.getLatestEpisodeIds()) && ObjectsCompat.equals(getNextToken(), podcastLatestSeed.getNextToken());
    }

    public int hashCode() {
        return (getEpisodeId() + getLatestEpisodeIds() + getNextToken()).hashCode();
    }
}
