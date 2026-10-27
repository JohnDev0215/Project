package defpackage;

import java.util.HashSet;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: UndoRefocusHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class qi3 implements v80 {
    public final y13 f;
    public final zp1<h61> g;
    public h61 h;
    public pi3 i;
    public oi3 j;
    public Integer k;
    public so m;
    public final boolean n;
    public final HashSet e = new HashSet();
    public boolean l = false;

    public qi3(a23 a23Var, aq1 aq1Var, boolean z) {
        this.g = aq1Var;
        this.f = a23Var;
        this.n = z;
        this.j = new oi3(this, a23Var);
        so soVar = new so(1, this);
        this.m = soVar;
        aq1Var.p(soVar);
    }

    public static void a(qi3 qi3Var) {
        Integer num;
        TabModel tabModelZ = qi3Var.f.z();
        if (tabModelZ == null || (num = qi3Var.k) == null) {
            return;
        }
        tabModelZ.q(z7.G(tabModelZ, num.intValue()), 5, false);
        qi3Var.k = null;
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.j.a();
        this.g.d(this.m);
        this.h.f(this.i);
    }
}
