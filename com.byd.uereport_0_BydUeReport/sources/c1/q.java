package c1;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v.g<RecyclerView.c0, a> f2290a = new v.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v.d<RecyclerView.c0> f2291b = new v.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static n0.e<a> f2292a = new n0.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.l.c f2294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.l.c f2295d;

        public static void a() {
            while (f2292a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = f2292a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.f2293b = 0;
            aVar.f2294c = null;
            aVar.f2295d = null;
            f2292a.a(aVar);
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
        a aVarB = this.f2290a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2290a.put(c0Var, aVarB);
        }
        aVarB.f2293b |= 2;
        aVarB.f2294c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f2290a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2290a.put(c0Var, aVarB);
        }
        aVarB.f2293b |= 1;
    }

    public void c(long j8, RecyclerView.c0 c0Var) {
        this.f2291b.k(j8, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2290a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2290a.put(c0Var, aVarB);
        }
        aVarB.f2295d = cVar;
        aVarB.f2293b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2290a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2290a.put(c0Var, aVarB);
        }
        aVarB.f2294c = cVar;
        aVarB.f2293b |= 4;
    }

    public void f() {
        this.f2290a.clear();
        this.f2291b.b();
    }

    public RecyclerView.c0 g(long j8) {
        return this.f2291b.f(j8);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f2290a.get(c0Var);
        return (aVar == null || (aVar.f2293b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f2290a.get(c0Var);
        return (aVar == null || (aVar.f2293b & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    public final RecyclerView.l.c l(RecyclerView.c0 c0Var, int i8) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f2290a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f2290a.m(iF)) != null) {
            int i9 = aVarM.f2293b;
            if ((i9 & i8) != 0) {
                int i10 = (~i8) & i9;
                aVarM.f2293b = i10;
                if (i8 == 4) {
                    cVar = aVarM.f2294c;
                } else {
                    if (i8 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f2295d;
                }
                if ((i10 & 12) == 0) {
                    this.f2290a.k(iF);
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
        for (int size = this.f2290a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f2290a.i(size);
            a aVarK = this.f2290a.k(size);
            int i8 = aVarK.f2293b;
            if ((i8 & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i8 & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f2294c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f2295d);
                }
            } else if ((i8 & 14) == 14) {
                bVar.b(c0VarI, aVarK.f2294c, aVarK.f2295d);
            } else if ((i8 & 12) == 12) {
                bVar.d(c0VarI, aVarK.f2294c, aVarK.f2295d);
            } else if ((i8 & 4) != 0) {
                bVar.c(c0VarI, aVarK.f2294c, null);
            } else if ((i8 & 8) != 0) {
                bVar.b(c0VarI, aVarK.f2294c, aVarK.f2295d);
            }
            a.c(aVarK);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.f2290a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f2293b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iN = this.f2291b.n() - 1;
        while (true) {
            if (iN < 0) {
                break;
            }
            if (c0Var == this.f2291b.o(iN)) {
                this.f2291b.m(iN);
                break;
            }
            iN--;
        }
        a aVarRemove = this.f2290a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
