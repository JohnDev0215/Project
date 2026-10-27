package com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceValue;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactOwnerPreferenceValue implements IPreferenceValue<String> {
    ENABLED("enabled"),
    DISABLED("disabled"),
    SELECTIVE_IMPORT("selective-import");

    private String value;

    ContactOwnerPreferenceValue(String str) {
        this.value = str;
    }

    public String getPreferenceValue() {
        return this.value;
    }

    public String getPreferenceValueAsString() {
        return String.valueOf(this.value);
    }

    public boolean isPreferenceValueForString(String str) {
        return Objects.equals(this.value, str);
    }
}
