package S8;

import q7.C2190d;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class t0 extends X8.q {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ThreadLocal f8138p;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public t0(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h) {
        u0 u0Var = u0.l;
        super(interfaceC2189c, interfaceC2194h.F(u0Var) == null ? interfaceC2194h.v(u0Var) : interfaceC2194h);
        this.f8138p = new ThreadLocal();
        if (interfaceC2189c.getContext().F(C2190d.l) instanceof AbstractC0721s) {
            return;
        }
        Object objN = X8.a.n(interfaceC2194h, null);
        X8.a.i(interfaceC2194h, objN);
        k0(interfaceC2194h, objN);
    }

    public final boolean j0() {
        boolean z6 = this.threadLocalIsSet && this.f8138p.get() == null;
        this.f8138p.remove();
        return !z6;
    }

    public final void k0(InterfaceC2194h interfaceC2194h, Object obj) {
        this.threadLocalIsSet = true;
        this.f8138p.set(new m7.i(interfaceC2194h, obj));
    }

    @Override // X8.q, S8.d0
    public final void t(Object obj) {
        if (this.threadLocalIsSet) {
            m7.i iVar = (m7.i) this.f8138p.get();
            if (iVar != null) {
                X8.a.i((InterfaceC2194h) iVar.l, iVar.f19259m);
            }
            this.f8138p.remove();
        }
        Object objY = AbstractC0727y.y(obj);
        InterfaceC2189c interfaceC2189c = this.o;
        InterfaceC2194h context = interfaceC2189c.getContext();
        Object objN = X8.a.n(context, null);
        t0 t0VarE = objN != X8.a.d ? AbstractC0727y.E(interfaceC2189c, context, objN) : null;
        try {
            this.o.resumeWith(objY);
        } finally {
            if (t0VarE == null || t0VarE.j0()) {
                X8.a.i(context, objN);
            }
        }
    }
}
