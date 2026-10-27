package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class PreUnityCirrusMediaSourceDb {
    private static final String TAG = "PreUnityCirrusMediaSourceDb";
    private final Context context;

    public PreUnityCirrusMediaSourceDb(final Context context) {
        this.context = context;
    }

    public File backup(final SQLiteDatabase db) {
        try {
            DatabaseUpgradeHelper databaseUpgradeHelper = new DatabaseUpgradeHelper(this.context);
            File file = new File(db.getPath());
            File backupFile = getBackupFile(file);
            if (backupFile.exists() && backupFile.canRead()) {
                Log.verbose(TAG, "DB backup is available: " + backupFile.getAbsolutePath());
                return backupFile;
            }
            File fileBackupDb = databaseUpgradeHelper.backupDb(file.getParent(), "CirrusMediaSource.db", "CirrusMediaSource_bak.db");
            Log.verbose(TAG, "DB is backed up: " + fileBackupDb.getAbsolutePath());
            return fileBackupDb;
        } catch (IOException e) {
            Log.error(TAG, "DB Backup error: " + e.getMessage(), e);
            return null;
        }
    }

    @NonNull
    public File getBackupFile(final SQLiteDatabase db) {
        return new File(new File(db.getPath()).getParent(), "CirrusMediaSource_bak.db");
    }

    @NonNull
    public File getBackupFile(final File dbPath) {
        return new File(dbPath.getParent(), "CirrusMediaSource_bak.db");
    }

    public void delete() {
        Log.verbose(TAG, "Removing db: CirrusMediaSource_bak.db");
        DbUtil.deleteDatabase(this.context, "CirrusMediaSource_bak.db");
    }

    public boolean isBackupPresent(final SQLiteDatabase db) {
        File backupFile = getBackupFile(db);
        return (backupFile.exists() && backupFile.canRead()) ? false : true;
    }

    public SQLiteDatabase getPreUnityReadOnlyDb() {
        return getReadOnlyDb(getBackupFile(new File(CirrusDatabase.getReadOnlyDatabase(this.context).getPath())));
    }

    public SQLiteDatabase getReadOnlyDb(final File dbFile) {
        return SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, 1);
    }
}
