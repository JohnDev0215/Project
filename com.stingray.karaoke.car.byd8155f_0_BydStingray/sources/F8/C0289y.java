package F8;

import java.util.List;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: F8.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0289y extends AbstractC0287w {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final E8.l f2487m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final A7.o f2488n;
    public final E8.i o;

    /* JADX WARN: Multi-variable type inference failed */
    public C0289y(E8.l lVar, InterfaceC2772a interfaceC2772a) {
        A7.m.f(lVar, "storageManager");
        this.f2487m = lVar;
        this.f2488n = (A7.o) interfaceC2772a;
        this.o = new E8.i(lVar, interfaceC2772a);
    }

    @Override // F8.AbstractC0287w
    public final K B0() {
        return L0().B0();
    }

    @Override // F8.AbstractC0287w
    public final boolean F0() {
        return L0().F0();
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        return new C0289y(this.f2487m, new B8.A(fVar, 9, this));
    }

    @Override // F8.AbstractC0287w
    public final a0 K0() {
        AbstractC0287w abstractC0287wL0 = L0();
        while (abstractC0287wL0 instanceof C0289y) {
            abstractC0287wL0 = ((C0289y) abstractC0287wL0).L0();
        }
        A7.m.d(abstractC0287wL0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.UnwrappedType");
        return (a0) abstractC0287wL0;
    }

    public final AbstractC0287w L0() {
        return (AbstractC0287w) this.o.a();
    }

    @Override // F8.AbstractC0287w
    public final List o0() {
        return L0().o0();
    }

    @Override // F8.AbstractC0287w
    public final H t0() {
        return L0().t0();
    }

    public final String toString() {
        E8.i iVar = this.o;
        return (iVar.f1854n == E8.k.l || iVar.f1854n == E8.k.f1857m) ? "<Not computed yet>" : L0().toString();
    }

    @Override // F8.AbstractC0287w
    public final y8.n z0() {
        return L0().z0();
    }
}
