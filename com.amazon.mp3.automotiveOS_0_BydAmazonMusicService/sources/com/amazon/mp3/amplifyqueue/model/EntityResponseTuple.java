package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class EntityResponseTuple {
    private final GenericQueueEntity entity;
    private final GetNextQueueEntityResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public GenericQueueEntity getEntity() {
        return this.entity;
    }

    public GetNextQueueEntityResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EntityResponseTuple.class != obj.getClass()) {
            return false;
        }
        EntityResponseTuple entityResponseTuple = (EntityResponseTuple) obj;
        return ObjectsCompat.equals(getEntity(), entityResponseTuple.getEntity()) && ObjectsCompat.equals(getStatus(), entityResponseTuple.getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntity());
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
