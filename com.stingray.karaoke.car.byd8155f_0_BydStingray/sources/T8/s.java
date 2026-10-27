package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0671y;

/* JADX INFO: loaded from: classes.dex */
public final class s extends n {
    public s(long j3) {
        super(Long.valueOf(j3));
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        A7.m.f(interfaceC0671y, "module");
        N7.i iVarL = interfaceC0671y.l();
        iVarL.getClass();
        return iVarL.r(N7.k.LONG);
    }

    @Override // t8.g
    public final String toString() {
        return ((Number) this.f22499a).longValue() + ".toLong()";
    }
}
