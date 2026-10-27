package com.byd.vrdownload.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.byd.vrdownload.model.LanguageUpdate;

/* JADX INFO: loaded from: classes.dex */
public class LanguageDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "languagetasks.db";
    public static final int DATABASE_VERSION = 6;

    public LanguageDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 6);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        setWriteAheadLoggingEnabled(true);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS languagetasksmanger" + String.format("(%s INTEGER PRIMARY KEY, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR )", "id", LanguageUpdate.LANGUAGE, LanguageUpdate.APK_VERSION, LanguageUpdate.CURRENT_VERSION, LanguageUpdate.DOWNLOADING_APK_VERSION, LanguageUpdate.DOWNLOADING_PKG_VERSION, LanguageUpdate.DOWNLOADING_PKG_KEY, LanguageUpdate.DOWNLOADING_URL, LanguageUpdate.DOWNLOADING_PKG_SIZE, LanguageUpdate.DOWNLOADING_PKG_DESC, LanguageUpdate.DOWNLOADING_PKGMd5, LanguageUpdate.LATEST_APK_VERSION, LanguageUpdate.LATEST_PKG_VERSION, LanguageUpdate.LATEST_PKG_KEY, LanguageUpdate.LATEST_URL, LanguageUpdate.LATEST_PKG_SIZE, LanguageUpdate.LATEST_PKG_DESC, LanguageUpdate.LATEST_PKGMd5, LanguageUpdate.DOWNLOADING_PATCH_PKG_KEY, LanguageUpdate.DOWNLOADING_PATCH_PKG_VERSION, LanguageUpdate.LATEST_PATCH_PKG_KEY, LanguageUpdate.LATEST_PATCH_PKG_VERSION, LanguageUpdate.LATEST_PATCH_PKG_SIZE, LanguageUpdate.LATEST_PATCH_PKG_MD5, LanguageUpdate.DOWNLOADING_PATH));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 3) {
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN downloadingPkgDesc VARCHAR");
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN latestPkgDesc VARCHAR");
        }
        if (i < 4) {
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN downloadingPath VARCHAR");
        }
        if (i < 5) {
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN currentVersion VARCHAR");
        }
        if (i < 6) {
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN latestPatchPkgMd5 VARCHAR");
            sQLiteDatabase.execSQL("ALTER TABLE languagetasksmanger ADD COLUMN latestPatchPkgSize VARCHAR");
        }
    }
}
