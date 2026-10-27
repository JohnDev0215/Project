package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class GetQueueViewInput {
    private final ParentalControlsInput allowedParentalControls;
    private final String currentEntityReferenceId;
    private final String customerId;
    private final Integer desiredNumberOfTracks;
    private final DeviceContextInput deviceContext;
    private final QueueViewDirectionEnum direction;
    private final String queueId;

    public interface BuildStep {
        BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput);

        GetQueueViewInput build();

        BuildStep currentEntityReferenceId(String str);

        BuildStep desiredNumberOfTracks(Integer num);
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

    public String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public QueueViewDirectionEnum getDirection() {
        return this.direction;
    }

    public Integer getDesiredNumberOfTracks() {
        return this.desiredNumberOfTracks;
    }

    public ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    private GetQueueViewInput(String str, String str2, DeviceContextInput deviceContextInput, String str3, QueueViewDirectionEnum queueViewDirectionEnum, Integer num, ParentalControlsInput parentalControlsInput) {
        this.customerId = str;
        this.queueId = str2;
        this.deviceContext = deviceContextInput;
        this.currentEntityReferenceId = str3;
        this.direction = queueViewDirectionEnum;
        this.desiredNumberOfTracks = num;
        this.allowedParentalControls = parentalControlsInput;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetQueueViewInput.class != obj.getClass()) {
            return false;
        }
        GetQueueViewInput getQueueViewInput = (GetQueueViewInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getQueueViewInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), getQueueViewInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), getQueueViewInput.getDeviceContext()) && ObjectsCompat.equals(getCurrentEntityReferenceId(), getQueueViewInput.getCurrentEntityReferenceId()) && ObjectsCompat.equals(getDirection(), getQueueViewInput.getDirection()) && ObjectsCompat.equals(getDesiredNumberOfTracks(), getQueueViewInput.getDesiredNumberOfTracks()) && ObjectsCompat.equals(getAllowedParentalControls(), getQueueViewInput.getAllowedParentalControls());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getCurrentEntityReferenceId() + getDirection() + getDesiredNumberOfTracks() + getAllowedParentalControls()).hashCode();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, BuildStep {
        private ParentalControlsInput allowedParentalControls;
        private String currentEntityReferenceId;
        private String customerId;
        private Integer desiredNumberOfTracks;
        private DeviceContextInput deviceContext;
        private QueueViewDirectionEnum direction;
        private String queueId;

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public GetQueueViewInput build() {
            return new GetQueueViewInput(this.customerId, this.queueId, this.deviceContext, this.currentEntityReferenceId, this.direction, this.desiredNumberOfTracks, this.allowedParentalControls);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.CustomerIdStep
        public QueueIdStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.QueueIdStep
        public DeviceContextStep queueId(String str) {
            Objects.requireNonNull(str);
            this.queueId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.DeviceContextStep
        public BuildStep deviceContext(DeviceContextInput deviceContextInput) {
            Objects.requireNonNull(deviceContextInput);
            this.deviceContext = deviceContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public BuildStep currentEntityReferenceId(String str) {
            this.currentEntityReferenceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public BuildStep desiredNumberOfTracks(Integer num) {
            this.desiredNumberOfTracks = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            this.allowedParentalControls = parentalControlsInput;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.QueueIdStep
        public CopyOfBuilder queueId(String str) {
            return (CopyOfBuilder) super.queueId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.DeviceContextStep
        public CopyOfBuilder deviceContext(DeviceContextInput deviceContextInput) {
            return (CopyOfBuilder) super.deviceContext(deviceContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public CopyOfBuilder currentEntityReferenceId(String str) {
            return (CopyOfBuilder) super.currentEntityReferenceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public CopyOfBuilder desiredNumberOfTracks(Integer num) {
            return (CopyOfBuilder) super.desiredNumberOfTracks(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.Builder, com.amazon.mp3.amplifyqueue.model.GetQueueViewInput.BuildStep
        public CopyOfBuilder allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            return (CopyOfBuilder) super.allowedParentalControls(parentalControlsInput);
        }
    }
}
