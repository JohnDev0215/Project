package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlDevicePlaybackInput {
    private final String currentEntityReferenceId;
    private final String deviceId;
    private final String deviceType;
    private final String loopMode;
    private final String queueId;
    private final String shuffleMode;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, DeviceIdStep, DeviceTypeStep, CurrentEntityReferenceIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CurrentEntityReferenceIdStep {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
    }

    public interface QueueIdStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public String getLoopMode() {
        return this.loopMode;
    }

    public String getShuffleMode() {
        return this.shuffleMode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ControlDevicePlaybackInput.class != obj.getClass()) {
            return false;
        }
        ControlDevicePlaybackInput controlDevicePlaybackInput = (ControlDevicePlaybackInput) obj;
        return ObjectsCompat.equals(getQueueId(), controlDevicePlaybackInput.getQueueId()) && ObjectsCompat.equals(getDeviceId(), controlDevicePlaybackInput.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), controlDevicePlaybackInput.getDeviceType()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), controlDevicePlaybackInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getLoopMode(), controlDevicePlaybackInput.getLoopMode()) && ObjectsCompat.equals(getShuffleMode(), controlDevicePlaybackInput.getShuffleMode());
    }

    public int hashCode() {
        return (getQueueId() + getDeviceId() + getDeviceType() + getCurrentEntityReferenceId() + getLoopMode() + getShuffleMode()).hashCode();
    }
}
