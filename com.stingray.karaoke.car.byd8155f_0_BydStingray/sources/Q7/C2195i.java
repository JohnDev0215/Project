package q7;

import A7.m;
import java.io.Serializable;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: q7.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2195i implements InterfaceC2194h, Serializable {
    public static final C2195i l = new C2195i();

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        m.f(interfaceC2193g, "key");
        return null;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return obj;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        m.f(interfaceC2193g, "key");
        return this;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        m.f(interfaceC2194h, "context");
        return interfaceC2194h;
    }
}
