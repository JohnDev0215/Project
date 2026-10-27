package com.dee.app.contacts.interfaces.models.data.preference.contactpreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceKey;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactPreferenceKey implements IPreferenceKey<String> {
    DROP_IN("drop_in", "DropIn"),
    EMERGENCY_CONTACT("EMERGENCY_CONTACT", new String[0]),
    FAVORITE_CONTACT("FAVORITE_CONTACT", new String[0]),
    UNKNOWN_KEY("UNKNOWN_KEY", new String[0]);

    private final String primaryKey;
    private final List<String> secondaryKeys;

    ContactPreferenceKey(String str, String... strArr) {
        this.primaryKey = str;
        this.secondaryKeys = Arrays.asList(strArr);
    }

    public String getPreferenceKey() {
        return this.primaryKey;
    }

    public String getPreferenceKeyAsString() {
        return this.primaryKey;
    }

    public boolean isPreferenceKeyForString(String str) {
        return this.primaryKey.equals(str) || this.secondaryKeys.contains(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.primaryKey;
    }

    public static ContactPreferenceKey getPreferenceKey(String str) {
        for (ContactPreferenceKey contactPreferenceKey : values()) {
            if (contactPreferenceKey.getPreferenceKeyAsString().equals(str) || contactPreferenceKey.secondaryKeys.contains(str)) {
                return contactPreferenceKey;
            }
        }
        return UNKNOWN_KEY;
    }
}
