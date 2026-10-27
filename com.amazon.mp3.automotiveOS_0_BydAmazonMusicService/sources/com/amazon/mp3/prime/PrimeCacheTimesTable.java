package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.prime.PrimeTracksCache;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeCacheTimesTable {
    private static final int DEFAULT_PRIME_UI_STATE = PrimeTracksCache.PrimeUIState.HIDE.getValue();

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void createPrimeCacheTimeTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CacheTimes ( asin TEXT UNIQUE NOT NULL, timestamp INTEGER DEFAULT 0 NOT NULL, ui_state INTEGER DEFAULT 0 NOT NULL, artwork_url TEXT, has_prime_content INTEGER DEFAULT " + DEFAULT_PRIME_UI_STATE + " NOT NULL, track_asins TEXT);");
    }

    public static void resetPrimeCacheTimeTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS CacheTimes");
        createPrimeCacheTimeTable(db);
    }
}
