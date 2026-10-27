package o0;

import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: o0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2020p extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20212m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2021q f20213n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2020p(C2021q c2021q, int i7) {
        super(1);
        this.f20212m = i7;
        this.f20213n = c2021q;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f20212m) {
            case 0:
                double dDoubleValue = ((Number) obj).doubleValue();
                return Double.valueOf(this.f20213n.f20223n.c(AbstractC2673a.t(dDoubleValue, r8.f20215e, r8.f20216f)));
            default:
                return Double.valueOf(AbstractC2673a.t(this.f20213n.f20221k.c(((Number) obj).doubleValue()), r8.f20215e, r8.f20216f));
        }
    }
}
