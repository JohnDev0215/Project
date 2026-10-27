package com.dee.app.contacts.interfaces.models.apis.cansync;

import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;

/* JADX INFO: loaded from: classes5.dex */
public class CanSyncContactsQueryConfig {
    private OwnerIdType ownerIdType;
    private String sourceDeviceId;

    public CanSyncContactsQueryConfig(String str) {
        this.sourceDeviceId = str;
    }

    public CanSyncContactsQueryConfig(String str, OwnerIdType ownerIdType) {
        this.sourceDeviceId = str;
        this.ownerIdType = ownerIdType;
    }

    public CanSyncContactsQueryConfig() {
    }

    public String getSourceDeviceId() {
        return this.sourceDeviceId;
    }

    public void setSourceDeviceId(String str) {
        this.sourceDeviceId = str;
    }

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }
}
