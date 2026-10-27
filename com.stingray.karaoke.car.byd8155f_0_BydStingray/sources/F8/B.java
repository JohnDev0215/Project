package F8;

import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class B extends A {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final K f2427m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f2428n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final y8.n f2429p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final InterfaceC2782k f2430q;

    public B(K k10, List list, boolean z6, y8.n nVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(k10, "constructor");
        A7.m.f(list, "arguments");
        A7.m.f(nVar, "memberScope");
        this.f2427m = k10;
        this.f2428n = list;
        this.o = z6;
        this.f2429p = nVar;
        this.f2430q = interfaceC2782k;
        if (!(nVar instanceof H8.e) || (nVar instanceof H8.j)) {
            return;
        }
        throw new IllegalStateException("SimpleTypeImpl should not be created for error type: " + nVar + '\n' + k10);
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return this.f2427m;
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return this.o;
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = (A) this.f2430q.b(fVar);
        return a10 == null ? this : a10;
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = (A) this.f2430q.b(fVar);
        return a10 == null ? this : a10;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        if (z6 == this.o) {
            return this;
        }
        return z6 ? new C0290z(this, 1) : new C0290z(this, 0);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return h10.isEmpty() ? this : new C(this, h10);
    }

    @Override // F8.AbstractC0287w
    public final List o0() {
        return this.f2428n;
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        H.f2437m.getClass();
        return H.f2438n;
    }

    @Override // F8.AbstractC0287w
    public final y8.n z0() {
        return this.f2429p;
    }
}
