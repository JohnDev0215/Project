package com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner;

import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreferenceKey;

/* JADX INFO: loaded from: classes5.dex */
public class GetPreferenceForOwnerRequest {
    private ContactOwnerPreferenceKey preferenceKey;
    private GetPreferenceForOwnerQueryConfig queryConfig;
    private String userId;

    public GetPreferenceForOwnerRequest(ContactOwnerPreferenceKey contactOwnerPreferenceKey) {
        this.preferenceKey = contactOwnerPreferenceKey;
    }

    public ContactOwnerPreferenceKey getPreferenceKey() {
        return this.preferenceKey;
    }

    public GetPreferenceForOwnerQueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public void setQueryConfig(GetPreferenceForOwnerQueryConfig getPreferenceForOwnerQueryConfig) {
        this.queryConfig = getPreferenceForOwnerQueryConfig;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
