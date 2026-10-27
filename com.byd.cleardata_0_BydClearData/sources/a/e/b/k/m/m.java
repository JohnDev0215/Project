package a.e.b.k.m;

import a.e.b.k.d;
import a.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class m implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a.e.b.k.e f248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    k f249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e.b f250d;
    g e = new g(this);
    public int f = 0;
    boolean g = false;
    public f h = new f(this);
    public f i = new f(this);
    protected b j = b.NONE;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f251a;

        static {
            int[] iArr = new int[d.b.values().length];
            f251a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f251a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f251a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f251a[d.b.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f251a[d.b.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    enum b {
        NONE,
        START,
        END,
        CENTER
    }

    public m(a.e.b.k.e eVar) {
        this.f248b = eVar;
    }

    private void l(int i, int i2) {
        g gVar;
        int iG;
        int i3 = this.f247a;
        if (i3 == 0) {
            gVar = this.e;
            iG = g(i2, i);
        } else if (i3 == 1) {
            int iG2 = g(this.e.m, i);
            gVar = this.e;
            iG = Math.min(iG2, i2);
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    return;
                }
                a.e.b.k.e eVar = this.f248b;
                m mVar = eVar.f;
                e.b bVar = mVar.f250d;
                e.b bVar2 = e.b.MATCH_CONSTRAINT;
                if (bVar == bVar2 && mVar.f247a == 3) {
                    l lVar = eVar.g;
                    if (lVar.f250d == bVar2 && lVar.f247a == 3) {
                        return;
                    }
                }
                if (i == 0) {
                    mVar = eVar.g;
                }
                if (mVar.e.j) {
                    float fR = eVar.r();
                    this.e.d(i == 1 ? (int) ((mVar.e.g / fR) + 0.5f) : (int) ((fR * mVar.e.g) + 0.5f));
                    return;
                }
                return;
            }
            a.e.b.k.e eVarE = this.f248b.E();
            if (eVarE == null) {
                return;
            }
            if (!(i == 0 ? eVarE.f : eVarE.g).e.j) {
                return;
            }
            a.e.b.k.e eVar2 = this.f248b;
            i2 = (int) ((r9.g * (i == 0 ? eVar2.r : eVar2.u)) + 0.5f);
            gVar = this.e;
            iG = g(i2, i);
        }
        gVar.d(iG);
    }

    @Override // a.e.b.k.m.d
    public void a(d dVar) {
    }

    protected final void b(f fVar, f fVar2, int i) {
        fVar.l.add(fVar2);
        fVar.f = i;
        fVar2.k.add(fVar);
    }

    protected final void c(f fVar, f fVar2, int i, g gVar) {
        fVar.l.add(fVar2);
        fVar.l.add(this.e);
        fVar.h = i;
        fVar.i = gVar;
        fVar2.k.add(fVar);
        gVar.k.add(fVar);
    }

    abstract void d();

    abstract void e();

    abstract void f();

    protected final int g(int i, int i2) {
        int iMax;
        if (i2 == 0) {
            a.e.b.k.e eVar = this.f248b;
            int i3 = eVar.q;
            iMax = Math.max(eVar.p, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            a.e.b.k.e eVar2 = this.f248b;
            int i4 = eVar2.t;
            iMax = Math.max(eVar2.s, i);
            if (i4 > 0) {
                iMax = Math.min(i4, i);
            }
            if (iMax == i) {
                return i;
            }
        }
        return iMax;
    }

    protected final f h(a.e.b.k.d dVar) {
        m mVar;
        m mVar2;
        a.e.b.k.d dVar2 = dVar.f207d;
        if (dVar2 == null) {
            return null;
        }
        a.e.b.k.e eVar = dVar2.f205b;
        int i = a.f251a[dVar2.f206c.ordinal()];
        if (i != 1) {
            if (i == 2) {
                mVar2 = eVar.f;
            } else if (i == 3) {
                mVar = eVar.g;
            } else {
                if (i == 4) {
                    return eVar.g.k;
                }
                if (i != 5) {
                    return null;
                }
                mVar2 = eVar.g;
            }
            return mVar2.i;
        }
        mVar = eVar.f;
        return mVar.h;
    }

    protected final f i(a.e.b.k.d dVar, int i) {
        a.e.b.k.d dVar2 = dVar.f207d;
        if (dVar2 == null) {
            return null;
        }
        a.e.b.k.e eVar = dVar2.f205b;
        m mVar = i == 0 ? eVar.f : eVar.g;
        int i2 = a.f251a[dVar2.f206c.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return mVar.i;
        }
        return mVar.h;
    }

    public long j() {
        if (this.e.j) {
            return r0.g;
        }
        return 0L;
    }

    public boolean k() {
        return this.g;
    }

    abstract boolean m();

    protected void n(d dVar, a.e.b.k.d dVar2, a.e.b.k.d dVar3, int i) {
        f fVar;
        f fVarH = h(dVar2);
        f fVarH2 = h(dVar3);
        if (fVarH.j && fVarH2.j) {
            int iB = fVarH.g + dVar2.b();
            int iB2 = fVarH2.g - dVar3.b();
            int i2 = iB2 - iB;
            if (!this.e.j && this.f250d == e.b.MATCH_CONSTRAINT) {
                l(i, i2);
            }
            g gVar = this.e;
            if (gVar.j) {
                if (gVar.g == i2) {
                    this.h.d(iB);
                    fVar = this.i;
                } else {
                    a.e.b.k.e eVar = this.f248b;
                    float fU = i == 0 ? eVar.u() : eVar.I();
                    if (fVarH == fVarH2) {
                        iB = fVarH.g;
                        iB2 = fVarH2.g;
                        fU = 0.5f;
                    }
                    this.h.d((int) (iB + 0.5f + (((iB2 - iB) - this.e.g) * fU)));
                    fVar = this.i;
                    iB2 = this.h.g + this.e.g;
                }
                fVar.d(iB2);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
