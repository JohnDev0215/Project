package com.dee.app.sync.api.sync;

import android.content.SharedPreferences;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdResponse;
import com.dee.app.sync.api.AbstractContactApiAction;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetMasterDeviceIdApi extends AbstractContactApiAction<GetMasterDeviceIdRequest, GetMasterDeviceIdResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetMasterDeviceIdApi.class);
    private final ContactsSyncGatewayService mContactsSyncGatewayService;

    public GetMasterDeviceIdApi(SharedPreferences sharedPreferences, ContactsSyncGatewayService contactsSyncGatewayService) {
        super(sharedPreferences);
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.sync.api.AbstractContactApiAction
    public Observable<GetMasterDeviceIdResponse> handleAction(GetMasterDeviceIdRequest getMasterDeviceIdRequest) {
        return handleGetMasterDeviceIdAction(getMasterDeviceIdRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetMasterDeviceIdResponse> handleGetMasterDeviceIdAction(GetMasterDeviceIdRequest getMasterDeviceIdRequest) {
        return this.mContactsSyncGatewayService.getMasterDeviceId(getMasterDeviceIdRequest).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.GetMasterDeviceIdApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetMasterDeviceIdApi.lambda$handleGetMasterDeviceIdAction$0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetMasterDeviceIdAction$0(Throwable th) {
        LOG.e("GetMasterDeviceId API failed " + th);
    }
}
