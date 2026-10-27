package com.dee.app.contacts.interfaces.models.apis.cansync;

/* JADX INFO: loaded from: classes5.dex */
public class CanSyncContactsRequest {
    private CanSyncContactsQueryConfig queryConfig;
    private String userId;

    public CanSyncContactsRequest() {
    }

    public CanSyncContactsRequest(String str, CanSyncContactsQueryConfig canSyncContactsQueryConfig) {
        this.userId = str;
        this.queryConfig = canSyncContactsQueryConfig;
    }

    public CanSyncContactsQueryConfig getQueryConfig() {
        return this.queryConfig;
    }

    public void setQueryConfig(CanSyncContactsQueryConfig canSyncContactsQueryConfig) {
        this.queryConfig = canSyncContactsQueryConfig;
    }

    public String getUserId() {
        return this.userId;
    }
}
