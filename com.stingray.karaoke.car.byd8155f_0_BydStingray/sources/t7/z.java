package T7;

import Q7.AbstractC0669w;
import Q7.C0668v;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0671y;
import T.C0746i0;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class z extends AbstractC0794m implements InterfaceC0671y {
    public final E8.l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final N7.i f8921p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Map f8922q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final E f8923r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C0746i0 f8924s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Q7.E f8925t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f8926u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final E8.e f8927v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final m7.n f8928w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(C2061f c2061f, E8.l lVar, N7.i iVar, int i7) {
        super(R7.g.f7535a, c2061f);
        n7.v vVar = n7.v.l;
        A7.m.f(c2061f, "moduleName");
        this.o = lVar;
        this.f8921p = iVar;
        if (!c2061f.f20573m) {
            throw new IllegalArgumentException("Module name must be special: " + c2061f);
        }
        this.f8922q = vVar;
        E.f8772a.getClass();
        E e10 = (E) P(C.f8770b);
        this.f8923r = e10 == null ? D.f8771b : e10;
        this.f8926u = true;
        this.f8927v = lVar.b(new S0.p(this, 5));
        this.f8928w = h6.r.o(new N7.l(this, 2));
    }

    @Override // Q7.InterfaceC0671y
    public final Object P(B2.q qVar) {
        A7.m.f(qVar, "capability");
        Object obj = this.f8922q.get(qVar);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.G(this, obj);
    }

    @Override // Q7.InterfaceC0671y
    public final List Z() {
        if (this.f8924s != null) {
            return n7.u.l;
        }
        StringBuilder sb = new StringBuilder("Dependencies of module ");
        String str = getName().l;
        A7.m.e(str, "name.toString()");
        sb.append(str);
        sb.append(" were not set");
        throw new AssertionError(sb.toString());
    }

    public final void b1() {
        if (this.f8926u) {
            return;
        }
        if (P(AbstractC0669w.f7209a) != null) {
            throw new ClassCastException();
        }
        String str = "Accessing invalid module descriptor " + this;
        A7.m.f(str, "message");
        throw new C0668v(str);
    }

    @Override // Q7.InterfaceC0671y
    public final N7.i l() {
        return this.f8921p;
    }

    @Override // Q7.InterfaceC0671y
    public final Q7.I n0(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        b1();
        return (Q7.I) this.f8927v.b(c2058c);
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return null;
    }

    @Override // Q7.InterfaceC0671y
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        A7.m.f(c2058c, "fqName");
        A7.m.f(interfaceC2782k, "nameFilter");
        b1();
        b1();
        return ((C0793l) this.f8928w.getValue()).r(c2058c, interfaceC2782k);
    }

    @Override // T7.AbstractC0794m, A9.b
    public final String toString() {
        String strA1 = AbstractC0794m.a1(this);
        return this.f8926u ? strA1 : strA1.concat(" !isValid");
    }

    @Override // Q7.InterfaceC0671y
    public final boolean u0(InterfaceC0671y interfaceC0671y) {
        A7.m.f(interfaceC0671y, "targetModule");
        if (equals(interfaceC0671y)) {
            return true;
        }
        A7.m.c(this.f8924s);
        if (AbstractC1998m.T(n7.w.l, interfaceC0671y)) {
            return true;
        }
        Z();
        n7.u.l.contains(interfaceC0671y);
        return interfaceC0671y.Z().contains(this);
    }
}
