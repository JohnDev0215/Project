package com.android.launcher3.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Process;
import android.util.Log;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.provider.LauncherDbUtils;

/* JADX INFO: loaded from: classes4.dex */
public class GridBackupTable {
    private static final int ID_PROPERTY = -1;
    private static final String KEY_DB_VERSION = "rank";
    private static final String KEY_GRID_X_SIZE = "spanX";
    private static final String KEY_GRID_Y_SIZE = "spanY";
    private static final String KEY_HOTSEAT_SIZE = "screen";
    private static final String TAG = "GridBackupTable";
    private final Context mContext;
    private final SQLiteDatabase mDb;
    private final int mOldGridX;
    private final int mOldGridY;
    private final int mOldHotseatSize;
    private int mRestoredGridX;
    private int mRestoredGridY;
    private int mRestoredHotseatSize;

    public GridBackupTable(Context context, SQLiteDatabase db, int hotseatSize, int gridX, int gridY) {
        this.mContext = context;
        this.mDb = db;
        this.mOldHotseatSize = hotseatSize;
        this.mOldGridX = gridX;
        this.mOldGridY = gridY;
    }

    public boolean backupOrRestoreAsNeeded() {
        if (!LauncherDbUtils.tableExists(this.mDb, LauncherSettings.Favorites.BACKUP_TABLE_NAME)) {
            if (LauncherSettings.Settings.call(this.mContext.getContentResolver(), LauncherSettings.Settings.METHOD_WAS_EMPTY_DB_CREATED).getBoolean("value", false)) {
                return false;
            }
            copyTable("favorites", LauncherSettings.Favorites.BACKUP_TABLE_NAME);
            encodeDBProperties();
            return false;
        }
        if (!loadDbProperties()) {
            return false;
        }
        copyTable(LauncherSettings.Favorites.BACKUP_TABLE_NAME, "favorites");
        Log.d(TAG, "Backup table found");
        return true;
    }

    public int getRestoreHotseatAndGridSize(Point outGridSize) {
        outGridSize.set(this.mRestoredGridX, this.mRestoredGridY);
        return this.mRestoredHotseatSize;
    }

    private void copyTable(String from, String to) {
        long serialNumberForUser = UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(Process.myUserHandle());
        LauncherDbUtils.dropTable(this.mDb, to);
        LauncherSettings.Favorites.addTableToDb(this.mDb, serialNumberForUser, false, to);
        this.mDb.execSQL("INSERT INTO " + to + " SELECT * FROM " + from + " where _id > -1");
    }

    private void encodeDBProperties() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Integer) (-1));
        contentValues.put("rank", Integer.valueOf(this.mDb.getVersion()));
        contentValues.put("spanX", Integer.valueOf(this.mOldGridX));
        contentValues.put("spanY", Integer.valueOf(this.mOldGridY));
        contentValues.put("screen", Integer.valueOf(this.mOldHotseatSize));
        this.mDb.insert(LauncherSettings.Favorites.BACKUP_TABLE_NAME, null, contentValues);
    }

    private boolean loadDbProperties() {
        Cursor cursorQuery = this.mDb.query(LauncherSettings.Favorites.BACKUP_TABLE_NAME, new String[]{"rank", "spanX", "spanY", "screen"}, "_id=-1", null, null, null, null);
        try {
            if (!cursorQuery.moveToNext()) {
                Log.e(TAG, "Meta data not found in backup table");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            }
            if (this.mDb.getVersion() != cursorQuery.getInt(0)) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            }
            this.mRestoredGridX = cursorQuery.getInt(1);
            this.mRestoredGridY = cursorQuery.getInt(2);
            this.mRestoredHotseatSize = cursorQuery.getInt(3);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return true;
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
}
