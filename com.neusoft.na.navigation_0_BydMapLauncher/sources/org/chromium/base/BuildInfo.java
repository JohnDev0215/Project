package org.chromium.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;

/* JADX INFO: loaded from: classes3.dex */
public class BuildInfo {
    private static final int MAX_FINGERPRINT_LENGTH = 128;
    private static final String TAG = "BuildInfo";

    public static boolean isGreaterThanN() {
        return true;
    }

    private BuildInfo() {
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getAndroidBuildId() {
        return Build.ID;
    }

    public static String getAndroidBuildFingerprint() {
        return Build.FINGERPRINT.substring(0, Math.min(Build.FINGERPRINT.length(), 128));
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getGMSVersionCode(Context context) {
        try {
            return Integer.toString(context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "GMS package is not found: %s", e);
            return "gms versionCode not available.";
        }
    }

    public static String getPackageVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode > 0 ? Integer.toString(packageInfo.versionCode) : "";
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(TAG, "versionCode not available.");
            return "versionCode not available.";
        }
    }

    public static String getPackageVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName != null ? packageInfo.versionName : "";
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(TAG, "versionName not available");
            return "versionName not available";
        }
    }

    public static String getPackageLabel(Context context) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            PackageManager packageManager = context.getPackageManager();
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128));
            return applicationLabel != null ? applicationLabel.toString() : "";
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static String getPackageName(Context context) {
        String packageName = context != null ? context.getPackageName() : null;
        return packageName != null ? packageName : "";
    }

    public static String getBuildType() {
        return Build.TYPE;
    }

    public static int getSdkInt() {
        return Build.VERSION.SDK_INT;
    }
}
