package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n extends RecyclerView.l {
    boolean g = true;

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
        int i;
        int i2;
        return (cVar == null || ((i = cVar.f1651a) == (i2 = cVar2.f1651a) && cVar.f1652b == cVar2.f1652b)) ? w(c0Var) : y(c0Var, i, cVar.f1652b, i2, cVar2.f1652b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.f1651a;
        int i4 = cVar.f1652b;
        if (c0Var2.J()) {
            int i5 = cVar.f1651a;
            i2 = cVar.f1652b;
            i = i5;
        } else {
            i = cVar2.f1651a;
            i2 = cVar2.f1652b;
        }
        return x(c0Var, c0Var2, i3, i4, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i = cVar.f1651a;
        int i2 = cVar.f1652b;
        View view = c0Var.f1637b;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1651a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1652b;
        if (c0Var.v() || (i == left && i2 == top)) {
            return z(c0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(c0Var, i, i2, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i = cVar.f1651a;
        int i2 = cVar2.f1651a;
        if (i != i2 || cVar.f1652b != cVar2.f1652b) {
            return y(c0Var, i, cVar.f1652b, i2, cVar2.f1652b);
        }
        E(c0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.c0 c0Var) {
        return !this.g || c0Var.t();
    }

    public abstract boolean w(RecyclerView.c0 c0Var);

    public abstract boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i, int i2, int i3, int i4);

    public abstract boolean y(RecyclerView.c0 c0Var, int i, int i2, int i3, int i4);

    public abstract boolean z(RecyclerView.c0 c0Var);
}
