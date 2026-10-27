package org.chromium.chrome.browser.download;

import android.util.Pair;
import defpackage.dy2;
import defpackage.i9;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* JADX INFO: compiled from: DownloadManagerService.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends i9<Pair<Boolean, Boolean>> {
    public final /* synthetic */ DownloadItem h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ DownloadManagerService j;

    public c(DownloadManagerService downloadManagerService, DownloadItem downloadItem, DownloadInfo downloadInfo, boolean z) {
        this.j = downloadManagerService;
        this.h = downloadItem;
        this.i = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003c  */
    @Override // defpackage.i9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<java.lang.Boolean, java.lang.Boolean> b() {
        /*
            r9 = this;
            java.util.HashSet r0 = org.chromium.chrome.browser.download.DownloadManagerService.r
            org.chromium.chrome.browser.download.DownloadManagerService r0 = r9.j
            r0.getClass()
            org.chromium.chrome.browser.download.DownloadItem r0 = r9.h
            org.chromium.chrome.browser.download.DownloadInfo r1 = r0.c
            java.lang.String r1 = r1.g
            org.chromium.base.ContentUriUtils.d(r1)
            sk r1 = defpackage.bq.a
            java.lang.String r1 = "UseDownloadOfflineContentProvider"
            J.N.M09VlOh_(r1)
            org.chromium.chrome.browser.download.DownloadInfo r1 = r0.c
            java.lang.String r1 = r1.c
            boolean r1 = org.chromium.chrome.browser.download.MimeUtils.isOMADownloadDescription(r1)
            if (r1 != 0) goto L3c
            boolean r5 = r9.i
            org.chromium.chrome.browser.download.DownloadInfo r9 = r0.c
            java.lang.String r2 = r9.g
            long r3 = r0.d
            r6 = 0
            r7 = 0
            java.lang.String r8 = r9.c
            android.content.Intent r9 = org.chromium.chrome.browser.download.DownloadManagerService.j(r2, r3, r5, r6, r7, r8)
            r0 = 0
            if (r9 != 0) goto L36
            r9 = r0
            goto L3a
        L36:
            boolean r9 = defpackage.hj0.o(r9)
        L3a:
            if (r9 == 0) goto L3d
        L3c:
            r0 = 1
        L3d:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            android.util.Pair r9 = android.util.Pair.create(r9, r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.c.b():java.lang.Object");
    }

    @Override // defpackage.i9
    public final void l(Pair<Boolean, Boolean> pair) {
        Pair<Boolean, Boolean> pair2 = pair;
        DownloadItem downloadItem = this.h;
        DownloadInfo downloadInfo = downloadItem.c;
        boolean zBooleanValue = ((Boolean) pair2.first).booleanValue();
        DownloadManagerService downloadManagerService = this.j;
        if (!zBooleanValue) {
            DownloadInfo.a aVarB = DownloadInfo.a.b(downloadInfo);
            aVarB.F = 1;
            ((dy2) downloadManagerService.f).d(new DownloadInfo(aVarB));
            return;
        }
        ((dy2) downloadManagerService.f).e(downloadInfo, downloadItem.d, ((Boolean) pair2.second).booleanValue(), this.i);
        Iterator<DownloadManagerService.a> it = downloadManagerService.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((DownloadManagerService.a) aVar.next()).getClass();
            }
        }
    }
}
