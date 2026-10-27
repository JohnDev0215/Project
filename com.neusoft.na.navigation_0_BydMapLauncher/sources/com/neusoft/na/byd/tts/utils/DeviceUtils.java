package com.neusoft.na.byd.tts.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class DeviceUtils {
    private static final String KEY_UUID = "KEY_UUID";
    private static SharedPreferences sp;

    public static String getUniqueDeviceId(Context context) {
        return getUniqueDeviceIdReal(context);
    }

    private static String getUniqueDeviceIdReal(Context context) {
        return getUuid(context);
    }

    private static String getUuid(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("tts_device_id", 0);
        }
        String string = sp.getString(KEY_UUID, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        sp.edit().putString(KEY_UUID, string2).apply();
        return string2;
    }
}
