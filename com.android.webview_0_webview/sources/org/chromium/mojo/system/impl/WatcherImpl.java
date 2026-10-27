package org.chromium.mojo.system.impl;

import J.N;
import defpackage.AbstractC0935iK;
import defpackage.InterfaceC0600cK;
import defpackage.InterfaceC0656dK;
import defpackage.KJ;
import defpackage.OJ;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WatcherImpl implements InterfaceC0656dK {
    public long a = N.MXGgOw9k(this);
    public InterfaceC0600cK b;

    private void onHandleReady(int i) {
        this.b.a(i);
    }

    public int a(OJ oj, KJ kj, InterfaceC0600cK interfaceC0600cK) {
        long j = this.a;
        if (j == 0 || !(oj instanceof AbstractC0935iK)) {
            return 3;
        }
        int iMOmBVeTt = N.MOmBVeTt(this, j, ((AbstractC0935iK) oj).s, kj.a);
        if (iMOmBVeTt == 0) {
            this.b = interfaceC0600cK;
        }
        return iMOmBVeTt;
    }
}
