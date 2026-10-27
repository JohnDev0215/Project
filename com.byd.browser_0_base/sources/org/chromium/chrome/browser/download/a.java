package org.chromium.chrome.browser.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import defpackage.fd0;
import defpackage.ny;
import defpackage.q11;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.chrome.browser.download.g;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: compiled from: DownloadBroadcastManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends SplitCompatService.a {
    public final g b = g.a.a;
    public final Handler d = new Handler();
    public final RunnableC0077a e = new RunnableC0077a();
    public final fd0 c = fd0.a.a;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.download.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DownloadBroadcastManagerImpl.java */
    public class RunnableC0077a implements Runnable {
        public RunnableC0077a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            a.this.a.stopSelf();
        }
    }

    public static ny h(Intent intent) {
        if (intent.hasExtra("org.chromium.chrome.browser.download.DownloadContentId_Id") && intent.hasExtra("org.chromium.chrome.browser.download.DownloadContentId_Namespace")) {
            return new ny(q11.r(intent, "org.chromium.chrome.browser.download.DownloadContentId_Namespace"), q11.r(intent, "org.chromium.chrome.browser.download.DownloadContentId_Id"));
        }
        return null;
    }

    public static void i(Context context, Intent intent, long j, ny nyVar) {
        String strR = q11.r(intent, "DownloadFilePath");
        boolean zI = q11.i(intent, "IsSupportedMimeType", false);
        q11.i(intent, "org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", false);
        if (DownloadUtils.b(intent)) {
            OTRProfileID oTRProfileIDA = OTRProfileID.a(q11.p(intent.getExtras(), "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
            Uri uri = (Uri) q11.n(intent, "android.intent.extra.ORIGINATING_URI");
            Uri uri2 = (Uri) q11.n(intent, "android.intent.extra.REFERRER");
            DownloadManagerService.m(3, j, context, strR, nyVar.b, uri == null ? null : uri.toString(), uri2 == null ? null : uri2.toString(), null, oTRProfileIDA, zI);
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0118, code lost:
    
        r3.add(r2);
        r10.b(false);
        r2 = r10.b.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0124, code lost:
    
        r3 = (org.chromium.base.b.a) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x012b, code lost:
    
        if (r3.hasNext() == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x012d, code lost:
    
        ((org.chromium.chrome.browser.download.g.b) r3.next()).b();
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0151  */
    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int e(android.content.Intent r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.a.e(android.content.Intent, int, int):int");
    }
}
