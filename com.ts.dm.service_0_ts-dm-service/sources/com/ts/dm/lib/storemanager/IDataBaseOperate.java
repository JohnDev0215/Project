package com.ts.dm.lib.storemanager;

import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.data.DeviceRecord;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDataBaseOperate {
    public static final String ANDROID_AUTO_PROJECTION_STATE = "ANDROID_AUTO_PROJECTION_STATE";
    public static final String CARLIFE_PROJECTION_STATE = "CARLIFE_PROJECTION_STATE";
    public static final String CARPLAY_PROJECTION_STATE = "CARPLAY_PROJECTION_STATE";
    public static final String COLUMN_TYPE_INTERGER = "INT";
    public static final String COLUMN_TYPE_TEXT = "TEXT";
    public static final String DATABASE_NAME = "DB_DMDEVICE.db";
    public static final String FRIENDLY_NAME = "FRIENDLY_NAME";
    public static final String HICAR_PROJECTION_STATE = "HICAR_PROJECTION_STATE";
    public static final String INDEX_OF_SAME_DEVICE_NAME = "INDEX_OF_SAME_DEVICE_NAME";
    public static final String IS_PAIRD = "IS_PAIRD";
    public static final String LAST_PROFILE = "LAST_PROFILE";
    public static final String LAST_PROFILE_TIME = "LAST_PROFILE_TIME";
    public static final String MAC_ADDRESS = "MAC_ADDRESS";
    public static final String TABLE_NAME = "DM_Device";

    boolean deleteDevice(String str);

    List<DeviceRecord> queryAllDevices();

    DeviceRecord queryDevice(String str);

    void resetProjectionStates();

    boolean saveOrUpdateDeviceRecord(DmDevice dmDevice);
}
