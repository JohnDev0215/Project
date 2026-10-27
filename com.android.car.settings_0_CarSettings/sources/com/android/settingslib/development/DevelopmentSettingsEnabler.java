package com.android.settingslib.development;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserManager;
import android.provider.Settings;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* JADX INFO: loaded from: classes6.dex */
public class DevelopmentSettingsEnabler {
    public static final String DEVELOPMENT_SETTINGS_CHANGED_ACTION = "com.android.settingslib.development.DevelopmentSettingsEnabler.SETTINGS_CHANGED";

    private DevelopmentSettingsEnabler() {
    }

    public static void setDevelopmentSettingsEnabled(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "development_settings_enabled", z ? 1 : 0);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(DEVELOPMENT_SETTINGS_CHANGED_ACTION));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isDevelopmentSettingsEnabled(Context context) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        return userManager.isAdminUser() && !userManager.hasUserRestriction("no_debugging_features") && (Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", Build.TYPE.equals("eng") ? 1 : 0) != 0) == true;
    }
}
