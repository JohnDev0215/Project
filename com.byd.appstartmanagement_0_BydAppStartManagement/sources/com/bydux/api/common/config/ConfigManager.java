package com.bydux.api.common.config;

import android.content.Context;
import android.provider.Settings;
import com.bydux.api.common.config.ConfigKey;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.save.BydPropertyHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigManager {
    private static volatile ConfigManager mInstance;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigManager();
                }
            }
        }
        return mInstance;
    }

    private int getInt(Context context, String str, int i) {
        return Settings.System.getInt(context.getContentResolver(), str, i);
    }

    private String getString(Context context, String str) {
        return Settings.System.getString(context.getContentResolver(), str);
    }

    private void putInt(Context context, String str, int i) {
        Settings.System.putInt(context.getContentResolver(), str, i);
    }

    private void putString(Context context, String str, String str2) {
        Settings.System.putString(context.getContentResolver(), str, str2);
    }

    public void flashConfig(Context context) {
        if (context != null && !"com.android.systemui".equals(context.getPackageName())) {
            BydLogHelper.logV("仅供SystemUI调用");
            return;
        }
        BydLogHelper.logV("start");
        BydPropertyHelper bydPropertyHelper = new BydPropertyHelper(context);
        bydPropertyHelper.init();
        int property = bydPropertyHelper.getProperty(ConfigKey.CarConfigKey.KEY_CAR_TYPE, 0);
        BydLogHelper.logV("carType=" + property);
        putInt(context, ConfigKey.CarConfigKey.KEY_CAR_TYPE, property);
        int property2 = bydPropertyHelper.getProperty(ConfigKey.CarConfigKey.KEY_CAR_CONFIG, 2);
        BydLogHelper.logV("carConfig=" + property2);
        putInt(context, ConfigKey.CarConfigKey.KEY_CAR_CONFIG, property2);
        int property3 = bydPropertyHelper.getProperty(ConfigKey.LogConfigKey.KEY_LOGV_ENABLE, 1);
        BydLogHelper.logV("logVEnable=" + property3);
        putInt(context, ConfigKey.LogConfigKey.KEY_LOGV_ENABLE, property3);
        int property4 = bydPropertyHelper.getProperty(ConfigKey.LogConfigKey.KEY_LOGD_ENABLE, 0);
        BydLogHelper.logV("logdEnable=" + property4);
        putInt(context, ConfigKey.LogConfigKey.KEY_LOGD_ENABLE, property4);
        int property5 = bydPropertyHelper.getProperty(ConfigKey.LogConfigKey.KEY_LOGE_ENABLE, 1);
        BydLogHelper.logV("logeEnable=" + property5);
        putInt(context, ConfigKey.LogConfigKey.KEY_LOGE_ENABLE, property5);
        BydLogHelper.logV("sysType = " + bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_SYS_TIER1_TYPE, 0));
        BydLogHelper.logV("hwType = " + bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_HARDWARE_TIER1_TYPE, 0));
        int property6 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_CATCH_CRASH_ENABLE, 0);
        BydLogHelper.logV("catchCrashEnable=" + property6);
        putInt(context, ConfigKey.SysConfigKey.KEY_CATCH_CRASH_ENABLE, property6);
        int property7 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MAX, 100);
        BydLogHelper.logV("brightnessMax=" + property7);
        putInt(context, ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MAX, property7);
        int property8 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MIN, 10);
        BydLogHelper.logV("brightnessMin=" + property8);
        putInt(context, ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MIN, property8);
        int property9 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_EQ_BAND_COUNT, 10);
        BydLogHelper.logV("eqBandCount=" + property9);
        putInt(context, ConfigKey.SysConfigKey.KEY_EQ_BAND_COUNT, property9);
        int property10 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_EQ_GAIN_MIN, -7);
        BydLogHelper.logV("eqGainMin=" + property10);
        putInt(context, ConfigKey.SysConfigKey.KEY_EQ_GAIN_MIN, property10);
        int property11 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_EQ_GAIN_MAX, 7);
        BydLogHelper.logV("eqGainMax=" + property11);
        putInt(context, ConfigKey.SysConfigKey.KEY_EQ_GAIN_MAX, property11);
        int property12 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MIN, -7);
        BydLogHelper.logV("fadeBalanceMin=" + property12);
        putInt(context, ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MIN, property12);
        int property13 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MAX, 7);
        BydLogHelper.logV("fadeBalanceMax=" + property13);
        putInt(context, ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MAX, property13);
        boolean property14 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_DEFAULT_AP_ENABLE, false);
        BydLogHelper.logV("defaultApEnable=" + property14);
        putInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_AP_ENABLE, property14 ? 1 : 0);
        boolean property15 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_DEFAULT_WIFI_ENABLE, true);
        BydLogHelper.logV("defaultWiFiEnable=" + property15);
        putInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_WIFI_ENABLE, property15 ? 1 : 0);
        boolean property16 = bydPropertyHelper.getProperty(ConfigKey.SysConfigKey.KEY_DEFAULT_MOBILE_ENABLE, true);
        BydLogHelper.logV("defaultMobileEnable=" + property16);
        putInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_MOBILE_ENABLE, property16 ? 1 : 0);
        bydPropertyHelper.release();
        BydLogHelper.logV("end");
    }

    public int getBrightnessMax(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MAX, 100);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getBrightnessMin(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_BRIGHTNESS_MIN, 10);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getCarConfig(Context context) {
        int i = getInt(context, ConfigKey.CarConfigKey.KEY_CAR_CONFIG, 2);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getCarType(Context context) {
        int i = getInt(context, ConfigKey.CarConfigKey.KEY_CAR_TYPE, 0);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public boolean getCatchCrashEnable(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_CATCH_CRASH_ENABLE, 1);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public boolean getDefaultApEnable(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_AP_ENABLE, 0);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public boolean getDefaultMobileEnable(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_MOBILE_ENABLE, 1);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public boolean getDefaultWiFiEnable(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_DEFAULT_WIFI_ENABLE, 1);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public int getEqBandCount(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_EQ_BAND_COUNT, 10);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getEqGainMax(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_EQ_GAIN_MAX, 7);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getEqGainMin(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_EQ_GAIN_MIN, -7);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getFadeBalanceMax(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MAX, 7);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getFadeBalanceMin(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_FADE_BALANCE_MIN, -7);
        BydLogHelper.logV("value=" + i);
        return i;
    }

    public int getHardWareTier1Type(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_HARDWARE_TIER1_TYPE, 0);
        BydLogHelper.logV("value = " + i);
        return i;
    }

    public boolean getLogdEnable(Context context) {
        int i = getInt(context, ConfigKey.LogConfigKey.KEY_LOGD_ENABLE, 0);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public boolean getLogeEnable(Context context) {
        int i = getInt(context, ConfigKey.LogConfigKey.KEY_LOGE_ENABLE, 1);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public boolean getLogvEnable(Context context) {
        int i = getInt(context, ConfigKey.LogConfigKey.KEY_LOGV_ENABLE, 1);
        BydLogHelper.logV("value=" + i);
        return i == 1;
    }

    public int getSysTier1Type(Context context) {
        int i = getInt(context, ConfigKey.SysConfigKey.KEY_SYS_TIER1_TYPE, 0);
        BydLogHelper.logV("value = " + i);
        return i;
    }
}
