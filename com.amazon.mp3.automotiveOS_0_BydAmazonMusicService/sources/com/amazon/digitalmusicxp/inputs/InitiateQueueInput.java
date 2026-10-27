package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InitiateQueueInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$\u0012\u0006\u0010*\u001a\u00020)\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010:¢\u0006\u0004\b?\u0010@J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001f\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b.\u0010\n\u001a\u0004\b/\u0010\fR\u0019\u00101\u001a\u0004\u0018\u0001008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0019\u00106\u001a\u0004\u0018\u0001058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0019\u0010;\u001a\u0004\u0018\u00010:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>¨\u0006A"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/InitiateQueueInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "deviceContext", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "getDeviceContext", "()Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "targetDeviceContext", "getTargetDeviceContext", "Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;", "customerDeviceSettings", "Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;", "getCustomerDeviceSettings", "()Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;", "", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "queueSeeds", "Ljava/util/List;", "getQueueSeeds", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "getMetricsContext", "Lcom/amazon/digitalmusicxp/inputs/StartAtInput;", "startAt", "Lcom/amazon/digitalmusicxp/inputs/StartAtInput;", "getStartAt", "()Lcom/amazon/digitalmusicxp/inputs/StartAtInput;", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "allowedParentalControls", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "getAllowedParentalControls", "()Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "serializedMusicRequestIdentityContext", "getSerializedMusicRequestIdentityContext", "Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "identityContext", "Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "getIdentityContext", "()Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptions", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "getPlaybackOptions", "()Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "stratusBenefits", "Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "getStratusBenefits", "()Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;Ljava/util/List;Ljava/util/List;Lcom/amazon/digitalmusicxp/inputs/StartAtInput;Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class InitiateQueueInput {
    private final ParentalControlsInput allowedParentalControls;
    private final CustomerDeviceSettingsInput customerDeviceSettings;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final IdentityContextInput identityContext;
    private final List<MetricsContextInput> metricsContext;
    private final PlaybackOptionsInput playbackOptions;
    private final String queueId;
    private final List<QueueSeedContextInput> queueSeeds;
    private final String serializedMusicRequestIdentityContext;
    private final StartAtInput startAt;
    private final DeviceContextInput targetDeviceContext;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InitiateQueueInput)) {
            return false;
        }
        InitiateQueueInput initiateQueueInput = (InitiateQueueInput) other;
        return Intrinsics.areEqual(this.customerId, initiateQueueInput.customerId) && Intrinsics.areEqual(this.queueId, initiateQueueInput.queueId) && Intrinsics.areEqual(this.deviceContext, initiateQueueInput.deviceContext) && Intrinsics.areEqual(this.targetDeviceContext, initiateQueueInput.targetDeviceContext) && Intrinsics.areEqual(this.customerDeviceSettings, initiateQueueInput.customerDeviceSettings) && Intrinsics.areEqual(this.queueSeeds, initiateQueueInput.queueSeeds) && Intrinsics.areEqual(this.metricsContext, initiateQueueInput.metricsContext) && Intrinsics.areEqual(this.startAt, initiateQueueInput.startAt) && Intrinsics.areEqual(this.allowedParentalControls, initiateQueueInput.allowedParentalControls) && Intrinsics.areEqual(this.serializedMusicRequestIdentityContext, initiateQueueInput.serializedMusicRequestIdentityContext) && Intrinsics.areEqual(this.identityContext, initiateQueueInput.identityContext) && Intrinsics.areEqual(this.playbackOptions, initiateQueueInput.playbackOptions) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final StratusBenefitsInput getStratusBenefits() {
        return null;
    }

    public int hashCode() {
        int iHashCode = this.customerId.hashCode() * 31;
        String str = this.queueId;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.deviceContext.hashCode()) * 31;
        DeviceContextInput deviceContextInput = this.targetDeviceContext;
        int iHashCode3 = (iHashCode2 + (deviceContextInput == null ? 0 : deviceContextInput.hashCode())) * 31;
        CustomerDeviceSettingsInput customerDeviceSettingsInput = this.customerDeviceSettings;
        int iHashCode4 = (((iHashCode3 + (customerDeviceSettingsInput == null ? 0 : customerDeviceSettingsInput.hashCode())) * 31) + this.queueSeeds.hashCode()) * 31;
        List<MetricsContextInput> list = this.metricsContext;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        StartAtInput startAtInput = this.startAt;
        int iHashCode6 = (((iHashCode5 + (startAtInput == null ? 0 : startAtInput.hashCode())) * 31) + this.allowedParentalControls.hashCode()) * 31;
        String str2 = this.serializedMusicRequestIdentityContext;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        IdentityContextInput identityContextInput = this.identityContext;
        int iHashCode8 = (iHashCode7 + (identityContextInput == null ? 0 : identityContextInput.hashCode())) * 31;
        PlaybackOptionsInput playbackOptionsInput = this.playbackOptions;
        return ((iHashCode8 + (playbackOptionsInput == null ? 0 : playbackOptionsInput.hashCode())) * 31) + 0;
    }

    public String toString() {
        return "InitiateQueueInput(customerId=" + this.customerId + ", queueId=" + this.queueId + ", deviceContext=" + this.deviceContext + ", targetDeviceContext=" + this.targetDeviceContext + ", customerDeviceSettings=" + this.customerDeviceSettings + ", queueSeeds=" + this.queueSeeds + ", metricsContext=" + this.metricsContext + ", startAt=" + this.startAt + ", allowedParentalControls=" + this.allowedParentalControls + ", serializedMusicRequestIdentityContext=" + this.serializedMusicRequestIdentityContext + ", identityContext=" + this.identityContext + ", playbackOptions=" + this.playbackOptions + ", stratusBenefits=" + ((Object) null) + ')';
    }

    public InitiateQueueInput(String customerId, String str, DeviceContextInput deviceContext, DeviceContextInput deviceContextInput, CustomerDeviceSettingsInput customerDeviceSettingsInput, List<QueueSeedContextInput> queueSeeds, List<MetricsContextInput> list, StartAtInput startAtInput, ParentalControlsInput allowedParentalControls, String str2, IdentityContextInput identityContextInput, PlaybackOptionsInput playbackOptionsInput, StratusBenefitsInput stratusBenefitsInput) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceContext, "deviceContext");
        Intrinsics.checkNotNullParameter(queueSeeds, "queueSeeds");
        Intrinsics.checkNotNullParameter(allowedParentalControls, "allowedParentalControls");
        this.customerId = customerId;
        this.queueId = str;
        this.deviceContext = deviceContext;
        this.targetDeviceContext = deviceContextInput;
        this.customerDeviceSettings = customerDeviceSettingsInput;
        this.queueSeeds = queueSeeds;
        this.metricsContext = list;
        this.startAt = startAtInput;
        this.allowedParentalControls = allowedParentalControls;
        this.serializedMusicRequestIdentityContext = str2;
        this.identityContext = identityContextInput;
        this.playbackOptions = playbackOptionsInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final DeviceContextInput getDeviceContext() {
        return this.deviceContext;
    }

    public final DeviceContextInput getTargetDeviceContext() {
        return this.targetDeviceContext;
    }

    public final CustomerDeviceSettingsInput getCustomerDeviceSettings() {
        return this.customerDeviceSettings;
    }

    public final List<QueueSeedContextInput> getQueueSeeds() {
        return this.queueSeeds;
    }

    public final StartAtInput getStartAt() {
        return this.startAt;
    }

    public final ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public final String getSerializedMusicRequestIdentityContext() {
        return this.serializedMusicRequestIdentityContext;
    }

    public final IdentityContextInput getIdentityContext() {
        return this.identityContext;
    }

    public final PlaybackOptionsInput getPlaybackOptions() {
        return this.playbackOptions;
    }
}
