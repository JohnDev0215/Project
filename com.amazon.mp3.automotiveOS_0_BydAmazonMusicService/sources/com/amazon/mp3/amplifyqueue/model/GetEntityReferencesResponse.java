package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GetEntityReferencesResponse {
    private final List<EntityReference> entityReferences;

    public interface BuildStep {
    }

    public static class Builder implements EntityReferencesStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityReferencesStep {
    }

    public List<EntityReference> getEntityReferences() {
        return this.entityReferences;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetEntityReferencesResponse.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getEntityReferences(), ((GetEntityReferencesResponse) obj).getEntityReferences());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntityReferences());
        return sb.toString().hashCode();
    }
}
