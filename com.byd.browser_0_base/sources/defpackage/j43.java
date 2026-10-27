package defpackage;

import android.animation.AnimatorSet;
import android.os.SystemClock;
import java.util.ArrayList;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class j43 implements Runnable {
    public final /* synthetic */ l43 e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ boolean h;

    public /* synthetic */ j43(l43 l43Var, boolean z, boolean z2, boolean z3) {
        this.e = l43Var;
        this.f = z;
        this.g = z2;
        this.h = z3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l43 l43Var = this.e;
        l43Var.getClass();
        p11 p11Var = new p11(16, l43Var);
        int i = 0;
        int i2 = 1;
        boolean z = l43Var.k.z().getCount() == 0;
        boolean z2 = this.f;
        boolean z3 = z2 && l43Var.S() && !z && !this.g;
        if (CachedFeatureFlags.b(ar.d.b("TabToGTSAnimation:skip-slow-zooming"), true)) {
            z3 &= this.h;
        }
        if (oj2.C(l43Var.i)) {
            z3 &= !qo.f().c();
        }
        if (!z3 || p11Var.get() == null) {
            l43Var.x.r(z2);
            return;
        }
        l43Var.h();
        if (l43Var.o == null) {
            m61 m61VarC = l43Var.c(l43Var.k.k(), l43Var.k.r());
            m61VarC.v();
            l43Var.o = new m61[]{m61VarC};
        }
        m61 m61Var = l43Var.o[0];
        ew ewVarJ = l43Var.j();
        ArrayList arrayList = new ArrayList(5);
        se1 se1Var = new se1(11);
        k43 k43Var = new k43(l43Var, i, p11Var);
        m30 m30Var = new m30(2);
        jj2 jj2Var = new jj2(l43Var, 3, p11Var);
        se1 se1Var2 = new se1(12);
        k43 k43Var2 = new k43(l43Var, i2, p11Var);
        PropertyModel.o oVar = m61.g;
        jk0 jk0Var = h21.e;
        arrayList.add(kw.k(ewVarJ, m61Var, oVar, se1Var, k43Var, 300L, jk0Var));
        arrayList.add(kw.k(ewVarJ, m61Var, m61.h, m30Var, jj2Var, 300L, jk0Var));
        arrayList.add(kw.k(ewVarJ, m61Var, m61.i, se1Var2, k43Var2, 300L, jk0Var));
        arrayList.add(kw.j(ewVarJ, m61Var, m61.w, m61Var.t(), oj2.G() ? Math.min(l43Var.e / TabUtils.c(l43Var.i), m61Var.t()) : l43Var.e, 300L, jk0Var));
        kw kwVarE = kw.e(ewVarJ, 0.0f, 1.0f, 150L, new y43(l43Var, i2));
        kwVarE.i = h21.d;
        arrayList.add(kwVarE);
        AnimatorSet animatorSet = new AnimatorSet();
        l43Var.t = animatorSet;
        animatorSet.playTogether(arrayList);
        l43Var.t.addListener(new m43(l43Var));
        l43Var.L = l43Var.H;
        l43Var.I = SystemClock.elapsedRealtime();
        l43Var.f44J = SystemClock.elapsedRealtime();
        l43Var.K = 0L;
        l43Var.t.start();
    }
}
