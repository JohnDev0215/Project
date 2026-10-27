package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b.d.g<RecyclerView.c0, a> f1296a = new b.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b.d.d<RecyclerView.c0> f1297b = new b.d.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static b.g.j.e<a> f1298a = new b.g.j.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1299b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f1300c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RecyclerView.l.c f1301d;

        private a() {
        }

        static void a() {
            while (f1298a.b() != null) {
            }
        }

        static a b() {
            a aVarB = f1298a.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f1299b = 0;
            aVar.f1300c = null;
            aVar.f1301d = null;
            f1298a.a(aVar);
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
        int iF = this.f1296a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f1296a.m(iF)) != null) {
            int i2 = aVarM.f1299b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.f1299b = i3;
                if (i == 4) {
                    cVar = aVarM.f1300c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f1301d;
                }
                if ((i3 & 12) == 0) {
                    this.f1296a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1296a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1296a.put(c0Var, aVarB);
        }
        aVarB.f1299b |= 2;
        aVarB.f1300c = cVar;
    }

    void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f1296a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1296a.put(c0Var, aVarB);
        }
        aVarB.f1299b |= 1;
    }

    void c(long j, RecyclerView.c0 c0Var) {
        this.f1297b.j(j, c0Var);
    }

    void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1296a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1296a.put(c0Var, aVarB);
        }
        aVarB.f1301d = cVar;
        aVarB.f1299b |= 8;
    }

    void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1296a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1296a.put(c0Var, aVarB);
        }
        aVarB.f1300c = cVar;
        aVarB.f1299b |= 4;
    }

    void f() {
        this.f1296a.clear();
        this.f1297b.b();
    }

    RecyclerView.c0 g(long j) {
        return this.f1297b.e(j);
    }

    boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f1296a.get(c0Var);
        return (aVar == null || (aVar.f1299b & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f1296a.get(c0Var);
        return (aVar == null || (aVar.f1299b & 4) == 0) ? false : true;
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
        for (int size = this.f1296a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f1296a.i(size);
            a aVarK = this.f1296a.k(size);
            int i = aVarK.f1299b;
            if ((i & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f1300c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f1301d);
                }
            } else if ((i & 14) == 14) {
                bVar.b(c0VarI, aVarK.f1300c, aVarK.f1301d);
            } else if ((i & 12) == 12) {
                bVar.d(c0VarI, aVarK.f1300c, aVarK.f1301d);
            } else if ((i & 4) != 0) {
                bVar.c(c0VarI, aVarK.f1300c, null);
            } else if ((i & 8) != 0) {
                bVar.b(c0VarI, aVarK.f1300c, aVarK.f1301d);
            }
            a.c(aVarK);
        }
    }

    void p(RecyclerView.c0 c0Var) {
        a aVar = this.f1296a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1299b &= -2;
    }

    void q(RecyclerView.c0 c0Var) {
        int iM = this.f1297b.m() - 1;
        while (true) {
            if (iM < 0) {
                break;
            }
            if (c0Var == this.f1297b.n(iM)) {
                this.f1297b.l(iM);
                break;
            }
            iM--;
        }
        a aVarRemove = this.f1296a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
