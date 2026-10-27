package com.amazon.mp3.playlist.db;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistsCacheTimesTable {
    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void createPrimePlaylistsCacheTimesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimePlaylistsCacheTimes ( asin TEXT UNIQUE NOT NULL, timestamp INTEGER DEFAULT 0 NOT NULL);");
    }

    public static void resetPrimePlaylistsCacheTimeTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS PrimePlaylistsCacheTimes");
        createPrimePlaylistsCacheTimesTable(db);
    }
}
