package Q;

import A7.InterfaceC0059g;
import n0.C1891t;
import n0.InterfaceC1892u;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class R0 implements InterfaceC1892u, InterfaceC0059g {
    public final /* synthetic */ E.m l;

    public R0(E.m mVar) {
        this.l = mVar;
    }

    @Override // n0.InterfaceC1892u
    public final long a() {
        return ((C1891t) this.l.get()).f19453a;
    }

    @Override // A7.InterfaceC0059g
    public final m7.c b() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof InterfaceC1892u) || !(obj instanceof InterfaceC0059g)) {
            return false;
        }
        return this.l.equals(((InterfaceC0059g) obj).b());
    }

    public final int hashCode() {
        return this.l.hashCode();
    }
}
