package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceIdentifier {
    private final String deviceId;
    private final String deviceType;

    public interface BuildStep {
    }

    public static class Builder implements DeviceIdStep, DeviceTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
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
        if (obj == null || DeviceIdentifier.class != obj.getClass()) {
            return false;
        }
        DeviceIdentifier deviceIdentifier = (DeviceIdentifier) obj;
        return ObjectsCompat.equals(getDeviceId(), deviceIdentifier.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), deviceIdentifier.getDeviceType());
    }

    public int hashCode() {
        return (getDeviceId() + getDeviceType()).hashCode();
    }
}
