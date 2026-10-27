package S8;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends k0 {
    public final InterfaceC2189c o;

    public e0(InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n) {
        super(interfaceC2194h, false);
        this.o = h6.e.g(this, this, interfaceC2785n);
    }

    @Override // S8.d0
    public final void Z() {
        try {
            X8.a.j(m7.q.f19267a, h6.e.k(this.o));
        } catch (Throwable th) {
            resumeWith(AbstractC1386c.f(th));
            throw th;
        }
    }
}
