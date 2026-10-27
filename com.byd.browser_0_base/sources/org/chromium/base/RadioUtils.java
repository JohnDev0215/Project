package org.chromium.base;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Process;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import defpackage.c6;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class RadioUtils {
    public static Boolean a;
    public static Boolean b;

    @CalledByNative
    public static int getCellDataActivity() {
        TraceEvent traceEventH0 = TraceEvent.H0("RadioUtils::getCellDataActivity", null);
        try {
            try {
                int dataActivity = ((TelephonyManager) xz.a.getSystemService("phone")).getDataActivity();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return dataActivity;
            } catch (SecurityException unused) {
                if (traceEventH0 == null) {
                    return -1;
                }
                traceEventH0.close();
                return -1;
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    @CalledByNative
    public static int getCellSignalLevel() {
        TraceEvent traceEventH0 = TraceEvent.H0("RadioUtils::getCellSignalLevel", null);
        try {
            int level = -1;
            try {
                SignalStrength signalStrength = ((TelephonyManager) xz.a.getSystemService("phone")).getSignalStrength();
                if (signalStrength != null) {
                    level = signalStrength.getLevel();
                }
            } catch (SecurityException unused) {
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return level;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    @CalledByNative
    public static boolean isSupported() {
        if (a == null) {
            a = Boolean.valueOf(c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (a.booleanValue()) {
            if (b == null) {
                b = Boolean.valueOf(c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_WIFI_STATE") == 0);
            }
            if (b.booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @CalledByNative
    public static boolean isWifiConnected() {
        TraceEvent traceEventH0 = TraceEvent.H0("RadioUtils::isWifiConnected", null);
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return false;
            }
            boolean zHasTransport = networkCapabilities.hasTransport(1);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return zHasTransport;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
