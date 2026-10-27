package a.f.a.i.l;

import a.f.a.i.d;
import a.f.a.i.l.f;

/* JADX INFO: loaded from: classes.dex */
public class m extends o {
    public f k;
    public g l;

    public m(a.f.a.i.d dVar) {
        super(dVar);
        f fVar = new f(this);
        this.k = fVar;
        this.l = null;
        this.h.f586e = f.a.TOP;
        this.i.f586e = f.a.BOTTOM;
        fVar.f586e = f.a.BASELINE;
        this.f611f = 1;
    }

    @Override // a.f.a.i.l.o, a.f.a.i.l.d
    public void a(d dVar) {
        float f2;
        int i;
        d.a aVar = d.a.MATCH_CONSTRAINT;
        int iOrdinal = this.j.ordinal();
        if (iOrdinal != 1 && iOrdinal != 2 && iOrdinal == 3) {
            a.f.a.i.d dVar2 = this.f607b;
            l(dVar2.M, dVar2.O, 1);
            return;
        }
        g gVar = this.f610e;
        if (gVar.f584c && !gVar.j && this.f609d == aVar) {
            a.f.a.i.d dVar3 = this.f607b;
            int i2 = dVar3.u;
            if (i2 == 2) {
                a.f.a.i.d dVar4 = dVar3.X;
                if (dVar4 != null) {
                    if (dVar4.f559e.f610e.j) {
                        gVar.c((int) ((r1.g * dVar3.B) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                if (dVar3.f558d.f610e.j) {
                    int i3 = dVar3.b0;
                    if (i3 == -1) {
                        f2 = r0.g / dVar3.a0;
                        i = (int) (f2 + 0.5f);
                        this.f610e.c(i);
                    } else if (i3 != 0) {
                        if (i3 != 1) {
                            i = 0;
                            this.f610e.c(i);
                        }
                        f2 = r0.g / dVar3.a0;
                        i = (int) (f2 + 0.5f);
                        this.f610e.c(i);
                    } else {
                        f2 = r0.g * dVar3.a0;
                        i = (int) (f2 + 0.5f);
                        this.f610e.c(i);
                    }
                }
            }
        }
        f fVar = this.h;
        if (fVar.f584c) {
            f fVar2 = this.i;
            if (fVar2.f584c) {
                if (fVar.j && fVar2.j && this.f610e.j) {
                    return;
                }
                if (!this.f610e.j && this.f609d == aVar) {
                    a.f.a.i.d dVar5 = this.f607b;
                    if (dVar5.t == 0 && !dVar5.B()) {
                        f fVar3 = this.h.l.get(0);
                        f fVar4 = this.i.l.get(0);
                        int i4 = fVar3.g;
                        f fVar5 = this.h;
                        int i5 = i4 + fVar5.f587f;
                        int i6 = fVar4.g + this.i.f587f;
                        fVar5.c(i5);
                        this.i.c(i6);
                        this.f610e.c(i6 - i5);
                        return;
                    }
                }
                if (!this.f610e.j && this.f609d == aVar && this.f606a == 1 && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    f fVar6 = this.h.l.get(0);
                    int i7 = (this.i.l.get(0).g + this.i.f587f) - (fVar6.g + this.h.f587f);
                    g gVar2 = this.f610e;
                    int i8 = gVar2.m;
                    if (i7 < i8) {
                        gVar2.c(i7);
                    } else {
                        gVar2.c(i8);
                    }
                }
                if (this.f610e.j && this.h.l.size() > 0 && this.i.l.size() > 0) {
                    f fVar7 = this.h.l.get(0);
                    f fVar8 = this.i.l.get(0);
                    int i9 = fVar7.g;
                    int i10 = this.h.f587f + i9;
                    int i11 = fVar8.g;
                    int i12 = this.i.f587f + i11;
                    float f3 = this.f607b.i0;
                    if (fVar7 == fVar8) {
                        f3 = 0.5f;
                    } else {
                        i9 = i10;
                        i11 = i12;
                    }
                    this.h.c((int) ((((i11 - i9) - this.f610e.g) * f3) + i9 + 0.5f));
                    this.i.c(this.h.g + this.f610e.g);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:121:0x032a, code lost:
    
        if (r10.f607b.G != false) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x032c, code lost:
    
        r0 = r10.k;
        r1 = r10.h;
        r2 = r10.l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x03c3, code lost:
    
        if (r10.f607b.G != false) goto L122;
     */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0433 A[PHI: r0
  0x0433: PHI (r0v31 a.f.a.i.l.k) = (r0v18 a.f.a.i.l.k), (r0v45 a.f.a.i.l.k) binds: [B:162:0x0431, B:136:0x038a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    @Override // a.f.a.i.l.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d() {
        /*
            Method dump skipped, instruction units count: 1116
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.m.d():void");
    }

    @Override // a.f.a.i.l.o
    public void e() {
        f fVar = this.h;
        if (fVar.j) {
            this.f607b.d0 = fVar.g;
        }
    }

    @Override // a.f.a.i.l.o
    public void f() {
        this.f608c = null;
        this.h.b();
        this.i.b();
        this.k.b();
        this.f610e.b();
        this.g = false;
    }

    @Override // a.f.a.i.l.o
    public boolean k() {
        return this.f609d != d.a.MATCH_CONSTRAINT || this.f607b.u == 0;
    }

    public void m() {
        this.g = false;
        this.h.b();
        this.h.j = false;
        this.i.b();
        this.i.j = false;
        this.k.b();
        this.k.j = false;
        this.f610e.j = false;
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("VerticalRun ");
        sbD.append(this.f607b.m0);
        return sbD.toString();
    }
}
