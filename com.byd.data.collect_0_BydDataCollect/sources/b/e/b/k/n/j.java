package b.e.b.k.n;

import b.e.b.k.d;
import b.e.b.k.e;
import b.e.b.k.n.f;
import b.e.b.k.n.m;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends m {
    private static int[] k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2503a;

        static {
            int[] iArr = new int[m.b.values().length];
            f2503a = iArr;
            try {
                iArr[m.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2503a[m.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2503a[m.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public j(b.e.b.k.e eVar) {
        super(eVar);
        this.f2520h.f2490e = f.a.LEFT;
        this.f2521i.f2490e = f.a.RIGHT;
        this.f2518f = 0;
    }

    private void q(int[] iArr, int i2, int i3, int i4, int i5, float f2, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 == 0) {
                iArr[0] = (int) ((i8 * f2) + 0.5f);
                iArr[1] = i8;
                return;
            } else {
                if (i6 != 1) {
                    return;
                }
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f2) + 0.5f);
                return;
            }
        }
        int i9 = (int) ((i8 * f2) + 0.5f);
        int i10 = (int) ((i7 / f2) + 0.5f);
        if (i9 <= i7 && i8 <= i8) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else {
            if (i7 > i7 || i10 > i8) {
                return;
            }
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // b.e.b.k.n.m, b.e.b.k.n.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(b.e.b.k.n.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.k.n.j.a(b.e.b.k.n.d):void");
    }

    @Override // b.e.b.k.n.m
    void d() {
        b.e.b.k.e eVarH;
        b.e.b.k.e eVarH2;
        b.e.b.k.e eVar = this.f2514b;
        if (eVar.f2439b) {
            this.f2517e.d(eVar.Q());
        }
        if (this.f2517e.j) {
            e.b bVar = this.f2516d;
            e.b bVar2 = e.b.MATCH_PARENT;
            if (bVar == bVar2 && (((eVarH = this.f2514b.H()) != null && eVarH.z() == e.b.FIXED) || eVarH.z() == bVar2)) {
                b(this.f2520h, eVarH.f2443f.f2520h, this.f2514b.E.c());
                b(this.f2521i, eVarH.f2443f.f2521i, -this.f2514b.G.c());
                return;
            }
        } else {
            e.b bVarZ = this.f2514b.z();
            this.f2516d = bVarZ;
            if (bVarZ != e.b.MATCH_CONSTRAINT) {
                e.b bVar3 = e.b.MATCH_PARENT;
                if (bVarZ == bVar3 && (((eVarH2 = this.f2514b.H()) != null && eVarH2.z() == e.b.FIXED) || eVarH2.z() == bVar3)) {
                    int iQ = (eVarH2.Q() - this.f2514b.E.c()) - this.f2514b.G.c();
                    b(this.f2520h, eVarH2.f2443f.f2520h, this.f2514b.E.c());
                    b(this.f2521i, eVarH2.f2443f.f2521i, -this.f2514b.G.c());
                    this.f2517e.d(iQ);
                    return;
                }
                if (this.f2516d == e.b.FIXED) {
                    this.f2517e.d(this.f2514b.Q());
                }
            }
        }
        g gVar = this.f2517e;
        if (gVar.j) {
            b.e.b.k.e eVar2 = this.f2514b;
            if (eVar2.f2439b) {
                b.e.b.k.d[] dVarArr = eVar2.M;
                if (dVarArr[0].f2425d != null && dVarArr[1].f2425d != null) {
                    if (eVar2.W()) {
                        this.f2520h.f2491f = this.f2514b.M[0].c();
                        this.f2521i.f2491f = -this.f2514b.M[1].c();
                        return;
                    }
                    f fVarH = h(this.f2514b.M[0]);
                    if (fVarH != null) {
                        b(this.f2520h, fVarH, this.f2514b.M[0].c());
                    }
                    f fVarH2 = h(this.f2514b.M[1]);
                    if (fVarH2 != null) {
                        b(this.f2521i, fVarH2, -this.f2514b.M[1].c());
                    }
                    this.f2520h.f2487b = true;
                    this.f2521i.f2487b = true;
                    return;
                }
                if (dVarArr[0].f2425d != null) {
                    f fVarH3 = h(dVarArr[0]);
                    if (fVarH3 != null) {
                        b(this.f2520h, fVarH3, this.f2514b.M[0].c());
                        b(this.f2521i, this.f2520h, this.f2517e.f2492g);
                        return;
                    }
                    return;
                }
                if (dVarArr[1].f2425d != null) {
                    f fVarH4 = h(dVarArr[1]);
                    if (fVarH4 != null) {
                        b(this.f2521i, fVarH4, -this.f2514b.M[1].c());
                        b(this.f2520h, this.f2521i, -this.f2517e.f2492g);
                        return;
                    }
                    return;
                }
                if ((eVar2 instanceof b.e.b.k.i) || eVar2.H() == null || this.f2514b.n(d.b.CENTER).f2425d != null) {
                    return;
                }
                b(this.f2520h, this.f2514b.H().f2443f.f2520h, this.f2514b.R());
                b(this.f2521i, this.f2520h, this.f2517e.f2492g);
                return;
            }
        }
        if (this.f2516d == e.b.MATCH_CONSTRAINT) {
            b.e.b.k.e eVar3 = this.f2514b;
            int i2 = eVar3.m;
            if (i2 == 2) {
                b.e.b.k.e eVarH3 = eVar3.H();
                if (eVarH3 != null) {
                    g gVar2 = eVarH3.f2444g.f2517e;
                    this.f2517e.l.add(gVar2);
                    gVar2.k.add(this.f2517e);
                    g gVar3 = this.f2517e;
                    gVar3.f2487b = true;
                    gVar3.k.add(this.f2520h);
                    this.f2517e.k.add(this.f2521i);
                }
            } else if (i2 == 3) {
                if (eVar3.n == 3) {
                    this.f2520h.f2486a = this;
                    this.f2521i.f2486a = this;
                    l lVar = eVar3.f2444g;
                    lVar.f2520h.f2486a = this;
                    lVar.f2521i.f2486a = this;
                    gVar.f2486a = this;
                    if (eVar3.Y()) {
                        this.f2517e.l.add(this.f2514b.f2444g.f2517e);
                        this.f2514b.f2444g.f2517e.k.add(this.f2517e);
                        l lVar2 = this.f2514b.f2444g;
                        lVar2.f2517e.f2486a = this;
                        this.f2517e.l.add(lVar2.f2520h);
                        this.f2517e.l.add(this.f2514b.f2444g.f2521i);
                        this.f2514b.f2444g.f2520h.k.add(this.f2517e);
                        this.f2514b.f2444g.f2521i.k.add(this.f2517e);
                    } else if (this.f2514b.W()) {
                        this.f2514b.f2444g.f2517e.l.add(this.f2517e);
                        this.f2517e.k.add(this.f2514b.f2444g.f2517e);
                    } else {
                        this.f2514b.f2444g.f2517e.l.add(this.f2517e);
                    }
                } else {
                    g gVar4 = eVar3.f2444g.f2517e;
                    gVar.l.add(gVar4);
                    gVar4.k.add(this.f2517e);
                    this.f2514b.f2444g.f2520h.k.add(this.f2517e);
                    this.f2514b.f2444g.f2521i.k.add(this.f2517e);
                    g gVar5 = this.f2517e;
                    gVar5.f2487b = true;
                    gVar5.k.add(this.f2520h);
                    this.f2517e.k.add(this.f2521i);
                    this.f2520h.l.add(this.f2517e);
                    this.f2521i.l.add(this.f2517e);
                }
            }
        }
        b.e.b.k.e eVar4 = this.f2514b;
        b.e.b.k.d[] dVarArr2 = eVar4.M;
        if (dVarArr2[0].f2425d != null && dVarArr2[1].f2425d != null) {
            if (eVar4.W()) {
                this.f2520h.f2491f = this.f2514b.M[0].c();
                this.f2521i.f2491f = -this.f2514b.M[1].c();
                return;
            }
            f fVarH5 = h(this.f2514b.M[0]);
            f fVarH6 = h(this.f2514b.M[1]);
            fVarH5.b(this);
            fVarH6.b(this);
            this.j = m.b.CENTER;
            return;
        }
        if (dVarArr2[0].f2425d != null) {
            f fVarH7 = h(dVarArr2[0]);
            if (fVarH7 != null) {
                b(this.f2520h, fVarH7, this.f2514b.M[0].c());
                c(this.f2521i, this.f2520h, 1, this.f2517e);
                return;
            }
            return;
        }
        if (dVarArr2[1].f2425d != null) {
            f fVarH8 = h(dVarArr2[1]);
            if (fVarH8 != null) {
                b(this.f2521i, fVarH8, -this.f2514b.M[1].c());
                c(this.f2520h, this.f2521i, -1, this.f2517e);
                return;
            }
            return;
        }
        if ((eVar4 instanceof b.e.b.k.i) || eVar4.H() == null) {
            return;
        }
        b(this.f2520h, this.f2514b.H().f2443f.f2520h, this.f2514b.R());
        c(this.f2521i, this.f2520h, 1, this.f2517e);
    }

    @Override // b.e.b.k.n.m
    public void e() {
        f fVar = this.f2520h;
        if (fVar.j) {
            this.f2514b.G0(fVar.f2492g);
        }
    }

    @Override // b.e.b.k.n.m
    void f() {
        this.f2515c = null;
        this.f2520h.c();
        this.f2521i.c();
        this.f2517e.c();
        this.f2519g = false;
    }

    @Override // b.e.b.k.n.m
    boolean m() {
        return this.f2516d != e.b.MATCH_CONSTRAINT || this.f2514b.m == 0;
    }

    void r() {
        this.f2519g = false;
        this.f2520h.c();
        this.f2520h.j = false;
        this.f2521i.c();
        this.f2521i.j = false;
        this.f2517e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f2514b.s();
    }
}
