package com.dee.app.contacts.interfaces.models.apis.getcontactpreference;

import com.dee.app.contacts.interfaces.models.data.preference.contactpreference.ContactPreferenceKey;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactPreferenceRequest {
    private ContactPreferenceKey preferenceKey;
    private String targetId;
    private String userId;

    public String getTargetId() {
        return this.targetId;
    }

    public ContactPreferenceKey getPreferenceKey() {
        return this.preferenceKey;
    }

    public String getUserId() {
        return this.userId;
    }
}
