package C0;

import q0.C2164c;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class V {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f871m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f872n = H7.H.c(0, 0);
    public long o = W.f874a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f873p = 0;

    public abstract int e0(C0107n c0107n);

    public int f0() {
        return (int) (this.f872n & 4294967295L);
    }

    public int h0() {
        return (int) (this.f872n >> 32);
    }

    public Object i() {
        return null;
    }

    public final void i0() {
        this.l = AbstractC2673a.v((int) (this.f872n >> 32), a1.a.j(this.o), a1.a.h(this.o));
        int iV = AbstractC2673a.v((int) (this.f872n & 4294967295L), a1.a.i(this.o), a1.a.g(this.o));
        this.f871m = iV;
        int i7 = this.l;
        long j3 = this.f872n;
        this.f873p = AbstractC2673a.d((i7 - ((int) (j3 >> 32))) / 2, (iV - ((int) (j3 & 4294967295L))) / 2);
    }

    public void k0(long j3, float f10, C2164c c2164c) {
        l0(j3, f10, null);
    }

    public abstract void l0(long j3, float f10, InterfaceC2782k interfaceC2782k);

    public final void n0(long j3) {
        if (a1.j.a(this.f872n, j3)) {
            return;
        }
        this.f872n = j3;
        i0();
    }

    public final void o0(long j3) {
        if (a1.a.b(this.o, j3)) {
            return;
        }
        this.o = j3;
        i0();
    }
}
