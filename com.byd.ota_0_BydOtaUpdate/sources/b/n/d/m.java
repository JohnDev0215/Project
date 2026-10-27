package b.n.d;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.d.g<RecyclerView.c0, a> f2116a = new b.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.d.d<RecyclerView.c0> f2117b = new b.d.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static b.f.l.d<a> f2118a = new b.f.l.e(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.l.c f2120c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.l.c f2121d;

        public static void a() {
            while (f2118a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = f2118a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.f2119b = 0;
            aVar.f2120c = null;
            aVar.f2121d = null;
            f2118a.a(aVar);
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
        a aVarB = this.f2116a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2116a.put(c0Var, aVarB);
        }
        aVarB.f2119b |= 2;
        aVarB.f2120c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f2116a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2116a.put(c0Var, aVarB);
        }
        aVarB.f2119b |= 1;
    }

    public void c(long j, RecyclerView.c0 c0Var) {
        this.f2117b.j(j, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2116a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2116a.put(c0Var, aVarB);
        }
        aVarB.f2121d = cVar;
        aVarB.f2119b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f2116a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f2116a.put(c0Var, aVarB);
        }
        aVarB.f2120c = cVar;
        aVarB.f2119b |= 4;
    }

    public void f() {
        this.f2116a.clear();
        this.f2117b.b();
    }

    public RecyclerView.c0 g(long j) {
        return this.f2117b.g(j);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f2116a.get(c0Var);
        return (aVar == null || (aVar.f2119b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f2116a.get(c0Var);
        return (aVar == null || (aVar.f2119b & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    public final RecyclerView.l.c l(RecyclerView.c0 c0Var, int i2) {
        a aVarL;
        RecyclerView.l.c cVar;
        int iF = this.f2116a.f(c0Var);
        if (iF >= 0 && (aVarL = this.f2116a.l(iF)) != null) {
            int i3 = aVarL.f2119b;
            if ((i3 & i2) != 0) {
                int i4 = (~i2) & i3;
                aVarL.f2119b = i4;
                if (i2 == 4) {
                    cVar = aVarL.f2120c;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarL.f2121d;
                }
                if ((i4 & 12) == 0) {
                    this.f2116a.j(iF);
                    a.c(aVarL);
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
        for (int size = this.f2116a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f2116a.i(size);
            a aVarJ = this.f2116a.j(size);
            int i2 = aVarJ.f2119b;
            if ((i2 & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i2 & 1) != 0) {
                RecyclerView.l.c cVar = aVarJ.f2120c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarJ.f2121d);
                }
            } else if ((i2 & 14) == 14) {
                bVar.b(c0VarI, aVarJ.f2120c, aVarJ.f2121d);
            } else if ((i2 & 12) == 12) {
                bVar.d(c0VarI, aVarJ.f2120c, aVarJ.f2121d);
            } else if ((i2 & 4) != 0) {
                bVar.c(c0VarI, aVarJ.f2120c, null);
            } else if ((i2 & 8) != 0) {
                bVar.b(c0VarI, aVarJ.f2120c, aVarJ.f2121d);
            }
            a.c(aVarJ);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.f2116a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f2119b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iM = this.f2117b.m() - 1;
        while (true) {
            if (iM < 0) {
                break;
            }
            if (c0Var == this.f2117b.n(iM)) {
                this.f2117b.l(iM);
                break;
            }
            iM--;
        }
        a aVarRemove = this.f2116a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
