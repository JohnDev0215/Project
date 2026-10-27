package com.dee.app.sync.api;

import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsResponse;
import com.dee.app.contacts.interfaces.models.apis.getcontactbycontactid.GetContactByContactIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontactbycontactid.GetContactByContactIdResponse;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Response;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdResponse;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncRequest;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncResponse;
import com.dee.app.sync.api.sync.CanSyncContactsApi;
import com.dee.app.sync.api.sync.ContactsDownSyncApi;
import com.dee.app.sync.api.sync.ContactsUpSyncApi;
import com.dee.app.sync.api.sync.GetContactByContactIdApi;
import com.dee.app.sync.api.sync.GetMasterDeviceIdApi;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsSyncApiHandler {
    private final AbstractContactApiAction<CanSyncContactsRequest, CanSyncContactsResponse> mCanSyncContactsApi;
    private final AbstractContactApiAction<GetContactsV2Request, GetContactsV2Response> mContactsDownSyncApi;
    private final AbstractContactApiAction<ContactsUpSyncRequest, ContactsUpSyncResponse> mContactsUpSyncApi;
    private final AbstractContactApiAction<GetContactByContactIdRequest, GetContactByContactIdResponse> mGetContactByContactIdApi;
    private final AbstractContactApiAction<GetMasterDeviceIdRequest, GetMasterDeviceIdResponse> mGetMasterDeviceIdApi;

    public ContactsSyncApiHandler(ContactsDownSyncApi contactsDownSyncApi, CanSyncContactsApi canSyncContactsApi, GetMasterDeviceIdApi getMasterDeviceIdApi, GetContactByContactIdApi getContactByContactIdApi, ContactsUpSyncApi contactsUpSyncApi) {
        this.mContactsDownSyncApi = contactsDownSyncApi;
        this.mCanSyncContactsApi = canSyncContactsApi;
        this.mGetMasterDeviceIdApi = getMasterDeviceIdApi;
        this.mGetContactByContactIdApi = getContactByContactIdApi;
        this.mContactsUpSyncApi = contactsUpSyncApi;
    }

    public Observable<GetContactsV2Response> getContactsV2(GetContactsV2Request getContactsV2Request) {
        return this.mContactsDownSyncApi.performAction(getContactsV2Request);
    }

    public Observable<ContactsUpSyncResponse> uploadContacts(ContactsUpSyncRequest contactsUpSyncRequest) {
        return this.mContactsUpSyncApi.performAction(contactsUpSyncRequest);
    }
}
