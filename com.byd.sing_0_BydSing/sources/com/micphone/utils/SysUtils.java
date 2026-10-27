package com.micphone.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class SysUtils {
    public static final String INTERFACE_NAME_ETHERNET = "eth0";
    public static final String INTERFACE_NAME_WLAN = "wlan0";
    private static final Map<String, String> MAC_CACHE = new ConcurrentHashMap();

    private static class Holder {
        private static final SysUtils instance = new SysUtils();

        private Holder() {
        }
    }

    private String findMacAddress(String str) {
        try {
        } catch (SocketException e) {
            e.printStackTrace();
        }
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (str != null && str.equals(networkInterface.getName())) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                for (byte b2 : hardwareAddress) {
                    sb.append(String.format("%02X", Byte.valueOf(b2)));
                }
                return sb.toString();
            }
            return null;
        }
        return null;
    }

    private String getDeviceMac(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map<String, String> map = MAC_CACHE;
        String str2 = map.get(str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String strFindMacAddress = findMacAddress(str);
        if (TextUtils.isEmpty(strFindMacAddress)) {
            return null;
        }
        map.put(str, strFindMacAddress);
        return strFindMacAddress;
    }

    public static SysUtils getInstance() {
        return Holder.instance;
    }

    public int getAPNType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return 1;
        }
        if (type != 0) {
            return 0;
        }
        int subtype = activeNetworkInfo.getSubtype();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (subtype == 20) {
            return 5;
        }
        if (subtype == 13 && !telephonyManager.isNetworkRoaming()) {
            return 4;
        }
        if (subtype == 3 || subtype == 8 || (subtype == 5 && !telephonyManager.isNetworkRoaming())) {
            return 3;
        }
        return (subtype == 1 || subtype == 2 || (subtype == 4 && !telephonyManager.isNetworkRoaming())) ? 2 : 0;
    }

    public String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), "android_id").toLowerCase();
    }

    public String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMacAddress() {
        String deviceMac = getDeviceMac(INTERFACE_NAME_ETHERNET);
        if (!TextUtils.isEmpty(deviceMac)) {
            return deviceMac.toLowerCase();
        }
        String deviceMac2 = getDeviceMac(INTERFACE_NAME_WLAN);
        if (TextUtils.isEmpty(deviceMac2)) {
            return null;
        }
        return deviceMac2.toLowerCase();
    }

    public String getScreenWidthHeightDensity(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int[] iArr = {displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi};
        return iArr[0] + "," + iArr[1];
    }

    public String netType(int i) {
        int i2 = 5;
        if (i == 0) {
            i2 = 1;
        } else if (i == 1) {
            i2 = 99;
        } else if (i == 2) {
            i2 = 2;
        } else if (i == 3) {
            i2 = 3;
        } else if (i == 4) {
            i2 = 4;
        } else if (i != 5) {
            i2 = 0;
        }
        return String.valueOf(i2);
    }

    private SysUtils() {
    }
}
