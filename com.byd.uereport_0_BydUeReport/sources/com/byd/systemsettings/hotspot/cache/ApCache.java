package com.byd.systemsettings.hotspot.cache;

import com.byd.systemsettings.hotspot.callback.ApCallback;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class ApCache {
    public static final String AP_CONNECT_STATE = "ap_connect_state";
    public static final String HOTSPOT_STATE = "hotspot_state";
    private static ConcurrentHashMap<String, ApCallback> apCallbackMap;

    public static void clear() {
        ConcurrentHashMap<String, ApCallback> concurrentHashMap = apCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static ApCallback get(String str) {
        if (apCallbackMap == null) {
            apCallbackMap = new ConcurrentHashMap<>();
        }
        return apCallbackMap.get(str);
    }

    public static void put(String str, ApCallback apCallback) {
        if (apCallbackMap == null) {
            apCallbackMap = new ConcurrentHashMap<>();
        }
        apCallbackMap.put(str, apCallback);
    }

    public static void remove(String str) {
        ConcurrentHashMap<String, ApCallback> concurrentHashMap = apCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(str);
        }
    }
}
