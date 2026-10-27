package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t4 {
    public static final String d = "com.amazon.identity.auth.device.t4";
    public static t4 e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f563a;
    public final u7 b;
    public final MAPApplicationInformationQueryer c;

    public t4(Context context) {
        t5 t5VarA = t5.a(context);
        this.f563a = t5VarA;
        this.b = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
        this.c = MAPApplicationInformationQueryer.a(t5VarA);
    }

    public static synchronized t4 a(Context context) {
        if (e == null) {
            e = new t4(context.getApplicationContext());
        }
        return e;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00d3 A[Catch: all -> 0x0159, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x003b, B:7:0x0043, B:8:0x004a, B:13:0x0059, B:14:0x005a, B:16:0x0062, B:18:0x0068, B:22:0x009b, B:20:0x008c, B:21:0x0097, B:24:0x00ab, B:25:0x00ac, B:26:0x00ad, B:28:0x00d3, B:30:0x00db, B:37:0x0113, B:39:0x0119, B:41:0x0121, B:42:0x0136, B:31:0x00e8, B:33:0x00f2, B:36:0x0108, B:43:0x0145, B:9:0x004b, B:11:0x004f, B:12:0x0057), top: B:51:0x0001, inners: #0, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int a() {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.t4.a():int");
    }
}
