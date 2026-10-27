package a.p.e;

import a.p.e.a;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class a0 implements a.InterfaceC0025a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f1139a;

    public a0(RecyclerView recyclerView) {
        this.f1139a = recyclerView;
    }

    public void a(a.b bVar) {
        int i = bVar.f1135a;
        if (i == 1) {
            RecyclerView recyclerView = this.f1139a;
            recyclerView.m.o0(recyclerView, bVar.f1136b, bVar.f1138d);
            return;
        }
        if (i == 2) {
            RecyclerView recyclerView2 = this.f1139a;
            recyclerView2.m.r0(recyclerView2, bVar.f1136b, bVar.f1138d);
        } else if (i == 4) {
            RecyclerView recyclerView3 = this.f1139a;
            recyclerView3.m.t0(recyclerView3, bVar.f1136b, bVar.f1138d, bVar.f1137c);
        } else {
            if (i != 8) {
                return;
            }
            RecyclerView recyclerView4 = this.f1139a;
            recyclerView4.m.q0(recyclerView4, bVar.f1136b, bVar.f1138d, 1);
        }
    }

    public RecyclerView.b0 b(int i) {
        RecyclerView recyclerView = this.f1139a;
        int iH = recyclerView.f1619e.h();
        int i2 = 0;
        RecyclerView.b0 b0Var = null;
        while (true) {
            if (i2 >= iH) {
                break;
            }
            RecyclerView.b0 b0VarK = RecyclerView.K(recyclerView.f1619e.g(i2));
            if (b0VarK != null && !b0VarK.m() && b0VarK.f1631c == i) {
                if (!recyclerView.f1619e.k(b0VarK.f1629a)) {
                    b0Var = b0VarK;
                    break;
                }
                b0Var = b0VarK;
            }
            i2++;
        }
        if (b0Var == null || this.f1139a.f1619e.k(b0Var.f1629a)) {
            return null;
        }
        return b0Var;
    }

    public void c(int i, int i2, Object obj) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.f1139a;
        int iH = recyclerView.f1619e.h();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < iH; i6++) {
            View viewG = recyclerView.f1619e.g(i6);
            RecyclerView.b0 b0VarK = RecyclerView.K(viewG);
            if (b0VarK != null && !b0VarK.u() && (i4 = b0VarK.f1631c) >= i && i4 < i5) {
                b0VarK.b(2);
                b0VarK.a(obj);
                ((RecyclerView.n) viewG.getLayoutParams()).f1661c = true;
            }
        }
        RecyclerView.t tVar = recyclerView.f1616b;
        int size = tVar.f1671c.size();
        while (true) {
            size--;
            if (size < 0) {
                this.f1139a.i0 = true;
                return;
            }
            RecyclerView.b0 b0Var = tVar.f1671c.get(size);
            if (b0Var != null && (i3 = b0Var.f1631c) >= i && i3 < i5) {
                b0Var.b(2);
                tVar.f(size);
            }
        }
    }

    public void d(int i, int i2) {
        RecyclerView recyclerView = this.f1139a;
        int iH = recyclerView.f1619e.h();
        for (int i3 = 0; i3 < iH; i3++) {
            RecyclerView.b0 b0VarK = RecyclerView.K(recyclerView.f1619e.g(i3));
            if (b0VarK != null && !b0VarK.u() && b0VarK.f1631c >= i) {
                b0VarK.q(i2, false);
                recyclerView.e0.f1694f = true;
            }
        }
        RecyclerView.t tVar = recyclerView.f1616b;
        int size = tVar.f1671c.size();
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView.b0 b0Var = tVar.f1671c.get(i4);
            if (b0Var != null && b0Var.f1631c >= i) {
                b0Var.q(i2, true);
            }
        }
        recyclerView.requestLayout();
        this.f1139a.h0 = true;
    }

    public void e(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        RecyclerView recyclerView = this.f1139a;
        int iH = recyclerView.f1619e.h();
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
            RecyclerView.b0 b0VarK = RecyclerView.K(recyclerView.f1619e.g(i11));
            if (b0VarK != null && (i9 = b0VarK.f1631c) >= i4 && i9 <= i3) {
                if (i9 == i) {
                    b0VarK.q(i2 - i, false);
                } else {
                    b0VarK.q(i5, false);
                }
                recyclerView.e0.f1694f = true;
            }
        }
        RecyclerView.t tVar = recyclerView.f1616b;
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        int size = tVar.f1671c.size();
        for (int i12 = 0; i12 < size; i12++) {
            RecyclerView.b0 b0Var = tVar.f1671c.get(i12);
            if (b0Var != null && (i8 = b0Var.f1631c) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    b0Var.q(i2 - i, false);
                } else {
                    b0Var.q(i10, false);
                }
            }
        }
        recyclerView.requestLayout();
        this.f1139a.h0 = true;
    }
}
