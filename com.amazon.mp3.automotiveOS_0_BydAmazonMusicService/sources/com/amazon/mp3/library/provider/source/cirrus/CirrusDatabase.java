package com.amazon.mp3.library.provider.source.cirrus;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTrackerDAO;
import com.amazon.mp3.library.db.ColumnType;
import com.amazon.mp3.library.item.TrackStatus;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.asinupdate.TracksToUpdateDAO;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.PreUnityCirrusMediaSourceDb;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueue;
import com.amazon.mp3.library.service.sync.CatalogStatusTiersOperation;
import com.amazon.mp3.library.service.sync.PlaylistSyncOperation;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.db.PrimePlaylistsCacheTimesTable;
import com.amazon.mp3.prime.PrimeArtistTracksTable;
import com.amazon.mp3.prime.PrimeCacheTimesTable;
import com.amazon.mp3.prime.PrimePlaylistToTrackTable;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.PrimeSearchQueriesTable;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.SQLiteOpenHelper;
import com.amazon.mp3.util.ThreadUtil;
import com.amazon.mp3.util.extensions.SQLiteDatabaseExtensions;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusDatabase {
    public static final int SCHEMA_VERSION = getCurrentSchemaVersion();
    private static final String TAG = CirrusDatabase.class.getSimpleName();
    private static SQLiteDatabase sInstance;
    private static SQLiteDatabase sReadOnlyInstance;

    private static int getCurrentSchemaVersion() {
        return 174;
    }

    public static abstract class Tracks extends MediaProvider.Tracks {
        public static void createTable(SQLiteDatabase db, String tableName) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, luid TEXT UNIQUE, asin TEXT, album_asin TEXT, artist_asin TEXT, title TEXT, sort_title TEXT, album TEXT, sort_album TEXT, album_id INTEGER, album_art_id INTEGER, album_artist TEXT, sort_album_artist TEXT, album_artist_id INTEGER, artist TEXT, sort_artist TEXT, artist_id INTEGER, track_num INTEGER, disc_num INTEGER, exists_flag INTEGER, remote_uri TEXT, order_id TEXT, date_created NUMBER, date_updated NUMBER, date_purchased NUMBER, purchased INTEGER, uploaded INTEGER, extension TEXT, size INTEGER DEFAULT 0, duration INTEGER DEFAULT -1, bookmark INTEGER DEFAULT 0, is_podcast INTEGER DEFAULT 0, version TEXT, match_hash INTEGER DEFAULT -1, " + IntlConfiguration.MARKETPLACE + " TEXT, lyrics_state TEXT, " + NotificationCompat.CATEGORY_STATUS + " TEXT, " + PrimeTracksCache.getCreatePrimeColumnStatement() + ", " + PrimeTracksCache.getCreateOwnershipColumnStatement() + ", " + ParserUtil.GENRE_SEGMENT_NAME + " TEXT, genre_id INTEGER DEFAULT 0," + PageUriUtils.SOURCE_QUERY_PARAM + " INTEGER DEFAULT 0, download_state INTEGER DEFAULT 5, luid_hash INTEGER DEFAULT -1 , is_explicit INTEGER DEFAULT 0 , popularity DECIMAL DEFAULT -1 , content_encoding TEXT , playlist_track_status INTEGER DEFAULT 0 , asset_qualities TEXT , asset_type TEXT , is_exclusion_tagged INTEGER DEFAULT 0 , playmode_eligibility TEXT , catalog_status_tiers INTEGER DEFAULT 0 , album_playmode_eligibility TEXT );");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            Log.verbose(CirrusDatabase.TAG, "Tracks: doUpgrade: " + oldVersion + ", current version: " + CirrusDatabase.SCHEMA_VERSION);
            if (oldVersion < 2) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "date_purchased", ColumnType.NumberType.INSTANCE);
                ColumnType.IntegerType integerType = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", "purchased", integerType);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "uploaded", integerType);
                SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
                editorEdit.putLong(context.getString(R.string.setting_key_sync_last_updated_time), 0L);
                editorEdit.apply();
            }
            if (oldVersion < 4) {
                ColumnType.IntegerType integerType2 = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", PageUriUtils.SOURCE_QUERY_PARAM, integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "match_hash", integerType2, -1);
            }
            if (oldVersion < 5) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "download_state", ColumnType.IntegerType.INSTANCE, 5);
            }
            if (oldVersion < 7 && oldVersion > 5) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("download_state", (Integer) 4);
                db.update("Track", contentValues, "download_state=?", new String[]{"2"});
                contentValues.put("download_state", (Integer) 5);
                db.update("Track", contentValues, "download_state=?", new String[]{"3"});
            }
            if (oldVersion < 8) {
                ColumnType.TextType textType = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", "sort_title", textType);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "sort_album", textType);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "sort_album_artist", textType);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "sort_artist", textType);
                CirrusDatabase.resetForFullResync(context);
            }
            if (oldVersion < 16) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "album_art_xl", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 19) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", IntlConfiguration.MARKETPLACE, ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 20) {
                db.execSQL("UPDATE Track SET match_hash = -1 ");
                CirrusDatabase.resetForFullResync(context);
                SyncService.setMatchHashesSyncNeeded(true);
            }
            if (oldVersion < 21) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", ParserUtil.GENRE_SEGMENT_NAME, ColumnType.TextType.INSTANCE);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "genre_id", ColumnType.IntegerType.INSTANCE, 0);
                CirrusDatabase.resetForFullResync(context);
            }
            if (oldVersion < 25) {
                ColumnType.TextType textType2 = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", "lyrics_state", textType2);
                SQLiteDatabaseExtensions.addColumn(db, "Track", NotificationCompat.CATEGORY_STATUS, textType2);
                PrimeTracksCache.addPrimeColumn(db, "Track");
            }
            if (oldVersion < 30) {
                db.execSQL("ALTER TABLE Track RENAME TO Track_tmp");
                createTable(db, "Track");
                db.execSQL("INSERT INTO Track (_id,luid,asin,album_asin,artist_asin,title,sort_title,album,sort_album,album_id,album_art_id,album_artist,sort_album_artist,album_artist_id,artist,sort_artist,artist_id,track_num,disc_num,exists_flag,remote_uri,order_id,date_created,date_updated,date_purchased,purchased,uploaded,extension,size,duration,bookmark,is_podcast,version,match_hash,marketplace,lyrics_state,status,prime_status,ownership_status,genre,genre_id,source,download_state,luid_hash) SELECT _id,luid,asin,album_asin,artist_asin,title,sort_title,album,sort_album,album_id,album_art_id,album_artist,sort_album_artist,album_artist_id,artist,sort_artist,artist_id,track_num,disc_num,exists_flag,remote_uri,order_id,date_created,date_updated,date_purchased,purchased,uploaded,extension,size,duration,bookmark,is_podcast,version,match_hash,marketplace,lyrics_state,status,prime_status,ownership_status,genre,genre_id,source,download_state,luid_hash FROM Track_tmp");
                db.execSQL("DROP TABLE IF EXISTS Track_tmp");
            }
            if (oldVersion < 37) {
                db.delete("Track", "status!=?", new String[]{TrackStatus.AVAILABLE.toString()});
            }
            if (oldVersion < 42) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("download_state", (Integer) 5);
                db.update("Track", contentValues2, "source=0 AND download_state<>0", null);
            }
            if (oldVersion < 121) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "is_explicit", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 154) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "popularity", ColumnType.DecimalType.INSTANCE, -1);
            }
            if (oldVersion < 158) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "content_encoding", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 160) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "playlist_track_status", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 163) {
                ColumnType.TextType textType3 = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", "asset_qualities", textType3);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "asset_type", textType3);
            }
            if (oldVersion < 164) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "is_exclusion_tagged", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 168) {
                SQLiteDatabaseExtensions.addColumn(db, "Track", "is_exclusion_tagged", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 169) {
                ColumnType.TextType textType4 = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Track", "playmode_eligibility", textType4);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "catalog_status_tiers", ColumnType.IntegerType.INSTANCE, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Track", "album_playmode_eligibility", textType4);
                CatalogStatusTiersOperation.setShouldPerformSync(context, true);
            }
        }
    }

    public static abstract class Artists extends MediaProvider.Artists {
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS Artists ( _id INTEGER PRIMARY KEY AUTOINCREMENT, artist_asin TEXT, name TEXT, sort_name TEXT, follow_status INTEGER DEFAULT 0, enabled_notification_events TEXT, date_created NUMBER );");
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public static abstract class Albums extends MediaProvider.Albums {
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS Albums ( _id INTEGER PRIMARY KEY AUTOINCREMENT, album_asin TEXT, title TEXT, sort_title TEXT, artist_asin TEXT, artist TEXT, sort_artist TEXT, content_encoding TEXT, like_status INTEGER DEFAULT 0, date_created NUMBER );");
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public static abstract class Playlists extends MediaProvider.Playlists {
        public static final String DEFAULT_SHARED_ID = null;

        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS Playlist ( _id INTEGER PRIMARY KEY, luid TEXT UNIQUE NOT NULL, version TEXT, exists_flag INTEGER DEFAULT 0, name TEXT, source INTEGER DEFAULT 0, download_state INTEGER DEFAULT 5, type TEXT, visibility INTEGER DEFAULT " + MediaProvider.Playlists.VisibilityState.PRIVATE.getInt() + ", " + MediaTrack.ROLE_DESCRIPTION + " TEXT, canRemove INTEGER DEFAULT 1, canRename INTEGER DEFAULT 1, thumbnailArtUrl TEXT, bannerArtUrl TEXT,updatedSinceLastSync INTEGER DEFAULT 0, dateLastModified NUMBER DEFAULT 0, is_sonic_rush INTEGER DEFAULT 0, is_sonic_rush_on_demand INTEGER DEFAULT 0, is_sonic_rush_golden INTEGER DEFAULT 0, playmode_eligibility TEXT, canLikeTracks INTEGER DEFAULT 1, dateCreated NUMBER DEFAULT 0, numberOfFollowers INTEGER DEFAULT 0, shared_id TEXT);");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            Log.verbose(CirrusDatabase.TAG, "Playlists: doUpgrade: " + oldVersion + ", current version: " + CirrusDatabase.SCHEMA_VERSION);
            if (oldVersion < 4) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", PageUriUtils.SOURCE_QUERY_PARAM, ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 5) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "download_state", ColumnType.IntegerType.INSTANCE, 5);
            }
            if (oldVersion < 7 && oldVersion > 5) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("download_state", (Integer) 4);
                db.update("Playlist", contentValues, "download_state=?", new String[]{"2"});
                contentValues.put("download_state", (Integer) 5);
                db.update("Playlist", contentValues, "download_state=?", new String[]{"3"});
            }
            if (oldVersion < 42) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("download_state", (Integer) 5);
                db.update("Playlist", contentValues2, "source=0 AND download_state<>0", null);
            }
            if (oldVersion < 157) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "visibility", ColumnType.IntegerType.INSTANCE, Integer.valueOf(MediaProvider.Playlists.VisibilityState.PRIVATE.getInt()));
            }
            if (oldVersion < 161) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", MediaTrack.ROLE_DESCRIPTION, ColumnType.TextType.INSTANCE);
                ColumnType.IntegerType integerType = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "canRemove", integerType, 1);
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "canRename", integerType, 1);
            }
            if (oldVersion < 162) {
                ColumnType.TextType textType = ColumnType.TextType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "thumbnailArtUrl", textType);
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "bannerArtUrl", textType);
            }
            if (oldVersion < 165) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "updatedSinceLastSync", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 166) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "dateLastModified", ColumnType.NumberType.INSTANCE, 0);
            }
            if (oldVersion < 169) {
                ColumnType.IntegerType integerType2 = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "is_sonic_rush", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "is_sonic_rush_on_demand", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "is_sonic_rush_golden", integerType2, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "playmode_eligibility", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 170) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "canLikeTracks", ColumnType.IntegerType.INSTANCE, 1);
            }
            if (oldVersion < 171) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "dateCreated", ColumnType.NumberType.INSTANCE, 0);
            }
            if (oldVersion < 173) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "numberOfFollowers", ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 174) {
                SQLiteDatabaseExtensions.addColumn(db, "Playlist", "shared_id", ColumnType.TextType.INSTANCE);
                PlaylistSyncOperation.setShouldPerformSync(context, true);
            }
        }
    }

    public static abstract class PlaylistTracks extends MediaProvider.UdoPlaylistTracks {
        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL(createTable("PlaylistTrack"));
            db.execSQL(createTable("PlaylistTrackScratch"));
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        private static String createTable(String table) {
            return "CREATE TABLE IF NOT EXISTS " + table + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, udo_playlist_id INTEGER, track_luid TEXT, udo INTEGER);";
        }
    }

    public static class LocalTrackUris {
        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS LocalTrackUri ( track_luid TEXT UNIQUE, track_asin TEXT, local_uri TEXT, license_expiration NUMBER);");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            if (oldVersion < 31) {
                db.execSQL("ALTER TABLE LocalTrackUri RENAME TO LocalTrackUri_tmp");
                createTable(db);
                db.execSQL("INSERT INTO LocalTrackUri (track_luid,local_uri) SELECT track_luid,local_uri FROM LocalTrackUri_tmp");
                db.execSQL("DROP TABLE IF EXISTS LocalTrackUri_tmp");
            }
        }
    }

    public static class AccessTimes {
        private static boolean requiresUpgrade(int oldVersion) {
            return oldVersion >= 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS AccessTime ( content_uri TEXT, content_uri_hash INTEGER, source INTEGER DEFAULT 0, date_synced NUMBER, date_played NUMBER, date_downloaded NUMBER, date_purchased NUMBER, date_sims_retrieved NUMBER, date_artist_image_retrieved NUMBER);");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            if (!requiresUpgrade(oldVersion)) {
                createTable(db);
                return;
            }
            if (oldVersion < 6) {
                SQLiteDatabaseExtensions.addColumn(db, "AccessTime", PageUriUtils.SOURCE_QUERY_PARAM, ColumnType.IntegerType.INSTANCE, 0);
            }
            if (oldVersion < 11) {
                SQLiteDatabaseExtensions.addColumn(db, "AccessTime", "content_uri_hash", ColumnType.IntegerType.INSTANCE);
                db.execSQL("DELETE FROM AccessTime");
            }
        }
    }

    public static class Contributors {
        private static boolean requiresUpgrade(int oldVersion) {
            return oldVersion >= 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS Contributors ( asin TEXT, artist_asin TEXT, name TEXT, xray_ready INTEGER DEFAULT 0, image_url TEXT, image_max_width INTEGER DEFAULT 0, image_max_height INTEGER DEFAULT 0, hero_image_url TEXT, hero_image_max_width INTEGER DEFAULT 0, hero_image_max_height INTEGER DEFAULT 0, is_temp INTEGER DEFAULT 0, date_last_updated NUMBER);");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            if (!requiresUpgrade(oldVersion)) {
                createTable(db);
                return;
            }
            if (oldVersion < 14) {
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "xray_ready", ColumnType.IntegerType.INSTANCE, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "hero_image_url", ColumnType.TextType.INSTANCE);
            }
            if (oldVersion < 15) {
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "artist_asin", ColumnType.TextType.INSTANCE);
                CirrusDatabase.resetForFullResync(context);
            }
            if (oldVersion < 22) {
                ColumnType.IntegerType integerType = ColumnType.IntegerType.INSTANCE;
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "image_max_width", integerType, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "image_max_height", integerType, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "hero_image_max_width", integerType, 0);
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "hero_image_max_height", integerType, 0);
                CirrusDatabase.resetForFullResync(context);
            }
            if (oldVersion < 26) {
                SQLiteDatabaseExtensions.addColumn(db, "Contributors", "is_temp", ColumnType.IntegerType.INSTANCE, 0);
            }
        }
    }

    public static class ItemContributors {
        private static boolean requiresUpgrade(int oldVersion) {
            return oldVersion >= 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void createTable(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS ItemContributors ( item_uri TEXT, contributor_asin TEXT, default_contrib_flag INTEGER, date_last_updated NUMBER);");
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void doUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
            if (requiresUpgrade(oldVersion)) {
                return;
            }
            createTable(db);
        }
    }

    public static SQLiteDatabase getWritableDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (CirrusDatabase.class) {
                if (sInstance == null) {
                    final OpenHelper openHelper = new OpenHelper(context);
                    SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
                    sInstance = writableDatabase;
                    writableDatabase.setLocale(context.getResources().getConfiguration().locale);
                    sInstance.execSQL("PRAGMA temp_store = MEMORY");
                    sInstance.execSQL("PRAGMA synchronous = NORMAL");
                    new Thread(new Runnable() { // from class: com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ThreadUtil.randomSleep(1500L, 2200L);
                            if (CirrusDatabase.sInstance == null || !CirrusDatabase.sInstance.isOpen()) {
                                return;
                            }
                            CirrusPlaylistScratch.removeAll(CirrusDatabase.sInstance);
                            openHelper.resumeDbMigrationIfRequired(CirrusDatabase.sInstance);
                        }
                    }).start();
                }
            }
        }
        return sInstance;
    }

    public static SQLiteDatabase getReadOnlyDatabase(final Context context) {
        SQLiteDatabase sQLiteDatabase;
        if (AmazonApplication.getCapabilities().shouldSplitDBAccess()) {
            synchronized (CirrusDatabase.class) {
                if (sReadOnlyInstance == null) {
                    getWritableDatabase(context);
                    SQLiteDatabase readOnlyDatabase = new OpenHelper(context).getReadOnlyDatabase();
                    sReadOnlyInstance = readOnlyDatabase;
                    readOnlyDatabase.execSQL("PRAGMA temp_store = MEMORY");
                    sReadOnlyInstance.execSQL("PRAGMA synchronous = NORMAL");
                }
                sQLiteDatabase = sReadOnlyInstance;
            }
            return sQLiteDatabase;
        }
        return getWritableDatabase(context);
    }

    public static SQLiteDatabase reopenDatabase(final Context context) {
        SQLiteDatabase writableDatabase;
        synchronized (CirrusDatabase.class) {
            final SQLiteDatabase sQLiteDatabase = sInstance;
            sInstance = null;
            final SQLiteDatabase sQLiteDatabase2 = sReadOnlyInstance;
            sReadOnlyInstance = null;
            Thread thread = new Thread("DelayedDatabaseClose") { // from class: com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    SystemClock.sleep(10000L);
                    SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase2;
                    if (sQLiteDatabase3 != null && sQLiteDatabase3.isOpen()) {
                        Log.info(CirrusDatabase.TAG, "Closing readable db");
                        sQLiteDatabase2.close();
                    }
                    SQLiteDatabase sQLiteDatabase4 = sQLiteDatabase;
                    if (sQLiteDatabase4 == null || !sQLiteDatabase4.isOpen()) {
                        return;
                    }
                    Log.info(CirrusDatabase.TAG, "Closing writable db");
                    sQLiteDatabase.close();
                }
            };
            thread.setPriority(1);
            thread.start();
            writableDatabase = getWritableDatabase(context);
        }
        return writableDatabase;
    }

    public static void resetForFullResync(Context context) {
        SettingsUtil.setLastLocalUpdatedTime(context, -1L);
        SettingsUtil.setShouldForceIncrementalUpdate(context, true);
        SyncService.resetLastCloudSyncTime(context);
        SyncService.resetLastSyncTime();
        SyncService.resetSyncLastCheckpoint(context);
    }

    public static void resetDB(Context context, SQLiteDatabase db, boolean clearLocalData) {
        db.beginTransaction();
        db.execSQL("DROP TABLE IF EXISTS Track");
        db.execSQL("DROP TABLE IF EXISTS Playlist");
        db.execSQL("DROP TABLE IF EXISTS Artists");
        db.execSQL("DROP TABLE IF EXISTS Albums");
        db.execSQL("DROP TABLE IF EXISTS PlaylistTrack");
        db.execSQL("DROP TABLE IF EXISTS PlaylistTrackScratch");
        db.execSQL("DROP TABLE IF EXISTS ItemContributors");
        db.execSQL("DROP TABLE IF EXISTS Contributors");
        if (clearLocalData) {
            db.execSQL("DROP TABLE IF EXISTS LocalTrackUri");
            db.execSQL("DROP TABLE IF EXISTS AccessTime");
        } else {
            try {
                db.execSQL("UPDATE AccessTime SET date_artist_image_retrieved = 0, date_sims_retrieved = 0");
            } catch (Exception e) {
                Log.warning(TAG, "Exeption on reset of access times", e);
            }
        }
        resetPrimeTables(db);
        new OpenHelper(context).onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.info(TAG, "database reset");
    }

    public static void reset(Context context, boolean clearLocalData) {
        resetDB(context, getWritableDatabase(context), clearLocalData);
    }

    private static void resetPrimeTables(SQLiteDatabase db) {
        PrimeCacheTimesTable.resetPrimeCacheTimeTable(db);
        PrimePlaylistsTable.resetPrimePlaylistsTable(db);
        PrimePlaylistTracksTable.resetPrimePlaylistTracksTable(db);
        PrimePlaylistToTrackTable.resetPrimePlaylistToTrackTable(db);
        PrimePlaylistsCacheTimesTable.resetPrimePlaylistsCacheTimeTable(db);
        PrimeSearchQueriesTable.resetPrimeSearchQueriesTable(db);
    }

    public static void resetPlaymodeEligibility(Context context) {
        SQLiteDatabase writableDatabase = getWritableDatabase(context);
        writableDatabase.beginTransaction();
        try {
            if (tableExists(writableDatabase, "Track")) {
                ContentValues contentValues = new ContentValues();
                contentValues.putNull("playmode_eligibility");
                contentValues.putNull("album_playmode_eligibility");
                writableDatabase.update("Track", contentValues, null, null);
            }
            if (tableExists(writableDatabase, "Playlist")) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.putNull("playmode_eligibility");
                writableDatabase.update("Playlist", contentValues2, null, null);
            }
            if (tableExists(writableDatabase, "PrimePlaylists")) {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.putNull("playmode_eligibility");
                writableDatabase.update("PrimePlaylists", contentValues3, null, null);
            }
            if (tableExists(writableDatabase, "PrimePlaylistTracks")) {
                ContentValues contentValues4 = new ContentValues();
                contentValues4.putNull("playmode_eligibility");
                writableDatabase.update("PrimePlaylistTracks", contentValues4, null, null);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private static boolean tableExists(final SQLiteDatabase db, final String tableName) {
        Cursor cursorRawQuery = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        boolean z = cursorRawQuery != null && cursorRawQuery.getCount() > 0;
        DbUtil.closeCursor(cursorRawQuery);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createPrimeTables(SQLiteDatabase db) {
        PrimeCacheTimesTable.createPrimeCacheTimeTable(db);
        PrimePlaylistsTable.createPrimePlaylistsTable(db);
        PrimePlaylistTracksTable.createPrimePlaylistTracksTable(db);
        PrimePlaylistToTrackTable.createPrimePlaylistToTrackTable(db);
        PrimePlaylistsCacheTimesTable.createPrimePlaylistsCacheTimesTable(db);
        PrimeSearchQueriesTable.createPrimeSearchQueriesTable(db);
        PrimeArtistTracksTable.createPrimeArtistTracksTable(db);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void primeUpgrade(Context context, SQLiteDatabase db, int oldVersion) {
        if (oldVersion < 25) {
            createPrimeTables(db);
            resetForFullResync(context);
        }
        if (oldVersion >= 27 && oldVersion < 29) {
            resetPrimeTables(db);
        }
        if (oldVersion < 38) {
            PrimeSearchQueriesTable.createPrimeSearchQueriesTable(db);
        }
        if (oldVersion < 40) {
            PrimeSearchQueriesTable.resetPrimeSearchQueriesTable(db);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void upgradePrimeTables(SQLiteDatabase db, int oldVersion, int newVersion) {
        PrimeCacheTimesTable.onUpgrade(db, oldVersion, newVersion);
        PrimePlaylistsTable.onUpgrade(db, oldVersion, newVersion);
        PrimePlaylistTracksTable.onUpgrade(db, oldVersion, newVersion);
        PrimePlaylistToTrackTable.onUpgrade(db, oldVersion, newVersion);
        PrimePlaylistsCacheTimesTable.onUpgrade(db, oldVersion, newVersion);
        PrimeArtistTracksTable.onUpgrade(db, oldVersion, newVersion);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        private final Context mContext;

        public OpenHelper(Context context) {
            super(context.getApplicationContext(), "CirrusMediaSource.db", null, CirrusDatabase.SCHEMA_VERSION);
            this.mContext = context.getApplicationContext();
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            db.enableWriteAheadLogging();
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            Log.verbose(CirrusDatabase.TAG, "onCreate");
            db.beginTransaction();
            Tracks.createTable(db, "Track");
            Artists.createTable(db);
            Albums.createTable(db);
            Playlists.createTable(db);
            PlaylistTracks.createTable(db);
            LocalTrackUris.createTable(db);
            AccessTimes.createTable(db);
            Contributors.createTable(db);
            ItemContributors.createTable(db);
            PlayQueue.NowPlaying.createTable(db);
            CirrusDatabase.createPrimeTables(db);
            createIndexes(db);
            new WidevineMigrationTrackerDAO(db).onCreate();
            RecentlyPlayedDatabase.getInstance(this.mContext).createAlexaTable();
            new TracksToUpdateDAO(db).createTable();
            CirrusDatabase.resetForFullResync(this.mContext);
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public int onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            boolean z;
            Log.verbose(CirrusDatabase.TAG, "onUpgrade: oldVersion: " + oldVersion + ", new version: " + newVersion);
            db.beginTransaction();
            try {
                try {
                } catch (SQLException e) {
                    DbUtil.reportDbUpgradeException(oldVersion, newVersion, CirrusDatabase.class.getSimpleName(), e);
                    Log.error(CirrusDatabase.TAG, "Error during upgrade [old version: %s], [new version: %s]", Integer.valueOf(oldVersion), Integer.valueOf(newVersion), e);
                    Log.error(CirrusDatabase.TAG, "Error: " + e.getMessage(), e);
                    CirrusDatabase.resetDB(this.mContext, db, true);
                    db.setTransactionSuccessful();
                }
                if (PlatformUtil.isFireOS() && oldVersion < 121) {
                    return migratePreUnityDb(db, oldVersion, newVersion);
                }
                if (oldVersion < 152) {
                    new WidevineMigrationTrackerDAO(db).onUpgrade();
                }
                Tracks.doUpgrade(this.mContext, db, oldVersion);
                Playlists.doUpgrade(this.mContext, db, oldVersion);
                PlaylistTracks.doUpgrade(this.mContext, db, oldVersion);
                LocalTrackUris.doUpgrade(this.mContext, db, oldVersion);
                AccessTimes.doUpgrade(this.mContext, db, oldVersion);
                Contributors.doUpgrade(this.mContext, db, oldVersion);
                PlayQueue.NowPlaying.doUpgrade(this.mContext, db, oldVersion);
                ItemContributors.doUpgrade(this.mContext, db, oldVersion);
                CirrusDatabase.primeUpgrade(this.mContext, db, oldVersion);
                if (oldVersion < 153) {
                    new TracksToUpdateDAO(db).createTable();
                }
                if (oldVersion < 25) {
                    db.execSQL("DROP TABLE IF EXISTS Lyrics");
                }
                if (oldVersion < 34) {
                    db.execSQL("DELETE FROM LocalTrackUri WHERE track_asin IS NOT NULL AND (track_luid IS NULL OR track_luid = '')");
                }
                if (oldVersion < 35) {
                    db.execSQL("ALTER TABLE LocalTrackUri RENAME TO LocalTrackUri_tmp");
                    LocalTrackUris.createTable(db);
                    db.execSQL("INSERT INTO LocalTrackUri (license_expiration,local_uri,track_asin,track_luid) SELECT license_expiration,local_uri,track_asin,track_luid FROM LocalTrackUri_tmp");
                    db.execSQL("DROP TABLE IF EXISTS LocalTrackUri_tmp");
                    db.execSQL("REPLACE INTO LocalTrackUri (track_asin, track_luid, local_uri, license_expiration) SELECT track_asin, luid, local_uri, license_expiration FROM Track INNER JOIN LocalTrackUri ON asin = track_asin WHERE source = 0 AND luid IS NOT NULL AND track_luid IS NULL AND local_uri NOT LIKE '%PrimePlaylist%'");
                }
                if (oldVersion < 36) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("version", "UNKNOWN");
                    db.update("Playlist", contentValues, "source =?", new String[]{String.valueOf(0)});
                    z = true;
                } else {
                    z = false;
                }
                if (oldVersion < 42) {
                    db.execSQL("DROP TABLE IF EXISTS Download");
                }
                if (oldVersion < 165) {
                    PlaylistConfigurationStorage.get().setMigrateAndUnifyDevicePlaylists(true);
                } else {
                    PlaylistConfigurationStorage.get().setMigrateAndUnifyDevicePlaylists(false);
                }
                if (oldVersion < 121) {
                    z = true;
                }
                if (oldVersion < 164) {
                    z = true;
                }
                if (z) {
                    CirrusDatabase.resetForFullResync(this.mContext);
                }
                CirrusDatabase.upgradePrimeTables(db, oldVersion, newVersion);
                createIndexes(db);
                db.setTransactionSuccessful();
                return newVersion;
            } finally {
                db.endTransaction();
            }
        }

        @VisibleForTesting
        int migratePreUnityDb(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            if (oldVersion >= 108 && oldVersion < 121 && new PreUnityCirrusMediaSourceDb(this.mContext).backup(db) != null) {
                Log.info(CirrusDatabase.TAG, "Clearing last playlist sync checkpoint");
                PlaylistConfigurationStorage.get().removePlaylistCheckpoint();
                SyncService.setDbMigrationSyncNeeded(true);
            }
            CirrusDatabase.resetDB(this.mContext, db, true);
            createIndexes(db);
            CirrusDatabase.resetForFullResync(this.mContext);
            db.setTransactionSuccessful();
            return newVersion;
        }

        void resumeDbMigrationIfRequired(SQLiteDatabase db) {
            if (!PlatformUtil.isFireOS() || isDbMigrationDone(db)) {
                return;
            }
            Log.debug(CirrusDatabase.TAG, "Resuming db migration");
            CirrusDatabase.resetForFullResync(this.mContext);
            SyncService.setDbMigrationSyncNeeded(true);
            SyncService.startSync(this.mContext, 16059);
        }

        private boolean isDbMigrationDone(final SQLiteDatabase db) {
            return new PreUnityCirrusMediaSourceDb(this.mContext).isBackupPresent(db);
        }

        private void createIndexes(SQLiteDatabase db) {
            db.beginTransaction();
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksTitleIndex ON Track(title)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksTitleArtistIndex ON Track(sort_title ASC,sort_artist ASC)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksArtistIndex ON Track(artist)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksAlbumArtistIndex ON Track(album_artist)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksArtistIdIndex ON Track(artist_id)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksAlbumArtistIdIndex ON Track(album_artist_id)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksAlbumIndex ON Track(album)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksAlbumIdIndex ON Track(album_id)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksLuidIndex ON Track(luid)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksMatchIndex ON Track(match_hash)");
            db.execSQL("CREATE INDEX IF NOT EXISTS TracksCreatedIndex ON Track(date_created)");
            db.execSQL("CREATE INDEX IF NOT EXISTS PlaylistTracksLuidIndex ON PlaylistTrack(track_luid)");
            db.execSQL("CREATE INDEX IF NOT EXISTS PlaylistTracksPlaylistIdIndex ON PlaylistTrack(udo_playlist_id)");
            db.execSQL("CREATE INDEX IF NOT EXISTS AccessUriIndex ON AccessTime(content_uri)");
            db.execSQL("CREATE INDEX IF NOT EXISTS AccessUriHashIndex ON AccessTime(content_uri_hash)");
            db.execSQL("CREATE INDEX IF NOT EXISTS ContribAsinIndex ON Contributors(asin)");
            db.execSQL("CREATE INDEX IF NOT EXISTS ContribUpdatedIndex ON Contributors(date_last_updated)");
            db.execSQL("CREATE INDEX IF NOT EXISTS ItemContribUriIndex ON ItemContributors(item_uri)");
            db.execSQL("CREATE INDEX IF NOT EXISTS GenresIndex ON Track(genre)");
            db.execSQL("CREATE INDEX IF NOT EXISTS GenresIdIndex ON Track(genre_id)");
            db.setTransactionSuccessful();
            db.endTransaction();
            PlayQueue.NowPlaying.createIndexes(db);
        }
    }
}
