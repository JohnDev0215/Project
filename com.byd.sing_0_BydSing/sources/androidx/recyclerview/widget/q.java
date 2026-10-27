package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.d.g<RecyclerView.c0, a> f1840a = new c.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.d.d<RecyclerView.c0> f1841b = new c.d.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static c.g.j.e<a> f1842a = new c.g.j.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1843b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f1844c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RecyclerView.l.c f1845d;

        private a() {
        }

        static void a() {
            while (f1842a.b() != null) {
            }
        }

        static a b() {
            a aVarB = f1842a.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f1843b = 0;
            aVar.f1844c = null;
            aVar.f1845d = null;
            f1842a.a(aVar);
        }
    }

    /* JADX INFO: compiled from: ViewInfoStore.java */
    interface b {
        void a(RecyclerView.c0 c0Var);

        void b(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    q() {
    }

    private RecyclerView.l.c l(RecyclerView.c0 c0Var, int i) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f1840a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f1840a.m(iF)) != null) {
            int i2 = aVarM.f1843b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.f1843b = i3;
                if (i == 4) {
                    cVar = aVarM.f1844c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f1845d;
                }
                if ((i3 & 12) == 0) {
                    this.f1840a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1840a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1840a.put(c0Var, aVarB);
        }
        aVarB.f1843b |= 2;
        aVarB.f1844c = cVar;
    }

    void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f1840a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1840a.put(c0Var, aVarB);
        }
        aVarB.f1843b |= 1;
    }

    void c(long j, RecyclerView.c0 c0Var) {
        this.f1841b.i(j, c0Var);
    }

    void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1840a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1840a.put(c0Var, aVarB);
        }
        aVarB.f1845d = cVar;
        aVarB.f1843b |= 8;
    }

    void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1840a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1840a.put(c0Var, aVarB);
        }
        aVarB.f1844c = cVar;
        aVarB.f1843b |= 4;
    }

    void f() {
        this.f1840a.clear();
        this.f1841b.b();
    }

    RecyclerView.c0 g(long j) {
        return this.f1841b.e(j);
    }

    boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f1840a.get(c0Var);
        return (aVar == null || (aVar.f1843b & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f1840a.get(c0Var);
        return (aVar == null || (aVar.f1843b & 4) == 0) ? false : true;
    }

    void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    RecyclerView.l.c m(RecyclerView.c0 c0Var) {
        return l(c0Var, 8);
    }

    RecyclerView.l.c n(RecyclerView.c0 c0Var) {
        return l(c0Var, 4);
    }

    void o(b bVar) {
        for (int size = this.f1840a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f1840a.i(size);
            a aVarK = this.f1840a.k(size);
            int i = aVarK.f1843b;
            if ((i & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f1844c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f1845d);
                }
            } else if ((i & 14) == 14) {
                bVar.b(c0VarI, aVarK.f1844c, aVarK.f1845d);
            } else if ((i & 12) == 12) {
                bVar.d(c0VarI, aVarK.f1844c, aVarK.f1845d);
            } else if ((i & 4) != 0) {
                bVar.c(c0VarI, aVarK.f1844c, null);
            } else if ((i & 8) != 0) {
                bVar.b(c0VarI, aVarK.f1844c, aVarK.f1845d);
            }
            a.c(aVarK);
        }
    }

    void p(RecyclerView.c0 c0Var) {
        a aVar = this.f1840a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1843b &= -2;
    }

    void q(RecyclerView.c0 c0Var) {
        int iL = this.f1841b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (c0Var == this.f1841b.m(iL)) {
                this.f1841b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.f1840a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
