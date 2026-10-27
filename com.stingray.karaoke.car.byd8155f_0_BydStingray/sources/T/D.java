package T;

import e0.AbstractC1155B;
import e0.AbstractC1156C;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class D extends AbstractC1155B implements Q0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2772a f8181m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final G0 f8182n;
    public C o = new C();

    public D(G0 g02, InterfaceC2772a interfaceC2772a) {
        this.f8181m = interfaceC2772a;
        this.f8182n = g02;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.o;
    }

    @Override // T.Q0
    public final Object getValue() {
        InterfaceC2782k interfaceC2782kF = AbstractC1174n.k().f();
        if (interfaceC2782kF != null) {
            interfaceC2782kF.b(this);
        }
        AbstractC1167g abstractC1167gK = AbstractC1174n.k();
        return i((C) AbstractC1174n.j(this.o, abstractC1167gK), abstractC1167gK, true, this.f8181m).f8179f;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T.C i(T.C r22, e0.AbstractC1167g r23, boolean r24, z7.InterfaceC2772a r25) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.D.i(T.C, e0.g, boolean, z7.a):T.C");
    }

    public final C j() {
        AbstractC1167g abstractC1167gK = AbstractC1174n.k();
        return i((C) AbstractC1174n.j(this.o, abstractC1167gK), abstractC1167gK, false, this.f8181m);
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState>");
        this.o = (C) abstractC1156C;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DerivedState(value=");
        C c = (C) AbstractC1174n.i(this.o);
        sb.append(c.c(this, AbstractC1174n.k()) ? String.valueOf(c.f8179f) : "<Not calculated>");
        sb.append(")@");
        sb.append(hashCode());
        return sb.toString();
    }
}
