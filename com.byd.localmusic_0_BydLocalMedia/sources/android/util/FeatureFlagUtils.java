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
        DEFAULT_FLAGS.put("settings_systemui_theme", "true");
        DEFAULT_FLAGS.put(SETTINGS_FUSE_FLAG, "true");
        DEFAULT_FLAGS.put(DYNAMIC_SYSTEM, "false");
        DEFAULT_FLAGS.put(SEAMLESS_TRANSFER, "false");
        DEFAULT_FLAGS.put(HEARING_AID_SETTINGS, "false");
        DEFAULT_FLAGS.put(SCREENRECORD_LONG_PRESS, "false");
        DEFAULT_FLAGS.put("settings_wifi_details_datausage_header", "false");
        DEFAULT_FLAGS.put("settings_skip_direction_mutable", "true");
        DEFAULT_FLAGS.put(SETTINGS_WIFITRACKER2, "true");
        DEFAULT_FLAGS.put("settings_controller_loading_enhancement", "true");
        DEFAULT_FLAGS.put("settings_conditionals", "false");
        DEFAULT_FLAGS.put(SETTINGS_DO_NOT_RESTORE_PRESERVED, "true");
        DEFAULT_FLAGS.put("settings_tether_all_in_one", "false");
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
