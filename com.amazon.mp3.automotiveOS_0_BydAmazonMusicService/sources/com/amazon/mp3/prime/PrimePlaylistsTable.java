package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.db.ColumnType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.PlaylistSyncOperation;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.SQLiteDatabaseExtensions;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylistsTable {
    private static final String PLAYLIST_VISIBILITY_COLUMN = "visibility INTEGER DEFAULT " + MediaProvider.Playlists.VisibilityState.PRIVATE.getInt();
    private static String TAG = PrimePlaylistsTable.class.getSimpleName();

    public static void createPrimePlaylistsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PrimePlaylists ( _id INTEGER PRIMARY KEY AUTOINCREMENT, luid TEXT UNIQUE, asin TEXT, title TEXT, source INTEGER DEFAULT 0, download_state INTEGER DEFAULT 5, updatedDate INTEGER DEFAULT 0, is_following INTEGER DEFAULT 0, description TEXT, is_new INTEGER DEFAULT 0, version TEXT, track_count INTEGER DEFAULT 0, art_url TEXT, thumbnail_art_url TEXT, marketplace_id TEXT, curated_by TEXT, duration INTEGER DEFAULT 0, is_prime INTEGER DEFAULT 1, is_shared_user_playlist INTEGER DEFAULT 0, is_nightwing INTEGER DEFAULT 0, is_nightwing_on_demand INTEGER DEFAULT 0, profile_id TEXT , is_entity_playlist INTEGER DEFAULT 0, is_sonic_rush INTEGER DEFAULT 0, is_sonic_rush_on_demand INTEGER DEFAULT 0, is_sonic_rush_golden INTEGER DEFAULT 0, playmode_eligibility TEXT , created_date NUMBER DEFAULT 0, last_modified_date NUMBER DEFAULT 0, " + PLAYLIST_VISIBILITY_COLUMN + ", number_of_followers INTEGER DEFAULT 0, UNIQUE(luid) ON CONFLICT REPLACE);");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion > 28) {
            if (oldVersion < 33) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "curated_by", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 34) {
                db.execSQL("DELETE FROM PrimePlaylists WHERE source = 1");
                db.execSQL("UPDATE PrimePlaylists SET download_state = 5");
            }
            if (oldVersion < 45) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_prime", ColumnType.IntegerType.INSTANCE, "1");
            }
            if (oldVersion < 47) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_shared_user_playlist", ColumnType.IntegerType.INSTANCE, "0");
            }
            if (oldVersion < 154) {
                try {
                    db.execSQL("ALTER TABLE PrimePlaylists RENAME TO PrimePlaylistsOld");
                } catch (SQLiteException e) {
                    Log.warning(TAG, "PrimePlaylistsOld doesn't exist", e);
                }
                db.execSQL("CREATE TABLE IF NOT EXISTS PrimePlaylists ( _id INTEGER PRIMARY KEY AUTOINCREMENT, luid TEXT UNIQUE, asin TEXT, title TEXT, source INTEGER DEFAULT 0, download_state INTEGER DEFAULT 5, updatedDate INTEGER DEFAULT 0, is_following INTEGER DEFAULT 0, description TEXT, is_new INTEGER DEFAULT 0, version TEXT, track_count INTEGER DEFAULT 0, art_url TEXT, thumbnail_art_url TEXT, marketplace_id TEXT, curated_by TEXT, duration INTEGER DEFAULT 0, is_prime INTEGER DEFAULT 1, is_shared_user_playlist INTEGER DEFAULT 0, UNIQUE(luid) ON CONFLICT REPLACE)");
                try {
                    db.execSQL("INSERT INTO PrimePlaylists SELECT * FROM PrimePlaylistsOld");
                    db.execSQL("DROP TABLE PrimePlaylistsOld");
                } catch (SQLiteException e2) {
                    Log.warning(TAG, "PrimePlaylistsOld doesn't exist", e2);
                }
            }
            if (oldVersion < 156) {
                ColumnType.IntegerType integerType = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_nightwing", integerType, 0);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_nightwing_on_demand", integerType, 0);
            }
            if (oldVersion < 159) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "profile_id", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 167) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_entity_playlist", ColumnType.IntegerType.INSTANCE, "0");
            }
            if (oldVersion < 169) {
                ColumnType.IntegerType integerType2 = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_sonic_rush", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_sonic_rush_on_demand", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "is_sonic_rush_golden", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "playmode_eligibility", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 171) {
                ColumnType.NumberType numberType = ColumnType.NumberType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "created_date", numberType, 0);
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "last_modified_date", numberType, 0);
            }
            if (oldVersion < 172) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "visibility", ColumnType.IntegerType.INSTANCE, Integer.valueOf(MediaProvider.Playlists.VisibilityState.PRIVATE.getInt()));
            }
            if (oldVersion < 173) {
                SQLiteDatabaseExtensions.addColumn(db, "PrimePlaylists", "number_of_followers", ColumnType.IntegerType.INSTANCE, 0);
                PlaylistSyncOperation.setShouldPerformSync(AmazonApplication.getApplication(), true);
            }
        }
    }

    public static void resetPrimePlaylistsTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS PrimePlaylists");
        createPrimePlaylistsTable(db);
    }

    public static String translateSource(int primeSource) {
        if (primeSource == 0) {
            return "cirrus";
        }
        if (primeSource == 1) {
            return "cirrus-local";
        }
        throw new IllegalArgumentException("Unknown Source Type");
    }

    public static int translateSource(String cirrusSource) {
        if ("cirrus".equals(cirrusSource)) {
            return 0;
        }
        if ("cirrus-local".equals(cirrusSource)) {
            return 1;
        }
        throw new IllegalArgumentException("Unknown Source Type");
    }
}
