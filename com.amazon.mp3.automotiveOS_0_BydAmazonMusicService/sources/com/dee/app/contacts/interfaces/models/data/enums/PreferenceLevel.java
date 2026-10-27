package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum PreferenceLevel {
    HOME_GROUP("HomeGroup"),
    NONE("None");

    private String preferenceLevel;

    PreferenceLevel(String str) {
        this.preferenceLevel = str;
    }

    public String getPreferenceLevel() {
        return this.preferenceLevel;
    }
}
