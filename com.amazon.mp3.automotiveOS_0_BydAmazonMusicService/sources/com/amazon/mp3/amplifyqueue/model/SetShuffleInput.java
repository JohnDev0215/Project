package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SetShuffleInput {
    private final String currentEntityReferenceId;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final String queueId;
    private final ShuffleModeEnum shuffleMode;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, CurrentEntityReferenceIdStep, ShuffleModeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CurrentEntityReferenceIdStep {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceContextStep {
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

    public DeviceContextInput getDeviceContext() {
        return this.deviceContext;
    }

    public String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SetShuffleInput.class != obj.getClass()) {
            return false;
        }
        SetShuffleInput setShuffleInput = (SetShuffleInput) obj;
        return ObjectsCompat.equals(getCustomerId(), setShuffleInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), setShuffleInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), setShuffleInput.getDeviceContext()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), setShuffleInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getShuffleMode(), setShuffleInput.getShuffleMode());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getCurrentEntityReferenceId() + getShuffleMode()).hashCode();
    }
}
