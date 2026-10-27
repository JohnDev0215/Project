package com.dee.app.contacts.interfaces.models.apis.setuserpreference;

import com.dee.app.contacts.interfaces.models.data.preference.identitypreference.ContactIdentityPreference;

/* JADX INFO: loaded from: classes5.dex */
public class SetContactIdentityPreferenceRequest {
    private ContactIdentityPreference preference;
    private String userId;

    public ContactIdentityPreference getPreference() {
        return this.preference;
    }

    public String getUserId() {
        return this.userId;
    }
}
