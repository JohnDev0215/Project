package com.amazon.music.nautilus;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSSchedule {
    private boolean addonRequired;
    private MSOSSubscriptionOffer bestOffer;
    private String promoType;
    private MSOSSubscriptionOffer rolloverPlan;
    private String scheduleId;

    public MSOSSchedule(MSOSSubscriptionOffer mSOSSubscriptionOffer, MSOSSubscriptionOffer mSOSSubscriptionOffer2, String str, String str2, boolean z) {
        this.bestOffer = mSOSSubscriptionOffer;
        this.rolloverPlan = mSOSSubscriptionOffer2;
        this.scheduleId = str;
        this.promoType = str2;
        this.addonRequired = z;
    }
}
