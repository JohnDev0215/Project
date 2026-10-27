package com.amazon.identity.auth.device;

import android.util.Log;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class nc extends pc {
    public static final String o = "com.amazon.identity.auth.device.nc";
    public String i;
    public String j;
    public String k;
    public String l;
    public kd m;
    public Map<String, jd> n;

    public boolean a(kd kdVar) {
        if (kdVar.a()) {
            this.m = kdVar;
            return true;
        }
        Log.e(ga.a(o), "setVersionNumber: version number was invalid. Cannot set.");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    @Override // com.amazon.identity.auth.device.pc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.ld c() {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.nc.c():com.amazon.identity.auth.device.ld");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean d(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = com.amazon.identity.auth.device.id.b(r5)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L15
            java.lang.String r0 = com.amazon.identity.auth.device.nc.o
            java.lang.String r0 = com.amazon.identity.auth.device.ga.a(r0)
            java.lang.String r3 = "isValidRadioId: returning false because a null or empty radio id was given."
            android.util.Log.i(r0, r3)
        L13:
            r0 = r2
            goto L28
        L15:
            boolean r0 = com.amazon.identity.auth.device.id.a(r5)
            if (r0 != 0) goto L27
            java.lang.String r0 = com.amazon.identity.auth.device.nc.o
            java.lang.String r0 = com.amazon.identity.auth.device.ga.a(r0)
            java.lang.String r3 = "isValidRadioId: returning false because a non alpha radio id number was given."
            android.util.Log.i(r0, r3)
            goto L13
        L27:
            r0 = r1
        L28:
            if (r0 != 0) goto L37
            java.lang.String r5 = com.amazon.identity.auth.device.nc.o
            java.lang.String r5 = com.amazon.identity.auth.device.ga.a(r5)
            java.lang.String r0 = "setRadioId: radio id was invalid. Cannot set."
            android.util.Log.e(r5, r0)
            return r2
        L37:
            r4.i = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.nc.d(java.lang.String):boolean");
    }
}
