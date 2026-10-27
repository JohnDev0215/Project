package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class RemoveFromQueueResponse {
    private final List<RemoveEntityResponseStatusTuple> entityStatuses;
    private final RemoveFromQueueResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, EntityStatusesStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityStatusesStep {
    }

    public interface StatusStep {
    }

    public RemoveFromQueueResponseStatusEnum getStatus() {
        return this.status;
    }

    public List<RemoveEntityResponseStatusTuple> getEntityStatuses() {
        return this.entityStatuses;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RemoveFromQueueResponse.class != obj.getClass()) {
            return false;
        }
        RemoveFromQueueResponse removeFromQueueResponse = (RemoveFromQueueResponse) obj;
        return ObjectsCompat.equals(getStatus(), removeFromQueueResponse.getStatus()) && ObjectsCompat.equals(getEntityStatuses(), removeFromQueueResponse.getEntityStatuses());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        sb.append(getEntityStatuses());
        return sb.toString().hashCode();
    }
}
