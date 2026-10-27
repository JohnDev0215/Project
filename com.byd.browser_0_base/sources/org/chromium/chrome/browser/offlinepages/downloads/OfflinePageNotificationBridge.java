package org.chromium.chrome.browser.offlinepages.downloads;

import defpackage.a8;
import defpackage.dy2;
import defpackage.hd0;
import defpackage.m62;
import defpackage.n3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* JADX INFO: loaded from: classes2.dex */
public class OfflinePageNotificationBridge {
    public static hd0 a() {
        return DownloadManagerService.g().f;
    }

    @CalledByNative
    public static void notifyDownloadCanceled(String str) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        ((dy2) hd0VarA).c(n3.p(str, true));
    }

    @CalledByNative
    public static void notifyDownloadFailed(String str, String str2, String str3, int i) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.v = true;
        aVar.m = str;
        aVar.e = str3;
        ((dy2) hd0VarA).d(aVar.a());
    }

    @CalledByNative
    public static void notifyDownloadInterrupted(String str, String str2, int i) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.v = true;
        aVar.m = str;
        aVar.e = str2;
        aVar.r = true;
        dy2.a aVar2 = new dy2.a(4, new DownloadInfo(aVar), 0);
        aVar2.f = true;
        aVar2.g = i;
        ((dy2) hd0VarA).a(aVar2);
    }

    @CalledByNative
    public static void notifyDownloadPaused(String str, String str2) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.v = true;
        aVar.m = str;
        aVar.e = str2;
        ((dy2) hd0VarA).a(new dy2.a(1, new DownloadInfo(aVar), 0));
    }

    @CalledByNative
    public static void notifyDownloadProgress(String str, String str2, long j, long j2, String str3) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.v = true;
        aVar.m = str;
        aVar.e = str3;
        aVar.g = str2;
        aVar.j = j2;
        aVar.c(null);
        aVar.r = true;
        aVar.q = 0L;
        ((dy2) hd0VarA).a(new dy2.a(0, new DownloadInfo(aVar), 1));
    }

    @CalledByNative
    public static void notifyDownloadSuccessful(String str, String str2, String str3, long j) {
        hd0 hd0VarA = a();
        if (hd0VarA == null) {
            return;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.v = true;
        aVar.m = str;
        aVar.e = str3;
        aVar.r = false;
        aVar.c(null);
        aVar.k = j;
        ((dy2) hd0VarA).e(new DownloadInfo(aVar), -1L, false, true);
    }

    @CalledByNative
    public static void showDownloadingToast() {
        a8.z().b(new m62(4));
        DownloadManagerService.g().m.c(null, true, false, false);
    }
}
