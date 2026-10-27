package com.dee.app.contacts.core;

import com.dee.app.contacts.api.PreferenceApiHandler;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerResponse;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsPreferenceManager {
    PreferenceApiHandler mPreferenceApiHandler;

    public Observable<GetPreferenceForOwnerResponse> getPreferenceForOwner(GetPreferenceForOwnerRequest getPreferenceForOwnerRequest) {
        return this.mPreferenceApiHandler.getPreferenceForOwner(getPreferenceForOwnerRequest);
    }

    public Observable<SetPreferenceForOwnerResponse> setPreferenceForOwner(SetPreferenceForOwnerRequest setPreferenceForOwnerRequest) {
        return this.mPreferenceApiHandler.setPreferenceForOwner(setPreferenceForOwnerRequest);
    }
}
