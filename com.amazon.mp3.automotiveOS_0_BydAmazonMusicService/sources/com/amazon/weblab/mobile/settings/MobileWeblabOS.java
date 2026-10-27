package com.amazon.weblab.mobile.settings;

/* JADX INFO: loaded from: classes5.dex */
public enum MobileWeblabOS {
    ANDROID("ANDROID"),
    FIRE_OS("FIRE-OS");

    private final String value;

    MobileWeblabOS(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
