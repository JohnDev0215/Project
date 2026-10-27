package defpackage;

import J.N;
import android.content.LocusId;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.DataRemovalRequest;
import org.chromium.chrome.browser.history.HistoryDeletionBridge;
import org.chromium.chrome.browser.history.HistoryDeletionInfo;
import org.chromium.components.content_capture.PlatformContentCaptureController;

/* JADX INFO: compiled from: ContentCaptureHistoryDeletionObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class ky implements HistoryDeletionBridge.a {
    public final nw2<PlatformContentCaptureController> a;

    public ky(m30 m30Var) {
        this.a = m30Var;
    }

    @Override // org.chromium.chrome.browser.history.HistoryDeletionBridge.a
    public final void a(HistoryDeletionInfo historyDeletionInfo) {
        PlatformContentCaptureController platformContentCaptureController = this.a.get();
        if (platformContentCaptureController == null) {
            return;
        }
        boolean zMDNd$JT3 = N.MDNd$JT3(historyDeletionInfo.a);
        ContentCaptureManager contentCaptureManager = platformContentCaptureController.c;
        if (!zMDNd$JT3) {
            long j = historyDeletionInfo.a;
            if (!N.MBUJ8Aeh(j)) {
                String[] strArrMLyEE9$M = N.MLyEE9$M(j);
                if (strArrMLyEE9$M.length <= 0 || contentCaptureManager == null) {
                    return;
                }
                try {
                    DataRemovalRequest.Builder builder = new DataRemovalRequest.Builder();
                    for (String str : strArrMLyEE9$M) {
                        builder = builder.addLocusId(new LocusId(str), 0);
                    }
                    contentCaptureManager.removeData(builder.build());
                    return;
                } catch (RuntimeException e) {
                    throw new uv("Deleted URLs length: " + strArrMLyEE9$M.length, e);
                }
            }
        }
        if (contentCaptureManager == null) {
            return;
        }
        contentCaptureManager.removeData(new DataRemovalRequest.Builder().forEverything().build());
    }
}
