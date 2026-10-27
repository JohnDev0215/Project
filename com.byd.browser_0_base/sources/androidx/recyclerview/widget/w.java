package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.a;
import java.util.ArrayList;

/* JADX INFO: compiled from: RecyclerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class w implements a.InterfaceC0024a {
    public final /* synthetic */ RecyclerView a;

    public w(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final void a(a.b bVar) {
        int i = bVar.a;
        RecyclerView recyclerView = this.a;
        if (i == 1) {
            recyclerView.r.c0(bVar.b, bVar.d);
            return;
        }
        if (i == 2) {
            recyclerView.r.f0(bVar.b, bVar.d);
        } else if (i == 4) {
            recyclerView.r.g0(bVar.b, bVar.d);
        } else {
            if (i != 8) {
                return;
            }
            recyclerView.r.e0(bVar.b, bVar.d);
        }
    }

    public final RecyclerView.a0 b(int i) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.j.h();
        int i2 = 0;
        RecyclerView.a0 a0Var = null;
        while (true) {
            if (i2 >= iH) {
                break;
            }
            RecyclerView.a0 a0VarK = RecyclerView.K(recyclerView.j.g(i2));
            if (a0VarK != null && !a0VarK.m() && a0VarK.g == i) {
                if (!recyclerView.j.j(a0VarK.e)) {
                    a0Var = a0VarK;
                    break;
                }
                a0Var = a0VarK;
            }
            i2++;
        }
        if (a0Var == null) {
            return null;
        }
        if (!recyclerView.j.j(a0Var.e)) {
            return a0Var;
        }
        boolean z = RecyclerView.F0;
        return null;
    }

    public final void c(int i, int i2, Object obj) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.j.h();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < iH; i6++) {
            View viewG = recyclerView.j.g(i6);
            RecyclerView.a0 a0VarK = RecyclerView.K(viewG);
            if (a0VarK != null && !a0VarK.s() && (i4 = a0VarK.g) >= i && i4 < i5) {
                a0VarK.d(2);
                a0VarK.c(obj);
                ((RecyclerView.LayoutParams) viewG.getLayoutParams()).c = true;
            }
        }
        RecyclerView.s sVar = recyclerView.g;
        ArrayList<RecyclerView.a0> arrayList = sVar.c;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                recyclerView.q0 = true;
                return;
            }
            RecyclerView.a0 a0Var = arrayList.get(size);
            if (a0Var != null && (i3 = a0Var.g) >= i && i3 < i5) {
                a0Var.d(2);
                sVar.g(size);
            }
        }
    }

    public final void d(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.j.h();
        for (int i3 = 0; i3 < iH; i3++) {
            RecyclerView.a0 a0VarK = RecyclerView.K(recyclerView.j.g(i3));
            if (a0VarK != null && !a0VarK.s() && a0VarK.g >= i) {
                if (RecyclerView.G0) {
                    a0VarK.toString();
                }
                a0VarK.p(i2, false);
                recyclerView.m0.f = true;
            }
        }
        ArrayList<RecyclerView.a0> arrayList = recyclerView.g.c;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView.a0 a0Var = arrayList.get(i4);
            if (a0Var != null && a0Var.g >= i) {
                if (RecyclerView.G0) {
                    a0Var.toString();
                }
                a0Var.p(i2, false);
            }
        }
        recyclerView.requestLayout();
        recyclerView.p0 = true;
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
        int iH = recyclerView.j.h();
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
            RecyclerView.a0 a0VarK = RecyclerView.K(recyclerView.j.g(i11));
            if (a0VarK != null && (i9 = a0VarK.g) >= i4 && i9 <= i3) {
                if (RecyclerView.G0) {
                    a0VarK.toString();
                }
                if (a0VarK.g == i) {
                    a0VarK.p(i2 - i, false);
                } else {
                    a0VarK.p(i5, false);
                }
                recyclerView.m0.f = true;
            }
        }
        RecyclerView.s sVar = recyclerView.g;
        sVar.getClass();
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        ArrayList<RecyclerView.a0> arrayList = sVar.c;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            RecyclerView.a0 a0Var = arrayList.get(i12);
            if (a0Var != null && (i8 = a0Var.g) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    a0Var.p(i2 - i, false);
                } else {
                    a0Var.p(i10, false);
                }
                if (RecyclerView.G0) {
                    a0Var.toString();
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.p0 = true;
    }
}
