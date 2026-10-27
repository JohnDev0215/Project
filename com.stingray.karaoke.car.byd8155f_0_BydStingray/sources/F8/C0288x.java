package F8;

import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0288x extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2485m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ K f2486n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0288x(H h10, K k10, List list, y8.n nVar, boolean z6) {
        super(1);
        this.f2486n = k10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2485m) {
            case 0:
                A7.m.f((G8.f) obj, "refiner");
                this.f2486n.n();
                break;
            default:
                A7.m.f((G8.f) obj, "kotlinTypeRefiner");
                this.f2486n.n();
                break;
        }
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0288x(H h10, K k10, List list, boolean z6) {
        super(1);
        this.f2486n = k10;
    }
}
