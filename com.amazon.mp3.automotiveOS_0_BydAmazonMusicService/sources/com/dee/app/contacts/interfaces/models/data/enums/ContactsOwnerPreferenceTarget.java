package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactsOwnerPreferenceTarget {
    ALEXA_COMMS("AlexaComms"),
    AMAZON_PHOTOS("AmazonPhotos"),
    AMAZON_MUSIC("AmazonMusic"),
    ALL("ALL");

    final String value;

    ContactsOwnerPreferenceTarget(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
