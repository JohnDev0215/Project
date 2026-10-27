package com.android.bluetooth.btservice.storage;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataDatabase_Impl extends MetadataDatabase {
    private volatile MetadataDao _metadataDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(104) { // from class: com.android.bluetooth.btservice.storage.MetadataDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `metadata` (`address` TEXT NOT NULL, `migrated` INTEGER NOT NULL, `a2dpSupportsOptionalCodecs` INTEGER NOT NULL, `a2dpOptionalCodecsEnabled` INTEGER NOT NULL, `last_active_time` INTEGER NOT NULL, `is_active_a2dp_device` INTEGER NOT NULL, `a2dp_connection_policy` INTEGER, `a2dp_sink_connection_policy` INTEGER, `hfp_connection_policy` INTEGER, `hfp_client_connection_policy` INTEGER, `hid_host_connection_policy` INTEGER, `pan_connection_policy` INTEGER, `pbap_connection_policy` INTEGER, `pbap_client_connection_policy` INTEGER, `map_connection_policy` INTEGER, `sap_connection_policy` INTEGER, `hearing_aid_connection_policy` INTEGER, `map_client_connection_policy` INTEGER, `manufacturer_name` BLOB, `model_name` BLOB, `software_version` BLOB, `hardware_version` BLOB, `companion_app` BLOB, `main_icon` BLOB, `is_untethered_headset` BLOB, `untethered_left_icon` BLOB, `untethered_right_icon` BLOB, `untethered_case_icon` BLOB, `untethered_left_battery` BLOB, `untethered_right_battery` BLOB, `untethered_case_battery` BLOB, `untethered_left_charging` BLOB, `untethered_right_charging` BLOB, `untethered_case_charging` BLOB, `enhanced_settings_ui_uri` BLOB, PRIMARY KEY(`address`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd944677aac93d61a462f5324f4d71207')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `metadata`");
                if (MetadataDatabase_Impl.this.mCallbacks != null) {
                    int _size = MetadataDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) MetadataDatabase_Impl.this.mCallbacks.get(_i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (MetadataDatabase_Impl.this.mCallbacks != null) {
                    int _size = MetadataDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) MetadataDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                MetadataDatabase_Impl.this.mDatabase = _db;
                MetadataDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (MetadataDatabase_Impl.this.mCallbacks != null) {
                    int _size = MetadataDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) MetadataDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap<String, TableInfo.Column> _columnsMetadata = new HashMap<>(35);
                _columnsMetadata.put("address", new TableInfo.Column("address", "TEXT", true, 1, null, 1));
                _columnsMetadata.put("migrated", new TableInfo.Column("migrated", "INTEGER", true, 0, null, 1));
                _columnsMetadata.put("a2dpSupportsOptionalCodecs", new TableInfo.Column("a2dpSupportsOptionalCodecs", "INTEGER", true, 0, null, 1));
                _columnsMetadata.put("a2dpOptionalCodecsEnabled", new TableInfo.Column("a2dpOptionalCodecsEnabled", "INTEGER", true, 0, null, 1));
                _columnsMetadata.put("last_active_time", new TableInfo.Column("last_active_time", "INTEGER", true, 0, null, 1));
                _columnsMetadata.put("is_active_a2dp_device", new TableInfo.Column("is_active_a2dp_device", "INTEGER", true, 0, null, 1));
                _columnsMetadata.put("a2dp_connection_policy", new TableInfo.Column("a2dp_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("a2dp_sink_connection_policy", new TableInfo.Column("a2dp_sink_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("hfp_connection_policy", new TableInfo.Column("hfp_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("hfp_client_connection_policy", new TableInfo.Column("hfp_client_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("hid_host_connection_policy", new TableInfo.Column("hid_host_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("pan_connection_policy", new TableInfo.Column("pan_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("pbap_connection_policy", new TableInfo.Column("pbap_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("pbap_client_connection_policy", new TableInfo.Column("pbap_client_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("map_connection_policy", new TableInfo.Column("map_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("sap_connection_policy", new TableInfo.Column("sap_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("hearing_aid_connection_policy", new TableInfo.Column("hearing_aid_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("map_client_connection_policy", new TableInfo.Column("map_client_connection_policy", "INTEGER", false, 0, null, 1));
                _columnsMetadata.put("manufacturer_name", new TableInfo.Column("manufacturer_name", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("model_name", new TableInfo.Column("model_name", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("software_version", new TableInfo.Column("software_version", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("hardware_version", new TableInfo.Column("hardware_version", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("companion_app", new TableInfo.Column("companion_app", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("main_icon", new TableInfo.Column("main_icon", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("is_untethered_headset", new TableInfo.Column("is_untethered_headset", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_left_icon", new TableInfo.Column("untethered_left_icon", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_right_icon", new TableInfo.Column("untethered_right_icon", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_case_icon", new TableInfo.Column("untethered_case_icon", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_left_battery", new TableInfo.Column("untethered_left_battery", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_right_battery", new TableInfo.Column("untethered_right_battery", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_case_battery", new TableInfo.Column("untethered_case_battery", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_left_charging", new TableInfo.Column("untethered_left_charging", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_right_charging", new TableInfo.Column("untethered_right_charging", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("untethered_case_charging", new TableInfo.Column("untethered_case_charging", "BLOB", false, 0, null, 1));
                _columnsMetadata.put("enhanced_settings_ui_uri", new TableInfo.Column("enhanced_settings_ui_uri", "BLOB", false, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysMetadata = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesMetadata = new HashSet<>(0);
                TableInfo _infoMetadata = new TableInfo("metadata", _columnsMetadata, _foreignKeysMetadata, _indicesMetadata);
                TableInfo _existingMetadata = TableInfo.read(_db, "metadata");
                if (!_infoMetadata.equals(_existingMetadata)) {
                    return new RoomOpenHelper.ValidationResult(false, "metadata(com.android.bluetooth.btservice.storage.Metadata).\n Expected:\n" + _infoMetadata + "\n Found:\n" + _existingMetadata);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "d944677aac93d61a462f5324f4d71207", "01c473e1c023b5e7b0ffe9283a9a74f3");
        SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(_openCallback).build();
        SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        HashMap<String, String> _shadowTablesMap = new HashMap<>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "metadata");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `metadata`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override // com.android.bluetooth.btservice.storage.MetadataDatabase
    protected MetadataDao mMetadataDao() {
        MetadataDao metadataDao;
        if (this._metadataDao != null) {
            return this._metadataDao;
        }
        synchronized (this) {
            if (this._metadataDao == null) {
                this._metadataDao = new MetadataDao_Impl(this);
            }
            metadataDao = this._metadataDao;
        }
        return metadataDao;
    }
}
