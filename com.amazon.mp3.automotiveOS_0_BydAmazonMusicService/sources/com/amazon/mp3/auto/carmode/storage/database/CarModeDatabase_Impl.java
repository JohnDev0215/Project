package com.amazon.mp3.auto.carmode.storage.database;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class CarModeDatabase_Impl extends CarModeDatabase {
    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.mp3.auto.carmode.storage.database.CarModeDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `preset` (`content_identifier` TEXT NOT NULL, `content_type` TEXT NOT NULL, `directed_profile_id` TEXT NOT NULL, PRIMARY KEY(`content_identifier`, `directed_profile_id`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5920de2903cf4c47cd998a5c5daff9c3')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `preset`");
                if (((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ((RoomDatabase) CarModeDatabase_Impl.this).mDatabase = _db;
                CarModeDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CarModeDatabase_Impl.this).mCallbacks.get(i)).onOpen(_db);
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
                map.put("content_identifier", new TableInfo.Column("content_identifier", "TEXT", true, 1, null, 1));
                map.put("content_type", new TableInfo.Column("content_type", "TEXT", true, 0, null, 1));
                map.put("directed_profile_id", new TableInfo.Column("directed_profile_id", "TEXT", true, 2, null, 1));
                TableInfo tableInfo = new TableInfo("preset", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "preset");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "preset(com.amazon.mp3.auto.carmode.storage.database.PresetEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "5920de2903cf4c47cd998a5c5daff9c3", "f36240053e148a24f3c37ce31bd53458")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "preset");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `preset`");
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
        map.put(PresetDao.class, PresetDao_Impl.getRequiredConverters());
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
}
