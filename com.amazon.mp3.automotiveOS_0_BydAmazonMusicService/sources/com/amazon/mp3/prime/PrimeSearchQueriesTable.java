package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeSearchQueriesTable {
    public static void createPrimeSearchQueriesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimeSearchQueries ( query TEXT NOT NULL, timestamp INTEGER DEFAULT 0 NOT NULL, UNIQUE (query) ON CONFLICT REPLACE );");
    }

    public static void resetPrimeSearchQueriesTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS PrimeSearchQueries");
        createPrimeSearchQueriesTable(db);
    }
}
