package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum SQLiteDataType {
    NULL("NULL"),
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    BLOB("BLOB");

    private final String sqliteDataType;

    SQLiteDataType(@NonNull String str) {
        this.sqliteDataType = str;
    }

    public String getSqliteDataType() {
        return this.sqliteDataType;
    }

    public static SQLiteDataType from(@NonNull String str) {
        for (SQLiteDataType sQLiteDataType : values()) {
            if (str.equals(sQLiteDataType.getSqliteDataType())) {
                return sQLiteDataType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value.");
    }
}
