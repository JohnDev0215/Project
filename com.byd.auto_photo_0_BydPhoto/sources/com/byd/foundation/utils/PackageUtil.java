package com.byd.foundation.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PackageUtil {
    private static final String TAG = "PackageUtil";
    private static final Map<String, String> mPackageVersionNameMap = new HashMap();
    private static final Map<String, String> mPathPackageVersionNameMap = new HashMap();
    private static final Map<String, Integer> mPackageVersionCodeMap = new HashMap();
    private static final Map<String, Integer> mPathPackageVersionCodeMap = new HashMap();

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return Integer.MIN_VALUE;
        }
    }

    public static int getPackageVersionCode(Context context, String str) {
        Map<String, Integer> map = mPackageVersionCodeMap;
        if (map.containsKey(str)) {
            return map.get(str).intValue();
        }
        int i = Integer.MIN_VALUE;
        try {
            i = context.getPackageManager().getPackageInfo(str, 0).versionCode;
            map.put(str, Integer.valueOf(i));
            return i;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return i;
        }
    }

    public static String getPackageVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return null;
        }
    }

    public static String getPackageVersionName(Context context, String str) {
        Map<String, String> map = mPackageVersionNameMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        String str2 = "";
        try {
            str2 = context.getPackageManager().getPackageInfo(str, 0).versionName;
            map.put(str, str2);
            return str2;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return str2;
        }
    }

    public static int getUninstallPackageVersionCode(Context context, String str, String str2) {
        Map<String, Integer> map = mPathPackageVersionCodeMap;
        if (map.containsKey(str)) {
            return map.get(str).intValue();
        }
        int i = Integer.MIN_VALUE;
        if (!new File(str2).exists()) {
            LogUtils.e(TAG, str + " !file.exists() !!!!!");
            map.put(str, Integer.MIN_VALUE);
            return Integer.MIN_VALUE;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo == null) {
                return Integer.MIN_VALUE;
            }
            i = packageArchiveInfo.versionCode;
            map.put(str, Integer.valueOf(i));
            return i;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return i;
        }
    }

    public static String getUninstallPackageVersionName(Context context, String str, String str2) {
        Map<String, String> map = mPathPackageVersionNameMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if (!new File(str2).exists()) {
            LogUtils.e(TAG, str + " !file.exists() !!!!!");
            map.put(str, "file not found");
            return "file not found";
        }
        String str3 = "";
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo == null) {
                return "";
            }
            str3 = packageArchiveInfo.versionName;
            map.put(str, str3);
            return str3;
        } catch (Throwable th) {
            LogUtils.e(TAG, th.getMessage(), th);
            return str3;
        }
    }
}
