package com.amazon.mp3.billing.event;

import kotlin.Metadata;

/* JADX INFO: compiled from: PurchaseEligibilityEvent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/billing/event/PurchaseEligibilityEvent;", "", "Lcom/amazon/mp3/billing/event/BillingEvent;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getFlexEventFs1", "getFlexEventName", "COULD_NOT_DETERMINE_CUSTOMER_MUSIC_TERRITORY", "NOT_ENABLED_IN_CUSTOMER_MUSIC_TERRITORY", "TERRITORY_MISMATCH", "COULD_NOT_DETERMINE_PLAY_STORE_TERRITORY", "NO_OFFERS_AVAILABLE", "NO_UPSELL_OFFERS_AVAILABLE", "NO_OFFER_TYPE", "NO_PLAN_SCHEDULE", "NO_OFFER_IN_PLAN_SCHEDULE", "NO_EXTERNAL_PRODUCT_ID", "ALREADY_SUBSCRIBED", "UNKNOWN_ERROR", "SERVICE_EXCEPTION", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PurchaseEligibilityEvent implements BillingEvent {
    COULD_NOT_DETERMINE_CUSTOMER_MUSIC_TERRITORY("couldNotDetermineCustomerMusicTerritory"),
    NOT_ENABLED_IN_CUSTOMER_MUSIC_TERRITORY("notEnabledInCustomerMusicTerritory"),
    TERRITORY_MISMATCH("territoryMismatch"),
    COULD_NOT_DETERMINE_PLAY_STORE_TERRITORY("couldNotDeterminePlayStoreTerritory"),
    NO_OFFERS_AVAILABLE("noOffersAvailable"),
    NO_UPSELL_OFFERS_AVAILABLE("noUpsellOfferAvailable"),
    NO_OFFER_TYPE("noOfferType"),
    NO_PLAN_SCHEDULE("noPlanSchedule"),
    NO_OFFER_IN_PLAN_SCHEDULE("noOfferInPlanSchedule"),
    NO_EXTERNAL_PRODUCT_ID("noExternalProductId"),
    ALREADY_SUBSCRIBED("alreadySubscribed"),
    UNKNOWN_ERROR("unknownError"),
    SERVICE_EXCEPTION("serviceException");

    private final String value;

    @Override // com.amazon.mp3.billing.event.BillingEvent
    public String getFlexEventName() {
        return "purchaseEligibilityEvent";
    }

    PurchaseEligibilityEvent(String str) {
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
