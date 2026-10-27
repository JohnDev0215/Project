package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.SharedPreferences;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.manager.SystemSettingManager;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPreferencesUtil {
    public static final String KAI_SHU = "BYD_CONFIG";
    private static final String TAG = "NEU_SharedPreferencesUtil";
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getInstance(Context context) {
        RLog.d(TAG, "SharedPreferences: getInstance");
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KAI_SHU, 0);
        }
        return sharedPreferences;
    }

    public static void putCurrentArea(Context context, String str) {
        try {
            RLog.d(TAG, "putCurrentArea: " + str);
            if (str == null || "".equals(str)) {
                str = SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT;
            }
            getInstance(context).edit().putString("area", str).apply();
        } catch (Exception e) {
            RLog.d(TAG, "putCurrentArea: ", e);
        }
    }

    public static String getCurrentArea(Context context) {
        try {
            String string = getInstance(context).getString("area", "");
            if ("".equals(string)) {
                string = SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT;
            }
            RLog.d(TAG, "getCurrentArea: " + string);
            return string;
        } catch (Exception e) {
            RLog.d(TAG, "getCurrentArea: ", e);
            return "";
        }
    }

    public static void putDefaultRouteOption(Context context, int i) {
        try {
            RLog.d(TAG, "putDefaultRouteOption: " + i);
            getInstance(context).edit().putInt("defaultRouteOption", i).apply();
        } catch (Exception e) {
            RLog.d(TAG, "putDefaultRouteOption: ", e);
        }
    }

    public static int getDefaultRouteOption(Context context) {
        try {
            int i = getInstance(context).getInt("defaultRouteOption", 0);
            RLog.d(TAG, "getDefaultRouteOption: " + i);
            return i;
        } catch (Exception e) {
            RLog.d(TAG, "getDefaultRouteOption: ", e);
            return 0;
        }
    }

    public static void putGuide(Context context, boolean z) {
        try {
            RLog.d(TAG, "putGuide: " + z);
            getInstance(context).edit().putBoolean(Constant.BUSINESS_GUIDE, z).apply();
        } catch (Exception e) {
            RLog.d(TAG, "putGuide: ", e);
        }
    }

    public static Boolean getGuide(Context context) {
        try {
            Boolean boolValueOf = Boolean.valueOf(getInstance(context).getBoolean(Constant.BUSINESS_GUIDE, true));
            RLog.d(TAG, "getCurrentArea: " + boolValueOf);
            return boolValueOf;
        } catch (Exception e) {
            RLog.d(TAG, "getGuide: ", e);
            return true;
        }
    }

    public static String getVoiceSetting(Context context, String str) {
        try {
            String string = getInstance(context).getString(str, "");
            RLog.d(TAG, "getVoiceSetting: " + string);
            return string;
        } catch (Exception e) {
            RLog.d(TAG, "getVoiceSetting: ", e);
            return "";
        }
    }

    public static void putVoiceSetting(Context context, String str, String str2) {
        try {
            RLog.d(TAG, "putVoiceSetting: " + str);
            if (str != null && !str.isEmpty()) {
                RLog.d(TAG, "putVoiceSetting: " + str2);
                getInstance(context).edit().putString(str, str2).apply();
            }
        } catch (Exception e) {
            RLog.d(TAG, "putVoiceSetting: ", e);
        }
    }

    public static boolean getFreeWakeupWords(Context context, String str) {
        boolean zEquals = Constant.KEY_TEL_CONTROL.equals(str);
        try {
            boolean z = getInstance(context).getBoolean(str, zEquals);
            RLog.d(TAG, "getFreeWakeupWords: " + z);
            return z;
        } catch (Exception e) {
            RLog.d(TAG, "getFreeWakeupWords: ", e);
            RLog.d(TAG, "getFreeWakeupWords defalutValue: " + zEquals);
            return zEquals;
        }
    }

    public static void putFreeWakeupWords(Context context, String str, boolean z) {
        try {
            RLog.d(TAG, "putFreeWakeupWords: " + str);
            if (str != null && !str.isEmpty()) {
                RLog.d(TAG, "putFreeWakeupWords: " + z);
                getInstance(context).edit().putBoolean(str, z).apply();
            }
        } catch (Exception e) {
            RLog.d(TAG, "putFreeWakeupWords: ", e);
        }
    }
}
