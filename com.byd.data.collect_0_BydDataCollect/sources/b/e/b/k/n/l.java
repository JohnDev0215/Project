package b.e.b.k.n;

import b.e.b.k.d;
import b.e.b.k.e;
import b.e.b.k.n.f;
import b.e.b.k.n.m;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends m {
    public f k;
    g l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2512a;

        static {
            int[] iArr = new int[m.b.values().length];
            f2512a = iArr;
            try {
                iArr[m.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2512a[m.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2512a[m.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public l(b.e.b.k.e eVar) {
        super(eVar);
        f fVar = new f(this);
        this.k = fVar;
        this.l = null;
        this.f2520h.f2490e = f.a.TOP;
        this.f2521i.f2490e = f.a.BOTTOM;
        fVar.f2490e = f.a.BASELINE;
        this.f2518f = 1;
    }

    @Override // b.e.b.k.n.m, b.e.b.k.n.d
    public void a(d dVar) {
        float f2;
        float fU;
        float fU2;
        int i2;
        int i3 = a.f2512a[this.j.ordinal()];
        if (i3 == 1) {
            p(dVar);
        } else if (i3 == 2) {
            o(dVar);
        } else if (i3 == 3) {
            b.e.b.k.e eVar = this.f2514b;
            n(dVar, eVar.F, eVar.H, 1);
            return;
        }
        g gVar = this.f2517e;
        if (gVar.f2488c && !gVar.j && this.f2516d == e.b.MATCH_CONSTRAINT) {
            b.e.b.k.e eVar2 = this.f2514b;
            int i4 = eVar2.n;
            if (i4 == 2) {
                b.e.b.k.e eVarH = eVar2.H();
                if (eVarH != null) {
                    if (eVarH.f2444g.f2517e.j) {
                        this.f2517e.d((int) ((r7.f2492g * this.f2514b.u) + 0.5f));
                    }
                }
            } else if (i4 == 3 && eVar2.f2443f.f2517e.j) {
                int iV = eVar2.v();
                if (iV == -1) {
                    b.e.b.k.e eVar3 = this.f2514b;
                    f2 = eVar3.f2443f.f2517e.f2492g;
                    fU = eVar3.u();
                } else if (iV == 0) {
                    fU2 = r7.f2443f.f2517e.f2492g * this.f2514b.u();
                    i2 = (int) (fU2 + 0.5f);
                    this.f2517e.d(i2);
                } else if (iV != 1) {
                    i2 = 0;
                    this.f2517e.d(i2);
                } else {
                    b.e.b.k.e eVar4 = this.f2514b;
                    f2 = eVar4.f2443f.f2517e.f2492g;
                    fU = eVar4.u();
                }
                fU2 = f2 / fU;
                i2 = (int) (fU2 + 0.5f);
                this.f2517e.d(i2);
            }
        }
        f fVar = this.f2520h;
        if (fVar.f2488c) {
            f fVar2 = this.f2521i;
            if (fVar2.f2488c) {
                if (fVar.j && fVar2.j && this.f2517e.j) {
                    return;
                }
                if (!this.f2517e.j && this.f2516d == e.b.MATCH_CONSTRAINT) {
                    b.e.b.k.e eVar5 = this.f2514b;
                    if (eVar5.m == 0 && !eVar5.Y()) {
                        f fVar3 = this.f2520h.l.get(0);
                        f fVar4 = this.f2521i.l.get(0);
                        int i5 = fVar3.f2492g;
                        f fVar5 = this.f2520h;
                        int i6 = i5 + fVar5.f2491f;
                        int i7 = fVar4.f2492g + this.f2521i.f2491f;
                        fVar5.d(i6);
                        this.f2521i.d(i7);
                        this.f2517e.d(i7 - i6);
                        return;
                    }
                }
                if (!this.f2517e.j && this.f2516d == e.b.MATCH_CONSTRAINT && this.f2513a == 1 && this.f2520h.l.size() > 0 && this.f2521i.l.size() > 0) {
                    f fVar6 = this.f2520h.l.get(0);
                    int i8 = (this.f2521i.l.get(0).f2492g + this.f2521i.f2491f) - (fVar6.f2492g + this.f2520h.f2491f);
                    g gVar2 = this.f2517e;
                    int i9 = gVar2.m;
                    if (i8 < i9) {
                        gVar2.d(i8);
                    } else {
                        gVar2.d(i9);
                    }
                }
                if (this.f2517e.j && this.f2520h.l.size() > 0 && this.f2521i.l.size() > 0) {
                    f fVar7 = this.f2520h.l.get(0);
                    f fVar8 = this.f2521i.l.get(0);
                    int i10 = fVar7.f2492g + this.f2520h.f2491f;
                    int i11 = fVar8.f2492g + this.f2521i.f2491f;
                    float fL = this.f2514b.L();
                    if (fVar7 == fVar8) {
                        i10 = fVar7.f2492g;
                        i11 = fVar8.f2492g;
                        fL = 0.5f;
                    }
                    this.f2520h.d((int) (i10 + 0.5f + (((i11 - i10) - this.f2517e.f2492g) * fL)));
                    this.f2521i.d(this.f2520h.f2492g + this.f2517e.f2492g);
                }
            }
        }
    }

    @Override // b.e.b.k.n.m
    void d() {
        b.e.b.k.e eVarH;
        b.e.b.k.e eVarH2;
        b.e.b.k.e eVar = this.f2514b;
        if (eVar.f2439b) {
            this.f2517e.d(eVar.w());
        }
        if (!this.f2517e.j) {
            this.f2516d = this.f2514b.N();
            if (this.f2514b.T()) {
                this.l = new b.e.b.k.n.a(this);
            }
            e.b bVar = this.f2516d;
            if (bVar != e.b.MATCH_CONSTRAINT) {
                if (bVar == e.b.MATCH_PARENT && (eVarH2 = this.f2514b.H()) != null && eVarH2.N() == e.b.FIXED) {
                    int iW = (eVarH2.w() - this.f2514b.F.c()) - this.f2514b.H.c();
                    b(this.f2520h, eVarH2.f2444g.f2520h, this.f2514b.F.c());
                    b(this.f2521i, eVarH2.f2444g.f2521i, -this.f2514b.H.c());
                    this.f2517e.d(iW);
                    return;
                }
                if (this.f2516d == e.b.FIXED) {
                    this.f2517e.d(this.f2514b.w());
                }
            }
        } else if (this.f2516d == e.b.MATCH_PARENT && (eVarH = this.f2514b.H()) != null && eVarH.N() == e.b.FIXED) {
            b(this.f2520h, eVarH.f2444g.f2520h, this.f2514b.F.c());
            b(this.f2521i, eVarH.f2444g.f2521i, -this.f2514b.H.c());
            return;
        }
        g gVar = this.f2517e;
        boolean z = gVar.j;
        if (z) {
            b.e.b.k.e eVar2 = this.f2514b;
            if (eVar2.f2439b) {
                b.e.b.k.d[] dVarArr = eVar2.M;
                if (dVarArr[2].f2425d != null && dVarArr[3].f2425d != null) {
                    if (eVar2.Y()) {
                        this.f2520h.f2491f = this.f2514b.M[2].c();
                        this.f2521i.f2491f = -this.f2514b.M[3].c();
                    } else {
                        f fVarH = h(this.f2514b.M[2]);
                        if (fVarH != null) {
                            b(this.f2520h, fVarH, this.f2514b.M[2].c());
                        }
                        f fVarH2 = h(this.f2514b.M[3]);
                        if (fVarH2 != null) {
                            b(this.f2521i, fVarH2, -this.f2514b.M[3].c());
                        }
                        this.f2520h.f2487b = true;
                        this.f2521i.f2487b = true;
                    }
                    if (this.f2514b.T()) {
                        b(this.k, this.f2520h, this.f2514b.o());
                        return;
                    }
                    return;
                }
                if (dVarArr[2].f2425d != null) {
                    f fVarH3 = h(dVarArr[2]);
                    if (fVarH3 != null) {
                        b(this.f2520h, fVarH3, this.f2514b.M[2].c());
                        b(this.f2521i, this.f2520h, this.f2517e.f2492g);
                        if (this.f2514b.T()) {
                            b(this.k, this.f2520h, this.f2514b.o());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (dVarArr[3].f2425d != null) {
                    f fVarH4 = h(dVarArr[3]);
                    if (fVarH4 != null) {
                        b(this.f2521i, fVarH4, -this.f2514b.M[3].c());
                        b(this.f2520h, this.f2521i, -this.f2517e.f2492g);
                    }
                    if (this.f2514b.T()) {
                        b(this.k, this.f2520h, this.f2514b.o());
                        return;
                    }
                    return;
                }
                if (dVarArr[4].f2425d != null) {
                    f fVarH5 = h(dVarArr[4]);
                    if (fVarH5 != null) {
                        b(this.k, fVarH5, 0);
                        b(this.f2520h, this.k, -this.f2514b.o());
                        b(this.f2521i, this.f2520h, this.f2517e.f2492g);
                        return;
                    }
                    return;
                }
                if ((eVar2 instanceof b.e.b.k.i) || eVar2.H() == null || this.f2514b.n(d.b.CENTER).f2425d != null) {
                    return;
                }
                b(this.f2520h, this.f2514b.H().f2444g.f2520h, this.f2514b.S());
                b(this.f2521i, this.f2520h, this.f2517e.f2492g);
                if (this.f2514b.T()) {
                    b(this.k, this.f2520h, this.f2514b.o());
                    return;
                }
                return;
            }
        }
        if (z || this.f2516d != e.b.MATCH_CONSTRAINT) {
            gVar.b(this);
        } else {
            b.e.b.k.e eVar3 = this.f2514b;
            int i2 = eVar3.n;
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
            } else if (i2 == 3 && !eVar3.Y()) {
                b.e.b.k.e eVar4 = this.f2514b;
                if (eVar4.m != 3) {
                    g gVar4 = eVar4.f2443f.f2517e;
                    this.f2517e.l.add(gVar4);
                    gVar4.k.add(this.f2517e);
                    g gVar5 = this.f2517e;
                    gVar5.f2487b = true;
                    gVar5.k.add(this.f2520h);
                    this.f2517e.k.add(this.f2521i);
                }
            }
        }
        b.e.b.k.e eVar5 = this.f2514b;
        b.e.b.k.d[] dVarArr2 = eVar5.M;
        if (dVarArr2[2].f2425d != null && dVarArr2[3].f2425d != null) {
            if (eVar5.Y()) {
                this.f2520h.f2491f = this.f2514b.M[2].c();
                this.f2521i.f2491f = -this.f2514b.M[3].c();
            } else {
                f fVarH6 = h(this.f2514b.M[2]);
                f fVarH7 = h(this.f2514b.M[3]);
                fVarH6.b(this);
                fVarH7.b(this);
                this.j = m.b.CENTER;
            }
            if (this.f2514b.T()) {
                c(this.k, this.f2520h, 1, this.l);
            }
        } else if (dVarArr2[2].f2425d != null) {
            f fVarH8 = h(dVarArr2[2]);
            if (fVarH8 != null) {
                b(this.f2520h, fVarH8, this.f2514b.M[2].c());
                c(this.f2521i, this.f2520h, 1, this.f2517e);
                if (this.f2514b.T()) {
                    c(this.k, this.f2520h, 1, this.l);
                }
                e.b bVar2 = this.f2516d;
                e.b bVar3 = e.b.MATCH_CONSTRAINT;
                if (bVar2 == bVar3 && this.f2514b.u() > 0.0f) {
                    j jVar = this.f2514b.f2443f;
                    if (jVar.f2516d == bVar3) {
                        jVar.f2517e.k.add(this.f2517e);
                        this.f2517e.l.add(this.f2514b.f2443f.f2517e);
                        this.f2517e.f2486a = this;
                    }
                }
            }
        } else if (dVarArr2[3].f2425d != null) {
            f fVarH9 = h(dVarArr2[3]);
            if (fVarH9 != null) {
                b(this.f2521i, fVarH9, -this.f2514b.M[3].c());
                c(this.f2520h, this.f2521i, -1, this.f2517e);
                if (this.f2514b.T()) {
                    c(this.k, this.f2520h, 1, this.l);
                }
            }
        } else if (dVarArr2[4].f2425d != null) {
            f fVarH10 = h(dVarArr2[4]);
            if (fVarH10 != null) {
                b(this.k, fVarH10, 0);
                c(this.f2520h, this.k, -1, this.l);
                c(this.f2521i, this.f2520h, 1, this.f2517e);
            }
        } else if (!(eVar5 instanceof b.e.b.k.i) && eVar5.H() != null) {
            b(this.f2520h, this.f2514b.H().f2444g.f2520h, this.f2514b.S());
            c(this.f2521i, this.f2520h, 1, this.f2517e);
            if (this.f2514b.T()) {
                c(this.k, this.f2520h, 1, this.l);
            }
            e.b bVar4 = this.f2516d;
            e.b bVar5 = e.b.MATCH_CONSTRAINT;
            if (bVar4 == bVar5 && this.f2514b.u() > 0.0f) {
                j jVar2 = this.f2514b.f2443f;
                if (jVar2.f2516d == bVar5) {
                    jVar2.f2517e.k.add(this.f2517e);
                    this.f2517e.l.add(this.f2514b.f2443f.f2517e);
                    this.f2517e.f2486a = this;
                }
            }
        }
        if (this.f2517e.l.size() == 0) {
            this.f2517e.f2488c = true;
        }
    }

    @Override // b.e.b.k.n.m
    public void e() {
        f fVar = this.f2520h;
        if (fVar.j) {
            this.f2514b.H0(fVar.f2492g);
        }
    }

    @Override // b.e.b.k.n.m
    void f() {
        this.f2515c = null;
        this.f2520h.c();
        this.f2521i.c();
        this.k.c();
        this.f2517e.c();
        this.f2519g = false;
    }

    @Override // b.e.b.k.n.m
    boolean m() {
        return this.f2516d != e.b.MATCH_CONSTRAINT || this.f2514b.n == 0;
    }

    void q() {
        this.f2519g = false;
        this.f2520h.c();
        this.f2520h.j = false;
        this.f2521i.c();
        this.f2521i.j = false;
        this.k.c();
        this.k.j = false;
        this.f2517e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f2514b.s();
    }
}
