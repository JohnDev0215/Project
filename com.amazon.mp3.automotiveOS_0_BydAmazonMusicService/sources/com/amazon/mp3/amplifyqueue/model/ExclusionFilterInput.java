package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ExclusionFilterInput {
    private final DeviceExclusionFilterInput deviceExclusionFilter;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public DeviceExclusionFilterInput getDeviceExclusionFilter() {
        return this.deviceExclusionFilter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ExclusionFilterInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getDeviceExclusionFilter(), ((ExclusionFilterInput) obj).getDeviceExclusionFilter());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDeviceExclusionFilter());
        return sb.toString().hashCode();
    }
}
