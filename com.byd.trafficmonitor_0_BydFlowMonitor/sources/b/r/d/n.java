package b.r.d;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n extends RecyclerView.l {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3124g = true;

    public final void A(RecyclerView.c0 c0Var) {
        I(c0Var);
        h(c0Var);
    }

    public final void B(RecyclerView.c0 c0Var) {
        J(c0Var);
    }

    public final void C(RecyclerView.c0 c0Var, boolean z) {
        K(c0Var, z);
        h(c0Var);
    }

    public final void D(RecyclerView.c0 c0Var, boolean z) {
        L(c0Var, z);
    }

    public final void E(RecyclerView.c0 c0Var) {
        M(c0Var);
        h(c0Var);
    }

    public final void F(RecyclerView.c0 c0Var) {
        N(c0Var);
    }

    public final void G(RecyclerView.c0 c0Var) {
        O(c0Var);
        h(c0Var);
    }

    public final void H(RecyclerView.c0 c0Var) {
        P(c0Var);
    }

    public void I(RecyclerView.c0 c0Var) {
    }

    public void J(RecyclerView.c0 c0Var) {
    }

    public void K(RecyclerView.c0 c0Var, boolean z) {
    }

    public void L(RecyclerView.c0 c0Var, boolean z) {
    }

    public void M(RecyclerView.c0 c0Var) {
    }

    public void N(RecyclerView.c0 c0Var) {
    }

    public void O(RecyclerView.c0 c0Var) {
    }

    public void P(RecyclerView.c0 c0Var) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i2;
        int i3;
        return (cVar == null || ((i2 = cVar.f1204a) == (i3 = cVar2.f1204a) && cVar.f1205b == cVar2.f1205b)) ? w(c0Var) : y(c0Var, i2, cVar.f1205b, i3, cVar2.f1205b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i2;
        int i3;
        int i4 = cVar.f1204a;
        int i5 = cVar.f1205b;
        if (c0Var2.J()) {
            int i6 = cVar.f1204a;
            i3 = cVar.f1205b;
            i2 = i6;
        } else {
            i2 = cVar2.f1204a;
            i3 = cVar2.f1205b;
        }
        return x(c0Var, c0Var2, i4, i5, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i2 = cVar.f1204a;
        int i3 = cVar.f1205b;
        View view = c0Var.f1185b;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1204a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1205b;
        if (c0Var.v() || (i2 == left && i3 == top)) {
            return z(c0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(c0Var, i2, i3, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i2 = cVar.f1204a;
        int i3 = cVar2.f1204a;
        if (i2 != i3 || cVar.f1205b != cVar2.f1205b) {
            return y(c0Var, i2, cVar.f1205b, i3, cVar2.f1205b);
        }
        E(c0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.c0 c0Var) {
        return !this.f3124g || c0Var.t();
    }

    public abstract boolean w(RecyclerView.c0 c0Var);

    public abstract boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i2, int i3, int i4, int i5);

    public abstract boolean y(RecyclerView.c0 c0Var, int i2, int i3, int i4, int i5);

    public abstract boolean z(RecyclerView.c0 c0Var);
}
