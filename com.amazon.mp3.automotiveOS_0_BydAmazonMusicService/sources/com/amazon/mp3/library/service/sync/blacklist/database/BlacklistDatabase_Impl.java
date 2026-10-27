package com.amazon.mp3.library.service.sync.blacklist.database;

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
import com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao;
import com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class BlacklistDatabase_Impl extends BlacklistDatabase {
    private volatile BlacklistDao _blacklistDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.mp3.library.service.sync.blacklist.database.BlacklistDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `local_uris` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `local_uri` TEXT)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5ce1845d1fca5d2fa01d00840ace6bd4')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `local_uris`");
                if (((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ((RoomDatabase) BlacklistDatabase_Impl.this).mDatabase = _db;
                BlacklistDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BlacklistDatabase_Impl.this).mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(2);
                map.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map.put("local_uri", new TableInfo.Column("local_uri", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("local_uris", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "local_uris");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "local_uris(com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "5ce1845d1fca5d2fa01d00840ace6bd4", "30def0288052b74ae5e6b6c2d5c413c1")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "local_uris");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `local_uris`");
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
        map.put(BlacklistDao.class, BlacklistDao_Impl.getRequiredConverters());
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

    @Override // com.amazon.mp3.library.service.sync.blacklist.database.BlacklistDatabase
    public BlacklistDao blacklistDao() {
        BlacklistDao blacklistDao;
        if (this._blacklistDao != null) {
            return this._blacklistDao;
        }
        synchronized (this) {
            if (this._blacklistDao == null) {
                this._blacklistDao = new BlacklistDao_Impl(this);
            }
            blacklistDao = this._blacklistDao;
        }
        return blacklistDao;
    }
}
