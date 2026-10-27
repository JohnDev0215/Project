package q7;

import A7.m;
import Q5.C0576h2;
import java.io.Serializable;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: q7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2188b implements InterfaceC2194h, Serializable {
    public final InterfaceC2194h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2192f f21257m;

    public C2188b(InterfaceC2192f interfaceC2192f, InterfaceC2194h interfaceC2194h) {
        m.f(interfaceC2194h, "left");
        m.f(interfaceC2192f, "element");
        this.l = interfaceC2194h;
        this.f21257m = interfaceC2192f;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        m.f(interfaceC2193g, "key");
        while (true) {
            InterfaceC2192f interfaceC2192fF = this.f21257m.F(interfaceC2193g);
            if (interfaceC2192fF != null) {
                return interfaceC2192fF;
            }
            InterfaceC2194h interfaceC2194h = this.l;
            if (!(interfaceC2194h instanceof C2188b)) {
                return interfaceC2194h.F(interfaceC2193g);
            }
            this = (C2188b) interfaceC2194h;
        }
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this != obj) {
            if (!(obj instanceof C2188b)) {
                return false;
            }
            C2188b c2188b = (C2188b) obj;
            c2188b.getClass();
            int i7 = 2;
            C2188b c2188b2 = c2188b;
            int i10 = 2;
            while (true) {
                InterfaceC2194h interfaceC2194h = c2188b2.l;
                c2188b2 = interfaceC2194h instanceof C2188b ? (C2188b) interfaceC2194h : null;
                if (c2188b2 == null) {
                    break;
                }
                i10++;
            }
            C2188b c2188b3 = this;
            while (true) {
                InterfaceC2194h interfaceC2194h2 = c2188b3.l;
                c2188b3 = interfaceC2194h2 instanceof C2188b ? (C2188b) interfaceC2194h2 : null;
                if (c2188b3 == null) {
                    break;
                }
                i7++;
            }
            if (i10 != i7) {
                return false;
            }
            while (true) {
                InterfaceC2192f interfaceC2192f = this.f21257m;
                if (!m.a(c2188b.F(interfaceC2192f.getKey()), interfaceC2192f)) {
                    zA = false;
                    break;
                }
                InterfaceC2194h interfaceC2194h3 = this.l;
                if (!(interfaceC2194h3 instanceof C2188b)) {
                    m.d(interfaceC2194h3, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                    InterfaceC2192f interfaceC2192f2 = (InterfaceC2192f) interfaceC2194h3;
                    zA = m.a(c2188b.F(interfaceC2192f2.getKey()), interfaceC2192f2);
                    break;
                }
                this = (C2188b) interfaceC2194h3;
            }
            if (!zA) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this.f21257m.hashCode() + this.l.hashCode();
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(this.l.j(obj, interfaceC2785n), this.f21257m);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        m.f(interfaceC2193g, "key");
        InterfaceC2192f interfaceC2192f = this.f21257m;
        InterfaceC2192f interfaceC2192fF = interfaceC2192f.F(interfaceC2193g);
        InterfaceC2194h interfaceC2194h = this.l;
        if (interfaceC2192fF != null) {
            return interfaceC2194h;
        }
        InterfaceC2194h interfaceC2194hK = interfaceC2194h.k(interfaceC2193g);
        return interfaceC2194hK == interfaceC2194h ? this : interfaceC2194hK == C2195i.l ? interfaceC2192f : new C2188b(interfaceC2192f, interfaceC2194hK);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("["), (String) j("", new C0576h2(10)), ']');
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        m.f(interfaceC2194h, "context");
        return interfaceC2194h == C2195i.l ? this : (InterfaceC2194h) interfaceC2194h.j(this, new C0576h2(11));
    }
}
