package com.android.inputmethod.dictionarypack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class PrivateLog {
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_EVENT = "event";
    public static final boolean DEBUG = false;
    private static final String LOG_DATABASE_NAME = "log";
    private static final int LOG_DATABASE_VERSION = 1;
    private static final String LOG_TABLE_CREATE = "CREATE TABLE log (date TEXT,event TEXT);";
    private static final String LOG_TABLE_NAME = "log";
    static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ROOT);
    private static PrivateLog sInstance = new PrivateLog();
    private static DebugHelper sDebugHelper = null;

    public static void log(String event) {
    }

    private PrivateLog() {
    }

    public static synchronized PrivateLog getInstance(final Context context) {
        return sInstance;
    }

    static class DebugHelper extends SQLiteOpenHelper {
        static void insert(SQLiteDatabase db, String event) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        DebugHelper(final Context context) {
            super(context, "log", (SQLiteDatabase.CursorFactory) null, 1);
        }
    }
}
