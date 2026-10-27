package com.android.car.developeroptions.dashboard;

import android.util.ArrayMap;
import com.android.car.developeroptions.DisplaySettings;
import com.android.car.developeroptions.LegalSettings;
import com.android.car.developeroptions.accounts.AccountDashboardFragment;
import com.android.car.developeroptions.accounts.AccountDetailDashboardFragment;
import com.android.car.developeroptions.applications.AppAndNotificationDashboardFragment;
import com.android.car.developeroptions.connecteddevice.AdvancedConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.connecteddevice.ConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment;
import com.android.car.developeroptions.deviceinfo.StorageDashboardFragment;
import com.android.car.developeroptions.deviceinfo.aboutphone.MyDeviceInfoFragment;
import com.android.car.developeroptions.display.NightDisplaySettings;
import com.android.car.developeroptions.enterprise.EnterprisePrivacySettings;
import com.android.car.developeroptions.fuelgauge.PowerUsageSummary;
import com.android.car.developeroptions.fuelgauge.batterysaver.BatterySaverSettings;
import com.android.car.developeroptions.gestures.GestureSettings;
import com.android.car.developeroptions.homepage.TopLevelSettings;
import com.android.car.developeroptions.language.LanguageAndInputSettings;
import com.android.car.developeroptions.network.NetworkDashboardFragment;
import com.android.car.developeroptions.notification.SoundSettings;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.car.developeroptions.privacy.PrivacyDashboardFragment;
import com.android.car.developeroptions.security.LockscreenDashboardFragment;
import com.android.car.developeroptions.security.SecuritySettings;
import com.android.car.developeroptions.system.SystemDashboardFragment;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DashboardFragmentRegistry {
    public static final Map<String, String> CATEGORY_KEY_TO_PARENT_MAP;
    public static final Map<String, String> PARENT_TO_CATEGORY_KEY_MAP;

    static {
        ArrayMap arrayMap = new ArrayMap();
        PARENT_TO_CATEGORY_KEY_MAP = arrayMap;
        arrayMap.put(TopLevelSettings.class.getName(), "com.android.settings.category.ia.homepage");
        PARENT_TO_CATEGORY_KEY_MAP.put(NetworkDashboardFragment.class.getName(), "com.android.settings.category.ia.wireless");
        PARENT_TO_CATEGORY_KEY_MAP.put(ConnectedDeviceDashboardFragment.class.getName(), "com.android.settings.category.ia.connect");
        PARENT_TO_CATEGORY_KEY_MAP.put(AdvancedConnectedDeviceDashboardFragment.class.getName(), "com.android.settings.category.ia.device");
        PARENT_TO_CATEGORY_KEY_MAP.put(AppAndNotificationDashboardFragment.class.getName(), "com.android.settings.category.ia.apps");
        PARENT_TO_CATEGORY_KEY_MAP.put(PowerUsageSummary.class.getName(), "com.android.settings.category.ia.battery");
        PARENT_TO_CATEGORY_KEY_MAP.put(DisplaySettings.class.getName(), "com.android.settings.category.ia.display");
        PARENT_TO_CATEGORY_KEY_MAP.put(SoundSettings.class.getName(), "com.android.settings.category.ia.sound");
        PARENT_TO_CATEGORY_KEY_MAP.put(StorageDashboardFragment.class.getName(), "com.android.settings.category.ia.storage");
        PARENT_TO_CATEGORY_KEY_MAP.put(SecuritySettings.class.getName(), "com.android.settings.category.ia.security");
        PARENT_TO_CATEGORY_KEY_MAP.put(AccountDetailDashboardFragment.class.getName(), "com.android.settings.category.ia.account_detail");
        PARENT_TO_CATEGORY_KEY_MAP.put(AccountDashboardFragment.class.getName(), "com.android.settings.category.ia.accounts");
        PARENT_TO_CATEGORY_KEY_MAP.put(SystemDashboardFragment.class.getName(), "com.android.settings.category.ia.system");
        PARENT_TO_CATEGORY_KEY_MAP.put(LanguageAndInputSettings.class.getName(), "com.android.settings.category.ia.language");
        PARENT_TO_CATEGORY_KEY_MAP.put(DevelopmentSettingsDashboardFragment.class.getName(), "com.android.settings.category.ia.development");
        PARENT_TO_CATEGORY_KEY_MAP.put(LockscreenDashboardFragment.class.getName(), "com.android.settings.category.ia.lockscreen");
        PARENT_TO_CATEGORY_KEY_MAP.put(ZenModeSettings.class.getName(), "com.android.settings.category.ia.dnd");
        PARENT_TO_CATEGORY_KEY_MAP.put(GestureSettings.class.getName(), "com.android.settings.category.ia.gestures");
        PARENT_TO_CATEGORY_KEY_MAP.put(NightDisplaySettings.class.getName(), "com.android.settings.category.ia.night_display");
        PARENT_TO_CATEGORY_KEY_MAP.put(PrivacyDashboardFragment.class.getName(), "com.android.settings.category.ia.privacy");
        PARENT_TO_CATEGORY_KEY_MAP.put(EnterprisePrivacySettings.class.getName(), "com.android.settings.category.ia.enterprise_privacy");
        PARENT_TO_CATEGORY_KEY_MAP.put(LegalSettings.class.getName(), "com.android.settings.category.ia.about_legal");
        PARENT_TO_CATEGORY_KEY_MAP.put(MyDeviceInfoFragment.class.getName(), "com.android.settings.category.ia.my_device_info");
        PARENT_TO_CATEGORY_KEY_MAP.put(BatterySaverSettings.class.getName(), "com.android.settings.category.ia.battery_saver_settings");
        CATEGORY_KEY_TO_PARENT_MAP = new ArrayMap(PARENT_TO_CATEGORY_KEY_MAP.size());
        for (Map.Entry<String, String> entry : PARENT_TO_CATEGORY_KEY_MAP.entrySet()) {
            CATEGORY_KEY_TO_PARENT_MAP.put(entry.getValue(), entry.getKey());
        }
    }
}
