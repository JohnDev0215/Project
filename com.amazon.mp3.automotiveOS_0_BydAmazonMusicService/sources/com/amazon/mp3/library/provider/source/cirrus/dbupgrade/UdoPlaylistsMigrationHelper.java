package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.util.PlaylistUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class UdoPlaylistsMigrationHelper extends PlaylistsMigrationHelper {
    private static final String TAG = "UdoPlaylistsMigrationHelper";

    public UdoPlaylistsMigrationHelper(final Context context, final SQLiteDatabase preUnityDb, final MigrationListener playlistsMigrationListener) {
        super(context, preUnityDb, playlistsMigrationListener);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean createLocalPlaylist(final Uri playlistUri, final String playlistAsin, final String name) {
        UdoPlaylistDownloadHelper udoPlaylistDownloadHelper = new UdoPlaylistDownloadHelper(this.mContext);
        udoPlaylistDownloadHelper.updateDownloadState(playlistUri, 0);
        udoPlaylistDownloadHelper.createLocalPlaylist(playlistUri);
        return true;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    @VisibleForTesting
    Cursor getDownloadedPlaylistsCursor() {
        return this.mPreUnityPlaylistUtil.getDownloadedUdoPlaylistsCursor(this.mPreUnityDb);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Uri getPlaylistContentUri(final String playlistAsin, final String name) {
        return MediaProvider.UdoPlaylists.getContentUri(this.mContext, "cirrus", name);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistDownloaded(final PreUnityPlaylist preUnityPlaylist) {
        return isPlaylistDownloaded(CirrusDatabase.getReadOnlyDatabase(this.mContext), preUnityPlaylist.playlistName, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isLocalPlaylistCopyExists(final PreUnityPlaylist preUnityPlaylist) {
        return MediaProvider.UdoPlaylists.getContentUri(this.mContext, "cirrus-local", preUnityPlaylist.playlistName) != null;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist) {
        return isLocalPlaylistCopyExists(preUnityPlaylist) && PlaylistUtil.allCloudPlaylistTracksDownloaded(MediaProvider.UdoPlaylistTracks.getContentUri("cirrus", MediaProvider.Playlists.getPlaylistId(getPlaylistContentUri(null, preUnityPlaylist.playlistName))));
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    void updatePlaylistWithMigratedTracks(final PreUnityPlaylist preUnityPlaylist, final Uri unityPlaylistUri, final List<Uri> migratedTrackUris) {
        String str = TAG;
        Log.v(str, "updatePlaylistWithMigratedTracks: " + unityPlaylistUri + ", " + migratedTrackUris);
        if (!MediaProvider.UdoPlaylists.isUdoPlaylist(unityPlaylistUri)) {
            Log.d(str, "Skipping adding tracks to a non Udo playlist: " + unityPlaylistUri);
            return;
        }
        addTracksToPlaylist(migratedTrackUris, MediaProvider.UdoPlaylists.getContentUri(this.mContext, "cirrus-local", preUnityPlaylist.playlistName));
        if (isPlaylistMigrated(preUnityPlaylist)) {
            new UdoPlaylistDownloadHelper(this.mContext).updateDownloadState(getPlaylistContentUri(null, preUnityPlaylist.playlistName), 0);
        }
    }

    void addTracksToPlaylist(final List<Uri> migratedTracksContentUris, final Uri playlistUri) {
        long playlistId = MediaProvider.Playlists.getPlaylistId(playlistUri);
        String source = MediaProvider.getSource(playlistUri);
        CirrusPlaylistScratch.delete(this.mContext, playlistId);
        CirrusPlaylistScratch.create(this.mContext, playlistId);
        int i = 0;
        for (Uri uri : migratedTracksContentUris) {
            if (!MediaProvider.Tracks.isValidTrackUri(uri) || MediaProvider.Tracks.isPrimeAdditionalTrack(uri)) {
                Log.v(TAG, "The track uri is not supported for UDO playlists: " + uri + ", " + playlistUri);
            } else if (PlaylistUtil.playlistContainsTrack(this.mContext, playlistUri, uri)) {
                Log.v(TAG, "The playlist contains this track already: " + uri + ", " + playlistUri);
            } else {
                String str = TAG;
                Log.v(str, "inserting udo playlist track: " + source + ", playlistId: " + playlistId + ", trackContentUri: " + uri);
                if (PlaylistUtil.insertUdoPlaylistTracks(this.mContext, source, playlistId, uri, null) > 0) {
                    Log.v(str, "The track is added: " + uri + " to a playlist: " + playlistUri);
                    i++;
                } else {
                    Log.v(str, "The track is not added: " + uri + " to a playlist " + playlistUri);
                }
            }
        }
        if (i > 0) {
            CirrusPlaylistScratch.postToDb(this.mContext, playlistId, source);
        } else {
            CirrusPlaylistScratch.delete(this.mContext, playlistId);
        }
    }
}
