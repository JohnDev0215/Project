package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylistToTrackTable {
    public static void createPrimePlaylistToTrackTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimePlaylistToTrack ( playlist_id INTEGER, track_asin TEXT, order_num INTEGER, is_new INTEGER DEFAULT 0, FOREIGN KEY(playlist_id) REFERENCES PrimePlaylists (_id), FOREIGN KEY(track_asin) REFERENCES PrimePlaylistTracks (asin));");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= 33 || oldVersion <= 28) {
            return;
        }
        db.execSQL("DELETE FROM PrimePlaylistToTrack WHERE track_asin LIKE 'local-%'");
    }

    public static void resetPrimePlaylistToTrackTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS PrimePlaylistToTrack");
        createPrimePlaylistToTrackTable(db);
    }
}
