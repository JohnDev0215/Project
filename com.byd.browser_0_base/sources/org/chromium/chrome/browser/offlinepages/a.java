package org.chromium.chrome.browser.offlinepages;

import defpackage.sy2;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: GetPagesByNamespaceForLivePageSharingCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements Callback<List<OfflinePageItem>> {
    public final Tab e;
    public final Callback<org.chromium.components.browser_ui.share.a> f;
    public final OfflinePageBridge g;

    public a(Tab tab, sy2 sy2Var, OfflinePageBridge offlinePageBridge) {
        this.e = tab;
        this.f = sy2Var;
        this.g = offlinePageBridge;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(List<OfflinePageItem> list) {
        Callback<org.chromium.components.browser_ui.share.a> callback;
        Tab tab;
        OfflinePageItem next;
        Iterator<OfflinePageItem> it = list.iterator();
        do {
            boolean zHasNext = it.hasNext();
            callback = this.f;
            tab = this.e;
            if (!zHasNext) {
                WebContents webContentsC = tab.c();
                ClientId clientId = new ClientId("live_page_sharing", Integer.toString(tab.getId()));
                WindowAndroid windowAndroidD = tab.D();
                OfflinePageBridge offlinePageBridge = this.g;
                offlinePageBridge.b(webContentsC, clientId, new e(windowAndroidD, callback, offlinePageBridge));
                return;
            }
            next = it.next();
        } while (!next.a.equals(tab.getUrl().j()));
        d.h(callback, next, tab.D());
    }
}
