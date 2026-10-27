package com.amazon.avod.ads.api;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TEVSClientDecorator {
    private final String mAdvertisingId;
    private final String mConsumptionId;
    private final Boolean mUserOptOutSetting;

    public TEVSClientDecorator(@Nullable String str, @Nullable Boolean bool, @Nullable String str2) {
        this.mAdvertisingId = str;
        this.mUserOptOutSetting = bool;
        this.mConsumptionId = str2;
    }

    @Nullable
    public String getAdvertisingId() {
        return this.mAdvertisingId;
    }

    @Nullable
    public Boolean getUserOptOutSetting() {
        return this.mUserOptOutSetting;
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}
