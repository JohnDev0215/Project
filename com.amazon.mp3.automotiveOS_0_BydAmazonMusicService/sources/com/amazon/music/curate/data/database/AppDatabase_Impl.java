package com.amazon.music.curate.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile LibraryContextMappingDao _libraryContextMappingDao;
    private volatile PlaylistDao _playlistDao;
    private volatile PlaylistTrackDao _playlistTrackDao;
    private volatile TrackDao _trackDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) { // from class: com.amazon.music.curate.data.database.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Track` (`asin` TEXT NOT NULL, `title` TEXT NOT NULL, `artist_name` TEXT NOT NULL, `artwork_url` TEXT NOT NULL, `badges` TEXT NOT NULL, `artist_asin` TEXT NOT NULL, `album_asin` TEXT NOT NULL, `album_name` TEXT NOT NULL, `is_in_library` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `marketplace` TEXT NOT NULL, `entry_id` TEXT, PRIMARY KEY(`asin`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Playlist` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `artwork_url` TEXT NOT NULL, `banner_artwork_url` TEXT NOT NULL, `num_songs` INTEGER NOT NULL, `duration_seconds` INTEGER, `version` TEXT NOT NULL, `expiry_time` INTEGER NOT NULL, `is_personalized` INTEGER, `is_followed` INTEGER, `is_shareable` INTEGER, `is_prime` INTEGER, `is_music_unlimited` INTEGER, `is_nightwing` INTEGER, `is_nightwing_on_demand` INTEGER, `can_rename` INTEGER, `can_edit_content` INTEGER, `can_remove` INTEGER, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `PlaylistTrack` (`playlist_id` TEXT NOT NULL, `track_asin` TEXT NOT NULL, `position_in_playlist` INTEGER NOT NULL, PRIMARY KEY(`playlist_id`, `position_in_playlist`), FOREIGN KEY(`track_asin`) REFERENCES `Track`(`asin`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_PlaylistTrack_track_asin` ON `PlaylistTrack` (`track_asin`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `LibraryContextMapping` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `page_context` TEXT NOT NULL, `widget_context` TEXT NOT NULL, `widget_item_context` TEXT NOT NULL, `entity_id_type` TEXT, `entity_id` TEXT, `label` TEXT, `primary_text` TEXT, `secondary_text` TEXT, `tertiary_text` TEXT, `image` TEXT, `content_encoding` TEXT, `content_src` TEXT, `content_info_inst_id` TEXT, `play_mode` TEXT)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b106cd745525bf0e25a83587b353e503')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Track`");
                _db.execSQL("DROP TABLE IF EXISTS `Playlist`");
                _db.execSQL("DROP TABLE IF EXISTS `PlaylistTrack`");
                _db.execSQL("DROP TABLE IF EXISTS `LibraryContextMapping`");
                if (((RoomDatabase) AppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (((RoomDatabase) AppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ((RoomDatabase) AppDatabase_Impl.this).mDatabase = _db;
                _db.execSQL("PRAGMA foreign_keys = ON");
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (((RoomDatabase) AppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AppDatabase_Impl.this).mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(12);
                map.put("asin", new TableInfo.Column("asin", "TEXT", true, 1, null, 1));
                map.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map.put("artist_name", new TableInfo.Column("artist_name", "TEXT", true, 0, null, 1));
                map.put("artwork_url", new TableInfo.Column("artwork_url", "TEXT", true, 0, null, 1));
                map.put("badges", new TableInfo.Column("badges", "TEXT", true, 0, null, 1));
                map.put("artist_asin", new TableInfo.Column("artist_asin", "TEXT", true, 0, null, 1));
                map.put("album_asin", new TableInfo.Column("album_asin", "TEXT", true, 0, null, 1));
                map.put("album_name", new TableInfo.Column("album_name", "TEXT", true, 0, null, 1));
                map.put("is_in_library", new TableInfo.Column("is_in_library", "INTEGER", true, 0, null, 1));
                map.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, 1));
                map.put(IntlConfiguration.MARKETPLACE, new TableInfo.Column(IntlConfiguration.MARKETPLACE, "TEXT", true, 0, null, 1));
                map.put("entry_id", new TableInfo.Column("entry_id", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("Track", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "Track");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "Track(com.amazon.music.curate.model.Track).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(18);
                map2.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map2.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map2.put("artwork_url", new TableInfo.Column("artwork_url", "TEXT", true, 0, null, 1));
                map2.put("banner_artwork_url", new TableInfo.Column("banner_artwork_url", "TEXT", true, 0, null, 1));
                map2.put("num_songs", new TableInfo.Column("num_songs", "INTEGER", true, 0, null, 1));
                map2.put("duration_seconds", new TableInfo.Column("duration_seconds", "INTEGER", false, 0, null, 1));
                map2.put("version", new TableInfo.Column("version", "TEXT", true, 0, null, 1));
                map2.put("expiry_time", new TableInfo.Column("expiry_time", "INTEGER", true, 0, null, 1));
                map2.put("is_personalized", new TableInfo.Column("is_personalized", "INTEGER", false, 0, null, 1));
                map2.put("is_followed", new TableInfo.Column("is_followed", "INTEGER", false, 0, null, 1));
                map2.put("is_shareable", new TableInfo.Column("is_shareable", "INTEGER", false, 0, null, 1));
                map2.put("is_prime", new TableInfo.Column("is_prime", "INTEGER", false, 0, null, 1));
                map2.put("is_music_unlimited", new TableInfo.Column("is_music_unlimited", "INTEGER", false, 0, null, 1));
                map2.put("is_nightwing", new TableInfo.Column("is_nightwing", "INTEGER", false, 0, null, 1));
                map2.put("is_nightwing_on_demand", new TableInfo.Column("is_nightwing_on_demand", "INTEGER", false, 0, null, 1));
                map2.put("can_rename", new TableInfo.Column("can_rename", "INTEGER", false, 0, null, 1));
                map2.put("can_edit_content", new TableInfo.Column("can_edit_content", "INTEGER", false, 0, null, 1));
                map2.put("can_remove", new TableInfo.Column("can_remove", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("Playlist", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "Playlist");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "Playlist(com.amazon.music.curate.model.Playlist).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(3);
                map3.put("playlist_id", new TableInfo.Column("playlist_id", "TEXT", true, 1, null, 1));
                map3.put("track_asin", new TableInfo.Column("track_asin", "TEXT", true, 0, null, 1));
                map3.put("position_in_playlist", new TableInfo.Column("position_in_playlist", "INTEGER", true, 2, null, 1));
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.ForeignKey("Track", "NO ACTION", "NO ACTION", Arrays.asList("track_asin"), Arrays.asList("asin")));
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_PlaylistTrack_track_asin", false, Arrays.asList("track_asin"), Arrays.asList("ASC")));
                TableInfo tableInfo5 = new TableInfo("PlaylistTrack", map3, hashSet, hashSet2);
                TableInfo tableInfo6 = TableInfo.read(_db, "PlaylistTrack");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "PlaylistTrack(com.amazon.music.curate.model.PlaylistTrack).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(15);
                map4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map4.put("page_context", new TableInfo.Column("page_context", "TEXT", true, 0, null, 1));
                map4.put("widget_context", new TableInfo.Column("widget_context", "TEXT", true, 0, null, 1));
                map4.put("widget_item_context", new TableInfo.Column("widget_item_context", "TEXT", true, 0, null, 1));
                map4.put("entity_id_type", new TableInfo.Column("entity_id_type", "TEXT", false, 0, null, 1));
                map4.put("entity_id", new TableInfo.Column("entity_id", "TEXT", false, 0, null, 1));
                map4.put(ContextMappingConstants.LABEL, new TableInfo.Column(ContextMappingConstants.LABEL, "TEXT", false, 0, null, 1));
                map4.put("primary_text", new TableInfo.Column("primary_text", "TEXT", false, 0, null, 1));
                map4.put("secondary_text", new TableInfo.Column("secondary_text", "TEXT", false, 0, null, 1));
                map4.put("tertiary_text", new TableInfo.Column("tertiary_text", "TEXT", false, 0, null, 1));
                map4.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", false, 0, null, 1));
                map4.put("content_encoding", new TableInfo.Column("content_encoding", "TEXT", false, 0, null, 1));
                map4.put("content_src", new TableInfo.Column("content_src", "TEXT", false, 0, null, 1));
                map4.put("content_info_inst_id", new TableInfo.Column("content_info_inst_id", "TEXT", false, 0, null, 1));
                map4.put("play_mode", new TableInfo.Column("play_mode", "TEXT", false, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("LibraryContextMapping", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(_db, "LibraryContextMapping");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "LibraryContextMapping(com.amazon.music.curate.model.LibraryContextMapping).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "b106cd745525bf0e25a83587b353e503", "ef5adb198ed1ea0cecf13211f1f756bb")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Track", "Playlist", "PlaylistTrack", "LibraryContextMapping");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
            writableDatabase.execSQL("DELETE FROM `Track`");
            writableDatabase.execSQL("DELETE FROM `Playlist`");
            writableDatabase.execSQL("DELETE FROM `PlaylistTrack`");
            writableDatabase.execSQL("DELETE FROM `LibraryContextMapping`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(TrackDao.class, TrackDao_Impl.getRequiredConverters());
        map.put(PlaylistDao.class, PlaylistDao_Impl.getRequiredConverters());
        map.put(PlaylistTrackDao.class, PlaylistTrackDao_Impl.getRequiredConverters());
        map.put(LibraryContextMappingDao.class, LibraryContextMappingDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(@NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.amazon.music.curate.data.database.AppDatabase
    public TrackDao trackDao() {
        TrackDao trackDao;
        if (this._trackDao != null) {
            return this._trackDao;
        }
        synchronized (this) {
            if (this._trackDao == null) {
                this._trackDao = new TrackDao_Impl(this);
            }
            trackDao = this._trackDao;
        }
        return trackDao;
    }

    @Override // com.amazon.music.curate.data.database.AppDatabase
    public PlaylistDao playlistDao() {
        PlaylistDao playlistDao;
        if (this._playlistDao != null) {
            return this._playlistDao;
        }
        synchronized (this) {
            if (this._playlistDao == null) {
                this._playlistDao = new PlaylistDao_Impl(this);
            }
            playlistDao = this._playlistDao;
        }
        return playlistDao;
    }

    @Override // com.amazon.music.curate.data.database.AppDatabase
    public PlaylistTrackDao playlistTrackDao() {
        PlaylistTrackDao playlistTrackDao;
        if (this._playlistTrackDao != null) {
            return this._playlistTrackDao;
        }
        synchronized (this) {
            if (this._playlistTrackDao == null) {
                this._playlistTrackDao = new PlaylistTrackDao_Impl(this);
            }
            playlistTrackDao = this._playlistTrackDao;
        }
        return playlistTrackDao;
    }

    @Override // com.amazon.music.curate.data.database.AppDatabase
    public LibraryContextMappingDao libraryContextMappingDao() {
        LibraryContextMappingDao libraryContextMappingDao;
        if (this._libraryContextMappingDao != null) {
            return this._libraryContextMappingDao;
        }
        synchronized (this) {
            if (this._libraryContextMappingDao == null) {
                this._libraryContextMappingDao = new LibraryContextMappingDao_Impl(this);
            }
            libraryContextMappingDao = this._libraryContextMappingDao;
        }
        return libraryContextMappingDao;
    }
}
