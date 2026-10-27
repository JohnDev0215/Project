package com.amazon.identity.auth.device;

import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class tc {
    public static final String d = "com.amazon.identity.auth.device.tc";
    public static final byte[] e = new byte[0];
    public final ac b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f585a = null;
    public boolean c = false;

    public tc(ac acVar) {
        this.b = acVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x024a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.lang.String r11, com.amazon.identity.auth.device.ld r12) {
        /*
            Method dump skipped, instruction units count: 609
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.tc.a(java.lang.String, com.amazon.identity.auth.device.ld):java.lang.String");
    }

    public boolean a(ld ldVar) {
        ac acVar = this.b;
        if (acVar != null) {
            String str = acVar.a().f165a;
            try {
                String strA = a(str, ldVar);
                if (strA == null) {
                    return false;
                }
                ldVar.b(this.c ? "X-ADP-Request-Digest" : "x-adp-signature", strA);
                ldVar.b(this.c ? "X-ADP-Authentication-Token" : "x-adp-token", str);
                boolean z = this.c;
                String str2 = null;
                if ((z ? null : "x-adp-alg") == null) {
                    return true;
                }
                String str3 = z ? null : "x-adp-alg";
                if (!z) {
                    a();
                    str2 = this.f585a + ":1.0";
                }
                ldVar.b(str3, str2);
                return true;
            } catch (Exception e2) {
                Log.e(ga.a(d), "Exception in getting ADP signature.", e2);
            }
        }
        return false;
    }

    public final void a() {
        if (this.f585a == null) {
            ac acVar = this.b;
            String str = acVar.c;
            if (str == null) {
                String str2 = acVar.a().b;
                if (str2 == null) {
                    acVar.c = null;
                } else if (str2.contains("-----BEGIN EC PRIVATE KEY-----")) {
                    acVar.c = "SHA256withECDSA";
                } else {
                    acVar.c = "SHA256WithRSA";
                }
                str = acVar.c;
            }
            this.f585a = str;
        }
    }
}
