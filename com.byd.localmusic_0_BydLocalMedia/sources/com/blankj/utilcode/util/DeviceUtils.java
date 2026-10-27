package com.blankj.utilcode.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.android.internal.content.NativeLibraryHelper;
import com.blankj.utilcode.util.ShellUtils;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceUtils {
    private static final String KEY_UDID = "KEY_UDID";
    private static volatile String udid;

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isDeviceRooted() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        for (int i = 0; i < 11; i++) {
            if (new File(strArr[i] + BydAdbHelper.COMMAND_SU).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdbEnabled() {
        return Settings.Secure.getInt(Utils.getApp().getContentResolver(), "adb_enabled", 0) > 0;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidID() {
        String string = Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        return ("9774d56d682e549c".equals(string) || string == null) ? "" : string;
    }

    public static String getMacAddress() {
        String[] strArr = (String[]) null;
        String macAddress = getMacAddress(strArr);
        if (!macAddress.equals("") || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress(strArr);
    }

    private static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    private static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    public static String getMacAddress(String... strArr) {
        String macAddressByNetworkInterface = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddressByNetworkInterface, strArr)) {
            return macAddressByNetworkInterface;
        }
        String macAddressByInetAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddressByInetAddress, strArr)) {
            return macAddressByInetAddress;
        }
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        String macAddressByFile = getMacAddressByFile();
        return isAddressNotInExcepts(macAddressByFile, strArr) ? macAddressByFile : "";
    }

    private static boolean isAddressNotInExcepts(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return !BluetoothAdapter.DEFAULT_MAC_ADDRESS.equals(str);
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    private static String getMacAddressByWifiInfo() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? BluetoothAdapter.DEFAULT_MAC_ADDRESS : connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
        }
    }

    private static String getMacAddressByNetworkInterface() {
        byte[] hardwareAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement != null && networkInterfaceNextElement.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = networkInterfaceNextElement.getHardwareAddress()) != null && hardwareAddress.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02x:", Byte.valueOf(b)));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
            return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
        } catch (Exception e) {
            e.printStackTrace();
            return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
        }
    }

    private static String getMacAddressByInetAddress() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress == null || (byInetAddress = NetworkInterface.getByInetAddress(inetAddress)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : hardwareAddress) {
                sb.append(String.format("%02x:", Byte.valueOf(b)));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
        }
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress() && inetAddressNextElement.getHostAddress().indexOf(58) < 0) {
                            return inetAddressNextElement;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getMacAddressByFile() {
        String str;
        String str2;
        ShellUtils.CommandResult commandResultExecCmd = ShellUtils.execCmd("getprop wifi.interface", false);
        if (commandResultExecCmd.result != 0 || (str = commandResultExecCmd.successMsg) == null) {
            return BluetoothAdapter.DEFAULT_MAC_ADDRESS;
        }
        ShellUtils.CommandResult commandResultExecCmd2 = ShellUtils.execCmd("cat /sys/class/net/" + str + "/address", false);
        return (commandResultExecCmd2.result != 0 || (str2 = commandResultExecCmd2.successMsg) == null || str2.length() <= 0) ? BluetoothAdapter.DEFAULT_MAC_ADDRESS : str2;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        return str != null ? str.trim().replaceAll("\\s*", "") : "";
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
    }

    public static boolean isTablet() {
        return (Utils.getApp().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean isEmulator() {
        String networkOperatorName;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) {
            return true;
        }
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null) {
            networkOperatorName = "";
        }
        if (networkOperatorName.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction(Intent.ACTION_DIAL);
        return intent.resolveActivity(Utils.getApp().getPackageManager()) == null;
    }

    public static String getUniqueDeviceId() {
        return getUniqueDeviceId("");
    }

    public static String getUniqueDeviceId(String str) {
        if (udid == null) {
            synchronized (DeviceUtils.class) {
                if (udid == null) {
                    String string = Utils.getSpUtils4Utils().getString(KEY_UDID, null);
                    if (string != null) {
                        udid = string;
                        return udid;
                    }
                    try {
                        String androidID = getAndroidID();
                        if (!TextUtils.isEmpty(androidID)) {
                            return saveUdid(str + 2, androidID);
                        }
                    } catch (Exception unused) {
                    }
                    return saveUdid(str + 9, "");
                }
            }
        }
        return udid;
    }

    public static boolean isSameDevice(String str) {
        if (TextUtils.isEmpty(str) && str.length() < 33) {
            return false;
        }
        if (str.equals(udid) || str.equals(Utils.getSpUtils4Utils().getString(KEY_UDID, null))) {
            return true;
        }
        int length = str.length() - 33;
        int i = length + 1;
        if (!str.substring(length, i).startsWith(ExifInterface.GPS_MEASUREMENT_2D)) {
            return false;
        }
        String androidID = getAndroidID();
        if (TextUtils.isEmpty(androidID)) {
            return false;
        }
        return str.substring(i).equals(getUdid("", androidID));
    }

    private static String saveUdid(String str, String str2) {
        udid = getUdid(str, str2);
        SPUtils.getInstance().put(KEY_UDID, udid);
        return udid;
    }

    private static String getUdid(String str, String str2) {
        if (str2.equals("")) {
            return str + UUID.randomUUID().toString().replace(NativeLibraryHelper.CLEAR_ABI_OVERRIDE, "");
        }
        return str + UUID.nameUUIDFromBytes(str2.getBytes()).toString().replace(NativeLibraryHelper.CLEAR_ABI_OVERRIDE, "");
    }
}
