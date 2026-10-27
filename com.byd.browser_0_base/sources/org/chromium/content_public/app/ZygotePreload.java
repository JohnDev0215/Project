package org.chromium.content_public.app;

/* JADX INFO: loaded from: classes3.dex */
public class ZygotePreload implements android.app.ZygotePreload {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038 A[Catch: all -> 0x004c, TryCatch #1 {all -> 0x004c, blocks: (B:3:0x0007, B:5:0x0022, B:10:0x002e, B:12:0x003a, B:13:0x003e, B:19:0x004b, B:11:0x0038, B:14:0x003f, B:15:0x0047), top: B:25:0x0007, inners: #0 }] */
    @Override // android.app.ZygotePreload
    @android.annotation.SuppressLint({"Override"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void doPreload(android.content.pm.ApplicationInfo r9) {
        /*
            r8 = this;
            java.lang.String r8 = "Loaded Zygote. version=4.1.1.0 minSdkVersion=24 isBundle=false"
            java.lang.String r0 = "cr_ZygotePreload"
            android.util.Log.i(r0, r8)
            int r8 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L4c
            long r0 = android.os.SystemClock.currentThreadTimeMillis()     // Catch: java.lang.Throwable -> L4c
            defpackage.zn.q = r8     // Catch: java.lang.Throwable -> L4c
            defpackage.zn.r = r0     // Catch: java.lang.Throwable -> L4c
            java.lang.Boolean r8 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L4c
            org.chromium.base.JNIUtils.a = r8     // Catch: java.lang.Throwable -> L4c
            org.chromium.base.library_loader.a r8 = org.chromium.base.library_loader.a.l     // Catch: java.lang.Throwable -> L4c
            org.chromium.base.library_loader.a$a r0 = r8.g     // Catch: java.lang.Throwable -> L4c
            org.chromium.base.library_loader.a r1 = org.chromium.base.library_loader.a.this     // Catch: java.lang.Throwable -> L4c
            boolean r2 = r1.d     // Catch: java.lang.Throwable -> L4c
            r3 = 1
            if (r2 == 0) goto L38
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L4c
            r4 = 30
            r5 = 0
            if (r2 > r4) goto L2b
            r2 = r3
            goto L2c
        L2b:
            r2 = r5
        L2c:
            if (r2 != 0) goto L38
            org.chromium.base.library_loader.Linker r1 = r1.b()     // Catch: java.lang.Throwable -> L4c
            r6 = 0
            r1.d(r6, r5, r3)     // Catch: java.lang.Throwable -> L4c
            goto L3a
        L38:
            r1.b = r3     // Catch: java.lang.Throwable -> L4c
        L3a:
            r0.c = r3     // Catch: java.lang.Throwable -> L4c
            java.lang.Object r0 = r8.h     // Catch: java.lang.Throwable -> L4c
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L4c
            r8.e(r9, r3)     // Catch: java.lang.Throwable -> L49
            r8.f()     // Catch: java.lang.Throwable -> L49
            r8.j = r3     // Catch: java.lang.Throwable -> L49
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L49
            goto L54
        L49:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L49
            throw r8     // Catch: java.lang.Throwable -> L4c
        L4c:
            r8 = move-exception
            java.lang.String r9 = "Exception in zygote"
            java.lang.String r0 = "cr_ZygotePreload"
            android.util.Log.w(r0, r9, r8)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content_public.app.ZygotePreload.doPreload(android.content.pm.ApplicationInfo):void");
    }
}
