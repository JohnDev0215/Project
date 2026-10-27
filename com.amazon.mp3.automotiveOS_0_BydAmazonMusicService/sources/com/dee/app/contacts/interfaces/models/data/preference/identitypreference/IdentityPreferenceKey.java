package com.dee.app.contacts.interfaces.models.data.preference.identitypreference;

import com.dee.app.contacts.interfaces.models.data.preference.IPreferenceKey;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public enum IdentityPreferenceKey implements IPreferenceKey<String> {
    CONTACT_SYNC_ENABLED("contact_sync_enabled", "ContactSyncEnabled"),
    CONTACT_ACCESS_CONTROL_TYPE("contact_access_control_type", "CONTACT_ACCESS"),
    GROUP_AMC_PREFERENCE("mpu_enabled", "MpuEnabled"),
    BLOCK_UNKNOWN_NUMBER_PREFERENCE("block_unknown_number", "BlockUnknownNumber");

    private final String primaryKey;
    private final List<String> secondaryKeys;

    IdentityPreferenceKey(String str, String... strArr) {
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
}
