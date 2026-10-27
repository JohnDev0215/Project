package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.inputs.IdentityContextInput;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.inputs.StratusBenefitsInput;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.MetricsContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InitiateQueueRequest.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$\u0012\u0006\u0010*\u001a\u00020)\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010:¢\u0006\u0004\b?\u0010@J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001f\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b.\u0010\n\u001a\u0004\b/\u0010\fR\u0019\u00101\u001a\u0004\u0018\u0001008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0019\u00106\u001a\u0004\u0018\u0001058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0019\u0010;\u001a\u0004\u0018\u00010:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>¨\u0006A"}, d2 = {"Lcom/amazon/digitalmusicxp/types/InitiateQueueRequest;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "deviceContext", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "getDeviceContext", "()Lcom/amazon/digitalmusicxp/types/DeviceContext;", "targetDeviceContext", "getTargetDeviceContext", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "customerDeviceSettings", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "getCustomerDeviceSettings", "()Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "queueSeeds", "Ljava/util/List;", "getQueueSeeds", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/models/MetricsContext;", "metricsContext", "getMetricsContext", "Lcom/amazon/digitalmusicxp/types/StartAt;", "startAt", "Lcom/amazon/digitalmusicxp/types/StartAt;", "getStartAt", "()Lcom/amazon/digitalmusicxp/types/StartAt;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "allowedParentalControls", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "getAllowedParentalControls", "()Lcom/amazon/digitalmusicxp/types/ParentalControls;", "serializedMusicRequestIdentityContext", "getSerializedMusicRequestIdentityContext", "Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "identityContext", "Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "getIdentityContext", "()Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptions", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "getPlaybackOptions", "()Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "stratusBenefitsInput", "Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "getStratusBenefitsInput", "()Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/DeviceContext;Lcom/amazon/digitalmusicxp/types/DeviceContext;Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;Ljava/util/List;Ljava/util/List;Lcom/amazon/digitalmusicxp/types/StartAt;Lcom/amazon/digitalmusicxp/types/ParentalControls;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/IdentityContextInput;Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;Lcom/amazon/digitalmusicxp/inputs/StratusBenefitsInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class InitiateQueueRequest {
    private final ParentalControls allowedParentalControls;
    private final CustomerDeviceSettings customerDeviceSettings;
    private final String customerId;
    private final DeviceContext deviceContext;
    private final IdentityContextInput identityContext;
    private final List<MetricsContext> metricsContext;
    private final PlaybackOptionsInput playbackOptions;
    private final String queueId;
    private final List<QueueSeedContextInput> queueSeeds;
    private final String serializedMusicRequestIdentityContext;
    private final StartAt startAt;
    private final DeviceContext targetDeviceContext;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InitiateQueueRequest)) {
            return false;
        }
        InitiateQueueRequest initiateQueueRequest = (InitiateQueueRequest) other;
        return Intrinsics.areEqual(this.customerId, initiateQueueRequest.customerId) && Intrinsics.areEqual(this.queueId, initiateQueueRequest.queueId) && Intrinsics.areEqual(this.deviceContext, initiateQueueRequest.deviceContext) && Intrinsics.areEqual(this.targetDeviceContext, initiateQueueRequest.targetDeviceContext) && Intrinsics.areEqual(this.customerDeviceSettings, initiateQueueRequest.customerDeviceSettings) && Intrinsics.areEqual(this.queueSeeds, initiateQueueRequest.queueSeeds) && Intrinsics.areEqual(this.metricsContext, initiateQueueRequest.metricsContext) && Intrinsics.areEqual(this.startAt, initiateQueueRequest.startAt) && Intrinsics.areEqual(this.allowedParentalControls, initiateQueueRequest.allowedParentalControls) && Intrinsics.areEqual(this.serializedMusicRequestIdentityContext, initiateQueueRequest.serializedMusicRequestIdentityContext) && Intrinsics.areEqual(this.identityContext, initiateQueueRequest.identityContext) && Intrinsics.areEqual(this.playbackOptions, initiateQueueRequest.playbackOptions) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final StratusBenefitsInput getStratusBenefitsInput() {
        return null;
    }

    public int hashCode() {
        int iHashCode = this.customerId.hashCode() * 31;
        String str = this.queueId;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.deviceContext.hashCode()) * 31;
        DeviceContext deviceContext = this.targetDeviceContext;
        int iHashCode3 = (iHashCode2 + (deviceContext == null ? 0 : deviceContext.hashCode())) * 31;
        CustomerDeviceSettings customerDeviceSettings = this.customerDeviceSettings;
        int iHashCode4 = (((iHashCode3 + (customerDeviceSettings == null ? 0 : customerDeviceSettings.hashCode())) * 31) + this.queueSeeds.hashCode()) * 31;
        List<MetricsContext> list = this.metricsContext;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        StartAt startAt = this.startAt;
        int iHashCode6 = (((iHashCode5 + (startAt == null ? 0 : startAt.hashCode())) * 31) + this.allowedParentalControls.hashCode()) * 31;
        String str2 = this.serializedMusicRequestIdentityContext;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        IdentityContextInput identityContextInput = this.identityContext;
        int iHashCode8 = (iHashCode7 + (identityContextInput == null ? 0 : identityContextInput.hashCode())) * 31;
        PlaybackOptionsInput playbackOptionsInput = this.playbackOptions;
        return ((iHashCode8 + (playbackOptionsInput == null ? 0 : playbackOptionsInput.hashCode())) * 31) + 0;
    }

    public String toString() {
        return "InitiateQueueRequest(customerId=" + this.customerId + ", queueId=" + this.queueId + ", deviceContext=" + this.deviceContext + ", targetDeviceContext=" + this.targetDeviceContext + ", customerDeviceSettings=" + this.customerDeviceSettings + ", queueSeeds=" + this.queueSeeds + ", metricsContext=" + this.metricsContext + ", startAt=" + this.startAt + ", allowedParentalControls=" + this.allowedParentalControls + ", serializedMusicRequestIdentityContext=" + this.serializedMusicRequestIdentityContext + ", identityContext=" + this.identityContext + ", playbackOptions=" + this.playbackOptions + ", stratusBenefitsInput=" + ((Object) null) + ')';
    }

    public InitiateQueueRequest(String customerId, String str, DeviceContext deviceContext, DeviceContext deviceContext2, CustomerDeviceSettings customerDeviceSettings, List<QueueSeedContextInput> queueSeeds, List<MetricsContext> list, StartAt startAt, ParentalControls allowedParentalControls, String str2, IdentityContextInput identityContextInput, PlaybackOptionsInput playbackOptionsInput, StratusBenefitsInput stratusBenefitsInput) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceContext, "deviceContext");
        Intrinsics.checkNotNullParameter(queueSeeds, "queueSeeds");
        Intrinsics.checkNotNullParameter(allowedParentalControls, "allowedParentalControls");
        this.customerId = customerId;
        this.queueId = str;
        this.deviceContext = deviceContext;
        this.targetDeviceContext = deviceContext2;
        this.customerDeviceSettings = customerDeviceSettings;
        this.queueSeeds = queueSeeds;
        this.metricsContext = list;
        this.startAt = startAt;
        this.allowedParentalControls = allowedParentalControls;
        this.serializedMusicRequestIdentityContext = str2;
        this.identityContext = identityContextInput;
        this.playbackOptions = playbackOptionsInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ InitiateQueueRequest(String str, String str2, DeviceContext deviceContext, DeviceContext deviceContext2, CustomerDeviceSettings customerDeviceSettings, List list, List list2, StartAt startAt, ParentalControls parentalControls, String str3, IdentityContextInput identityContextInput, PlaybackOptionsInput playbackOptionsInput, StratusBenefitsInput stratusBenefitsInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, deviceContext, (i & 8) != 0 ? null : deviceContext2, (i & 16) != 0 ? null : customerDeviceSettings, list, (i & 64) != 0 ? null : list2, (i & 128) != 0 ? null : startAt, parentalControls, (i & 512) != 0 ? null : str3, (i & 1024) != 0 ? null : identityContextInput, (i & 2048) != 0 ? null : playbackOptionsInput, (i & 4096) != 0 ? null : stratusBenefitsInput);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final DeviceContext getDeviceContext() {
        return this.deviceContext;
    }

    public final DeviceContext getTargetDeviceContext() {
        return this.targetDeviceContext;
    }

    public final CustomerDeviceSettings getCustomerDeviceSettings() {
        return this.customerDeviceSettings;
    }

    public final List<QueueSeedContextInput> getQueueSeeds() {
        return this.queueSeeds;
    }

    public final List<MetricsContext> getMetricsContext() {
        return this.metricsContext;
    }

    public final StartAt getStartAt() {
        return this.startAt;
    }

    public final ParentalControls getAllowedParentalControls() {
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
