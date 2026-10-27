package org.chromium.chrome.browser.feed.webfeed;

import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.widget.LoadingView;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedFollowIntroController.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements LoadingView.c {
    public final /* synthetic */ WebFeedBridge$FollowResults e;
    public final /* synthetic */ Tab f;
    public final /* synthetic */ GURL g;
    public final /* synthetic */ e.b h;
    public final /* synthetic */ e i;

    public d(WebFeedBridge$FollowResults webFeedBridge$FollowResults, e.b bVar, e eVar, Tab tab, GURL gurl) {
        this.i = eVar;
        this.e = webFeedBridge$FollowResults;
        this.f = tab;
        this.g = gurl;
        this.h = bVar;
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        e eVar = this.i;
        eVar.h.b();
        if (this.e.a == 1) {
            eVar.h.d();
        }
        WebFeedBridge$FollowResults webFeedBridge$FollowResults = this.e;
        WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = webFeedBridge$FollowResults.b;
        eVar.g.a(this.f, webFeedBridge$FollowResults, webFeedBridge$WebFeedMetadata != null ? webFeedBridge$WebFeedMetadata.a : null, this.g, this.h.c, 2);
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
    }
}
