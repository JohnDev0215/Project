package com.amazon.mp3.prime.upsell;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.activity.settings.SettingsUtil;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeUpsellSettingsUtil {
    public static Boolean hasCloudTracks(Context context) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        if (prefs.contains("KEY_FTU_HAS_CLOUD_TRACKS")) {
            return Boolean.valueOf(prefs.getBoolean("KEY_FTU_HAS_CLOUD_TRACKS", true));
        }
        return null;
    }

    public static void setHasCloudTracks(Context context, boolean hasCloudTracks) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean("KEY_FTU_HAS_CLOUD_TRACKS", hasCloudTracks);
        editorEdit.apply();
    }

    public static Boolean hasDeviceTracks(Context context) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        if (prefs.contains("KEY_FTU_HAS_DEVICE_TRACKS")) {
            return Boolean.valueOf(prefs.getBoolean("KEY_FTU_HAS_DEVICE_TRACKS", true));
        }
        return null;
    }

    public static void setHasDeviceTracks(Context context, boolean hasDeviceTracks) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean("KEY_FTU_HAS_DEVICE_TRACKS", hasDeviceTracks);
        editorEdit.apply();
    }

    public static boolean isUpgradeCustomer(Context context) {
        return SettingsUtil.getPrefs(context).getBoolean("KEY_FTU_IS_UPGRADE_CUSTOMER", false);
    }
}
