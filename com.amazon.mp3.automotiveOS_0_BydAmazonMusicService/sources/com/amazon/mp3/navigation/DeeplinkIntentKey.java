package com.amazon.mp3.navigation;

/* JADX INFO: loaded from: classes3.dex */
public enum DeeplinkIntentKey {
    KEY_REF_MARKER("refMarker"),
    KEY_ASSOCIATE_TAG("associateTag");

    private final String keyName;

    DeeplinkIntentKey(String keyName) {
        this.keyName = keyName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.keyName;
    }
}
