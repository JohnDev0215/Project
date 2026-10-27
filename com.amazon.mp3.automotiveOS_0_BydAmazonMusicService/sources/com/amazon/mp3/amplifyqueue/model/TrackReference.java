package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackReference {
    private final String id;
    private final TrackReferenceIdTypeEnum idType;

    public interface BuildStep {
    }

    public static class Builder implements IdStep, IdTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface IdStep {
    }

    public interface IdTypeStep {
    }

    public String getId() {
        return this.id;
    }

    public TrackReferenceIdTypeEnum getIdType() {
        return this.idType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackReference.class != obj.getClass()) {
            return false;
        }
        TrackReference trackReference = (TrackReference) obj;
        return ObjectsCompat.equals(getId(), trackReference.getId()) && ObjectsCompat.equals(getIdType(), trackReference.getIdType());
    }

    public int hashCode() {
        return (getId() + getIdType()).hashCode();
    }
}
