package defpackage;

import J.N;
import org.chromium.chrome.browser.download.DownloadController;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class q52 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        if (iv.e().g("type")) {
            return;
        }
        DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
        sk skVar = bq.a;
        if (N.M09VlOh_("UseDownloadOfflineContentProvider")) {
            return;
        }
        DownloadController.a = downloadManagerServiceG;
    }
}
