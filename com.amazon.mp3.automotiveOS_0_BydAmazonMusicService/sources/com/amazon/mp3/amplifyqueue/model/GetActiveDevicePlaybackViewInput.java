package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetActiveDevicePlaybackViewInput {
    private final String customerId;
    private final ExclusionFilterInput exclusionFilter;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public ExclusionFilterInput getExclusionFilter() {
        return this.exclusionFilter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetActiveDevicePlaybackViewInput.class != obj.getClass()) {
            return false;
        }
        GetActiveDevicePlaybackViewInput getActiveDevicePlaybackViewInput = (GetActiveDevicePlaybackViewInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getActiveDevicePlaybackViewInput.getCustomerId()) && ObjectsCompat.equals(getExclusionFilter(), getActiveDevicePlaybackViewInput.getExclusionFilter());
    }

    public int hashCode() {
        return (getCustomerId() + getExclusionFilter()).hashCode();
    }
}
