package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.DatabaseMigrationMetricsProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.DatabaseMigrationStats;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.DatabaseUpgradeHelper;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PreUnityCirrusMediaSourceDb;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import org.apache.commons.lang3.time.StopWatch;

/* JADX INFO: loaded from: classes3.dex */
public class DbMigrationOperation extends SyncOperation {
    private static final String TAG = "DbMigrationOperation";
    private final DatabaseMigrationMetricsProvider mDatabaseMigrationMetricsProvider;

    public DbMigrationOperation(final Context context, final SQLiteDatabase db) {
        this(context, db, new DatabaseMigrationMetricsProvider());
    }

    @VisibleForTesting
    DbMigrationOperation(final Context context, final SQLiteDatabase db, final DatabaseMigrationMetricsProvider databaseMigrationMetricsProvider) {
        super(context, db);
        this.mDatabaseMigrationMetricsProvider = databaseMigrationMetricsProvider;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        PreUnityCirrusMediaSourceDb preUnityCirrusMediaSourceDb = new PreUnityCirrusMediaSourceDb(getContext());
        int i = 0;
        if (!preUnityCirrusMediaSourceDb.getBackupFile(getDatabase()).exists()) {
            return 0;
        }
        DatabaseUpgradeHelper databaseUpgradeHelper = new DatabaseUpgradeHelper(getContext());
        if (databaseUpgradeHelper.getRetryCount() > 3) {
            Log.verbose(TAG, "Reached a max number of retries.");
            return 0;
        }
        Profiler.enable();
        Profiler.begin("Migrating tracks and playlists");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        SQLiteDatabase preUnityReadOnlyDb = preUnityCirrusMediaSourceDb.getPreUnityReadOnlyDb();
        String str = TAG;
        Log.verbose(str, "Begin migrating tracks and playlists.");
        DatabaseMigrationStats databaseMigrationStatsMigrateDownloadedTracksAndPlaylists = databaseUpgradeHelper.migrateDownloadedTracksAndPlaylists(preUnityReadOnlyDb);
        preUnityReadOnlyDb.close();
        stopWatch.stop();
        Profiler.end();
        databaseUpgradeHelper.incrementRetryCount();
        if (databaseMigrationStatsMigrateDownloadedTracksAndPlaylists == null) {
            Log.verbose(str, "The DB migration is failed");
            this.mDatabaseMigrationMetricsProvider.sendDbMigrationFailedEvent(null, stopWatch);
            return 5;
        }
        if (databaseMigrationStatsMigrateDownloadedTracksAndPlaylists.isEverythingMigrated()) {
            Log.verbose(str, "The DB is migrated.");
            databaseMigrationStatsMigrateDownloadedTracksAndPlaylists.logStats();
            databaseUpgradeHelper.clearMigrationSharedPrefs(getContext());
            preUnityCirrusMediaSourceDb.delete();
            SyncService.setDbMigrationSyncNeeded(false);
            this.mDatabaseMigrationMetricsProvider.sendDbMigrationCompletedEvent(databaseMigrationStatsMigrateDownloadedTracksAndPlaylists, stopWatch);
        } else {
            Log.verbose(str, "The " + databaseMigrationStatsMigrateDownloadedTracksAndPlaylists.getWhatIsNotMigratedMsg() + " are not migrated.");
            if (databaseUpgradeHelper.getRetryCount() >= 3) {
                Log.verbose(str, "Reached a max number of retries.");
                this.mDatabaseMigrationMetricsProvider.sendDbMigrationFailedEvent(databaseMigrationStatsMigrateDownloadedTracksAndPlaylists, stopWatch);
            } else {
                i = 1;
            }
        }
        Log.verbose(str, "DB migration time (ms): " + stopWatch.getTime());
        Profiler.logAll();
        Profiler.disable();
        return i;
    }
}
