package w0;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n extends RecyclerView.l {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5661g = true;

    public final void A(RecyclerView.c0 c0Var) {
        I(c0Var);
        h(c0Var);
    }

    public final void B(RecyclerView.c0 c0Var) {
        J(c0Var);
    }

    public final void C(RecyclerView.c0 c0Var, boolean z3) {
        K(c0Var, z3);
        h(c0Var);
    }

    public final void D(RecyclerView.c0 c0Var, boolean z3) {
        L(c0Var, z3);
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

    public void K(RecyclerView.c0 c0Var, boolean z3) {
    }

    public void L(RecyclerView.c0 c0Var, boolean z3) {
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
        int i3;
        int i4;
        return (cVar == null || ((i3 = cVar.f1503a) == (i4 = cVar2.f1503a) && cVar.f1504b == cVar2.f1504b)) ? w(c0Var) : y(c0Var, i3, cVar.f1504b, i4, cVar2.f1504b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i3;
        int i4;
        int i5 = cVar.f1503a;
        int i6 = cVar.f1504b;
        if (c0Var2.J()) {
            int i7 = cVar.f1503a;
            i4 = cVar.f1504b;
            i3 = i7;
        } else {
            i3 = cVar2.f1503a;
            i4 = cVar2.f1504b;
        }
        return x(c0Var, c0Var2, i5, i6, i3, i4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i3 = cVar.f1503a;
        int i4 = cVar.f1504b;
        View view = c0Var.f1474b;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1503a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1504b;
        if (c0Var.v() || (i3 == left && i4 == top)) {
            return z(c0Var);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(c0Var, i3, i4, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i3 = cVar.f1503a;
        int i4 = cVar2.f1503a;
        if (i3 != i4 || cVar.f1504b != cVar2.f1504b) {
            return y(c0Var, i3, cVar.f1504b, i4, cVar2.f1504b);
        }
        E(c0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean f(RecyclerView.c0 c0Var) {
        return !this.f5661g || c0Var.t();
    }

    public abstract boolean w(RecyclerView.c0 c0Var);

    public abstract boolean x(RecyclerView.c0 c0Var, RecyclerView.c0 c0Var2, int i3, int i4, int i5, int i6);

    public abstract boolean y(RecyclerView.c0 c0Var, int i3, int i4, int i5, int i6);

    public abstract boolean z(RecyclerView.c0 c0Var);
}
