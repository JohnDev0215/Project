package com.android.packageinstaller.wear;

import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class WearPackageArgs {
    private static final String KEY_ASSET_URI = "com.google.android.clockwork.EXTRA_ASSET_URI";
    private static final String KEY_CHECK_PERMS = "com.google.android.clockwork.EXTRA_CHECK_PERMS";
    private static final String KEY_COMPANION_DEVICE_VERSION = "com.google.android.clockwork.EXTRA_KEY_COMPANION_DEVICE_VERSION";
    private static final String KEY_COMPANION_SDK_VERSION = "com.google.android.clockwork.EXTRA_KEY_COMPANION_SDK_VERSION";
    private static final String KEY_COMPRESSION_ALG = "com.google.android.clockwork.EXTRA_KEY_COMPRESSION_ALG";
    private static final String KEY_PACKAGE_NAME = "com.google.android.clockwork.EXTRA_PACKAGE_NAME";
    private static final String KEY_PERM_URI = "com.google.android.clockwork.EXTRA_PERM_URI";
    private static final String KEY_SHOULD_CHECK_GMS_DEPENDENCY = "com.google.android.clockwork.EXTRA_KEY_SHOULD_CHECK_GMS_DEPENDENCY";
    private static final String KEY_SKIP_IF_LOWER_VERSION = "com.google.android.clockwork.EXTRA_SKIP_IF_LOWER_VERSION";
    private static final String KEY_SKIP_IF_SAME_VERSION = "com.google.android.clockwork.EXTRA_SKIP_IF_SAME_VERSION";
    private static final String KEY_START_ID = "com.google.android.clockwork.EXTRA_START_ID";

    public static String getPackageName(Bundle b) {
        return b.getString(KEY_PACKAGE_NAME);
    }

    public static Bundle setPackageName(Bundle b, String packageName) {
        b.putString(KEY_PACKAGE_NAME, packageName);
        return b;
    }

    public static Uri getAssetUri(Bundle b) {
        return (Uri) b.getParcelable(KEY_ASSET_URI);
    }

    public static Uri getPermUri(Bundle b) {
        return (Uri) b.getParcelable(KEY_PERM_URI);
    }

    public static boolean checkPerms(Bundle b) {
        return b.getBoolean(KEY_CHECK_PERMS);
    }

    public static boolean skipIfSameVersion(Bundle b) {
        return b.getBoolean(KEY_SKIP_IF_SAME_VERSION);
    }

    public static int getCompanionSdkVersion(Bundle b) {
        return b.getInt(KEY_COMPANION_SDK_VERSION);
    }

    public static int getCompanionDeviceVersion(Bundle b) {
        return b.getInt(KEY_COMPANION_DEVICE_VERSION);
    }

    public static String getCompressionAlg(Bundle b) {
        return b.getString(KEY_COMPRESSION_ALG);
    }

    public static int getStartId(Bundle b) {
        return b.getInt(KEY_START_ID);
    }

    public static boolean skipIfLowerVersion(Bundle b) {
        return b.getBoolean(KEY_SKIP_IF_LOWER_VERSION, false);
    }

    public static Bundle setStartId(Bundle b, int startId) {
        b.putInt(KEY_START_ID, startId);
        return b;
    }
}
