package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeArtistTracksTable {
    public static void createPrimeArtistTracksTable(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimeArtistTracks ( _id INTEGER PRIMARY KEY AUTOINCREMENT, track_asin TEXT, track_luid TEXT, prime_artist_asin TEXT, prime_artist_id TEXT )");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public static void deletePrimeArtistTracksTable(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("DROP TABLE IF EXISTS PrimeArtistTracks");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 151) {
            deletePrimeArtistTracksTable(db);
            createPrimeArtistTracksTable(db);
        }
    }
}
