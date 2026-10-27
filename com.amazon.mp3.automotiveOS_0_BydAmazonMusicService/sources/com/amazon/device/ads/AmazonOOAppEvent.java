package com.amazon.device.ads;

import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonOOAppEvent extends AppEvent {
    public static final AmazonOOAppEvent FIRST_LOGIN = new AmazonOOAppEvent("firstLogin");
    public static final AmazonOOAppEvent FIRST_PURCHASE = new AmazonOOAppEvent("firstPurchase");
    public static final AmazonOOAppEvent LOGIN = new AmazonOOAppEvent("login");
    public static final AmazonOOAppEvent PURCHASE = new AmazonOOAppEvent("purchase");

    @Override // com.amazon.device.ads.AppEvent
    public /* bridge */ /* synthetic */ String getEventName() {
        return super.getEventName();
    }

    @Override // com.amazon.device.ads.AppEvent
    public /* bridge */ /* synthetic */ Set getPropertyEntries() {
        return super.getPropertyEntries();
    }

    @Override // com.amazon.device.ads.AppEvent
    public /* bridge */ /* synthetic */ long getTimestamp() {
        return super.getTimestamp();
    }

    @Override // com.amazon.device.ads.AppEvent
    public /* bridge */ /* synthetic */ AppEvent setProperty(String str, String str2) {
        return super.setProperty(str, str2);
    }

    @Override // com.amazon.device.ads.AppEvent
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    AmazonOOAppEvent(String str) {
        super(str);
    }

    public AmazonOOAppEvent(String str, long j) {
        super(str, j);
    }
}
