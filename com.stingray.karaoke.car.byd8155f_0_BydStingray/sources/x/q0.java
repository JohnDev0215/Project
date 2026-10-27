package x;

import T.Q0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class q0 implements Q0 {
    public final u0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.o f23609m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public A7.o f23610n;
    public final /* synthetic */ r0 o;

    /* JADX WARN: Multi-variable type inference failed */
    public q0(r0 r0Var, u0 u0Var, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        this.o = r0Var;
        this.l = u0Var;
        this.f23609m = (A7.o) interfaceC2782k;
        this.f23610n = (A7.o) interfaceC2782k2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r1v4, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r4v1, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r4v4, types: [A7.o, z7.k] */
    public final void b(s0 s0Var) {
        Object objB = this.f23610n.b(s0Var.c());
        boolean zG = this.o.c.g();
        u0 u0Var = this.l;
        if (zG) {
            u0Var.h(this.f23610n.b(s0Var.a()), objB, (InterfaceC2615D) this.f23609m.b(s0Var));
        } else {
            u0Var.i(objB, (InterfaceC2615D) this.f23609m.b(s0Var));
        }
    }

    @Override // T.Q0
    public final Object getValue() {
        b(this.o.c.f());
        return this.l.f23639u.getValue();
    }
}
