package defpackage;

import defpackage.kj;
import defpackage.l23;
import defpackage.l61;
import defpackage.t41;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BottomControlsMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bi implements kj.a, t41.b, l61.a, l23.a {
    public final PropertyModel e;
    public final rq0 f;
    public final jj g;
    public final l23 h;
    public final ll i;
    public final int j;
    public final WindowAndroid k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public l61 p;

    public bi(WindowAndroid windowAndroid, PropertyModel propertyModel, jj jjVar, rq0 rq0Var, l23 l23Var, int i, aq1 aq1Var) {
        this.e = propertyModel;
        this.f = rq0Var;
        this.g = jjVar;
        jjVar.o(this);
        this.h = l23Var;
        l23Var.b(this);
        this.j = i;
        ll llVar = new ll();
        this.i = llVar;
        aq1Var.p(llVar.b(new jb1(14, this)));
        this.k = windowAndroid;
        windowAndroid.m().a(this);
    }

    @Override // t41.b
    public final void a(boolean z) {
        this.o = z;
        boolean zB = b();
        this.e.l(ci.d, zB);
        int i = zB ? this.j : 0;
        jj jjVar = this.g;
        jjVar.k(i, jjVar.b());
        d();
    }

    public final boolean b() {
        if (!this.l || this.o) {
            return false;
        }
        rq0 rq0Var = this.f;
        return !(rq0Var != null && rq0Var.g());
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        this.e.n(ci.b, i3);
        d();
    }

    public final void d() {
        this.e.l(ci.c, b() && !this.m && !this.n && this.g.u() == 0);
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        this.n = i == 4;
        d();
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        this.e.l(ci.e, z2);
    }
}
