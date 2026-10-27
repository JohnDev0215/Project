package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import com.blankj.utilcode.util.Utils;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtils {
    private static final String KEY_UUID = "KEY_UUID";
    private static SharedPreferences sp;

    public static String getUniqueDeviceId(Context context) {
        return getUniqueDeviceIdReal(context);
    }

    private static String getUniqueDeviceIdReal(Context context) {
        try {
            String androidId = getAndroidId();
            if (!TextUtils.isEmpty(androidId)) {
                return androidId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUuid(context);
    }

    private static String getAndroidId() {
        return Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
    }

    private static String getUuid(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("sp_demo", 0);
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
