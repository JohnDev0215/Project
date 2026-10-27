package com.amazon.mp3.lyrics;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes3.dex */
class LyricsStorage {
    private static String LAST_FULL_DOWNLOAD_LOCAL_TIME_KEY = "last_full_download_local_time";
    private static String LAST_TAKEDOWN_COMPLETED_TIMESTAMP_KEY = "last_takedown_completed_timestamp";
    private static String LAST_TAKEDOWN_LOCAL_TIME_KEY = "last_takedown_local_time";
    private static String LAST_UPDATE_COMPLETED_TIMESTAMP_KEY = "last_update_completed_timestamp";
    private static String LAST_UPDATE_LOCAL_TIME_KEY = "last_update_local_time";
    private static String SHARED_PREF_NAME = "com.amazon.mp3_lyrics";
    private SharedPreferences mSharedPreferences;

    public LyricsStorage(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    public long getLastTakedownLocalTimeInMillis() {
        return this.mSharedPreferences.getLong(LAST_TAKEDOWN_LOCAL_TIME_KEY, 0L);
    }

    public void setLastTakedownLocalTimeInMillis(long timeInMillis) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong(LAST_TAKEDOWN_LOCAL_TIME_KEY, timeInMillis);
        editorEdit.apply();
    }

    public long getLastRefreshLocalTimeInMillis() {
        return this.mSharedPreferences.getLong(LAST_UPDATE_LOCAL_TIME_KEY, 0L);
    }

    public void setLastRefreshLocalTimeInMillis(long timeInMillis) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong(LAST_UPDATE_LOCAL_TIME_KEY, timeInMillis);
        editorEdit.apply();
    }

    public long getLastFullDownloadLocalTimeInMillis() {
        return this.mSharedPreferences.getLong(LAST_FULL_DOWNLOAD_LOCAL_TIME_KEY, 0L);
    }

    public void setLastFullDownloadLocalTimeInMillis(long timeInMillis) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong(LAST_FULL_DOWNLOAD_LOCAL_TIME_KEY, timeInMillis);
        editorEdit.apply();
    }

    public long getTakeDownsCompletedTimestamp() {
        return this.mSharedPreferences.getLong(LAST_TAKEDOWN_COMPLETED_TIMESTAMP_KEY, 0L);
    }

    public void setTakeDownsCompletedTimestamp(long timeInSeconds) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong(LAST_TAKEDOWN_COMPLETED_TIMESTAMP_KEY, timeInSeconds);
        editorEdit.apply();
    }

    public long getUpdatesCompletedTimestamp() {
        return this.mSharedPreferences.getLong(LAST_UPDATE_COMPLETED_TIMESTAMP_KEY, 0L);
    }

    public void setUpdatesCompletedTimestamp(long timeInSeconds) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong(LAST_UPDATE_COMPLETED_TIMESTAMP_KEY, timeInSeconds);
        editorEdit.apply();
    }
}
