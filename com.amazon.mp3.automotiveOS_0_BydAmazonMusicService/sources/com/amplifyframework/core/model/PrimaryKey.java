package com.amplifyframework.core.model;

/* JADX INFO: loaded from: classes5.dex */
public final class PrimaryKey {
    private static final String ID = "id";

    public static String fieldName() {
        return ID;
    }

    private PrimaryKey() {
    }

    public static boolean matches(Object obj) {
        return (obj instanceof String) && fieldName().equals(obj);
    }
}
