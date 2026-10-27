package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum UiConfig {
    Photos("amazon-contacts-photos"),
    Music("amazon-contacts-music");

    final String value;

    UiConfig(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
