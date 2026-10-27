package com.amazon.mp3.billing.event;

import kotlin.Metadata;

/* JADX INFO: compiled from: GoogleBillingClientEvent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001f¨\u0006 "}, d2 = {"Lcom/amazon/mp3/billing/event/GoogleBillingClientEvent;", "", "Lcom/amazon/mp3/billing/event/BillingEvent;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getFlexEventFs1", "getFlexEventName", "QUERY_SKU_SUBS_BILLING_NOT_SUPPORTED", "QUERY_SKU_CLIENT_NOT_READY", "QUERY_SKU_SUCCESS", "QUERY_SKU_NO_PRODUCTS_RETURNED", "QUERY_SKU_OTHER_ERRORS", "BILLING_SETUP_SUCCESS", "BILLING_SETUP_BILLING_UNAVAILABLE", "BILLING_SETUP_BILLING_AVAILABLE_BUT_SUBS_NOT_SUPPORTED", "BILLING_SETUP_OTHER_ERRORS", "QUERY_PURCHASES_SUBS_BILLING_NOT_SUPPORTED", "QUERY_PURCHASES_CLIENT_NOT_READY", "QUERY_PURCHASES_SUCCESS", "QUERY_PURCHASES_OTHER_ERRORS", "LAUNCH_BILLING_FLOW_SUBS_BILLING_NOT_SUPPORTED", "LAUNCH_BILLING_FLOW_CLIENT_NOT_READY", "LAUNCH_BILLING_FLOW_INVALID_SKU", "LAUNCH_BILLING_FLOW_SUCCESS", "LAUNCH_BILLING_FLOW_OTHER_ERRORS", "ON_PURCHASES_UPDATED_SUCCESS", "ON_PURCHASES_UPDATED_USER_CANCELLED", "ON_PURCHASES_UPDATED_OTHER_ERRORS", "UNKNOWN_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum GoogleBillingClientEvent implements BillingEvent {
    QUERY_SKU_SUBS_BILLING_NOT_SUPPORTED("querySkuSubsBillingNotSupported"),
    QUERY_SKU_CLIENT_NOT_READY("querySkuClientNotReady"),
    QUERY_SKU_SUCCESS("querySkuSuccess"),
    QUERY_SKU_NO_PRODUCTS_RETURNED("querySkuNoProductsReturned"),
    QUERY_SKU_OTHER_ERRORS("querySkuOtherErrors"),
    BILLING_SETUP_SUCCESS("billingSetupSuccess"),
    BILLING_SETUP_BILLING_UNAVAILABLE("billingSetupBillingUnavailable"),
    BILLING_SETUP_BILLING_AVAILABLE_BUT_SUBS_NOT_SUPPORTED("billingSetupBillingAvailableButSubNotSupported"),
    BILLING_SETUP_OTHER_ERRORS("billingSetupOtherErrors"),
    QUERY_PURCHASES_SUBS_BILLING_NOT_SUPPORTED("queryPurchasesSubsBillingNotSupported"),
    QUERY_PURCHASES_CLIENT_NOT_READY("queryPurchasesClientNotReady"),
    QUERY_PURCHASES_SUCCESS("queryPurchasesSuccess"),
    QUERY_PURCHASES_OTHER_ERRORS("queryPurchasesOtherErrors"),
    LAUNCH_BILLING_FLOW_SUBS_BILLING_NOT_SUPPORTED("launchBillingFlowSubsBillingNotSupported"),
    LAUNCH_BILLING_FLOW_CLIENT_NOT_READY("launchBillingFlowClientNotReady"),
    LAUNCH_BILLING_FLOW_INVALID_SKU("launchBillingFlowInvalidSKU"),
    LAUNCH_BILLING_FLOW_SUCCESS("launchBillingFlowSuccess"),
    LAUNCH_BILLING_FLOW_OTHER_ERRORS("launchBillingFlowOtherErrors"),
    ON_PURCHASES_UPDATED_SUCCESS("onPurchasesUpdatedSuccess"),
    ON_PURCHASES_UPDATED_USER_CANCELLED("onPurchasesUpdatedUserCancelled"),
    ON_PURCHASES_UPDATED_OTHER_ERRORS("onPurchasesUpdatedOtherErrors"),
    UNKNOWN_ERROR("unknownError");

    private final String value;

    @Override // com.amazon.mp3.billing.event.BillingEvent
    public String getFlexEventName() {
        return "purchaseGoogleBillingClientEvent";
    }

    GoogleBillingClientEvent(String str) {
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
