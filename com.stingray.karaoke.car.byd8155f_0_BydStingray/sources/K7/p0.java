package K7;

import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import h8.C1391h;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class p0 implements H7.y, A {
    public static final /* synthetic */ H7.w[] o;
    public final Q7.Q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t0 f3828m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q0 f3829n;

    static {
        A7.B b10 = A7.A.f443a;
        o = new H7.w[]{b10.h(new A7.s(b10.b(p0.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};
    }

    public p0(q0 q0Var, Q7.Q q4) {
        Class cls;
        C0401z c0401zF;
        Object objS;
        A7.m.f(q4, "descriptor");
        this.l = q4;
        this.f3828m = v0.n(null, new K(this, 2));
        if (q0Var == null) {
            InterfaceC0657j interfaceC0657jO = q4.o();
            A7.m.e(interfaceC0657jO, "descriptor.containingDeclaration");
            if (interfaceC0657jO instanceof InterfaceC0652e) {
                objS = f((InterfaceC0652e) interfaceC0657jO);
            } else {
                if (!(interfaceC0657jO instanceof InterfaceC0650c)) {
                    throw new r0("Unknown type parameter container: " + interfaceC0657jO);
                }
                InterfaceC0657j interfaceC0657jO2 = ((InterfaceC0650c) interfaceC0657jO).o();
                A7.m.e(interfaceC0657jO2, "declaration.containingDeclaration");
                if (interfaceC0657jO2 instanceof InterfaceC0652e) {
                    c0401zF = f((InterfaceC0652e) interfaceC0657jO2);
                } else {
                    D8.m mVar = interfaceC0657jO instanceof D8.m ? (D8.m) interfaceC0657jO : null;
                    if (mVar == null) {
                        throw new r0("Non-class callable descriptor must be deserialized: " + interfaceC0657jO);
                    }
                    D8.l lVarV = mVar.v();
                    C1391h c1391h = lVarV instanceof C1391h ? (C1391h) lVarV : null;
                    V7.c cVar = c1391h != null ? c1391h.f15612n : null;
                    V7.c cVar2 = cVar instanceof V7.c ? cVar : null;
                    if (cVar2 == null || (cls = cVar2.f9548a) == null) {
                        throw new r0("Container of deserialized member is not resolved: " + mVar);
                    }
                    c0401zF = (C0401z) n7.z.l(cls);
                }
                objS = interfaceC0657jO.S(new B2.O(c0401zF), m7.q.f19267a);
            }
            A7.m.e(objS, "when (val declaration = … $declaration\")\n        }");
            q0Var = (q0) objS;
        }
        this.f3829n = q0Var;
    }

    public static C0401z f(InterfaceC0652e interfaceC0652e) {
        Class clsJ = A0.j(interfaceC0652e);
        C0401z c0401z = (C0401z) (clsJ != null ? n7.z.l(clsJ) : null);
        if (c0401z != null) {
            return c0401z;
        }
        throw new r0("Type parameter container is not resolved: " + interfaceC0652e.o());
    }

    public final String d() {
        String strB = this.l.getName().b();
        A7.m.e(strB, "descriptor.name.asString()");
        return strB;
    }

    @Override // K7.A
    public final InterfaceC0654g e() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p0) {
            p0 p0Var = (p0) obj;
            if (A7.m.a(this.f3829n, p0Var.f3829n) && d().equals(p0Var.d())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return d().hashCode() + (this.f3829n.hashCode() * 31);
    }

    public final String toString() {
        H7.B b10;
        StringBuilder sb = new StringBuilder();
        int iC = AbstractC2641j.c(this.l.e0());
        if (iC == 0) {
            b10 = H7.B.l;
        } else if (iC == 1) {
            b10 = H7.B.f2841m;
        } else {
            if (iC != 2) {
                throw new A9.k();
            }
            b10 = H7.B.f2842n;
        }
        int iOrdinal = b10.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                sb.append("in ");
            } else {
                if (iOrdinal != 2) {
                    throw new A9.k();
                }
                sb.append("out ");
            }
        }
        sb.append(d());
        return sb.toString();
    }
}
