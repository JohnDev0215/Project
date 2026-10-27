package com.android.car.settings.wifi;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.settingslib.wifi.AccessPoint;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes5.dex */
public class WifiUtil {
    public static final int INVALID_NET_ID = -1;
    private static final Logger LOG = new Logger((Class<?>) WifiUtil.class);
    private static final Pattern HEX_PATTERN = Pattern.compile("^[0-9a-fA-F]+$");

    public static int getIconRes(int state) {
        if (state == 1 || state == 2) {
            return R.drawable.ic_settings_wifi_disabled;
        }
        return R.drawable.ic_settings_wifi;
    }

    public static boolean isWifiOn(int state) {
        if (state != 1 && state != 2) {
            return true;
        }
        return false;
    }

    public static Integer getStateDesc(int state) {
        if (state == 0) {
            return Integer.valueOf(R.string.wifi_stopping);
        }
        if (state == 1) {
            return Integer.valueOf(R.string.wifi_disabled);
        }
        if (state == 2) {
            return Integer.valueOf(R.string.wifi_starting);
        }
        return 0;
    }

    public static boolean isWifiAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.wifi");
    }

    public static String getKey(AccessPoint accessPoint) {
        return String.valueOf(accessPoint.hashCode());
    }

    public static boolean isNetworkLockedDown(Context context, WifiConfiguration config) {
        ComponentName deviceOwner;
        if (config == null) {
            return false;
        }
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService("device_policy");
        PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature("android.software.device_admin") && dpm == null) {
            return true;
        }
        boolean isConfigEligibleForLockdown = false;
        if (dpm != null && (deviceOwner = dpm.getDeviceOwnerComponentOnAnyUser()) != null) {
            int deviceOwnerUserId = dpm.getDeviceOwnerUserId();
            try {
                int deviceOwnerUid = pm.getPackageUidAsUser(deviceOwner.getPackageName(), deviceOwnerUserId);
                isConfigEligibleForLockdown = deviceOwnerUid == config.creatorUid;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        if (!isConfigEligibleForLockdown) {
            return false;
        }
        ContentResolver resolver = context.getContentResolver();
        return Settings.Global.getInt(resolver, "wifi_device_owner_configs_lockdown", 0) != 0;
    }

    public static boolean isOpenNetwork(int security) {
        return security == 0 || security == 4;
    }

    public static boolean canSignIntoNetwork(NetworkCapabilities capabilities) {
        return capabilities != null && capabilities.hasCapability(17);
    }

    public static void connectToAccessPoint(Context context, String ssid, int security, String password, boolean hidden, WifiManager.ActionListener listener) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        WifiConfiguration wifiConfig = getWifiConfig(ssid, security, password, hidden);
        wifiManager.connect(wifiConfig, listener);
    }

    private static WifiConfiguration getWifiConfig(String ssid, int security, String password, boolean hidden) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.hiddenSSID = hidden;
        return finishWifiConfig(wifiConfig, security, password);
    }

    public static WifiConfiguration getWifiConfig(AccessPoint accessPoint, String password) {
        if (accessPoint == null) {
            throw new IllegalArgumentException("AccessPoint input is required.");
        }
        WifiConfiguration wifiConfig = new WifiConfiguration();
        if (!accessPoint.isSaved()) {
            wifiConfig.SSID = AccessPoint.convertToQuotedString(accessPoint.getSsidStr());
        } else {
            wifiConfig.networkId = accessPoint.getConfig().networkId;
            wifiConfig.hiddenSSID = accessPoint.getConfig().hiddenSSID;
        }
        return finishWifiConfig(wifiConfig, accessPoint.getSecurity(), password);
    }

    private static WifiConfiguration finishWifiConfig(WifiConfiguration wifiConfig, int security, String password) {
        switch (security) {
            case 0:
                wifiConfig.setSecurityParams(0);
                return wifiConfig;
            case 1:
                wifiConfig.setSecurityParams(1);
                if (!TextUtils.isEmpty(password)) {
                    int length = password.length();
                    if ((length == 10 || length == 26 || length == 58) && password.matches("[0-9A-Fa-f]*")) {
                        wifiConfig.wepKeys[0] = password;
                    } else {
                        wifiConfig.wepKeys[0] = Typography.quote + password + Typography.quote;
                    }
                }
                return wifiConfig;
            case 2:
                wifiConfig.setSecurityParams(2);
                if (!TextUtils.isEmpty(password)) {
                    if (password.matches("[0-9A-Fa-f]{64}")) {
                        wifiConfig.preSharedKey = password;
                    } else {
                        wifiConfig.preSharedKey = Typography.quote + password + Typography.quote;
                    }
                }
                return wifiConfig;
            case 3:
            case 6:
                if (security == 6) {
                    wifiConfig.setSecurityParams(5);
                } else {
                    wifiConfig.setSecurityParams(3);
                }
                if (!TextUtils.isEmpty(password)) {
                    wifiConfig.enterpriseConfig.setPassword(password);
                }
                return wifiConfig;
            case 4:
                wifiConfig.setSecurityParams(6);
                return wifiConfig;
            case 5:
                wifiConfig.setSecurityParams(4);
                if (!TextUtils.isEmpty(password)) {
                    wifiConfig.preSharedKey = Typography.quote + password + Typography.quote;
                }
                return wifiConfig;
            default:
                throw new IllegalArgumentException("unknown security type " + security);
        }
    }

    public static void forget(Context context, AccessPoint accessPoint) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        if (!accessPoint.isSaved()) {
            if (accessPoint.getNetworkInfo() != null && accessPoint.getNetworkInfo().getState() != NetworkInfo.State.DISCONNECTED) {
                wifiManager.disableEphemeralNetwork(AccessPoint.convertToQuotedString(accessPoint.getSsidStr()));
                return;
            }
            LOG.e("Failed to forget invalid network " + accessPoint.getConfig());
            return;
        }
        wifiManager.forget(accessPoint.getConfig().networkId, new WifiManager.ActionListener() { // from class: com.android.car.settings.wifi.WifiUtil.1
            public void onSuccess() {
                WifiUtil.LOG.d("Network successfully forgotten");
            }

            public void onFailure(int reason) {
                WifiUtil.LOG.d("Could not forget network. Failure code: " + reason);
            }
        });
    }

    public static boolean isAccessPointDisabledByWrongPassword(AccessPoint accessPoint) {
        WifiConfiguration.NetworkSelectionStatus networkStatus;
        WifiConfiguration config = accessPoint.getConfig();
        return (config == null || (networkStatus = config.getNetworkSelectionStatus()) == null || networkStatus.getNetworkSelectionStatus() == 0 || networkStatus.getNetworkSelectionDisableReason() != 8) ? false : true;
    }

    private static boolean isHexString(String password) {
        return HEX_PATTERN.matcher(password).matches();
    }
}
