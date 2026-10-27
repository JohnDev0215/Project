package com.dee.app.contacts.api.preference;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.models.server.PreferenceResponse;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getcontactpreference.GetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontactpreference.GetContactPreferenceResponse;
import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreference;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactPreferenceApi extends AbstractContactApiAction<GetContactPreferenceRequest, GetContactPreferenceResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetContactPreferenceApi.class);
    private final PreferenceServiceGateway mPreferenceServiceGateway;

    public GetContactPreferenceApi(SharedPreferences sharedPreferences, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<GetContactPreferenceResponse> handleAction(GetContactPreferenceRequest getContactPreferenceRequest) {
        return handleGetContactPreferenceAction(getContactPreferenceRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetContactPreferenceResponse> handleGetContactPreferenceAction(final GetContactPreferenceRequest getContactPreferenceRequest) {
        return this.mPreferenceServiceGateway.getContactPreference(getContactPreferenceRequest).map(new Func1() { // from class: com.dee.app.contacts.api.preference.GetContactPreferenceApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleGetContactPreferenceAction$0(getContactPreferenceRequest, (PreferenceResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.GetContactPreferenceApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetContactPreferenceApi.lambda$handleGetContactPreferenceAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ GetContactPreferenceResponse lambda$handleGetContactPreferenceAction$0(GetContactPreferenceRequest getContactPreferenceRequest, PreferenceResponse preferenceResponse) {
        GetContactPreferenceResponse getContactPreferenceResponse = new GetContactPreferenceResponse();
        getContactPreferenceResponse.setPreference(buildContactPreferenceResponse(preferenceResponse));
        getContactPreferenceResponse.setTargetId(getContactPreferenceRequest.getTargetId());
        return getContactPreferenceResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetContactPreferenceAction$1(Throwable th) {
        LOG.e("Failed to get contact preference. ", th);
    }

    private ContactPreference buildContactPreferenceResponse(PreferenceResponse preferenceResponse) {
        return ContactPreference.getContactPreferenceForKeyAndValue(preferenceResponse.getKey(), preferenceResponse.getValue());
    }
}
