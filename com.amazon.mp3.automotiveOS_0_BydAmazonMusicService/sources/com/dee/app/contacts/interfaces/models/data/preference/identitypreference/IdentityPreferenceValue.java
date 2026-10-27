package com.dee.app.contacts.interfaces.models.data.preference.identitypreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceValue;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum IdentityPreferenceValue implements IPreferenceValue<String> {
    ALL_CONTACTS("ALL_CONTACTS"),
    FAVORITES_ONLY("FAVORITES_ONLY"),
    ENABLED("true"),
    DISABLED("false");

    private String value;

    IdentityPreferenceValue(String str) {
        this.value = str;
    }

    public String getPreferenceValue() {
        return this.value;
    }

    public String getPreferenceValueAsString() {
        return this.value;
    }

    public boolean isPreferenceValueForString(String str) {
        return Objects.equals(this.value, str);
    }
}
