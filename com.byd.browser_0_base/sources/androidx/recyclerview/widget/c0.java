package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import defpackage.sa1;
import defpackage.t22;
import defpackage.ta1;
import defpackage.wm2;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public final wm2<RecyclerView.a0, a> a = new wm2<>();
    public final sa1<RecyclerView.a0> b = new sa1<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {
        public static final t22 d = new t22(20, 1);
        public int a;
        public RecyclerView.j.c b;
        public RecyclerView.j.c c;

        public static a a() {
            a aVar = (a) d.a();
            return aVar == null ? new a() : aVar;
        }
    }

    public final void a(RecyclerView.a0 a0Var, RecyclerView.j.c cVar) {
        wm2<RecyclerView.a0, a> wm2Var = this.a;
        a aVarA = wm2Var.get(a0Var);
        if (aVarA == null) {
            aVarA = a.a();
            wm2Var.put(a0Var, aVarA);
        }
        aVarA.c = cVar;
        aVarA.a |= 8;
    }

    public final RecyclerView.j.c b(RecyclerView.a0 a0Var, int i) {
        a aVarI;
        RecyclerView.j.c cVar;
        wm2<RecyclerView.a0, a> wm2Var = this.a;
        int iD = wm2Var.d(a0Var);
        if (iD >= 0 && (aVarI = wm2Var.i(iD)) != null) {
            int i2 = aVarI.a;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                aVarI.a = i3;
                if (i == 4) {
                    cVar = aVarI.b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarI.c;
                }
                if ((i3 & 12) == 0) {
                    wm2Var.g(iD);
                    aVarI.a = 0;
                    aVarI.b = null;
                    aVarI.c = null;
                    a.d.b(aVarI);
                }
                return cVar;
            }
        }
        return null;
    }

    public final void c(RecyclerView.a0 a0Var) {
        a aVar = this.a.get(a0Var);
        if (aVar == null) {
            return;
        }
        aVar.a &= -2;
    }

    public final void d(RecyclerView.a0 a0Var) {
        sa1<RecyclerView.a0> sa1Var = this.b;
        int iM = sa1Var.m() - 1;
        while (true) {
            if (iM < 0) {
                break;
            }
            if (a0Var == sa1Var.n(iM)) {
                Object[] objArr = sa1Var.g;
                Object obj = objArr[iM];
                Object obj2 = ta1.a;
                if (obj != obj2) {
                    objArr[iM] = obj2;
                    sa1Var.e = true;
                }
            } else {
                iM--;
            }
        }
        a aVarRemove = this.a.remove(a0Var);
        if (aVarRemove != null) {
            aVarRemove.a = 0;
            aVarRemove.b = null;
            aVarRemove.c = null;
            a.d.b(aVarRemove);
        }
    }
}
