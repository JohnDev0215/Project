package com.ts.appservice.pki.c;

import android.content.Context;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TelephonyManager f25a;

    public String a() {
        String simSerialNumber = this.f25a.getSimSerialNumber();
        com.ts.appservice.pki.d.b.b("DevicePropertyManager", " iccid " + simSerialNumber);
        return simSerialNumber;
    }

    public String b() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.diag.sn");
        com.ts.appservice.pki.d.b.b("DevicePropertyManager", " snNumber " + str);
        return str;
    }

    public void c(Context context) {
        this.f25a = (TelephonyManager) context.getSystemService("phone");
    }
}
