package com.ts.dm.lib.config;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManagerConfig {
    private static final int DEVICE_LIST_MAX_SIZE = 21;
    public static final String TAG = "DeviceManagerConfig";
    private static boolean mEnableIndexOfSameDeviceName = true;
    private static boolean mEnableCarPlay = true;
    private static boolean mEnableAndroidAuto = true;

    public static void init(Config config) {
        if (config == null) {
            return;
        }
        mEnableIndexOfSameDeviceName = config.isEnableIndexOfSameDeviceName();
        mEnableCarPlay = config.isCarPlayEnable();
        mEnableAndroidAuto = config.isAndroidAutoEnable();
    }

    public static int getDeviceListMaxSize() {
        return 21;
    }

    public static boolean isEnableIndexOfSameDeviceName() {
        return mEnableIndexOfSameDeviceName;
    }

    public static boolean isCarPlayEnable() {
        return mEnableCarPlay;
    }

    public static boolean isAndroidAutoEnable() {
        return mEnableAndroidAuto;
    }
}
