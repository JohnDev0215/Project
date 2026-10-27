package org.chromium.android_webview.gfx;

import J.N;
import defpackage.AbstractC0715eO;
import defpackage.AbstractC0827gO;
import defpackage.InterfaceC0771fO;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RootBeginFrameSourceWebView extends AbstractC0715eO implements InterfaceC0771fO {
    public long s;
    public final AbstractC0827gO t;

    public RootBeginFrameSourceWebView(long j) {
        this.s = j;
        AbstractC0827gO abstractC0827gOC = AbstractC0827gO.c(Is.a);
        this.t = abstractC0827gOC;
        abstractC0827gOC.a.put(this, null);
        N.MsBIPWH$(this.s, this, this.t.h);
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void j(float f) {
        N.MsBIPWH$(this.s, this, f);
    }
}
