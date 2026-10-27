package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum PermissionAuthStatus {
    DENIED("Denied"),
    GRANTED("Granted"),
    UNDETERMINED("Undetermined");

    public final String name;

    PermissionAuthStatus(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
