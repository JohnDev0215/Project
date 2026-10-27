package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceContext {
    private final AdContext adContext;
    private final String deviceId;
    private final String deviceType;
    private final String sessionId;

    public interface AdContextStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements DeviceIdStep, DeviceTypeStep, AdContextStep, BuildStep {
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

    public AdContext getAdContext() {
        return this.adContext;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceContext.class != obj.getClass()) {
            return false;
        }
        DeviceContext deviceContext = (DeviceContext) obj;
        return ObjectsCompat.equals(getDeviceId(), deviceContext.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), deviceContext.getDeviceType()) && ObjectsCompat.equals(getAdContext(), deviceContext.getAdContext()) && ObjectsCompat.equals(getSessionId(), deviceContext.getSessionId());
    }

    public int hashCode() {
        return (getDeviceId() + getDeviceType() + getAdContext() + getSessionId()).hashCode();
    }
}
