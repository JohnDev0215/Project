package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class EntityContextInput {
    private final String entityReferenceId;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EntityContextInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getEntityReferenceId(), ((EntityContextInput) obj).getEntityReferenceId());
    }

    public int hashCode() {
        return (getEntityReferenceId()).hashCode();
    }
}
