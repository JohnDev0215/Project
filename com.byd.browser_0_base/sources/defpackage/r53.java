package defpackage;

import android.app.Activity;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabbedModeTabDelegateFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class r53 implements jz2 {
    public final Activity a;
    public final lj b;
    public final nw2<ck2> c;
    public final nw2<EphemeralTabCoordinator> d;
    public final Runnable e;
    public final ji f;
    public final jp g;
    public final kj h;
    public final rq0 i;
    public final gz2 j;
    public final nw2<y13> k;
    public final nw2<CompositorViewHolder> l;
    public final nw2<bi1> m;
    public final nw2<wp2> n;
    public final fj o;
    public final nw2<Tab> p;
    public final q2 q;
    public final WindowAndroid r;
    public final nw2<Long> s;
    public final BooleanSupplier t;
    public final i31 u;
    public final nw2<qb3> v;
    public lk1 w;

    public r53(Activity activity, bw bwVar, gk2 gk2Var, nw2 nw2Var, ds dsVar, f fVar, jp jpVar, fj fjVar, qq0 qq0Var, gz2 gz2Var, g23 g23Var, aq1 aq1Var, aq1 aq1Var2, ks ksVar, fj fjVar2, z2 z2Var, r2 r2Var, g3 g3Var, ls lsVar, vr vrVar, j31 j31Var, es esVar) {
        this.a = activity;
        this.b = bwVar;
        this.c = gk2Var;
        this.d = nw2Var;
        this.e = dsVar;
        this.f = fVar;
        this.g = jpVar;
        this.h = fjVar;
        this.i = qq0Var;
        this.j = gz2Var;
        this.k = g23Var;
        this.l = aq1Var;
        this.m = aq1Var2;
        this.n = ksVar;
        this.o = fjVar2;
        this.p = z2Var;
        this.q = r2Var;
        this.r = g3Var;
        this.s = lsVar;
        this.t = vrVar;
        this.u = j31Var;
        this.v = esVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.jz2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.jk1 a(java.lang.String r28, defpackage.jk1 r29, org.chromium.chrome.browser.tab.Tab r30) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r53.a(java.lang.String, jk1, org.chromium.chrome.browser.tab.Tab):jk1");
    }

    @Override // defpackage.jz2
    public final ContextMenuPopulatorFactory b(Tab tab) {
        return new f31(new az2(tab, this.k.get(), this.d, this.e, this.n), this.c, 0, zi0.b);
    }

    @Override // defpackage.jz2
    public final lj c(TabImpl tabImpl) {
        return new bw(new r33(tabImpl), this.b);
    }

    @Override // defpackage.jz2
    public final hj0 d(TabImpl tabImpl) {
        return new hj0(new cj0(tabImpl));
    }

    @Override // defpackage.jz2
    public final k53 e(Tab tab) {
        return new f3(tab, this.a, this.g, false, this.h, this.i, this.j, this.k, this.l, this.m);
    }
}
