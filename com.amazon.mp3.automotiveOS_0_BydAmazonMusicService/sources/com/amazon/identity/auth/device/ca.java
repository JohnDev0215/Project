package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ca {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f266a = new a();

    /* JADX INFO: compiled from: DCP */
    public static class a implements b {
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0009  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent a(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            com.amazon.identity.auth.device.t9$a r5 = com.amazon.identity.auth.device.t9.b(r4)
            r0 = 1
            r1 = 0
            r2 = 0
            if (r5 != 0) goto Lb
        L9:
            r3 = r1
            goto L2a
        Lb:
            java.lang.String r5 = r5.f583a
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            r3.setClassName(r5, r6)
            android.content.pm.PackageManager r5 = r4.getPackageManager()
            java.util.List r5 = r5.queryIntentActivities(r3, r2)
            if (r5 == 0) goto L27
            int r5 = r5.size()
            if (r5 <= 0) goto L27
            r5 = r0
            goto L28
        L27:
            r5 = r2
        L28:
            if (r5 == 0) goto L9
        L2a:
            if (r3 == 0) goto L2e
        L2c:
            r1 = r3
            goto L4f
        L2e:
            java.lang.String r5 = r4.getPackageName()
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            r3.setClassName(r5, r6)
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            java.util.List r4 = r4.queryIntentActivities(r3, r2)
            if (r4 == 0) goto L4b
            int r4 = r4.size()
            if (r4 <= 0) goto L4b
            goto L4c
        L4b:
            r0 = r2
        L4c:
            if (r0 == 0) goto L4f
            goto L2c
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ca.a(android.content.Context, java.lang.String, java.lang.String):android.content.Intent");
    }
}
