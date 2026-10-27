package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class RemoveEntityResponseStatusTuple {
    private final String entityReferenceId;
    private final RemoveEntityResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, EntityReferenceIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityReferenceIdStep {
    }

    public interface StatusStep {
    }

    public RemoveEntityResponseStatusEnum getStatus() {
        return this.status;
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RemoveEntityResponseStatusTuple.class != obj.getClass()) {
            return false;
        }
        RemoveEntityResponseStatusTuple removeEntityResponseStatusTuple = (RemoveEntityResponseStatusTuple) obj;
        return ObjectsCompat.equals(getStatus(), removeEntityResponseStatusTuple.getStatus()) && ObjectsCompat.equals(getEntityReferenceId(), removeEntityResponseStatusTuple.getEntityReferenceId());
    }

    public int hashCode() {
        return (getStatus() + getEntityReferenceId()).hashCode();
    }
}
