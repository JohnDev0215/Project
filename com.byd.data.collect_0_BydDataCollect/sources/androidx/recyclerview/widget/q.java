package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b.d.g<RecyclerView.c0, a> f2025a = new b.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b.d.d<RecyclerView.c0> f2026b = new b.d.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static b.g.j.e<a> f2027a = new b.g.j.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2028b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f2029c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RecyclerView.l.c f2030d;

        private a() {
        }

        static void a() {
            while (f2027a.b() != null) {
            }
        }

        static a b() {
            a aVarB = f2027a.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f2028b = 0;
            aVar.f2029c = null;
            aVar.f2030d = null;
            f2027a.a(aVar);
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

    private RecyclerView.l.c l(RecyclerView.c0 c0Var, int i2) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f2025a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f2025a.m(iF)) != null) {
            int i3 = aVarM.f2028b;
            if ((i3 & i2) != 0) {
                int i4 = (~i2) & i3;
                aVarM.f2028b = i4;
                if (i2 == 4) {
                    cVar = aVarM.f2029c;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f2030d;
                }
                if ((i4 & 12) == 0) {
                    this.f2025a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2025a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2025a.put(c0Var, aVarB);
        }
        aVarB.f2028b |= 2;
        aVarB.f2029c = cVar;
    }

    void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f2025a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2025a.put(c0Var, aVarB);
        }
        aVarB.f2028b |= 1;
    }

    void c(long j, RecyclerView.c0 c0Var) {
        this.f2026b.l(j, c0Var);
    }

    void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2025a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2025a.put(c0Var, aVarB);
        }
        aVarB.f2030d = cVar;
        aVarB.f2028b |= 8;
    }

    void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2025a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2025a.put(c0Var, aVarB);
        }
        aVarB.f2029c = cVar;
        aVarB.f2028b |= 4;
    }

    void f() {
        this.f2025a.clear();
        this.f2026b.c();
    }

    RecyclerView.c0 g(long j) {
        return this.f2026b.g(j);
    }

    boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f2025a.get(c0Var);
        return (aVar == null || (aVar.f2028b & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f2025a.get(c0Var);
        return (aVar == null || (aVar.f2028b & 4) == 0) ? false : true;
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
        for (int size = this.f2025a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f2025a.i(size);
            a aVarK = this.f2025a.k(size);
            int i2 = aVarK.f2028b;
            if ((i2 & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i2 & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f2029c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f2030d);
                }
            } else if ((i2 & 14) == 14) {
                bVar.b(c0VarI, aVarK.f2029c, aVarK.f2030d);
            } else if ((i2 & 12) == 12) {
                bVar.d(c0VarI, aVarK.f2029c, aVarK.f2030d);
            } else if ((i2 & 4) != 0) {
                bVar.c(c0VarI, aVarK.f2029c, null);
            } else if ((i2 & 8) != 0) {
                bVar.b(c0VarI, aVarK.f2029c, aVarK.f2030d);
            }
            a.c(aVarK);
        }
    }

    void p(RecyclerView.c0 c0Var) {
        a aVar = this.f2025a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f2028b &= -2;
    }

    void q(RecyclerView.c0 c0Var) {
        int iO = this.f2026b.o() - 1;
        while (true) {
            if (iO < 0) {
                break;
            }
            if (c0Var == this.f2026b.p(iO)) {
                this.f2026b.n(iO);
                break;
            }
            iO--;
        }
        a aVarRemove = this.f2025a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
