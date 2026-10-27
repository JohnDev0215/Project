package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public enum IonType {
    NULL,
    BOOL,
    INT,
    FLOAT,
    DECIMAL,
    TIMESTAMP,
    SYMBOL,
    STRING,
    CLOB,
    BLOB,
    LIST,
    SEXP,
    STRUCT,
    DATAGRAM;

    public static boolean isContainer(IonType ionType) {
        return ionType != null && ionType.ordinal() >= LIST.ordinal();
    }

    public static boolean isText(IonType ionType) {
        return ionType == STRING || ionType == SYMBOL;
    }

    public static boolean isLob(IonType ionType) {
        return ionType == BLOB || ionType == CLOB;
    }
}
