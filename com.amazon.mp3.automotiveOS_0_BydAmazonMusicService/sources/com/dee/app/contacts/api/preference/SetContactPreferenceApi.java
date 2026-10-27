package com.dee.app.contacts.api.preference;

import android.content.SharedPreferences;
import com.dee.app.contacts.api.AbstractContactApiAction;
import com.dee.app.contacts.common.network.gateway.PreferenceServiceGateway;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.setcontactpreference.SetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setcontactpreference.SetContactPreferenceResponse;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class SetContactPreferenceApi extends AbstractContactApiAction<SetContactPreferenceRequest, SetContactPreferenceResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", SetContactPreferenceApi.class);
    private final PreferenceServiceGateway mPreferenceServiceGateway;

    public SetContactPreferenceApi(SharedPreferences sharedPreferences, PreferenceServiceGateway preferenceServiceGateway) {
        super(sharedPreferences);
        this.mPreferenceServiceGateway = preferenceServiceGateway;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.contacts.api.AbstractContactApiAction
    public Observable<SetContactPreferenceResponse> handleAction(SetContactPreferenceRequest setContactPreferenceRequest) {
        return handleSetContactAction(setContactPreferenceRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<SetContactPreferenceResponse> handleSetContactAction(SetContactPreferenceRequest setContactPreferenceRequest) {
        return this.mPreferenceServiceGateway.setContactPreference(setContactPreferenceRequest).map(new Func1() { // from class: com.dee.app.contacts.api.preference.SetContactPreferenceApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return SetContactPreferenceApi.lambda$handleSetContactAction$0((String) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.contacts.api.preference.SetContactPreferenceApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SetContactPreferenceApi.lambda$handleSetContactAction$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SetContactPreferenceResponse lambda$handleSetContactAction$0(String str) {
        SetContactPreferenceResponse setContactPreferenceResponse = new SetContactPreferenceResponse();
        setContactPreferenceResponse.setSuccess(true);
        return setContactPreferenceResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSetContactAction$1(Throwable th) {
        LOG.e("Failed to set contact preference. ", th);
    }
}
