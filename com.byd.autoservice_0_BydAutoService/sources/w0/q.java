package w0;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s.g<RecyclerView.c0, a> f5675a = new s.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s.d<RecyclerView.c0> f5676b = new s.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static k0.e<a> f5677a = new k0.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5678b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.l.c f5679c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.l.c f5680d;

        public static void a() {
            while (f5677a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = f5677a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.f5678b = 0;
            aVar.f5679c = null;
            aVar.f5680d = null;
            f5677a.a(aVar);
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
        a aVarB = this.f5675a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f5675a.put(c0Var, aVarB);
        }
        aVarB.f5678b |= 2;
        aVarB.f5679c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f5675a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f5675a.put(c0Var, aVarB);
        }
        aVarB.f5678b |= 1;
    }

    public void c(long j3, RecyclerView.c0 c0Var) {
        this.f5676b.i(j3, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f5675a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f5675a.put(c0Var, aVarB);
        }
        aVarB.f5680d = cVar;
        aVarB.f5678b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f5675a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f5675a.put(c0Var, aVarB);
        }
        aVarB.f5679c = cVar;
        aVarB.f5678b |= 4;
    }

    public void f() {
        this.f5675a.clear();
        this.f5676b.b();
    }

    public RecyclerView.c0 g(long j3) {
        return this.f5676b.e(j3);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f5675a.get(c0Var);
        return (aVar == null || (aVar.f5678b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f5675a.get(c0Var);
        return (aVar == null || (aVar.f5678b & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    public final RecyclerView.l.c l(RecyclerView.c0 c0Var, int i3) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f5675a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f5675a.m(iF)) != null) {
            int i4 = aVarM.f5678b;
            if ((i4 & i3) != 0) {
                int i5 = (~i3) & i4;
                aVarM.f5678b = i5;
                if (i3 == 4) {
                    cVar = aVarM.f5679c;
                } else {
                    if (i3 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f5680d;
                }
                if ((i5 & 12) == 0) {
                    this.f5675a.k(iF);
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
        for (int size = this.f5675a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f5675a.i(size);
            a aVarK = this.f5675a.k(size);
            int i3 = aVarK.f5678b;
            if ((i3 & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i3 & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f5679c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f5680d);
                }
            } else if ((i3 & 14) == 14) {
                bVar.b(c0VarI, aVarK.f5679c, aVarK.f5680d);
            } else if ((i3 & 12) == 12) {
                bVar.d(c0VarI, aVarK.f5679c, aVarK.f5680d);
            } else if ((i3 & 4) != 0) {
                bVar.c(c0VarI, aVarK.f5679c, null);
            } else if ((i3 & 8) != 0) {
                bVar.b(c0VarI, aVarK.f5679c, aVarK.f5680d);
            }
            a.c(aVarK);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.f5675a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f5678b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iL = this.f5676b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (c0Var == this.f5676b.m(iL)) {
                this.f5676b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.f5675a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
