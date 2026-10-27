package N1;

import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g0 implements InterfaceC2192f {
    public final g0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Q f4525m;

    public g0(g0 g0Var, Q q4) {
        A7.m.f(q4, "instance");
        this.l = g0Var;
        this.f4525m = q4;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        return h6.q.r(this, interfaceC2193g);
    }

    public final void a(Q q4) {
        if (this.f4525m == q4) {
            throw new IllegalStateException("Calling updateData inside updateData on the same DataStore instance is not supported\nsince updates made in the parent updateData call will not be visible to the nested\nupdateData call. See https://issuetracker.google.com/issues/241760537 for details.");
        }
        g0 g0Var = this.l;
        if (g0Var != null) {
            g0Var.a(q4);
        }
    }

    @Override // q7.InterfaceC2192f
    public final InterfaceC2193g getKey() {
        return f0.l;
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        return h6.q.w(this, interfaceC2193g);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        return h6.q.x(this, interfaceC2194h);
    }
}
