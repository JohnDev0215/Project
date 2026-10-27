package S8;

import q7.AbstractC2187a;
import q7.C2190d;
import q7.C2195i;
import q7.InterfaceC2191e;
import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: S8.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0721s extends AbstractC2187a implements InterfaceC2191e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final r f8135m = new r(C2190d.l, new A3.c(27));

    public AbstractC0721s() {
        super(C2190d.l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [q7.f] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    @Override // q7.AbstractC2187a, q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        ?? r32;
        A7.m.f(interfaceC2193g, "key");
        if (interfaceC2193g instanceof r) {
            r rVar = (r) interfaceC2193g;
            InterfaceC2193g interfaceC2193g2 = this.l;
            if (interfaceC2193g2 != rVar && rVar.f8132m != interfaceC2193g2) {
                return null;
            }
            InterfaceC2192f interfaceC2192f = (InterfaceC2192f) rVar.l.b(this);
            r32 = interfaceC2192f;
            if (interfaceC2192f == null) {
                return null;
            }
        } else {
            this = this;
            if (C2190d.l != interfaceC2193g) {
                r32 = 0;
            }
        }
        return r32;
    }

    public abstract void L(InterfaceC2194h interfaceC2194h, Runnable runnable);

    public void M(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        L(interfaceC2194h, runnable);
    }

    public boolean N(InterfaceC2194h interfaceC2194h) {
        return !(this instanceof s0);
    }

    public AbstractC0721s O(int i7, String str) {
        X8.a.c(i7);
        return new X8.g(this, i7, str);
    }

    @Override // q7.AbstractC2187a, q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        A7.m.f(interfaceC2193g, "key");
        boolean z6 = interfaceC2193g instanceof r;
        C2195i c2195i = C2195i.l;
        if (z6) {
            r rVar = (r) interfaceC2193g;
            InterfaceC2193g interfaceC2193g2 = this.l;
            if ((interfaceC2193g2 != rVar && rVar.f8132m != interfaceC2193g2) || ((InterfaceC2192f) rVar.l.b(this)) == null) {
                return this;
            }
        } else if (C2190d.l != interfaceC2193g) {
            return this;
        }
        return c2195i;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + AbstractC0727y.n(this);
    }
}
