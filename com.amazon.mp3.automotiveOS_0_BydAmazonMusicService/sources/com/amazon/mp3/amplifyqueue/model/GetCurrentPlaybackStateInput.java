package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetCurrentPlaybackStateInput {
    private final String customerId;
    private final String deviceId;
    private final String deviceType;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, DeviceIdStep, DeviceTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetCurrentPlaybackStateInput.class != obj.getClass()) {
            return false;
        }
        GetCurrentPlaybackStateInput getCurrentPlaybackStateInput = (GetCurrentPlaybackStateInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getCurrentPlaybackStateInput.getCustomerId()) && ObjectsCompat.equals(getDeviceId(), getCurrentPlaybackStateInput.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), getCurrentPlaybackStateInput.getDeviceType());
    }

    public int hashCode() {
        return (getCustomerId() + getDeviceId() + getDeviceType()).hashCode();
    }
}
