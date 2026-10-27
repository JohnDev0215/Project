package m1;

import l1.C1731b;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b extends l1.g {

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public int f18893n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public int f18894o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public o1.a f18895p0;

    @Override // l1.g, l1.C1731b, l1.h
    public final void a() {
        t();
        int iC = AbstractC2641j.c(this.f18893n0);
        int i7 = 1;
        if (iC != 1 && iC != 3) {
            i7 = iC != 4 ? iC != 5 ? 0 : 3 : 2;
        }
        o1.a aVar = this.f18895p0;
        aVar.f20233t0 = i7;
        aVar.f20235v0 = this.f18894o0;
    }

    @Override // l1.C1731b
    public final C1731b l(int i7) {
        this.f18894o0 = i7;
        return this;
    }

    @Override // l1.C1731b
    public final C1731b m(Float f10) {
        this.f18894o0 = this.f18431k0.c(f10);
        return this;
    }

    @Override // l1.g
    public final o1.i t() {
        if (this.f18895p0 == null) {
            this.f18895p0 = new o1.a();
        }
        return this.f18895p0;
    }
}
