package org.chromium.weblayer_private;

import J.N;
import defpackage.BinderC0998jS;
import defpackage.InterfaceC0552bS;
import defpackage.ZR;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class TabCallbackProxy {
    public long a;
    public InterfaceC0552bS b;

    public TabCallbackProxy(long j, InterfaceC0552bS interfaceC0552bS) {
        this.b = interfaceC0552bS;
        this.a = N.MOUs9Qy6(this, j);
    }

    private void onRenderProcessGone() {
        ((ZR) this.b).f();
    }

    private void onTitleUpdated(String str) {
        if (WebLayerFactoryImpl.c() < 83) {
            return;
        }
        ((ZR) this.b).u(new BinderC0998jS(str));
    }

    private void visibleUriChanged(String str) {
        ((ZR) this.b).w(str);
    }
}
