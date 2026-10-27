package q7;

import h6.q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: q7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2187a implements InterfaceC2192f {
    public final InterfaceC2193g l;

    public AbstractC2187a(InterfaceC2193g interfaceC2193g) {
        this.l = interfaceC2193g;
    }

    @Override // q7.InterfaceC2194h
    public InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        return q.r(this, interfaceC2193g);
    }

    @Override // q7.InterfaceC2192f
    public final InterfaceC2193g getKey() {
        return this.l;
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }

    @Override // q7.InterfaceC2194h
    public InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        return q.w(this, interfaceC2193g);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        return q.x(this, interfaceC2194h);
    }
}
