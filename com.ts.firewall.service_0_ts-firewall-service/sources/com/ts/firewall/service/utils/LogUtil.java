package com.ts.firewall.service.utils;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    private static final boolean DEBUG_SWITCH = true;
    private static final String PERSIST_FIREWALL_DEBUGGABLE = "persist.sys.firewallservice.debuggable";
    private static final String TAG = "firewallservice";
    private static boolean sFirewallDebuggable;

    static {
        sFirewallDebuggable = false;
        sFirewallDebuggable = SystemProperties.getBoolean(PERSIST_FIREWALL_DEBUGGABLE, false);
    }

    public static void warn(String msg) {
        Log.w(TAG, msg);
    }

    public static void warn(String msg, Exception exception) {
        Log.w(TAG, msg, exception);
    }

    public static void debug(String msg) {
        Log.d(TAG, msg);
    }

    public static void error(String msg) {
        Log.e(TAG, "error:" + msg);
    }

    public static void wtf(Exception exception) {
        Log.wtf(TAG, exception);
    }
}
