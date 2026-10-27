package b.e.b.k.n;

import b.e.b.k.d;
import b.e.b.k.e;

/* JADX INFO: compiled from: WidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class m implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    b.e.b.k.e f2514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    k f2515c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e.b f2516d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    g f2517e = new g(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2518f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f2519g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f f2520h = new f(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f2521i = new f(this);
    protected b j = b.NONE;

    /* JADX INFO: compiled from: WidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2522a;

        static {
            int[] iArr = new int[d.b.values().length];
            f2522a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2522a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2522a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2522a[d.b.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2522a[d.b.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: WidgetRun.java */
    enum b {
        NONE,
        START,
        END,
        CENTER
    }

    public m(b.e.b.k.e eVar) {
        this.f2514b = eVar;
    }

    private void l(int i2, int i3) {
        int i4 = this.f2513a;
        if (i4 == 0) {
            this.f2517e.d(g(i3, i2));
            return;
        }
        if (i4 == 1) {
            this.f2517e.d(Math.min(g(this.f2517e.m, i2), i3));
            return;
        }
        if (i4 == 2) {
            b.e.b.k.e eVarH = this.f2514b.H();
            if (eVarH != null) {
                if ((i2 == 0 ? eVarH.f2443f : eVarH.f2444g).f2517e.j) {
                    b.e.b.k.e eVar = this.f2514b;
                    this.f2517e.d(g((int) ((r9.f2492g * (i2 == 0 ? eVar.r : eVar.u)) + 0.5f), i2));
                    return;
                }
                return;
            }
            return;
        }
        if (i4 != 3) {
            return;
        }
        b.e.b.k.e eVar2 = this.f2514b;
        m mVar = eVar2.f2443f;
        e.b bVar = mVar.f2516d;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        if (bVar == bVar2 && mVar.f2513a == 3) {
            l lVar = eVar2.f2444g;
            if (lVar.f2516d == bVar2 && lVar.f2513a == 3) {
                return;
            }
        }
        if (i2 == 0) {
            mVar = eVar2.f2444g;
        }
        if (mVar.f2517e.j) {
            float fU = eVar2.u();
            this.f2517e.d(i2 == 1 ? (int) ((mVar.f2517e.f2492g / fU) + 0.5f) : (int) ((fU * mVar.f2517e.f2492g) + 0.5f));
        }
    }

    @Override // b.e.b.k.n.d
    public void a(d dVar) {
    }

    protected final void b(f fVar, f fVar2, int i2) {
        fVar.l.add(fVar2);
        fVar.f2491f = i2;
        fVar2.k.add(fVar);
    }

    protected final void c(f fVar, f fVar2, int i2, g gVar) {
        fVar.l.add(fVar2);
        fVar.l.add(this.f2517e);
        fVar.f2493h = i2;
        fVar.f2494i = gVar;
        fVar2.k.add(fVar);
        gVar.k.add(fVar);
    }

    abstract void d();

    abstract void e();

    abstract void f();

    protected final int g(int i2, int i3) {
        int iMax;
        if (i3 == 0) {
            b.e.b.k.e eVar = this.f2514b;
            int i4 = eVar.q;
            iMax = Math.max(eVar.p, i2);
            if (i4 > 0) {
                iMax = Math.min(i4, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        } else {
            b.e.b.k.e eVar2 = this.f2514b;
            int i5 = eVar2.t;
            iMax = Math.max(eVar2.s, i2);
            if (i5 > 0) {
                iMax = Math.min(i5, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        }
        return iMax;
    }

    protected final f h(b.e.b.k.d dVar) {
        b.e.b.k.d dVar2 = dVar.f2425d;
        if (dVar2 == null) {
            return null;
        }
        b.e.b.k.e eVar = dVar2.f2423b;
        int i2 = a.f2522a[dVar2.f2424c.ordinal()];
        if (i2 == 1) {
            return eVar.f2443f.f2520h;
        }
        if (i2 == 2) {
            return eVar.f2443f.f2521i;
        }
        if (i2 == 3) {
            return eVar.f2444g.f2520h;
        }
        if (i2 == 4) {
            return eVar.f2444g.k;
        }
        if (i2 != 5) {
            return null;
        }
        return eVar.f2444g.f2521i;
    }

    protected final f i(b.e.b.k.d dVar, int i2) {
        b.e.b.k.d dVar2 = dVar.f2425d;
        if (dVar2 == null) {
            return null;
        }
        b.e.b.k.e eVar = dVar2.f2423b;
        m mVar = i2 == 0 ? eVar.f2443f : eVar.f2444g;
        int i3 = a.f2522a[dVar2.f2424c.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return mVar.f2521i;
        }
        return mVar.f2520h;
    }

    public long j() {
        if (this.f2517e.j) {
            return r0.f2492g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f2519g;
    }

    abstract boolean m();

    protected void n(d dVar, b.e.b.k.d dVar2, b.e.b.k.d dVar3, int i2) {
        f fVarH = h(dVar2);
        f fVarH2 = h(dVar3);
        if (fVarH.j && fVarH2.j) {
            int iC = fVarH.f2492g + dVar2.c();
            int iC2 = fVarH2.f2492g - dVar3.c();
            int i3 = iC2 - iC;
            if (!this.f2517e.j && this.f2516d == e.b.MATCH_CONSTRAINT) {
                l(i2, i3);
            }
            g gVar = this.f2517e;
            if (gVar.j) {
                if (gVar.f2492g == i3) {
                    this.f2520h.d(iC);
                    this.f2521i.d(iC2);
                    return;
                }
                b.e.b.k.e eVar = this.f2514b;
                float fX = i2 == 0 ? eVar.x() : eVar.L();
                if (fVarH == fVarH2) {
                    iC = fVarH.f2492g;
                    iC2 = fVarH2.f2492g;
                    fX = 0.5f;
                }
                this.f2520h.d((int) (iC + 0.5f + (((iC2 - iC) - this.f2517e.f2492g) * fX)));
                this.f2521i.d(this.f2520h.f2492g + this.f2517e.f2492g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
