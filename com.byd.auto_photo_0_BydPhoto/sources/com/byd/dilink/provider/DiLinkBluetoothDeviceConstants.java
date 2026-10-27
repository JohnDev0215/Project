package com.byd.dilink.provider;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkBluetoothDeviceConstants {
    public static final String AUTHORITY = "com.byd.bluetoothprovider";
    public static final int CLOSE = 0;
    public static final int DB_VERSION = 1;
    public static final String DEVICE_URL = "content://com.byd.bluetoothprovider/device";
    public static final int KEY_AUTO_ANSWER = 4;
    public static final int KEY_AUTO_CONNECT = 3;
    public static final int KEY_AVRCP = 1;
    public static final int KEY_PBAP = 2;
    public static final int KEY_PREFERRED = 5;
    public static final int KEY_TIME = 6;
    public static final int MAX_AUTO_CONNECT_DEVICE = 3;
    public static final int OPEN = 1;
    public static final String SORT_ORDER = "time desc";
    public static final String TAG = "RemoteDeiviceUtils";
    public static final String TIME = "time";
    public static final String _ID = "_id";
    private static final String[] black_list_for_audio_source_device = {"f6:d0:10"};
    public static final String DEVICE_PBAP = "use_pbap";
    public static final String DEVICE_AVRCP = "use_avrcp";
    public static final String DEVICE_AUTO_CONNECT = "auto_connect";
    public static final String DEVICE_AUTO_ANSWER = "auto_answer";
    public static final String DEVICE_PREFERRED = "is_preferred";
    private static final String[] PROJECTION_FILTERED_MEMBERS = {DEVICE_PBAP, DEVICE_AVRCP, DEVICE_AUTO_CONNECT, DEVICE_AUTO_ANSWER, DEVICE_PREFERRED, "time"};
    public static final String DEVICE_ADDRESS = "address";
    public static final String[] PROJECTION_FILTERED_TIME_MEMBERS = {DEVICE_ADDRESS, DEVICE_AUTO_CONNECT, DEVICE_PREFERRED, "time"};
    public static final String[] PROJECTION_CONNECT_TIME_MEMBERS = {DEVICE_ADDRESS, "time"};
}
