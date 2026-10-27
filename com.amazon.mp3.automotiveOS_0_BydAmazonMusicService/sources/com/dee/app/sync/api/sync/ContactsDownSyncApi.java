package com.dee.app.sync.api.sync;

import android.content.SharedPreferences;
import com.dee.app.contacts.common.models.server.GetContactsV2ServerResponse;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsConverterUtil;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Response;
import com.dee.app.sync.api.AbstractContactApiAction;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsDownSyncApi extends AbstractContactApiAction<GetContactsV2Request, GetContactsV2Response> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", ContactsDownSyncApi.class);
    private final ContactsSyncGatewayService mContactsSyncGatewayService;

    public ContactsDownSyncApi(SharedPreferences sharedPreferences, ContactsSyncGatewayService contactsSyncGatewayService) {
        super(sharedPreferences);
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.sync.api.AbstractContactApiAction
    public Observable<GetContactsV2Response> handleAction(GetContactsV2Request getContactsV2Request) {
        return handleDownSyncAction(getContactsV2Request).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetContactsV2Response> handleDownSyncAction(GetContactsV2Request getContactsV2Request) {
        return this.mContactsSyncGatewayService.getContactsV2(getContactsV2Request).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsDownSyncApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContactsDownSyncApi.lambda$handleDownSyncAction$0((GetContactsV2ServerResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsDownSyncApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactsDownSyncApi.lambda$handleDownSyncAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ GetContactsV2Response lambda$handleDownSyncAction$0(GetContactsV2ServerResponse getContactsV2ServerResponse) {
        return new GetContactsV2Response(ContactsConverterUtil.convertServerContactsToLocalContacts(getContactsV2ServerResponse.getContactList()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleDownSyncAction$1(Throwable th) {
        LOG.e("Failed to perform down sync. " + th);
    }
}
