package com.dee.app.contacts.common.network.gateway;

import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.common.constants.ApiNameConstant;
import com.dee.app.contacts.common.exceptions.AuthException;
import com.dee.app.contacts.common.models.server.PreferenceForOwnerResponse;
import com.dee.app.contacts.common.models.server.PreferenceResponse;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.GenericType;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.UserAgent;
import com.dee.app.contacts.interfaces.models.apis.getcontactpreference.GetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.getuserpreference.GetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setcontactpreference.SetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.setuserpreference.SetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceServiceGateway extends AbstractServiceGateway {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", PreferenceServiceGateway.class);
    private final NetworkUtils mNetworkUtils;

    public PreferenceServiceGateway(ObjectMapper objectMapper, NetworkUtils networkUtils, INetworkingClient iNetworkingClient, ContactsMetricsRecorder contactsMetricsRecorder, UserAgent userAgent) {
        super(objectMapper, iNetworkingClient, contactsMetricsRecorder, userAgent);
        this.mNetworkUtils = networkUtils;
    }

    public Observable<String> setContactPreference(final SetContactPreferenceRequest setContactPreferenceRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setContactPreference$0(setContactPreferenceRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setContactPreference$0(SetContactPreferenceRequest setContactPreferenceRequest, Subscriber subscriber) {
        try {
            String targetId = setContactPreferenceRequest.getTargetId();
            String str = (String) patch(patchRequestObject(this.mNetworkUtils.getSetContactPreferenceURL(setContactPreferenceRequest.getUserId(), targetId), ApiNameConstant.SET_PREFERENCE_API), buildMapForSetContactPreference(setContactPreferenceRequest.getPreference()), new GenericType(String.class));
            recordMetrics("CommsContacts.SetContactPreference.Success");
            subscriber.onNext(str);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling SetContactPreference service call. ", e);
            recordMetrics("CommsContacts.SetContactPreference.Error");
            subscriber.onError(e);
        } catch (IOException e2) {
            e = e2;
            LOG.e("Error while handling SetContactPreference service call. ", e);
            recordMetrics("CommsContacts.SetContactPreference.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling SetContactPreference service call. ", e3);
            recordMetrics("CommsContacts.SetContactPreference.Failure");
            subscriber.onError(e3);
        }
    }

    public Observable<PreferenceResponse> getContactPreference(final GetContactPreferenceRequest getContactPreferenceRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getContactPreference$1(getContactPreferenceRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getContactPreference$1(GetContactPreferenceRequest getContactPreferenceRequest, Subscriber subscriber) {
        try {
            String targetId = getContactPreferenceRequest.getTargetId();
            PreferenceResponse preferenceResponse = (PreferenceResponse) get(getRequestObject(this.mNetworkUtils.getContactPreferenceURL(getContactPreferenceRequest.getUserId(), targetId, getContactPreferenceRequest.getPreferenceKey().getPreferenceKeyAsString()), ApiNameConstant.GET_PREFERENCE_API), new GenericType(PreferenceResponse.class));
            recordMetrics("CommsContacts.GetContactPreference.Success");
            subscriber.onNext(preferenceResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetContactPreference service call. ", e);
            recordMetrics("CommsContacts.GetContactPreference.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetContactPreference service call. ", e);
            recordMetrics("CommsContacts.GetContactPreference.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling GetContactPreference service call. ", e3);
            recordMetrics("CommsContacts.GetContactPreference.Failure");
            subscriber.onError(e3);
        }
    }

    public Observable<String> setIdentityPreference(final SetContactIdentityPreferenceRequest setContactIdentityPreferenceRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setIdentityPreference$2(setContactIdentityPreferenceRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setIdentityPreference$2(SetContactIdentityPreferenceRequest setContactIdentityPreferenceRequest, Subscriber subscriber) {
        try {
            String userId = setContactIdentityPreferenceRequest.getUserId();
            String preferenceKeyAsString = setContactIdentityPreferenceRequest.getPreference().getPreferenceKey().getPreferenceKeyAsString();
            String str = (String) patch(patchRequestObject(this.mNetworkUtils.getSetIdentityPreferenceURL(userId, preferenceKeyAsString), ApiNameConstant.SET_IDENTITY_PREFERENCE_API), setContactIdentityPreferenceRequest.getPreference().getPreferenceValue().getPreferenceValueAsString(), new GenericType(String.class));
            recordMetrics("CommsContacts.SetContactIdentityPreference.Success");
            subscriber.onNext(str);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling SetIdentityPreference service call. ", e);
            recordMetrics("CommsContacts.SetContactIdentityPreference.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling SetIdentityPreference service call. ", e);
            recordMetrics("CommsContacts.SetContactIdentityPreference.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling SetIdentityPreference service call. ", e3);
            recordMetrics("CommsContacts.SetContactIdentityPreference.Failure");
            subscriber.onError(e3);
        }
    }

    public Observable<PreferenceResponse> getIdentityPreference(final GetContactIdentityPreferenceRequest getContactIdentityPreferenceRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getIdentityPreference$3(getContactIdentityPreferenceRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getIdentityPreference$3(GetContactIdentityPreferenceRequest getContactIdentityPreferenceRequest, Subscriber subscriber) {
        try {
            PreferenceResponse preferenceResponse = (PreferenceResponse) get(getRequestObject(this.mNetworkUtils.getIdentityPreferenceURL(getContactIdentityPreferenceRequest.getUserId(), getContactIdentityPreferenceRequest.getKey().getPreferenceKeyAsString()), ApiNameConstant.GET_IDENTITY_PREFERENCE_API), new GenericType(PreferenceResponse.class));
            recordMetrics("CommsContacts.GetContactIdentityPreference.Success");
            subscriber.onNext(preferenceResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetIdentityPreference service call. ", e);
            recordMetrics("CommsContacts.GetContactIdentityPreference.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetIdentityPreference service call. ", e);
            recordMetrics("CommsContacts.GetContactIdentityPreference.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling GetIdentityPreference service call. ", e3);
            recordMetrics("CommsContacts.GetContactIdentityPreference.Failure");
            subscriber.onError(e3);
        }
    }

    public Observable<String> setPreferenceForOwner(final SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setPreferenceForOwner$4(setPreferenceForOwnerRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPreferenceForOwner$4(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest, Subscriber subscriber) {
        try {
            LOG.i("[tanu] setPreferenceForOwner");
            INetworkRequest iNetworkRequestPatchRequestObject = patchRequestObject(this.mNetworkUtils.setPreferenceForOwnerURL(setPreferenceForOwnerRequest.getUserId()), ApiNameConstant.SET_OWNER_PREFERENCE_API);
            addQueryParamForSetPreferenceForOwner(iNetworkRequestPatchRequestObject, setPreferenceForOwnerRequest.getQueryConfig());
            String str = (String) patch(iNetworkRequestPatchRequestObject, buildMapForSetPreferenceForOwner(setPreferenceForOwnerRequest), new GenericType(String.class));
            recordMetrics("CommsContacts.SetPreferenceForOwner.Success");
            subscriber.onNext(str);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling SetPreferenceForOwner service call. ", e);
            recordMetrics("CommsContacts.SetPreferenceForOwner.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling SetPreferenceForOwner service call. ", e);
            recordMetrics("CommsContacts.SetPreferenceForOwner.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling SetPreferenceForOwner service call. ", e3);
            recordMetrics("CommsContacts.SetPreferenceForOwner.Failure");
            subscriber.onError(e3);
        }
    }

    public Observable<PreferenceForOwnerResponse> getPreferenceForOwner(final GetPreferenceForOwnerRequest getPreferenceForOwnerRequest) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getPreferenceForOwner$5(getPreferenceForOwnerRequest, (Subscriber) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getPreferenceForOwner$5(GetPreferenceForOwnerRequest getPreferenceForOwnerRequest, Subscriber subscriber) {
        try {
            INetworkRequest requestObject = getRequestObject(this.mNetworkUtils.getPreferenceForOwnerURL(getPreferenceForOwnerRequest.getUserId(), getPreferenceForOwnerRequest.getPreferenceKey().getPreferenceKeyAsString()), ApiNameConstant.GET_OWNER_PREFERENCE_API);
            addQueryParamForGetPreferenceForOwner(requestObject, getPreferenceForOwnerRequest.getQueryConfig());
            PreferenceForOwnerResponse preferenceForOwnerResponse = (PreferenceForOwnerResponse) get(requestObject, new GenericType(PreferenceForOwnerResponse.class));
            recordMetrics("CommsContacts.GetPreferenceForOwner.Success");
            subscriber.onNext(preferenceForOwnerResponse);
        } catch (NetworkException e) {
            e = e;
            LOG.e("Error while handling GetPreferenceForOwner service call. ", e);
            recordMetrics("CommsContacts.GetPreferenceForOwner.Error");
            subscriber.onError(e);
        } catch (AuthException e2) {
            e = e2;
            LOG.e("Error while handling GetPreferenceForOwner service call. ", e);
            recordMetrics("CommsContacts.GetPreferenceForOwner.Error");
            subscriber.onError(e);
        } catch (Exception e3) {
            LOG.e("Unknown exception while handling GetPreferenceForOwner service call. ", e3);
            recordMetrics("CommsContacts.GetPreferenceForOwner.Failure");
            subscriber.onError(e3);
        }
    }

    private Map<String, String> buildMapForSetContactPreference(ContactPreference contactPreference) {
        String preferenceKeyAsString = contactPreference.getPreferenceKey().getPreferenceKeyAsString();
        String preferenceValueAsString = contactPreference.getPreferenceValue().getPreferenceValueAsString();
        HashMap map = new HashMap();
        map.put(preferenceKeyAsString, preferenceValueAsString);
        return map;
    }

    private Map<String, String> buildMapForSetPreferenceForOwner(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        String preferenceKeyAsString = setPreferenceForOwnerRequest.getPreference().getPreferenceKey().getPreferenceKeyAsString();
        String preferenceValueAsString = setPreferenceForOwnerRequest.getPreference().getPreferenceValue().getPreferenceValueAsString();
        String target = setPreferenceForOwnerRequest.getTarget();
        String value = setPreferenceForOwnerRequest.getPreferenceNameSpace().getValue();
        HashMap map = new HashMap();
        map.put("preferenceKey", preferenceKeyAsString);
        map.put("preferenceValue", preferenceValueAsString);
        map.put("preferenceNamespace", value);
        map.put("target", target);
        return map;
    }

    private void addQueryParamForGetPreferenceForOwner(INetworkRequest iNetworkRequest, GetPreferenceForOwnerQueryConfig getPreferenceForOwnerQueryConfig) {
        if (getPreferenceForOwnerQueryConfig.getPreferenceNameSpace() != null) {
            iNetworkRequest.withParameter("preferenceNamespace", getPreferenceForOwnerQueryConfig.getPreferenceNameSpace().getValue());
        }
        if (getPreferenceForOwnerQueryConfig.getTarget() != null) {
            iNetworkRequest.withParameter("target", getPreferenceForOwnerQueryConfig.getTarget());
        }
        if (getPreferenceForOwnerQueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", getPreferenceForOwnerQueryConfig.getOwnerIdType().getValue());
        }
    }

    private void addQueryParamForSetPreferenceForOwner(INetworkRequest iNetworkRequest, SetPreferenceForOwnerQueryConfig setPreferenceForOwnerQueryConfig) {
        if (setPreferenceForOwnerQueryConfig.getOwnerIdType() != null) {
            iNetworkRequest.withParameter("ownerIdType", setPreferenceForOwnerQueryConfig.getOwnerIdType().getValue());
        }
    }

    private void recordMetrics(String str) {
        recordMetrics(str, PreferenceServiceGateway.class);
    }
}
