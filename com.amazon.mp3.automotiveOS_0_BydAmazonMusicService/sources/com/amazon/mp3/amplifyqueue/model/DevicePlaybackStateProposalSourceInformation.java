package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class DevicePlaybackStateProposalSourceInformation {
    private final String deviceId;
    private final String deviceType;
    private final DevicePlaybackStateProposalSourceEnum type;

    public interface BuildStep {
    }

    public static class Builder implements TypeStep, DeviceIdStep, DeviceTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
    }

    public interface TypeStep {
    }

    public DevicePlaybackStateProposalSourceEnum getType() {
        return this.type;
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
        if (obj == null || DevicePlaybackStateProposalSourceInformation.class != obj.getClass()) {
            return false;
        }
        DevicePlaybackStateProposalSourceInformation devicePlaybackStateProposalSourceInformation = (DevicePlaybackStateProposalSourceInformation) obj;
        return ObjectsCompat.equals(getType(), devicePlaybackStateProposalSourceInformation.getType()) && ObjectsCompat.equals(getDeviceId(), devicePlaybackStateProposalSourceInformation.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), devicePlaybackStateProposalSourceInformation.getDeviceType());
    }

    public int hashCode() {
        return (getType() + getDeviceId() + getDeviceType()).hashCode();
    }
}
