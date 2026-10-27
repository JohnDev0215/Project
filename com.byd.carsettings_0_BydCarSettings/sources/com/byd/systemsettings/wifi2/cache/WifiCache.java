package com.byd.systemsettings.wifi2.cache;

import b.b.m.c.a;
import com.byd.systemsettings.wifi2.callback.WifiCallback;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class WifiCache {
    public static final String AP_CONNECT_STATE = "ap_connect_state";
    public static final String RSSI_CHANGED = "rssi_changed";
    public static final String TAG = "WifiCache";
    public static final String WIFI_CONNECT_STATE = "wifi_connect_state";
    public static final String WIFI_DISCOVER = "wifi_discover";
    public static final String WIFI_STATE = "wifi_state";
    private static volatile boolean listeningScanResult = false;
    private static ConcurrentHashMap<String, WifiCallback> wifiCallbackMap;

    public static void clear() {
        ConcurrentHashMap<String, WifiCallback> concurrentHashMap = wifiCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static WifiCallback get(String str) {
        if (wifiCallbackMap == null) {
            wifiCallbackMap = new ConcurrentHashMap<>();
        }
        return wifiCallbackMap.get(str);
    }

    public static boolean isListeningScanResult() {
        return listeningScanResult;
    }

    public static void put(String str, WifiCallback wifiCallback) {
        if (wifiCallbackMap == null) {
            wifiCallbackMap = new ConcurrentHashMap<>();
        }
        wifiCallbackMap.put(str, wifiCallback);
    }

    public static void remove(String str) {
        ConcurrentHashMap<String, WifiCallback> concurrentHashMap = wifiCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(str);
        }
    }

    public static void setListeningScanResult(boolean z) {
        a.g(TAG, ", setListeningScanResult---listeningScanResult: " + z);
        listeningScanResult = z;
    }
}
