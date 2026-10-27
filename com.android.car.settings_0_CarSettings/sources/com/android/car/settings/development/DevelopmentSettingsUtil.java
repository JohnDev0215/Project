package com.android.car.settings.development;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.UserManager;
import android.provider.Settings;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class DevelopmentSettingsUtil {
    private static final Logger LOG = new Logger((Class<?>) DevelopmentSettingsUtil.class);

    private DevelopmentSettingsUtil() {
    }

    public static void setDevelopmentSettingsEnabled(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "development_settings_enabled", z ? 1 : 0);
        setDeveloperOptionsEnabledState(context, showDeveloperOptions(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isDevelopmentSettingsEnabled(Context context, UserManager userManager) {
        return userManager.isAdminUser() && !userManager.hasUserRestriction("no_debugging_features") && (Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", Build.IS_ENG ? 1 : 0) != 0) == true;
    }

    public static boolean isDeviceProvisioned(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0;
    }

    public static boolean isDeveloperOptionsModuleEnabled(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentName component = getDeveloperOptionsModule(context);
        int state = pm.getComponentEnabledSetting(component);
        return state == 1;
    }

    private static ComponentName getDeveloperOptionsModule(Context context) {
        return ComponentName.unflattenFromString(context.getString(R.string.config_dev_options_module));
    }

    private static boolean showDeveloperOptions(Context context) {
        UserManager userManager = UserManager.get(context);
        boolean showDev = isDevelopmentSettingsEnabled(context, userManager) && !ActivityManager.isUserAMonkey();
        boolean isAdmin = userManager.isAdminUser();
        if (!isAdmin) {
            return false;
        }
        return showDev;
    }

    private static void setDeveloperOptionsEnabledState(Context context, boolean enabled) {
        int i;
        PackageManager pm = context.getPackageManager();
        ComponentName component = getDeveloperOptionsModule(context);
        int state = pm.getComponentEnabledSetting(component);
        boolean isEnabled = isDeveloperOptionsModuleEnabled(context);
        LOG.i("Enabling developer options module: " + component.flattenToString() + " Current state: " + state + " Currently enabled: " + isEnabled + " Should enable: " + enabled);
        if (isEnabled != enabled || state == 0) {
            if (enabled) {
                i = 1;
            } else {
                i = 2;
            }
            pm.setComponentEnabledSetting(component, i, 1);
        }
    }
}
