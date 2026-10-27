package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum ClientConfig {
    Photos("amzn_mshop_android_v2_us"),
    Music("amzn_mshop_android_v2_us");

    final String value;

    ClientConfig(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
