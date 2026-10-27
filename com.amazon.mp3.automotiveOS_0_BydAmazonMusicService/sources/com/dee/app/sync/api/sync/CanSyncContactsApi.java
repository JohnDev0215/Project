package com.dee.app.sync.api.sync;

import android.content.SharedPreferences;
import com.dee.app.contacts.common.models.server.CanSyncContactsServerResponse;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsResponse;
import com.dee.app.contacts.interfaces.models.data.enums.CanSyncContactsStatus;
import com.dee.app.sync.api.AbstractContactApiAction;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class CanSyncContactsApi extends AbstractContactApiAction<CanSyncContactsRequest, CanSyncContactsResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", CanSyncContactsApi.class);
    private final ContactsSyncGatewayService mContactsSyncGatewayService;
    private final String mDeviceId;

    public CanSyncContactsApi(SharedPreferences sharedPreferences, String str, ContactsSyncGatewayService contactsSyncGatewayService) {
        super(sharedPreferences);
        this.mDeviceId = str;
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.sync.api.AbstractContactApiAction
    public Observable<CanSyncContactsResponse> handleAction(CanSyncContactsRequest canSyncContactsRequest) {
        return handleCanSyncContactsAction(canSyncContactsRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<CanSyncContactsResponse> handleCanSyncContactsAction(CanSyncContactsRequest canSyncContactsRequest) {
        if (canSyncContactsRequest.getQueryConfig() != null && StringUtils.isBlank(canSyncContactsRequest.getQueryConfig().getSourceDeviceId())) {
            CanSyncContactsQueryConfig queryConfig = canSyncContactsRequest.getQueryConfig();
            queryConfig.setSourceDeviceId(this.mDeviceId);
            canSyncContactsRequest.setQueryConfig(queryConfig);
        }
        if (canSyncContactsRequest.getQueryConfig() == null) {
            canSyncContactsRequest.setQueryConfig(new CanSyncContactsQueryConfig(this.mDeviceId));
        }
        return this.mContactsSyncGatewayService.canSync(canSyncContactsRequest).map(new Func1() { // from class: com.dee.app.sync.api.sync.CanSyncContactsApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return CanSyncContactsApi.lambda$handleCanSyncContactsAction$0((CanSyncContactsServerResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.CanSyncContactsApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CanSyncContactsApi.lambda$handleCanSyncContactsAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CanSyncContactsResponse lambda$handleCanSyncContactsAction$0(CanSyncContactsServerResponse canSyncContactsServerResponse) {
        String canSyncStatus = canSyncContactsServerResponse.getCanSyncStatus();
        CanSyncContactsResponse canSyncContactsResponse = new CanSyncContactsResponse();
        canSyncContactsResponse.setCanSyncStatus(CanSyncContactsStatus.getStatusFromString(canSyncStatus));
        return canSyncContactsResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleCanSyncContactsAction$1(Throwable th) {
        LOG.e("CanSync contacts API failed " + th);
    }
}
