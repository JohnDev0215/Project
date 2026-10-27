package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class InAppPurchaseDestination extends Destination {
    private final String scheduleId;
    private final String sku;

    public InAppPurchaseDestination(String str, String str2, String str3, String str4) {
        super(str3, str4);
        this.sku = str;
        this.scheduleId = str2;
    }

    public String getSku() {
        return this.sku;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }
}
