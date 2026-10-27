package com.byd.logtools.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* JADX INFO: compiled from: CrashLogDatabaseHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends SQLiteOpenHelper {
    public d(Context context) {
        super(context, "CrashLogDatabase.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.d("CrashLogDatabaseHelper", "CrashLogDatabaseHelper onCreate");
        sQLiteDatabase.execSQL("create table if not exists crash_info (id integer primary key,crash_type varchar(255),crash_module text,count interger DEFAULT 0)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
