package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GetEntitiesResponse {
    private final List<GenericQueueEntity> entities;
    private final GetEntitiesResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public List<GenericQueueEntity> getEntities() {
        return this.entities;
    }

    public GetEntitiesResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetEntitiesResponse.class != obj.getClass()) {
            return false;
        }
        GetEntitiesResponse getEntitiesResponse = (GetEntitiesResponse) obj;
        return ObjectsCompat.equals(getEntities(), getEntitiesResponse.getEntities()) && ObjectsCompat.equals(getStatus(), getEntitiesResponse.getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntities());
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
