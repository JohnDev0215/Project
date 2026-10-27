package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a.d.g<RecyclerView.d0, a> f1613a = new a.d.g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a.d.d<RecyclerView.d0> f1614b = new a.d.d<>();

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static a.g.j.e<a> f1615a = new a.g.j.f(20);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1616b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        RecyclerView.l.c f1617c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RecyclerView.l.c f1618d;

        private a() {
        }

        static void a() {
            while (f1615a.b() != null) {
            }
        }

        static a b() {
            a aVarB = f1615a.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f1616b = 0;
            aVar.f1617c = null;
            aVar.f1618d = null;
            f1615a.a(aVar);
        }
    }

    interface b {
        void a(RecyclerView.d0 d0Var);

        void b(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void d(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    q() {
    }

    private RecyclerView.l.c l(RecyclerView.d0 d0Var, int i) {
        a aVarM;
        RecyclerView.l.c cVar;
        int iF = this.f1613a.f(d0Var);
        if (iF >= 0 && (aVarM = this.f1613a.m(iF)) != null) {
            int i2 = aVarM.f1616b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                aVarM.f1616b = i3;
                if (i == 4) {
                    cVar = aVarM.f1617c;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM.f1618d;
                }
                if ((i3 & 12) == 0) {
                    this.f1613a.k(iF);
                    a.c(aVarM);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1613a.get(d0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1613a.put(d0Var, aVarB);
        }
        aVarB.f1616b |= 2;
        aVarB.f1617c = cVar;
    }

    void b(RecyclerView.d0 d0Var) {
        a aVarB = this.f1613a.get(d0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1613a.put(d0Var, aVarB);
        }
        aVarB.f1616b |= 1;
    }

    void c(long j, RecyclerView.d0 d0Var) {
        this.f1614b.i(j, d0Var);
    }

    void d(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1613a.get(d0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1613a.put(d0Var, aVarB);
        }
        aVarB.f1618d = cVar;
        aVarB.f1616b |= 8;
    }

    void e(RecyclerView.d0 d0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f1613a.get(d0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1613a.put(d0Var, aVarB);
        }
        aVarB.f1617c = cVar;
        aVarB.f1616b |= 4;
    }

    void f() {
        this.f1613a.clear();
        this.f1614b.b();
    }

    RecyclerView.d0 g(long j) {
        return this.f1614b.e(j);
    }

    boolean h(RecyclerView.d0 d0Var) {
        a aVar = this.f1613a.get(d0Var);
        return (aVar == null || (aVar.f1616b & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.d0 d0Var) {
        a aVar = this.f1613a.get(d0Var);
        return (aVar == null || (aVar.f1616b & 4) == 0) ? false : true;
    }

    void j() {
        a.a();
    }

    public void k(RecyclerView.d0 d0Var) {
        p(d0Var);
    }

    RecyclerView.l.c m(RecyclerView.d0 d0Var) {
        return l(d0Var, 8);
    }

    RecyclerView.l.c n(RecyclerView.d0 d0Var) {
        return l(d0Var, 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void o(androidx.recyclerview.widget.q.b r7) {
        /*
            r6 = this;
            a.d.g<androidx.recyclerview.widget.RecyclerView$d0, androidx.recyclerview.widget.q$a> r0 = r6.f1613a
            int r0 = r0.size()
            int r0 = r0 + (-1)
        L8:
            if (r0 < 0) goto L63
            a.d.g<androidx.recyclerview.widget.RecyclerView$d0, androidx.recyclerview.widget.q$a> r1 = r6.f1613a
            java.lang.Object r1 = r1.i(r0)
            androidx.recyclerview.widget.RecyclerView$d0 r1 = (androidx.recyclerview.widget.RecyclerView.d0) r1
            a.d.g<androidx.recyclerview.widget.RecyclerView$d0, androidx.recyclerview.widget.q$a> r2 = r6.f1613a
            java.lang.Object r2 = r2.k(r0)
            androidx.recyclerview.widget.q$a r2 = (androidx.recyclerview.widget.q.a) r2
            int r3 = r2.f1616b
            r4 = r3 & 3
            r5 = 3
            if (r4 != r5) goto L25
        L21:
            r7.a(r1)
            goto L5d
        L25:
            r4 = r3 & 1
            if (r4 == 0) goto L34
            androidx.recyclerview.widget.RecyclerView$l$c r3 = r2.f1617c
            if (r3 != 0) goto L2e
            goto L21
        L2e:
            androidx.recyclerview.widget.RecyclerView$l$c r4 = r2.f1618d
        L30:
            r7.c(r1, r3, r4)
            goto L5d
        L34:
            r4 = r3 & 14
            r5 = 14
            if (r4 != r5) goto L42
        L3a:
            androidx.recyclerview.widget.RecyclerView$l$c r3 = r2.f1617c
            androidx.recyclerview.widget.RecyclerView$l$c r4 = r2.f1618d
            r7.b(r1, r3, r4)
            goto L5d
        L42:
            r4 = r3 & 12
            r5 = 12
            if (r4 != r5) goto L50
            androidx.recyclerview.widget.RecyclerView$l$c r3 = r2.f1617c
            androidx.recyclerview.widget.RecyclerView$l$c r4 = r2.f1618d
            r7.d(r1, r3, r4)
            goto L5d
        L50:
            r4 = r3 & 4
            if (r4 == 0) goto L58
            androidx.recyclerview.widget.RecyclerView$l$c r3 = r2.f1617c
            r4 = 0
            goto L30
        L58:
            r3 = r3 & 8
            if (r3 == 0) goto L5d
            goto L3a
        L5d:
            androidx.recyclerview.widget.q.a.c(r2)
            int r0 = r0 + (-1)
            goto L8
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.q.o(androidx.recyclerview.widget.q$b):void");
    }

    void p(RecyclerView.d0 d0Var) {
        a aVar = this.f1613a.get(d0Var);
        if (aVar == null) {
            return;
        }
        aVar.f1616b &= -2;
    }

    void q(RecyclerView.d0 d0Var) {
        int iL = this.f1614b.l() - 1;
        while (true) {
            if (iL < 0) {
                break;
            }
            if (d0Var == this.f1614b.m(iL)) {
                this.f1614b.k(iL);
                break;
            }
            iL--;
        }
        a aVarRemove = this.f1613a.remove(d0Var);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
