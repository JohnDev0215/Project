package A3;

import C0.U;
import C0.V;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V f401m;

    public /* synthetic */ r(V v10, int i7) {
        this.l = i7;
        this.f401m = v10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        U u4 = (U) obj;
        switch (this.l) {
            case 0:
                U.d(u4, this.f401m, 0, 0);
                break;
            default:
                U.f(u4, this.f401m, 0, 0);
                break;
        }
        return m7.q.f19267a;
    }
}
