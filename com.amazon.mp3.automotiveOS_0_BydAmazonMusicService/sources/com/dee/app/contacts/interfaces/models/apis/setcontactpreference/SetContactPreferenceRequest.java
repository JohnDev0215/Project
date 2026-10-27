package com.dee.app.contacts.interfaces.models.apis.setcontactpreference;

import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreference;

/* JADX INFO: loaded from: classes5.dex */
public class SetContactPreferenceRequest {
    private ContactPreference preference;
    private String targetId;
    private String userId;

    public String getTargetId() {
        return this.targetId;
    }

    public ContactPreference getPreference() {
        return this.preference;
    }

    public String getUserId() {
        return this.userId;
    }
}
