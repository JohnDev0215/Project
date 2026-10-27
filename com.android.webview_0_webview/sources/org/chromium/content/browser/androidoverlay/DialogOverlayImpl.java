package org.chromium.content.browser.androidoverlay;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.Surface;
import defpackage.AA;
import defpackage.AbstractC0543bJ;
import defpackage.C1044kI;
import defpackage.C1211nI;
import defpackage.C1378qK;
import defpackage.CA;
import defpackage.DA;
import defpackage.EA;
import defpackage.FA;
import defpackage.InterfaceC0709eI;
import defpackage.InterfaceC0765fI;
import defpackage.InterfaceC0822gJ;
import defpackage.InterfaceC1862zA;
import defpackage.Is;
import defpackage.JA;
import defpackage.WJ;
import org.chromium.base.ThreadUtils;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogOverlayImpl implements InterfaceC0709eI, InterfaceC1862zA {
    public boolean A;
    public InterfaceC0765fI s;
    public Handler t;
    public Runnable u;
    public Runnable v;
    public final JA w;
    public AA x;
    public long y;
    public int z;

    public DialogOverlayImpl(InterfaceC0765fI interfaceC0765fI, C1211nI c1211nI, Handler handler, Runnable runnable, boolean z) {
        ThreadUtils.b();
        this.s = interfaceC0765fI;
        this.u = runnable;
        this.t = handler;
        this.x = new AA();
        this.w = new JA(this);
        C1378qK c1378qK = c1211nI.b;
        long jMqPi0d6D = N.MqPi0d6D(this, c1378qK.b, c1378qK.c, c1211nI.e);
        this.y = jMqPi0d6D;
        if (jMqPi0d6D == 0) {
            ((C1044kI) this.s).x();
            P();
            return;
        }
        AA aa = this.x;
        Context context = Is.a;
        N.MAd6qeVr(jMqPi0d6D, this, c1211nI.c);
        this.t.post(new CA(this, aa, context, c1211nI, z));
        this.v = new DA(this, aa);
    }

    private void onPowerEfficientState(boolean z) {
        InterfaceC0765fI interfaceC0765fI;
        ThreadUtils.b();
        if (this.x == null || (interfaceC0765fI = this.s) == null) {
            return;
        }
        ((C1044kI) interfaceC0765fI).A(z);
    }

    public static void receiveCompositorOffset(Rect rect, int i, int i2) {
        rect.b += i;
        rect.c += i2;
    }

    @Override // defpackage.InterfaceC1862zA
    public void A(Surface surface) {
        ThreadUtils.b();
        if (this.x == null || this.s == null) {
            return;
        }
        int iMpcpmTlm = N.MpcpmTlm(surface);
        this.z = iMpcpmTlm;
        ((C1044kI) this.s).G(iMpcpmTlm);
    }

    @Override // defpackage.InterfaceC1862zA
    public void G() {
        close();
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        ThreadUtils.b();
        close();
    }

    @Override // defpackage.InterfaceC1862zA
    public void M() {
    }

    public final void P() {
        ThreadUtils.b();
        if (this.z != 0) {
            N.M1e4GdYZ(this.z);
            this.z = 0;
        }
        if (this.y != 0) {
            N.MJj9v_ba(this.y, this);
            this.y = 0L;
        }
        this.x = null;
        InterfaceC0822gJ interfaceC0822gJ = this.s;
        if (interfaceC0822gJ != null) {
            ((AbstractC0543bJ) interfaceC0822gJ).close();
        }
        this.s = null;
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ThreadUtils.b();
        if (this.A) {
            return;
        }
        this.A = true;
        this.w.u.release(1);
        Runnable runnable = this.v;
        if (runnable != null) {
            this.t.post(runnable);
            this.v = null;
            P();
        }
        this.u.run();
    }

    @Override // defpackage.InterfaceC0709eI
    public void n(Rect rect) {
        ThreadUtils.b();
        if (this.x == null) {
            return;
        }
        N.MAd6qeVr(this.y, this, rect);
        this.t.post(new EA(this, this.x, rect));
    }

    public void onDismissed() {
        ThreadUtils.b();
        InterfaceC0765fI interfaceC0765fI = this.s;
        if (interfaceC0765fI != null) {
            ((C1044kI) interfaceC0765fI).x();
        }
        AA aa = this.x;
        if (aa != null) {
            this.t.post(new FA(this, aa, null));
        }
        P();
    }

    public void onWindowToken(IBinder iBinder) {
        ThreadUtils.b();
        AA aa = this.x;
        if (aa == null || aa == null) {
            return;
        }
        this.t.post(new FA(this, aa, iBinder));
    }

    @Override // defpackage.InterfaceC1862zA
    public void x() {
        ThreadUtils.b();
        if (this.x == null) {
            return;
        }
        InterfaceC0765fI interfaceC0765fI = this.s;
        if (interfaceC0765fI != null) {
            ((C1044kI) interfaceC0765fI).x();
        }
        P();
    }
}
