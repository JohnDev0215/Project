package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.DeviceOnlyPlaylist;
import com.amazon.mp3.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
class DeviceOnlyPlaylistsMigrationHelper extends UdoPlaylistsMigrationHelper {
    private static final String TAG = "DeviceOnlyPlaylistsMigrationHelper";

    public DeviceOnlyPlaylistsMigrationHelper(final Context context, final SQLiteDatabase preUnityDb, final MigrationListener migrationListener) {
        super(context, preUnityDb, migrationListener);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    public boolean migratePlaylist(final PreUnityPlaylist preUnityPlaylist) {
        getMigrationListener().onPlaylistMigrationStarted(preUnityPlaylist);
        if (isPlaylistDownloaded(preUnityPlaylist)) {
            String str = TAG;
            Log.debug(str, "The local playlist " + preUnityPlaylist + " is downloaded already.");
            getMigrationListener().onPlaylistAlreadyMigrated(preUnityPlaylist);
            int numberOfTracksToMigrate = preUnityPlaylist.getNumberOfTracksToMigrate(this.mPreUnityDb);
            Uri playlistContentUri = getPlaylistContentUri(preUnityPlaylist.playlistAsin, preUnityPlaylist.playlistName);
            int playlistCount = PlaylistUtil.getPlaylistCount(this.mContext, playlistContentUri);
            Log.verbose(str, "totalTracksToMigrate: " + numberOfTracksToMigrate + ", numberOfMigratedTracks: " + playlistCount);
            if (numberOfTracksToMigrate <= playlistCount) {
                return true;
            }
            List<Uri> listAddMissingTracks = addMissingTracks(preUnityPlaylist, playlistContentUri);
            if (listAddMissingTracks.size() > 0) {
                addTracksToPlaylist(listAddMissingTracks, playlistContentUri);
                Log.verbose(str, "Adding " + listAddMissingTracks.size() + " tracks to playlist: " + playlistContentUri);
            }
            return numberOfTracksToMigrate + listAddMissingTracks.size() >= playlistCount;
        }
        if (!createLocalPlaylist(null, null, preUnityPlaylist.playlistName)) {
            Log.warning(TAG, "Could not create a device only playlist: " + preUnityPlaylist.playlistName);
            return false;
        }
        Uri playlistContentUri2 = getPlaylistContentUri(preUnityPlaylist.playlistAsin, preUnityPlaylist.playlistName);
        Log.debug(TAG, "Migrating a playlist: " + preUnityPlaylist + ", uri: " + playlistContentUri2);
        if (playlistContentUri2 != null && PlaylistUtil.isPlaylistValid(this.mContext, playlistContentUri2)) {
            MigrationStats migrationStatsMigratePlaylistTracks = migratePlaylistTracks(preUnityPlaylist, playlistContentUri2);
            getMigrationListener().addTracksMigrationStats(preUnityPlaylist, migrationStatsMigratePlaylistTracks);
            if (migrationStatsMigratePlaylistTracks.isAllTracksMigrated()) {
                getMigrationListener().onPlaylistMigrated(preUnityPlaylist);
                return true;
            }
            getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "Not all tracks are migrated: " + preUnityPlaylist);
        } else {
            getMigrationListener().onPlaylistMigrationFailed(preUnityPlaylist, "The playlist is not found");
        }
        return false;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    MigrationStats migratePlaylistTracks(final PreUnityPlaylist preUnityPlaylist, final Uri playlistUri) {
        String str = TAG;
        Log.debug(str, "Migrating a pre-Unity playlist: " + preUnityPlaylist + ", uri: " + playlistUri);
        MigrationStats migrationStatsMigrateDeviceOnlyPlaylistsTracks = migrateDeviceOnlyPlaylistsTracks(preUnityPlaylist);
        StringBuilder sb = new StringBuilder();
        sb.append("MigrationStats: ");
        sb.append(migrationStatsMigrateDeviceOnlyPlaylistsTracks);
        Log.verbose(str, sb.toString());
        if (migrationStatsMigrateDeviceOnlyPlaylistsTracks.getMigratedTracksContentUris().size() > 0) {
            addTracksToPlaylist(migrationStatsMigrateDeviceOnlyPlaylistsTracks.getMigratedTracksContentUris(), playlistUri);
        }
        return migrationStatsMigrateDeviceOnlyPlaylistsTracks;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean createLocalPlaylist(final Uri playlistUri, final String playlistAsin, final String playlistName) {
        String str = TAG;
        Log.verbose(str, "Creating a device only playlist: " + playlistName);
        if (PlaylistUtil.isUniqueUdoPlaylistName(this.mContext, "cirrus-local", playlistName)) {
            if (DeviceOnlyPlaylist.create(this.mContext, playlistName) == null) {
                return false;
            }
            Log.verbose(str, "A Device Only Playlist is created: " + playlistName + ", uri: " + playlistUri);
            return true;
        }
        Log.verbose(str, "A device only playlist with the same name exists already: " + playlistName);
        return true;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    @VisibleForTesting
    Cursor getDownloadedPlaylistsCursor() {
        return this.mPreUnityPlaylistUtil.getDeviceSidePlaylistsCursor(this.mPreUnityDb);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Uri getPlaylistContentUri(final String playlistAsin, final String name) {
        return MediaProvider.UdoPlaylists.getContentUri(this.mContext, "cirrus-local", name);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistDownloaded(final PreUnityPlaylist preUnityPlaylist) {
        return isPlaylistDownloaded(CirrusDatabase.getReadOnlyDatabase(this.mContext), preUnityPlaylist.playlistName, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isLocalPlaylistCopyExists(final PreUnityPlaylist preUnityPlaylist) {
        return isPlaylistDownloaded(preUnityPlaylist);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.UdoPlaylistsMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist) {
        return isPlaylistDownloaded(preUnityPlaylist);
    }

    private MigrationStats migrateDeviceOnlyPlaylistsTracks(final PreUnityPlaylist preUnityPlaylist) throws SQLException {
        return new TracksCollectionMigrationHelper(this.mContext).migrateDownloadedTracks(this.mPreUnityPlaylistUtil.getDownloadedPlaylistsTracksCursor(this.mPreUnityDb, preUnityPlaylist.playlistId), this.mTrackMigrationHelper);
    }

    private List<Uri> addMissingTracks(final PreUnityPlaylist preUnityPlaylist, final Uri playlistUri) {
        ArrayList arrayList = new ArrayList();
        Set<String> udoPlaylistTracksLuids = PlaylistUtil.getUdoPlaylistTracksLuids(this.mContext, playlistUri);
        Cursor downloadedPlaylistsTracksCursor = this.mPreUnityPlaylistUtil.getDownloadedPlaylistsTracksCursor(this.mPreUnityDb, preUnityPlaylist.playlistId);
        try {
            if (!downloadedPlaylistsTracksCursor.moveToFirst()) {
                Log.verbose(TAG, "No tracks to migrate.");
                downloadedPlaylistsTracksCursor.close();
                return arrayList;
            }
            do {
                PreUnityTrack preUnityTrack = new PreUnityTrack(downloadedPlaylistsTracksCursor);
                Uri migratedTrackContentUri = this.mTrackMigrationHelper.getMigratedTrackContentUri(preUnityTrack);
                if (migratedTrackContentUri == null) {
                    Log.verbose(TAG, "Migrating track: " + preUnityTrack);
                    try {
                        migratedTrackContentUri = this.mTrackMigrationHelper.migrateTrack(preUnityTrack);
                    } catch (IOException e) {
                        Log.error(TAG, "Track migration failed: " + preUnityTrack + ", " + e.getMessage(), e);
                    }
                }
                if (migratedTrackContentUri != null) {
                    String trackLuid = TrackUtil.getTrackLuid(this.mContext, migratedTrackContentUri);
                    if (!udoPlaylistTracksLuids.contains(trackLuid)) {
                        Log.verbose(TAG, "Adding a missing track to playlist: " + playlistUri + ", " + migratedTrackContentUri);
                        arrayList.add(migratedTrackContentUri);
                    } else {
                        Log.verbose(TAG, "The playlist " + playlistUri + " has the track: " + trackLuid + ", " + migratedTrackContentUri);
                    }
                } else {
                    Log.verbose(TAG, "Could not migrate a track: " + preUnityTrack);
                }
            } while (downloadedPlaylistsTracksCursor.moveToNext());
            downloadedPlaylistsTracksCursor.close();
            return arrayList;
        } catch (Throwable th) {
            if (downloadedPlaylistsTracksCursor != null) {
                try {
                    downloadedPlaylistsTracksCursor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
