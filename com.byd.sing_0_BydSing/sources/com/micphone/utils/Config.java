package com.micphone.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Config {
    public static final String HUAWEI_E11 = "ICSVE11-HI";
    public static final String HUAWEI_M5 = "ICSA-S106";
    public static final String HUAWEI_M7 = "ICSA-F1";
    public static final String HUAWEI_N61 = "ICSVN61-HI";
    public static final String KT_CDC_0203_NAME = "KTMICRO_USB_AUDIO";
    public static final String KT_CDC_0712_NAME = "KT Virtual Com Port";
    public static int KT_CDC_PID = 52672;
    public static int KT_CDC_VID = 34952;
    public static int KT_LOADER_PID = 1;
    public static int KT_LOADER_VID = 12722;
    public static int MIC_PID = 2;
    public static int MIC_VID = 4661;
    private static final String TAG = "Config";
    public static int ZERO_PID = 0;
    public static int ZERO_VID = 0;
    private static boolean mIsSsytemUid = true;
    private static boolean mIsSupportChangeTouchSettings = false;
    private static boolean mNeedCheckMicphoneName = true;
    private static boolean mUseAudioRecordDrive = false;
    private static boolean mUseNativeLoopbackDrive = true;
    private static Map<Integer, Integer> mDeviceList = new HashMap<Integer, Integer>() { // from class: com.micphone.utils.Config.1
        {
            put(Integer.valueOf(Config.ZERO_VID), Integer.valueOf(Config.ZERO_PID));
            put(Integer.valueOf(Config.MIC_VID), Integer.valueOf(Config.MIC_PID));
            put(Integer.valueOf(Config.KT_CDC_VID), Integer.valueOf(Config.KT_CDC_PID));
            put(Integer.valueOf(Config.KT_LOADER_VID), Integer.valueOf(Config.KT_LOADER_PID));
        }
    };
    public static final ArrayList<String> DONGLE_DEFAULT = new ArrayList<>(Arrays.asList("S10", "K10"));
    public static final ArrayList<String> DONGLE_LING_KE = new ArrayList<>(Arrays.asList("S25", "K109"));
    public static final ArrayList<String> DONGLE_BYD = new ArrayList<>(Arrays.asList("TS-D001", "BYD-micTS02"));
    public static final ArrayList<String> DONGLE_DENZA = new ArrayList<>(Arrays.asList("BYD-micTS03"));
    public static final ArrayList<String> DONGLE_CHANGCHENG = new ArrayList<>(Arrays.asList("S10", "K107"));
    public static final ArrayList<String> DONGLE_HUAWEI_JIHU = new ArrayList<>(Arrays.asList("K105", "S105"));
    public static final ArrayList<String> DONGLE_HUAWEI_SAILISI = new ArrayList<>(Arrays.asList("K101", "S101"));
    public static final ArrayList<String> DONGLE_HUAWEI_AWEITA = new ArrayList<>(Arrays.asList("K102", "S102"));

    public static String getDeviceValue(Context context, String str) {
        String str2;
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            str2 = (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, new String(str));
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            str2 = BuildConfig.FLAVOR;
        }
        Log.e(TAG, "devices:" + str2);
        return str2;
    }

    public static String getProductType() {
        return "byd";
    }

    public static String getVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return BuildConfig.FLAVOR;
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean isAudioRecordDrive() {
        return mUseAudioRecordDrive;
    }

    private static boolean isDisableLoopbackByProduct() {
        String productType = getProductType();
        Log.d(TAG, "product=" + productType);
        if (TextUtils.isEmpty(productType)) {
            return false;
        }
        return productType.contains("huawei") || productType.contains("changcheng") || productType.equals("byd") || productType.contains("lingKe06") || productType.contains("baoteng11");
    }

    public static boolean isFrontService() {
        return false;
    }

    public static boolean isKtCdc(String str) {
        return KT_CDC_0203_NAME.equals(str) || KT_CDC_0712_NAME.equals(str);
    }

    public static boolean isNativeLoopbackDrive() {
        if (isDisableLoopbackByProduct()) {
            return false;
        }
        return mUseNativeLoopbackDrive;
    }

    public static boolean isSupportChangeTouchSettings() {
        return mIsSupportChangeTouchSettings;
    }

    public static boolean isSupportDeviceId(int i, int i2) {
        if (mDeviceList.isEmpty()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : mDeviceList.entrySet()) {
            if (entry.getKey().intValue() == i && entry.getValue().intValue() == i2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupportLaunchKTVApp() {
        return false;
    }

    public static boolean isSystemUid() {
        return mIsSsytemUid;
    }

    public static boolean needCheckMicphoneName() {
        if (noCheckMicNameByProduct()) {
            return false;
        }
        return mNeedCheckMicphoneName;
    }

    private static boolean noCheckMicNameByProduct() {
        String productType = getProductType();
        Log.d(TAG, "product=" + productType);
        if (TextUtils.isEmpty(productType)) {
            return false;
        }
        return productType.contains("lingKe06") || productType.contains("a40") || productType.contains("baoteng11");
    }

    public static void setAudioRecordDrive(boolean z) {
        mUseAudioRecordDrive = z;
        mUseNativeLoopbackDrive = false;
    }
}
