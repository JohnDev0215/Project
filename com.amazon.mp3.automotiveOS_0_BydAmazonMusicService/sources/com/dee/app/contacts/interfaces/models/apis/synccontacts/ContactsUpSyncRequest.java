package com.dee.app.contacts.interfaces.models.apis.synccontacts;

import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsUpSyncRequest {
    private String ownerId;
    private OwnerIdType ownerIdType;
    private String sourceDeviceName;

    public ContactsUpSyncRequest(String str) {
        this.sourceDeviceName = str;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }

    public void setOwnerIdType(OwnerIdType ownerIdType) {
        this.ownerIdType = ownerIdType;
    }

    public String getSourceDeviceName() {
        return this.sourceDeviceName;
    }
}
