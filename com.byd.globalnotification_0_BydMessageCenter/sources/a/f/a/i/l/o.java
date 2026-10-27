package a.f.a.i.l;

import a.f.a.i.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.f.a.i.d f607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l f608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d.a f609d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f610e = new g(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f611f = 0;
    public boolean g = false;
    public f h = new f(this);
    public f i = new f(this);
    public a j = a.NONE;

    public enum a {
        NONE,
        START,
        END,
        CENTER
    }

    public o(a.f.a.i.d dVar) {
        this.f607b = dVar;
    }

    @Override // a.f.a.i.l.d
    public void a(d dVar) {
    }

    public final void b(f fVar, f fVar2, int i) {
        fVar.l.add(fVar2);
        fVar.f587f = i;
        fVar2.k.add(fVar);
    }

    public final void c(f fVar, f fVar2, int i, g gVar) {
        fVar.l.add(fVar2);
        fVar.l.add(this.f610e);
        fVar.h = i;
        fVar.i = gVar;
        fVar2.k.add(fVar);
        gVar.k.add(fVar);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        int iMax;
        a.f.a.i.d dVar = this.f607b;
        if (i2 == 0) {
            int i3 = dVar.x;
            iMax = Math.max(dVar.w, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax == i) {
                return i;
            }
        } else {
            int i4 = dVar.A;
            iMax = Math.max(dVar.z, i);
            if (i4 > 0) {
                iMax = Math.min(i4, i);
            }
            if (iMax == i) {
                return i;
            }
        }
        return iMax;
    }

    public final f h(a.f.a.i.c cVar) {
        o oVar;
        o oVar2;
        a.f.a.i.c cVar2 = cVar.f548f;
        if (cVar2 == null) {
            return null;
        }
        a.f.a.i.d dVar = cVar2.f546d;
        int iOrdinal = cVar2.f547e.ordinal();
        if (iOrdinal == 1) {
            oVar = dVar.f558d;
        } else {
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    oVar2 = dVar.f558d;
                } else {
                    if (iOrdinal != 4) {
                        if (iOrdinal != 5) {
                            return null;
                        }
                        return dVar.f559e.k;
                    }
                    oVar2 = dVar.f559e;
                }
                return oVar2.i;
            }
            oVar = dVar.f559e;
        }
        return oVar.h;
    }

    public final f i(a.f.a.i.c cVar, int i) {
        a.f.a.i.c cVar2 = cVar.f548f;
        if (cVar2 == null) {
            return null;
        }
        a.f.a.i.d dVar = cVar2.f546d;
        o oVar = i == 0 ? dVar.f558d : dVar.f559e;
        int iOrdinal = cVar.f548f.f547e.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            return oVar.h;
        }
        if (iOrdinal == 3 || iOrdinal == 4) {
            return oVar.i;
        }
        return null;
    }

    public long j() {
        if (this.f610e.j) {
            return r2.g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l(a.f.a.i.c r11, a.f.a.i.c r12, int r13) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.o.l(a.f.a.i.c, a.f.a.i.c, int):void");
    }
}
