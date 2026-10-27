package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ReportDevicePlaybackInput {
    private final CurrentEntityStateInput currentEntityState;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final DevicePlaybackStateEnum playbackState;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, CurrentEntityStateStep, PlaybackStateStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CurrentEntityStateStep {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceContextStep {
    }

    public interface PlaybackStateStep {
    }

    public interface QueueIdStep {
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public DeviceContextInput getDeviceContext() {
        return this.deviceContext;
    }

    public CurrentEntityStateInput getCurrentEntityState() {
        return this.currentEntityState;
    }

    public DevicePlaybackStateEnum getPlaybackState() {
        return this.playbackState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ReportDevicePlaybackInput.class != obj.getClass()) {
            return false;
        }
        ReportDevicePlaybackInput reportDevicePlaybackInput = (ReportDevicePlaybackInput) obj;
        return ObjectsCompat.equals(getCustomerId(), reportDevicePlaybackInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), reportDevicePlaybackInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), reportDevicePlaybackInput.getDeviceContext()) && ObjectsCompat.equals(getCurrentEntityState(), reportDevicePlaybackInput.getCurrentEntityState()) && ObjectsCompat.equals(getPlaybackState(), reportDevicePlaybackInput.getPlaybackState());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getCurrentEntityState() + getPlaybackState()).hashCode();
    }
}
