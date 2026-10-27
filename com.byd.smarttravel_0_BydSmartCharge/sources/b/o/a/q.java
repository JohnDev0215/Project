package b.o.a;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.e.g<RecyclerView.c0, a> f1420a = new b.e.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.e.d<RecyclerView.c0> f1421b = new b.e.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static b.g.i.e<a> f1422a = new b.g.i.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1423b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.l.c f1424c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.l.c f1425d;

        public static void a() {
            while (f1422a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = f1422a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.f1423b = 0;
            aVar.f1424c = null;
            aVar.f1425d = null;
            f1422a.a(aVar);
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
        a aVarB = this.f1420a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1420a.put(c0Var, aVarB);
        }
        aVarB.f1423b |= 2;
        aVarB.f1424c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f1420a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1420a.put(c0Var, aVarB);
        }
        aVarB.f1423b |= 1;
    }

    public void c(long j, RecyclerView.c0 c0Var) {
        this.f1421b.i(j, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1420a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1420a.put(c0Var, aVarB);
        }
        aVarB.f1425d = cVar;
        aVarB.f1423b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1420a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1420a.put(c0Var, aVarB);
        }
        aVarB.f1424c = cVar;
        aVarB.f1423b |= 4;
    }

    public void f() {
        this.f1420a.clear();
        this.f1421b.b();
    }

    public RecyclerView.c0 g(long j) {
        return this.f1421b.e(j);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f1420a.get(c0Var);
        return (aVar == null || (aVar.f1423b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f1420a.get(c0Var);
        return (aVar == null || (aVar.f1423b & 4) == 0) ? false : true;
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
        int iF = this.f1420a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f1420a.m(iF)) != null) {
            int i2 = aVarM.f1423b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.f1423b = i3;
                if (i == 4) {
                    cVar = aVarM.f1424c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f1425d;
                }
                if ((i3 & 12) == 0) {
                    this.f1420a.k(iF);
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
        for (int size = this.f1420a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f1420a.i(size);
            a aVarK = this.f1420a.k(size);
            int i = aVarK.f1423b;
            if ((i & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f1424c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f1425d);
                }
            } else if ((i & 14) == 14) {
                bVar.b(c0VarI, aVarK.f1424c, aVarK.f1425d);
            } else if ((i & 12) == 12) {
                bVar.d(c0VarI, aVarK.f1424c, aVarK.f1425d);
            } else if ((i & 4) != 0) {
                bVar.c(c0VarI, aVarK.f1424c, null);
            } else if ((i & 8) != 0) {
                bVar.b(c0VarI, aVarK.f1424c, aVarK.f1425d);
            }
            a.c(aVarK);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.f1420a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1423b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iL = this.f1421b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (c0Var == this.f1421b.m(iL)) {
                this.f1421b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.f1420a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
