package org.chromium.components.location;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.provider.Settings;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class LocationUtils {
    private static Factory sFactory;
    private static LocationUtils sInstance;

    public interface Factory {
        LocationUtils create();
    }

    protected LocationUtils() {
    }

    public static LocationUtils getInstance() {
        ThreadUtils.assertOnUiThread();
        if (sInstance == null) {
            Factory factory = sFactory;
            if (factory == null) {
                sInstance = new LocationUtils();
            } else {
                sInstance = factory.create();
            }
        }
        return sInstance;
    }

    private boolean hasPermission(Context context, String str) {
        return ApiCompatibilityUtils.checkPermission(context, str, Process.myPid(), Process.myUid()) == 0;
    }

    public boolean hasAndroidLocationPermission(Context context) {
        return hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || hasPermission(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    public boolean isSystemLocationSettingEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0;
    }

    public Intent getSystemLocationSettingsIntent() {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(268435456);
        return intent;
    }

    public static void setFactory(Factory factory) {
        sFactory = factory;
        sInstance = null;
    }
}
