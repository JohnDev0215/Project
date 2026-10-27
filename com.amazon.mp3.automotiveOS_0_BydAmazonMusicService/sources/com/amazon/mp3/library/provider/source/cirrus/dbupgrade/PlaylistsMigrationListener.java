package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistsMigrationListener implements MigrationListener {
    private static final String TAG = "PlaylistsMigrationListener";
    private PlaylistsMigrationStats playlistsMigrationStats = new PlaylistsMigrationStats();

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void setTotalPlaylistsToMigrate(final int numberOfPlaylists) {
        Log.verbose(TAG, "number of playlists to migrate: " + numberOfPlaylists);
        this.playlistsMigrationStats.setNumberOfPlaylistsToMigrate(numberOfPlaylists);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void onPlaylistMigrationStarted(final PreUnityPlaylist preUnityPlaylist) {
        Log.verbose(TAG, "Migrating a playlist: " + preUnityPlaylist);
        this.playlistsMigrationStats.startMigrationStopWatch(preUnityPlaylist);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void onPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist) {
        Log.verbose(TAG, "Playlist is migrated: " + preUnityPlaylist.playlistName);
        this.playlistsMigrationStats.stopMigrationStopWatch(preUnityPlaylist);
        this.playlistsMigrationStats.playlistMigrationCompleted(preUnityPlaylist);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void onPlaylistAlreadyMigrated(final PreUnityPlaylist preUnityPlaylist) {
        Log.verbose(TAG, "The local playlist " + preUnityPlaylist + " is downloaded already.");
        this.playlistsMigrationStats.stopMigrationStopWatch(preUnityPlaylist);
        this.playlistsMigrationStats.playlistMigrationCompleted(preUnityPlaylist);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void onPlaylistMigrationFailed(final PreUnityPlaylist preUnityPlaylist, final String reason) {
        Log.warning(TAG, "Playlist did not migrate: " + preUnityPlaylist.playlistName + ", " + reason);
        this.playlistsMigrationStats.stopMigrationStopWatch(preUnityPlaylist);
        this.playlistsMigrationStats.playlistMigrationFailed(preUnityPlaylist, reason);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void addTracksMigrationStats(final PreUnityPlaylist preUnityPlaylist, final MigrationStats tracksMigrationStats) {
        this.playlistsMigrationStats.addTracksMigrationStats(preUnityPlaylist, tracksMigrationStats);
    }

    public PlaylistsMigrationStats getPlaylistsMigrationStats() {
        return this.playlistsMigrationStats;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.MigrationListener
    public void onMigrationFailed(final String reason) {
        Log.error(TAG, "Migration failed: " + reason);
        this.playlistsMigrationStats.migrationFailed(reason);
    }
}
