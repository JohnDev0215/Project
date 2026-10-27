package F8;

import java.util.List;

/* JADX INFO: renamed from: F8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0279n extends A {
    @Override // F8.AbstractC0287w
    public final K B0() {
        return Q0().B0();
    }

    @Override // F8.AbstractC0287w
    public boolean F0() {
        return Q0().F0();
    }

    public abstract A Q0();

    @Override // F8.a0
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public A M0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A aQ0 = Q0();
        A7.m.f(aQ0, "type");
        return S0(aQ0);
    }

    public abstract AbstractC0279n S0(A a10);

    @Override // F8.AbstractC0287w
    public final List o0() {
        return Q0().o0();
    }

    @Override // F8.AbstractC0287w
    public H t0() {
        return Q0().t0();
    }

    @Override // F8.AbstractC0287w
    public final y8.n z0() {
        return Q0().z0();
    }
}
