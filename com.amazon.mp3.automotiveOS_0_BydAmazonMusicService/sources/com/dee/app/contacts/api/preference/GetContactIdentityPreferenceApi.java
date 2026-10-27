package com.dee.app.contacts.api.preference;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.models.server.PreferenceResponse;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getuserpreference.GetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.getuserpreference.GetContactIdentityPreferenceResponse;
import com.dee.app.contacts.interfaces.models.data.preference.identitypreference.ContactIdentityPreference;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactIdentityPreferenceApi extends AbstractContactApiAction<GetContactIdentityPreferenceRequest, GetContactIdentityPreferenceResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GetContactIdentityPreferenceApi.class);
    private final PreferenceServiceGateway mPreferenceServiceGateway;

    public GetContactIdentityPreferenceApi(SharedPreferences sharedPreferences, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<GetContactIdentityPreferenceResponse> handleAction(GetContactIdentityPreferenceRequest getContactIdentityPreferenceRequest) {
        return handleGetIdentityPreferenceAction(getContactIdentityPreferenceRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<GetContactIdentityPreferenceResponse> handleGetIdentityPreferenceAction(GetContactIdentityPreferenceRequest getContactIdentityPreferenceRequest) {
        return this.mPreferenceServiceGateway.getIdentityPreference(getContactIdentityPreferenceRequest).map(new Func1() { // from class: com.dee.app.contacts.api.preference.GetContactIdentityPreferenceApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleGetIdentityPreferenceAction$0((PreferenceResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.GetContactIdentityPreferenceApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GetContactIdentityPreferenceApi.lambda$handleGetIdentityPreferenceAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ GetContactIdentityPreferenceResponse lambda$handleGetIdentityPreferenceAction$0(PreferenceResponse preferenceResponse) {
        GetContactIdentityPreferenceResponse getContactIdentityPreferenceResponse = new GetContactIdentityPreferenceResponse();
        getContactIdentityPreferenceResponse.setPreference(buildIdentityPreferenceResponse(preferenceResponse));
        return getContactIdentityPreferenceResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleGetIdentityPreferenceAction$1(Throwable th) {
        LOG.e("Failed to get contact identity preference. ", th);
    }

    private ContactIdentityPreference buildIdentityPreferenceResponse(PreferenceResponse preferenceResponse) {
        return ContactIdentityPreference.getIdentityPreferenceForKeyAndValue(preferenceResponse.getKey(), preferenceResponse.getValue());
    }
}
