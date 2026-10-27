package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MoveToResponse {
    private final GenericQueueEntity entity;
    private final MoveToResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements EntityStep, StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityStep {
    }

    public interface StatusStep {
    }

    public GenericQueueEntity getEntity() {
        return this.entity;
    }

    public MoveToResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoveToResponse.class != obj.getClass()) {
            return false;
        }
        MoveToResponse moveToResponse = (MoveToResponse) obj;
        return ObjectsCompat.equals(getEntity(), moveToResponse.getEntity()) && ObjectsCompat.equals(getStatus(), moveToResponse.getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntity());
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
