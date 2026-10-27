package org.chromium.chrome.browser.download;

import defpackage.ny;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class DownloadItem {
    public final ny a;
    public final boolean b;
    public DownloadInfo c;
    public long d;
    public long e;
    public long f;
    public boolean g;

    public DownloadItem(DownloadInfo downloadInfo, boolean z) {
        ny nyVar = new ny();
        this.a = nyVar;
        this.d = -1L;
        this.b = z;
        this.c = downloadInfo;
        if (downloadInfo != null) {
            nyVar.a = downloadInfo.z.a;
        }
        nyVar.b = a();
    }

    @CalledByNative
    public static DownloadItem createDownloadItem(DownloadInfo downloadInfo, long j, long j2, boolean z) {
        DownloadItem downloadItem = new DownloadItem(downloadInfo, false);
        downloadItem.e = j;
        downloadItem.f = j2;
        downloadItem.g = z;
        return downloadItem;
    }

    public final String a() {
        return this.b ? String.valueOf(this.d) : this.c.l;
    }
}
