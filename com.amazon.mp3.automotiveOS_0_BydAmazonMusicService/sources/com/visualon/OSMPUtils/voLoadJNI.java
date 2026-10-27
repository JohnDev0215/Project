package com.visualon.OSMPUtils;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes7.dex */
public class voLoadJNI {
    private static final String DYNAMIC_HELPER_NAME = "vodl";
    private static final String ONELIB_NAME = "voOSOneLib";
    private static final String PRODUCT_SUFFIX = "_OSMP";
    private static final String TAG = "voLoadJNI.java";
    private static boolean voDynamicHelperLoaded = false;

    private static native int nativeSetProductSuffix(String str);

    public static boolean loadReachableLib(String str, String str2) {
        boolean zLoadLibInPath = loadLibInPath(str, str2);
        return !zLoadLibInPath ? loadLib(str2) : zLoadLibInPath;
    }

    public static boolean loadProductLib(String str, String str2) {
        if (!loadDynamicHelper(str)) {
            return false;
        }
        return loadReachableLib(str, str2 + PRODUCT_SUFFIX);
    }

    private static boolean loadDynamicHelper(String str) {
        if (voDynamicHelperLoaded) {
            return true;
        }
        boolean zLoadReachableLib = loadReachableLib(str, DYNAMIC_HELPER_NAME);
        voDynamicHelperLoaded = zLoadReachableLib;
        if (zLoadReachableLib) {
            nativeSetProductSuffix(PRODUCT_SUFFIX);
        }
        return voDynamicHelperLoaded;
    }

    public static boolean loadLibs(String[] strArr) {
        for (String str : strArr) {
            if (!loadLib(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean loadLibsInPath(String str, String[] strArr) {
        int i = 0;
        for (String str2 : strArr) {
            if (loadLibInPath(str, str2)) {
                i++;
            }
        }
        return i == strArr.length;
    }

    public static boolean loadOneLib() {
        return loadLib(ONELIB_NAME);
    }

    public static boolean loadOneLibInPath(String str) {
        loadDynamicHelper(str);
        return loadLibInPath(str, ONELIB_NAME);
    }

    private static boolean loadLib(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception | UnsatisfiedLinkError unused) {
            return false;
        }
    }

    private static boolean loadLibInPath(String str, String str2) {
        if (str == null) {
            str = "";
        }
        try {
            String str3 = str + System.mapLibraryName(str2);
            if (!new File(str3).exists()) {
                return false;
            }
            System.load(str3);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "General Exception in loadLibInPath");
            e.printStackTrace();
            return false;
        } catch (UnsatisfiedLinkError e2) {
            Log.e(TAG, "UnsatisfiedLinkError in loadLibInPath");
            e2.printStackTrace();
            return false;
        }
    }
}
