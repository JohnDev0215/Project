package org.chromium.chrome.browser.history;

import J.N;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes.dex */
public class HistoryDeletionBridge {
    public static HistoryDeletionBridge b;
    public final org.chromium.base.b<a> a = new org.chromium.base.b<>();

    public interface a {
        void a(HistoryDeletionInfo historyDeletionInfo);
    }

    public HistoryDeletionBridge() {
        N.M41yd4uo(this);
    }

    @CalledByNative
    public void onURLsDeleted(HistoryDeletionInfo historyDeletionInfo) {
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(historyDeletionInfo);
            }
        }
    }
}
