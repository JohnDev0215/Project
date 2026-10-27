package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.download.helper.PrimePlaylistDownloadHelper;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
class PrimePlaylistMigrationHelper extends PlaylistsMigrationHelper {
    private static final String TAG = "PrimePlaylistMigrationHelper";
    private TrackMigrator mCatalogPlaylistTrackMigrationHelper;

    public PrimePlaylistMigrationHelper(final Context context, final SQLiteDatabase preUnityDb, final MigrationListener migrationListener) {
        super(context, preUnityDb, migrationListener);
        this.mCatalogPlaylistTrackMigrationHelper = new CatalogPlaylistTrackMigrationHelper(context);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean createLocalPlaylist(final Uri playlistUri, final String playlistAsin, final String playlistName) {
        PrimePlaylistDownloadHelper primePlaylistDownloadHelper = new PrimePlaylistDownloadHelper(this.mContext);
        String str = TAG;
        Log.verbose(str, "Creating a local playlist: " + playlistAsin + ", " + playlistName + ", " + playlistUri);
        primePlaylistDownloadHelper.updateDownloadState(playlistUri, 0);
        primePlaylistDownloadHelper.createLocalPlaylist(playlistUri);
        Log.verbose(str, "A local playlist is created: " + playlistName + ", " + playlistUri);
        return true;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Uri getPlaylistContentUri(final String playlistAsin, final String name) {
        return MediaProvider.PrimePlaylists.getContentUri("cirrus", playlistAsin);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    Cursor getDownloadedPlaylistsCursor() {
        return this.mPreUnityPlaylistUtil.getDownloadedPrimePlaylistsCursor(this.mPreUnityDb);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistDownloaded(final PreUnityPlaylist preUnityPlaylist) {
        return PlaylistUtil.isCatalogPlaylistDownloaded(CirrusDatabase.getReadOnlyDatabase(this.mContext), preUnityPlaylist.playlistAsin);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    MigrationStats migratePlaylistTracks(final PreUnityPlaylist preUnityPlaylist, final Uri playlistUri) {
        TracksCollectionMigrationHelper tracksCollectionMigrationHelper = new TracksCollectionMigrationHelper(this.mContext);
        String str = TAG;
        Log.verbose(str, "Migrating tracks for a pre-Unity playlist: " + preUnityPlaylist.playlistId + ", asin: " + preUnityPlaylist.playlistAsin + ", name: " + preUnityPlaylist.playlistName);
        MigrationStats migrationStatsMigrateDownloadedTracks = tracksCollectionMigrationHelper.migrateDownloadedTracks(this.mPreUnityPlaylistUtil.getDownloadedPlaylistsTracksCursor(this.mPreUnityDb, preUnityPlaylist.playlistId), this.mCatalogPlaylistTrackMigrationHelper);
        StringBuilder sb = new StringBuilder();
        sb.append("Prime Playlist tracks migration stats: ");
        sb.append(preUnityPlaylist);
        sb.append(", ");
        sb.append(migrationStatsMigrateDownloadedTracks);
        Log.verbose(str, sb.toString());
        return migrationStatsMigrateDownloadedTracks;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PlaylistsMigrationHelper
    boolean isPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist) {
        return PlaylistUtil.isCatalogPlaylistDownloaded(CirrusDatabase.getReadOnlyDatabase(this.mContext), preUnityPlaylist.playlistAsin);
    }
}
