package p;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class is {
    public final p12 a;
    public final hs b = new hs(0);
    public final ArrayList c = new ArrayList();

    public is(p12 p12Var) {
        this.a = p12Var;
    }

    public final void a(View view, int i, boolean z) {
        p12 p12Var = this.a;
        int childCount = i < 0 ? p12Var.a.getChildCount() : f(i);
        this.b.e(childCount, z);
        if (z) {
            i(view);
        }
        p12Var.a.addView(view, childCount);
        RecyclerView.I(view);
    }

    public final void b(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        p12 p12Var = this.a;
        int childCount = i < 0 ? p12Var.a.getChildCount() : f(i);
        this.b.e(childCount, z);
        if (z) {
            i(view);
        }
        p12Var.getClass();
        n22 n22VarI = RecyclerView.I(view);
        RecyclerView recyclerView = p12Var.a;
        if (n22VarI != null) {
            if (!n22VarI.l() && !n22VarI.q()) {
                StringBuilder sb = new StringBuilder("Called attach on a child which is not detached: ");
                sb.append(n22VarI);
                throw new IllegalArgumentException(u4.c(recyclerView, sb));
            }
            n22VarI.j &= -257;
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public final void c(int i) {
        n22 n22VarI;
        int iF = f(i);
        this.b.f(iF);
        RecyclerView recyclerView = this.a.a;
        View childAt = recyclerView.getChildAt(iF);
        if (childAt != null && (n22VarI = RecyclerView.I(childAt)) != null) {
            if (n22VarI.l() && !n22VarI.q()) {
                StringBuilder sb = new StringBuilder("called detach on an already detached child ");
                sb.append(n22VarI);
                throw new IllegalArgumentException(u4.c(recyclerView, sb));
            }
            n22VarI.b(256);
        }
        recyclerView.detachViewFromParent(iF);
    }

    public final View d(int i) {
        return this.a.a.getChildAt(f(i));
    }

    public final int e() {
        return this.a.a.getChildCount() - this.c.size();
    }

    public final int f(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.a.a.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            hs hsVar = this.b;
            int iB = i - (i2 - hsVar.b(i2));
            if (iB == 0) {
                while (hsVar.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public final View g(int i) {
        return this.a.a.getChildAt(i);
    }

    public final int h() {
        return this.a.a.getChildCount();
    }

    public final void i(View view) {
        this.c.add(view);
        p12 p12Var = this.a;
        p12Var.getClass();
        n22 n22VarI = RecyclerView.I(view);
        if (n22VarI != null) {
            int i = n22VarI.q;
            View view2 = n22VarI.a;
            if (i != -1) {
                n22VarI.f157p = i;
            } else {
                WeakHashMap weakHashMap = zt2.a;
                n22VarI.f157p = it2.c(view2);
            }
            RecyclerView recyclerView = p12Var.a;
            if (recyclerView.L()) {
                n22VarI.q = 4;
                recyclerView.x0.add(n22VarI);
            } else {
                WeakHashMap weakHashMap2 = zt2.a;
                it2.s(view2, 4);
            }
        }
    }

    public final boolean j(View view) {
        return this.c.contains(view);
    }

    public final void k(View view) {
        if (this.c.remove(view)) {
            p12 p12Var = this.a;
            p12Var.getClass();
            n22 n22VarI = RecyclerView.I(view);
            if (n22VarI != null) {
                int i = n22VarI.f157p;
                RecyclerView recyclerView = p12Var.a;
                if (recyclerView.L()) {
                    n22VarI.q = i;
                    recyclerView.x0.add(n22VarI);
                } else {
                    WeakHashMap weakHashMap = zt2.a;
                    it2.s(n22VarI.a, i);
                }
                n22VarI.f157p = 0;
            }
        }
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
