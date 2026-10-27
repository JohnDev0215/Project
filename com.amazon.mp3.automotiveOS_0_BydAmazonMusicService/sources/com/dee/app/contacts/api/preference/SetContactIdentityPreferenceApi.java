package com.dee.app.contacts.api.preference;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.setuserpreference.SetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setuserpreference.SetContactIdentityPreferenceResponse;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class SetContactIdentityPreferenceApi extends AbstractContactApiAction<SetContactIdentityPreferenceRequest, SetContactIdentityPreferenceResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", SetContactIdentityPreferenceApi.class);
    private final PreferenceServiceGateway mPreferenceServiceGateway;

    public SetContactIdentityPreferenceApi(SharedPreferences sharedPreferences, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<SetContactIdentityPreferenceResponse> handleAction(SetContactIdentityPreferenceRequest setContactIdentityPreferenceRequest) {
        return handleSetContactAction(setContactIdentityPreferenceRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<SetContactIdentityPreferenceResponse> handleSetContactAction(SetContactIdentityPreferenceRequest setContactIdentityPreferenceRequest) {
        return this.mPreferenceServiceGateway.setIdentityPreference(setContactIdentityPreferenceRequest).map(new Func1() { // from class: com.dee.app.contacts.api.preference.SetContactIdentityPreferenceApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return SetContactIdentityPreferenceApi.lambda$handleSetContactAction$0((String) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.SetContactIdentityPreferenceApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SetContactIdentityPreferenceApi.lambda$handleSetContactAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SetContactIdentityPreferenceResponse lambda$handleSetContactAction$0(String str) {
        SetContactIdentityPreferenceResponse setContactIdentityPreferenceResponse = new SetContactIdentityPreferenceResponse();
        setContactIdentityPreferenceResponse.setSuccess(true);
        return setContactIdentityPreferenceResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSetContactAction$1(Throwable th) {
        LOG.e("Failed to set contact identity preference. ", th);
    }
}
