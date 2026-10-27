package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class kd {
    public final g4<RecyclerView.c0, a> a = new g4<>();
    public final d4<RecyclerView.c0> b = new d4<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {
        public static w8<a> a = new x8(20);
        public int b;
        public RecyclerView.l.c c;
        public RecyclerView.l.c d;

        public static void a() {
            while (a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.b = 0;
            aVar.c = null;
            aVar.d = null;
            a.a(aVar);
        }
    }

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public interface b {
        void a(RecyclerView.c0 c0Var);

        void b(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    public void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.a.put(c0Var, aVarB);
        }
        aVarB.b |= 2;
        aVarB.c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.a.put(c0Var, aVarB);
        }
        aVarB.b |= 1;
    }

    public void c(long j, RecyclerView.c0 c0Var) {
        this.b.i(j, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.a.put(c0Var, aVarB);
        }
        aVarB.d = cVar;
        aVarB.b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.a.put(c0Var, aVarB);
        }
        aVarB.c = cVar;
        aVarB.b |= 4;
    }

    public void f() {
        this.a.clear();
        this.b.b();
    }

    public RecyclerView.c0 g(long j) {
        return this.b.e(j);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.a.get(c0Var);
        return (aVar == null || (aVar.b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.a.get(c0Var);
        return (aVar == null || (aVar.b & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    public final RecyclerView.l.c l(RecyclerView.c0 c0Var, int i) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.a.f(c0Var);
        if (iF >= 0 && (aVarM = this.a.m(iF)) != null) {
            int i2 = aVarM.b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.b = i3;
                if (i == 4) {
                    cVar = aVarM.c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.d;
                }
                if ((i3 & 12) == 0) {
                    this.a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    public RecyclerView.l.c m(RecyclerView.c0 c0Var) {
        return l(c0Var, 8);
    }

    public RecyclerView.l.c n(RecyclerView.c0 c0Var) {
        return l(c0Var, 4);
    }

    public void o(b bVar) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.a.i(size);
            a aVarK = this.a.k(size);
            int i = aVarK.b;
            if ((i & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.d);
                }
            } else if ((i & 14) == 14) {
                bVar.b(c0VarI, aVarK.c, aVarK.d);
            } else if ((i & 12) == 12) {
                bVar.d(c0VarI, aVarK.c, aVarK.d);
            } else if ((i & 4) != 0) {
                bVar.c(c0VarI, aVarK.c, null);
            } else if ((i & 8) != 0) {
                bVar.b(c0VarI, aVarK.c, aVarK.d);
            }
            a.c(aVarK);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iL = this.b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (c0Var == this.b.m(iL)) {
                this.b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
