package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.QueueViewDirectionEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetQueueViewInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b$\u0010%J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\fR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/GetQueueViewInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "deviceContext", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "getDeviceContext", "()Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "currentEntityReferenceId", "getCurrentEntityReferenceId", "Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "direction", "Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "getDirection", "()Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "desiredNumberOfTracks", "Ljava/lang/Integer;", "getDesiredNumberOfTracks", "()Ljava/lang/Integer;", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "allowedParentalControls", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "getAllowedParentalControls", "()Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;Ljava/lang/Integer;Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GetQueueViewInput {
    private final ParentalControlsInput allowedParentalControls;
    private final String currentEntityReferenceId;
    private final String customerId;
    private final Integer desiredNumberOfTracks;
    private final DeviceContextInput deviceContext;
    private final QueueViewDirectionEnum direction;
    private final String queueId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetQueueViewInput)) {
            return false;
        }
        GetQueueViewInput getQueueViewInput = (GetQueueViewInput) other;
        return Intrinsics.areEqual(this.customerId, getQueueViewInput.customerId) && Intrinsics.areEqual(this.queueId, getQueueViewInput.queueId) && Intrinsics.areEqual(this.deviceContext, getQueueViewInput.deviceContext) && Intrinsics.areEqual(this.currentEntityReferenceId, getQueueViewInput.currentEntityReferenceId) && this.direction == getQueueViewInput.direction && Intrinsics.areEqual(this.desiredNumberOfTracks, getQueueViewInput.desiredNumberOfTracks) && Intrinsics.areEqual(this.allowedParentalControls, getQueueViewInput.allowedParentalControls);
    }

    public int hashCode() {
        int iHashCode = ((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + this.deviceContext.hashCode()) * 31;
        String str = this.currentEntityReferenceId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        QueueViewDirectionEnum queueViewDirectionEnum = this.direction;
        int iHashCode3 = (iHashCode2 + (queueViewDirectionEnum == null ? 0 : queueViewDirectionEnum.hashCode())) * 31;
        Integer num = this.desiredNumberOfTracks;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        ParentalControlsInput parentalControlsInput = this.allowedParentalControls;
        return iHashCode4 + (parentalControlsInput != null ? parentalControlsInput.hashCode() : 0);
    }

    public String toString() {
        return "GetQueueViewInput(customerId=" + this.customerId + ", queueId=" + this.queueId + ", deviceContext=" + this.deviceContext + ", currentEntityReferenceId=" + this.currentEntityReferenceId + ", direction=" + this.direction + ", desiredNumberOfTracks=" + this.desiredNumberOfTracks + ", allowedParentalControls=" + this.allowedParentalControls + ')';
    }

    public GetQueueViewInput(String customerId, String queueId, DeviceContextInput deviceContext, String str, QueueViewDirectionEnum queueViewDirectionEnum, Integer num, ParentalControlsInput parentalControlsInput) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(deviceContext, "deviceContext");
        this.customerId = customerId;
        this.queueId = queueId;
        this.deviceContext = deviceContext;
        this.currentEntityReferenceId = str;
        this.direction = queueViewDirectionEnum;
        this.desiredNumberOfTracks = num;
        this.allowedParentalControls = parentalControlsInput;
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

    public final String getCurrentEntityReferenceId() {
        return this.currentEntityReferenceId;
    }

    public final Integer getDesiredNumberOfTracks() {
        return this.desiredNumberOfTracks;
    }

    public final ParentalControlsInput getAllowedParentalControls() {
        return this.allowedParentalControls;
    }
}
