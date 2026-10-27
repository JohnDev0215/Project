package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum OwnerIdType {
    COMMS_ID("CommsId"),
    CUSTOMER_ID("CustomerId");

    final String value;

    OwnerIdType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
