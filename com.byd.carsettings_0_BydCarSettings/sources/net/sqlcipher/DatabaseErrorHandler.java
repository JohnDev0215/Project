package net.sqlcipher;

import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: loaded from: classes3.dex */
public interface DatabaseErrorHandler {
    void onCorruption(SQLiteDatabase sQLiteDatabase);
}
