package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
abstract class PlaylistsMigrationHelper {
    private static final String TAG = "PlaylistsMigrationHelper";
    final Context mContext;
    private final MigrationListener mMigrationListener;
    final SQLiteDatabase mPreUnityDb;
    final PreUnityPlaylistUtil mPreUnityPlaylistUtil = new PreUnityPlaylistUtil();
    final TrackMigrationHelper mTrackMigrationHelper;

    abstract boolean createLocalPlaylist(final Uri playlistUri, final String playlistAsin, final String playlistName);

    abstract Cursor getDownloadedPlaylistsCursor();

    abstract Uri getPlaylistContentUri(final String playlistAsin, final String playlistName);

    abstract boolean isPlaylistDownloaded(final PreUnityPlaylist preUnityPlaylist);

    abstract boolean isPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist);

    void updatePlaylistWithMigratedTracks(final PreUnityPlaylist preUnityPlaylist, final Uri playlistUri, final List<Uri> migratedTrackUris) {
    }

    public PlaylistsMigrationHelper(final Context context, final SQLiteDatabase preUnityDb, final MigrationListener migrationListener) {
        this.mContext = context;
        this.mPreUnityDb = preUnityDb;
        this.mTrackMigrationHelper = new TrackMigrationHelper(context);
        this.mMigrationListener = migrationListener;
    }

    public boolean migratePlaylists() {
        Profiler.begin("Migrating playlists: " + getClass().getSimpleName());
        try {
            Cursor downloadedPlaylistsCursor = getDownloadedPlaylistsCursor();
            try {
                if (downloadedPlaylistsCursor == null) {
                    getMigrationListener().onMigrationFailed("The downloadedPlaylistsCursor is null.");
                    if (downloadedPlaylistsCursor != null) {
                        downloadedPlaylistsCursor.close();
                    }
                    return false;
                }
                int count = downloadedPlaylistsCursor.getCount();
                if (!downloadedPlaylistsCursor.moveToFirst()) {
                    downloadedPlaylistsCursor.close();
                    return true;
                }
                Log.verbose(TAG, "numberOfPlaylistsToMigrate: " + count);
                getMigrationListener().setTotalPlaylistsToMigrate(count);
                int i = 0;
                while (!downloadedPlaylistsCursor.isAfterLast()) {
                    if (migratePlaylist(new PreUnityPlaylist(downloadedPlaylistsCursor))) {
                        i++;
                    }
                    downloadedPlaylistsCursor.moveToNext();
                }
                boolean z = i == count;
                downloadedPlaylistsCursor.close();
                return z;
            } finally {
            }
        } finally {
            Profiler.end();
        }
    }

    boolean migratePlaylist(final PreUnityPlaylist preUnityPlaylist) {
        getMigrationListener().onPlaylistMigrationStarted(preUnityPlaylist);
        if (preUnityPlaylist.getNumberOfTracksToMigrate(this.mPreUnityDb) == 0) {
            getMigrationListener().onPlaylistAlreadyMigrated(preUnityPlaylist);
            Log.debug(TAG, "The playlist has no tracks to migrate: " + preUnityPlaylist.playlistId + ",  " + preUnityPlaylist.playlistName);
            return true;
        }
        if (isPlaylistMigrated(preUnityPlaylist)) {
            getMigrationListener().onPlaylistAlreadyMigrated(preUnityPlaylist);
            Log.debug(TAG, "The playlist is already migrated: " + preUnityPlaylist.playlistId + ",  " + preUnityPlaylist.playlistName);
            return true;
        }
        Uri playlistContentUri = getPlaylistContentUri(preUnityPlaylist.playlistAsin, preUnityPlaylist.playlistName);
        if (playlistContentUri == null || !PlaylistUtil.isPlaylistValid(this.mContext, playlistContentUri)) {
            getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "The playlist is not found");
            return false;
        }
        MigrationStats migrationStatsMigratePlaylistTracks = migratePlaylistTracks(preUnityPlaylist, playlistContentUri);
        getMigrationListener().addTracksMigrationStats(preUnityPlaylist, migrationStatsMigratePlaylistTracks);
        if (migrationStatsMigratePlaylistTracks.getTracksMigrated() == 0) {
            getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "No tracks migrated for this playlist");
            return false;
        }
        if (!isLocalPlaylistCopyExists(preUnityPlaylist)) {
            if (!createLocalPlaylist(playlistContentUri, preUnityPlaylist.playlistAsin, preUnityPlaylist.playlistName)) {
                getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "Could not create a local playlist: " + preUnityPlaylist.playlistName);
                return false;
            }
        } else {
            List<Uri> migratedTracksContentUris = migrationStatsMigratePlaylistTracks.getMigratedTracksContentUris();
            if (migratedTracksContentUris.size() > 0) {
                updatePlaylistWithMigratedTracks(preUnityPlaylist, playlistContentUri, migratedTracksContentUris);
            }
        }
        if (!migrationStatsMigratePlaylistTracks.isAllTracksMigrated()) {
            getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "Not all tracks are migrated: " + preUnityPlaylist);
            return false;
        }
        getMigrationListener().onPlaylistMigrated(preUnityPlaylist);
        return true;
    }

    @VisibleForTesting
    MigrationStats migratePlaylistTracks(final PreUnityPlaylist preUnityPlaylist, final Uri playlistUri) {
        TracksCollectionMigrationHelper tracksCollectionMigrationHelper = new TracksCollectionMigrationHelper(this.mContext);
        String str = TAG;
        Log.verbose(str, "Migrating tracks for a pre-Unity playlist: " + preUnityPlaylist.playlistId);
        MigrationStats migrationStatsMigrateDownloadedTracks = tracksCollectionMigrationHelper.migrateDownloadedTracks(this.mPreUnityPlaylistUtil.getDownloadedPlaylistsTracksCursor(this.mPreUnityDb, preUnityPlaylist.playlistId), this.mTrackMigrationHelper);
        Log.verbose(str, "Playlist tracks migration stats: " + preUnityPlaylist + ", " + migrationStatsMigrateDownloadedTracks);
        return migrationStatsMigrateDownloadedTracks;
    }

    MigrationListener getMigrationListener() {
        return this.mMigrationListener;
    }

    @VisibleForTesting
    boolean isPlaylistDownloaded(final SQLiteDatabase readOnlyDb, final String playlistName, final String contentItemType) {
        return PlaylistUtil.isPlaylistDownloaded(readOnlyDb, playlistName, contentItemType);
    }

    boolean isLocalPlaylistCopyExists(final PreUnityPlaylist preUnityPlaylist) {
        return isPlaylistDownloaded(preUnityPlaylist);
    }
}
