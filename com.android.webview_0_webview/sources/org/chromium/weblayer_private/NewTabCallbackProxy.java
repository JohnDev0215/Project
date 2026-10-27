package org.chromium.weblayer_private;

import J.N;
import defpackage.AbstractC1054kS;
import defpackage.InterfaceC0552bS;
import defpackage.ZR;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class NewTabCallbackProxy {
    public long a;
    public final TabImpl b;

    public NewTabCallbackProxy(TabImpl tabImpl) {
        this.b = tabImpl;
        this.a = N.Mm1ByG3d(this, tabImpl.a);
    }

    private void onCloseTab() {
        ((ZR) this.b.m).c();
    }

    public void onNewTab(long j, int i) {
        TabImpl tabImpl = this.b;
        TabImpl tabImpl2 = new TabImpl(tabImpl.b, tabImpl.j.e, j);
        this.b.j.c(tabImpl2);
        InterfaceC0552bS interfaceC0552bS = this.b.m;
        AbstractC1054kS.a();
        ((ZR) interfaceC0552bS).d(tabImpl2.n, i);
    }
}
