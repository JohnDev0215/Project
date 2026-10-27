package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StartAtInput {
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
        if (obj == null || StartAtInput.class != obj.getClass()) {
            return false;
        }
        StartAtInput startAtInput = (StartAtInput) obj;
        return ObjectsCompat.equals(getQueueEntityIdType(), startAtInput.getQueueEntityIdType()) && ObjectsCompat.equals(getQueueEntityId(), startAtInput.getQueueEntityId()) && ObjectsCompat.equals(getOffsetInMs(), startAtInput.getOffsetInMs());
    }

    public int hashCode() {
        return (getQueueEntityIdType() + getQueueEntityId() + getOffsetInMs()).hashCode();
    }
}
