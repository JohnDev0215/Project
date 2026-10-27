package com.dee.app.contacts.common.utils;

/* JADX INFO: loaded from: classes5.dex */
public class GenericType<T> {
    private final Class<T> type;

    public GenericType(Class<T> cls) {
        this.type = cls;
    }

    public Class<T> getType() {
        return this.type;
    }
}
