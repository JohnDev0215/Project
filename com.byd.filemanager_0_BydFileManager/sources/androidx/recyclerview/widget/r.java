package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b.d.g<RecyclerView.c0, a> f1488a = new b.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b.d.d<RecyclerView.c0> f1489b = new b.d.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static b.g.j.e<a> f1490a = new b.g.j.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1491b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f1492c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RecyclerView.l.c f1493d;

        private a() {
        }

        static void a() {
            while (f1490a.b() != null) {
            }
        }

        static a b() {
            a aVarB = f1490a.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f1491b = 0;
            aVar.f1492c = null;
            aVar.f1493d = null;
            f1490a.a(aVar);
        }
    }

    /* JADX INFO: compiled from: ViewInfoStore.java */
    interface b {
        void a(RecyclerView.c0 c0Var);

        void b(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    r() {
    }

    private RecyclerView.l.c l(RecyclerView.c0 c0Var, int i) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f1488a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f1488a.m(iF)) != null) {
            int i2 = aVarM.f1491b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.f1491b = i3;
                if (i == 4) {
                    cVar = aVarM.f1492c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f1493d;
                }
                if ((i3 & 12) == 0) {
                    this.f1488a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1488a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1488a.put(c0Var, aVarB);
        }
        aVarB.f1491b |= 2;
        aVarB.f1492c = cVar;
    }

    void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f1488a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1488a.put(c0Var, aVarB);
        }
        aVarB.f1491b |= 1;
    }

    void c(long j, RecyclerView.c0 c0Var) {
        this.f1489b.j(j, c0Var);
    }

    void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1488a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1488a.put(c0Var, aVarB);
        }
        aVarB.f1493d = cVar;
        aVarB.f1491b |= 8;
    }

    void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1488a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1488a.put(c0Var, aVarB);
        }
        aVarB.f1492c = cVar;
        aVarB.f1491b |= 4;
    }

    void f() {
        this.f1488a.clear();
        this.f1489b.b();
    }

    RecyclerView.c0 g(long j) {
        return this.f1489b.f(j);
    }

    boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f1488a.get(c0Var);
        return (aVar == null || (aVar.f1491b & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f1488a.get(c0Var);
        return (aVar == null || (aVar.f1491b & 4) == 0) ? false : true;
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
        for (int size = this.f1488a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f1488a.i(size);
            a aVarK = this.f1488a.k(size);
            int i = aVarK.f1491b;
            if ((i & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f1492c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f1493d);
                }
            } else if ((i & 14) == 14) {
                bVar.b(c0VarI, aVarK.f1492c, aVarK.f1493d);
            } else if ((i & 12) == 12) {
                bVar.d(c0VarI, aVarK.f1492c, aVarK.f1493d);
            } else if ((i & 4) != 0) {
                bVar.c(c0VarI, aVarK.f1492c, null);
            } else if ((i & 8) != 0) {
                bVar.b(c0VarI, aVarK.f1492c, aVarK.f1493d);
            }
            a.c(aVarK);
        }
    }

    void p(RecyclerView.c0 c0Var) {
        a aVar = this.f1488a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1491b &= -2;
    }

    void q(RecyclerView.c0 c0Var) {
        int iM = this.f1489b.m() - 1;
        while (true) {
            if (iM < 0) {
                break;
            }
            if (c0Var == this.f1489b.n(iM)) {
                this.f1489b.l(iM);
                break;
            }
            iM--;
        }
        a aVarRemove = this.f1488a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
