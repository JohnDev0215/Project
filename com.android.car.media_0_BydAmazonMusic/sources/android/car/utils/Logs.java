package android.car.utils;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.Nullable;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class Logs {
    public static final String TAG_GLOBAL = "BydAmazonMusic";
    private static final String split = ": ";
    private static String version = "";

    public static void d(String str, String str2) {
        if (Log.isLoggable(TAG_GLOBAL, 3)) {
            Log.d(TAG_GLOBAL, version + str + split + str2);
        }
    }

    public static void debugTrace(String str) {
    }

    public static void e(String str, String str2) {
        if (Log.isLoggable(TAG_GLOBAL, 6)) {
            Log.e(TAG_GLOBAL, version + str + split + str2);
        }
    }

    private static String getVersion(Application application, @Nullable String str) {
        try {
            PackageManager packageManager = application.getPackageManager();
            if (str == null) {
                str = application.getPackageName();
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return packageInfo == null ? "" : packageInfo.versionName;
        } catch (Exception unused) {
            e("", "can not getVersion");
            return "";
        }
    }

    public static void i(String str, String str2) {
        if (Log.isLoggable(TAG_GLOBAL, 4)) {
            Log.i(TAG_GLOBAL, version + str + split + str2);
        }
    }

    public static void init(Application application) {
        StringBuilder sbK = c.k("[");
        sbK.append(getVersion(application, null));
        sbK.append("_");
        version = c.h(sbK, getVersion(application, "com.amazon.mp3.automotiveOS"), "]");
    }

    public static void v(String str, String str2) {
        if (Log.isLoggable(TAG_GLOBAL, 2)) {
            Log.v(TAG_GLOBAL, version + str + split + str2);
        }
    }

    public static void w(String str, String str2) {
        if (Log.isLoggable(TAG_GLOBAL, 5)) {
            Log.w(TAG_GLOBAL, version + str + split + str2);
        }
    }
}
