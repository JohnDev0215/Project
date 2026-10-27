package com.visualon.OSMPUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/* JADX INFO: loaded from: classes7.dex */
public class voOSUniqID {
    private static final String PREFS_DEVICE_ID = "review_policy";
    private static final String PREFS_FILE = "voosmp_data.xml";
    private static String prefix;
    private static UUID uuid;

    public voOSUniqID(Context context) {
        synchronized (voOSUniqID.class) {
            if (context == null) {
                voLog.e2("Fatal error, context is null, please check init code at app side.", new Object[0]);
                return;
            }
            if (uuid == null) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, 0);
                String string = sharedPreferences.getString(PREFS_DEVICE_ID, null);
                if (string != null) {
                    uuid = UUID.fromString(string);
                } else {
                    String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    try {
                        if (!"9774d56d682e549c".equals(string2)) {
                            uuid = UUID.nameUUIDFromBytes(string2.getBytes("utf8"));
                        } else {
                            uuid = UUID.randomUUID();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        uuid = UUID.randomUUID();
                    }
                    sharedPreferences.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
                }
            }
        }
    }

    public UUID getDeviceUUID() {
        return uuid;
    }

    public String getDeviceUUIDString() {
        UUID uuid2 = uuid;
        return uuid2 == null ? "" : uuid2.toString();
    }

    public static void setDrmUUIDPrefix(String str) {
        if (str.length() > 20) {
            prefix = str.substring(0, 20);
        } else {
            prefix = str;
        }
    }

    public String getDrmUUIDString() {
        UUID uuid2 = uuid;
        String string = uuid2 == null ? "" : uuid2.toString();
        if (prefix == null) {
            return string;
        }
        return prefix + string;
    }
}
