package g0;

import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public interface m extends o {
    @Override // g0.o
    default boolean e(InterfaceC2782k interfaceC2782k) {
        return ((Boolean) interfaceC2782k.b(this)).booleanValue();
    }

    @Override // g0.o
    default Object h(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }
}
