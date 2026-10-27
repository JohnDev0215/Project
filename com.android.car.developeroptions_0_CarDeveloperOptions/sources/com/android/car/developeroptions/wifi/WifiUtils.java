package com.android.car.developeroptions.wifi;

import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.settingslib.wifi.AccessPoint;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class WifiUtils {
    public static boolean isSSIDTooLong(String str) {
        return !TextUtils.isEmpty(str) && str.getBytes(StandardCharsets.UTF_8).length > 32;
    }

    public static boolean isSSIDTooShort(String str) {
        return TextUtils.isEmpty(str) || str.length() < 1;
    }

    public static boolean isHotspotPasswordValid(String str) {
        int length;
        return !TextUtils.isEmpty(str) && (length = str.length()) >= 8 && length <= 63;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isNetworkLockedDown(android.content.Context r5, android.net.wifi.WifiConfiguration r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "device_policy"
            java.lang.Object r1 = r5.getSystemService(r1)
            android.app.admin.DevicePolicyManager r1 = (android.app.admin.DevicePolicyManager) r1
            android.content.pm.PackageManager r2 = r5.getPackageManager()
            java.lang.String r3 = "android.software.device_admin"
            boolean r3 = r2.hasSystemFeature(r3)
            r4 = 1
            if (r3 == 0) goto L1c
            if (r1 != 0) goto L1c
            return r4
        L1c:
            if (r1 == 0) goto L36
            android.content.ComponentName r3 = r1.getDeviceOwnerComponentOnAnyUser()
            if (r3 == 0) goto L36
            int r1 = r1.getDeviceOwnerUserId()
            java.lang.String r3 = r3.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L36
            int r1 = r2.getPackageUidAsUser(r3, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L36
            int r6 = r6.creatorUid     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L36
            if (r1 != r6) goto L36
            r6 = r4
            goto L37
        L36:
            r6 = r0
        L37:
            if (r6 != 0) goto L3a
            return r0
        L3a:
            android.content.ContentResolver r5 = r5.getContentResolver()
            java.lang.String r6 = "wifi_device_owner_configs_lockdown"
            int r5 = android.provider.Settings.Global.getInt(r5, r6, r0)
            if (r5 == 0) goto L47
            r0 = r4
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.wifi.WifiUtils.isNetworkLockedDown(android.content.Context, android.net.wifi.WifiConfiguration):boolean");
    }

    public static boolean canSignIntoNetwork(NetworkCapabilities networkCapabilities) {
        return networkCapabilities != null && networkCapabilities.hasCapability(17);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static WifiConfiguration getWifiConfig(AccessPoint accessPoint, ScanResult scanResult, String str) {
        int security;
        if (accessPoint == null && scanResult == null) {
            throw new IllegalArgumentException("At least one of AccessPoint and ScanResult input is required.");
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        if (accessPoint == null) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(scanResult.SSID);
            security = getAccessPointSecurity(scanResult);
        } else {
            if (!accessPoint.isSaved()) {
                wifiConfiguration.SSID = AccessPoint.convertToQuotedString(accessPoint.getSsidStr());
            } else {
                wifiConfiguration.networkId = accessPoint.getConfig().networkId;
                wifiConfiguration.hiddenSSID = accessPoint.getConfig().hiddenSSID;
            }
            security = accessPoint.getSecurity();
        }
        switch (security) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                return wifiConfiguration;
            case 1:
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                if (!TextUtils.isEmpty(str)) {
                    int length = str.length();
                    if ((length == 10 || length == 26 || length == 58) && str.matches("[0-9A-Fa-f]*")) {
                        wifiConfiguration.wepKeys[0] = str;
                    } else {
                        wifiConfiguration.wepKeys[0] = '\"' + str + '\"';
                    }
                }
                return wifiConfiguration;
            case 2:
                wifiConfiguration.allowedKeyManagement.set(1);
                if (!TextUtils.isEmpty(str)) {
                    if (str.matches("[0-9A-Fa-f]{64}")) {
                        wifiConfiguration.preSharedKey = str;
                    } else {
                        wifiConfiguration.preSharedKey = '\"' + str + '\"';
                    }
                }
                return wifiConfiguration;
            case 3:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                wifiConfiguration.allowedKeyManagement.set(2);
                wifiConfiguration.allowedKeyManagement.set(3);
                if (security == 6) {
                    wifiConfiguration.allowedKeyManagement.set(10);
                    wifiConfiguration.requirePmf = true;
                    wifiConfiguration.allowedPairwiseCiphers.set(3);
                    wifiConfiguration.allowedGroupCiphers.set(5);
                    wifiConfiguration.allowedGroupManagementCiphers.set(2);
                }
                if (!TextUtils.isEmpty(str)) {
                    wifiConfiguration.enterpriseConfig.setPassword(str);
                }
                return wifiConfiguration;
            case 4:
                wifiConfiguration.allowedKeyManagement.set(9);
                wifiConfiguration.requirePmf = true;
                return wifiConfiguration;
            case 5:
                wifiConfiguration.allowedKeyManagement.set(8);
                wifiConfiguration.requirePmf = true;
                if (!TextUtils.isEmpty(str)) {
                    wifiConfiguration.preSharedKey = '\"' + str + '\"';
                }
                return wifiConfiguration;
            default:
                return wifiConfiguration;
        }
    }

    public static int getAccessPointSecurity(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return 1;
        }
        if (scanResult.capabilities.contains("SAE")) {
            return 5;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        if (scanResult.capabilities.contains("EAP_SUITE_B_192")) {
            return 6;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return 3;
        }
        return scanResult.capabilities.contains("OWE") ? 4 : 0;
    }

    public static int getConnectingType(AccessPoint accessPoint) {
        WifiConfiguration config = accessPoint.getConfig();
        if (accessPoint.isOsuProvider()) {
            return 3;
        }
        if (accessPoint.getSecurity() == 0 || accessPoint.getSecurity() == 4) {
            return 1;
        }
        return ((!accessPoint.isSaved() || config == null || config.getNetworkSelectionStatus() == null || !config.getNetworkSelectionStatus().hasEverConnected()) && !accessPoint.isPasspoint()) ? 0 : 2;
    }
}
