package com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference;

import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;

/* JADX INFO: loaded from: classes5.dex */
public class Preference {
    private ContactOwnerPreference preference;
    private PreferenceNameSpace preferenceNameSpace;
    private String target;

    public void setPreferenceNameSpace(PreferenceNameSpace preferenceNameSpace) {
        this.preferenceNameSpace = preferenceNameSpace;
    }

    public void setPreference(ContactOwnerPreference contactOwnerPreference) {
        this.preference = contactOwnerPreference;
    }

    public void setTarget(String str) {
        this.target = str;
    }
}
