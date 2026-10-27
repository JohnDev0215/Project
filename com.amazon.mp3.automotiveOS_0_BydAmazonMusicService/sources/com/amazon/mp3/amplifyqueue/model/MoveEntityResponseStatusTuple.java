package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MoveEntityResponseStatusTuple {
    private final String entityReferenceId;
    private final MoveEntityResponseTupleStatusEnum status;

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

    public MoveEntityResponseTupleStatusEnum getStatus() {
        return this.status;
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoveEntityResponseStatusTuple.class != obj.getClass()) {
            return false;
        }
        MoveEntityResponseStatusTuple moveEntityResponseStatusTuple = (MoveEntityResponseStatusTuple) obj;
        return ObjectsCompat.equals(getStatus(), moveEntityResponseStatusTuple.getStatus()) && ObjectsCompat.equals(getEntityReferenceId(), moveEntityResponseStatusTuple.getEntityReferenceId());
    }

    public int hashCode() {
        return (getStatus() + getEntityReferenceId()).hashCode();
    }
}
