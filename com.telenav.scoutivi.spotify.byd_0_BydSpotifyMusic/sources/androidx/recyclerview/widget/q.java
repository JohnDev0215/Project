package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q extends RecyclerView.l {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f3174g = true;

    public final void A(RecyclerView.c0 c0Var) {
        I(c0Var);
        h(c0Var);
    }

    public final void B(RecyclerView.c0 c0Var) {
        J(c0Var);
    }

    public final void C(RecyclerView.c0 c0Var, boolean z6) {
        K(c0Var, z6);
        h(c0Var);
    }

    public final void D(RecyclerView.c0 c0Var, boolean z6) {
        L(c0Var, z6);
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

    public void K(RecyclerView.c0 c0Var, boolean z6) {
    }

    public void L(RecyclerView.c0 c0Var, boolean z6) {
    }

    public void M(RecyclerView.c0 c0Var) {
    }

    public void N(RecyclerView.c0 c0Var) {
    }

    public void O(RecyclerView.c0 c0Var) {
    }

    public void P(RecyclerView.c0 c0Var) {
    }

    public void Q(boolean z6) {
        this.f3174g = z6;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i7;
        int i8;
        return (cVar == null || ((i7 = cVar.f2886a) == (i8 = cVar2.f2886a) && cVar.f2887b == cVar2.f2887b)) ? w(c0Var) : y(c0Var, i7, cVar.f2887b, i8, cVar2.f2887b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i7;
        int i8;
        int i9 = cVar.f2886a;
        int i10 = cVar.f2887b;
        if (c0Var2.J()) {
            int i11 = cVar.f2886a;
            i8 = cVar.f2887b;
            i7 = i11;
        } else {
            i7 = cVar2.f2886a;
            i8 = cVar2.f2887b;
        }
        return x(c0Var, c0Var2, i9, i10, i7, i8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i7 = cVar.f2886a;
        int i8 = cVar.f2887b;
        View view = c0Var.f2857a;
        int left = cVar2 == null ? view.getLeft() : cVar2.f2886a;
        int top = cVar2 == null ? view.getTop() : cVar2.f2887b;
        if (c0Var.v() || (i7 == left && i8 == top)) {
            return z(c0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(c0Var, i7, i8, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i7 = cVar.f2886a;
        int i8 = cVar2.f2886a;
        if (i7 != i8 || cVar.f2887b != cVar2.f2887b) {
            return y(c0Var, i7, cVar.f2887b, i8, cVar2.f2887b);
        }
        E(c0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.c0 c0Var) {
        return !this.f3174g || c0Var.t();
    }

    public abstract boolean w(RecyclerView.c0 c0Var);

    public abstract boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i7, int i8, int i9, int i10);

    public abstract boolean y(RecyclerView.c0 c0Var, int i7, int i8, int i9, int i10);

    public abstract boolean z(RecyclerView.c0 c0Var);
}
