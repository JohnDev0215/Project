package com.amazon.mp3.billing.event;

import kotlin.Metadata;

/* JADX INFO: compiled from: PurchaseSubmissionEvent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/billing/event/PurchaseSubmissionEvent;", "", "Lcom/amazon/mp3/billing/event/BillingEvent;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getFlexEventFs1", "getFlexEventName", "SUBMISSION_SUCCESS", "UPGRADE_PENDING", "UPGRADE_SUCCESS", "INVALID_SKU", "INVALID_PARAMETER_EXCEPTION", "SERVICE_EXCEPTION", "REQUEST_THROTTLED_EXCEPTION", "ACCOUNT_LOCKED_EXCEPTION", "UNKNOWN_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PurchaseSubmissionEvent implements BillingEvent {
    SUBMISSION_SUCCESS("submissionSuccess"),
    UPGRADE_PENDING("upgradePending"),
    UPGRADE_SUCCESS("upgradeSuccess"),
    INVALID_SKU("invalidSku"),
    INVALID_PARAMETER_EXCEPTION("invalidParameterException"),
    SERVICE_EXCEPTION("serviceException"),
    REQUEST_THROTTLED_EXCEPTION("requestThrottledException"),
    ACCOUNT_LOCKED_EXCEPTION("accountLockedException"),
    UNKNOWN_ERROR("unknownError");

    private final String value;

    @Override // com.amazon.mp3.billing.event.BillingEvent
    public String getFlexEventName() {
        return "purchaseSubmissionEvent";
    }

    PurchaseSubmissionEvent(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    @Override // com.amazon.mp3.billing.event.BillingEvent
    /* JADX INFO: renamed from: getFlexEventFs1, reason: from getter */
    public String getValue() {
        return this.value;
    }
}
