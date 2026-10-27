package com.dee.app.contacts.common.models.server;

import com.fasterxml.jackson.annotation.JsonProperty;

/* JADX INFO: loaded from: classes5.dex */
public class CanSyncContactsServerResponse {

    @JsonProperty
    private String canSyncStatus;

    public String getCanSyncStatus() {
        return this.canSyncStatus;
    }
}
