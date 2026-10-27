package com.byd.systemsettings.system;

import android.app.INotificationManager;
import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.time.BYDAutoTimeDevice;
import android.os.ServiceManager;
import android.provider.Settings;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.system.callback.SettingsCallback;
import com.byd.systemsettings.system.common.Fields;
import com.byd.systemsettings.system.model.ClockStyle;
import java.util.Locale;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydSystemSettings {
    public static final String BYD_SCREENSAVER_MODE = "byd_screensaver_mode";
    public static final String H_12 = "12";
    public static final String H_24 = "24";
    public static final int INDEX_12H = 1;
    public static final int INDEX_24H = 0;
    public static final String KEY_COUNTRY_CODE = "sys.byd.countrycode";
    public static final String MEMORY_PLAYBACK_STATE = "byd_memory_playback_state";
    public static final String MULTIMEDIA_BRIGHTNESS_LINKAGE = "multimedia_brightness_linkage";
    public static final int NAVIBAR_MODE_MODE_AC = 2;
    public static final int NAVIBAR_MODE_MODE_ALL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;
    public static final String SCREEN_BRIGHTNESS_FLOAT = "screen_brightness_float";
    private static final String TAG = "BydSystemSettings";
    private BYDAutoTimeDevice autoTimeDevice;
    private volatile boolean isInited;
    private BYDAutoSettingDevice mSettings;
    private INotificationManager notificationManager;

    public static class Singleton {
        private static final BydSystemSettings instance = new BydSystemSettings();

        public static BydSystemSettings get() {
            return instance;
        }
    }

    public String getCountryCode() {
        return BydSystemProperties.get("sys.byd.countrycode", null);
    }

    public Locale getCurLanguage(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public int getNavibarModeScreensaver(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.Global.getInt(context.getContentResolver(), NAVIBAR_MODE_ON_SCREENSAVER, 0);
    }

    public ClockStyle getScreenSaverClockStyle(Context context) {
        if (context == null) {
            return ClockStyle.CLOSE;
        }
        int i8 = Settings.Global.getInt(context.getContentResolver(), BYD_SCREENSAVER_MODE, ClockStyle.CLOSE.value);
        a.a(TAG, "--getScreenSaverClockStyle---mode = " + i8);
        return ClockStyle.getStyle(i8);
    }

    public int getTimeSystem(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        a.a(TAG, "--getTimeSystem--v:" + string);
        return "12".equals(string) ? 1 : 0;
    }

    public void init(Context context) {
        if (this.isInited || context == null) {
            return;
        }
        this.isInited = false;
        context.getApplicationContext();
        this.mSettings = BYDAutoSettingDevice.getInstance(context.getApplicationContext());
        this.notificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        this.isInited = true;
    }

    public boolean isLanguageChinese(Context context) {
        if (context == null) {
            return true;
        }
        return Locale.CHINA.equals(context.getResources().getConfiguration().getLocales().get(0));
    }

    public boolean isWifiUnsupport5GHzBand() {
        return isWifiUnsupport5GHzBand(BydSystemProperties.get("sys.byd.countrycode", null));
    }

    public void registerGlobalSettingsCallback(Context context, String str, SettingsCallback settingsCallback) {
        if (context == null || settingsCallback == null) {
            return;
        }
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(str), false, settingsCallback, -1);
    }

    public void registerSystemSettingsCallback(Context context, String str, SettingsCallback settingsCallback) {
        if (context == null || settingsCallback == null) {
            return;
        }
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(str), false, settingsCallback, -1);
    }

    public void release() {
        this.isInited = false;
    }

    public void setNavibarModeScreensaver(Context context, int i8) {
        if (context == null) {
            return;
        }
        if (i8 < 0) {
            i8 = 0;
        }
        Settings.Global.putInt(context.getContentResolver(), NAVIBAR_MODE_ON_SCREENSAVER, i8);
    }

    public void setScreenSaverClockStyle(Context context, ClockStyle clockStyle) {
        a.a(TAG, "--setScreenSaverClockStyle---style.value = " + clockStyle.value);
        if (context != null) {
            Settings.Global.putInt(context.getContentResolver(), BYD_SCREENSAVER_MODE, clockStyle.value);
        }
    }

    public void setTimeSystem(Context context, boolean z7) {
        if (context == null || AppConfig.b() == AppConfig.AppMode.DEBUG) {
            return;
        }
        a.c(TAG, "--setTimeSystem--is24H = " + z7);
        this.autoTimeDevice.setTimeFormat(z7 ? 1 : 0);
    }

    public void unregisterSettingsCallback(Context context, SettingsCallback settingsCallback) {
        if (context == null || settingsCallback == null) {
            return;
        }
        context.getContentResolver().unregisterContentObserver(settingsCallback);
    }

    private BydSystemSettings() {
        this.isInited = false;
    }

    public boolean isWifiUnsupport5GHzBand(String str) {
        if (str == null) {
            return true;
        }
        for (String str2 : Fields.UNSUPPORT_5GHZ_BAND_COUNTRY_ARR) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
