package com.byd.vrsettings.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.telephony.TelephonyManager;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.chad.library.BuildConfig;
import com.google.gson.Gson;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class NetUtils {
    public static final int NETWORK_CLASS_2_G = 996;
    public static final int NETWORK_CLASS_3_G = 997;
    public static final int NETWORK_CLASS_4_G = 998;
    public static final int NETWORK_CLASS_UNKNOWN = 995;
    public static final int NETWORK_WIFI = 999;

    public static int getNetWorkClass(Context context) {
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NETWORK_CLASS_2_G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NETWORK_CLASS_3_G;
            case 13:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    public static boolean isNetAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        LogUtils.e("NetUtils", "isNetAvailable:" + new Gson().toJson(activeNetworkInfo));
        return activeNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED;
    }

    public static boolean isWifiConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceSettingApplication.getContext().getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1);
    }

    public static boolean isCellularConnect() {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceSettingApplication.getContext().getSystemService("connectivity");
        if (connectivityManager == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(0);
    }

    public static boolean isBluetoothConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceSettingApplication.getContext().getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return false;
        }
        return networkCapabilities.hasTransport(2);
    }

    public static void registNetCallBack(ConnectivityManager.NetworkCallback networkCallback) {
        ((ConnectivityManager) BydVoiceSettingApplication.getContext().getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().addTransportType(0).addTransportType(1).build(), networkCallback);
    }

    public static void unRegistNetCallBack(ConnectivityManager.NetworkCallback networkCallback) {
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceSettingApplication.getContext().getSystemService("connectivity");
        if (networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", BuildConfig.FLAVOR);
    }
}
