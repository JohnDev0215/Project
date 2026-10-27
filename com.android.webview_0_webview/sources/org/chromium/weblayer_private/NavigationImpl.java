package org.chromium.weblayer_private;

import J.N;
import android.os.RemoteException;
import defpackage.HR;
import defpackage.InterfaceC1109lR;
import defpackage.KR;
import defpackage.NR;
import defpackage.ZQ;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class NavigationImpl extends HR {
    public final InterfaceC1109lR a;
    public long b;

    public NavigationImpl(NR nr, long j) {
        this.b = j;
        try {
            this.a = ((KR) nr).c(this);
            N.MAxzZ9OU(this.b, this);
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }

    private void onNativeDestroyed() {
        this.b = 0L;
    }

    public final void c() {
        if (this.b == 0) {
            throw new IllegalStateException("Using Navigation after native destroyed");
        }
    }
}
