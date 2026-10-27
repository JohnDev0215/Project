package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class d8 {
    @TargetApi(11)
    public static synchronized SQLiteDatabase a(SQLiteOpenHelper sQLiteOpenHelper) {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = sQLiteOpenHelper.getWritableDatabase();
            ga.a("LocalDataStorageUtils");
        } catch (SQLiteException e) {
            Log.e(ga.a("LocalDataStorageUtils"), "Failed to open MAP writable db", e);
            if (!(e instanceof SQLiteDatabaseLockedException)) {
                throw e;
            }
            sQLiteOpenHelper.close();
            try {
                SQLiteDatabase writableDatabase2 = sQLiteOpenHelper.getWritableDatabase();
                Log.i(ga.a("LocalDataStorageUtils"), "Successfully open MAP writable db after closing it and retry");
                yd.b("OpenMAPDBOnRetrySuccess", new String[0]);
                return writableDatabase2;
            } catch (SQLiteDatabaseLockedException e2) {
                Log.e(ga.a("LocalDataStorageUtils"), "Failed open MAP writable db after closing it and retry", e);
                yd.b("OpenMAPDBOnRetryFailed", new String[0]);
                throw e2;
            }
        }
        return writableDatabase;
    }
}
