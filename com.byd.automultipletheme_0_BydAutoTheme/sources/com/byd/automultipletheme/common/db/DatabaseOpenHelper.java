package com.byd.automultipletheme.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DatabaseOpenHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u000f"}, d2 = {"Lcom/byd/automultipletheme/common/db/DatabaseOpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "upgradeToVersion2", "upgradeToVersion3", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DatabaseOpenHelper extends SQLiteOpenHelper {
    private final void upgradeToVersion2(SQLiteDatabase db) {
    }

    private final void upgradeToVersion3(SQLiteDatabase db) {
    }

    public DatabaseOpenHelper(Context context) {
        super(context, DB.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("create table external(_id INTEGER PRIMARY KEY,wallpaper_path VARCHAR,wallpaper_type INTEGER,res_id INTEGER,app_version_name VARCHAR,app_version_code VARCHAR)");
        db.execSQL("create table carousel_time(_id INTEGER PRIMARY KEY,time INTEGER)");
        db.execSQL("create table carousel_res(_id INTEGER PRIMARY KEY,path0 VARCHAR,path1 VARCHAR,path2 VARCHAR,path3 VARCHAR)");
        db.execSQL("create table if not exists carousel_save_path(_id integer primary key,res_id integer ,res_path text not null)");
        db.execSQL("create table wallpaper_result(_id INTEGER PRIMARY KEY,res_id INTEGER, result INTEGER )");
        onUpgrade(db, 1, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        while (oldVersion < newVersion) {
            if (oldVersion == 1) {
                upgradeToVersion2(db);
            } else if (oldVersion == 2) {
                upgradeToVersion3(db);
            }
            oldVersion++;
        }
    }
}
