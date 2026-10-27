package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdHistoricalPlaybackSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface QueueIdStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdHistoricalPlaybackSeedContextInput.class != obj.getClass()) {
            return false;
        }
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput = (QueueIdHistoricalPlaybackSeedContextInput) obj;
        return ObjectsCompat.equals(getQueueId(), queueIdHistoricalPlaybackSeedContextInput.getQueueId()) && ObjectsCompat.equals(getMetricsContext(), queueIdHistoricalPlaybackSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getQueueId() + getMetricsContext()).hashCode();
    }
}
