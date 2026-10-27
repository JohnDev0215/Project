package com.android.launcher3.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.util.Log;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.util.IntArray;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherDbUtils {
    private static final String TAG = "LauncherDbUtils";

    public static boolean prepareScreenZeroToHostQsb(Context context, SQLiteDatabase db) {
        try {
            SQLiteTransaction sQLiteTransaction = new SQLiteTransaction(db);
            try {
                Cursor cursorRawQuery = db.rawQuery(String.format(Locale.ENGLISH, "SELECT MIN(%1$s) from %2$s where %3$s = %4$d", LauncherSettings.Favorites.SCREEN, "favorites", LauncherSettings.Favorites.CONTAINER, -100), null);
                try {
                    if (!cursorRawQuery.moveToNext()) {
                        sQLiteTransaction.commit();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        sQLiteTransaction.close();
                        return true;
                    }
                    int i = cursorRawQuery.getInt(0);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (i != 0) {
                        renameScreen(db, i, 0);
                    }
                    if (DatabaseUtils.queryNumEntries(db, "favorites", "container = -100 and screen = 0 and cellY = 0") == 0) {
                        sQLiteTransaction.commit();
                        sQLiteTransaction.close();
                        return true;
                    }
                    new LossyScreenMigrationTask(context, LauncherAppState.getIDP(context), db).migrateScreen0();
                    sQLiteTransaction.commit();
                    sQLiteTransaction.close();
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to update workspace size", e);
            return false;
        }
    }

    private static void renameScreen(SQLiteDatabase db, int oldScreen, int newScreen) {
        String[] strArr = {Integer.toString(oldScreen)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(newScreen));
        db.update("favorites", contentValues, "container = -100 and screen = ?", strArr);
    }

    public static IntArray queryIntArray(SQLiteDatabase db, String tableName, String columnName, String selection, String groupBy, String orderBy) {
        IntArray intArray = new IntArray();
        Cursor cursorQuery = db.query(tableName, new String[]{columnName}, selection, null, groupBy, null, orderBy);
        while (cursorQuery.moveToNext()) {
            try {
                intArray.add(cursorQuery.getInt(0));
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return intArray;
    }

    public static boolean tableExists(SQLiteDatabase db, String tableName) {
        Cursor cursorQuery = db.query(true, "sqlite_master", new String[]{"tbl_name"}, "tbl_name = ?", new String[]{tableName}, null, null, null, null, null);
        try {
            boolean z = cursorQuery.getCount() > 0;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return z;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void dropTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public static class SQLiteTransaction extends Binder implements AutoCloseable {
        private final SQLiteDatabase mDb;

        public SQLiteTransaction(SQLiteDatabase db) {
            this.mDb = db;
            db.beginTransaction();
        }

        public void commit() {
            this.mDb.setTransactionSuccessful();
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.mDb.endTransaction();
        }

        public SQLiteDatabase getDb() {
            return this.mDb;
        }
    }
}
