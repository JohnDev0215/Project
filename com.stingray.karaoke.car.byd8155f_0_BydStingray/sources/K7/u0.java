package K7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class u0 extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A7.o f3841m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile Object f3842n = null;

    /* JADX WARN: Multi-variable type inference failed */
    public u0(InterfaceC2772a interfaceC2772a) {
        this.f3841m = (A7.o) interfaceC2772a;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.a] */
    public final Object a() {
        Object obj = this.f3842n;
        Object obj2 = v0.l;
        if (obj != null) {
            if (obj == obj2) {
                return null;
            }
            return obj;
        }
        Object objA = this.f3841m.a();
        if (objA != null) {
            obj2 = objA;
        }
        this.f3842n = obj2;
        return objA;
    }
}
