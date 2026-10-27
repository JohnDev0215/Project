package com.dee.app.contacts.common.constants;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum ApiNameConstant {
    GET_ACTIVE_CONTACTS_API("GetActiveContactsApi"),
    GET_CONTACT_API("GetContactApi"),
    GET_CONTACTS_API("GetContactsApi"),
    GET_CONTACTS_V2_API("GetContactsV2Api"),
    SYNC_CONTACTS_API("SyncContactsApi"),
    CAN_SYNC_CONTACTS_API("CanSyncContactsApi"),
    GET_CONTACT_BY_CONTACT_ID_API("GetContactByContactIdApi"),
    GET_DEVICES_API("GetDevicesApi"),
    GET_MASTER_DEVICE_ID("GetMasterDeviceIdApi"),
    GET_PREFERENCE_API("GetContactPreferenceApi"),
    SET_PREFERENCE_API("SetContactPreferenceApi"),
    GET_IDENTITY_PREFERENCE_API("GetIdentityPreferenceApi"),
    SET_IDENTITY_PREFERENCE_API("SetIdentityPreferenceApi"),
    GET_OWNER_PREFERENCE_API("GetOwnerPreferenceApi"),
    SET_OWNER_PREFERENCE_API("SetOwnerPreferenceApi"),
    GET_ADDRESS_BOOKS_API("GetAddressBooksApi"),
    DELETE_ADDRESS_BOOKS_API("DeleteAddressBooksApi"),
    GET_PNV_URL_API("GetPhoneNumberVerificationURLApi"),
    GET_ACCOUNTS_API("GetAccountsApi");

    private final String mApiName;

    ApiNameConstant(String str) {
        this.mApiName = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.mApiName;
    }
}
