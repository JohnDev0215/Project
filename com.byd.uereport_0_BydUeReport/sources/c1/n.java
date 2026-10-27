package c1;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n extends RecyclerView.l {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2279g = true;

    public final void A(RecyclerView.c0 c0Var) {
        I(c0Var);
        h(c0Var);
    }

    public final void B(RecyclerView.c0 c0Var) {
        J(c0Var);
    }

    public final void C(RecyclerView.c0 c0Var, boolean z7) {
        K(c0Var, z7);
        h(c0Var);
    }

    public final void D(RecyclerView.c0 c0Var, boolean z7) {
        L(c0Var, z7);
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

    public void K(RecyclerView.c0 c0Var, boolean z7) {
    }

    public void L(RecyclerView.c0 c0Var, boolean z7) {
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
        int i8;
        int i9;
        return (cVar == null || ((i8 = cVar.f1618a) == (i9 = cVar2.f1618a) && cVar.f1619b == cVar2.f1619b)) ? w(c0Var) : y(c0Var, i8, cVar.f1619b, i9, cVar2.f1619b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i8;
        int i9;
        int i10 = cVar.f1618a;
        int i11 = cVar.f1619b;
        if (c0Var2.shouldIgnore()) {
            int i12 = cVar.f1618a;
            i9 = cVar.f1619b;
            i8 = i12;
        } else {
            i8 = cVar2.f1618a;
            i9 = cVar2.f1619b;
        }
        return x(c0Var, c0Var2, i10, i11, i8, i9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i8 = cVar.f1618a;
        int i9 = cVar.f1619b;
        View view = c0Var.itemView;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1618a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1619b;
        if (c0Var.isRemoved() || (i8 == left && i9 == top)) {
            return z(c0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(c0Var, i8, i9, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i8 = cVar.f1618a;
        int i9 = cVar2.f1618a;
        if (i8 != i9 || cVar.f1619b != cVar2.f1619b) {
            return y(c0Var, i8, cVar.f1619b, i9, cVar2.f1619b);
        }
        E(c0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.c0 c0Var) {
        return !this.f2279g || c0Var.isInvalid();
    }

    public abstract boolean w(RecyclerView.c0 c0Var);

    public abstract boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i8, int i9, int i10, int i11);

    public abstract boolean y(RecyclerView.c0 c0Var, int i8, int i9, int i10, int i11);

    public abstract boolean z(RecyclerView.c0 c0Var);
}
