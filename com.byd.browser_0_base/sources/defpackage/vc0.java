package defpackage;

import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: compiled from: DownloadManagerService.java */
/* JADX INFO: loaded from: classes.dex */
public final class vc0 extends i9<Intent> {
    public final /* synthetic */ String h;
    public final /* synthetic */ long i;
    public final /* synthetic */ boolean j;
    public final /* synthetic */ String k;
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Context n;
    public final /* synthetic */ OTRProfileID o;
    public final /* synthetic */ int p;
    public final /* synthetic */ String q;

    public vc0(int i, long j, Context context, String str, String str2, String str3, String str4, String str5, OTRProfileID oTRProfileID, boolean z) {
        this.h = str;
        this.i = j;
        this.j = z;
        this.k = str2;
        this.l = str3;
        this.m = str4;
        this.n = context;
        this.o = oTRProfileID;
        this.p = i;
        this.q = str5;
    }

    @Override // defpackage.i9
    public final Intent b() {
        return DownloadManagerService.j(this.h, this.i, this.j, this.k, this.l, this.m);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
    @Override // defpackage.i9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(android.content.Intent r6) {
        /*
            r5 = this;
            android.content.Intent r6 = (android.content.Intent) r6
            android.content.Context r0 = r5.n
            r1 = 0
            r2 = 1
            if (r6 == 0) goto L3d
            boolean r3 = defpackage.hj0.o(r6)
            if (r3 == 0) goto L3d
            java.lang.String r3 = r6.getPackage()     // Catch: java.lang.Exception -> L26 java.lang.SecurityException -> L2a android.content.ActivityNotFoundException -> L2e
            java.lang.String r4 = r0.getPackageName()     // Catch: java.lang.Exception -> L26 java.lang.SecurityException -> L2a android.content.ActivityNotFoundException -> L2e
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Exception -> L26 java.lang.SecurityException -> L2a android.content.ActivityNotFoundException -> L2e
            if (r3 == 0) goto L21
            r3 = 0
            org.chromium.chrome.browser.b.A(r3, r6, r3)     // Catch: java.lang.Exception -> L26 java.lang.SecurityException -> L2a android.content.ActivityNotFoundException -> L2e
            goto L24
        L21:
            r0.startActivity(r6)     // Catch: java.lang.Exception -> L26 java.lang.SecurityException -> L2a android.content.ActivityNotFoundException -> L2e
        L24:
            r6 = r2
            goto L39
        L26:
            java.util.Objects.toString(r6)
            goto L38
        L2a:
            java.util.Objects.toString(r6)
            goto L38
        L2e:
            r6.getType()
            android.net.Uri r6 = r6.getData()
            r6.getScheme()
        L38:
            r6 = r1
        L39:
            if (r6 == 0) goto L3d
            r6 = r2
            goto L3e
        L3d:
            r6 = r1
        L3e:
            int r3 = r5.p
            org.chromium.chrome.browser.profiles.OTRProfileID r4 = r5.o
            if (r6 != 0) goto L48
            org.chromium.chrome.browser.download.DownloadManagerService.openDownloadsPage(r4, r3)
            goto L6f
        L48:
            if (r6 == 0) goto L6f
            java.util.HashSet r6 = org.chromium.chrome.browser.download.DownloadManagerService.r
            java.lang.Object r6 = org.chromium.base.ThreadUtils.a
            org.chromium.chrome.browser.download.DownloadManagerService r6 = org.chromium.chrome.browser.download.DownloadManagerService.s
            if (r6 == 0) goto L53
            r1 = r2
        L53:
            if (r1 == 0) goto L6f
            org.chromium.chrome.browser.download.DownloadManagerService r6 = org.chromium.chrome.browser.download.DownloadManagerService.g()
            java.lang.String r1 = r5.q
            r6.q(r1, r4)
            java.lang.String r6 = "download"
            java.lang.Object r6 = r0.getSystemService(r6)
            android.app.DownloadManager r6 = (android.app.DownloadManager) r6
            long r0 = r5.i
            java.lang.String r5 = r6.getMimeTypeForDownloadedFile(r0)
            defpackage.ed0.a(r3, r5)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vc0.l(java.lang.Object):void");
    }
}
