package com.ts.car.dcm.dccontroller.storemanager;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IDataBaseOperate {
    public static final String COLUMN_ALWAYS_CAPABILITY = "ALWAYS_CAPABILITY";
    public static final String COLUMN_BLUETOOTH_PAIRED = "BLUETOOTH_PAIRED";
    public static final String COLUMN_BTADDR = "BTADDR";
    public static final String COLUMN_BT_DEVICE_PAIRED_TIME = "BT_DEVICE_PAIRED_TIME";
    public static final String COLUMN_CPWL_OVER_IAP_DETECTED = "CPWL_OVER_IAP_DETECTED";
    public static final String COLUMN_CPWL_OVER_IAP_DETECTED_NOTIFIED = "CPWL_OVER_IAP_DETECTED_NOTIFIED";
    public static final String COLUMN_DEVICE_CAPABILITY = "DEVICE_CAPABILITY";
    public static final String COLUMN_DEVICE_FRIENDLY_NAME = "DEVICE_FRIENDLY_NAME";
    public static final String COLUMN_DEVICE_IS_ANDROIDAUTO_FIRST = "IS_ANDROIDAUTO_FIRST";
    public static final String COLUMN_DEVICE_IS_CARLIFE_FIRST = "IS_CARLIFE_FIRST";
    public static final String COLUMN_DEVICE_IS_CARPLAY_FIRST = "IS_CARPLAY_FIRST";
    public static final String COLUMN_DEVICE_IS_WIRELESS_AA_REQUESTED = "IS_WIRELESS_AA_REQUESTED";
    public static final String COLUMN_DEVICE_TYPE = "DEVICE_TYPE";
    public static final String COLUMN_DEVICE_UUID = "DEVICE_UUID";
    public static final String COLUMN_FAVORTE = "FAVORTE";
    public static final String COLUMN_INDEX_OF_SAME_DEVICE_NAME = "INDEX_OF_SAME_DEVICE_NAME";
    public static final String COLUMN_IS_LAST_PROJECTION_DEV = "IS_LAST_PROJECTION_DEV";
    public static final String COLUMN_LAST_ONLINE_TIME = "LAST_ONLINE_TIME";
    public static final String COLUMN_LAST_SERVICE = "LAST_SERVICE";
    public static final String COLUMN_MOBILE_DEVICE_ID = "MOBILE_DEVICE_ID";
    public static final String COLUMN_TYPE_BLOB = "BLOB";
    public static final String COLUMN_TYPE_INTERGER = "INT";
    public static final String COLUMN_TYPE_TEXT = "TEXT";
    public static final String COLUMN_TYPE_TINYINT = "TINYINT(1)";
    public static final String COLUMN_USB_ANDROID_AUTO_CONNECTED_TIME = "USB_ANDROID_AUTO_CONNECTED_TIME";
    public static final String COLUMN_USB_CARPLAY_CONNECTED_TIME = "USB_CARPLAY_CONNECTED_TIME";
    public static final String COLUMN_USB_SERIAL_NUMBER = "USB_SERIAL_NUMBER";
    public static final String COLUMN_WIFI_MAC_ADDR = "WIFI_MAC_ADDR";
    public static final String COLUMN_WIRELESS_ANDROID_AUTO_CONNECTED_TIME = "WIRELESS_ANDROID_AUTO_CONNECTED_TIME";
    public static final String COLUMN_WIRELESS_CARPLAY_CONNECTED_TIME = "WIRELESS_CARPLAY_CONNECTED_TIME";
    public static final int DATABASE_DELETE_MAX = 10;
    public static final int DATABASE_LIMIT = 100;
    public static final String DATABASE_NAME = "DB_DCMDEVICE.db";
    public static final String TABLE_NAME = "TB_Device";

    void beginTransaction();

    boolean deleteAllDevice();

    boolean deleteDevice(String str);

    boolean deleteDeviceByOrderAscLimit(String str, String str2, int i);

    void endTransaction();

    int getDataCount();

    boolean insertAllDevice(Map<String, Device> map);

    boolean insertDevice(Device device);

    Map<String, Device> selectAllDevice();

    Device selectDevice(String str, String str2);

    List<Device> selectDeviceByOrderAscLimit(String str, String str2, int i);

    void setTransactionSuccessful();

    boolean updateDevice(Device device);
}
