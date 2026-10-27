package com.amazon.mp3.playlist;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaylistConfigurationStorage {
    private static PlaylistConfigurationStorage sInstance;
    private final Context mContext;

    public int getMaximumNumberOfTracksInPlaylist() {
        return 2500;
    }

    private static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new PlaylistConfigurationStorage(context);
        }
    }

    public static PlaylistConfigurationStorage get(final Context context) {
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    public static PlaylistConfigurationStorage get() {
        if (sInstance == null) {
            createInstance(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private PlaylistConfigurationStorage() {
        this(AmazonApplication.getContext());
    }

    private PlaylistConfigurationStorage(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    private SharedPreferences getSharedPreference() {
        return this.mContext.getSharedPreferences("com.amazon.mp3_PlaylistConfiguration", 4);
    }

    public String getPlaylistCheckpoint() {
        return getSharedPreference().getString(this.mContext.getString(R.string.setting_key_playlist_checkpoint), null);
    }

    public void removePlaylistCheckpoint() {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.remove(this.mContext.getString(R.string.setting_key_playlist_checkpoint));
        editorEdit.apply();
    }

    public void setPlaylistCheckpoint(String playlistCheckpoint) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putString(this.mContext.getString(R.string.setting_key_playlist_checkpoint), playlistCheckpoint);
        editorEdit.apply();
    }

    public void setConfigurationsCheckTime() {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putLong(this.mContext.getString(R.string.setting_key_playlist_configurations_check_time), System.currentTimeMillis());
        editorEdit.apply();
    }

    public long getConfigurationsCheckTime() {
        return getSharedPreference().getLong(this.mContext.getString(R.string.setting_key_playlist_configurations_check_time), 0L);
    }

    public int getMaximumNumberOfPlaylistsAllowed() {
        return getSharedPreference().getInt(this.mContext.getString(R.string.setting_key_playlist_max_playlists_allowed), 100);
    }

    public void setMaximumNumberOfPlaylistsAllowed(int limit) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putInt(this.mContext.getString(R.string.setting_key_playlist_max_playlists_allowed), limit);
        editorEdit.apply();
    }

    public int getMaximumNumberOfPlaylistsPerRequest() {
        return getSharedPreference().getInt(this.mContext.getString(R.string.setting_key_playlist_max_playlists_per_request), 100);
    }

    public void setMaximumNumberOfPlaylistsPerRequest(int limit) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putInt(this.mContext.getString(R.string.setting_key_playlist_max_playlists_per_request), limit);
        editorEdit.apply();
    }

    public void setMaximumNumberOfTracksInPlaylist(int limit) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putInt(this.mContext.getString(R.string.setting_key_playlist_max_tracks_in_playlist), limit);
        editorEdit.apply();
    }

    public int getMaximumNumberOfTracksPerRequest() {
        return getSharedPreference().getInt(this.mContext.getString(R.string.setting_key_playlist_max_tracks_per_request), 100);
    }

    public void setMaximumNumberOfTracksPerRequest(int limit) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putInt(this.mContext.getString(R.string.setting_key_playlist_max_tracks_per_request), limit);
        editorEdit.apply();
    }

    public int getMaximumPlaylistTitleLength() {
        return getSharedPreference().getInt(this.mContext.getString(R.string.setting_key_playlist_max_playlist_title_length), 100);
    }

    public void setMaximumPlaylistTitleLength(int limit) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putInt(this.mContext.getString(R.string.setting_key_playlist_max_playlist_title_length), limit);
        editorEdit.apply();
    }

    public boolean shouldMigrateAndUnifyDevicePlaylists() {
        return getSharedPreference().getBoolean(this.mContext.getString(R.string.setting_key_playlist_migrate_and_unify_device_playlists), false);
    }

    public void setMigrateAndUnifyDevicePlaylists(boolean shouldMigrateAndUnify) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putBoolean(this.mContext.getString(R.string.setting_key_playlist_migrate_and_unify_device_playlists), shouldMigrateAndUnify);
        editorEdit.apply();
    }
}
