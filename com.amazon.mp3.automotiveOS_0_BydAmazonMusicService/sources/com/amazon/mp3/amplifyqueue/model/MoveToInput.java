package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MoveToInput {
    private final ParentalControlsInput allowedParentalControls;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final String entityReferenceId;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, EntityReferenceIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceContextStep {
    }

    public interface EntityReferenceIdStep {
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

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoveToInput.class != obj.getClass()) {
            return false;
        }
        MoveToInput moveToInput = (MoveToInput) obj;
        return ObjectsCompat.equals(getCustomerId(), moveToInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), moveToInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), moveToInput.getDeviceContext()) && ObjectsCompat.equals(getEntityReferenceId(), moveToInput.getEntityReferenceId()) && ObjectsCompat.equals(getAllowedParentalControls(), moveToInput.getAllowedParentalControls());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getEntityReferenceId() + getAllowedParentalControls()).hashCode();
    }
}
