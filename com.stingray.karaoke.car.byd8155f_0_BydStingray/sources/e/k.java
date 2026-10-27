package E;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class k extends G.E {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B5.u f1360b = new B5.u(1, (byte) 0);

    public k(InterfaceC2782k interfaceC2782k) {
        interfaceC2782k.b(this);
    }

    public static void m(k kVar, b0.b bVar) {
        kVar.getClass();
        kVar.f1360b.c(1, new h(null, new i(null, 0), new b0.b(-1010194746, new j(bVar, 0), true)));
    }

    @Override // G.E
    public final B5.u j() {
        return this.f1360b;
    }

    public final void n(int i7, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, b0.b bVar) {
        this.f1360b.c(i7, new h(interfaceC2782k, interfaceC2782k2, bVar));
    }
}
