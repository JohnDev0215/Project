package K8;

import A7.m;
import F8.K;
import F8.M;
import F8.O;
import F8.P;
import s8.InterfaceC2414b;

/* JADX INFO: loaded from: classes.dex */
public final class c extends M {
    @Override // F8.M
    public final O g(K k10) {
        m.f(k10, "key");
        InterfaceC2414b interfaceC2414b = k10 instanceof InterfaceC2414b ? (InterfaceC2414b) k10 : null;
        if (interfaceC2414b == null) {
            return null;
        }
        return interfaceC2414b.a().c() ? new P(3, interfaceC2414b.a().b()) : interfaceC2414b.a();
    }
}
