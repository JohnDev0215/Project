package org.chromium.chrome.browser.feed.webfeed;

import defpackage.nh0;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends nh0 {
    public final /* synthetic */ i.c e;

    public j(i.b bVar) {
        this.e = bVar;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        i.c cVar = this.e;
        i.this.c.a(cVar);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        i.c cVar = this.e;
        i.this.c.a(cVar);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        i.c cVar = this.e;
        if (cVar.f.equals(gurl)) {
            return;
        }
        i.this.c.a(cVar);
    }
}
