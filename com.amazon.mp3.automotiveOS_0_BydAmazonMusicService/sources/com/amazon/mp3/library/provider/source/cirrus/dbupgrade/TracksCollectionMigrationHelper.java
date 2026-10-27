package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.amazon.mp3.download.library.scanner.MediaScanner;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class TracksCollectionMigrationHelper {
    private static final String TAG = "TracksCollectionMigrationHelper";
    private final Context mContext;

    public TracksCollectionMigrationHelper(final Context context) {
        this.mContext = context;
    }

    public MigrationStats migrateDownloadedTracks(final Cursor tracksToMigrateCursor, final TrackMigrator trackMigrator) throws SQLException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        MigrationStats migrationStats = new MigrationStats();
        try {
            Profiler.begin("Migrating downloaded tracks");
            if (!tracksToMigrateCursor.moveToFirst()) {
                Log.verbose(TAG, "No tracks to migrate.");
                return migrationStats;
            }
            migrationStats.setTotalTracksToMigrate(tracksToMigrateCursor.getCount());
            int i = 0;
            while (!tracksToMigrateCursor.isAfterLast()) {
                PreUnityTrack preUnityTrack = new PreUnityTrack(tracksToMigrateCursor);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                i++;
                sb.append(String.valueOf(i));
                sb.append(". Migrating track: ");
                sb.append(preUnityTrack);
                Log.verbose(str, sb.toString());
                try {
                    Uri uriMigrateTrack = trackMigrator.migrateTrack(preUnityTrack);
                    if (uriMigrateTrack == null) {
                        if (preUnityTrack.isOwned()) {
                            Log.verbose(str, "Scheduling a media scan for " + preUnityTrack.localUri);
                            MediaScanner.scanSync(this.mContext, preUnityTrack.localUri);
                        }
                        Log.verbose(str, String.valueOf(i) + ". The track is not migrated: " + preUnityTrack);
                        migrationStats.addNotMigratedTrack(preUnityTrack);
                    } else {
                        migrationStats.addMigratedTracksContentUri(uriMigrateTrack);
                        migrationStats.incrementTracksMigrated();
                        onTrackMigrated(preUnityTrack, uriMigrateTrack);
                    }
                } catch (IOException unused) {
                    Log.warning(TAG, "The track cannot be migrated. Unable to read the track file: " + preUnityTrack.localUri);
                    migrationStats.incrementTracksMigrated();
                }
                tracksToMigrateCursor.moveToNext();
            }
            Log.verbose(TAG, "Number of migrated tracks: " + migrationStats.getTracksMigrated() + " out of " + migrationStats.getTotalTracksToMigrate());
            return migrationStats;
        } finally {
            tracksToMigrateCursor.close();
            Profiler.end();
            migrationStats.setMigrationTimeMs(System.currentTimeMillis() - jCurrentTimeMillis);
        }
    }

    void onTrackMigrated(final PreUnityTrack preUnityTrack, final Uri migratedTrackContentUri) {
        Log.verbose(TAG, "Track is migrated: " + preUnityTrack + ", migratedTrackContentUri: " + migratedTrackContentUri);
    }
}
