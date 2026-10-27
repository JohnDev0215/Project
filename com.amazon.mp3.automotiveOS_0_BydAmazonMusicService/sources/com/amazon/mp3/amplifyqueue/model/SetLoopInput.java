package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SetLoopInput {
    private final String currentEntityReferenceId;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final LoopModeEnum loopMode;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, CurrentEntityReferenceIdStep, LoopModeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CurrentEntityReferenceIdStep {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceContextStep {
    }

    public interface LoopModeStep {
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

    public String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SetLoopInput.class != obj.getClass()) {
            return false;
        }
        SetLoopInput setLoopInput = (SetLoopInput) obj;
        return ObjectsCompat.equals(getCustomerId(), setLoopInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), setLoopInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), setLoopInput.getDeviceContext()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), setLoopInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getLoopMode(), setLoopInput.getLoopMode());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getCurrentEntityReferenceId() + getLoopMode()).hashCode();
    }
}
