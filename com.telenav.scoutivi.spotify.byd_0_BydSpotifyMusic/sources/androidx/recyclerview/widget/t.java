package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n.g<RecyclerView.c0, a> f3187a = new n.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n.d<RecyclerView.c0> f3188b = new n.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    static class a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        static h0.e<a> f3189d = new h0.f(20);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        RecyclerView.l.c f3191b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f3192c;

        private a() {
        }

        static void a() {
            while (f3189d.b() != null) {
            }
        }

        static a b() {
            a aVarB = f3189d.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f3190a = 0;
            aVar.f3191b = null;
            aVar.f3192c = null;
            f3189d.a(aVar);
        }
    }

    /* JADX INFO: compiled from: ViewInfoStore.java */
    interface b {
        void a(RecyclerView.c0 c0Var);

        void b(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    t() {
    }

    private RecyclerView.l.c l(RecyclerView.c0 c0Var, int i7) {
        a aVarO;
        RecyclerView.l.c cVar;
        int iH = this.f3187a.h(c0Var);
        if (iH >= 0 && (aVarO = this.f3187a.o(iH)) != null) {
            int i8 = aVarO.f3190a;
            if ((i8 & i7) != 0) {
                int i9 = (~i7) & i8;
                aVarO.f3190a = i9;
                if (i7 == 4) {
                    cVar = aVarO.f3191b;
                } else {
                    if (i7 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarO.f3192c;
                }
                if ((i9 & 12) == 0) {
                    this.f3187a.m(iH);
                    a.c(aVarO);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f3187a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3187a.put(c0Var, aVarB);
        }
        aVarB.f3190a |= 2;
        aVarB.f3191b = cVar;
    }

    void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f3187a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3187a.put(c0Var, aVarB);
        }
        aVarB.f3190a |= 1;
    }

    void c(long j7, RecyclerView.c0 c0Var) {
        this.f3188b.j(j7, c0Var);
    }

    void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f3187a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3187a.put(c0Var, aVarB);
        }
        aVarB.f3192c = cVar;
        aVarB.f3190a |= 8;
    }

    void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f3187a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3187a.put(c0Var, aVarB);
        }
        aVarB.f3191b = cVar;
        aVarB.f3190a |= 4;
    }

    void f() {
        this.f3187a.clear();
        this.f3188b.a();
    }

    RecyclerView.c0 g(long j7) {
        return this.f3188b.e(j7);
    }

    boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f3187a.get(c0Var);
        return (aVar == null || (aVar.f3190a & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f3187a.get(c0Var);
        return (aVar == null || (aVar.f3190a & 4) == 0) ? false : true;
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
        for (int size = this.f3187a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarK = this.f3187a.k(size);
            a aVarM = this.f3187a.m(size);
            int i7 = aVarM.f3190a;
            if ((i7 & 3) == 3) {
                bVar.a(c0VarK);
            } else if ((i7 & 1) != 0) {
                RecyclerView.l.c cVar = aVarM.f3191b;
                if (cVar == null) {
                    bVar.a(c0VarK);
                } else {
                    bVar.c(c0VarK, cVar, aVarM.f3192c);
                }
            } else if ((i7 & 14) == 14) {
                bVar.b(c0VarK, aVarM.f3191b, aVarM.f3192c);
            } else if ((i7 & 12) == 12) {
                bVar.d(c0VarK, aVarM.f3191b, aVarM.f3192c);
            } else if ((i7 & 4) != 0) {
                bVar.c(c0VarK, aVarM.f3191b, null);
            } else if ((i7 & 8) != 0) {
                bVar.b(c0VarK, aVarM.f3191b, aVarM.f3192c);
            }
            a.c(aVarM);
        }
    }

    void p(RecyclerView.c0 c0Var) {
        a aVar = this.f3187a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f3190a &= -2;
    }

    void q(RecyclerView.c0 c0Var) {
        int iN = this.f3188b.n() - 1;
        while (true) {
            if (iN < 0) {
                break;
            }
            if (c0Var == this.f3188b.o(iN)) {
                this.f3188b.l(iN);
                break;
            }
            iN--;
        }
        a aVarRemove = this.f3187a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
