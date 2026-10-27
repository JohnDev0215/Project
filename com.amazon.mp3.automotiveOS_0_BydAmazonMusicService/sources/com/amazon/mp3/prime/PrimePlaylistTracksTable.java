package com.amazon.mp3.prime;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.db.ColumnType;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.extensions.SQLiteDatabaseExtensions;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylistTracksTable {

    public enum AssetType {
        VIDEO("VIDEO"),
        AUDIO("AUDIO");

        private String assetType;

        AssetType(final String assetType) {
            this.assetType = assetType;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.assetType;
        }

        public static boolean containsType(String type) {
            for (AssetType assetType : values()) {
                if (assetType.toString().equals(type)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void createPrimePlaylistTracksTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimePlaylistTracks ( _id INTEGER PRIMARY KEY AUTOINCREMENT, track_num INTEGER, asin TEXT UNIQUE, title TEXT, sort_title TEXT, artist TEXT, artist_asin TEXT, album TEXT, album_asin TEXT, album_artist TEXT, album_artist_asin TEXT, genre TEXT, album_art_url_thumbnail TEXT, album_art_url_large TEXT, duration INTEGER DEFAULT -1, lyrics_state TEXT, download_state INTEGER DEFAULT 5, match_hash INTEGER DEFAULT -1, catalog_status INTEGER DEFAULT " + ContentCatalogStatus.PRIME.getValue() + ", fileSizeKilobytes INTEGER DEFAULT 0 , artwork_id INTEGER DEFAULT 0 ,track_source INTEGER DEFAULT 0,is_explicit INTEGER DEFAULT 0 , content_encoding TEXT, asset_qualities TEXT, asset_type TEXT, is_exclusion_tagged INTEGER DEFAULT 0, playmode_eligibility TEXT, catalog_status_tiers INTEGER DEFAULT 0 );");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion > 28) {
            if (oldVersion < 30) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "fileSizeKilobytes", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 34) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "artwork_id", ColumnType.IntegerType.INSTANCE, 0);
                updateArtworkIdForExistingTracks(db);
            }
            if (oldVersion < 34) {
                db.execSQL("DELETE FROM PrimePlaylistTracks WHERE asin LIKE 'local-%'");
                db.execSQL("UPDATE PrimePlaylistTracks SET download_state = 5");
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "track_source", ColumnType.IntegerType.INSTANCE, "0");
            }
            if (oldVersion < 45) {
                db.execSQL("ALTER TABLE PrimePlaylistTracks RENAME TO PrimePlaylistTracks_tmp");
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_prime", Integer.valueOf(ContentCatalogStatus.PREVIOUSLY_CATALOG.getValue()));
                db.update("PrimePlaylistTracks_tmp", contentValues, "is_prime=?", new String[]{"0"});
                contentValues.put("is_prime", Integer.valueOf(ContentCatalogStatus.PRIME.getValue()));
                db.update("PrimePlaylistTracks_tmp", contentValues, "is_prime=?", new String[]{"1"});
                createPrimePlaylistTracksTable(db);
                db.execSQL("INSERT INTO PrimePlaylistTracks (_id,track_num,asin,title,sort_title,artist,artist_asin,album,album_asin,album_artist,album_artist_asin,genre,album_art_url_thumbnail,album_art_url_large,duration,lyrics_state,download_state,match_hash,fileSizeKilobytes,artwork_id,track_source, catalog_status) SELECT _id,track_num,asin,title,sort_title,artist,artist_asin,album,album_asin,album_artist,album_artist_asin,genre,album_art_url_thumbnail,album_art_url_large,duration,lyrics_state,download_state,match_hash,fileSizeKilobytes,artwork_id,track_source, is_prime FROM PrimePlaylistTracks_tmp");
                db.execSQL("DROP TABLE IF EXISTS PrimePlaylistTracks_tmp");
            }
            if (oldVersion < 121) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "is_explicit", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 155) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "content_encoding", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 163) {
                ColumnType.TextType textType = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "asset_qualities", textType);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "asset_type", textType);
            }
            if (oldVersion < 164) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "is_exclusion_tagged", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 168) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "is_exclusion_tagged", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 169) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "playmode_eligibility", ColumnType.TextType.INSTANCE);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylistTracks", "catalog_status_tiers", ColumnType.IntegerType.INSTANCE, 0);
            }
        }
    }

    private static void updateArtworkIdForExistingTracks(SQLiteDatabase db) {
        String[] strArr = {"asin"};
        db.beginTransaction();
        Cursor cursorQuery = null;
        try {
            cursorQuery = db.query("PrimePlaylistTracks", strArr, null, null, null, null, null);
            int columnIndex = cursorQuery.getColumnIndex("asin");
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex);
                int iHashCode = string.hashCode();
                ContentValues contentValues = new ContentValues();
                contentValues.put("artwork_id", Integer.valueOf(iHashCode));
                db.update("PrimePlaylistTracks", contentValues, "asin=?", new String[]{string});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static void resetPrimePlaylistTracksTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS PrimePlaylistTracks");
        createPrimePlaylistTracksTable(db);
    }
}
