package com.byd.util;

import android.content.Context;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class s {
    public static int a(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        try {
            Method method = wifiManager.getClass().getMethod("getWifiApState", new Class[0]);
            method.setAccessible(true);
            return ((Integer) method.invoke(wifiManager, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            w.c("check isWifiApOn exception : " + e.getMessage());
            return 0;
        }
    }

    public static int b(Context context) {
        int i;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        if (activeNetworkInfo.getType() != 0) {
            return 5;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                i = 4;
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 2;
            case 13:
            case 18:
                i = 3;
                break;
            case 19:
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? 2 : 5;
            case 20:
                i = 6;
                break;
        }
        return i;
    }

    public static String c(BYDAutoSettingDevice bYDAutoSettingDevice) {
        try {
            byte[] bArr = bYDAutoSettingDevice.get(new int[]{BYDAutoFeatureIds.Setting.SET_HOTSPOT_USERNAME}, byte[].class).bufferDataValue;
            String str = Arrays.equals(Arrays.copyOfRange(bArr, 0, 4), new byte[]{0, 0, 0, 0}) ? new String(Arrays.copyOfRange(bArr, 4, bArr.length), StandardCharsets.UTF_8) : new String(Arrays.copyOfRange(bArr, 0, bArr.length), StandardCharsets.UTF_8);
            w.c("getWifiName : " + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            w.c("getWifiName exception : " + e.getMessage());
            return null;
        }
    }

    public static String d(BYDAutoSettingDevice bYDAutoSettingDevice) {
        String str;
        try {
            byte[] bArr = bYDAutoSettingDevice.get(new int[]{BYDAutoFeatureIds.Setting.SET_HOTSPOT_PASSWORD}, byte[].class).bufferDataValue;
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 4);
            byte[] bArr2 = {0, 0, 0, 0};
            if (Arrays.equals(bArrCopyOfRange, bArr2)) {
                str = new String(Arrays.copyOfRange(bArr, 4, bArr.length), StandardCharsets.UTF_8);
                w.c("含有4.0平台前4位校验 = " + Arrays.equals(bArrCopyOfRange, bArr2));
            } else {
                str = new String(Arrays.copyOfRange(bArr, 0, bArr.length), StandardCharsets.UTF_8);
            }
            w.c("getWifiPwd : " + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            w.c("getWifiPwd exception : " + e.getMessage());
            return null;
        }
    }

    public static boolean e(Context context) {
        int iJ;
        int iA = a(context);
        try {
            iJ = e.j(WifiManager.class, "WIFI_AP_STATE_ENABLED");
        } catch (Exception e) {
            e.printStackTrace();
            iJ = 10;
        }
        w.c("wifi ap state : " + iA + " " + iJ);
        return iA == iJ;
    }
}
