package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ae {
    public static ae a(Context context) {
        td tdVar;
        ee eeVar;
        if (context != null) {
            if (yd.d(context)) {
                try {
                    synchronized (ee.class) {
                        if (ee.c == null) {
                            ee.c = new ee(context);
                        }
                        eeVar = ee.c;
                    }
                    return eeVar;
                } catch (Throwable th) {
                    Log.e(ga.a("PeriodicMetricsCollector"), "Cannot create ThirdPartyPeriodicMetricsCollector", th);
                }
            } else if (yd.d()) {
                try {
                    synchronized (td.class) {
                        if (td.b == null) {
                            td.b = new td(context);
                        }
                        tdVar = td.b;
                    }
                    return tdVar;
                } catch (Throwable th2) {
                    Log.e(ga.a("PeriodicMetricsCollector"), "Cannot create FireOSPeriodicMetricsCollector", th2);
                }
            }
        }
        Log.i(ga.a("PeriodicMetricsCollector"), "Appropriate metrics component cannot be found");
        return new zd(null);
    }

    public abstract de a(String str);

    public abstract void b(String str);
}
