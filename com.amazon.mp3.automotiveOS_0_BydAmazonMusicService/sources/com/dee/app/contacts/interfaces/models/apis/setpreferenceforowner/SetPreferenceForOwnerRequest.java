package com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner;

import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreference;

/* JADX INFO: loaded from: classes5.dex */
public class SetPreferenceForOwnerRequest {
    private ContactOwnerPreference preference;
    private PreferenceNameSpace preferenceNameSpace;
    private SetPreferenceForOwnerQueryConfig queryConfig;
    private String target;
    private String userId;

    public SetPreferenceForOwnerRequest(ContactOwnerPreference contactOwnerPreference, String str, PreferenceNameSpace preferenceNameSpace) {
        this.preference = contactOwnerPreference;
        this.target = str;
        this.preferenceNameSpace = preferenceNameSpace;
    }

    public ContactOwnerPreference getPreference() {
        return this.preference;
    }

    public SetPreferenceForOwnerQueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public void setQueryConfig(SetPreferenceForOwnerQueryConfig setPreferenceForOwnerQueryConfig) {
        this.queryConfig = setPreferenceForOwnerQueryConfig;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public PreferenceNameSpace getPreferenceNameSpace() {
        return this.preferenceNameSpace;
    }

    public void setPreferenceNameSpace(PreferenceNameSpace preferenceNameSpace) {
        this.preferenceNameSpace = preferenceNameSpace;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
