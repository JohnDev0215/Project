package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistBrowseCleanOperation extends SyncOperation {
    PlaylistBrowseCleanOperation(Context context, SQLiteDatabase db) {
        super(context, db);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() {
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3_browsePlaylists", 0);
        long j = sharedPreferences.getLong("last_browse_playlist_clear", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (j < jCurrentTimeMillis - 3600000) {
            new PrimePlaylistDatabaseManager(context).removeExpiringBrowsePlaylists();
            PrimePlaylistUtil.deleteUnreferencedPrimePlaylistTracks(context);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putLong("last_browse_playlist_clear", jCurrentTimeMillis);
            editorEdit.apply();
        }
        return 0;
    }
}
