package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

/* JADX INFO: loaded from: classes3.dex */
public interface MigrationListener {
    void addTracksMigrationStats(final PreUnityPlaylist preUnityPlaylist, final MigrationStats tracksMigrationStats);

    void onMigrationFailed(String reason);

    void onPlaylistAlreadyMigrated(final PreUnityPlaylist preUnityPlaylist);

    void onPlaylistMigrated(final PreUnityPlaylist preUnityPlaylist);

    void onPlaylistMigrationFailed(final PreUnityPlaylist preUnityPlaylist, String reason);

    void onPlaylistMigrationStarted(final PreUnityPlaylist preUnityPlaylist);

    void setTotalPlaylistsToMigrate(int numberOfPlaylists);
}
