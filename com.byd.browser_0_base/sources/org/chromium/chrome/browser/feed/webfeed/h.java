package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import defpackage.ul0;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$UnfollowResults;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends i.c {
    public final /* synthetic */ byte[] i;
    public final /* synthetic */ int j;
    public final /* synthetic */ GURL k;
    public final /* synthetic */ String l;
    public final /* synthetic */ i m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(i iVar, byte[] bArr, int i, GURL gurl, String str) {
        super();
        this.m = iVar;
        this.i = bArr;
        this.j = i;
        this.k = gurl;
        this.l = str;
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
        N.MxULk9PS(0, 36);
        final byte[] bArr = this.i;
        final int i = this.j;
        final GURL gurl = this.k;
        final String str = this.l;
        N.MVWVyQhp(bArr, false, i, new Callback() { // from class: kr3
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                this.e.m.e(((WebFeedBridge$UnfollowResults) obj2).a, bArr, gurl, str, i);
            }
        });
    }
}
