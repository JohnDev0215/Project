package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LocationInput {
    private final LocationTypeEnum locationType;
    private final RelativeLocationInput relativeLocation;

    public interface BuildStep {
    }

    public static class Builder implements LocationTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface LocationTypeStep {
    }

    public LocationTypeEnum getLocationType() {
        return this.locationType;
    }

    public RelativeLocationInput getRelativeLocation() {
        return this.relativeLocation;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocationInput.class != obj.getClass()) {
            return false;
        }
        LocationInput locationInput = (LocationInput) obj;
        return ObjectsCompat.equals(getLocationType(), locationInput.getLocationType()) && ObjectsCompat.equals(getRelativeLocation(), locationInput.getRelativeLocation());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getLocationType());
        sb.append(getRelativeLocation());
        return sb.toString().hashCode();
    }
}
