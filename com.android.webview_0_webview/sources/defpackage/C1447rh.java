package defpackage;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;

/* JADX INFO: renamed from: rh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1447rh implements InterfaceC1173mh {
    public static C1447rh c;
    public final Context a;
    public final ContentObserver b;

    public C1447rh(Context context) {
        this.a = context;
        this.b = new C1557th();
        context.getContentResolver().registerContentObserver(AbstractC0839gh.a, true, this.b);
    }

    public static synchronized void b() {
        if (c != null && c.a != null && c.b != null) {
            c.a.getContentResolver().unregisterContentObserver(c.b);
        }
        c = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042 A[Catch: all -> 0x006c, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:32:0x005b, B:34:0x0066, B:33:0x0061, B:11:0x0025, B:16:0x0030, B:18:0x003a, B:21:0x003e, B:23:0x0042, B:35:0x0068, B:36:0x006a), top: B:41:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.C1447rh c(android.content.Context r9) {
        /*
            java.lang.Class<rh> r0 = defpackage.C1447rh.class
            monitor-enter(r0)
            rh r1 = defpackage.C1447rh.c     // Catch: java.lang.Throwable -> L6c
            if (r1 != 0) goto L68
            java.lang.String r1 = "com.google.android.providers.gsf.permission.READ_GSERVICES"
            int r2 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L6c
            int r3 = android.os.Process.myUid()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r4 = r9.getPackageName()     // Catch: java.lang.Throwable -> L6c
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L6c
            int r2 = r9.checkPermission(r1, r2, r3)     // Catch: java.lang.Throwable -> L6c
            r6 = -1
            r7 = 1
            r8 = 0
            if (r2 != r6) goto L21
            goto L55
        L21:
            r2 = 23
            if (r5 < r2) goto L2a
            java.lang.String r1 = android.app.AppOpsManager.permissionToOp(r1)     // Catch: java.lang.Throwable -> L6c
            goto L2b
        L2a:
            r1 = 0
        L2b:
            if (r1 != 0) goto L2e
            goto L54
        L2e:
            if (r4 != 0) goto L40
            android.content.pm.PackageManager r4 = r9.getPackageManager()     // Catch: java.lang.Throwable -> L6c
            java.lang.String[] r3 = r4.getPackagesForUid(r3)     // Catch: java.lang.Throwable -> L6c
            if (r3 == 0) goto L55
            int r4 = r3.length     // Catch: java.lang.Throwable -> L6c
            if (r4 > 0) goto L3e
            goto L55
        L3e:
            r4 = r3[r8]     // Catch: java.lang.Throwable -> L6c
        L40:
            if (r5 < r2) goto L4f
            java.lang.Class<android.app.AppOpsManager> r2 = android.app.AppOpsManager.class
            java.lang.Object r2 = r9.getSystemService(r2)     // Catch: java.lang.Throwable -> L6c
            android.app.AppOpsManager r2 = (android.app.AppOpsManager) r2     // Catch: java.lang.Throwable -> L6c
            int r1 = r2.noteProxyOpNoThrow(r1, r4)     // Catch: java.lang.Throwable -> L6c
            goto L50
        L4f:
            r1 = 1
        L50:
            if (r1 == 0) goto L54
            r6 = -2
            goto L55
        L54:
            r6 = 0
        L55:
            if (r6 != 0) goto L58
            goto L59
        L58:
            r7 = 0
        L59:
            if (r7 == 0) goto L61
            rh r1 = new rh     // Catch: java.lang.Throwable -> L6c
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L6c
            goto L66
        L61:
            rh r1 = new rh     // Catch: java.lang.Throwable -> L6c
            r1.<init>()     // Catch: java.lang.Throwable -> L6c
        L66:
            defpackage.C1447rh.c = r1     // Catch: java.lang.Throwable -> L6c
        L68:
            rh r9 = defpackage.C1447rh.c     // Catch: java.lang.Throwable -> L6c
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6c
            return r9
        L6c:
            r9 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6c
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1447rh.c(android.content.Context):rh");
    }

    @Override // defpackage.InterfaceC1173mh
    public final Object a(final String str) {
        if (this.a == null) {
            return null;
        }
        try {
            return (String) AbstractC1338ph.a(new InterfaceC1283oh(this, str) { // from class: qh
                public final C1447rh a;
                public final String b;

                {
                    this.a = this;
                    this.b = str;
                }

                @Override // defpackage.InterfaceC1283oh
                public final Object a() {
                    C1447rh c1447rh = this.a;
                    return AbstractC0839gh.a(c1447rh.a.getContentResolver(), this.b);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String strValueOf = String.valueOf(str);
            Log.e("GservicesLoader", strValueOf.length() != 0 ? "Unable to read GServices for: ".concat(strValueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    public C1447rh() {
        this.a = null;
        this.b = null;
    }
}
