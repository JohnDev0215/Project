package S0;

import E0.r0;
import j0.C1536d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class I extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7811m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ I(Object obj, int i7) {
        super(1);
        this.f7811m = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f7811m) {
            case 0:
                A7.k.q(obj);
                throw null;
            default:
                C1536d c1536d = (C1536d) obj;
                if (!c1536d.l.f14695x) {
                    return r0.f1697m;
                }
                c1536d.f16998y = null;
                return r0.l;
        }
    }
}
