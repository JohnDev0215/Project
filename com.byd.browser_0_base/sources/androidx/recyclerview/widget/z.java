package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.c;

/* JADX INFO: compiled from: SimpleItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z extends RecyclerView.j {
    public boolean g = true;

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean a(RecyclerView.a0 a0Var, RecyclerView.j.c cVar, RecyclerView.j.c cVar2) {
        int i;
        int i2;
        if (cVar != null && ((i = cVar.a) != (i2 = cVar2.a) || cVar.b != cVar2.b)) {
            return m(a0Var, i, cVar.b, i2, cVar2.b);
        }
        c cVar3 = (c) this;
        cVar3.r(a0Var);
        a0Var.e.setAlpha(0.0f);
        cVar3.i.add(a0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean b(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2, RecyclerView.j.c cVar, RecyclerView.j.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.a;
        int i4 = cVar.b;
        if (a0Var2.s()) {
            int i5 = cVar.a;
            i2 = cVar.b;
            i = i5;
        } else {
            i = cVar2.a;
            i2 = cVar2.b;
        }
        c cVar3 = (c) this;
        if (a0Var == a0Var2) {
            return cVar3.m(a0Var, i3, i4, i, i2);
        }
        View view = a0Var.e;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float alpha = view.getAlpha();
        cVar3.r(a0Var);
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        view.setAlpha(alpha);
        cVar3.r(a0Var2);
        float f = -((int) ((i - i3) - translationX));
        View view2 = a0Var2.e;
        view2.setTranslationX(f);
        view2.setTranslationY(-((int) ((i2 - i4) - translationY)));
        view2.setAlpha(0.0f);
        cVar3.k.add(new c.d(a0Var, a0Var2, i3, i4, i, i2));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean c(RecyclerView.a0 a0Var, RecyclerView.j.c cVar, RecyclerView.j.c cVar2) {
        int i = cVar.a;
        int i2 = cVar.b;
        View view = a0Var.e;
        int left = cVar2 == null ? view.getLeft() : cVar2.a;
        int top = cVar2 == null ? view.getTop() : cVar2.b;
        if (!a0Var.m() && (i != left || i2 != top)) {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            return m(a0Var, i, i2, left, top);
        }
        c cVar3 = (c) this;
        cVar3.r(a0Var);
        cVar3.h.add(a0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean d(RecyclerView.a0 a0Var, RecyclerView.j.c cVar, RecyclerView.j.c cVar2) {
        int i = cVar.a;
        int i2 = cVar2.a;
        if (i != i2 || cVar.b != cVar2.b) {
            return m(a0Var, i, cVar.b, i2, cVar2.b);
        }
        h(a0Var);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.j
    public final boolean f(RecyclerView.a0 a0Var) {
        return !this.g || a0Var.k();
    }

    @SuppressLint({"UnknownNullness"})
    public abstract boolean m(RecyclerView.a0 a0Var, int i, int i2, int i3, int i4);
}
