package com.android.billingclient.api;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class InAppMessageResult {

    @Nullable
    private final String mPurchaseToken;
    private final int mResponseCode;

    public InAppMessageResult(int i, @Nullable String str) {
        this.mResponseCode = i;
        this.mPurchaseToken = str;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }
}
