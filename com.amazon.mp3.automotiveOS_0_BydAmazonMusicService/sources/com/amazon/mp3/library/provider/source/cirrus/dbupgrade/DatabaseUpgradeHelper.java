package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.util.FileUtil;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class DatabaseUpgradeHelper {
    private final Context context;
    private final TrackMigrationHelper mTrackMigrationHelper;
    private SQLiteDatabase preUnityDb;

    public DatabaseUpgradeHelper(final Context context) {
        this.context = context;
        this.mTrackMigrationHelper = new TrackMigrationHelper(context);
    }

    public File backupDb(String path, String dbName, String dbNameBak) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        String str = File.separator;
        sb.append(str);
        sb.append(dbName);
        File file = new File(sb.toString());
        File file2 = new File(path + str + dbNameBak);
        FileUtil.copyFile(file, file2, false);
        return file2;
    }

    public DatabaseMigrationStats migrateDownloadedTracksAndPlaylists(final SQLiteDatabase preUnityDb) {
        this.preUnityDb = preUnityDb;
        DatabaseMigrationStats databaseMigrationStats = new DatabaseMigrationStats();
        databaseMigrationStats.setTracksMigrationStats(migrateTracks());
        databaseMigrationStats.setUdoPlaylistsStats(migrateUdoPlaylists());
        databaseMigrationStats.setPrimePlaylistsStats(migratePrimePlaylists());
        databaseMigrationStats.setSharedPlaylistsStats(migrateSharedPlaylists());
        databaseMigrationStats.setDeviceOnlyPlaylistsStats(migrateDeviceOnlyPlaylists());
        return databaseMigrationStats;
    }

    public void clearMigrationSharedPrefs(final Context context) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.remove("KEY_DB_TRACKS_MIGRATED");
        editorEdit.remove("KEY_DB_UDO_PLAYLISTS_MIGRATED");
        editorEdit.remove("KEY_DB_PRIME_PLAYLISTS_MIGRATED");
        editorEdit.remove("KEY_DB_SHARED_PLAYLISTS_MIGRATED");
        editorEdit.remove("KEY_DB_DEVICE_PLAYLISTS_MIGRATED");
        editorEdit.remove("KEY_DB_MIGRATION_RETRY");
        editorEdit.apply();
    }

    private PlaylistsMigrationStats migrateUdoPlaylists() {
        PlaylistsMigrationListener playlistsMigrationListener = new PlaylistsMigrationListener();
        migratePlaylists("KEY_DB_UDO_PLAYLISTS_MIGRATED", new UdoPlaylistsMigrationHelper(this.context, this.preUnityDb, playlistsMigrationListener));
        return playlistsMigrationListener.getPlaylistsMigrationStats();
    }

    private PlaylistsMigrationStats migratePrimePlaylists() {
        PlaylistsMigrationListener playlistsMigrationListener = new PlaylistsMigrationListener();
        migratePlaylists("KEY_DB_PRIME_PLAYLISTS_MIGRATED", new PrimePlaylistMigrationHelper(this.context, this.preUnityDb, playlistsMigrationListener));
        return playlistsMigrationListener.getPlaylistsMigrationStats();
    }

    private PlaylistsMigrationStats migrateSharedPlaylists() {
        PlaylistsMigrationListener playlistsMigrationListener = new PlaylistsMigrationListener();
        migratePlaylists("KEY_DB_SHARED_PLAYLISTS_MIGRATED", new SharedPlaylistsMigrationHelper(this.context, this.preUnityDb, playlistsMigrationListener));
        return playlistsMigrationListener.getPlaylistsMigrationStats();
    }

    private PlaylistsMigrationStats migrateDeviceOnlyPlaylists() {
        PlaylistsMigrationListener playlistsMigrationListener = new PlaylistsMigrationListener();
        migratePlaylists("KEY_DB_DEVICE_PLAYLISTS_MIGRATED", new DeviceOnlyPlaylistsMigrationHelper(this.context, this.preUnityDb, playlistsMigrationListener));
        return playlistsMigrationListener.getPlaylistsMigrationStats();
    }

    private boolean migratePlaylists(final String playlistMigrationFlagSharedPropertyKey, final PlaylistsMigrationHelper playlistsMigrationHelper) {
        boolean flag = getFlag(this.context, playlistMigrationFlagSharedPropertyKey);
        if (flag) {
            return flag;
        }
        boolean zMigratePlaylists = playlistsMigrationHelper.migratePlaylists();
        saveFlag(playlistMigrationFlagSharedPropertyKey, zMigratePlaylists);
        return zMigratePlaylists;
    }

    private MigrationStats migrateTracks() {
        MigrationStats migrationStats = new MigrationStats();
        if (getFlag(this.context, "KEY_DB_TRACKS_MIGRATED")) {
            return migrationStats;
        }
        MigrationStats migrationStatsMigrateNonPlaylistsTracks = migrateNonPlaylistsTracks(this.context, this.preUnityDb);
        saveFlag("KEY_DB_TRACKS_MIGRATED", migrationStatsMigrateNonPlaylistsTracks.isAllTracksMigrated());
        return migrationStatsMigrateNonPlaylistsTracks;
    }

    private boolean getFlag(final Context context, final String key) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        if (prefs.contains(key)) {
            return prefs.getBoolean(key, false);
        }
        return false;
    }

    private void saveFlag(final String key, boolean value) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.context).edit();
        editorEdit.putBoolean(key, value);
        editorEdit.apply();
    }

    public void incrementRetryCount() {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.context).edit();
        editorEdit.putInt("KEY_DB_MIGRATION_RETRY", SettingsUtil.getPrefs(this.context).getInt("KEY_DB_MIGRATION_RETRY", 0) + 1);
        editorEdit.apply();
    }

    public int getRetryCount() {
        return SettingsUtil.getPrefs(this.context).getInt("KEY_DB_MIGRATION_RETRY", 0);
    }

    private MigrationStats migrateNonPlaylistsTracks(final Context context, final SQLiteDatabase preUnityDb) throws SQLException {
        return new TracksCollectionMigrationHelper(context).migrateDownloadedTracks(new PreUnityPlaylistUtil().getDownloadedNonPlaylistTracksCursor(preUnityDb), this.mTrackMigrationHelper);
    }
}
