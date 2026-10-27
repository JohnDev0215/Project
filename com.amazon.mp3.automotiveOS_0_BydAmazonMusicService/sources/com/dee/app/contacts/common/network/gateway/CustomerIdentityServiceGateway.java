package com.dee.app.contacts.common.network.gateway;

import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.common.constants.ApiNameConstant;
import com.dee.app.contacts.common.exceptions.AuthException;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.utils.Client;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.GenericType;
import com.dee.app.contacts.common.utils.JsonUtils;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.UserAgent;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLBody;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLResponse;
import com.dee.app.contacts.interfaces.models.data.enums.ClientConfig;
import com.dee.app.contacts.interfaces.models.data.enums.UiConfig;
import com.dee.app.contacts.interfaces.models.data.server.ServerAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerIdentityServiceGateway extends AbstractServiceGateway {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", CustomerIdentityServiceGateway.class);
    private final String mAppName;
    private final NetworkUtils mNetworkUtils;

    public CustomerIdentityServiceGateway(ObjectMapper objectMapper, String str, NetworkUtils networkUtils, INetworkingClient iNetworkingClient, ContactsMetricsRecorder contactsMetricsRecorder, UserAgent userAgent) {
        super(objectMapper, iNetworkingClient, contactsMetricsRecorder, userAgent);
        this.mNetworkUtils = networkUtils;
        this.mAppName = str;
    }

    public Observable<GetPhoneNumberVerificationURLResponse> getPhoneNumberVerificationURL(final GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getPhoneNumberVerificationURL$0(getPhoneNumberVerificationURLRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getPhoneNumberVerificationURL$0(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest, Subscriber subscriber) {
        try {
            GetPhoneNumberVerificationURLResponse getPhoneNumberVerificationURLResponse = (GetPhoneNumberVerificationURLResponse) post(postRequestObject(this.mNetworkUtils.getPhoneNumberVerificationURL(), ApiNameConstant.GET_PNV_URL_API), JsonUtils.toJson(getPhoneNumberVerificationURLBody(getPhoneNumberVerificationURLRequest)), new GenericType(GetPhoneNumberVerificationURLResponse.class));
            recordMetrics("CommsContacts.GetPhoneNumberVerificationURL.Success");
            subscriber.onNext(getPhoneNumberVerificationURLResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Exception while calling GetPhoneNumberVerificationURL api. ", e);
            recordMetrics("CommsContacts.GetPhoneNumberVerificationURL.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Exception while calling GetPhoneNumberVerificationURL api. ", e);
            recordMetrics("CommsContacts.GetPhoneNumberVerificationURL.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Exception while calling GetPhoneNumberVerificationURL api. ", e);
            recordMetrics("CommsContacts.GetPhoneNumberVerificationURL.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling GetPhoneNumberVerificationURL api. ", e4);
            recordMetrics("CommsContacts.GetPhoneNumberVerificationURL.Failure");
            subscriber.onError(e4);
        }
    }

    public Observable<ServerAccount[]> getAccounts(final GetAccountsRequest getAccountsRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getAccounts$1(getAccountsRequest, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getAccounts$1(GetAccountsRequest getAccountsRequest, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.getAccountsURL(), ApiNameConstant.GET_ACCOUNTS_API);
            addQueryParamForGetAccounts(requestObject, getAccountsRequest.getQueryConfig());
            ServerAccount[] serverAccountArr = (ServerAccount[]) get(requestObject, new GenericType(ServerAccount[].class));
            recordMetrics("CommsContacts.GetAccounts.Success");
            subscriber.onNext(serverAccountArr);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Exception while calling GetAccounts api. ", e);
            recordMetrics("CommsContacts.GetAccounts.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Exception while calling GetAccounts api. ", e);
            recordMetrics("CommsContacts.GetAccounts.Error");
            subscriber.onError(e);
        } catch (ClientNotSupportedException e3) {
            e = e3;
            LOG.e("Exception while calling GetAccounts api. ", e);
            recordMetrics("CommsContacts.GetAccounts.Error");
            subscriber.onError(e);
        } catch (Exception e4) {
            LOG.e("Unknown exception while handling GetAccounts api. ", e4);
            recordMetrics("CommsContacts.GetAccounts.Failure");
            subscriber.onError(e4);
        }
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.common.network.gateway.CustomerIdentityServiceGateway$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$common$utils$Client;

        static {
            int[] iArr = new int[Client.values().length];
            $SwitchMap$com$dee$app$contacts$common$utils$Client = iArr;
            try {
                iArr[Client.MusicApp.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$common$utils$Client[Client.PhotosApp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private GetPhoneNumberVerificationURLBody getPhoneNumberVerificationURLBody(GetPhoneNumberVerificationURLRequest getPhoneNumberVerificationURLRequest) throws ClientNotSupportedException {
        GetPhoneNumberVerificationURLBody body = getPhoneNumberVerificationURLRequest.getBody();
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$common$utils$Client[Client.get(this.mAppName).ordinal()];
        if (i == 1) {
            body.setClientConfig(ClientConfig.Music);
            body.setUiConfig(UiConfig.Music);
            return body;
        }
        if (i == 2) {
            body.setClientConfig(ClientConfig.Photos);
            body.setUiConfig(UiConfig.Photos);
        }
        throw new ClientNotSupportedException(this.mAppName);
    }

    private void addQueryParamForGetAccounts(INetworkRequest iNetworkRequest, GetAccountsQueryConfig getAccountsQueryConfig) {
        iNetworkRequest.withParameter("includeDefaultActor", String.valueOf(getAccountsQueryConfig.getIncludeDefaultActor()));
    }

    private void recordMetrics(String str) {
        recordMetrics(str, CustomerIdentityServiceGateway.class);
    }
}
