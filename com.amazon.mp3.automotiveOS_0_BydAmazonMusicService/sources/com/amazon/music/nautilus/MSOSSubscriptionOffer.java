package com.amazon.music.nautilus;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSSubscriptionOffer {
    private boolean autoRenew;
    private String contractType;
    private String currencyCode;
    private String planId;
    private String price;
    private String promotionId;
    private String timeInterval;

    public MSOSSubscriptionOffer(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        this.autoRenew = z;
        this.planId = str;
        this.price = str2;
        this.currencyCode = str3;
        this.timeInterval = str4;
        this.promotionId = str5;
        this.contractType = str6;
    }
}
