package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetPreviousEntityInput {
    private final ParentalControlsInput allowedParentalControls;
    private final String currentEntityReferenceId;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, CurrentEntityReferenceIdStep, BuildStep {
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

    public ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetPreviousEntityInput.class != obj.getClass()) {
            return false;
        }
        GetPreviousEntityInput getPreviousEntityInput = (GetPreviousEntityInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getPreviousEntityInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), getPreviousEntityInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), getPreviousEntityInput.getDeviceContext()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), getPreviousEntityInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getAllowedParentalControls(), getPreviousEntityInput.getAllowedParentalControls());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getCurrentEntityReferenceId() + getAllowedParentalControls()).hashCode();
    }
}
