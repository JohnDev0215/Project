package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdHistoricalPlaybackSeed {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdHistoricalPlaybackSeed.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getQueueId(), ((QueueIdHistoricalPlaybackSeed) obj).getQueueId());
    }

    public int hashCode() {
        return (getQueueId()).hashCode();
    }
}
