package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdSeed {
    private final String queueId;
    private final Boolean shouldManagePlayback;

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

    public Boolean getShouldManagePlayback() {
        return this.shouldManagePlayback;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdSeed.class != obj.getClass()) {
            return false;
        }
        QueueIdSeed queueIdSeed = (QueueIdSeed) obj;
        return ObjectsCompat.equals(getQueueId(), queueIdSeed.getQueueId()) && ObjectsCompat.equals(getShouldManagePlayback(), queueIdSeed.getShouldManagePlayback());
    }

    public int hashCode() {
        return (getQueueId() + getShouldManagePlayback()).hashCode();
    }
}
