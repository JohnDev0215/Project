package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import defpackage.ul0;
import org.chromium.chrome.browser.feed.webfeed.i;

/* JADX INFO: compiled from: WebFeedSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends i.c {
    public final /* synthetic */ int i;
    public final /* synthetic */ i j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(i iVar, int i) {
        super();
        this.j = iVar;
        this.i = i;
    }

    @Override // wp2.b
    public final void j(Object obj) {
        j jVar = this.g;
        if (jVar != null) {
            this.e.t(jVar);
            this.g = null;
        }
        ul0.b().b.c(this);
        i.this.e.remove(this);
        N.MxULk9PS(0, this.i == 2 ? 63 : 52);
        this.j.b.o();
    }
}
