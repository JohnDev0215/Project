package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ArtistRole {
    private final String sequenceNumber;
    private final String type;

    public interface BuildStep {
    }

    public static class Builder implements TypeStep, SequenceNumberStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SequenceNumberStep {
    }

    public interface TypeStep {
    }

    public String getType() {
        return this.type;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ArtistRole.class != obj.getClass()) {
            return false;
        }
        ArtistRole artistRole = (ArtistRole) obj;
        return ObjectsCompat.equals(getType(), artistRole.getType()) && ObjectsCompat.equals(getSequenceNumber(), artistRole.getSequenceNumber());
    }

    public int hashCode() {
        return (getType() + getSequenceNumber()).hashCode();
    }
}
