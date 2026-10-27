package E0;

import androidx.compose.ui.node.Owner;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class J extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ N f1504m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Owner f1505n;
    public final /* synthetic */ long o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(N n10, Owner owner, long j3) {
        super(0);
        this.f1504m = n10;
        this.f1505n = owner;
        this.o = j3;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        S sO0;
        N n10 = this.f1504m;
        C0.U placementScope = null;
        if (AbstractC0156f.p(n10.f1555a)) {
            d0 d0Var = n10.a().f1650y;
            if (d0Var != null) {
                placementScope = d0Var.f1578t;
            }
        } else {
            d0 d0Var2 = n10.a().f1650y;
            if (d0Var2 != null && (sO0 = d0Var2.O0()) != null) {
                placementScope = sO0.f1578t;
            }
        }
        if (placementScope == null) {
            placementScope = this.f1505n.getPlacementScope();
        }
        S sO02 = n10.a().O0();
        A7.m.c(sO02);
        C0.U.e(placementScope, sO02, this.o);
        return m7.q.f19267a;
    }
}
