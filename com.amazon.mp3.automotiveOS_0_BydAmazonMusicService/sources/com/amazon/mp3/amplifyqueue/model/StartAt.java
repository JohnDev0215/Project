package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StartAt {
    private final Integer offsetInMs;
    private final String queueEntityId;
    private final String queueEntityIdType;

    public interface BuildStep {
    }

    public static class Builder implements QueueEntityIdTypeStep, QueueEntityIdStep, OffsetInMsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface OffsetInMsStep {
    }

    public interface QueueEntityIdStep {
    }

    public interface QueueEntityIdTypeStep {
    }

    public String getQueueEntityIdType() {
        return this.queueEntityIdType;
    }

    public String getQueueEntityId() {
        return this.queueEntityId;
    }

    public Integer getOffsetInMs() {
        return this.offsetInMs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StartAt.class != obj.getClass()) {
            return false;
        }
        StartAt startAt = (StartAt) obj;
        return ObjectsCompat.equals(getQueueEntityIdType(), startAt.getQueueEntityIdType()) && ObjectsCompat.equals(getQueueEntityId(), startAt.getQueueEntityId()) && ObjectsCompat.equals(getOffsetInMs(), startAt.getOffsetInMs());
    }

    public int hashCode() {
        return (getQueueEntityIdType() + getQueueEntityId() + getOffsetInMs()).hashCode();
    }
}
