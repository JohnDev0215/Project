package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ResourceType implements MetricValueType {
    STORE("STORE"),
    CLOUD_PLAYER("CLOUD_PLAYER"),
    UNLIMITED_MUSIC("UNLIMITED_MUSIC"),
    PRIME_MUSIC("PRIME_MUSIC"),
    AMF_MUSIC("AMF_MUSIC");

    private String mName;

    ResourceType(String str) {
        this.mName = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mName;
    }
}
