package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class GetNextEntityInput {
    private final AdContextInput adContext;
    private final ParentalControlsInput allowedParentalControls;
    private final String currentEntityReferenceId;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final Boolean isUserInitiated;
    private final String queueId;

    public interface BuildStep {
        BuildStep adContext(AdContextInput adContextInput);

        BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput);

        GetNextEntityInput build();

        BuildStep currentEntityReferenceId(String str);

        BuildStep isUserInitiated(Boolean bool);
    }

    public interface CustomerIdStep {
        QueueIdStep customerId(String str);
    }

    public interface DeviceContextStep {
        BuildStep deviceContext(DeviceContextInput deviceContextInput);
    }

    public interface QueueIdStep {
        DeviceContextStep queueId(String str);
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

    public AdContextInput getAdContext() {
        return this.adContext;
    }

    public String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public Boolean getIsUserInitiated() {
        return this.isUserInitiated;
    }

    public ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    private GetNextEntityInput(String str, String str2, DeviceContextInput deviceContextInput, AdContextInput adContextInput, String str3, Boolean bool, ParentalControlsInput parentalControlsInput) {
        this.customerId = str;
        this.queueId = str2;
        this.deviceContext = deviceContextInput;
        this.adContext = adContextInput;
        this.currentEntityReferenceId = str3;
        this.isUserInitiated = bool;
        this.allowedParentalControls = parentalControlsInput;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetNextEntityInput.class != obj.getClass()) {
            return false;
        }
        GetNextEntityInput getNextEntityInput = (GetNextEntityInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getNextEntityInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), getNextEntityInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), getNextEntityInput.getDeviceContext()) && ObjectsCompat.equals(getAdContext(), getNextEntityInput.getAdContext()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), getNextEntityInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getIsUserInitiated(), getNextEntityInput.getIsUserInitiated()) && ObjectsCompat.equals(getAllowedParentalControls(), getNextEntityInput.getAllowedParentalControls());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getAdContext() + getCurrentEntityReferenceId() + getIsUserInitiated() + getAllowedParentalControls()).hashCode();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, BuildStep {
        private AdContextInput adContext;
        private ParentalControlsInput allowedParentalControls;
        private String currentEntityReferenceId;
        private String customerId;
        private DeviceContextInput deviceContext;
        private Boolean isUserInitiated;
        private String queueId;

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public GetNextEntityInput build() {
            return new GetNextEntityInput(this.customerId, this.queueId, this.deviceContext, this.adContext, this.currentEntityReferenceId, this.isUserInitiated, this.allowedParentalControls);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.CustomerIdStep
        public QueueIdStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.QueueIdStep
        public DeviceContextStep queueId(String str) {
            Objects.requireNonNull(str);
            this.queueId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.DeviceContextStep
        public BuildStep deviceContext(DeviceContextInput deviceContextInput) {
            Objects.requireNonNull(deviceContextInput);
            this.deviceContext = deviceContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public BuildStep adContext(AdContextInput adContextInput) {
            this.adContext = adContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public BuildStep currentEntityReferenceId(String str) {
            this.currentEntityReferenceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public BuildStep isUserInitiated(Boolean bool) {
            this.isUserInitiated = bool;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            this.allowedParentalControls = parentalControlsInput;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.QueueIdStep
        public CopyOfBuilder queueId(String str) {
            return (CopyOfBuilder) super.queueId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.DeviceContextStep
        public CopyOfBuilder deviceContext(DeviceContextInput deviceContextInput) {
            return (CopyOfBuilder) super.deviceContext(deviceContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public CopyOfBuilder adContext(AdContextInput adContextInput) {
            return (CopyOfBuilder) super.adContext(adContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public CopyOfBuilder currentEntityReferenceId(String str) {
            return (CopyOfBuilder) super.currentEntityReferenceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public CopyOfBuilder isUserInitiated(Boolean bool) {
            return (CopyOfBuilder) super.isUserInitiated(bool);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.Builder, com.amazon.mp3.amplifyqueue.model.GetNextEntityInput.BuildStep
        public CopyOfBuilder allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            return (CopyOfBuilder) super.allowedParentalControls(parentalControlsInput);
        }
    }
}
