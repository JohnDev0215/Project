package com.dee.app.contacts.interfaces.models.data;

import com.dee.app.contacts.interfaces.models.data.enums.SyncOperationType;

/* JADX INFO: loaded from: classes5.dex */
public class ContactForSync {
    private ContactUploadInfo contact;
    private SyncOperationType syncOperationType;

    public ContactForSync(ContactUploadInfo contactUploadInfo, SyncOperationType syncOperationType) {
        this.contact = contactUploadInfo;
        this.syncOperationType = syncOperationType;
    }

    public SyncOperationType getSyncOperationType() {
        return this.syncOperationType;
    }
}
