package N0;

import d0.C1053b;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B implements d0.n {
    public final /* synthetic */ A7.o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.o f4307m;

    /* JADX WARN: Multi-variable type inference failed */
    public B(InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k) {
        this.l = (A7.o) interfaceC2785n;
        this.f4307m = (A7.o) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.n] */
    @Override // d0.n
    public final Object v(C1053b c1053b, Object obj) {
        return this.l.invoke(c1053b, obj);
    }
}
