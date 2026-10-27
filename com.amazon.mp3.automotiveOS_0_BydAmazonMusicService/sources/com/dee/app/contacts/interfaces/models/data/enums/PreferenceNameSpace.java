package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum PreferenceNameSpace {
    AMAZON("com.amazon"),
    COMMS("com.amazon.comms"),
    PHOTOS("com.amazon.photos"),
    MUSIC("com.amazon.music");

    final String value;

    PreferenceNameSpace(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static PreferenceNameSpace getPreferenceNameSpaceKeyForValue(String str) {
        for (PreferenceNameSpace preferenceNameSpace : values()) {
            if (preferenceNameSpace.getValue().equals(str)) {
                return preferenceNameSpace;
            }
        }
        return null;
    }
}
