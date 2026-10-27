package g0;

import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public interface o {
    boolean e(InterfaceC2782k interfaceC2782k);

    Object h(Object obj, InterfaceC2785n interfaceC2785n);

    default o k(o oVar) {
        return oVar == l.f14684a ? this : new i(this, oVar);
    }
}
