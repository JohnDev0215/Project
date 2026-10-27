package com.waze.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: classes7.dex */
public class WazeSdkUtils {
    private static final byte[] IV_BYTES = {105, -35, -88, 69, 92, 125, -44, 37, 75, -13, 83, -73, 115, 48, 78, -20};

    public static String getWazeBuildnumber(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.waze", 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static void openWazeInPlayStore(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?").buildUpon().appendQueryParameter("id", "com.waze").appendQueryParameter("referrer", "utm_source=partner&utm_medium=direct&utm_campaign=" + context.getPackageName()).build());
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    static int getWazeVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.waze", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    static String createAESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return Base64.encodeToString(keyGenerator.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    static class WazeStats {
        final String mEventName;
        final HashMap<String, String> mParams = new HashMap<>();

        WazeStats(String str) {
            this.mEventName = str;
        }

        WazeStats addParam(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.mParams.put(str, str2);
            }
            return this;
        }
    }
}
