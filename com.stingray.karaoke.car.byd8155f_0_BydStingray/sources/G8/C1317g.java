package g8;

import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0279n;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.H;
import F8.InterfaceC0276k;
import F8.Y;
import F8.a0;

/* JADX INFO: renamed from: g8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1317g extends AbstractC0279n implements InterfaceC0276k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f15006m;

    public C1317g(A a10) {
        A7.m.f(a10, "delegate");
        this.f15006m = a10;
    }

    @Override // F8.InterfaceC0276k
    public final a0 A(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "replacement");
        a0 a0VarK0 = abstractC0287w.K0();
        if (!Y.f(a0VarK0) && !Y.e(a0VarK0)) {
            return a0VarK0;
        }
        if (a0VarK0 instanceof A) {
            A a10 = (A) a0VarK0;
            A aL0 = a10.L0(false);
            return !Y.f(a10) ? aL0 : new C1317g(aL0);
        }
        if (!(a0VarK0 instanceof AbstractC0282q)) {
            throw new IllegalStateException(("Incorrect type: " + a0VarK0).toString());
        }
        AbstractC0282q abstractC0282q = (AbstractC0282q) a0VarK0;
        A a11 = abstractC0282q.f2477m;
        A aL02 = a11.L0(false);
        if (Y.f(a11)) {
            aL02 = new C1317g(aL02);
        }
        A a12 = abstractC0282q.f2478n;
        A aL03 = a12.L0(false);
        if (Y.f(a12)) {
            aL03 = new C1317g(aL03);
        }
        return AbstractC0268c.A(C0269d.j(aL02, aL03), AbstractC0268c.e(a0VarK0));
    }

    @Override // F8.AbstractC0279n, F8.AbstractC0287w
    public final boolean F0() {
        return false;
    }

    @Override // F8.A, F8.a0
    public final a0 N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return new C1317g(this.f15006m.N0(h10));
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        return z6 ? this.f15006m.L0(true) : this;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return new C1317g(this.f15006m.N0(h10));
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        return this.f15006m;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        return new C1317g(a10);
    }

    @Override // F8.InterfaceC0276k
    public final boolean W() {
        return true;
    }
}
