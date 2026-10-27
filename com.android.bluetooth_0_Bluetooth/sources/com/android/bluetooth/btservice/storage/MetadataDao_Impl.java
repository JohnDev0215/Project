package com.android.bluetooth.btservice.storage;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataDao_Impl implements MetadataDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Metadata> __insertionAdapterOfMetadata;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public MetadataDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMetadata = new EntityInsertionAdapter<Metadata>(__db) { // from class: com.android.bluetooth.btservice.storage.MetadataDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `metadata` (`address`,`migrated`,`a2dpSupportsOptionalCodecs`,`a2dpOptionalCodecsEnabled`,`last_active_time`,`is_active_a2dp_device`,`a2dp_connection_policy`,`a2dp_sink_connection_policy`,`hfp_connection_policy`,`hfp_client_connection_policy`,`hid_host_connection_policy`,`pan_connection_policy`,`pbap_connection_policy`,`pbap_client_connection_policy`,`map_connection_policy`,`sap_connection_policy`,`hearing_aid_connection_policy`,`map_client_connection_policy`,`manufacturer_name`,`model_name`,`software_version`,`hardware_version`,`companion_app`,`main_icon`,`is_untethered_headset`,`untethered_left_icon`,`untethered_right_icon`,`untethered_case_icon`,`untethered_left_battery`,`untethered_right_battery`,`untethered_case_battery`,`untethered_left_charging`,`untethered_right_charging`,`untethered_case_charging`,`enhanced_settings_ui_uri`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Metadata metadata) {
                if (metadata.getAddress() != null) {
                    supportSQLiteStatement.bindString(1, metadata.getAddress());
                } else {
                    supportSQLiteStatement.bindNull(1);
                }
                supportSQLiteStatement.bindLong(2, metadata.migrated ? 1L : 0L);
                supportSQLiteStatement.bindLong(3, metadata.a2dpSupportsOptionalCodecs);
                supportSQLiteStatement.bindLong(4, metadata.a2dpOptionalCodecsEnabled);
                supportSQLiteStatement.bindLong(5, metadata.last_active_time);
                supportSQLiteStatement.bindLong(6, metadata.is_active_a2dp_device ? 1L : 0L);
                if (metadata.profileConnectionPolicies != null) {
                    supportSQLiteStatement.bindLong(7, r4.a2dp_connection_policy);
                    supportSQLiteStatement.bindLong(8, r4.a2dp_sink_connection_policy);
                    supportSQLiteStatement.bindLong(9, r4.hfp_connection_policy);
                    supportSQLiteStatement.bindLong(10, r4.hfp_client_connection_policy);
                    supportSQLiteStatement.bindLong(11, r4.hid_host_connection_policy);
                    supportSQLiteStatement.bindLong(12, r4.pan_connection_policy);
                    supportSQLiteStatement.bindLong(13, r4.pbap_connection_policy);
                    supportSQLiteStatement.bindLong(14, r4.pbap_client_connection_policy);
                    supportSQLiteStatement.bindLong(15, r4.map_connection_policy);
                    supportSQLiteStatement.bindLong(16, r4.sap_connection_policy);
                    supportSQLiteStatement.bindLong(17, r4.hearing_aid_connection_policy);
                    supportSQLiteStatement.bindLong(18, r4.map_client_connection_policy);
                } else {
                    supportSQLiteStatement.bindNull(7);
                    supportSQLiteStatement.bindNull(8);
                    supportSQLiteStatement.bindNull(9);
                    supportSQLiteStatement.bindNull(10);
                    supportSQLiteStatement.bindNull(11);
                    supportSQLiteStatement.bindNull(12);
                    supportSQLiteStatement.bindNull(13);
                    supportSQLiteStatement.bindNull(14);
                    supportSQLiteStatement.bindNull(15);
                    supportSQLiteStatement.bindNull(16);
                    supportSQLiteStatement.bindNull(17);
                    supportSQLiteStatement.bindNull(18);
                }
                CustomizedMetadataEntity customizedMetadataEntity = metadata.publicMetadata;
                if (customizedMetadataEntity != null) {
                    if (customizedMetadataEntity.manufacturer_name != null) {
                        supportSQLiteStatement.bindBlob(19, customizedMetadataEntity.manufacturer_name);
                    } else {
                        supportSQLiteStatement.bindNull(19);
                    }
                    if (customizedMetadataEntity.model_name != null) {
                        supportSQLiteStatement.bindBlob(20, customizedMetadataEntity.model_name);
                    } else {
                        supportSQLiteStatement.bindNull(20);
                    }
                    if (customizedMetadataEntity.software_version != null) {
                        supportSQLiteStatement.bindBlob(21, customizedMetadataEntity.software_version);
                    } else {
                        supportSQLiteStatement.bindNull(21);
                    }
                    if (customizedMetadataEntity.hardware_version != null) {
                        supportSQLiteStatement.bindBlob(22, customizedMetadataEntity.hardware_version);
                    } else {
                        supportSQLiteStatement.bindNull(22);
                    }
                    if (customizedMetadataEntity.companion_app != null) {
                        supportSQLiteStatement.bindBlob(23, customizedMetadataEntity.companion_app);
                    } else {
                        supportSQLiteStatement.bindNull(23);
                    }
                    if (customizedMetadataEntity.main_icon != null) {
                        supportSQLiteStatement.bindBlob(24, customizedMetadataEntity.main_icon);
                    } else {
                        supportSQLiteStatement.bindNull(24);
                    }
                    if (customizedMetadataEntity.is_untethered_headset != null) {
                        supportSQLiteStatement.bindBlob(25, customizedMetadataEntity.is_untethered_headset);
                    } else {
                        supportSQLiteStatement.bindNull(25);
                    }
                    if (customizedMetadataEntity.untethered_left_icon != null) {
                        supportSQLiteStatement.bindBlob(26, customizedMetadataEntity.untethered_left_icon);
                    } else {
                        supportSQLiteStatement.bindNull(26);
                    }
                    if (customizedMetadataEntity.untethered_right_icon != null) {
                        supportSQLiteStatement.bindBlob(27, customizedMetadataEntity.untethered_right_icon);
                    } else {
                        supportSQLiteStatement.bindNull(27);
                    }
                    if (customizedMetadataEntity.untethered_case_icon == null) {
                        supportSQLiteStatement.bindNull(28);
                    } else {
                        supportSQLiteStatement.bindBlob(28, customizedMetadataEntity.untethered_case_icon);
                    }
                    if (customizedMetadataEntity.untethered_left_battery == null) {
                        supportSQLiteStatement.bindNull(29);
                    } else {
                        supportSQLiteStatement.bindBlob(29, customizedMetadataEntity.untethered_left_battery);
                    }
                    if (customizedMetadataEntity.untethered_right_battery == null) {
                        supportSQLiteStatement.bindNull(30);
                    } else {
                        supportSQLiteStatement.bindBlob(30, customizedMetadataEntity.untethered_right_battery);
                    }
                    if (customizedMetadataEntity.untethered_case_battery == null) {
                        supportSQLiteStatement.bindNull(31);
                    } else {
                        supportSQLiteStatement.bindBlob(31, customizedMetadataEntity.untethered_case_battery);
                    }
                    if (customizedMetadataEntity.untethered_left_charging == null) {
                        supportSQLiteStatement.bindNull(32);
                    } else {
                        supportSQLiteStatement.bindBlob(32, customizedMetadataEntity.untethered_left_charging);
                    }
                    if (customizedMetadataEntity.untethered_right_charging == null) {
                        supportSQLiteStatement.bindNull(33);
                    } else {
                        supportSQLiteStatement.bindBlob(33, customizedMetadataEntity.untethered_right_charging);
                    }
                    if (customizedMetadataEntity.untethered_case_charging == null) {
                        supportSQLiteStatement.bindNull(34);
                    } else {
                        supportSQLiteStatement.bindBlob(34, customizedMetadataEntity.untethered_case_charging);
                    }
                    if (customizedMetadataEntity.enhanced_settings_ui_uri == null) {
                        supportSQLiteStatement.bindNull(35);
                        return;
                    } else {
                        supportSQLiteStatement.bindBlob(35, customizedMetadataEntity.enhanced_settings_ui_uri);
                        return;
                    }
                }
                supportSQLiteStatement.bindNull(19);
                supportSQLiteStatement.bindNull(20);
                supportSQLiteStatement.bindNull(21);
                supportSQLiteStatement.bindNull(22);
                supportSQLiteStatement.bindNull(23);
                supportSQLiteStatement.bindNull(24);
                supportSQLiteStatement.bindNull(25);
                supportSQLiteStatement.bindNull(26);
                supportSQLiteStatement.bindNull(27);
                supportSQLiteStatement.bindNull(28);
                supportSQLiteStatement.bindNull(29);
                supportSQLiteStatement.bindNull(30);
                supportSQLiteStatement.bindNull(31);
                supportSQLiteStatement.bindNull(32);
                supportSQLiteStatement.bindNull(33);
                supportSQLiteStatement.bindNull(34);
                supportSQLiteStatement.bindNull(35);
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) { // from class: com.android.bluetooth.btservice.storage.MetadataDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM metadata WHERE address = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.android.bluetooth.btservice.storage.MetadataDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM metadata";
            }
        };
    }

    @Override // com.android.bluetooth.btservice.storage.MetadataDao
    public void insert(Metadata... metadata) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMetadata.insert(metadata);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.android.bluetooth.btservice.storage.MetadataDao
    public void delete(String address) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDelete.acquire();
        if (address == null) {
            _stmt.bindNull(1);
        } else {
            _stmt.bindString(1, address);
        }
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(_stmt);
        }
    }

    @Override // com.android.bluetooth.btservice.storage.MetadataDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(_stmt);
        }
    }

    @Override // com.android.bluetooth.btservice.storage.MetadataDao
    public List<Metadata> load() throws Throwable {
        RoomSQLiteQuery _statement;
        int _cursorIndexOfPbapClientConnectionPolicy;
        int _cursorIndexOfAddress;
        int _cursorIndexOfA2dpConnectionPolicy;
        ProfilePrioritiesEntity _tmpProfileConnectionPolicies;
        int _cursorIndexOfMigrated;
        boolean z;
        int _cursorIndexOfLastActiveTime;
        boolean z2;
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT * FROM metadata ORDER BY last_active_time DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfAddress2 = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
            int _cursorIndexOfMigrated2 = CursorUtil.getColumnIndexOrThrow(_cursor, "migrated");
            int _cursorIndexOfA2dpSupportsOptionalCodecs = CursorUtil.getColumnIndexOrThrow(_cursor, "a2dpSupportsOptionalCodecs");
            int _cursorIndexOfA2dpOptionalCodecsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "a2dpOptionalCodecsEnabled");
            int _cursorIndexOfLastActiveTime2 = CursorUtil.getColumnIndexOrThrow(_cursor, "last_active_time");
            int _cursorIndexOfIsActiveA2dpDevice = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active_a2dp_device");
            int _cursorIndexOfA2dpConnectionPolicy2 = CursorUtil.getColumnIndexOrThrow(_cursor, "a2dp_connection_policy");
            int _cursorIndexOfA2dpSinkConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "a2dp_sink_connection_policy");
            int _cursorIndexOfHfpConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "hfp_connection_policy");
            int _cursorIndexOfHfpClientConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "hfp_client_connection_policy");
            int _cursorIndexOfHidHostConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "hid_host_connection_policy");
            int _cursorIndexOfIsActiveA2dpDevice2 = CursorUtil.getColumnIndexOrThrow(_cursor, "pan_connection_policy");
            int _cursorIndexOfPbapConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "pbap_connection_policy");
            try {
                _cursorIndexOfPbapClientConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "pbap_client_connection_policy");
                _statement = _statement2;
            } catch (Throwable th) {
                th = th;
                _statement = _statement2;
            }
            try {
                int _tmp_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "map_connection_policy");
                int _cursorIndexOfLastActiveTime3 = _cursorIndexOfIsActiveA2dpDevice;
                int _cursorIndexOfSapConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "sap_connection_policy");
                int _cursorIndexOfMapConnectionPolicy = _cursorIndexOfLastActiveTime2;
                int _cursorIndexOfHearingAidConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "hearing_aid_connection_policy");
                int _cursorIndexOfA2dpSupportsOptionalCodecs2 = _cursorIndexOfA2dpOptionalCodecsEnabled;
                int _cursorIndexOfMapClientConnectionPolicy = CursorUtil.getColumnIndexOrThrow(_cursor, "map_client_connection_policy");
                int _cursorIndexOfPanConnectionPolicy = _cursorIndexOfA2dpSupportsOptionalCodecs;
                int _cursorIndexOfManufacturerName = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer_name");
                int _tmp = _cursorIndexOfMigrated2;
                int _cursorIndexOfModelName = CursorUtil.getColumnIndexOrThrow(_cursor, "model_name");
                int _cursorIndexOfManufacturerName2 = _cursorIndexOfModelName;
                int _cursorIndexOfSoftwareVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "software_version");
                int _cursorIndexOfModelName2 = _cursorIndexOfSoftwareVersion;
                int _cursorIndexOfHardwareVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "hardware_version");
                int _cursorIndexOfSoftwareVersion2 = _cursorIndexOfHardwareVersion;
                int _cursorIndexOfCompanionApp = CursorUtil.getColumnIndexOrThrow(_cursor, "companion_app");
                int _cursorIndexOfHardwareVersion2 = _cursorIndexOfCompanionApp;
                int _cursorIndexOfMainIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "main_icon");
                int _cursorIndexOfCompanionApp2 = _cursorIndexOfMainIcon;
                int _cursorIndexOfIsUntetheredHeadset = CursorUtil.getColumnIndexOrThrow(_cursor, "is_untethered_headset");
                int _cursorIndexOfMainIcon2 = _cursorIndexOfIsUntetheredHeadset;
                int _cursorIndexOfUntetheredLeftIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_left_icon");
                int _cursorIndexOfIsUntetheredHeadset2 = _cursorIndexOfUntetheredLeftIcon;
                int _cursorIndexOfUntetheredRightIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_right_icon");
                int _cursorIndexOfUntetheredLeftIcon2 = _cursorIndexOfUntetheredRightIcon;
                int _cursorIndexOfUntetheredCaseIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_case_icon");
                int _cursorIndexOfUntetheredRightIcon2 = _cursorIndexOfUntetheredCaseIcon;
                int _cursorIndexOfUntetheredLeftBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_left_battery");
                int _cursorIndexOfUntetheredCaseIcon2 = _cursorIndexOfUntetheredLeftBattery;
                int _cursorIndexOfUntetheredRightBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_right_battery");
                int _cursorIndexOfUntetheredLeftBattery2 = _cursorIndexOfUntetheredRightBattery;
                int _cursorIndexOfUntetheredCaseBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_case_battery");
                int _cursorIndexOfUntetheredRightBattery2 = _cursorIndexOfUntetheredCaseBattery;
                int _cursorIndexOfUntetheredLeftCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_left_charging");
                int _cursorIndexOfUntetheredCaseBattery2 = _cursorIndexOfUntetheredLeftCharging;
                int _cursorIndexOfUntetheredRightCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_right_charging");
                int _cursorIndexOfUntetheredLeftCharging2 = _cursorIndexOfUntetheredRightCharging;
                int _cursorIndexOfUntetheredCaseCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "untethered_case_charging");
                int _cursorIndexOfUntetheredRightCharging2 = _cursorIndexOfUntetheredCaseCharging;
                int _cursorIndexOfUntetheredCaseCharging2 = CursorUtil.getColumnIndexOrThrow(_cursor, "enhanced_settings_ui_uri");
                int _cursorIndexOfPbapClientConnectionPolicy2 = _cursorIndexOfManufacturerName;
                int _cursorIndexOfManufacturerName3 = _cursor.getCount();
                List<Metadata> _result = new ArrayList<>(_cursorIndexOfManufacturerName3);
                while (_cursor.moveToNext()) {
                    String _tmpAddress = _cursor.getString(_cursorIndexOfAddress2);
                    if (!_cursor.isNull(_cursorIndexOfA2dpConnectionPolicy2) || !_cursor.isNull(_cursorIndexOfA2dpSinkConnectionPolicy) || !_cursor.isNull(_cursorIndexOfHfpConnectionPolicy) || !_cursor.isNull(_cursorIndexOfHfpClientConnectionPolicy) || !_cursor.isNull(_cursorIndexOfHidHostConnectionPolicy) || !_cursor.isNull(_cursorIndexOfIsActiveA2dpDevice2) || !_cursor.isNull(_cursorIndexOfPbapConnectionPolicy) || !_cursor.isNull(_cursorIndexOfPbapClientConnectionPolicy) || !_cursor.isNull(_tmp_1) || !_cursor.isNull(_cursorIndexOfSapConnectionPolicy) || !_cursor.isNull(_cursorIndexOfHearingAidConnectionPolicy) || !_cursor.isNull(_cursorIndexOfMapClientConnectionPolicy)) {
                        ProfilePrioritiesEntity _tmpProfileConnectionPolicies2 = new ProfilePrioritiesEntity();
                        _cursorIndexOfAddress = _cursorIndexOfAddress2;
                        int _cursorIndexOfAddress3 = _cursor.getInt(_cursorIndexOfA2dpConnectionPolicy2);
                        _cursorIndexOfA2dpConnectionPolicy = _cursorIndexOfA2dpConnectionPolicy2;
                        _tmpProfileConnectionPolicies = _tmpProfileConnectionPolicies2;
                        _tmpProfileConnectionPolicies.a2dp_connection_policy = _cursorIndexOfAddress3;
                        _tmpProfileConnectionPolicies.a2dp_sink_connection_policy = _cursor.getInt(_cursorIndexOfA2dpSinkConnectionPolicy);
                        _tmpProfileConnectionPolicies.hfp_connection_policy = _cursor.getInt(_cursorIndexOfHfpConnectionPolicy);
                        _tmpProfileConnectionPolicies.hfp_client_connection_policy = _cursor.getInt(_cursorIndexOfHfpClientConnectionPolicy);
                        _tmpProfileConnectionPolicies.hid_host_connection_policy = _cursor.getInt(_cursorIndexOfHidHostConnectionPolicy);
                        _tmpProfileConnectionPolicies.pan_connection_policy = _cursor.getInt(_cursorIndexOfIsActiveA2dpDevice2);
                        _tmpProfileConnectionPolicies.pbap_connection_policy = _cursor.getInt(_cursorIndexOfPbapConnectionPolicy);
                        _tmpProfileConnectionPolicies.pbap_client_connection_policy = _cursor.getInt(_cursorIndexOfPbapClientConnectionPolicy);
                        _tmpProfileConnectionPolicies.map_connection_policy = _cursor.getInt(_tmp_1);
                        _tmpProfileConnectionPolicies.sap_connection_policy = _cursor.getInt(_cursorIndexOfSapConnectionPolicy);
                        _tmpProfileConnectionPolicies.hearing_aid_connection_policy = _cursor.getInt(_cursorIndexOfHearingAidConnectionPolicy);
                        _tmpProfileConnectionPolicies.map_client_connection_policy = _cursor.getInt(_cursorIndexOfMapClientConnectionPolicy);
                    } else {
                        _cursorIndexOfA2dpConnectionPolicy = _cursorIndexOfA2dpConnectionPolicy2;
                        _tmpProfileConnectionPolicies = null;
                        _cursorIndexOfAddress = _cursorIndexOfAddress2;
                    }
                    CustomizedMetadataEntity _tmpPublicMetadata = new CustomizedMetadataEntity();
                    int _cursorIndexOfPbapConnectionPolicy2 = _cursorIndexOfPbapConnectionPolicy;
                    int _cursorIndexOfPbapConnectionPolicy3 = _cursorIndexOfPbapClientConnectionPolicy2;
                    int _cursorIndexOfManufacturerName4 = _cursorIndexOfPbapClientConnectionPolicy;
                    _tmpPublicMetadata.manufacturer_name = _cursor.getBlob(_cursorIndexOfPbapConnectionPolicy3);
                    int _cursorIndexOfModelName3 = _cursorIndexOfManufacturerName2;
                    _tmpPublicMetadata.model_name = _cursor.getBlob(_cursorIndexOfModelName3);
                    int _cursorIndexOfSoftwareVersion3 = _cursorIndexOfModelName2;
                    _tmpPublicMetadata.software_version = _cursor.getBlob(_cursorIndexOfSoftwareVersion3);
                    int _cursorIndexOfHardwareVersion3 = _cursorIndexOfSoftwareVersion2;
                    _tmpPublicMetadata.hardware_version = _cursor.getBlob(_cursorIndexOfHardwareVersion3);
                    int _cursorIndexOfCompanionApp3 = _cursorIndexOfHardwareVersion2;
                    _tmpPublicMetadata.companion_app = _cursor.getBlob(_cursorIndexOfCompanionApp3);
                    int _cursorIndexOfMainIcon3 = _cursorIndexOfCompanionApp2;
                    _tmpPublicMetadata.main_icon = _cursor.getBlob(_cursorIndexOfMainIcon3);
                    int _cursorIndexOfIsUntetheredHeadset3 = _cursorIndexOfMainIcon2;
                    _tmpPublicMetadata.is_untethered_headset = _cursor.getBlob(_cursorIndexOfIsUntetheredHeadset3);
                    int _cursorIndexOfUntetheredLeftIcon3 = _cursorIndexOfIsUntetheredHeadset2;
                    _tmpPublicMetadata.untethered_left_icon = _cursor.getBlob(_cursorIndexOfUntetheredLeftIcon3);
                    int _cursorIndexOfUntetheredRightIcon3 = _cursorIndexOfUntetheredLeftIcon2;
                    _tmpPublicMetadata.untethered_right_icon = _cursor.getBlob(_cursorIndexOfUntetheredRightIcon3);
                    int _cursorIndexOfUntetheredCaseIcon3 = _cursorIndexOfUntetheredRightIcon2;
                    _tmpPublicMetadata.untethered_case_icon = _cursor.getBlob(_cursorIndexOfUntetheredCaseIcon3);
                    int _cursorIndexOfUntetheredLeftBattery3 = _cursorIndexOfUntetheredCaseIcon2;
                    _tmpPublicMetadata.untethered_left_battery = _cursor.getBlob(_cursorIndexOfUntetheredLeftBattery3);
                    int _cursorIndexOfUntetheredRightBattery3 = _cursorIndexOfUntetheredLeftBattery2;
                    _tmpPublicMetadata.untethered_right_battery = _cursor.getBlob(_cursorIndexOfUntetheredRightBattery3);
                    int _cursorIndexOfUntetheredCaseBattery3 = _cursorIndexOfUntetheredRightBattery2;
                    _tmpPublicMetadata.untethered_case_battery = _cursor.getBlob(_cursorIndexOfUntetheredCaseBattery3);
                    int _cursorIndexOfUntetheredLeftCharging3 = _cursorIndexOfUntetheredCaseBattery2;
                    _tmpPublicMetadata.untethered_left_charging = _cursor.getBlob(_cursorIndexOfUntetheredLeftCharging3);
                    int _cursorIndexOfUntetheredRightCharging3 = _cursorIndexOfUntetheredLeftCharging2;
                    _tmpPublicMetadata.untethered_right_charging = _cursor.getBlob(_cursorIndexOfUntetheredRightCharging3);
                    int _cursorIndexOfUntetheredCaseCharging3 = _cursorIndexOfUntetheredRightCharging2;
                    _tmpPublicMetadata.untethered_case_charging = _cursor.getBlob(_cursorIndexOfUntetheredCaseCharging3);
                    int _cursorIndexOfEnhancedSettingsUiUri = _cursorIndexOfUntetheredCaseCharging2;
                    _tmpPublicMetadata.enhanced_settings_ui_uri = _cursor.getBlob(_cursorIndexOfEnhancedSettingsUiUri);
                    Metadata _item = new Metadata(_tmpAddress);
                    int _cursorIndexOfEnhancedSettingsUiUri2 = _tmp;
                    int _tmp2 = _cursor.getInt(_cursorIndexOfEnhancedSettingsUiUri2);
                    if (_tmp2 != 0) {
                        _cursorIndexOfMigrated = _cursorIndexOfEnhancedSettingsUiUri2;
                        z = true;
                    } else {
                        _cursorIndexOfMigrated = _cursorIndexOfEnhancedSettingsUiUri2;
                        z = false;
                    }
                    _item.migrated = z;
                    int _cursorIndexOfA2dpSupportsOptionalCodecs3 = _cursorIndexOfPanConnectionPolicy;
                    int _cursorIndexOfA2dpSupportsOptionalCodecs4 = _cursorIndexOfIsActiveA2dpDevice2;
                    int _cursorIndexOfPanConnectionPolicy2 = _cursor.getInt(_cursorIndexOfA2dpSupportsOptionalCodecs3);
                    _item.a2dpSupportsOptionalCodecs = _cursorIndexOfPanConnectionPolicy2;
                    int _cursorIndexOfA2dpOptionalCodecsEnabled2 = _cursorIndexOfA2dpSupportsOptionalCodecs2;
                    int _cursorIndexOfA2dpSupportsOptionalCodecs5 = _cursor.getInt(_cursorIndexOfA2dpOptionalCodecsEnabled2);
                    _item.a2dpOptionalCodecsEnabled = _cursorIndexOfA2dpSupportsOptionalCodecs5;
                    int _cursorIndexOfLastActiveTime4 = _cursorIndexOfMapConnectionPolicy;
                    int _cursorIndexOfLastActiveTime5 = _tmp_1;
                    _item.last_active_time = _cursor.getLong(_cursorIndexOfLastActiveTime4);
                    int _cursorIndexOfIsActiveA2dpDevice3 = _cursorIndexOfLastActiveTime3;
                    int _tmp_12 = _cursor.getInt(_cursorIndexOfIsActiveA2dpDevice3);
                    if (_tmp_12 != 0) {
                        _cursorIndexOfLastActiveTime = _cursorIndexOfLastActiveTime4;
                        z2 = true;
                    } else {
                        _cursorIndexOfLastActiveTime = _cursorIndexOfLastActiveTime4;
                        z2 = false;
                    }
                    _item.is_active_a2dp_device = z2;
                    _item.profileConnectionPolicies = _tmpProfileConnectionPolicies;
                    _item.publicMetadata = _tmpPublicMetadata;
                    _result.add(_item);
                    _tmp_1 = _cursorIndexOfLastActiveTime5;
                    _cursorIndexOfPbapClientConnectionPolicy = _cursorIndexOfManufacturerName4;
                    _cursorIndexOfAddress2 = _cursorIndexOfAddress;
                    _cursorIndexOfPbapConnectionPolicy = _cursorIndexOfPbapConnectionPolicy2;
                    _cursorIndexOfA2dpConnectionPolicy2 = _cursorIndexOfA2dpConnectionPolicy;
                    _tmp = _cursorIndexOfMigrated;
                    _cursorIndexOfMapConnectionPolicy = _cursorIndexOfLastActiveTime;
                    _cursorIndexOfPbapClientConnectionPolicy2 = _cursorIndexOfPbapConnectionPolicy3;
                    _cursorIndexOfManufacturerName2 = _cursorIndexOfModelName3;
                    _cursorIndexOfModelName2 = _cursorIndexOfSoftwareVersion3;
                    _cursorIndexOfSoftwareVersion2 = _cursorIndexOfHardwareVersion3;
                    _cursorIndexOfHardwareVersion2 = _cursorIndexOfCompanionApp3;
                    _cursorIndexOfCompanionApp2 = _cursorIndexOfMainIcon3;
                    _cursorIndexOfMainIcon2 = _cursorIndexOfIsUntetheredHeadset3;
                    _cursorIndexOfIsUntetheredHeadset2 = _cursorIndexOfUntetheredLeftIcon3;
                    _cursorIndexOfUntetheredLeftIcon2 = _cursorIndexOfUntetheredRightIcon3;
                    _cursorIndexOfUntetheredRightIcon2 = _cursorIndexOfUntetheredCaseIcon3;
                    _cursorIndexOfUntetheredCaseIcon2 = _cursorIndexOfUntetheredLeftBattery3;
                    _cursorIndexOfUntetheredLeftBattery2 = _cursorIndexOfUntetheredRightBattery3;
                    _cursorIndexOfUntetheredRightBattery2 = _cursorIndexOfUntetheredCaseBattery3;
                    _cursorIndexOfUntetheredCaseBattery2 = _cursorIndexOfUntetheredLeftCharging3;
                    _cursorIndexOfUntetheredLeftCharging2 = _cursorIndexOfUntetheredRightCharging3;
                    _cursorIndexOfUntetheredRightCharging2 = _cursorIndexOfUntetheredCaseCharging3;
                    _cursorIndexOfUntetheredCaseCharging2 = _cursorIndexOfEnhancedSettingsUiUri;
                    _cursorIndexOfLastActiveTime3 = _cursorIndexOfIsActiveA2dpDevice3;
                    _cursorIndexOfIsActiveA2dpDevice2 = _cursorIndexOfA2dpSupportsOptionalCodecs4;
                    _cursorIndexOfPanConnectionPolicy = _cursorIndexOfA2dpSupportsOptionalCodecs3;
                    _cursorIndexOfA2dpSupportsOptionalCodecs2 = _cursorIndexOfA2dpOptionalCodecsEnabled2;
                }
                _cursor.close();
                _statement.release();
                return _result;
            } catch (Throwable th2) {
                th = th2;
                _cursor.close();
                _statement.release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            _statement = _statement2;
        }
    }
}
