package com.dee.app.contacts.common.network.gateway;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.common.constants.ApiNameConstant;
import com.dee.app.contacts.common.exceptions.AuthException;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.models.server.CanSyncContactsServerResponse;
import com.dee.app.contacts.common.models.server.GetContactsV2ServerResponse;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.GenericType;
import com.dee.app.contacts.common.utils.JsonUtils;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.UserAgent;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsResponse;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontactbycontactid.GetContactByContactIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2QueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdResponse;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceLevel;
import com.dee.app.contacts.interfaces.models.data.server.ServerContact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsSyncGatewayService extends AbstractServiceGateway {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", ContactsSyncGatewayService.class);
    private final NetworkUtils mNetworkUtils;

    public ContactsSyncGatewayService(NetworkUtils networkUtils, ObjectMapper objectMapper, INetworkingClient iNetworkingClient, ContactsMetricsRecorder contactsMetricsRecorder, UserAgent userAgent) {
        super(objectMapper, iNetworkingClient, contactsMetricsRecorder, userAgent);
        this.mNetworkUtils = networkUtils;
    }

    public Observable<GetContactsV2ServerResponse> getContactsV2(@NonNull final GetContactsV2Request getContactsV2Request) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getContactsV2$0(getContactsV2Request, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getContactsV2$0(GetContactsV2Request getContactsV2Request, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.getContactsV2URL(getContactsV2Request.getUserId()), ApiNameConstant.GET_CONTACTS_V2_API);
            addQueryParamForGetContactsV2(requestObject, getContactsV2Request.getQueryConfig());
            GetContactsV2ServerResponse getContactsV2ServerResponse = (GetContactsV2ServerResponse) get(requestObject, new GenericType(GetContactsV2ServerResponse.class));
            recordMetrics("CommsContacts.GetContactsV2.Success");
            subscriber.onNext(getContactsV2ServerResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetContactsV2 service call for contactId: %s", getContactsV2Request.getUserId(), e);
            recordMetrics("CommsContacts.GetContactsV2.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetContactsV2 service call for contactId: %s", getContactsV2Request.getUserId(), e);
            recordMetrics("CommsContacts.GetContactsV2.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Error while handling GetContactsV2 service call for contactId: %s", getContactsV2Request.getUserId(), e);
            recordMetrics("CommsContacts.GetContactsV2.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling GetContactsV2 api for contactId: %s", getContactsV2Request.getUserId(), e4);
            recordMetrics("CommsContacts.GetContactsV2.Failure");
            subscriber.onError(e4);
        }
    }

    public Observable<BatchUpdateLABContactsResponse> batchUpdateContacts(@NonNull final BatchUpdateLABContactsRequest batchUpdateLABContactsRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$batchUpdateContacts$1(batchUpdateLABContactsRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$batchUpdateContacts$1(BatchUpdateLABContactsRequest batchUpdateLABContactsRequest, Subscriber subscriber) {
        try {
            LOG.i("Sending batchUpdate request on thread: " + Thread.currentThread().getName());
            INetworkRequest iNetworkRequestPatchRequestObject = patchRequestObject(this.mNetworkUtils.batchUpdateContactsURL(batchUpdateLABContactsRequest.getUserId()), ApiNameConstant.SYNC_CONTACTS_API);
            addQueryParamForBatchUpdateContacts(iNetworkRequestPatchRequestObject, batchUpdateLABContactsRequest.getQueryConfig());
            BatchUpdateLABContactsResponse batchUpdateLABContactsResponse = (BatchUpdateLABContactsResponse) patch(iNetworkRequestPatchRequestObject, JsonUtils.toJson(batchUpdateLABContactsRequest.getBody()), new GenericType(BatchUpdateLABContactsResponse.class));
            recordMetrics("CommsContacts.BatchUpdateContacts.Success");
            subscriber.onNext(batchUpdateLABContactsResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling batchUpdateContacts service call for contactId: %s", batchUpdateLABContactsRequest.getUserId(), e);
            recordMetrics("CommsContacts.BatchUpdateContacts.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling batchUpdateContacts service call for contactId: %s", batchUpdateLABContactsRequest.getUserId(), e);
            recordMetrics("CommsContacts.BatchUpdateContacts.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Error while handling batchUpdateContacts service call for contactId: %s", batchUpdateLABContactsRequest.getUserId(), e);
            recordMetrics("CommsContacts.BatchUpdateContacts.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling batchUpdateContacts service call for contactId: %s", batchUpdateLABContactsRequest.getUserId(), e4);
            recordMetrics("CommsContacts.BatchUpdateContacts.Failure");
            subscriber.onError(e4);
        }
    }

    public Observable<CanSyncContactsServerResponse> canSync(@NonNull final CanSyncContactsRequest canSyncContactsRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$canSync$2(canSyncContactsRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$canSync$2(CanSyncContactsRequest canSyncContactsRequest, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.canSyncContactsURL(canSyncContactsRequest.getUserId()), ApiNameConstant.CAN_SYNC_CONTACTS_API);
            addQueryParamForCanSync(requestObject, canSyncContactsRequest.getQueryConfig());
            CanSyncContactsServerResponse canSyncContactsServerResponse = (CanSyncContactsServerResponse) get(requestObject, new GenericType(CanSyncContactsServerResponse.class));
            recordMetrics("CommsContacts.CanSync.Success");
            subscriber.onNext(canSyncContactsServerResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling CanSyncContacts api. ", e);
            recordMetrics("CommsContacts.CanSync.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling CanSyncContacts api. ", e);
            recordMetrics("CommsContacts.CanSync.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Error while handling CanSyncContacts api. ", e);
            recordMetrics("CommsContacts.CanSync.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling CanSyncContacts api. ", e4);
            recordMetrics("CommsContacts.CanSync.Failure");
            subscriber.onError(e4);
        }
    }

    public Observable<GetMasterDeviceIdResponse> getMasterDeviceId(@NonNull final GetMasterDeviceIdRequest getMasterDeviceIdRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getMasterDeviceId$3(getMasterDeviceIdRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getMasterDeviceId$3(GetMasterDeviceIdRequest getMasterDeviceIdRequest, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.getMasterDeviceIdURL(getMasterDeviceIdRequest.getUserId()), ApiNameConstant.GET_MASTER_DEVICE_ID);
            addQueryParamForGetMasterDeviceId(requestObject, getMasterDeviceIdRequest.getQueryConfig());
            GetMasterDeviceIdResponse getMasterDeviceIdResponse = (GetMasterDeviceIdResponse) get(requestObject, new GenericType(GetMasterDeviceIdResponse.class));
            recordMetrics("CommsContacts.GetMasterDeviceId.Success");
            subscriber.onNext(getMasterDeviceIdResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetMasterDeviceId service call. ", e);
            recordMetrics("CommsContacts.GetMasterDeviceId.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetMasterDeviceId service call. ", e);
            recordMetrics("CommsContacts.GetMasterDeviceId.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Error while handling GetMasterDeviceId service call. ", e);
            recordMetrics("CommsContacts.GetMasterDeviceId.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling GetMasterDeviceId api. ", e4);
            recordMetrics("CommsContacts.GetMasterDeviceId.Failure");
            subscriber.onError(e4);
        }
    }

    public Observable<ServerContact> getContactByContactId(@NonNull final GetContactByContactIdRequest getContactByContactIdRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getContactByContactId$4(getContactByContactIdRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getContactByContactId$4(GetContactByContactIdRequest getContactByContactIdRequest, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.getContactByContactIdURL(getContactByContactIdRequest.getUserId(), getContactByContactIdRequest.getTargetContactId()), ApiNameConstant.GET_CONTACT_BY_CONTACT_ID_API);
            addQueryParamForGetContactByContactId(requestObject);
            ServerContact serverContact = (ServerContact) get(requestObject, new GenericType(ServerContact.class));
            recordMetrics("CommsContacts.GetContactByContactId.Success");
            subscriber.onNext(serverContact);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetContactByContactId service call. ", e);
            recordMetrics("CommsContacts.GetContactByContactId.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetContactByContactId service call. ", e);
            recordMetrics("CommsContacts.GetContactByContactId.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Error while handling GetContactByContactId service call. ", e);
            recordMetrics("CommsContacts.GetContactByContactId.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling GetContactByContactId api. ", e4);
            recordMetrics("CommsContacts.GetContactByContactId.Failure");
            subscriber.onError(e4);
        }
    }

    private void addQueryParamForGetContactsV2(INetworkRequest iNetworkRequest, GetContactsV2QueryConfig getContactsV2QueryConfig) {
        if (getContactsV2QueryConfig == null) {
            getContactsV2QueryConfig = new GetContactsV2QueryConfig();
        }
        PreferenceLevel preferenceLevel = PreferenceLevel.NONE;
        String homeGroupId = this.mNetworkUtils.getHomeGroupId();
        if (!StringUtils.isBlank(homeGroupId)) {
            LOG.i("GetContacts will include HomeGroup preferences");
            preferenceLevel = PreferenceLevel.HOME_GROUP;
            iNetworkRequest.withParameter("homeGroupId", homeGroupId);
        }
        if (getContactsV2QueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", getContactsV2QueryConfig.getOwnerIdType().getValue());
        }
        iNetworkRequest.withParameter("includePreferencesByLevel", preferenceLevel.getPreferenceLevel());
        iNetworkRequest.withParameter("includeNonAlexaContacts", String.valueOf(getContactsV2QueryConfig.getIncludeNonAlexaEnabledContacts()));
        iNetworkRequest.withParameter("includeHomeGroupMembers", String.valueOf(getContactsV2QueryConfig.getIncludeHomeGroupMembers()));
        iNetworkRequest.withParameter("bulkImportOnly", String.valueOf(getContactsV2QueryConfig.getBulkImportOnly()));
        iNetworkRequest.withParameter("includeBlockStatus", String.valueOf(getContactsV2QueryConfig.getIncludeBlockStatus()));
        iNetworkRequest.withParameter("includeMergedContacts", String.valueOf(getContactsV2QueryConfig.getIncludeMergedContacts()));
        iNetworkRequest.withParameter("dedupeMode", String.valueOf(getContactsV2QueryConfig.getDedupeMode().getDedupeMode()));
    }

    private void addQueryParamForCanSync(INetworkRequest iNetworkRequest, CanSyncContactsQueryConfig canSyncContactsQueryConfig) {
        if (canSyncContactsQueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", canSyncContactsQueryConfig.getOwnerIdType().getValue());
        }
        iNetworkRequest.withParameter("sourceDeviceId", canSyncContactsQueryConfig.getSourceDeviceId());
    }

    private void addQueryParamForGetMasterDeviceId(INetworkRequest iNetworkRequest, GetMasterDeviceIdQueryConfig getMasterDeviceIdQueryConfig) {
        if (getMasterDeviceIdQueryConfig.getSyncType() != null) {
            iNetworkRequest.withParameter("contactSyncType", getMasterDeviceIdQueryConfig.getSyncType().getSyncTypeValue());
        }
        if (getMasterDeviceIdQueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", getMasterDeviceIdQueryConfig.getOwnerIdType().getValue());
        }
    }

    private void addQueryParamForBatchUpdateContacts(INetworkRequest iNetworkRequest, BatchUpdateLABContactsQueryConfig batchUpdateLABContactsQueryConfig) {
        if (batchUpdateLABContactsQueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", batchUpdateLABContactsQueryConfig.getOwnerIdType().getValue());
        }
    }

    private void addQueryParamForGetContactByContactId(INetworkRequest iNetworkRequest) {
        iNetworkRequest.withParameter("view", "full");
    }

    private void recordMetrics(String str) {
        recordMetrics(str, ContactsSyncGatewayService.class);
    }
}
