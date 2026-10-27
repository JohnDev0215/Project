package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoChannelSeedContextInput {
    private final VideoShowInput activeShow;
    private final String channelId;
    private final MetricsContextInput metricsContext;
    private final List<VideoShowInput> shows;

    public interface ActiveShowStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ActiveShowStep, ShowsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface ShowsStep {
    }

    public String getChannelId() {
        return this.channelId;
    }

    public VideoShowInput getActiveShow() {
        return this.activeShow;
    }

    public List<VideoShowInput> getShows() {
        return this.shows;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || VideoChannelSeedContextInput.class != obj.getClass()) {
            return false;
        }
        VideoChannelSeedContextInput videoChannelSeedContextInput = (VideoChannelSeedContextInput) obj;
        return ObjectsCompat.equals(getChannelId(), videoChannelSeedContextInput.getChannelId()) && ObjectsCompat.equals(getActiveShow(), videoChannelSeedContextInput.getActiveShow()) && ObjectsCompat.equals(getShows(), videoChannelSeedContextInput.getShows()) && ObjectsCompat.equals(getMetricsContext(), videoChannelSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getChannelId() + getActiveShow() + getShows() + getMetricsContext()).hashCode();
    }
}
