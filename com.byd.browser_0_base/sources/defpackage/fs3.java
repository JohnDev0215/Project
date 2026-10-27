package defpackage;

/* JADX INFO: compiled from: WebappDirectoryManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fs3 extends ac<Void> {
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    @Override // defpackage.i9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b() {
        /*
            r10 = this;
            java.util.concurrent.atomic.AtomicBoolean r10 = defpackage.gs3.a
            java.io.File r10 = new java.io.File
            java.lang.String r0 = org.chromium.base.PathUtils.getCacheDirectory()
            java.lang.String r1 = "webapk/update"
            r10.<init>(r0, r1)
            java.io.File[] r10 = r10.listFiles()
            r0 = 0
            if (r10 == 0) goto L48
            int r1 = r10.length
            r2 = r0
            r3 = r2
        L17:
            if (r2 >= r1) goto L49
            r4 = r10[r2]
            org.chromium.chrome.browser.webapps.WebappRegistry r5 = org.chromium.chrome.browser.webapps.WebappRegistry.b.a
            java.lang.String r4 = r4.getName()
            ds3 r4 = r5.c(r4)
            if (r4 != 0) goto L28
            goto L43
        L28:
            long r5 = java.lang.System.currentTimeMillis()
            r7 = 0
            android.content.SharedPreferences r4 = r4.b
            java.lang.String r9 = "last_check_web_manifest_update_time"
            long r7 = r4.getLong(r9, r7)
            long r5 = r5 - r7
            r7 = 86400000(0x5265c00, double:4.2687272E-316)
            int r4 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r4 >= 0) goto L40
            r4 = 1
            goto L41
        L40:
            r4 = r0
        L41:
            if (r4 != 0) goto L45
        L43:
            int r3 = r3 + 1
        L45:
            int r2 = r2 + 1
            goto L17
        L48:
            r3 = r0
        L49:
            java.lang.String r10 = "WebApk.Update.NumStaleUpdateRequestFiles"
            defpackage.oj2.R(r3, r10)
            android.content.Context r10 = defpackage.xz.a
            java.lang.String r1 = "WebappActivity"
            java.io.File r10 = r10.getDir(r1, r0)
            om0 r0 = defpackage.pm0.a
            defpackage.pm0.d(r10, r0)
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fs3.b():java.lang.Object");
    }
}
