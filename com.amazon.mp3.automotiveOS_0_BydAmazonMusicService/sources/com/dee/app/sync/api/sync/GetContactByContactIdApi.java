package com.dee.app.sync.api.sync;

import android.content.SharedPreferences;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsConverterUtil;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getcontactbycontactid.GetContactByContactIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontactbycontactid.GetContactByContactIdResponse;
import com.dee.app.contacts.interfaces.models.data.server.ServerContact;
import com.dee.app.sync.api.AbstractContactApiAction;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactByContactIdApi extends AbstractContactApiAction<GetContactByContactIdRequest, GetContactByContactIdResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetMasterDeviceIdApi.class);
    private final ContactsSyncGatewayService mContactsSyncGatewayService;

    public GetContactByContactIdApi(SharedPreferences sharedPreferences, ContactsSyncGatewayService contactsSyncGatewayService) {
        super(sharedPreferences);
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.sync.api.AbstractContactApiAction
    public Observable<GetContactByContactIdResponse> handleAction(GetContactByContactIdRequest getContactByContactIdRequest) {
        return handleGetContactByContactIdAction(getContactByContactIdRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetContactByContactIdResponse> handleGetContactByContactIdAction(GetContactByContactIdRequest getContactByContactIdRequest) {
        return this.mContactsSyncGatewayService.getContactByContactId(getContactByContactIdRequest).map(new Func1() { // from class: com.dee.app.sync.api.sync.GetContactByContactIdApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return GetContactByContactIdApi.lambda$handleGetContactByContactIdAction$0((ServerContact) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.GetContactByContactIdApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetContactByContactIdApi.lambda$handleGetContactByContactIdAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ GetContactByContactIdResponse lambda$handleGetContactByContactIdAction$0(ServerContact serverContact) {
        GetContactByContactIdResponse getContactByContactIdResponse = new GetContactByContactIdResponse();
        getContactByContactIdResponse.setContact(ContactsConverterUtil.convertServerContactToLocalContact(serverContact));
        return getContactByContactIdResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetContactByContactIdAction$1(Throwable th) {
        LOG.e("GetContactByContactId API failed " + th);
    }
}
