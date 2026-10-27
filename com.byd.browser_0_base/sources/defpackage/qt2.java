package defpackage;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import defpackage.ft2;
import defpackage.kj;
import defpackage.l23;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qt2 implements kj.a, View.OnLayoutChangeListener, l23.a {
    public PropertyModel e;
    public final kj f;
    public final HashSet<ft2.a> g = new HashSet<>();
    public final l23 h;
    public final nw2<Integer> i;
    public zx1 j;
    public Runnable k;
    public Runnable l;
    public final nw2<Boolean> m;
    public Callback<Runnable> n;
    public Runnable o;
    public ValueAnimator p;
    public ValueAnimator q;
    public AnimatorSet r;
    public AnimatorSet s;
    public int t;
    public int u;
    public boolean v;

    public qt2(fj fjVar, l23 l23Var, yq2 yq2Var, nw2 nw2Var) {
        this.f = fjVar;
        this.h = l23Var;
        this.i = yq2Var;
        this.m = nw2Var;
    }

    public final void a(int i) {
        Iterator<ft2.a> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public final void b(boolean z) {
        this.v = z;
        this.t = z ? 0 : this.u;
        if (!z) {
            this.f.o(this);
        }
        int i = this.t;
        Iterator<ft2.a> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().e(i);
        }
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        boolean z2 = i2 > 0;
        PropertyModel propertyModel = this.e;
        PropertyModel.n nVar = rt2.d;
        nw2<Boolean> nw2Var = this.m;
        propertyModel.l(nVar, z2 && nw2Var.get().booleanValue());
        this.e.n(rt2.i, i2);
        boolean z3 = z2 && i2 == this.t;
        this.e.n(rt2.c, (!this.v || (!nw2Var.get().booleanValue() && z2)) ? (z3 || !nw2Var.get().booleanValue()) ? 0 : 4 : 8);
        zx1 zx1Var = this.j;
        if (zx1Var != null && z3) {
            zx1Var.run();
            this.j = null;
        }
        if (!z2 && this.v) {
            this.f.s(this);
            this.v = false;
            this.u = 0;
            this.l.run();
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.v || this.u != 0 || view.getHeight() <= 0) {
            return;
        }
        this.n.onResult(null);
        this.u = view.getHeight();
        b(false);
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        this.e.l(rt2.j, z2);
    }
}
