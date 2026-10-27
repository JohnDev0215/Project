package K7;

import H7.InterfaceC0317b;
import Q7.InterfaceC0650c;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class r implements InterfaceC0317b, q0 {
    public final t0 l;

    public r() {
        v0.n(null, new C0391o(this, 1));
        this.l = v0.n(null, new C0391o(this, 2));
        v0.n(null, new C0391o(this, 4));
        v0.n(null, new C0391o(this, 5));
        v0.n(null, new C0391o(this, 0));
    }

    public final Object h(Object... objArr) throws I7.a {
        try {
            return i().q(objArr);
        } catch (IllegalAccessException e10) {
            throw new I7.a(e10);
        }
    }

    public abstract L7.e i();

    public abstract E j();

    public abstract L7.e l();

    public abstract InterfaceC0650c n();

    public final List p() {
        Object objA = this.l.a();
        A7.m.e(objA, "_parameters()");
        return (List) objA;
    }

    public final boolean q() {
        return A7.m.a(getName(), "<init>") && j().d().isAnnotation();
    }

    public abstract boolean r();
}
