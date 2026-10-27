package K7;

import W7.AbstractC0839c;
import java.lang.reflect.Type;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3819m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o0 f3820n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n0(o0 o0Var, int i7) {
        super(0);
        this.f3819m = i7;
        this.f3820n = o0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3819m) {
            case 0:
                t0 t0Var = this.f3820n.f3825b;
                Type type = t0Var != null ? (Type) t0Var.a() : null;
                A7.m.c(type);
                return AbstractC0839c.c(type);
            default:
                o0 o0Var = this.f3820n;
                return o0Var.d(o0Var.f3824a);
        }
    }
}
