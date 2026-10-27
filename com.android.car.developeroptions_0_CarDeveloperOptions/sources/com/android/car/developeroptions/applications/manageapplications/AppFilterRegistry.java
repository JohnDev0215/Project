package com.android.car.developeroptions.applications.manageapplications;

import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppStateInstallAppsBridge;
import com.android.car.developeroptions.applications.AppStateNotificationBridge;
import com.android.car.developeroptions.applications.AppStateOverlayBridge;
import com.android.car.developeroptions.applications.AppStatePowerBridge;
import com.android.car.developeroptions.applications.AppStateUsageBridge;
import com.android.car.developeroptions.applications.AppStateWriteSettingsBridge;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.wifi.AppStateChangeWifiStateBridge;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class AppFilterRegistry {
    private static AppFilterRegistry sRegistry;
    private final AppFilterItem[] mFilters;

    public int getDefaultFilterType(int i) {
        if (i == 1) {
            return 6;
        }
        if (i == 13) {
            return 15;
        }
        switch (i) {
            case 4:
                return 10;
            case 5:
                return 0;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return 11;
            case 7:
                return 12;
            case 8:
                return 13;
            default:
                return 2;
        }
    }

    private AppFilterRegistry() {
        AppFilterItem[] appFilterItemArr = new AppFilterItem[17];
        this.mFilters = appFilterItemArr;
        appFilterItemArr[0] = new AppFilterItem(new ApplicationsState.CompoundFilter(AppStatePowerBridge.FILTER_POWER_WHITELISTED, ApplicationsState.FILTER_ALL_ENABLED), 0, R.string.high_power_filter_on);
        this.mFilters[1] = new AppFilterItem(new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_WITHOUT_DISABLED_UNTIL_USED, ApplicationsState.FILTER_ALL_ENABLED), 1, R.string.filter_all_apps);
        this.mFilters[2] = new AppFilterItem(ApplicationsState.FILTER_EVERYTHING, 2, R.string.filter_all_apps);
        this.mFilters[3] = new AppFilterItem(ApplicationsState.FILTER_ALL_ENABLED, 3, R.string.filter_enabled_apps);
        this.mFilters[5] = new AppFilterItem(ApplicationsState.FILTER_DISABLED, 5, R.string.filter_apps_disabled);
        this.mFilters[4] = new AppFilterItem(ApplicationsState.FILTER_INSTANT, 4, R.string.filter_instant_apps);
        this.mFilters[6] = new AppFilterItem(AppStateNotificationBridge.FILTER_APP_NOTIFICATION_RECENCY, 6, R.string.sort_order_recent_notification);
        this.mFilters[7] = new AppFilterItem(AppStateNotificationBridge.FILTER_APP_NOTIFICATION_FREQUENCY, 7, R.string.sort_order_frequent_notification);
        this.mFilters[8] = new AppFilterItem(ApplicationsState.FILTER_PERSONAL, 8, R.string.filter_personal_apps);
        this.mFilters[9] = new AppFilterItem(ApplicationsState.FILTER_WORK, 9, R.string.filter_work_apps);
        this.mFilters[10] = new AppFilterItem(AppStateUsageBridge.FILTER_APP_USAGE, 10, R.string.filter_all_apps);
        this.mFilters[11] = new AppFilterItem(AppStateOverlayBridge.FILTER_SYSTEM_ALERT_WINDOW, 11, R.string.filter_overlay_apps);
        this.mFilters[12] = new AppFilterItem(AppStateWriteSettingsBridge.FILTER_WRITE_SETTINGS, 12, R.string.filter_write_settings_apps);
        this.mFilters[13] = new AppFilterItem(AppStateInstallAppsBridge.FILTER_APP_SOURCES, 13, R.string.filter_install_sources_apps);
        this.mFilters[15] = new AppFilterItem(AppStateChangeWifiStateBridge.FILTER_CHANGE_WIFI_STATE, 15, R.string.filter_write_settings_apps);
        this.mFilters[16] = new AppFilterItem(AppStateNotificationBridge.FILTER_APP_NOTIFICATION_BLOCKED, 16, R.string.filter_notif_blocked_apps);
    }

    public static AppFilterRegistry getInstance() {
        if (sRegistry == null) {
            sRegistry = new AppFilterRegistry();
        }
        return sRegistry;
    }

    public AppFilterItem get(int i) {
        return this.mFilters[i];
    }
}
