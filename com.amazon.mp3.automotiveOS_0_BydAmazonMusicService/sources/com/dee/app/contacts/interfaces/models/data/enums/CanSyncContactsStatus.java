package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum CanSyncContactsStatus {
    ALLOWED("allowed"),
    AMAZON_PREFERENCE_NOT_ALLOWED("not_allowed_amzn_preference"),
    DEVICE_IMPORT_NOT_ALLOWED("not_allowed_device_import"),
    UNKNOWN("UNKNOWN");

    private final String status;

    CanSyncContactsStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public static CanSyncContactsStatus getStatusFromString(String str) {
        for (CanSyncContactsStatus canSyncContactsStatus : values()) {
            if (canSyncContactsStatus.getStatus().equals(str)) {
                return canSyncContactsStatus;
            }
        }
        return UNKNOWN;
    }
}
