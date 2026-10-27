package com.ts.appservice.engineeringmode.d;

import android.os.SystemProperties;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f44a;

    e() {
    }

    public static synchronized e a() {
        if (f44a == null) {
            f44a = new e();
        }
        return f44a;
    }

    public String b() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.diag.vin");
        com.ts.appservice.engineeringmode.e.a.g("HardwarePropertyManager", "boschVin length : " + (str == null ? 0 : str.length()));
        return str;
    }
}
