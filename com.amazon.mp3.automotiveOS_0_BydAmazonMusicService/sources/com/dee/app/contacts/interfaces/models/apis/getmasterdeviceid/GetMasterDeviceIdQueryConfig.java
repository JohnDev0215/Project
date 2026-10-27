package com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid;

import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;
import com.dee.app.contacts.interfaces.models.data.enums.SyncType;

/* JADX INFO: loaded from: classes5.dex */
public class GetMasterDeviceIdQueryConfig {
    private OwnerIdType ownerIdType;
    private SyncType syncType;

    public GetMasterDeviceIdQueryConfig() {
    }

    public GetMasterDeviceIdQueryConfig(SyncType syncType) {
        this.syncType = syncType;
    }

    public GetMasterDeviceIdQueryConfig(OwnerIdType ownerIdType) {
        this.ownerIdType = ownerIdType;
    }

    public SyncType getSyncType() {
        return this.syncType;
    }

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }
}
