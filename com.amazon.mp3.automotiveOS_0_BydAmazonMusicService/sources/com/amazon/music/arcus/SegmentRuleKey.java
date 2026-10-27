package com.amazon.music.arcus;

/* JADX INFO: loaded from: classes4.dex */
public enum SegmentRuleKey {
    MARKETPLACE("marketplace_id"),
    TERRITORY("territory"),
    ENVIRONMENT("environment"),
    PLATFORM("platform");

    private final String key;

    SegmentRuleKey(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
