package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class InitiateQueueInput {
    private final ParentalControlsInput allowedParentalControls;
    private final CustomerDeviceSettingsInput customerDeviceSettings;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final IdentityContextInput identityContext;
    private final PlaybackOptionsInput playbackOptions;
    private final String queueId;
    private final List<QueueSeedContextInput> queueSeeds;
    private final String serializedMusicRequestIdentityContext;
    private final StartAtInput startAt;
    private final StratusBenefitsInput stratusBenefits;
    private final DeviceContextInput targetDeviceContext;

    public interface AllowedParentalControlsStep {
        BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput);
    }

    public interface BuildStep {
        InitiateQueueInput build();

        BuildStep customerDeviceSettings(CustomerDeviceSettingsInput customerDeviceSettingsInput);

        BuildStep identityContext(IdentityContextInput identityContextInput);

        BuildStep playbackOptions(PlaybackOptionsInput playbackOptionsInput);

        BuildStep queueId(String str);

        BuildStep serializedMusicRequestIdentityContext(String str);

        BuildStep startAt(StartAtInput startAtInput);

        BuildStep stratusBenefits(StratusBenefitsInput stratusBenefitsInput);

        BuildStep targetDeviceContext(DeviceContextInput deviceContextInput);
    }

    public interface CustomerIdStep {
        DeviceContextStep customerId(String str);
    }

    public interface DeviceContextStep {
        QueueSeedsStep deviceContext(DeviceContextInput deviceContextInput);
    }

    public interface QueueSeedsStep {
        AllowedParentalControlsStep queueSeeds(List<QueueSeedContextInput> list);
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

    public DeviceContextInput getTargetDeviceContext() {
        return this.targetDeviceContext;
    }

    public CustomerDeviceSettingsInput getCustomerDeviceSettings() {
        return this.customerDeviceSettings;
    }

    public List<QueueSeedContextInput> getQueueSeeds() {
        return this.queueSeeds;
    }

    public StartAtInput getStartAt() {
        return this.startAt;
    }

    public ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getSerializedMusicRequestIdentityContext() {
        return this.serializedMusicRequestIdentityContext;
    }

    public IdentityContextInput getIdentityContext() {
        return this.identityContext;
    }

    public PlaybackOptionsInput getPlaybackOptions() {
        return this.playbackOptions;
    }

    public StratusBenefitsInput getStratusBenefits() {
        return this.stratusBenefits;
    }

    private InitiateQueueInput(String str, String str2, DeviceContextInput deviceContextInput, DeviceContextInput deviceContextInput2, CustomerDeviceSettingsInput customerDeviceSettingsInput, List<QueueSeedContextInput> list, StartAtInput startAtInput, ParentalControlsInput parentalControlsInput, String str3, IdentityContextInput identityContextInput, PlaybackOptionsInput playbackOptionsInput, StratusBenefitsInput stratusBenefitsInput) {
        this.customerId = str;
        this.queueId = str2;
        this.deviceContext = deviceContextInput;
        this.targetDeviceContext = deviceContextInput2;
        this.customerDeviceSettings = customerDeviceSettingsInput;
        this.queueSeeds = list;
        this.startAt = startAtInput;
        this.allowedParentalControls = parentalControlsInput;
        this.serializedMusicRequestIdentityContext = str3;
        this.identityContext = identityContextInput;
        this.playbackOptions = playbackOptionsInput;
        this.stratusBenefits = stratusBenefitsInput;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || InitiateQueueInput.class != obj.getClass()) {
            return false;
        }
        InitiateQueueInput initiateQueueInput = (InitiateQueueInput) obj;
        return ObjectsCompat.equals(getCustomerId(), initiateQueueInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), initiateQueueInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), initiateQueueInput.getDeviceContext()) && ObjectsCompat.equals(getTargetDeviceContext(), initiateQueueInput.getTargetDeviceContext()) && ObjectsCompat.equals(getCustomerDeviceSettings(), initiateQueueInput.getCustomerDeviceSettings()) && ObjectsCompat.equals(getQueueSeeds(), initiateQueueInput.getQueueSeeds()) && ObjectsCompat.equals(getStartAt(), initiateQueueInput.getStartAt()) && ObjectsCompat.equals(getAllowedParentalControls(), initiateQueueInput.getAllowedParentalControls()) && ObjectsCompat.equals(getSerializedMusicRequestIdentityContext(), initiateQueueInput.getSerializedMusicRequestIdentityContext()) && ObjectsCompat.equals(getIdentityContext(), initiateQueueInput.getIdentityContext()) && ObjectsCompat.equals(getPlaybackOptions(), initiateQueueInput.getPlaybackOptions()) && ObjectsCompat.equals(getStratusBenefits(), initiateQueueInput.getStratusBenefits());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getTargetDeviceContext() + getCustomerDeviceSettings() + getQueueSeeds() + getStartAt() + getAllowedParentalControls() + getSerializedMusicRequestIdentityContext() + getIdentityContext() + getPlaybackOptions() + getStratusBenefits()).hashCode();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public static class Builder implements CustomerIdStep, DeviceContextStep, QueueSeedsStep, AllowedParentalControlsStep, BuildStep {
        private ParentalControlsInput allowedParentalControls;
        private CustomerDeviceSettingsInput customerDeviceSettings;
        private String customerId;
        private DeviceContextInput deviceContext;
        private IdentityContextInput identityContext;
        private PlaybackOptionsInput playbackOptions;
        private String queueId;
        private List<QueueSeedContextInput> queueSeeds;
        private String serializedMusicRequestIdentityContext;
        private StartAtInput startAt;
        private StratusBenefitsInput stratusBenefits;
        private DeviceContextInput targetDeviceContext;

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public InitiateQueueInput build() {
            return new InitiateQueueInput(this.customerId, this.queueId, this.deviceContext, this.targetDeviceContext, this.customerDeviceSettings, this.queueSeeds, this.startAt, this.allowedParentalControls, this.serializedMusicRequestIdentityContext, this.identityContext, this.playbackOptions, this.stratusBenefits);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.CustomerIdStep
        public DeviceContextStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.DeviceContextStep
        public QueueSeedsStep deviceContext(DeviceContextInput deviceContextInput) {
            Objects.requireNonNull(deviceContextInput);
            this.deviceContext = deviceContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.QueueSeedsStep
        public AllowedParentalControlsStep queueSeeds(List<QueueSeedContextInput> list) {
            Objects.requireNonNull(list);
            this.queueSeeds = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.AllowedParentalControlsStep
        public BuildStep allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            Objects.requireNonNull(parentalControlsInput);
            this.allowedParentalControls = parentalControlsInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep queueId(String str) {
            this.queueId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep targetDeviceContext(DeviceContextInput deviceContextInput) {
            this.targetDeviceContext = deviceContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep customerDeviceSettings(CustomerDeviceSettingsInput customerDeviceSettingsInput) {
            this.customerDeviceSettings = customerDeviceSettingsInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep startAt(StartAtInput startAtInput) {
            this.startAt = startAtInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep serializedMusicRequestIdentityContext(String str) {
            this.serializedMusicRequestIdentityContext = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep identityContext(IdentityContextInput identityContextInput) {
            this.identityContext = identityContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep playbackOptions(PlaybackOptionsInput playbackOptionsInput) {
            this.playbackOptions = playbackOptionsInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public BuildStep stratusBenefits(StratusBenefitsInput stratusBenefitsInput) {
            this.stratusBenefits = stratusBenefitsInput;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.QueueSeedsStep
        public /* bridge */ /* synthetic */ AllowedParentalControlsStep queueSeeds(List list) {
            return queueSeeds((List<QueueSeedContextInput>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.DeviceContextStep
        public CopyOfBuilder deviceContext(DeviceContextInput deviceContextInput) {
            return (CopyOfBuilder) super.deviceContext(deviceContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.QueueSeedsStep
        public CopyOfBuilder queueSeeds(List<QueueSeedContextInput> list) {
            return (CopyOfBuilder) super.queueSeeds(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.AllowedParentalControlsStep
        public CopyOfBuilder allowedParentalControls(ParentalControlsInput parentalControlsInput) {
            return (CopyOfBuilder) super.allowedParentalControls(parentalControlsInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder queueId(String str) {
            return (CopyOfBuilder) super.queueId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder targetDeviceContext(DeviceContextInput deviceContextInput) {
            return (CopyOfBuilder) super.targetDeviceContext(deviceContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder customerDeviceSettings(CustomerDeviceSettingsInput customerDeviceSettingsInput) {
            return (CopyOfBuilder) super.customerDeviceSettings(customerDeviceSettingsInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder startAt(StartAtInput startAtInput) {
            return (CopyOfBuilder) super.startAt(startAtInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder serializedMusicRequestIdentityContext(String str) {
            return (CopyOfBuilder) super.serializedMusicRequestIdentityContext(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder identityContext(IdentityContextInput identityContextInput) {
            return (CopyOfBuilder) super.identityContext(identityContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder playbackOptions(PlaybackOptionsInput playbackOptionsInput) {
            return (CopyOfBuilder) super.playbackOptions(playbackOptionsInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.Builder, com.amazon.mp3.amplifyqueue.model.InitiateQueueInput.BuildStep
        public CopyOfBuilder stratusBenefits(StratusBenefitsInput stratusBenefitsInput) {
            return (CopyOfBuilder) super.stratusBenefits(stratusBenefitsInput);
        }
    }
}
