package com.android.keychain.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class GrantsDatabase {
    public DatabaseHelper mDatabaseHelper;

    private class DatabaseHelper extends SQLiteOpenHelper {
        private final ExistingKeysProvider mKeysProvider;

        public DatabaseHelper(GrantsDatabase grantsDatabase, Context context, ExistingKeysProvider existingKeysProvider) {
            super(context, "grants.db", (SQLiteDatabase.CursorFactory) null, 2);
            this.mKeysProvider = existingKeysProvider;
        }

        void createSelectableTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS userselectable (  alias STRING NOT NULL,  is_selectable STRING NOT NULL,  UNIQUE (alias))");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.w("KeyChain", "Creating new DB.");
            sQLiteDatabase.execSQL("CREATE TABLE grants (  alias STRING NOT NULL,  uid INTEGER NOT NULL,  UNIQUE (alias,uid))");
            createSelectableTable(sQLiteDatabase);
            markExistingKeysAsSelectable(sQLiteDatabase);
        }

        private void markExistingKeysAsSelectable(SQLiteDatabase sQLiteDatabase) {
            for (String str : this.mKeysProvider.getExistingKeyAliases()) {
                Log.w("KeyChain", "Existing alias: " + str);
                if (!hasEntryInUserSelectableTable(sQLiteDatabase, str)) {
                    Log.w("KeyChain", "Marking as selectable: " + str);
                    markKeyAsSelectable(sQLiteDatabase, str);
                }
            }
        }

        private void markKeyAsSelectable(SQLiteDatabase sQLiteDatabase, String str) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("alias", str);
            contentValues.put("is_selectable", Boolean.toString(true));
            sQLiteDatabase.replace("userselectable", null, contentValues);
        }

        private boolean hasEntryInUserSelectableTable(SQLiteDatabase sQLiteDatabase, String str) {
            return DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT COUNT(*) FROM userselectable WHERE alias=?", new String[]{str}) > 0;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w("KeyChain", "upgrade from version " + i + " to version " + i2);
            if (i == 1) {
                createSelectableTable(sQLiteDatabase);
                markExistingKeysAsSelectable(sQLiteDatabase);
            }
        }
    }

    public GrantsDatabase(Context context, ExistingKeysProvider existingKeysProvider) {
        this.mDatabaseHelper = new DatabaseHelper(this, context, existingKeysProvider);
    }

    public void destroy() {
        this.mDatabaseHelper.close();
        this.mDatabaseHelper = null;
    }

    boolean hasGrantInternal(SQLiteDatabase sQLiteDatabase, int i, String str) {
        return DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT COUNT(*) FROM grants WHERE uid=? AND alias=?", new String[]{String.valueOf(i), str}) > 0;
    }

    public boolean hasGrant(int i, String str) {
        return hasGrantInternal(this.mDatabaseHelper.getWritableDatabase(), i, str);
    }

    public void setGrant(int i, String str, boolean z) {
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        if (z) {
            if (hasGrantInternal(writableDatabase, i, str)) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("alias", str);
            contentValues.put("uid", Integer.valueOf(i));
            writableDatabase.insert("grants", "alias", contentValues);
            return;
        }
        writableDatabase.delete("grants", "uid=? AND alias=?", new String[]{String.valueOf(i), str});
    }

    public void removeAliasInformation(String str) {
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        writableDatabase.delete("grants", "alias=?", new String[]{str});
        writableDatabase.delete("userselectable", "alias=?", new String[]{str});
    }

    public void removeAllAliasesInformation() {
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        writableDatabase.delete("grants", null, null);
        writableDatabase.delete("userselectable", null, null);
    }

    public void purgeOldGrants(PackageManager packageManager) {
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        Cursor cursorQuery = writableDatabase.query("grants", new String[]{"uid"}, null, null, "uid", null, null);
        while (cursorQuery != null) {
            try {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                int i = cursorQuery.getInt(0);
                if (!(packageManager.getPackagesForUid(i) != null)) {
                    Log.d("KeyChain", String.format("deleting grants for UID %d because its package is no longer installed", Integer.valueOf(i)));
                    writableDatabase.delete("grants", "uid=?", new String[]{Integer.toString(i)});
                }
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
        writableDatabase.setTransactionSuccessful();
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        writableDatabase.endTransaction();
    }

    public void setIsUserSelectable(String str, boolean z) {
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("alias", str);
        contentValues.put("is_selectable", Boolean.toString(z));
        writableDatabase.replace("userselectable", null, contentValues);
    }

    public boolean isUserSelectable(String str) {
        Cursor cursorQuery = this.mDatabaseHelper.getWritableDatabase().query("userselectable", new String[]{"is_selectable"}, "alias=?", new String[]{str}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToNext()) {
                    boolean z = Boolean.parseBoolean(cursorQuery.getString(0));
                    if (cursorQuery.getCount() > 1) {
                        Log.w("KeyChain", String.format("Have more than one result for alias %s", str));
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return z;
                }
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
        return false;
    }
}
