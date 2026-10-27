package org.chromium.chrome.browser.offlinepages;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SavePageAndShareCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements OfflinePageBridge.SavePageCallback {
    public final WindowAndroid a;
    public final Callback<org.chromium.components.browser_ui.share.a> b;
    public final OfflinePageBridge c;

    /* JADX INFO: compiled from: SavePageAndShareCallback.java */
    public class a implements Callback<OfflinePageItem> {
        public a() {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(OfflinePageItem offlinePageItem) {
            e eVar = e.this;
            WindowAndroid windowAndroid = eVar.a;
            d.h(eVar.b, offlinePageItem, windowAndroid);
        }
    }

    public e(WindowAndroid windowAndroid, Callback<org.chromium.components.browser_ui.share.a> callback, OfflinePageBridge offlinePageBridge) {
        this.a = windowAndroid;
        this.b = callback;
        this.c = offlinePageBridge;
    }

    @Override // org.chromium.chrome.browser.offlinepages.OfflinePageBridge.SavePageCallback
    public final void onSavePageDone(int i, String str, long j) {
        if (i != 0) {
            return;
        }
        a aVar = new a();
        OfflinePageBridge offlinePageBridge = this.c;
        N.M8YdeM7z(offlinePageBridge.a, offlinePageBridge, j, aVar);
    }
}
