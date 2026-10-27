package com.byd.systemsettings.wifi2.util;

import android.net.wifi.WifiConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class WifiSecurityUtils {
    public static String toSecurity(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.contains("WPA")) {
            if (str.contains("WPA-")) {
                sb.append("WPA");
            }
            if (str.contains("WPA2-")) {
                if (sb.toString().length() > 0) {
                    sb.append("/");
                }
                sb.append("WPA2");
            }
            if (str.contains("WPA3-") || (str.contains("WPA2-") && str.contains("+SAE"))) {
                if (sb.toString().length() > 0) {
                    sb.append("/");
                }
                sb.append("WPA3");
            }
        } else if (str.contains("WEP")) {
            sb.append("WEP");
        }
        if (str.contains("PSK-")) {
            sb.append(" PSK");
        }
        if (str.contains("RSN-SAE-CCMP")) {
            sb.append("WPA3-Personal");
        }
        return sb.toString();
    }

    public static String toSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            sb.append("WPA");
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            if (sb.toString().length() > 0) {
                sb.append("/");
            }
            sb.append("WPA2");
        }
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            if (sb.toString().length() > 0) {
                sb.append("/");
            }
            sb.append("WPA3");
        }
        if (wifiConfiguration.allowedKeyManagement.get(4)) {
            sb.append(" PSK");
        }
        if (wifiConfiguration.allowedKeyManagement.get(2)) {
            sb.append("WEP");
        }
        return sb.toString();
    }
}
