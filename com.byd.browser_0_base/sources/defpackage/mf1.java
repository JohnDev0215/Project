package defpackage;

import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MerchantTrustBottomSheetCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mf1 extends bh0 {
    public int e;
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ nf1 g;

    public mf1(nf1 nf1Var, uu2 uu2Var) {
        this.g = nf1Var;
        this.f = uu2Var;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void b(ii iiVar) {
        int i;
        nf1 nf1Var = this.g;
        if (iiVar != nf1Var.k) {
            int i2 = this.e;
            wf1 wf1Var = nf1Var.h;
            wf1Var.c();
            wf1Var.b();
            wf1Var.a();
            oj2.O("MerchantTrust.BottomSheet.IsHalfViewed", wf1Var.h);
            oj2.O("MerchantTrust.BottomSheet.IsFullyViewed", wf1Var.i);
            oj2.U(i2, 10, "MerchantTrust.BottomSheet.CloseReason");
            wf1Var.e = false;
            wf1Var.f = false;
            wf1Var.g = false;
            wf1Var.h = false;
            wf1Var.i = false;
            wf1Var.j = 0L;
            wf1Var.k = 0L;
            wf1Var.l = 0L;
            Runnable runnable = this.f;
            if (runnable != null && ((i = this.e) == 0 || i == 1 || i == 2 || i == 3)) {
                runnable.run();
            }
            nf1Var.g.removeOnLayoutChangeListener(nf1Var);
            mf1 mf1Var = nf1Var.j;
            ji jiVar = nf1Var.f;
            if (mf1Var != null) {
                jiVar.e(mf1Var);
            }
            jiVar.c(nf1Var.k, true);
            nf1Var.k = null;
            qf1 qf1Var = nf1Var.i;
            of1 of1Var = qf1Var.l;
            if (of1Var != null) {
                of1Var.destroy();
                qf1Var.l = null;
            }
            WebContents webContents = qf1Var.i;
            if (webContents != null) {
                webContents.destroy();
                qf1Var.i = null;
                qf1Var.j = null;
            }
            qf1Var.k = null;
            qf1Var.h = null;
            ThinWebViewImpl thinWebViewImpl = nf1Var.m;
            if (thinWebViewImpl != null) {
                thinWebViewImpl.b();
            }
            nf1Var.m = null;
            j jVar = nf1Var.p;
            if (jVar != null) {
                jVar.c();
            }
            nf1Var.o = null;
            nf1Var.n = null;
        }
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void c(int i) {
        this.e = i;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void d(int i, int i2) {
        nf1 nf1Var = this.g;
        if (nf1Var.k == null) {
            return;
        }
        wf1 wf1Var = nf1Var.h;
        if (i == 1) {
            if (wf1Var.j == 0) {
                wf1Var.j = System.nanoTime();
            }
            wf1Var.b();
            wf1Var.a();
            return;
        }
        if (i == 2) {
            wf1Var.h = true;
            if (wf1Var.k == 0) {
                wf1Var.k = System.nanoTime();
            }
            wf1Var.c();
            wf1Var.a();
            return;
        }
        if (i != 3) {
            return;
        }
        wf1Var.i = true;
        if (wf1Var.l == 0) {
            wf1Var.l = System.nanoTime();
        }
        wf1Var.c();
        wf1Var.b();
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void e() {
        wf1 wf1Var = this.g.h;
        wf1Var.h = true;
        if (wf1Var.k == 0) {
            wf1Var.k = System.nanoTime();
        }
        wf1Var.c();
        wf1Var.a();
    }
}
