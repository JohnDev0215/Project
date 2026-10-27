package com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts;

import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;

/* JADX INFO: loaded from: classes5.dex */
public class BatchUpdateLABContactsQueryConfig {
    private OwnerIdType ownerIdType;

    public BatchUpdateLABContactsQueryConfig(OwnerIdType ownerIdType) {
        this.ownerIdType = ownerIdType;
    }

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }
}
