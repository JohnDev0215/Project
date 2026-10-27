package androidx.recyclerview.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class n extends RecyclerView.ItemAnimator {
    boolean g = true;

    public final void A(RecyclerView.A a2) {
        I(a2);
        h(a2);
    }

    public final void B(RecyclerView.A a2) {
        J(a2);
    }

    public final void C(RecyclerView.A a2, boolean z) {
        K(a2, z);
        h(a2);
    }

    public final void D(RecyclerView.A a2, boolean z) {
        L(a2, z);
    }

    public final void E(RecyclerView.A a2) {
        M(a2);
        h(a2);
    }

    public final void F(RecyclerView.A a2) {
        N(a2);
    }

    public final void G(RecyclerView.A a2) {
        O(a2);
        h(a2);
    }

    public final void H(RecyclerView.A a2) {
        P(a2);
    }

    public void I(RecyclerView.A a2) {
    }

    public void J(RecyclerView.A a2) {
    }

    public void K(RecyclerView.A a2, boolean z) {
    }

    public void L(RecyclerView.A a2, boolean z) {
    }

    public void M(RecyclerView.A a2) {
    }

    public void N(RecyclerView.A a2) {
    }

    public void O(RecyclerView.A a2) {
    }

    public void P(RecyclerView.A a2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean a(@NonNull RecyclerView.A a2, @Nullable RecyclerView.ItemAnimator.c cVar, @NonNull RecyclerView.ItemAnimator.c cVar2) {
        return (cVar == null || (cVar.f1101a == cVar2.f1101a && cVar.f1102b == cVar2.f1102b)) ? w(a2) : y(a2, cVar.f1101a, cVar.f1102b, cVar2.f1101a, cVar2.f1102b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean b(@NonNull RecyclerView.A a2, @NonNull RecyclerView.A a3, @NonNull RecyclerView.ItemAnimator.c cVar, @NonNull RecyclerView.ItemAnimator.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.f1101a;
        int i4 = cVar.f1102b;
        if (a3.J()) {
            int i5 = cVar.f1101a;
            i2 = cVar.f1102b;
            i = i5;
        } else {
            i = cVar2.f1101a;
            i2 = cVar2.f1102b;
        }
        return x(a2, a3, i3, i4, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean c(@NonNull RecyclerView.A a2, @NonNull RecyclerView.ItemAnimator.c cVar, @Nullable RecyclerView.ItemAnimator.c cVar2) {
        int i = cVar.f1101a;
        int i2 = cVar.f1102b;
        View view = a2.f1092a;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1101a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1102b;
        if (a2.v() || (i == left && i2 == top)) {
            return z(a2);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return y(a2, i, i2, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean d(@NonNull RecyclerView.A a2, @NonNull RecyclerView.ItemAnimator.c cVar, @NonNull RecyclerView.ItemAnimator.c cVar2) {
        if (cVar.f1101a != cVar2.f1101a || cVar.f1102b != cVar2.f1102b) {
            return y(a2, cVar.f1101a, cVar.f1102b, cVar2.f1101a, cVar2.f1102b);
        }
        E(a2);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean f(@NonNull RecyclerView.A a2) {
        return !this.g || a2.t();
    }

    public abstract boolean w(RecyclerView.A a2);

    public abstract boolean x(RecyclerView.A a2, RecyclerView.A a3, int i, int i2, int i3, int i4);

    public abstract boolean y(RecyclerView.A a2, int i, int i2, int i3, int i4);

    public abstract boolean z(RecyclerView.A a2);
}
