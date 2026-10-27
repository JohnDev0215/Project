package com.micphone;

import com.micphone.bean.TsBydDeviceInfo;
import com.micphone.bean.TsBydDongleInfo;
import com.micphone.bean.TsBydMicInfo;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class TsBydEventCustom {
    private static final String KEY_BASE_FLAG_1 = "&";
    private static final String KEY_BASE_FLAG_2 = "=";
    private static final String KEY_CUS_STR1 = "str1";
    private static final String KEY_CUS_STR2 = "str2";
    private static final String KEY_CUS_STR3 = "str3";
    private static final String KEY_CUS_STR4 = "str4";
    private static final String KEY_CUS_STR5 = "str5";
    private static final String KEY_CUS_STR6 = "str6";
    private static final String KEY_CUS_STR7 = "str7";
    private static final String KEY_CUS_STR8 = "str8";
    private static final String KEY_CUS_STR9 = "str9";
    private static final String TAG = "TsBydEventCustom";
    public static final int VALUE_CUS_STR1_CONNECT = 1001;
    public static final int VALUE_CUS_STR1_DIS_CONNECT = 1000;

    private static String addArgument(String str, Object obj) {
        return KEY_BASE_FLAG_1 + str + KEY_BASE_FLAG_2 + obj;
    }

    private static String getCusStr3() {
        return TsBydMicManager.SDK_VERSION_NAME;
    }

    private static String getCusStr4(TsBydMicManager tsBydMicManager) {
        TsBydDongleInfo tsBydDongleInfo;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (tsBydDongleInfo = tsBydDeviceInfo.mTsBydDongleInfo) == null) {
            return null;
        }
        return tsBydDongleInfo.version;
    }

    private static String getCusStr5(TsBydMicManager tsBydMicManager, int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (concurrentHashMap = tsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return concurrentHashMap.get(Integer.valueOf(i)).productName;
    }

    private static String getCusStr6(TsBydMicManager tsBydMicManager, int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (concurrentHashMap = tsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return concurrentHashMap.get(Integer.valueOf(i)).version;
    }

    private static int getCusStr7(TsBydMicManager tsBydMicManager, int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (concurrentHashMap = tsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return -1;
        }
        return concurrentHashMap.get(Integer.valueOf(i)).battery;
    }

    private static String getCusStr8(TsBydMicManager tsBydMicManager, int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (concurrentHashMap = tsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return null;
        }
        int i2 = concurrentHashMap.get(Integer.valueOf(i)).supplier;
        if (i2 == 2) {
            return "LS";
        }
        if (i2 != 3) {
            return null;
        }
        return "TH";
    }

    private static int getCusStr9(TsBydMicManager tsBydMicManager, int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        TsBydDeviceInfo tsBydDeviceInfo = tsBydMicManager.getmTsBydDeviceInfo();
        if (tsBydDeviceInfo == null || (concurrentHashMap = tsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return -1;
        }
        return concurrentHashMap.get(Integer.valueOf(i)).pairType;
    }

    public static String getCustomEvent(TsBydMicManager tsBydMicManager, int i, int i2) {
        tsBydMicManager.getContext();
        return addArgument(KEY_CUS_STR1, Integer.valueOf(i)) + addArgument(KEY_CUS_STR2, Integer.valueOf(i2)) + addArgument(KEY_CUS_STR3, getCusStr3()) + addArgument(KEY_CUS_STR4, getCusStr4(tsBydMicManager)) + addArgument(KEY_CUS_STR5, getCusStr5(tsBydMicManager, i2)) + addArgument(KEY_CUS_STR6, getCusStr6(tsBydMicManager, i2)) + addArgument(KEY_CUS_STR7, Integer.valueOf(getCusStr7(tsBydMicManager, i2))) + addArgument(KEY_CUS_STR8, getCusStr8(tsBydMicManager, i2)) + addArgument(KEY_CUS_STR9, Integer.valueOf(getCusStr9(tsBydMicManager, i2)));
    }
}
