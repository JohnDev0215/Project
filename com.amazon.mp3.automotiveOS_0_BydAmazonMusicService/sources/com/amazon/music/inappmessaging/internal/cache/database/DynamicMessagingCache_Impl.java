package com.amazon.music.inappmessaging.internal.cache.database;

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
import com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao;
import com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class DynamicMessagingCache_Impl extends DynamicMessagingCache {
    private volatile DynamicMessagingDao _dynamicMessagingDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.music.inappmessaging.internal.cache.database.DynamicMessagingCache_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `dynamicMessages` (`trigger` TEXT NOT NULL, `dynamicMessages` TEXT, `epochTimeToLive` INTEGER NOT NULL, PRIMARY KEY(`trigger`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ac301afa593dec6767d79eab66cedf72')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `dynamicMessages`");
                if (((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ((RoomDatabase) DynamicMessagingCache_Impl.this).mDatabase = _db;
                DynamicMessagingCache_Impl.this.internalInitInvalidationTracker(_db);
                if (((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DynamicMessagingCache_Impl.this).mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(3);
                map.put("trigger", new TableInfo.Column("trigger", "TEXT", true, 1, null, 1));
                map.put("dynamicMessages", new TableInfo.Column("dynamicMessages", "TEXT", false, 0, null, 1));
                map.put("epochTimeToLive", new TableInfo.Column("epochTimeToLive", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("dynamicMessages", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "dynamicMessages");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "dynamicMessages(com.amazon.music.inappmessaging.internal.cache.entity.DynamicMessagesPerTrigger).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "ac301afa593dec6767d79eab66cedf72", "08ec08b0fbb1afb021a09d7a1cbc0c39")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "dynamicMessages");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `dynamicMessages`");
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
        map.put(DynamicMessagingDao.class, DynamicMessagingDao_Impl.getRequiredConverters());
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

    @Override // com.amazon.music.inappmessaging.internal.cache.database.DynamicMessagingCache
    public DynamicMessagingDao dynamicMessagingDao() {
        DynamicMessagingDao dynamicMessagingDao;
        if (this._dynamicMessagingDao != null) {
            return this._dynamicMessagingDao;
        }
        synchronized (this) {
            if (this._dynamicMessagingDao == null) {
                this._dynamicMessagingDao = new DynamicMessagingDao_Impl(this);
            }
            dynamicMessagingDao = this._dynamicMessagingDao;
        }
        return dynamicMessagingDao;
    }
}
