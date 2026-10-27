package p;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class p12 {
    public final /* synthetic */ RecyclerView a;

    public /* synthetic */ p12(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final void a(a5 a5Var) {
        int i = a5Var.a;
        RecyclerView recyclerView = this.a;
        if (i == 1) {
            recyclerView.t.R(a5Var.b, a5Var.d);
            return;
        }
        if (i == 2) {
            recyclerView.t.U(a5Var.b, a5Var.d);
        } else if (i == 4) {
            recyclerView.t.V(a5Var.b, a5Var.d);
        } else {
            if (i != 8) {
                return;
            }
            recyclerView.t.T(a5Var.b, a5Var.d);
        }
    }

    public final n22 b(int i) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.m.h();
        int i2 = 0;
        n22 n22Var = null;
        while (true) {
            if (i2 >= iH) {
                break;
            }
            n22 n22VarI = RecyclerView.I(recyclerView.m.g(i2));
            if (n22VarI != null && !n22VarI.j() && n22VarI.c == i) {
                if (!recyclerView.m.j(n22VarI.a)) {
                    n22Var = n22VarI;
                    break;
                }
                n22Var = n22VarI;
            }
            i2++;
        }
        if (n22Var == null || recyclerView.m.j(n22Var.a)) {
            return null;
        }
        return n22Var;
    }

    public final void c(int i, int i2, Object obj) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.m.h();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < iH; i6++) {
            View viewG = recyclerView.m.g(i6);
            n22 n22VarI = RecyclerView.I(viewG);
            if (n22VarI != null && !n22VarI.q() && (i4 = n22VarI.c) >= i && i4 < i5) {
                n22VarI.b(2);
                n22VarI.a(obj);
                ((z12) viewG.getLayoutParams()).c = true;
            }
        }
        f22 f22Var = recyclerView.e;
        ArrayList arrayList = (ArrayList) f22Var.f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n22 n22Var = (n22) arrayList.get(size);
            if (n22Var != null && (i3 = n22Var.c) >= i && i3 < i5) {
                n22Var.b(2);
                f22Var.e(size);
            }
        }
        recyclerView.o0 = true;
    }

    public final void d(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.m.h();
        for (int i3 = 0; i3 < iH; i3++) {
            n22 n22VarI = RecyclerView.I(recyclerView.m.g(i3));
            if (n22VarI != null && !n22VarI.q() && n22VarI.c >= i) {
                n22VarI.n(i2, false);
                recyclerView.k0.f = true;
            }
        }
        ArrayList arrayList = (ArrayList) recyclerView.e.f;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            n22 n22Var = (n22) arrayList.get(i4);
            if (n22Var != null && n22Var.c >= i) {
                n22Var.n(i2, true);
            }
        }
        recyclerView.requestLayout();
        recyclerView.n0 = true;
    }

    public final void e(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.m.h();
        int i10 = -1;
        if (i < i2) {
            i4 = i;
            i3 = i2;
            i5 = -1;
        } else {
            i3 = i;
            i4 = i2;
            i5 = 1;
        }
        for (int i11 = 0; i11 < iH; i11++) {
            n22 n22VarI = RecyclerView.I(recyclerView.m.g(i11));
            if (n22VarI != null && (i9 = n22VarI.c) >= i4 && i9 <= i3) {
                if (i9 == i) {
                    n22VarI.n(i2 - i, false);
                } else {
                    n22VarI.n(i5, false);
                }
                recyclerView.k0.f = true;
            }
        }
        f22 f22Var = recyclerView.e;
        f22Var.getClass();
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        ArrayList arrayList = (ArrayList) f22Var.f;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            n22 n22Var = (n22) arrayList.get(i12);
            if (n22Var != null && (i8 = n22Var.c) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    n22Var.n(i2 - i, false);
                } else {
                    n22Var.n(i10, false);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.n0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(p.n22 r9, p.u12 r10, p.u12 r11) {
        /*
            r8 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r8.a
            r0.getClass()
            r1 = 0
            r9.p(r1)
            p.v12 r1 = r0.P
            r2 = r1
            p.m70 r2 = (p.m70) r2
            if (r10 == 0) goto L2b
            r2.getClass()
            int r4 = r10.a
            int r6 = r11.a
            if (r4 != r6) goto L1f
            int r1 = r10.b
            int r3 = r11.b
            if (r1 == r3) goto L2b
        L1f:
            int r5 = r10.b
            int r7 = r11.b
            r3 = r9
            boolean r9 = r2.g(r3, r4, r5, r6, r7)
            if (r9 == 0) goto L3c
            goto L39
        L2b:
            r2.l(r9)
            android.view.View r10 = r9.a
            r11 = 0
            r10.setAlpha(r11)
            java.util.ArrayList r10 = r2.i
            r10.add(r9)
        L39:
            r0.S()
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.p12.f(p.n22, p.u12, p.u12):void");
    }

    public final void g(n22 n22Var, u12 u12Var, u12 u12Var2) {
        RecyclerView recyclerView = this.a;
        recyclerView.e.j(n22Var);
        recyclerView.f(n22Var);
        n22Var.p(false);
        m70 m70Var = (m70) recyclerView.P;
        m70Var.getClass();
        int i = u12Var.a;
        int i2 = u12Var.b;
        View view = n22Var.a;
        int left = u12Var2 == null ? view.getLeft() : u12Var2.a;
        int top = u12Var2 == null ? view.getTop() : u12Var2.b;
        if (n22Var.j() || (i == left && i2 == top)) {
            m70Var.l(n22Var);
            m70Var.h.add(n22Var);
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            if (!m70Var.g(n22Var, i, i2, left, top)) {
                return;
            }
        }
        recyclerView.S();
    }

    public final void h(int i) {
        RecyclerView recyclerView = this.a;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            RecyclerView.I(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }
}
