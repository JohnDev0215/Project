package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.temporal.Temporal;

/* JADX INFO: loaded from: classes3.dex */
public final class ActiveDevicePlayback {
    private final String customerId;
    private final String deviceId;
    private final String deviceName;
    private final String deviceType;
    private final String entityReferenceId;
    private final ImageMetadata imageMetadata;
    private final LoopModeEnum loopMode;
    private final DevicePlaybackStateEnum playbackState;
    private final Double progressInMs;
    private final Temporal.Timestamp progressTimestamp;
    private final String queueId;
    private final String serializedMusicRequestIdentityContext;
    private final ShuffleModeEnum shuffleMode;
    private final String subTitle;
    private final String title;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceIdStep, DeviceTypeStep, EntityReferenceIdStep, ProgressInMsStep, ProgressTimestampStep, LoopModeStep, ShuffleModeStep, PlaybackStateStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
    }

    public interface EntityReferenceIdStep {
    }

    public interface LoopModeStep {
    }

    public interface PlaybackStateStep {
    }

    public interface ProgressInMsStep {
    }

    public interface ProgressTimestampStep {
    }

    public interface QueueIdStep {
    }

    public interface ShuffleModeStep {
    }

    public String getCustomerId() {
        return this.customerId;
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

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public Double getProgressInMs() {
        return this.progressInMs;
    }

    public Temporal.Timestamp getProgressTimestamp() {
        return this.progressTimestamp;
    }

    public LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }

    public DevicePlaybackStateEnum getPlaybackState() {
        return this.playbackState;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public ImageMetadata getImageMetadata() {
        return this.imageMetadata;
    }

    public String getSerializedMusicRequestIdentityContext() {
        return this.serializedMusicRequestIdentityContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ActiveDevicePlayback.class != obj.getClass()) {
            return false;
        }
        ActiveDevicePlayback activeDevicePlayback = (ActiveDevicePlayback) obj;
        return ObjectsCompat.equals(getCustomerId(), activeDevicePlayback.getCustomerId()) && ObjectsCompat.equals(getQueueId(), activeDevicePlayback.getQueueId()) && ObjectsCompat.equals(getDeviceId(), activeDevicePlayback.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), activeDevicePlayback.getDeviceType()) && ObjectsCompat.equals(getDeviceName(), activeDevicePlayback.getDeviceName()) && ObjectsCompat.equals(getEntityReferenceId(), activeDevicePlayback.getEntityReferenceId()) && ObjectsCompat.equals(getProgressInMs(), activeDevicePlayback.getProgressInMs()) && ObjectsCompat.equals(getProgressTimestamp(), activeDevicePlayback.getProgressTimestamp()) && ObjectsCompat.equals(getLoopMode(), activeDevicePlayback.getLoopMode()) && ObjectsCompat.equals(getShuffleMode(), activeDevicePlayback.getShuffleMode()) && ObjectsCompat.equals(getPlaybackState(), activeDevicePlayback.getPlaybackState()) && ObjectsCompat.equals(getTitle(), activeDevicePlayback.getTitle()) && ObjectsCompat.equals(getSubTitle(), activeDevicePlayback.getSubTitle()) && ObjectsCompat.equals(getImageMetadata(), activeDevicePlayback.getImageMetadata()) && ObjectsCompat.equals(getSerializedMusicRequestIdentityContext(), activeDevicePlayback.getSerializedMusicRequestIdentityContext());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceId() + getDeviceType() + getDeviceName() + getEntityReferenceId() + getProgressInMs() + getProgressTimestamp() + getLoopMode() + getShuffleMode() + getPlaybackState() + getTitle() + getSubTitle() + getImageMetadata() + getSerializedMusicRequestIdentityContext()).hashCode();
    }
}
