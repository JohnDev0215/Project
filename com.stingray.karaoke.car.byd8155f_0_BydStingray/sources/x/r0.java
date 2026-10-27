package x;

import T.C0735d;
import T.C0738e0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D0 f23613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0738e0 f23614b = C0735d.O(null, T.S.f8224q);
    public final /* synthetic */ x0 c;

    public r0(x0 x0Var, D0 d02, String str) {
        this.c = x0Var;
        this.f23613a = d02;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final q0 a(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        C0738e0 c0738e0 = this.f23614b;
        q0 q0Var = (q0) c0738e0.getValue();
        x0 x0Var = this.c;
        if (q0Var == null) {
            Object objB = interfaceC2782k2.b(x0Var.f23661a.P0());
            Object objB2 = interfaceC2782k2.b(x0Var.f23661a.P0());
            D0 d02 = this.f23613a;
            AbstractC2652s abstractC2652s = (AbstractC2652s) d02.f23394a.b(objB2);
            abstractC2652s.d();
            u0 u0Var = new u0(x0Var, objB, abstractC2652s, d02);
            q0Var = new q0(this, u0Var, interfaceC2782k, interfaceC2782k2);
            c0738e0.setValue(q0Var);
            x0Var.f23667i.add(u0Var);
        }
        q0Var.f23610n = (A7.o) interfaceC2782k2;
        q0Var.f23609m = (A7.o) interfaceC2782k;
        q0Var.b(x0Var.f());
        return q0Var;
    }
}
