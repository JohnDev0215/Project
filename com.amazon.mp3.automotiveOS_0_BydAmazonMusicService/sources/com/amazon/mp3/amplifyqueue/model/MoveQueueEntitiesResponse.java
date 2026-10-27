package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MoveQueueEntitiesResponse {
    private final List<MoveEntityResponseStatusTuple> entityStatuses;
    private final MoveQueueEntitiesResponseEnum status;

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

    public MoveQueueEntitiesResponseEnum getStatus() {
        return this.status;
    }

    public List<MoveEntityResponseStatusTuple> getEntityStatuses() {
        return this.entityStatuses;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoveQueueEntitiesResponse.class != obj.getClass()) {
            return false;
        }
        MoveQueueEntitiesResponse moveQueueEntitiesResponse = (MoveQueueEntitiesResponse) obj;
        return ObjectsCompat.equals(getStatus(), moveQueueEntitiesResponse.getStatus()) && ObjectsCompat.equals(getEntityStatuses(), moveQueueEntitiesResponse.getEntityStatuses());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        sb.append(getEntityStatuses());
        return sb.toString().hashCode();
    }
}
