package u2;

import e2.C1207o;
import e2.C1208p;
import l5.C1778f;

/* JADX INFO: renamed from: u2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2499b implements a0 {
    public final a0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22796m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2500c f22797n;

    public C2499b(C2500c c2500c, a0 a0Var) {
        this.f22797n = c2500c;
        this.l = a0Var;
    }

    @Override // u2.a0
    public final boolean k() {
        return !this.f22797n.f() && this.l.k();
    }

    @Override // u2.a0
    public final int p(C1778f c1778f, m2.g gVar, int i7) {
        C2500c c2500c = this.f22797n;
        if (c2500c.f()) {
            return -3;
        }
        if (this.f22796m) {
            gVar.f15673m = 4;
            return -4;
        }
        long jL = c2500c.l();
        int iP = this.l.p(c1778f, gVar, i7);
        if (iP != -5) {
            long j3 = c2500c.f22801q;
            if (j3 == Long.MIN_VALUE || ((iP != -4 || gVar.f18957r < j3) && !(iP == -3 && jL == Long.MIN_VALUE && !gVar.f18956q))) {
                return iP;
            }
            gVar.f();
            gVar.f15673m = 4;
            this.f22796m = true;
            return -4;
        }
        C1208p c1208p = (C1208p) c1778f.f18639n;
        c1208p.getClass();
        int i10 = c1208p.f14299G;
        int i11 = c1208p.f14298F;
        if (i11 != 0 || i10 != 0) {
            if (c2500c.f22800p != 0) {
                i11 = 0;
            }
            if (c2500c.f22801q != Long.MIN_VALUE) {
                i10 = 0;
            }
            C1207o c1207oA = c1208p.a();
            c1207oA.f14230E = i11;
            c1207oA.f14231F = i10;
            c1778f.f18639n = new C1208p(c1207oA);
        }
        return -5;
    }

    @Override // u2.a0
    public final void r() {
        this.l.r();
    }

    @Override // u2.a0
    public final int s(long j3) {
        if (this.f22797n.f()) {
            return -3;
        }
        return this.l.s(j3);
    }
}
