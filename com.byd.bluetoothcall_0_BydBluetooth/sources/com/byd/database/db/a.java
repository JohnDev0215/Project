package com.byd.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: DBHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends SQLiteOpenHelper {
    public a(Context context) {
        super(context, "bluetooth_database.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table phone_book(_id integer primary key autoincrement, name varchar, number varchar, pinyin varchar, katakana varchar, image_id varchar, firstletter varchar, firstword varchar, mac varchar)");
        sQLiteDatabase.execSQL("create table call_log_book(_id integer primary key autoincrement, name varchar, number varchar, mac varchar, type varchar, time varchar)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
