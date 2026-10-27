package F8;

import java.util.List;
import q8.C2203g;

/* JADX INFO: renamed from: F8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0282q extends a0 implements I8.c {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f2477m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final A f2478n;

    public AbstractC0282q(A a10, A a11) {
        A7.m.f(a10, "lowerBound");
        A7.m.f(a11, "upperBound");
        this.f2477m = a10;
        this.f2478n = a11;
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return O0().B0();
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return O0().F0();
    }

    public abstract A O0();

    public abstract String P0(C2203g c2203g, C2203g c2203g2);

    @Override // F8.AbstractC0287w
    public final List o0() {
        return O0().o0();
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        return O0().t0();
    }

    public String toString() {
        return C2203g.f21280e.Y(this);
    }

    @Override // F8.AbstractC0287w
    public y8.n z0() {
        return O0().z0();
    }
}
