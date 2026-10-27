package p1;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public abstract class p implements InterfaceC2077e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o1.d f20680b;
    public m c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2080h f20681e = new C2080h(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20682f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f20683g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C2079g f20684h = new C2079g(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2079g f20685i = new C2079g(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20686j = 1;

    public p(o1.d dVar) {
        this.f20680b = dVar;
    }

    public static void b(C2079g c2079g, C2079g c2079g2, int i7) {
        c2079g.l.add(c2079g2);
        c2079g.f20663f = i7;
        c2079g2.f20668k.add(c2079g);
    }

    public static C2079g h(o1.c cVar) {
        o1.c cVar2 = cVar.f20253f;
        if (cVar2 == null) {
            return null;
        }
        o1.d dVar = cVar2.d;
        int iC = AbstractC2641j.c(cVar2.f20252e);
        if (iC == 1) {
            return dVar.d.f20684h;
        }
        if (iC == 2) {
            return dVar.f20288e.f20684h;
        }
        if (iC == 3) {
            return dVar.d.f20685i;
        }
        if (iC == 4) {
            return dVar.f20288e.f20685i;
        }
        if (iC != 5) {
            return null;
        }
        return dVar.f20288e.f20674k;
    }

    public static C2079g i(o1.c cVar, int i7) {
        o1.c cVar2 = cVar.f20253f;
        if (cVar2 == null) {
            return null;
        }
        o1.d dVar = cVar2.d;
        p pVar = i7 == 0 ? dVar.d : dVar.f20288e;
        int iC = AbstractC2641j.c(cVar2.f20252e);
        if (iC == 1 || iC == 2) {
            return pVar.f20684h;
        }
        if (iC == 3 || iC == 4) {
            return pVar.f20685i;
        }
        return null;
    }

    public final void c(C2079g c2079g, C2079g c2079g2, int i7, C2080h c2080h) {
        c2079g.l.add(c2079g2);
        c2079g.l.add(this.f20681e);
        c2079g.f20665h = i7;
        c2079g.f20666i = c2080h;
        c2079g2.f20668k.add(c2079g);
        c2080h.f20668k.add(c2079g);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i7, int i10) {
        int iMax;
        if (i10 == 0) {
            o1.d dVar = this.f20680b;
            int i11 = dVar.f20317w;
            iMax = Math.max(dVar.f20316v, i7);
            if (i11 > 0) {
                iMax = Math.min(i11, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        } else {
            o1.d dVar2 = this.f20680b;
            int i12 = dVar2.f20320z;
            iMax = Math.max(dVar2.f20319y, i7);
            if (i12 > 0) {
                iMax = Math.min(i12, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        }
        return iMax;
    }

    public long j() {
        if (this.f20681e.f20667j) {
            return r2.f20664g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(o1.c r12, o1.c r13, int r14) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.p.l(o1.c, o1.c, int):void");
    }
}
