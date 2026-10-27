package com.amazon.mp3.billing.event;

import kotlin.Metadata;

/* JADX INFO: compiled from: PurchaseEvaluationEvent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/billing/event/PurchaseEvaluationEvent;", "", "Lcom/amazon/mp3/billing/event/BillingEvent;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getFlexEventFs1", "getFlexEventName", "EVALUATION_SUCCESS", "SUBS_BILLING_FEATURE_NOT_SUPPORTED", "INVALID_SCHEDULE_ID", "INVALID_SKU_NAME", "SKU_DETAILS_NOT_READY", "INVALID_PARAMETER_EXCEPTION", "INELIGIBLE_OFFER_EXCEPTION", "CUSTOMER_ALREADY_SUBSCRIBED_EXCEPTION", "GEO_CHECK_FAILED_EXCEPTION", "FRAUDULENT_ACCOUNT_EXCEPTION", "SERVICE_EXCEPTION", "REQUEST_THROTTLED_EXCEPTION", "ACCOUNT_LOCKED_EXCEPTION", "EMP_AMAZON_PRICE_MISMATCH_EXCEPTION", "UNKNOWN_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PurchaseEvaluationEvent implements BillingEvent {
    EVALUATION_SUCCESS("evaluationSuccess"),
    SUBS_BILLING_FEATURE_NOT_SUPPORTED("subsBillingFeatureNotSupported"),
    INVALID_SCHEDULE_ID("invalidScheduleId"),
    INVALID_SKU_NAME("invalidSkuName"),
    SKU_DETAILS_NOT_READY("skuDetailsNotReady"),
    INVALID_PARAMETER_EXCEPTION("invalidParameterException"),
    INELIGIBLE_OFFER_EXCEPTION("ineligibleOfferException"),
    CUSTOMER_ALREADY_SUBSCRIBED_EXCEPTION("customerAlreadySubscribedException"),
    GEO_CHECK_FAILED_EXCEPTION("geocheckFailedException"),
    FRAUDULENT_ACCOUNT_EXCEPTION("fraudulentAccountException"),
    SERVICE_EXCEPTION("serviceException"),
    REQUEST_THROTTLED_EXCEPTION("requestThrottledException"),
    ACCOUNT_LOCKED_EXCEPTION("accountLockedException"),
    EMP_AMAZON_PRICE_MISMATCH_EXCEPTION("empAmazonPriceMismatchException"),
    UNKNOWN_ERROR("unknownError");

    private final String value;

    @Override // com.amazon.mp3.billing.event.BillingEvent
    public String getFlexEventName() {
        return "purchaseEvaluationEvent";
    }

    PurchaseEvaluationEvent(String str) {
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
