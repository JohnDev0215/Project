package com.byd.music.netObserver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.utils.LogUtil;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkUtils {
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_IWLAN = 18;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;

    public interface LinkNetWorkType {
        public static final int UNKNOWN = 0;
        public static final int WIFI = 1;
        public static final int WWAN = 2;
        public static final int _2G = 3;
        public static final int _3G = 4;
        public static final int _4G = 5;
    }

    public enum NetworkType {
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openWirelessSettings() {
        if (Build.VERSION.SDK_INT > 10) {
            Utils.getApp().startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS).setFlags(268435456));
        } else {
            Utils.getApp().startActivity(new Intent(Settings.ACTION_SETTINGS).setFlags(268435456));
        }
    }

    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) Utils.getApp().getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isAvailableByPing() {
        ShellUtils.CommandResult commandResultExecCmd = ShellUtils.execCmd("ping -c 1 -w 1 223.5.5.5", false);
        boolean z = commandResultExecCmd.result == 0;
        if (commandResultExecCmd.errorMsg != null) {
            LogUtil.d("isAvailableByPing errorMsg", commandResultExecCmd.errorMsg);
        }
        if (commandResultExecCmd.successMsg != null) {
            LogUtil.d("isAvailableByPing successMsg", commandResultExecCmd.successMsg);
        }
        return z;
    }

    public static boolean getDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setDataEnabled(boolean z) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", Boolean.TYPE);
            if (declaredMethod != null) {
                declaredMethod.invoke(telephonyManager, Boolean.valueOf(z));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean getWifiEnabled() {
        return ((WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi")).isWifiEnabled();
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
        if (z) {
            if (wifiManager.isWifiEnabled()) {
                return;
            }
            wifiManager.setWifiEnabled(true);
        } else if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || connectivityManager.getActiveNetworkInfo().getType() != 1) ? false : true;
    }

    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailableByPing();
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public static NetworkType getNetworkType() {
        NetworkType networkType = NetworkType.NETWORK_NO;
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return networkType;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() == 0) {
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
                    return NetworkType.NETWORK_2G;
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
                    return NetworkType.NETWORK_3G;
                case 13:
                case 18:
                    return NetworkType.NETWORK_4G;
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                        return NetworkType.NETWORK_3G;
                    }
                    return NetworkType.NETWORK_UNKNOWN;
            }
        }
        return NetworkType.NETWORK_UNKNOWN;
    }

    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress()) {
                            String hostAddress = inetAddressNextElement.getHostAddress();
                            boolean z2 = hostAddress.indexOf(58) < 0;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int iIndexOf = hostAddress.indexOf(37);
                                return iIndexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, iIndexOf).toUpperCase();
                            }
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

    public static String getDomainAddress(final String str) {
        try {
            return (String) Executors.newCachedThreadPool().submit(new Callable<String>() { // from class: com.byd.music.netObserver.NetworkUtils.1
                @Override // java.util.concurrent.Callable
                public String call() throws Exception {
                    try {
                        return InetAddress.getByName(str).getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNetSystemUsable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return true;
        }
        boolean zHasCapability = networkCapabilities.hasCapability(16);
        if (!zHasCapability && getNetworkType() == NetworkType.NETWORK_WIFI) {
            for (int i = 0; i < 3; i++) {
                SystemClock.sleep(1000L);
                zHasCapability = networkCapabilities.hasCapability(16);
                if (zHasCapability) {
                    break;
                }
            }
        }
        return zHasCapability;
    }

    public static boolean isNetUseful() {
        Runtime runtime = Runtime.getRuntime();
        Process processExec = null;
        try {
            processExec = runtime.exec("ping -c 1 -w 1 www.baidu.com");
            boolean z = processExec.waitFor() == 0;
            if (processExec != null) {
                processExec.destroy();
            }
            runtime.gc();
            return z;
        } catch (Exception unused) {
            if (processExec != null) {
                processExec.destroy();
            }
            runtime.gc();
            return false;
        } catch (Throwable th) {
            if (processExec != null) {
                processExec.destroy();
            }
            runtime.gc();
            throw th;
        }
    }

    public static int getNetworkTypeForLink(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return 1;
                }
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return 4;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return 3;
                        case 13:
                            return 5;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return 0;
    }
}
