package com.bydux.api.common.tools.net;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydWifiHelper {
    private static final String TAG = "BydWifiHelper";
    public static final int WIFI_STATE_DISABLED = 1;
    public static final int WIFI_STATE_DISABLING = 0;
    public static final int WIFI_STATE_ENABLED = 3;
    public static final int WIFI_STATE_ENABLING = 2;
    public static final int WIFI_STATE_UNKNOWN = 4;
    private static String mSsid = "<unknown ssid>";

    private BydWifiHelper() {
    }

    public static String getLocalWifiIp(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return null;
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }

    public static int getWifiSignalLevel(Context context, int i) {
        int iCalculateSignalLevel = WifiManager.calculateSignalLevel(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getRssi(), i);
        BydLogHelper.logV(TAG, "getWifiSignalLevel", "max=" + i + ", level=" + iCalculateSignalLevel);
        return iCalculateSignalLevel;
    }

    public static String getWifiSsid(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String ssid = connectionInfo.getSSID();
        mSsid = ssid;
        if ("<unknown ssid>".equals(ssid)) {
            int networkId = connectionInfo.getNetworkId();
            Iterator<WifiConfiguration> it = wifiManager.getConfiguredNetworks().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WifiConfiguration next = it.next();
                if (next.networkId == networkId) {
                    mSsid = next.SSID;
                    break;
                }
            }
        }
        if (mSsid.startsWith("\"") && mSsid.endsWith("\"")) {
            String str = mSsid;
            mSsid = str.substring(1, str.length() - 1);
        }
        BydLogHelper.logV(TAG, "getWifiSsid", "ssid=" + mSsid);
        return mSsid;
    }

    public static int getWifiState(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getWifiState();
    }

    public static boolean isWifiEnabled(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
    }

    public static boolean setWifiEnabled(Context context, boolean z) {
        BydLogHelper.logV(TAG, "setWifiEnabled", "enable=" + z);
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (z) {
            if (wifiManager.isWifiEnabled()) {
                return false;
            }
            return wifiManager.setWifiEnabled(true);
        }
        if (wifiManager.isWifiEnabled()) {
            return wifiManager.setWifiEnabled(false);
        }
        return false;
    }
}
