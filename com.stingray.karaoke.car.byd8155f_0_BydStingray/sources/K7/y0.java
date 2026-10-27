package K7;

import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import T7.AbstractC0794m;
import b8.C1007b;
import b8.C1011f;
import b8.C1012g;
import h8.AbstractC1386c;
import j8.C1567l;
import j8.C1579y;
import java.lang.reflect.Method;
import o8.C2057b;
import o8.C2058c;
import p8.AbstractC2126a;
import p8.C2132g;
import p8.C2138m;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public abstract class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2057b f3854a = C2057b.j(new C2058c("java.lang.Void"));

    /* JADX WARN: Multi-variable type inference failed */
    public static C0386j a(InterfaceC0666t interfaceC0666t) {
        String strX = P8.n.x(interfaceC0666t);
        if (strX == null) {
            if (interfaceC0666t instanceof T7.I) {
                String strB = AbstractC2564d.k(interfaceC0666t).getName().b();
                A7.m.e(strB, "descriptor.propertyIfAccessor.name.asString()");
                strX = Z7.w.a(strB);
            } else if (interfaceC0666t instanceof T7.J) {
                String strB2 = AbstractC2564d.k(interfaceC0666t).getName().b();
                A7.m.e(strB2, "descriptor.propertyIfAccessor.name.asString()");
                strX = Z7.w.b(strB2);
            } else {
                strX = ((AbstractC0794m) interfaceC0666t).getName().b();
                A7.m.e(strX, "descriptor.name.asString()");
            }
        }
        return new C0386j(new n8.e(strX, h6.f.c(interfaceC0666t, 1)));
    }

    public static v0 b(Q7.L l) {
        A7.m.f(l, "possiblyOverriddenProperty");
        Q7.L lB1 = ((Q7.L) r8.d.t(l)).b1();
        A7.m.e(lB1, "unwrapFakeOverride(possi…rriddenProperty).original");
        if (lB1 instanceof D8.t) {
            D8.t tVar = (D8.t) lB1;
            C2138m c2138m = m8.k.d;
            A7.m.e(c2138m, "propertySignature");
            j8.G g7 = tVar.f1272M;
            m8.e eVar = (m8.e) AbstractC1386c.h(g7, c2138m);
            if (eVar != null) {
                return new C0389m(lB1, g7, eVar, tVar.f1273N, tVar.f1274O);
            }
        } else if (lB1 instanceof C1012g) {
            Q7.N nG = ((C1012g) lB1).g();
            V7.g gVar = nG instanceof V7.g ? (V7.g) nG : null;
            W7.r rVar = gVar != null ? gVar.l : null;
            if (rVar instanceof W7.t) {
                return new C0387k(((W7.t) rVar).f9897a);
            }
            if (!(rVar instanceof W7.w)) {
                throw new r0("Incorrect resolution sequence for Java field " + lB1 + " (source = " + rVar + ')');
            }
            Method method = ((W7.w) rVar).f9899a;
            T7.J jD = lB1.d();
            Q7.N nG2 = jD != null ? jD.g() : null;
            V7.g gVar2 = nG2 instanceof V7.g ? (V7.g) nG2 : null;
            W7.r rVar2 = gVar2 != null ? gVar2.l : null;
            W7.w wVar = rVar2 instanceof W7.w ? (W7.w) rVar2 : null;
            return new C0388l(method, wVar != null ? wVar.f9899a : null);
        }
        T7.I iC = lB1.c();
        A7.m.c(iC);
        C0386j c0386jA = a(iC);
        T7.J jD2 = lB1.d();
        return new C0390n(c0386jA, jD2 != null ? a(jD2) : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static v0 c(InterfaceC0666t interfaceC0666t) {
        Method method;
        A7.m.f(interfaceC0666t, "possiblySubstitutedFunction");
        InterfaceC0666t interfaceC0666tB1 = ((InterfaceC0666t) r8.d.t(interfaceC0666t)).b1();
        A7.m.e(interfaceC0666tB1, "unwrapFakeOverride(possi…titutedFunction).original");
        if (interfaceC0666tB1 instanceof D8.b) {
            D8.b bVar = (D8.b) interfaceC0666tB1;
            AbstractC2126a abstractC2126aO = bVar.O();
            if (abstractC2126aO instanceof C1579y) {
                C2132g c2132g = n8.h.f20171a;
                n8.e eVarC = n8.h.c((C1579y) abstractC2126aO, bVar.G0(), bVar.r0());
                if (eVarC != null) {
                    return new C0386j(eVarC);
                }
            }
            if (abstractC2126aO instanceof C1567l) {
                C2132g c2132g2 = n8.h.f20171a;
                n8.e eVarA = n8.h.a((C1567l) abstractC2126aO, bVar.G0(), bVar.r0());
                if (eVarA != null) {
                    InterfaceC0657j interfaceC0657jO = interfaceC0666t.o();
                    A7.m.e(interfaceC0657jO, "possiblySubstitutedFunction.containingDeclaration");
                    return r8.h.b(interfaceC0657jO) ? new C0386j(eVarA) : new C0385i(eVarA);
                }
            }
            return a(interfaceC0666tB1);
        }
        if (interfaceC0666tB1 instanceof C1011f) {
            Q7.N nG = ((C1011f) interfaceC0666tB1).g();
            V7.g gVar = nG instanceof V7.g ? (V7.g) nG : null;
            W7.r rVar = gVar != null ? gVar.l : null;
            W7.w wVar = rVar instanceof W7.w ? (W7.w) rVar : null;
            if (wVar != null && (method = wVar.f9899a) != null) {
                return new C0384h(method);
            }
            throw new r0("Incorrect resolution sequence for Java method " + interfaceC0666tB1);
        }
        if (!(interfaceC0666tB1 instanceof C1007b)) {
            AbstractC0794m abstractC0794m = (AbstractC0794m) interfaceC0666tB1;
            if ((abstractC0794m.getName().equals(N7.o.c) && r8.m.n(interfaceC0666tB1)) || ((abstractC0794m.getName().equals(N7.o.f4868a) && r8.m.n(interfaceC0666tB1)) || (A7.m.a(abstractC0794m.getName(), P7.a.f5413e) && interfaceC0666tB1.D0().isEmpty()))) {
                return a(interfaceC0666tB1);
            }
            throw new r0("Unknown origin of " + interfaceC0666tB1 + " (" + interfaceC0666tB1.getClass() + ')');
        }
        Q7.N nG2 = ((C1007b) interfaceC0666tB1).g();
        V7.g gVar2 = nG2 instanceof V7.g ? (V7.g) nG2 : null;
        Object obj = gVar2 != null ? gVar2.l : null;
        if (obj instanceof W7.q) {
            return new C0383g(((W7.q) obj).f9895a);
        }
        if (obj instanceof W7.n) {
            W7.n nVar = (W7.n) obj;
            if (nVar.f9891a.isAnnotation()) {
                return new C0382f(nVar.f9891a);
            }
        }
        throw new r0("Incorrect resolution sequence for Java constructor " + interfaceC0666tB1 + " (" + obj + ')');
    }
}
