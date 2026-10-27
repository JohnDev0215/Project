package com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner;

import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;

/* JADX INFO: loaded from: classes5.dex */
public class GetPreferenceForOwnerQueryConfig {
    private OwnerIdType ownerIdType;
    private PreferenceNameSpace preferenceNameSpace;
    private String target;

    public OwnerIdType getOwnerIdType() {
        return this.ownerIdType;
    }

    public void setOwnerIdType(OwnerIdType ownerIdType) {
        this.ownerIdType = ownerIdType;
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
}
