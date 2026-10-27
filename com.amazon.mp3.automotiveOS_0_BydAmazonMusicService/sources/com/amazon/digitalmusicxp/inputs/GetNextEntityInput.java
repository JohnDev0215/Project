package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetNextEntityInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b#\u0010$J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001b\u0010\u001dR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/GetNextEntityInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "deviceContext", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "getDeviceContext", "()Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "adContext", "Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "getAdContext", "()Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "currentEntityReferenceId", "getCurrentEntityReferenceId", "isUserInitiated", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "allowedParentalControls", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "getAllowedParentalControls", "()Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;Lcom/amazon/digitalmusicxp/inputs/AdContextInput;Ljava/lang/String;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GetNextEntityInput {
    private final AdContextInput adContext;
    private final ParentalControlsInput allowedParentalControls;
    private final String currentEntityReferenceId;
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final Boolean isUserInitiated;
    private final String queueId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetNextEntityInput)) {
            return false;
        }
        GetNextEntityInput getNextEntityInput = (GetNextEntityInput) other;
        return Intrinsics.areEqual(this.customerId, getNextEntityInput.customerId) && Intrinsics.areEqual(this.queueId, getNextEntityInput.queueId) && Intrinsics.areEqual(this.deviceContext, getNextEntityInput.deviceContext) && Intrinsics.areEqual(this.adContext, getNextEntityInput.adContext) && Intrinsics.areEqual(this.currentEntityReferenceId, getNextEntityInput.currentEntityReferenceId) && Intrinsics.areEqual(this.isUserInitiated, getNextEntityInput.isUserInitiated) && Intrinsics.areEqual(this.allowedParentalControls, getNextEntityInput.allowedParentalControls);
    }

    public int hashCode() {
        int iHashCode = ((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + this.deviceContext.hashCode()) * 31;
        AdContextInput adContextInput = this.adContext;
        int iHashCode2 = (iHashCode + (adContextInput == null ? 0 : adContextInput.hashCode())) * 31;
        String str = this.currentEntityReferenceId;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.isUserInitiated;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        ParentalControlsInput parentalControlsInput = this.allowedParentalControls;
        return iHashCode4 + (parentalControlsInput != null ? parentalControlsInput.hashCode() : 0);
    }

    public String toString() {
        return "GetNextEntityInput(customerId=" + this.customerId + ", queueId=" + this.queueId + ", deviceContext=" + this.deviceContext + ", adContext=" + this.adContext + ", currentEntityReferenceId=" + this.currentEntityReferenceId + ", isUserInitiated=" + this.isUserInitiated + ", allowedParentalControls=" + this.allowedParentalControls + ')';
    }

    public GetNextEntityInput(String customerId, String queueId, DeviceContextInput deviceContext, AdContextInput adContextInput, String str, Boolean bool, ParentalControlsInput parentalControlsInput) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(deviceContext, "deviceContext");
        this.customerId = customerId;
        this.queueId = queueId;
        this.deviceContext = deviceContext;
        this.adContext = adContextInput;
        this.currentEntityReferenceId = str;
        this.isUserInitiated = bool;
        this.allowedParentalControls = parentalControlsInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ GetNextEntityInput(String str, String str2, DeviceContextInput deviceContextInput, AdContextInput adContextInput, String str3, Boolean bool, ParentalControlsInput parentalControlsInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, deviceContextInput, (i & 8) != 0 ? null : adContextInput, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : bool, (i & 64) != 0 ? null : parentalControlsInput);
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

    public final AdContextInput getAdContext() {
        return this.adContext;
    }

    public final String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    /* JADX INFO: renamed from: isUserInitiated, reason: from getter */
    public final Boolean getIsUserInitiated() {
        return this.isUserInitiated;
    }

    public final ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }
}
