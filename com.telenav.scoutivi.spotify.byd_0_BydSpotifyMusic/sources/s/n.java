package s;

import r.d;
import r.e;
import s.f;
import s.p;

/* JADX INFO: compiled from: VerticalWidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f f9434k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    g f9435l;

    /* JADX INFO: compiled from: VerticalWidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9436a;

        static {
            int[] iArr = new int[p.b.values().length];
            f9436a = iArr;
            try {
                iArr[p.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9436a[p.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9436a[p.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public n(r.e eVar) {
        super(eVar);
        f fVar = new f(this);
        this.f9434k = fVar;
        this.f9435l = null;
        this.f9459h.f9411e = f.a.TOP;
        this.f9460i.f9411e = f.a.BOTTOM;
        fVar.f9411e = f.a.BASELINE;
        this.f9457f = 1;
    }

    @Override // s.p, s.d
    public void a(d dVar) {
        float f7;
        float fX;
        float fX2;
        int i7;
        int i8 = a.f9436a[this.f9461j.ordinal()];
        if (i8 == 1) {
            p(dVar);
        } else if (i8 == 2) {
            o(dVar);
        } else if (i8 == 3) {
            r.e eVar = this.f9453b;
            n(dVar, eVar.R, eVar.T, 1);
            return;
        }
        g gVar = this.f9456e;
        if (gVar.f9409c && !gVar.f9416j && this.f9455d == e.b.MATCH_CONSTRAINT) {
            r.e eVar2 = this.f9453b;
            int i9 = eVar2.f9250x;
            if (i9 == 2) {
                r.e eVarM = eVar2.M();
                if (eVarM != null) {
                    if (eVarM.f9214f.f9456e.f9416j) {
                        this.f9456e.d((int) ((r7.f9413g * this.f9453b.E) + 0.5f));
                    }
                }
            } else if (i9 == 3 && eVar2.f9212e.f9456e.f9416j) {
                int iY = eVar2.y();
                if (iY == -1) {
                    r.e eVar3 = this.f9453b;
                    f7 = eVar3.f9212e.f9456e.f9413g;
                    fX = eVar3.x();
                } else if (iY == 0) {
                    fX2 = r7.f9212e.f9456e.f9413g * this.f9453b.x();
                    i7 = (int) (fX2 + 0.5f);
                    this.f9456e.d(i7);
                } else if (iY != 1) {
                    i7 = 0;
                    this.f9456e.d(i7);
                } else {
                    r.e eVar4 = this.f9453b;
                    f7 = eVar4.f9212e.f9456e.f9413g;
                    fX = eVar4.x();
                }
                fX2 = f7 / fX;
                i7 = (int) (fX2 + 0.5f);
                this.f9456e.d(i7);
            }
        }
        f fVar = this.f9459h;
        if (fVar.f9409c) {
            f fVar2 = this.f9460i;
            if (fVar2.f9409c) {
                if (fVar.f9416j && fVar2.f9416j && this.f9456e.f9416j) {
                    return;
                }
                if (!this.f9456e.f9416j && this.f9455d == e.b.MATCH_CONSTRAINT) {
                    r.e eVar5 = this.f9453b;
                    if (eVar5.f9248w == 0 && !eVar5.m0()) {
                        f fVar3 = this.f9459h.f9418l.get(0);
                        f fVar4 = this.f9460i.f9418l.get(0);
                        int i10 = fVar3.f9413g;
                        f fVar5 = this.f9459h;
                        int i11 = i10 + fVar5.f9412f;
                        int i12 = fVar4.f9413g + this.f9460i.f9412f;
                        fVar5.d(i11);
                        this.f9460i.d(i12);
                        this.f9456e.d(i12 - i11);
                        return;
                    }
                }
                if (!this.f9456e.f9416j && this.f9455d == e.b.MATCH_CONSTRAINT && this.f9452a == 1 && this.f9459h.f9418l.size() > 0 && this.f9460i.f9418l.size() > 0) {
                    f fVar6 = this.f9459h.f9418l.get(0);
                    int i13 = (this.f9460i.f9418l.get(0).f9413g + this.f9460i.f9412f) - (fVar6.f9413g + this.f9459h.f9412f);
                    g gVar2 = this.f9456e;
                    int i14 = gVar2.f9420m;
                    if (i13 < i14) {
                        gVar2.d(i13);
                    } else {
                        gVar2.d(i14);
                    }
                }
                if (this.f9456e.f9416j && this.f9459h.f9418l.size() > 0 && this.f9460i.f9418l.size() > 0) {
                    f fVar7 = this.f9459h.f9418l.get(0);
                    f fVar8 = this.f9460i.f9418l.get(0);
                    int i15 = fVar7.f9413g + this.f9459h.f9412f;
                    int i16 = fVar8.f9413g + this.f9460i.f9412f;
                    float fT = this.f9453b.T();
                    if (fVar7 == fVar8) {
                        i15 = fVar7.f9413g;
                        i16 = fVar8.f9413g;
                        fT = 0.5f;
                    }
                    this.f9459h.d((int) (i15 + 0.5f + (((i16 - i15) - this.f9456e.f9413g) * fT)));
                    this.f9460i.d(this.f9459h.f9413g + this.f9456e.f9413g);
                }
            }
        }
    }

    @Override // s.p
    void d() {
        r.e eVarM;
        r.e eVarM2;
        r.e eVar = this.f9453b;
        if (eVar.f9204a) {
            this.f9456e.d(eVar.z());
        }
        if (!this.f9456e.f9416j) {
            this.f9455d = this.f9453b.V();
            if (this.f9453b.b0()) {
                this.f9435l = new s.a(this);
            }
            e.b bVar = this.f9455d;
            if (bVar != e.b.MATCH_CONSTRAINT) {
                if (bVar == e.b.MATCH_PARENT && (eVarM2 = this.f9453b.M()) != null && eVarM2.V() == e.b.FIXED) {
                    int iZ = (eVarM2.z() - this.f9453b.R.f()) - this.f9453b.T.f();
                    b(this.f9459h, eVarM2.f9214f.f9459h, this.f9453b.R.f());
                    b(this.f9460i, eVarM2.f9214f.f9460i, -this.f9453b.T.f());
                    this.f9456e.d(iZ);
                    return;
                }
                if (this.f9455d == e.b.FIXED) {
                    this.f9456e.d(this.f9453b.z());
                }
            }
        } else if (this.f9455d == e.b.MATCH_PARENT && (eVarM = this.f9453b.M()) != null && eVarM.V() == e.b.FIXED) {
            b(this.f9459h, eVarM.f9214f.f9459h, this.f9453b.R.f());
            b(this.f9460i, eVarM.f9214f.f9460i, -this.f9453b.T.f());
            return;
        }
        g gVar = this.f9456e;
        boolean z6 = gVar.f9416j;
        if (z6) {
            r.e eVar2 = this.f9453b;
            if (eVar2.f9204a) {
                r.d[] dVarArr = eVar2.Y;
                if (dVarArr[2].f9198f != null && dVarArr[3].f9198f != null) {
                    if (eVar2.m0()) {
                        this.f9459h.f9412f = this.f9453b.Y[2].f();
                        this.f9460i.f9412f = -this.f9453b.Y[3].f();
                    } else {
                        f fVarH = h(this.f9453b.Y[2]);
                        if (fVarH != null) {
                            b(this.f9459h, fVarH, this.f9453b.Y[2].f());
                        }
                        f fVarH2 = h(this.f9453b.Y[3]);
                        if (fVarH2 != null) {
                            b(this.f9460i, fVarH2, -this.f9453b.Y[3].f());
                        }
                        this.f9459h.f9408b = true;
                        this.f9460i.f9408b = true;
                    }
                    if (this.f9453b.b0()) {
                        b(this.f9434k, this.f9459h, this.f9453b.r());
                        return;
                    }
                    return;
                }
                if (dVarArr[2].f9198f != null) {
                    f fVarH3 = h(dVarArr[2]);
                    if (fVarH3 != null) {
                        b(this.f9459h, fVarH3, this.f9453b.Y[2].f());
                        b(this.f9460i, this.f9459h, this.f9456e.f9413g);
                        if (this.f9453b.b0()) {
                            b(this.f9434k, this.f9459h, this.f9453b.r());
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (dVarArr[3].f9198f != null) {
                    f fVarH4 = h(dVarArr[3]);
                    if (fVarH4 != null) {
                        b(this.f9460i, fVarH4, -this.f9453b.Y[3].f());
                        b(this.f9459h, this.f9460i, -this.f9456e.f9413g);
                    }
                    if (this.f9453b.b0()) {
                        b(this.f9434k, this.f9459h, this.f9453b.r());
                        return;
                    }
                    return;
                }
                if (dVarArr[4].f9198f != null) {
                    f fVarH5 = h(dVarArr[4]);
                    if (fVarH5 != null) {
                        b(this.f9434k, fVarH5, 0);
                        b(this.f9459h, this.f9434k, -this.f9453b.r());
                        b(this.f9460i, this.f9459h, this.f9456e.f9413g);
                        return;
                    }
                    return;
                }
                if ((eVar2 instanceof r.i) || eVar2.M() == null || this.f9453b.q(d.b.CENTER).f9198f != null) {
                    return;
                }
                b(this.f9459h, this.f9453b.M().f9214f.f9459h, this.f9453b.a0());
                b(this.f9460i, this.f9459h, this.f9456e.f9413g);
                if (this.f9453b.b0()) {
                    b(this.f9434k, this.f9459h, this.f9453b.r());
                    return;
                }
                return;
            }
        }
        if (z6 || this.f9455d != e.b.MATCH_CONSTRAINT) {
            gVar.b(this);
        } else {
            r.e eVar3 = this.f9453b;
            int i7 = eVar3.f9250x;
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
            } else if (i7 == 3 && !eVar3.m0()) {
                r.e eVar4 = this.f9453b;
                if (eVar4.f9248w != 3) {
                    g gVar4 = eVar4.f9212e.f9456e;
                    this.f9456e.f9418l.add(gVar4);
                    gVar4.f9417k.add(this.f9456e);
                    g gVar5 = this.f9456e;
                    gVar5.f9408b = true;
                    gVar5.f9417k.add(this.f9459h);
                    this.f9456e.f9417k.add(this.f9460i);
                }
            }
        }
        r.e eVar5 = this.f9453b;
        r.d[] dVarArr2 = eVar5.Y;
        if (dVarArr2[2].f9198f != null && dVarArr2[3].f9198f != null) {
            if (eVar5.m0()) {
                this.f9459h.f9412f = this.f9453b.Y[2].f();
                this.f9460i.f9412f = -this.f9453b.Y[3].f();
            } else {
                f fVarH6 = h(this.f9453b.Y[2]);
                f fVarH7 = h(this.f9453b.Y[3]);
                if (fVarH6 != null) {
                    fVarH6.b(this);
                }
                if (fVarH7 != null) {
                    fVarH7.b(this);
                }
                this.f9461j = p.b.CENTER;
            }
            if (this.f9453b.b0()) {
                c(this.f9434k, this.f9459h, 1, this.f9435l);
            }
        } else if (dVarArr2[2].f9198f != null) {
            f fVarH8 = h(dVarArr2[2]);
            if (fVarH8 != null) {
                b(this.f9459h, fVarH8, this.f9453b.Y[2].f());
                c(this.f9460i, this.f9459h, 1, this.f9456e);
                if (this.f9453b.b0()) {
                    c(this.f9434k, this.f9459h, 1, this.f9435l);
                }
                e.b bVar2 = this.f9455d;
                e.b bVar3 = e.b.MATCH_CONSTRAINT;
                if (bVar2 == bVar3 && this.f9453b.x() > 0.0f) {
                    l lVar = this.f9453b.f9212e;
                    if (lVar.f9455d == bVar3) {
                        lVar.f9456e.f9417k.add(this.f9456e);
                        this.f9456e.f9418l.add(this.f9453b.f9212e.f9456e);
                        this.f9456e.f9407a = this;
                    }
                }
            }
        } else if (dVarArr2[3].f9198f != null) {
            f fVarH9 = h(dVarArr2[3]);
            if (fVarH9 != null) {
                b(this.f9460i, fVarH9, -this.f9453b.Y[3].f());
                c(this.f9459h, this.f9460i, -1, this.f9456e);
                if (this.f9453b.b0()) {
                    c(this.f9434k, this.f9459h, 1, this.f9435l);
                }
            }
        } else if (dVarArr2[4].f9198f != null) {
            f fVarH10 = h(dVarArr2[4]);
            if (fVarH10 != null) {
                b(this.f9434k, fVarH10, 0);
                c(this.f9459h, this.f9434k, -1, this.f9435l);
                c(this.f9460i, this.f9459h, 1, this.f9456e);
            }
        } else if (!(eVar5 instanceof r.i) && eVar5.M() != null) {
            b(this.f9459h, this.f9453b.M().f9214f.f9459h, this.f9453b.a0());
            c(this.f9460i, this.f9459h, 1, this.f9456e);
            if (this.f9453b.b0()) {
                c(this.f9434k, this.f9459h, 1, this.f9435l);
            }
            e.b bVar4 = this.f9455d;
            e.b bVar5 = e.b.MATCH_CONSTRAINT;
            if (bVar4 == bVar5 && this.f9453b.x() > 0.0f) {
                l lVar2 = this.f9453b.f9212e;
                if (lVar2.f9455d == bVar5) {
                    lVar2.f9456e.f9417k.add(this.f9456e);
                    this.f9456e.f9418l.add(this.f9453b.f9212e.f9456e);
                    this.f9456e.f9407a = this;
                }
            }
        }
        if (this.f9456e.f9418l.size() == 0) {
            this.f9456e.f9409c = true;
        }
    }

    @Override // s.p
    public void e() {
        f fVar = this.f9459h;
        if (fVar.f9416j) {
            this.f9453b.q1(fVar.f9413g);
        }
    }

    @Override // s.p
    void f() {
        this.f9454c = null;
        this.f9459h.c();
        this.f9460i.c();
        this.f9434k.c();
        this.f9456e.c();
        this.f9458g = false;
    }

    @Override // s.p
    boolean m() {
        return this.f9455d != e.b.MATCH_CONSTRAINT || this.f9453b.f9250x == 0;
    }

    void q() {
        this.f9458g = false;
        this.f9459h.c();
        this.f9459h.f9416j = false;
        this.f9460i.c();
        this.f9460i.f9416j = false;
        this.f9434k.c();
        this.f9434k.f9416j = false;
        this.f9456e.f9416j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f9453b.v();
    }
}
