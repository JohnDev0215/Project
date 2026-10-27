package com.dee.app.contacts.api;

import com.dee.app.contacts.api.preference.GetContactIdentityPreferenceApi;
import com.dee.app.contacts.api.preference.GetContactPreferenceApi;
import com.dee.app.contacts.api.preference.GetPreferenceForOwnerApi;
import com.dee.app.contacts.api.preference.SetContactIdentityPreferenceApi;
import com.dee.app.contacts.api.preference.SetContactPreferenceApi;
import com.dee.app.contacts.api.preference.SetPreferenceForOwnerApi;
import com.dee.app.contacts.interfaces.models.apis.getcontactpreference.GetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.getcontactpreference.GetContactPreferenceResponse;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.apis.getuserpreference.GetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.getuserpreference.GetContactIdentityPreferenceResponse;
import com.dee.app.contacts.interfaces.models.apis.setcontactpreference.SetContactPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setcontactpreference.SetContactPreferenceResponse;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.apis.setuserpreference.SetContactIdentityPreferenceRequest;
import com.dee.app.contacts.interfaces.models.apis.setuserpreference.SetContactIdentityPreferenceResponse;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceApiHandler {
    private final AbstractContactApiAction<GetContactIdentityPreferenceRequest, GetContactIdentityPreferenceResponse> mGetContactIdentityPreferenceApi;
    private final AbstractContactApiAction<GetContactPreferenceRequest, GetContactPreferenceResponse> mGetContactPreferenceApi;
    private final AbstractContactApiAction<GetPreferenceForOwnerRequest, GetPreferenceForOwnerResponse> mGetPreferenceForOwnerApi;
    private final AbstractContactApiAction<SetContactIdentityPreferenceRequest, SetContactIdentityPreferenceResponse> mSetContactIdentityPreferenceApi;
    private final AbstractContactApiAction<SetContactPreferenceRequest, SetContactPreferenceResponse> mSetContactPreference;
    private final AbstractContactApiAction<SetPreferenceForOwnerRequest, SetPreferenceForOwnerResponse> mSetPreferenceForOwnerApi;

    public PreferenceApiHandler(GetContactPreferenceApi getContactPreferenceApi, SetContactPreferenceApi setContactPreferenceApi, GetContactIdentityPreferenceApi getContactIdentityPreferenceApi, SetContactIdentityPreferenceApi setContactIdentityPreferenceApi, GetPreferenceForOwnerApi getPreferenceForOwnerApi, SetPreferenceForOwnerApi setPreferenceForOwnerApi) {
        this.mSetContactPreference = setContactPreferenceApi;
        this.mGetContactPreferenceApi = getContactPreferenceApi;
        this.mGetContactIdentityPreferenceApi = getContactIdentityPreferenceApi;
        this.mSetContactIdentityPreferenceApi = setContactIdentityPreferenceApi;
        this.mGetPreferenceForOwnerApi = getPreferenceForOwnerApi;
        this.mSetPreferenceForOwnerApi = setPreferenceForOwnerApi;
    }

    public Observable<GetPreferenceForOwnerResponse> getPreferenceForOwner(GetPreferenceForOwnerRequest getPreferenceForOwnerRequest) {
        return this.mGetPreferenceForOwnerApi.performAction(getPreferenceForOwnerRequest);
    }

    public Observable<SetPreferenceForOwnerResponse> setPreferenceForOwner(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        return this.mSetPreferenceForOwnerApi.performAction(setPreferenceForOwnerRequest);
    }
}
