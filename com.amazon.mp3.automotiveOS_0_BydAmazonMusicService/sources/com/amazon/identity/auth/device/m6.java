package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m6 implements l6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f430a = "m6";

    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.content.Context r4, java.lang.String r5, boolean r6) {
        /*
            r3 = this;
            com.amazon.identity.auth.device.z5 r0 = new com.amazon.identity.auth.device.z5
            r1 = 0
            r0.<init>(r4, r1)
            java.lang.String r4 = r0.f701a
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L15
            boolean r4 = com.amazon.identity.auth.device.xa.a()
            if (r4 != 0) goto L15
            goto L3f
        L15:
            boolean r4 = r0.c
            if (r4 == 0) goto L1a
            goto L3d
        L1a:
            android.content.pm.PackageManager r4 = r0.b     // Catch: java.lang.Exception -> L23
            java.lang.String r2 = r0.f701a     // Catch: java.lang.Exception -> L23
            int r4 = r4.checkSignatures(r2, r5)     // Catch: java.lang.Exception -> L23
            goto L2f
        L23:
            r4 = move-exception
            com.amazon.identity.auth.device.z5.a(r4)
            android.content.pm.PackageManager r4 = r0.b
            java.lang.String r2 = r0.f701a
            int r4 = r4.checkSignatures(r2, r5)
        L2f:
            if (r4 != 0) goto L32
            goto L3f
        L32:
            java.util.Set<android.content.pm.Signature> r4 = r0.e
            if (r4 != 0) goto L37
            goto L3d
        L37:
            boolean r4 = r0.a(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            if (r4 != 0) goto L3f
        L3d:
            r4 = -3
            goto L42
        L3f:
            r4 = r1
            goto L42
        L41:
            r4 = -4
        L42:
            if (r4 != 0) goto L45
            r1 = 1
        L45:
            if (r6 == 0) goto L5a
            if (r1 == 0) goto L4f
            java.lang.String r4 = com.amazon.identity.auth.device.m6.f430a
            com.amazon.identity.auth.device.ga.a(r4)
            goto L5a
        L4f:
            java.lang.String r4 = com.amazon.identity.auth.device.m6.f430a
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)
            java.lang.String r5 = "MAP doesn't trust the package. Usually it is because the app is signed with a different cert comparing to current package"
            android.util.Log.e(r4, r5)
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.m6.a(android.content.Context, java.lang.String, boolean):boolean");
    }
}
