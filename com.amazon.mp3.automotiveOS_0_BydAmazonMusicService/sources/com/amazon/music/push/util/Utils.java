package com.amazon.music.push.util;

import android.content.Context;
import android.util.Log;
import com.amazon.music.push.NotificationUtilData;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class Utils {
    private static final String TAG = "Utils";

    public static String readFile(Context context, String str) {
        String str2;
        String str3 = TAG;
        Log.i(str3, "Static File name is " + str);
        String str4 = "";
        try {
            InputStream inputStreamOpen = context.getAssets().open(str, 2);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            str2 = new String(bArr, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.i(str3, "JsonString[redacted]: " + str2.substring(0, 50));
            return str2;
        } catch (Exception e2) {
            e = e2;
            str4 = str2;
            Log.e(TAG, "Error while reading the file " + e);
            return str4;
        }
    }

    public static String extractFileTag(NotificationUtilData notificationUtilData, String str) {
        try {
            return ((JSONObject) ((JSONObject) new JSONObject(notificationUtilData.getJsonFileString()).get(notificationUtilData.getNotificationType())).get(notificationUtilData.getDeviceLocale())).get(str).toString();
        } catch (JSONException e) {
            Log.i(TAG, "Error in file tag read " + e);
            return "";
        }
    }
}
