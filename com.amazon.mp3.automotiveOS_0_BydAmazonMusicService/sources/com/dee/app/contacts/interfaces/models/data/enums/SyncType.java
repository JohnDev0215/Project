package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum SyncType {
    ALL("all"),
    MASTER("master");

    private String syncType;

    SyncType(String str) {
        this.syncType = str;
    }

    public String getSyncTypeValue() {
        return this.syncType;
    }
}
