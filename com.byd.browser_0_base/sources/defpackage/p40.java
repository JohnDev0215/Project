package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Rect;
import android.os.SystemClock;
import java.util.ArrayList;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tasks.tab_management.r1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p40 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ Object h;

    public /* synthetic */ p40(Object obj, int i, boolean z, boolean z2) {
        this.e = i;
        this.h = obj;
        this.f = z;
        this.g = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        boolean z = this.g;
        final int i2 = 1;
        boolean z2 = this.f;
        final int i3 = 0;
        Object obj = this.h;
        switch (i) {
            case 0:
                CustomTabToolbar.b bVar = (CustomTabToolbar.b) obj;
                bVar.r = false;
                if (z2) {
                    bVar.m(true);
                }
                if (!z) {
                    bVar.w(true);
                }
                bVar.c();
                break;
            default:
                final q1 q1Var = (q1) obj;
                q1Var.getClass();
                final yq2 yq2Var = new yq2(19, q1Var);
                boolean z3 = z2 && q1Var.Q() && !(q1Var.k.z().getCount() == 0);
                if (CachedFeatureFlags.b(ar.d.b("TabToGTSAnimation:skip-slow-zooming"), true)) {
                    z3 &= z;
                }
                if (oj2.C(q1Var.i)) {
                    z3 &= !qo.f().c();
                }
                if (z3 && yq2Var.get() != null) {
                    q1Var.h();
                    if (q1Var.o == null) {
                        m61 m61VarC = q1Var.c(q1Var.k.k(), q1Var.k.r());
                        m61VarC.v();
                        q1Var.o = new m61[]{m61VarC};
                    }
                    m61 m61Var = q1Var.o[0];
                    ew ewVarJ = q1Var.j();
                    ArrayList arrayList = new ArrayList(5);
                    ap apVar = new ap(2);
                    nw2 nw2Var = new nw2() { // from class: z43
                        @Override // defpackage.nw2
                        public final Object get() {
                            int i4 = i3;
                            nw2 nw2Var2 = yq2Var;
                            q1 q1Var2 = q1Var;
                            switch (i4) {
                                case 0:
                                    q1Var2.getClass();
                                    return Float.valueOf(((Rect) nw2Var2.get()).width() / (q1Var2.e * q1Var2.r));
                                default:
                                    q1Var2.getClass();
                                    return Float.valueOf(((Rect) nw2Var2.get()).top / q1Var2.r);
                            }
                        }
                    };
                    zq2 zq2Var = new zq2(4);
                    ij2 ij2Var = new ij2(q1Var, 2, yq2Var);
                    ap apVar2 = new ap(3);
                    nw2 nw2Var2 = new nw2() { // from class: z43
                        @Override // defpackage.nw2
                        public final Object get() {
                            int i4 = i2;
                            nw2 nw2Var22 = yq2Var;
                            q1 q1Var2 = q1Var;
                            switch (i4) {
                                case 0:
                                    q1Var2.getClass();
                                    return Float.valueOf(((Rect) nw2Var22.get()).width() / (q1Var2.e * q1Var2.r));
                                default:
                                    q1Var2.getClass();
                                    return Float.valueOf(((Rect) nw2Var22.get()).top / q1Var2.r);
                            }
                        }
                    };
                    PropertyModel.o oVar = m61.g;
                    jk0 jk0Var = h21.e;
                    arrayList.add(kw.k(ewVarJ, m61Var, oVar, apVar, nw2Var, 300L, jk0Var));
                    arrayList.add(kw.k(ewVarJ, m61Var, m61.h, zq2Var, ij2Var, 300L, jk0Var));
                    arrayList.add(kw.k(ewVarJ, m61Var, m61.i, apVar2, nw2Var2, 300L, jk0Var));
                    arrayList.add(kw.j(ewVarJ, m61Var, m61.w, m61Var.t(), oj2.G() ? Math.min(q1Var.e / TabUtils.c(q1Var.i), m61Var.t()) : q1Var.e, 300L, jk0Var));
                    kw kwVarE = kw.e(ewVarJ, 0.0f, 1.0f, 150L, new jt1(3, q1Var));
                    kwVarE.i = h21.d;
                    arrayList.add(kwVarE);
                    AnimatorSet animatorSet = new AnimatorSet();
                    q1Var.t = animatorSet;
                    animatorSet.playTogether(arrayList);
                    q1Var.t.addListener(new r1(q1Var));
                    q1Var.L = q1Var.H;
                    q1Var.I = SystemClock.elapsedRealtime();
                    q1Var.f81J = SystemClock.elapsedRealtime();
                    q1Var.K = 0L;
                    q1Var.t.start();
                } else {
                    q1Var.z.Z0(z2);
                }
                break;
        }
    }
}
