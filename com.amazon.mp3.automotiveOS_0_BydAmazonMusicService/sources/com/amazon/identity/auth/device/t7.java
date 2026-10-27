package com.amazon.identity.auth.device;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t7 {
    public static final String e = "com.amazon.identity.auth.device.t7";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f578a = new AtomicBoolean(false);
    public final List<qb> b;
    public String c;
    public JSONObject d;

    public t7(List<qb> list) {
        if (list != null) {
            this.b = list;
        } else {
            this.b = new ArrayList();
        }
        this.d = new JSONObject();
    }

    public final void a() {
        if (this.f578a.getAndSet(true)) {
            return;
        }
        for (qb qbVar : this.b) {
            try {
                String str = qbVar.b;
                if (str != null) {
                    if (str.startsWith("x-main")) {
                        this.c = a(str);
                    }
                    if (str.startsWith("x-main") || str.startsWith("x-acb")) {
                        this.d.put("https://www" + qbVar.f516a, qbVar.b);
                    }
                }
            } catch (JSONException e2) {
                Log.e(ga.a(e), "Adding JSON value failed", e2);
            }
        }
    }

    public String b() {
        a();
        return this.d.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[Catch: IllegalArgumentException -> 0x0042, TRY_LEAVE, TryCatch #0 {IllegalArgumentException -> 0x0042, blocks: (B:3:0x0001, B:5:0x000b, B:7:0x000f, B:17:0x0036, B:19:0x003a, B:8:0x0017, B:10:0x0024, B:12:0x0028, B:14:0x0031), top: B:24:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = ";"
            java.lang.String[] r5 = r5.split(r1)     // Catch: java.lang.IllegalArgumentException -> L42
            int r1 = r5.length     // Catch: java.lang.IllegalArgumentException -> L42
            r2 = 1
            if (r1 >= r2) goto L17
            java.lang.String r5 = com.amazon.identity.auth.device.t7.e     // Catch: java.lang.IllegalArgumentException -> L42
            java.lang.String r1 = "Cookie does not seem to be in a valid format"
            java.lang.String r5 = com.amazon.identity.auth.device.ga.a(r5)     // Catch: java.lang.IllegalArgumentException -> L42
            android.util.Log.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L42
            goto L2f
        L17:
            r1 = 0
            r5 = r5[r1]     // Catch: java.lang.IllegalArgumentException -> L42
            java.lang.String r1 = "="
            java.lang.String[] r5 = r5.split(r1)     // Catch: java.lang.IllegalArgumentException -> L42
            int r1 = r5.length     // Catch: java.lang.IllegalArgumentException -> L42
            r3 = 2
            if (r1 == r3) goto L31
            java.lang.String r5 = com.amazon.identity.auth.device.t7.e     // Catch: java.lang.IllegalArgumentException -> L42
            java.lang.String r1 = "Cookie name/value pair does not seem to be in a valid format"
            java.lang.String r5 = com.amazon.identity.auth.device.ga.a(r5)     // Catch: java.lang.IllegalArgumentException -> L42
            android.util.Log.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L42
        L2f:
            r5 = r0
            goto L33
        L31:
            r5 = r5[r2]     // Catch: java.lang.IllegalArgumentException -> L42
        L33:
            if (r5 == 0) goto L36
            return r5
        L36:
            java.lang.String r5 = com.amazon.identity.auth.device.t7.e     // Catch: java.lang.IllegalArgumentException -> L42
            java.lang.String r1 = "Found no x-main cookie in RegisterDeviceResponse"
            java.lang.String r5 = com.amazon.identity.auth.device.ga.a(r5)     // Catch: java.lang.IllegalArgumentException -> L42
            android.util.Log.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L42
            goto L61
        L42:
            r5 = move-exception
            java.lang.String r1 = com.amazon.identity.auth.device.t7.e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Caught exception parsing the cookie value out of RegisterDeviceResponse"
            r2.append(r3)
            java.lang.String r5 = r5.getMessage()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)
            android.util.Log.e(r1, r5)
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.t7.a(java.lang.String):java.lang.String");
    }
}
