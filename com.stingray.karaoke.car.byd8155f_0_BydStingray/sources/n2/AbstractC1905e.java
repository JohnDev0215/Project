package n2;

import e2.C1207o;
import e2.C1208p;
import e2.f0;
import h2.AbstractC1346c;
import l5.C1778f;
import u2.C2492C;

/* JADX INFO: renamed from: n2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1905e implements W {
    public w2.o B;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f19636m;
    public a0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f19638p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public o2.l f19639q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public h2.v f19640r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f19641s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public u2.a0 f19642t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1208p[] f19643u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f19644v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f19645w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f19647y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f19648z;
    public final Object l = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1778f f19637n = new C1778f(1, (byte) 0);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f19646x = Long.MIN_VALUE;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public f0 f19635A = f0.f14097a;

    public AbstractC1905e(int i7) {
        this.f19636m = i7;
    }

    public static int d(int i7, int i10, int i11, int i12) {
        return i7 | i10 | i11 | 128 | i12;
    }

    public static int h(int i7) {
        return i7 & 384;
    }

    public static int i(int i7) {
        return i7 & 64;
    }

    public static boolean o(int i7, boolean z6) {
        int i10 = i7 & 7;
        return i10 == 4 || (z6 && i10 == 3);
    }

    public final void A(C1208p[] c1208pArr, u2.a0 a0Var, long j3, long j10, C2492C c2492c) {
        AbstractC1346c.j(!this.f19647y);
        this.f19642t = a0Var;
        if (this.f19646x == Long.MIN_VALUE) {
            this.f19646x = j3;
        }
        this.f19643u = c1208pArr;
        this.f19644v = j10;
        x(c1208pArr, j3, j10, c2492c);
    }

    public final void B() {
        AbstractC1346c.j(this.f19641s == 0);
        this.f19637n.c();
        u();
    }

    public void C(float f10, float f11) {
    }

    public abstract int D(C1208p c1208p);

    public int E() {
        return 0;
    }

    @Override // n2.W
    public void b(int i7, Object obj) {
    }

    public final C1913m e(Exception exc, C1208p c1208p, boolean z6, int i7) {
        int iD;
        if (c1208p == null || this.f19648z) {
            iD = 4;
        } else {
            this.f19648z = true;
            try {
                iD = D(c1208p) & 7;
            } catch (C1913m unused) {
                iD = 4;
            } finally {
                this.f19648z = false;
            }
        }
        return new C1913m(1, exc, i7, l(), this.f19638p, c1208p, c1208p == null ? 4 : iD, z6);
    }

    public void g() {
    }

    public H k() {
        return null;
    }

    public abstract String l();

    public final boolean m() {
        return this.f19646x == Long.MIN_VALUE;
    }

    public abstract boolean n();

    public abstract boolean p();

    public abstract void q();

    public void r(boolean z6, boolean z10) {
    }

    public abstract void s(long j3, boolean z6);

    public void t() {
    }

    public void u() {
    }

    public void v() {
    }

    public void w() {
    }

    public void x(C1208p[] c1208pArr, long j3, long j10, C2492C c2492c) {
    }

    public final int y(C1778f c1778f, m2.g gVar, int i7) {
        u2.a0 a0Var = this.f19642t;
        a0Var.getClass();
        int iP = a0Var.p(c1778f, gVar, i7);
        if (iP == -4) {
            if (gVar.e(4)) {
                this.f19646x = Long.MIN_VALUE;
                return this.f19647y ? -4 : -3;
            }
            long j3 = gVar.f18957r + this.f19644v;
            gVar.f18957r = j3;
            this.f19646x = Math.max(this.f19646x, j3);
        } else if (iP == -5) {
            C1208p c1208p = (C1208p) c1778f.f18639n;
            c1208p.getClass();
            long j10 = c1208p.f14320s;
            if (j10 != Long.MAX_VALUE) {
                C1207o c1207oA = c1208p.a();
                c1207oA.f14250r = j10 + this.f19644v;
                c1778f.f18639n = new C1208p(c1207oA);
            }
        }
        return iP;
    }

    public abstract void z(long j3, long j10);
}
