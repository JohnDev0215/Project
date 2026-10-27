package G8;

import F8.A;
import F8.H;
import F8.K;
import F8.O;
import F8.a0;
import java.util.List;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A implements I8.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2726m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f2727n;
    public final a0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final H f2728p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f2729q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f2730r;

    public h(int i7, i iVar, a0 a0Var, H h10, boolean z6, boolean z10) {
        A7.k.p(i7, "captureStatus");
        A7.m.f(iVar, "constructor");
        A7.m.f(h10, "attributes");
        this.f2726m = i7;
        this.f2727n = iVar;
        this.o = a0Var;
        this.f2728p = h10;
        this.f2729q = z6;
        this.f2730r = z10;
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return this.f2727n;
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return this.f2729q;
    }

    @Override // F8.A, F8.a0
    public final a0 L0(boolean z6) {
        return new h(this.f2726m, this.f2727n, this.o, this.f2728p, z6, 32);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        return new h(this.f2726m, this.f2727n, this.o, this.f2728p, z6, 32);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return new h(this.f2726m, this.f2727n, this.o, h10, this.f2729q, this.f2730r);
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final h I0(f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        i iVar = this.f2727n;
        iVar.getClass();
        O oD = iVar.f2731a.d(fVar);
        B8.A a10 = iVar.f2732b != null ? new B8.A(iVar, 10, fVar) : null;
        i iVar2 = iVar.c;
        if (iVar2 == null) {
            iVar2 = iVar;
        }
        i iVar3 = new i(oD, a10, iVar2, iVar.d);
        a0 a0Var = this.o;
        return new h(this.f2726m, iVar3, a0Var != null ? a0Var : null, this.f2728p, this.f2729q, 32);
    }

    @Override // F8.AbstractC0287w
    public final List o0() {
        return u.l;
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        return this.f2728p;
    }

    @Override // F8.AbstractC0287w
    public final y8.n z0() {
        return H8.i.a(1, true, new String[0]);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(int i7, i iVar, a0 a0Var, H h10, boolean z6, int i10) {
        if ((i10 & 8) != 0) {
            H.f2437m.getClass();
            h10 = H.f2438n;
        }
        this(i7, iVar, a0Var, h10, (i10 & 16) != 0 ? false : z6, false);
    }
}
