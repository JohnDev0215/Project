package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class OpaqueTrackSearchSeedInput {
    private final String opaqueTrackSearch;

    public interface BuildStep {
    }

    public static class Builder implements OpaqueTrackSearchStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface OpaqueTrackSearchStep {
    }

    public String getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OpaqueTrackSearchSeedInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getOpaqueTrackSearch(), ((OpaqueTrackSearchSeedInput) obj).getOpaqueTrackSearch());
    }

    public int hashCode() {
        return (getOpaqueTrackSearch()).hashCode();
    }
}
