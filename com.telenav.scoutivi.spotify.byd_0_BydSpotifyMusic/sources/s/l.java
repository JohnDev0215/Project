package s;

import r.d;
import r.e;
import s.f;
import s.p;

/* JADX INFO: compiled from: HorizontalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static int[] f9424k = new int[2];

    /* JADX INFO: compiled from: HorizontalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9425a;

        static {
            int[] iArr = new int[p.b.values().length];
            f9425a = iArr;
            try {
                iArr[p.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9425a[p.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9425a[p.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public l(r.e eVar) {
        super(eVar);
        this.f9459h.f9411e = f.a.LEFT;
        this.f9460i.f9411e = f.a.RIGHT;
        this.f9457f = 0;
    }

    private void q(int[] iArr, int i7, int i8, int i9, int i10, float f7, int i11) {
        int i12 = i8 - i7;
        int i13 = i10 - i9;
        if (i11 != -1) {
            if (i11 == 0) {
                iArr[0] = (int) ((i13 * f7) + 0.5f);
                iArr[1] = i13;
                return;
            } else {
                if (i11 != 1) {
                    return;
                }
                iArr[0] = i12;
                iArr[1] = (int) ((i12 * f7) + 0.5f);
                return;
            }
        }
        int i14 = (int) ((i13 * f7) + 0.5f);
        int i15 = (int) ((i12 / f7) + 0.5f);
        if (i14 <= i12) {
            iArr[0] = i14;
            iArr[1] = i13;
        } else if (i15 <= i13) {
            iArr[0] = i12;
            iArr[1] = i15;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02de  */
    @Override // s.p, s.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(s.d r17) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s.l.a(s.d):void");
    }

    @Override // s.p
    void d() {
        r.e eVarM;
        r.e eVarM2;
        r.e eVar = this.f9453b;
        if (eVar.f9204a) {
            this.f9456e.d(eVar.Y());
        }
        if (this.f9456e.f9416j) {
            e.b bVar = this.f9455d;
            e.b bVar2 = e.b.MATCH_PARENT;
            if (bVar == bVar2 && (eVarM = this.f9453b.M()) != null && (eVarM.C() == e.b.FIXED || eVarM.C() == bVar2)) {
                b(this.f9459h, eVarM.f9212e.f9459h, this.f9453b.Q.f());
                b(this.f9460i, eVarM.f9212e.f9460i, -this.f9453b.S.f());
                return;
            }
        } else {
            e.b bVarC = this.f9453b.C();
            this.f9455d = bVarC;
            if (bVarC != e.b.MATCH_CONSTRAINT) {
                e.b bVar3 = e.b.MATCH_PARENT;
                if (bVarC == bVar3 && (eVarM2 = this.f9453b.M()) != null && (eVarM2.C() == e.b.FIXED || eVarM2.C() == bVar3)) {
                    int iY = (eVarM2.Y() - this.f9453b.Q.f()) - this.f9453b.S.f();
                    b(this.f9459h, eVarM2.f9212e.f9459h, this.f9453b.Q.f());
                    b(this.f9460i, eVarM2.f9212e.f9460i, -this.f9453b.S.f());
                    this.f9456e.d(iY);
                    return;
                }
                if (this.f9455d == e.b.FIXED) {
                    this.f9456e.d(this.f9453b.Y());
                }
            }
        }
        g gVar = this.f9456e;
        if (gVar.f9416j) {
            r.e eVar2 = this.f9453b;
            if (eVar2.f9204a) {
                r.d[] dVarArr = eVar2.Y;
                if (dVarArr[0].f9198f != null && dVarArr[1].f9198f != null) {
                    if (eVar2.k0()) {
                        this.f9459h.f9412f = this.f9453b.Y[0].f();
                        this.f9460i.f9412f = -this.f9453b.Y[1].f();
                        return;
                    }
                    f fVarH = h(this.f9453b.Y[0]);
                    if (fVarH != null) {
                        b(this.f9459h, fVarH, this.f9453b.Y[0].f());
                    }
                    f fVarH2 = h(this.f9453b.Y[1]);
                    if (fVarH2 != null) {
                        b(this.f9460i, fVarH2, -this.f9453b.Y[1].f());
                    }
                    this.f9459h.f9408b = true;
                    this.f9460i.f9408b = true;
                    return;
                }
                if (dVarArr[0].f9198f != null) {
                    f fVarH3 = h(dVarArr[0]);
                    if (fVarH3 != null) {
                        b(this.f9459h, fVarH3, this.f9453b.Y[0].f());
                        b(this.f9460i, this.f9459h, this.f9456e.f9413g);
                        return;
                    }
                    return;
                }
                if (dVarArr[1].f9198f != null) {
                    f fVarH4 = h(dVarArr[1]);
                    if (fVarH4 != null) {
                        b(this.f9460i, fVarH4, -this.f9453b.Y[1].f());
                        b(this.f9459h, this.f9460i, -this.f9456e.f9413g);
                        return;
                    }
                    return;
                }
                if ((eVar2 instanceof r.i) || eVar2.M() == null || this.f9453b.q(d.b.CENTER).f9198f != null) {
                    return;
                }
                b(this.f9459h, this.f9453b.M().f9212e.f9459h, this.f9453b.Z());
                b(this.f9460i, this.f9459h, this.f9456e.f9413g);
                return;
            }
        }
        if (this.f9455d == e.b.MATCH_CONSTRAINT) {
            r.e eVar3 = this.f9453b;
            int i7 = eVar3.f9248w;
            if (i7 == 2) {
                r.e eVarM3 = eVar3.M();
                if (eVarM3 != null) {
                    g gVar2 = eVarM3.f9214f.f9456e;
                    this.f9456e.f9418l.add(gVar2);
                    gVar2.f9417k.add(this.f9456e);
                    g gVar3 = this.f9456e;
                    gVar3.f9408b = true;
                    gVar3.f9417k.add(this.f9459h);
                    this.f9456e.f9417k.add(this.f9460i);
                }
            } else if (i7 == 3) {
                if (eVar3.f9250x == 3) {
                    this.f9459h.f9407a = this;
                    this.f9460i.f9407a = this;
                    n nVar = eVar3.f9214f;
                    nVar.f9459h.f9407a = this;
                    nVar.f9460i.f9407a = this;
                    gVar.f9407a = this;
                    if (eVar3.m0()) {
                        this.f9456e.f9418l.add(this.f9453b.f9214f.f9456e);
                        this.f9453b.f9214f.f9456e.f9417k.add(this.f9456e);
                        n nVar2 = this.f9453b.f9214f;
                        nVar2.f9456e.f9407a = this;
                        this.f9456e.f9418l.add(nVar2.f9459h);
                        this.f9456e.f9418l.add(this.f9453b.f9214f.f9460i);
                        this.f9453b.f9214f.f9459h.f9417k.add(this.f9456e);
                        this.f9453b.f9214f.f9460i.f9417k.add(this.f9456e);
                    } else if (this.f9453b.k0()) {
                        this.f9453b.f9214f.f9456e.f9418l.add(this.f9456e);
                        this.f9456e.f9417k.add(this.f9453b.f9214f.f9456e);
                    } else {
                        this.f9453b.f9214f.f9456e.f9418l.add(this.f9456e);
                    }
                } else {
                    g gVar4 = eVar3.f9214f.f9456e;
                    gVar.f9418l.add(gVar4);
                    gVar4.f9417k.add(this.f9456e);
                    this.f9453b.f9214f.f9459h.f9417k.add(this.f9456e);
                    this.f9453b.f9214f.f9460i.f9417k.add(this.f9456e);
                    g gVar5 = this.f9456e;
                    gVar5.f9408b = true;
                    gVar5.f9417k.add(this.f9459h);
                    this.f9456e.f9417k.add(this.f9460i);
                    this.f9459h.f9418l.add(this.f9456e);
                    this.f9460i.f9418l.add(this.f9456e);
                }
            }
        }
        r.e eVar4 = this.f9453b;
        r.d[] dVarArr2 = eVar4.Y;
        if (dVarArr2[0].f9198f != null && dVarArr2[1].f9198f != null) {
            if (eVar4.k0()) {
                this.f9459h.f9412f = this.f9453b.Y[0].f();
                this.f9460i.f9412f = -this.f9453b.Y[1].f();
                return;
            }
            f fVarH5 = h(this.f9453b.Y[0]);
            f fVarH6 = h(this.f9453b.Y[1]);
            if (fVarH5 != null) {
                fVarH5.b(this);
            }
            if (fVarH6 != null) {
                fVarH6.b(this);
            }
            this.f9461j = p.b.CENTER;
            return;
        }
        if (dVarArr2[0].f9198f != null) {
            f fVarH7 = h(dVarArr2[0]);
            if (fVarH7 != null) {
                b(this.f9459h, fVarH7, this.f9453b.Y[0].f());
                c(this.f9460i, this.f9459h, 1, this.f9456e);
                return;
            }
            return;
        }
        if (dVarArr2[1].f9198f != null) {
            f fVarH8 = h(dVarArr2[1]);
            if (fVarH8 != null) {
                b(this.f9460i, fVarH8, -this.f9453b.Y[1].f());
                c(this.f9459h, this.f9460i, -1, this.f9456e);
                return;
            }
            return;
        }
        if ((eVar4 instanceof r.i) || eVar4.M() == null) {
            return;
        }
        b(this.f9459h, this.f9453b.M().f9212e.f9459h, this.f9453b.Z());
        c(this.f9460i, this.f9459h, 1, this.f9456e);
    }

    @Override // s.p
    public void e() {
        f fVar = this.f9459h;
        if (fVar.f9416j) {
            this.f9453b.p1(fVar.f9413g);
        }
    }

    @Override // s.p
    void f() {
        this.f9454c = null;
        this.f9459h.c();
        this.f9460i.c();
        this.f9456e.c();
        this.f9458g = false;
    }

    @Override // s.p
    boolean m() {
        return this.f9455d != e.b.MATCH_CONSTRAINT || this.f9453b.f9248w == 0;
    }

    void r() {
        this.f9458g = false;
        this.f9459h.c();
        this.f9459h.f9416j = false;
        this.f9460i.c();
        this.f9460i.f9416j = false;
        this.f9456e.f9416j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f9453b.v();
    }
}
