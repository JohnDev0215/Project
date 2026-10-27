package com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceKey;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactOwnerPreferenceKey implements IPreferenceKey<String> {
    AMAZON_CONTACTS("AMAZON_CONTACTS", new String[0]),
    CONTACT_IMPORT("CONTACT_IMPORT", new String[0]),
    AUTO_DISCOVERY("AUTO_DISCOVERY", new String[0]),
    PHONE_NUMBER_VERIFIED("PHONE_NUMBER_VERIFIED", new String[0]),
    UNKNOWN_KEY("UNKNOWN_KEY", new String[0]);

    private final String primaryKey;
    private final List<String> secondaryKeys;

    ContactOwnerPreferenceKey(String str, String... strArr) {
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

    public static ContactOwnerPreferenceKey getPreferenceKey(String str) {
        for (ContactOwnerPreferenceKey contactOwnerPreferenceKey : values()) {
            if (contactOwnerPreferenceKey.getPreferenceKeyAsString().equals(str) || contactOwnerPreferenceKey.secondaryKeys.contains(str)) {
                return contactOwnerPreferenceKey;
            }
        }
        return UNKNOWN_KEY;
    }
}
