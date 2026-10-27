package org.chromium.weblayer_private;

import J.N;
import defpackage.BinderC0998jS;
import defpackage.C1493sQ;
import defpackage.ER;
import defpackage.GR;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class FullscreenCallbackProxy {
    public long a;
    public GR b;

    public FullscreenCallbackProxy(long j, GR gr) {
        this.b = gr;
        this.a = N.MGmykU1g(this, j);
    }

    private void enterFullscreen() {
        C1493sQ c1493sQ = new C1493sQ(this);
        ((ER) this.b).c(new BinderC0998jS(c1493sQ));
    }

    private void exitFullscreen() {
        ((ER) this.b).d();
    }
}
