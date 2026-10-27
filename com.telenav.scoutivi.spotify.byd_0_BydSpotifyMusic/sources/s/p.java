package s;

import r.d;
import r.e;

/* JADX INFO: compiled from: WidgetRun.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    r.e f9453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    m f9454c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e.b f9455d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    g f9456e = new g(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9457f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f9458g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f f9459h = new f(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f9460i = new f(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected b f9461j = b.NONE;

    /* JADX INFO: compiled from: WidgetRun.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9462a;

        static {
            int[] iArr = new int[d.b.values().length];
            f9462a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9462a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9462a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9462a[d.b.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9462a[d.b.BOTTOM.ordinal()] = 5;
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

    public p(r.e eVar) {
        this.f9453b = eVar;
    }

    private void l(int i7, int i8) {
        int i9 = this.f9452a;
        if (i9 == 0) {
            this.f9456e.d(g(i8, i7));
            return;
        }
        if (i9 == 1) {
            this.f9456e.d(Math.min(g(this.f9456e.f9420m, i7), i8));
            return;
        }
        if (i9 == 2) {
            r.e eVarM = this.f9453b.M();
            if (eVarM != null) {
                if ((i7 == 0 ? eVarM.f9212e : eVarM.f9214f).f9456e.f9416j) {
                    r.e eVar = this.f9453b;
                    this.f9456e.d(g((int) ((r9.f9413g * (i7 == 0 ? eVar.B : eVar.E)) + 0.5f), i7));
                    return;
                }
                return;
            }
            return;
        }
        if (i9 != 3) {
            return;
        }
        r.e eVar2 = this.f9453b;
        p pVar = eVar2.f9212e;
        e.b bVar = pVar.f9455d;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        if (bVar == bVar2 && pVar.f9452a == 3) {
            n nVar = eVar2.f9214f;
            if (nVar.f9455d == bVar2 && nVar.f9452a == 3) {
                return;
            }
        }
        if (i7 == 0) {
            pVar = eVar2.f9214f;
        }
        if (pVar.f9456e.f9416j) {
            float fX = eVar2.x();
            this.f9456e.d(i7 == 1 ? (int) ((pVar.f9456e.f9413g / fX) + 0.5f) : (int) ((fX * pVar.f9456e.f9413g) + 0.5f));
        }
    }

    @Override // s.d
    public void a(d dVar) {
    }

    protected final void b(f fVar, f fVar2, int i7) {
        fVar.f9418l.add(fVar2);
        fVar.f9412f = i7;
        fVar2.f9417k.add(fVar);
    }

    protected final void c(f fVar, f fVar2, int i7, g gVar) {
        fVar.f9418l.add(fVar2);
        fVar.f9418l.add(this.f9456e);
        fVar.f9414h = i7;
        fVar.f9415i = gVar;
        fVar2.f9417k.add(fVar);
        gVar.f9417k.add(fVar);
    }

    abstract void d();

    abstract void e();

    abstract void f();

    protected final int g(int i7, int i8) {
        int iMax;
        if (i8 == 0) {
            r.e eVar = this.f9453b;
            int i9 = eVar.A;
            iMax = Math.max(eVar.f9254z, i7);
            if (i9 > 0) {
                iMax = Math.min(i9, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        } else {
            r.e eVar2 = this.f9453b;
            int i10 = eVar2.D;
            iMax = Math.max(eVar2.C, i7);
            if (i10 > 0) {
                iMax = Math.min(i10, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        }
        return iMax;
    }

    protected final f h(r.d dVar) {
        r.d dVar2 = dVar.f9198f;
        if (dVar2 == null) {
            return null;
        }
        r.e eVar = dVar2.f9196d;
        int i7 = a.f9462a[dVar2.f9197e.ordinal()];
        if (i7 == 1) {
            return eVar.f9212e.f9459h;
        }
        if (i7 == 2) {
            return eVar.f9212e.f9460i;
        }
        if (i7 == 3) {
            return eVar.f9214f.f9459h;
        }
        if (i7 == 4) {
            return eVar.f9214f.f9434k;
        }
        if (i7 != 5) {
            return null;
        }
        return eVar.f9214f.f9460i;
    }

    protected final f i(r.d dVar, int i7) {
        r.d dVar2 = dVar.f9198f;
        if (dVar2 == null) {
            return null;
        }
        r.e eVar = dVar2.f9196d;
        p pVar = i7 == 0 ? eVar.f9212e : eVar.f9214f;
        int i8 = a.f9462a[dVar2.f9197e.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 != 5) {
                        return null;
                    }
                }
            }
            return pVar.f9460i;
        }
        return pVar.f9459h;
    }

    public long j() {
        if (this.f9456e.f9416j) {
            return r0.f9413g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f9458g;
    }

    abstract boolean m();

    protected void n(d dVar, r.d dVar2, r.d dVar3, int i7) {
        f fVarH = h(dVar2);
        f fVarH2 = h(dVar3);
        if (fVarH.f9416j && fVarH2.f9416j) {
            int iF = fVarH.f9413g + dVar2.f();
            int iF2 = fVarH2.f9413g - dVar3.f();
            int i8 = iF2 - iF;
            if (!this.f9456e.f9416j && this.f9455d == e.b.MATCH_CONSTRAINT) {
                l(i7, i8);
            }
            g gVar = this.f9456e;
            if (gVar.f9416j) {
                if (gVar.f9413g == i8) {
                    this.f9459h.d(iF);
                    this.f9460i.d(iF2);
                    return;
                }
                r.e eVar = this.f9453b;
                float fA = i7 == 0 ? eVar.A() : eVar.T();
                if (fVarH == fVarH2) {
                    iF = fVarH.f9413g;
                    iF2 = fVarH2.f9413g;
                    fA = 0.5f;
                }
                this.f9459h.d((int) (iF + 0.5f + (((iF2 - iF) - this.f9456e.f9413g) * fA)));
                this.f9460i.d(this.f9459h.f9413g + this.f9456e.f9413g);
            }
        }
    }

    protected void o(d dVar) {
    }

    protected void p(d dVar) {
    }
}
