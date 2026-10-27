package com.dee.app.sync.core;

import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Response;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncRequest;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncResponse;
import com.dee.app.sync.api.ContactsSyncApiHandler;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsSyncManager {
    ContactsSyncApiHandler contactsSyncApiHandler;

    public Observable<ContactsUpSyncResponse> startContactsUpSync(ContactsUpSyncRequest contactsUpSyncRequest) {
        return this.contactsSyncApiHandler.uploadContacts(contactsUpSyncRequest);
    }

    public Observable<GetContactsV2Response> startContactsDownSync(GetContactsV2Request getContactsV2Request) {
        return this.contactsSyncApiHandler.getContactsV2(getContactsV2Request);
    }
}
