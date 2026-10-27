package org.xwalk.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes4.dex */
class XWalkEnvironment {
    private static final String META_XWALK_APK_URL = "xwalk_apk_url";
    private static final String META_XWALK_DOWNLOAD_MODE = "xwalk_download_mode";
    private static final String META_XWALK_DOWNLOAD_MODE_UPDATE = "xwalk_download_mode_update";
    private static final String META_XWALK_ENABLE_DOWNLOAD_MODE = "xwalk_enable_download_mode";
    private static final String META_XWALK_VERIFY = "xwalk_verify";
    private static final String OPTIMIZED_DEX_DIR = "dex";
    private static final String PACKAGE_RE = "[a-z]+\\.[a-z0-9]+\\.[a-z0-9]+.*";
    private static final String PRIVATE_DATA_DIRECTORY_SUFFIX = "xwalkcore";
    private static final String TAG = "XWalkLib";
    private static final String XWALK_CORE_EXTRACTED_DIR = "extracted_xwalkcore";
    private static Context sApplicationContext;
    private static String sApplicationName;
    private static String sDeviceAbi;
    private static Boolean sIsDownloadMode;
    private static Boolean sIsDownloadModeUpdate;
    private static Boolean sIsXWalkVerify;
    private static String sRuntimeAbi;
    private static String sXWalkApkUrl;

    XWalkEnvironment() {
    }

    public static void init(Context context) {
        sApplicationContext = context.getApplicationContext();
    }

    public static Context getApplicationContext() {
        return sApplicationContext;
    }

    public static SharedPreferences getSharedPreferences() {
        return sApplicationContext.getSharedPreferences("libxwalkcore", 0);
    }

    public static String getPrivateDataDir() {
        return sApplicationContext.getDir(PRIVATE_DATA_DIRECTORY_SUFFIX, 0).getAbsolutePath();
    }

    public static String getExtractedCoreDir() {
        return sApplicationContext.getDir(XWALK_CORE_EXTRACTED_DIR, 0).getAbsolutePath();
    }

    public static String getOptimizedDexDir() {
        return sApplicationContext.getDir(OPTIMIZED_DEX_DIR, 0).getAbsolutePath();
    }

    public static void setXWalkApkUrl(String str) {
        sXWalkApkUrl = str;
        Log.d(TAG, "Crosswalk APK download URL: " + sXWalkApkUrl);
    }

    public static String getXWalkApkUrl() {
        if (sXWalkApkUrl == null) {
            String applicationMetaData = getApplicationMetaData(META_XWALK_APK_URL);
            if (applicationMetaData == null) {
                sXWalkApkUrl = "";
            } else {
                String str = "arch=" + getRuntimeAbi();
                try {
                    URI uri = new URI(applicationMetaData);
                    String query = uri.getQuery();
                    if (query != null) {
                        str = query + "&" + str;
                    }
                    sXWalkApkUrl = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), str, uri.getFragment()).toString();
                } catch (URISyntaxException e) {
                    throw new RuntimeException("Invalid xwalk_apk_url", e);
                }
            }
            Log.d(TAG, "Crosswalk APK download URL: " + sXWalkApkUrl);
        }
        return sXWalkApkUrl;
    }

    public static String getApplicationName() {
        if (sApplicationName == null) {
            try {
                PackageManager packageManager = sApplicationContext.getPackageManager();
                sApplicationName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(sApplicationContext.getPackageName(), 0));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            String str = sApplicationName;
            if (str == null || str.isEmpty() || sApplicationName.matches(PACKAGE_RE)) {
                sApplicationName = "this application";
            }
            Log.d(TAG, "Crosswalk application name: " + sApplicationName);
        }
        return sApplicationName;
    }

    public static boolean isDownloadMode() {
        if (sIsDownloadMode == null) {
            String applicationMetaData = getApplicationMetaData(META_XWALK_DOWNLOAD_MODE);
            if (applicationMetaData == null) {
                applicationMetaData = getApplicationMetaData(META_XWALK_ENABLE_DOWNLOAD_MODE);
            }
            sIsDownloadMode = Boolean.valueOf(applicationMetaData != null && (applicationMetaData.equalsIgnoreCase("enable") || applicationMetaData.equalsIgnoreCase("true")));
            Log.d(TAG, "Crosswalk download mode: " + sIsDownloadMode);
        }
        return sIsDownloadMode.booleanValue();
    }

    public static boolean isDownloadModeUpdate() {
        if (sIsDownloadModeUpdate == null) {
            String applicationMetaData = getApplicationMetaData(META_XWALK_DOWNLOAD_MODE_UPDATE);
            sIsDownloadModeUpdate = Boolean.valueOf(applicationMetaData != null && (applicationMetaData.equalsIgnoreCase("enable") || applicationMetaData.equalsIgnoreCase("true")));
            Log.d(TAG, "Crosswalk download mode update: " + sIsDownloadModeUpdate);
        }
        return sIsDownloadModeUpdate.booleanValue();
    }

    public static boolean isXWalkVerify() {
        if (sIsXWalkVerify == null) {
            String applicationMetaData = getApplicationMetaData(META_XWALK_VERIFY);
            sIsXWalkVerify = Boolean.valueOf(applicationMetaData == null || !(applicationMetaData.equalsIgnoreCase("disable") || applicationMetaData.equalsIgnoreCase("false")));
            Log.d(TAG, "Crosswalk verify: " + sIsXWalkVerify);
        }
        return sIsXWalkVerify.booleanValue();
    }

    public static boolean isIaDevice() {
        String deviceAbi = getDeviceAbi();
        return deviceAbi.equals("x86") || deviceAbi.equals("x86_64");
    }

    public static boolean is64bitDevice() {
        String deviceAbi = getDeviceAbi();
        return deviceAbi.equals("arm64-v8a") || deviceAbi.equals("x86_64");
    }

    public static boolean is64bitApp() {
        String runtimeAbi = getRuntimeAbi();
        return runtimeAbi.equals("arm64-v8a") || runtimeAbi.equals("x86_64");
    }

    public static String getRuntimeAbi() {
        String lowerCase;
        if (sRuntimeAbi == null) {
            try {
                throw new NoSuchFieldError();
            } catch (NoSuchFieldError unused) {
                lowerCase = System.getProperty("os.arch").toLowerCase();
                lowerCase.hashCode();
                switch (lowerCase) {
                    case "armv7l":
                    case "armeabi":
                    case "armeabi-v7a":
                        sRuntimeAbi = "armeabi-v7a";
                        break;
                    case "aarch64":
                    case "arm64":
                    case "armv8":
                        if (is64bitDevice()) {
                            sRuntimeAbi = "arm64-v8a";
                            break;
                        } else {
                            sRuntimeAbi = "armeabi-v7a";
                            break;
                        }
                        break;
                    case "x86_64":
                    case "x64":
                        if (is64bitDevice()) {
                            sRuntimeAbi = "x86_64";
                            break;
                        } else {
                            sRuntimeAbi = "x86";
                            break;
                        }
                        break;
                    case "x86":
                    case "i386":
                    case "i686":
                    case "ia32":
                        sRuntimeAbi = "x86";
                        break;
                    default:
                        throw new RuntimeException("Unexpected os.arch: " + lowerCase);
                }
                if (sRuntimeAbi.equals("armeabi-v7a")) {
                    if (isIaDevice()) {
                        sRuntimeAbi = "x86";
                    }
                } else if (sRuntimeAbi.equals("arm64-v8a") && isIaDevice()) {
                    sRuntimeAbi = "x86_64";
                }
                Log.d(TAG, "Runtime ABI: " + sRuntimeAbi);
            }
        }
        return sRuntimeAbi;
    }

    public static String getDeviceAbi() {
        if (sDeviceAbi == null) {
            try {
                try {
                    sDeviceAbi = Build.SUPPORTED_ABIS[0].toLowerCase();
                } catch (NoSuchFieldError unused) {
                    InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    sDeviceAbi = bufferedReader.readLine().toLowerCase();
                    bufferedReader.close();
                    inputStreamReader.close();
                }
                Log.d(TAG, "Device ABI: " + sDeviceAbi);
            } catch (IOException unused2) {
                throw new RuntimeException("Can not detect device's ABI");
            }
        }
        return sDeviceAbi;
    }

    private static String getApplicationMetaData(String str) {
        try {
            return sApplicationContext.getPackageManager().getApplicationInfo(sApplicationContext.getPackageName(), 128).metaData.get(str).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            return null;
        }
    }
}
