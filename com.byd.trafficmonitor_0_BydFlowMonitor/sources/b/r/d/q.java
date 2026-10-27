package b.r.d;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.e.g<RecyclerView.c0, a> f3137a = new b.e.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.e.d<RecyclerView.c0> f3138b = new b.e.d<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static b.h.l.d<a> f3139a = new b.h.l.e(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3140b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.l.c f3141c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.l.c f3142d;

        public static void a() {
            while (f3139a.b() != null) {
            }
        }

        public static a b() {
            a aVarB = f3139a.b();
            return aVarB == null ? new a() : aVarB;
        }

        public static void c(a aVar) {
            aVar.f3140b = 0;
            aVar.f3141c = null;
            aVar.f3142d = null;
            f3139a.a(aVar);
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
        a aVarB = this.f3137a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3137a.put(c0Var, aVarB);
        }
        aVarB.f3140b |= 2;
        aVarB.f3141c = cVar;
    }

    public void b(RecyclerView.c0 c0Var) {
        a aVarB = this.f3137a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3137a.put(c0Var, aVarB);
        }
        aVarB.f3140b |= 1;
    }

    public void c(long j, RecyclerView.c0 c0Var) {
        this.f3138b.i(j, c0Var);
    }

    public void d(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f3137a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3137a.put(c0Var, aVarB);
        }
        aVarB.f3142d = cVar;
        aVarB.f3140b |= 8;
    }

    public void e(RecyclerView.c0 c0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f3137a.get(c0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f3137a.put(c0Var, aVarB);
        }
        aVarB.f3141c = cVar;
        aVarB.f3140b |= 4;
    }

    public void f() {
        this.f3137a.clear();
        this.f3138b.b();
    }

    public RecyclerView.c0 g(long j) {
        return this.f3138b.e(j);
    }

    public boolean h(RecyclerView.c0 c0Var) {
        a aVar = this.f3137a.get(c0Var);
        return (aVar == null || (aVar.f3140b & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.c0 c0Var) {
        a aVar = this.f3137a.get(c0Var);
        return (aVar == null || (aVar.f3140b & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.c0 c0Var) {
        p(c0Var);
    }

    public final RecyclerView.l.c l(RecyclerView.c0 c0Var, int i2) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f3137a.f(c0Var);
        if (iF >= 0 && (aVarM = this.f3137a.m(iF)) != null) {
            int i3 = aVarM.f3140b;
            if ((i3 & i2) != 0) {
                int i4 = (~i2) & i3;
                aVarM.f3140b = i4;
                if (i2 == 4) {
                    cVar = aVarM.f3141c;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f3142d;
                }
                if ((i4 & 12) == 0) {
                    this.f3137a.k(iF);
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
        for (int size = this.f3137a.size() - 1; size >= 0; size--) {
            RecyclerView.c0 c0VarI = this.f3137a.i(size);
            a aVarK = this.f3137a.k(size);
            int i2 = aVarK.f3140b;
            if ((i2 & 3) == 3) {
                bVar.a(c0VarI);
            } else if ((i2 & 1) != 0) {
                RecyclerView.l.c cVar = aVarK.f3141c;
                if (cVar == null) {
                    bVar.a(c0VarI);
                } else {
                    bVar.c(c0VarI, cVar, aVarK.f3142d);
                }
            } else if ((i2 & 14) == 14) {
                bVar.b(c0VarI, aVarK.f3141c, aVarK.f3142d);
            } else if ((i2 & 12) == 12) {
                bVar.d(c0VarI, aVarK.f3141c, aVarK.f3142d);
            } else if ((i2 & 4) != 0) {
                bVar.c(c0VarI, aVarK.f3141c, null);
            } else if ((i2 & 8) != 0) {
                bVar.b(c0VarI, aVarK.f3141c, aVarK.f3142d);
            }
            a.c(aVarK);
        }
    }

    public void p(RecyclerView.c0 c0Var) {
        a aVar = this.f3137a.get(c0Var);
        if (aVar == null) {
            return;
        }
        aVar.f3140b &= -2;
    }

    public void q(RecyclerView.c0 c0Var) {
        int iL = this.f3138b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (c0Var == this.f3138b.m(iL)) {
                this.f3138b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.f3137a.remove(c0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
