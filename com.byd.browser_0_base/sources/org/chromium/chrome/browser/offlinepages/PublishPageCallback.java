package org.chromium.chrome.browser.offlinepages;

import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PublishPageCallback implements Callback<String> {
    public final Callback<org.chromium.components.browser_ui.share.a> e;
    public final OfflinePageItem f;
    public final WindowAndroid g;

    public PublishPageCallback(Callback callback, OfflinePageItem offlinePageItem, WindowAndroid windowAndroid) {
        this.g = windowAndroid;
        this.f = offlinePageItem;
        this.e = callback;
    }

    @Override // org.chromium.base.Callback
    @CalledByNative
    public void onResult(String str) {
        OfflinePageItem offlinePageItem;
        if (str.isEmpty()) {
            offlinePageItem = null;
        } else {
            OfflinePageItem offlinePageItem2 = this.f;
            String str2 = offlinePageItem2.a;
            long j = offlinePageItem2.b;
            ClientId clientId = offlinePageItem2.c;
            offlinePageItem = new OfflinePageItem(str2, j, clientId.a, clientId.b, offlinePageItem2.d, str, offlinePageItem2.f, offlinePageItem2.g, offlinePageItem2.h, offlinePageItem2.i, offlinePageItem2.j);
        }
        d.h(this.e, offlinePageItem, this.g);
    }
}
