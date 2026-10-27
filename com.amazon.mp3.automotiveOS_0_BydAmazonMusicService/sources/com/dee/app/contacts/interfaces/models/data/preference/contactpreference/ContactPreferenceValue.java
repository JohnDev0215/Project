package com.dee.app.contacts.interfaces.models.data.preference.contactpreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceValue;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactPreferenceValue implements IPreferenceValue<String> {
    TRUE("TRUE"),
    FALSE("FALSE"),
    ENABLED("enabled"),
    DISABLED("disabled");

    private String value;

    ContactPreferenceValue(String str) {
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
