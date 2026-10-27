package org.chromium.weblayer_private;

import J.N;
import defpackage.AR;
import defpackage.C1824yR;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ErrorPageCallbackProxy {
    public long a;
    public AR b;

    public ErrorPageCallbackProxy(long j, AR ar) {
        this.b = ar;
        this.a = N.M0JND2JE(this, j);
    }

    private boolean onBackToSafety() {
        return ((C1824yR) this.b).c();
    }
}
