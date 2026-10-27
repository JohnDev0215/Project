package net.sqlcipher.database;

/* JADX INFO: loaded from: classes3.dex */
public interface SQLiteDatabaseHook {
    void postKey(SQLiteDatabase sQLiteDatabase);

    void preKey(SQLiteDatabase sQLiteDatabase);
}
