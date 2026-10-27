package com.byd.vrsettings.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class SharedPreferencesUtils {
    private static final String FILE_NAME = "BYD_VOICE_SETTING";
    public static final String KEY_AC_CONTROL_FIRST = "KEY_AC_CONTROL_FIRST";
    public static final String KEY_AC_CONTROL_SWITCH = "KEY_AC_CONTROL_SWITCH";
    public static final String KEY_AC_CONTROL_UP_DOWN = "KEY_AC_CONTROL_UP_DOWN";
    public static final String KEY_HOME_COMMAND_FIRST = "KEY_HOME_COMMAND_FIRST";
    public static final String KEY_HOME_COMMAND_SWITCH = "KEY_HOME_COMMAND_SWITCH";
    public static final String KEY_HOME_COMMAND_UP_DOWN = "KEY_HOME_COMMAND_UP_DOWN";
    public static final String KEY_LANGUAGE = "KEY_LANGUAGE";
    public static final String KEY_MAP_NAV_FIRST = "KEY_MAP_NAV_FIRST";
    public static final String KEY_MAP_NAV_SWITCH = "KEY_MAP_NAV_SWITCH";
    public static final String KEY_MAP_NAV_UP_DOWN = "KEY_MAP_NAV_UP_DOWN";
    public static final String KEY_MUSIC_PLAYER_FIRST = "KEY_MUSIC_PLAYER_FIRST";
    public static final String KEY_MUSIC_PLAYER_SWITCH = "KEY_MUSIC_PLAYER_SWITCH";
    public static final String KEY_MUSIC_PLAYER_UP_DOWN = "KEY_MUSIC_PLAYER_UP_DOWN";
    public static final String KEY_REGISTER_LOCK = "KEY_FOUR_REGISTER_LOCK";
    public static final String KEY_TEL_CONTROL_FIRST = "KEY_TEL_CONTROL_FIRST";
    public static final String KEY_TEL_CONTROL_SWITCH = "KEY_TEL_CONTROL_SWITCH";
    public static final String KEY_TEL_CONTROL_UP_DOWN = "KEY_TEL_CONTROL_UP_DOWN";
    public static final String KEY_VOICE_VOLUME_FIRST = "KEY_VOICE_VOLUME_FIRST";
    public static final String KEY_VOLUME_CONTROL_FIRST = "KEY_VOLUME_CONTROL_FIRST";
    public static final String KEY_VOLUME_CONTROL_SWITCH = "KEY_VOLUME_CONTROL_SWITCH";
    public static final String KEY_VOLUME_CONTROL_UP_DOWN = "KEY_VOLUME_CONTROL_UP_DOWN";
    public static final String KEY_WAKEUP_WORD = "KEY_WAKEUP_WORD";
    public static final String OPEN_LANGUAGE_FIRST = "OPEN_LANGUAGE_FIRST";
    public static final String OPEN_SETTING_FIRST = "OPEN_SETTING_FIRST";
    public static final String OPEN_USER_DEFINE_FIRST = "OPEN_USER_DEFINE_FIRST";
    public static final String OPEN_WAKEUP_FIRST = "OPEN_WAKEUP_FIRST";
    public static final String SP_KEY_AUTO_ENABLE_TIP = "auto_enable";
    public static final String SP_KEY_NET_DIALOG_TODAY_NOT_TIP = "net_dialog_tip_time";
    public static final String SP_KEY_PAUSE_LIST = "SP_KEY_PAUSE_LIST";
    public static final String SP_KEY_TODAY_NOT_TIP = "tip_time";

    public static void setParam(Context context, String str, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if ("String".equals(simpleName)) {
            editorEdit.putString(str, (String) obj);
        } else if ("Integer".equals(simpleName)) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if ("Boolean".equals(simpleName)) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if ("Float".equals(simpleName)) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if ("Long".equals(simpleName)) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        }
        editorEdit.apply();
    }

    public static Object getParam(Context context, String str, Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if ("String".equals(simpleName)) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if ("Integer".equals(simpleName)) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if ("Boolean".equals(simpleName)) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if ("Float".equals(simpleName)) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if ("Long".equals(simpleName)) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    public static void clearAll(Context context) {
        context.getSharedPreferences(FILE_NAME, 0).edit().clear().apply();
    }

    public static void clear(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }
}
