package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PodcastSavedSeedInput {
    private final String episodeId;

    public interface BuildStep {
    }

    public static class Builder implements EpisodeIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EpisodeIdStep {
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PodcastSavedSeedInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getEpisodeId(), ((PodcastSavedSeedInput) obj).getEpisodeId());
    }

    public int hashCode() {
        return (getEpisodeId()).hashCode();
    }
}
