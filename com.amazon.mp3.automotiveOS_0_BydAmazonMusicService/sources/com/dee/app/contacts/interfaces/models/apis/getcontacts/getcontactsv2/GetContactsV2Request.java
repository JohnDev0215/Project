package com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2;

import com.dee.app.contacts.interfaces.models.apis.getcontacts.GetContactsRequest;

/* JADX INFO: loaded from: classes5.dex */
public class GetContactsV2Request extends GetContactsRequest {
    private GetContactsV2QueryConfig queryConfig;
    private String userId;

    public GetContactsV2Request() {
    }

    public GetContactsV2Request(String str, GetContactsV2QueryConfig getContactsV2QueryConfig) {
        this.userId = str;
        this.queryConfig = getContactsV2QueryConfig;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public GetContactsV2QueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public void setQueryConfig(GetContactsV2QueryConfig getContactsV2QueryConfig) {
        this.queryConfig = getContactsV2QueryConfig;
    }
}
