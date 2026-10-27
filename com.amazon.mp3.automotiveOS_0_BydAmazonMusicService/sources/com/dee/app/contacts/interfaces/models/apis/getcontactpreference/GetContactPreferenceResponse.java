package com.dee.app.contacts.interfaces.models.apis.getcontactpreference;

import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreference;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactPreferenceResponse {
    private ContactPreference preference;
    private String targetId;

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public void setPreference(ContactPreference contactPreference) {
        this.preference = contactPreference;
    }
}
