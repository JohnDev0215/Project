package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum StorePageType implements MetricValueType {
    STORE_SEARCH("storeSearch"),
    ALBUM_DETAIL("albumDetail"),
    ARTIST_DETAIL("artistDetail"),
    PURCHASE_COMPLETED("purchaseCompleted"),
    STORE_HOME("storeHome"),
    BM_MESSAGING("messaging"),
    BM_DEVICE_AUTH("device_auth"),
    BM_ACCOUNT_VALIDATION("account_val");

    private final String mMetricValue;

    StorePageType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
