package y8;

import n7.AbstractC1999n;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24215m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r f24216n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(r rVar, int i7) {
        super(0);
        this.f24215m = i7;
        this.f24216n = rVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f24215m) {
            case 0:
                r rVar = this.f24216n;
                return AbstractC1999n.D(r8.m.i(rVar.f24218b), r8.m.j(rVar.f24218b));
            default:
                return AbstractC1999n.E(r8.m.h(this.f24216n.f24218b));
        }
    }
}
