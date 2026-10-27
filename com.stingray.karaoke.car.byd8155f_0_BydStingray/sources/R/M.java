package R;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f7274m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7275n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M(long j3, InterfaceC2785n interfaceC2785n, int i7) {
        super(2);
        this.f7274m = j3;
        this.f7275n = interfaceC2785n;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.o | 1);
        O.c(this.f7274m, this.f7275n, (C0761q) obj, iZ);
        return m7.q.f19267a;
    }
}
