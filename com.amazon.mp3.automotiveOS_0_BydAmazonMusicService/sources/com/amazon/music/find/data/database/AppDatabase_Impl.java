package com.amazon.music.find.data.database;

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
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.actions.SearchIntents;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile SearchEntityDao _searchEntityDao;
    private volatile SearchQueryDao _searchQueryDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) { // from class: com.amazon.music.find.data.database.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `search_entity` (`title` TEXT NOT NULL COLLATE NOCASE, `entity_id` TEXT NOT NULL, `entity_id_type` TEXT NOT NULL, `entity_type` TEXT NOT NULL, `image_url` TEXT NOT NULL, `entity_property` TEXT, `timestamp` INTEGER NOT NULL, `did_navigate_from_search` INTEGER NOT NULL, `content_url` TEXT, `profile_id` TEXT NOT NULL, PRIMARY KEY(`entity_id`, `entity_id_type`, `profile_id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `search_query` (`query` TEXT NOT NULL COLLATE NOCASE, `timestamp` INTEGER NOT NULL, `profile_id` TEXT NOT NULL, PRIMARY KEY(`query`, `profile_id`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '131812765c54c0f2bc10497cfe454722')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `search_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `search_query`");
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
                HashMap map = new HashMap(10);
                map.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map.put("entity_id", new TableInfo.Column("entity_id", "TEXT", true, 1, null, 1));
                map.put("entity_id_type", new TableInfo.Column("entity_id_type", "TEXT", true, 2, null, 1));
                map.put("entity_type", new TableInfo.Column("entity_type", "TEXT", true, 0, null, 1));
                map.put("image_url", new TableInfo.Column("image_url", "TEXT", true, 0, null, 1));
                map.put("entity_property", new TableInfo.Column("entity_property", "TEXT", false, 0, null, 1));
                map.put(TetheredMessageKey.timestamp, new TableInfo.Column(TetheredMessageKey.timestamp, "INTEGER", true, 0, null, 1));
                map.put("did_navigate_from_search", new TableInfo.Column("did_navigate_from_search", "INTEGER", true, 0, null, 1));
                map.put("content_url", new TableInfo.Column("content_url", "TEXT", false, 0, null, 1));
                map.put("profile_id", new TableInfo.Column("profile_id", "TEXT", true, 3, null, 1));
                TableInfo tableInfo = new TableInfo("search_entity", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "search_entity");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "search_entity(com.amazon.music.find.data.database.entities.SearchEntityData).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(3);
                map2.put(SearchIntents.EXTRA_QUERY, new TableInfo.Column(SearchIntents.EXTRA_QUERY, "TEXT", true, 1, null, 1));
                map2.put(TetheredMessageKey.timestamp, new TableInfo.Column(TetheredMessageKey.timestamp, "INTEGER", true, 0, null, 1));
                map2.put("profile_id", new TableInfo.Column("profile_id", "TEXT", true, 2, null, 1));
                TableInfo tableInfo3 = new TableInfo("search_query", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "search_query");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "search_query(com.amazon.music.find.data.database.entities.SearchQueryData).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "131812765c54c0f2bc10497cfe454722", "246b0ec8d6d480d871fce438800cb37b")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "search_entity", "search_query");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `search_entity`");
            writableDatabase.execSQL("DELETE FROM `search_query`");
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
        map.put(SearchEntityDao.class, SearchEntityDao_Impl.getRequiredConverters());
        map.put(SearchQueryDao.class, SearchQueryDao_Impl.getRequiredConverters());
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

    @Override // com.amazon.music.find.data.database.AppDatabase
    public SearchEntityDao getSearchEntityDao() {
        SearchEntityDao searchEntityDao;
        if (this._searchEntityDao != null) {
            return this._searchEntityDao;
        }
        synchronized (this) {
            if (this._searchEntityDao == null) {
                this._searchEntityDao = new SearchEntityDao_Impl(this);
            }
            searchEntityDao = this._searchEntityDao;
        }
        return searchEntityDao;
    }

    @Override // com.amazon.music.find.data.database.AppDatabase
    public SearchQueryDao getSearchQueryDao() {
        SearchQueryDao searchQueryDao;
        if (this._searchQueryDao != null) {
            return this._searchQueryDao;
        }
        synchronized (this) {
            if (this._searchQueryDao == null) {
                this._searchQueryDao = new SearchQueryDao_Impl(this);
            }
            searchQueryDao = this._searchQueryDao;
        }
        return searchQueryDao;
    }
}
