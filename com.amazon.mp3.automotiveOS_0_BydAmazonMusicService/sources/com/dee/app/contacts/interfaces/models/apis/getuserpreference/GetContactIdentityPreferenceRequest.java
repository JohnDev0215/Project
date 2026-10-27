package com.dee.app.contacts.interfaces.models.apis.getuserpreference;

import com.dee.app.contacts.interfaces.models.data.preference.identitypreference.IdentityPreferenceKey;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactIdentityPreferenceRequest {
    private IdentityPreferenceKey key;
    private String userId;

    public IdentityPreferenceKey getKey() {
        return this.key;
    }

    public String getUserId() {
        return this.userId;
    }
}
