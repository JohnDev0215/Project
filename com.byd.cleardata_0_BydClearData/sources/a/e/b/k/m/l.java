package a.e.b.k.m;

import a.e.b.k.e;
import a.e.b.k.m.f;
import a.e.b.k.m.m;

/* JADX INFO: loaded from: classes.dex */
public class l extends m {
    public f k;
    g l;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f246a;

        static {
            int[] iArr = new int[m.b.values().length];
            f246a = iArr;
            try {
                iArr[m.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f246a[m.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f246a[m.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public l(a.e.b.k.e eVar) {
        super(eVar);
        f fVar = new f(this);
        this.k = fVar;
        this.l = null;
        this.h.e = f.a.TOP;
        this.i.e = f.a.BOTTOM;
        fVar.e = f.a.BASELINE;
        this.f = 1;
    }

    @Override // a.e.b.k.m.m, a.e.b.k.m.d
    public void a(d dVar) {
        int i;
        float fR;
        int i2 = a.f246a[this.j.ordinal()];
        if (i2 == 1) {
            p(dVar);
        } else if (i2 == 2) {
            o(dVar);
        } else if (i2 == 3) {
            a.e.b.k.e eVar = this.f248b;
            n(dVar, eVar.D, eVar.F, 1);
            return;
        }
        g gVar = this.e;
        if (gVar.f236c && !gVar.j && this.f250d == e.b.MATCH_CONSTRAINT) {
            a.e.b.k.e eVar2 = this.f248b;
            int i3 = eVar2.n;
            if (i3 == 2) {
                a.e.b.k.e eVarE = eVar2.E();
                if (eVarE != null) {
                    if (eVarE.g.e.j) {
                        i = (int) ((r7.g * this.f248b.u) + 0.5f);
                        this.e.d(i);
                    }
                }
            } else if (i3 == 3 && eVar2.f.e.j) {
                int iS = eVar2.s();
                if (iS == -1) {
                    fR = r7.f.e.g / this.f248b.r();
                    i = (int) (fR + 0.5f);
                    this.e.d(i);
                } else if (iS != 0) {
                    if (iS != 1) {
                        i = 0;
                        this.e.d(i);
                    }
                    fR = r7.f.e.g / this.f248b.r();
                    i = (int) (fR + 0.5f);
                    this.e.d(i);
                } else {
                    fR = r7.f.e.g * this.f248b.r();
                    i = (int) (fR + 0.5f);
                    this.e.d(i);
                }
            }
        }
        f fVar = this.h;
        if (fVar.f236c) {
            f fVar2 = this.i;
            if (fVar2.f236c) {
                if (fVar.j && fVar2.j && this.e.j) {
                    return;
                }
                if (!this.e.j && this.f250d == e.b.MATCH_CONSTRAINT) {
                    a.e.b.k.e eVar3 = this.f248b;
                    if (eVar3.m == 0 && !eVar3.V()) {
                        f fVar3 = this.h.l.get(0);
                        f fVar4 = this.i.l.get(0);
                        int i4 = fVar3.g;
                        f fVar5 = this.h;
                        int i5 = i4 + fVar5.f;
                        int i6 = fVar4.g + this.i.f;
                        fVar5.d(i5);
                        this.i.d(i6);
                        this.e.d(i6 - i5);
                        return;
                    }
                }
                if (!this.e.j && this.f250d == e.b.MATCH_CONSTRAINT && this.f247a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    f fVar6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f) - (fVar6.g + this.h.f);
                    g gVar2 = this.e;
                    int i8 = gVar2.m;
                    if (i7 < i8) {
                        gVar2.d(i7);
                    } else {
                        gVar2.d(i8);
                    }
                }
                if (this.e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    f fVar7 = this.h.l.get(0);
                    f fVar8 = this.i.l.get(0);
                    int i9 = fVar7.g + this.h.f;
                    int i10 = fVar8.g + this.i.f;
                    float fI = this.f248b.I();
                    if (fVar7 == fVar8) {
                        i9 = fVar7.g;
                        i10 = fVar8.g;
                        fI = 0.5f;
                    }
                    this.h.d((int) (i9 + 0.5f + (((i10 - i9) - this.e.g) * fI)));
                    this.i.d(this.h.g + this.e.g);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x02e3, code lost:
    
        if (r9.f248b.Q() != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02e5, code lost:
    
        r0 = r9.k;
        r1 = r9.h;
        r2 = r9.l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0370, code lost:
    
        if (r9.f248b.Q() != false) goto L111;
     */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03e9 A[PHI: r0
  0x03e9: PHI (r0v34 a.e.b.k.m.j) = (r0v23 a.e.b.k.m.j), (r0v50 a.e.b.k.m.j) binds: [B:151:0x03e7, B:125:0x033e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:167:? A[RETURN, SYNTHETIC] */
    @Override // a.e.b.k.m.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void d() {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.l.d():void");
    }

    @Override // a.e.b.k.m.m
    public void e() {
        f fVar = this.h;
        if (fVar.j) {
            this.f248b.D0(fVar.g);
        }
    }

    @Override // a.e.b.k.m.m
    void f() {
        this.f249c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // a.e.b.k.m.m
    boolean m() {
        return this.f250d != e.b.MATCH_CONSTRAINT || this.f248b.n == 0;
    }

    void q() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.k.c();
        this.k.j = false;
        this.e.j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f248b.p();
    }
}
