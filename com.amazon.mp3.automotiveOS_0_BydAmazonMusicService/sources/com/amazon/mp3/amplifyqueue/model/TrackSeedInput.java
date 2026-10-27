package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackSeedInput {
    private final String id;
    private final TrackSeedIdTypeEnum idType;

    public interface BuildStep {
    }

    public static class Builder implements IdTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface IdTypeStep {
    }

    public TrackSeedIdTypeEnum getIdType() {
        return this.idType;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackSeedInput.class != obj.getClass()) {
            return false;
        }
        TrackSeedInput trackSeedInput = (TrackSeedInput) obj;
        return ObjectsCompat.equals(getIdType(), trackSeedInput.getIdType()) && ObjectsCompat.equals(getId(), trackSeedInput.getId());
    }

    public int hashCode() {
        return (getIdType() + getId()).hashCode();
    }
}
