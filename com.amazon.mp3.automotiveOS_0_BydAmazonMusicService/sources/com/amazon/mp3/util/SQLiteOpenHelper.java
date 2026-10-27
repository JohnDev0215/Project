package com.amazon.mp3.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.AmazonApplication;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SQLiteOpenHelper {
    private static final String TAG = "SQLiteOpenHelper";
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private final SQLiteDatabase.CursorFactory mFactory;
    private boolean mIsInitializing = false;
    private final String mName;
    private final int mNewVersion;
    private SQLiteDatabase mReadOnlyDatabase;

    public abstract void onCreate(SQLiteDatabase db);

    public void onOpen(SQLiteDatabase db) {
    }

    public abstract int onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if (version < 1) {
            throw new IllegalArgumentException("Version must be >= 1, was " + version);
        }
        this.mContext = context;
        this.mName = name;
        this.mFactory = factory;
        this.mNewVersion = version;
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.database.sqlite.SQLiteDatabase getWritableDatabase() {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.SQLiteOpenHelper.getWritableDatabase():android.database.sqlite.SQLiteDatabase");
    }

    private String getApplicationInstallDirectory() {
        ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
        return applicationInfo == null ? "" : applicationInfo.sourceDir;
    }

    private String getApplicationInstallLocation(final String installDirectory) {
        return (installDirectory == null || installDirectory.isEmpty()) ? "" : installDirectory.startsWith("/data/") ? "internal memory" : (installDirectory.startsWith("/mnt/") || installDirectory.startsWith("/sdcard/")) ? "sdCard" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public synchronized SQLiteDatabase getReadOnlyDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mReadOnlyDatabase;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            return this.mReadOnlyDatabase;
        }
        if (this.mIsInitializing) {
            throw new IllegalStateException("getReadOnlyDatabase called recursively");
        }
        try {
            getWritableDatabase();
        } catch (SQLiteException e) {
            if (this.mName == null) {
                throw e;
            }
            Log.error(TAG, "Couldn't open " + this.mName + " for writing (will try read-only):", e);
        }
        SQLiteClosable sQLiteClosable = null;
        try {
            this.mIsInitializing = true;
            String path = this.mContext.getDatabasePath(this.mName).getPath();
            SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(path, this.mFactory, 1);
            if (sQLiteDatabaseOpenDatabase.getVersion() != this.mNewVersion) {
                throw new SQLiteException("Can't upgrade read-only database from version " + sQLiteDatabaseOpenDatabase.getVersion() + " to " + this.mNewVersion + ": " + path);
            }
            onOpen(sQLiteDatabaseOpenDatabase);
            Log.warning(TAG, "Opened " + this.mName + " in read-only mode");
            this.mReadOnlyDatabase = sQLiteDatabaseOpenDatabase;
            this.mIsInitializing = false;
            return sQLiteDatabaseOpenDatabase;
        } catch (Throwable th) {
            this.mIsInitializing = false;
            if (0 != 0 && null != this.mReadOnlyDatabase) {
                sQLiteClosable.close();
            }
            throw th;
        }
    }

    private synchronized SQLiteDatabase createOrOpenDatabase() {
        int i;
        i = AmazonApplication.getCapabilities().supportsLocalizedCollation() ? 805306368 : 805306384;
        Log.info(TAG, "Try create or open database with path: " + this.mContext.getDatabasePath(this.mName).getPath());
        return SQLiteDatabase.openDatabase(this.mContext.getDatabasePath(this.mName).getPath(), this.mFactory, i);
    }
}
