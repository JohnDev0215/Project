package android.util;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class FeatureFlagUtils {
    private static final Map<String, String> DEFAULT_FLAGS;
    public static final String DYNAMIC_SYSTEM = "settings_dynamic_system";
    public static final String FFLAG_OVERRIDE_PREFIX = "sys.fflag.override.";
    public static final String FFLAG_PREFIX = "sys.fflag.";
    public static final String HEARING_AID_SETTINGS = "settings_bluetooth_hearing_aid";
    public static final String PERSIST_PREFIX = "persist.sys.fflag.override.";
    public static final String SCREENRECORD_LONG_PRESS = "settings_screenrecord_long_press";
    public static final String SEAMLESS_TRANSFER = "settings_seamless_transfer";
    public static final String SETTINGS_DO_NOT_RESTORE_PRESERVED = "settings_do_not_restore_preserved";
    public static final String SETTINGS_FUSE_FLAG = "settings_fuse";
    public static final String SETTINGS_WIFITRACKER2 = "settings_wifitracker2";

    static {
        HashMap map = new HashMap();
        DEFAULT_FLAGS = map;
        map.put("settings_audio_switcher", "true");
        map.put("settings_systemui_theme", "true");
        map.put(SETTINGS_FUSE_FLAG, "true");
        map.put(DYNAMIC_SYSTEM, "false");
        map.put(SEAMLESS_TRANSFER, "false");
        map.put(HEARING_AID_SETTINGS, "false");
        map.put(SCREENRECORD_LONG_PRESS, "false");
        map.put("settings_wifi_details_datausage_header", "false");
        map.put("settings_skip_direction_mutable", "true");
        map.put(SETTINGS_WIFITRACKER2, "true");
        map.put("settings_controller_loading_enhancement", "true");
        map.put("settings_conditionals", "false");
        map.put(SETTINGS_DO_NOT_RESTORE_PRESERVED, "true");
        map.put("settings_tether_all_in_one", "false");
    }

    public static boolean isEnabled(Context context, String str) {
        if (context != null) {
            String string = Settings.Global.getString(context.getContentResolver(), str);
            if (!TextUtils.isEmpty(string)) {
                return Boolean.parseBoolean(string);
            }
        }
        String str2 = SystemProperties.get(FFLAG_OVERRIDE_PREFIX + str);
        if (!TextUtils.isEmpty(str2)) {
            return Boolean.parseBoolean(str2);
        }
        return Boolean.parseBoolean(getAllFeatureFlags().get(str));
    }

    public static void setEnabled(Context context, String str, boolean z) {
        SystemProperties.set(FFLAG_OVERRIDE_PREFIX + str, z ? "true" : "false");
    }

    public static Map<String, String> getAllFeatureFlags() {
        return DEFAULT_FLAGS;
    }
}
