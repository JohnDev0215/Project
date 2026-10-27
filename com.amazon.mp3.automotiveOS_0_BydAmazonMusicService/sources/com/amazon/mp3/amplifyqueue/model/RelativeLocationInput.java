package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class RelativeLocationInput {
    private final String entityReferenceId;
    private final RelativePlacementEnum relativePlacement;

    public interface BuildStep {
    }

    public static class Builder implements EntityReferenceIdStep, RelativePlacementStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityReferenceIdStep {
    }

    public interface RelativePlacementStep {
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public RelativePlacementEnum getRelativePlacement() {
        return this.relativePlacement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RelativeLocationInput.class != obj.getClass()) {
            return false;
        }
        RelativeLocationInput relativeLocationInput = (RelativeLocationInput) obj;
        return ObjectsCompat.equals(getEntityReferenceId(), relativeLocationInput.getEntityReferenceId()) && ObjectsCompat.equals(getRelativePlacement(), relativeLocationInput.getRelativePlacement());
    }

    public int hashCode() {
        return (getEntityReferenceId() + getRelativePlacement()).hashCode();
    }
}
