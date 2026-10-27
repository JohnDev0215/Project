package E0;

import n0.AbstractC1864N;
import n0.AbstractC1887p;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class M extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1553m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f1554n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ M(int i7, long j3, Object obj) {
        super(0);
        this.f1553m = i7;
        this.o = obj;
        this.f1554n = j3;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1553m) {
            case 0:
                S sO0 = ((N) this.o).a().O0();
                A7.m.c(sO0);
                sO0.b(this.f1554n);
                return m7.q.f19267a;
            default:
                return ((AbstractC1864N) ((AbstractC1887p) this.o)).b(this.f1554n);
        }
    }
}
