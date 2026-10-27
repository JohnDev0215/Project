package defpackage;

import android.app.Activity;
import org.chromium.base.b;

/* JADX INFO: compiled from: ActivityLifecycleDispatcherImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class r2 implements q2 {
    public final Activity k;
    public boolean m;
    public boolean n;
    public final b<sz0> a = new b<>();
    public final b<hk1> b = new b<>();
    public final b<hy1> c = new b<>();
    public final b<kr2> d = new b<>();
    public final b<v80> e = new b<>();
    public final b<vd2> f = new b<>();
    public final b<lt3> g = new b<>();
    public final b<y2> h = new b<>();
    public final b<bx> i = new b<>();
    public final b<w92> j = new b<>();
    public int l = 6;

    public r2(Activity activity) {
        this.k = activity;
    }

    @Override // defpackage.q2
    public final boolean d() {
        return this.n || this.k.isFinishing();
    }

    @Override // defpackage.q2
    public final void e(z61 z61Var) {
        if (z61Var instanceof sz0) {
            this.a.c((sz0) z61Var);
        }
        if (z61Var instanceof hy1) {
            this.c.c((hy1) z61Var);
        }
        if (z61Var instanceof kr2) {
            this.d.c((kr2) z61Var);
        }
        if (z61Var instanceof hk1) {
            this.b.c((hk1) z61Var);
        }
        if (z61Var instanceof v80) {
            this.e.c((v80) z61Var);
        }
        if (z61Var instanceof vd2) {
            this.f.c((vd2) z61Var);
        }
        if (z61Var instanceof lt3) {
            this.g.c((lt3) z61Var);
        }
        if (z61Var instanceof y2) {
            this.h.c((y2) z61Var);
        }
        if (z61Var instanceof bx) {
            this.i.c((bx) z61Var);
        }
        if (z61Var instanceof w92) {
            this.j.c((w92) z61Var);
        }
    }

    @Override // defpackage.q2
    public final void f(z61 z61Var) {
        if (z61Var instanceof sz0) {
            this.a.a((sz0) z61Var);
        }
        if (z61Var instanceof hy1) {
            this.c.a((hy1) z61Var);
        }
        if (z61Var instanceof kr2) {
            this.d.a((kr2) z61Var);
        }
        if (z61Var instanceof hk1) {
            this.b.a((hk1) z61Var);
        }
        if (z61Var instanceof v80) {
            this.e.a((v80) z61Var);
        }
        if (z61Var instanceof vd2) {
            this.f.a((vd2) z61Var);
        }
        if (z61Var instanceof lt3) {
            this.g.a((lt3) z61Var);
        }
        if (z61Var instanceof y2) {
            this.h.a((y2) z61Var);
        }
        if (z61Var instanceof bx) {
            this.i.a((bx) z61Var);
        }
        if (z61Var instanceof w92) {
            this.j.a((w92) z61Var);
        }
    }

    @Override // defpackage.q2
    public final int g() {
        return this.l;
    }

    @Override // defpackage.q2
    public final boolean h() {
        return this.m;
    }
}
