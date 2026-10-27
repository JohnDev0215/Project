package K7;

import A7.AbstractC0056d;
import A7.InterfaceC0057e;
import A7.InterfaceC0060h;
import F0.C0226s;
import F8.AbstractC0287w;
import F8.C0269d;
import H7.InterfaceC0318c;
import H7.InterfaceC0320e;
import H7.InterfaceC0321f;
import H7.InterfaceC0324i;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0666t;
import T.C0746i0;
import j8.C1556a;
import j8.C1579y;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import n7.AbstractC1998m;
import o8.C2058c;
import p8.AbstractC2126a;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;
import q8.C2203g;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public class w0 extends A7.B {
    public static E m(AbstractC0056d abstractC0056d) {
        InterfaceC0320e interfaceC0320eH = abstractC0056d.h();
        return interfaceC0320eH instanceof E ? (E) interfaceC0320eH : C0380d.f3781m;
    }

    @Override // A7.B
    public final InterfaceC0321f a(A7.i iVar) {
        E eM = m(iVar);
        String name = iVar.getName();
        String strI = iVar.i();
        A7.m.f(eM, "container");
        A7.m.f(name, "name");
        A7.m.f(strI, "signature");
        return new G(eM, name, strI, null, iVar.f455m);
    }

    @Override // A7.B
    public final InterfaceC0318c b(Class cls) {
        return AbstractC0379c.a(cls);
    }

    @Override // A7.B
    public final InterfaceC0320e c(Class cls, String str) {
        M.t tVar = AbstractC0379c.f3777a;
        A7.m.f(cls, "jClass");
        return (InterfaceC0320e) AbstractC0379c.f3778b.D(cls);
    }

    @Override // A7.B
    public final H7.x d(H7.x xVar) {
        A7.m.f(xVar, "type");
        AbstractC0287w abstractC0287w = ((o0) xVar).f3824a;
        if (!(abstractC0287w instanceof F8.A)) {
            throw new IllegalArgumentException(("Non-simple type cannot be a mutable collection type: " + xVar).toString());
        }
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
        if (interfaceC0652e == null) {
            throw new IllegalArgumentException("Non-class type cannot be a mutable collection type: " + xVar);
        }
        F8.A a10 = (F8.A) abstractC0287w;
        String str = P7.d.f5417a;
        C2058c c2058c = (C2058c) P7.d.f5425k.get(AbstractC2564d.h(interfaceC0652e));
        if (c2058c == null) {
            throw new IllegalArgumentException("Not a readonly collection: " + interfaceC0652e);
        }
        F8.K kC = AbstractC2564d.e(interfaceC0652e).i(c2058c).C();
        A7.m.e(kC, "classifier.readOnlyToMutable().typeConstructor");
        F8.H hT0 = a10.t0();
        List listO0 = a10.o0();
        boolean zF0 = a10.F0();
        A7.m.f(hT0, "annotations");
        A7.m.f(listO0, "arguments");
        return new o0(C0269d.r(hT0, kC, listO0, zF0), null);
    }

    @Override // A7.B
    public final InterfaceC0324i e(C0226s c0226s) {
        return new I(m(c0226s), c0226s.o, c0226s.f457p, c0226s.f455m);
    }

    @Override // A7.B
    public final H7.k f(A7.p pVar) {
        return new L(m(pVar), pVar.o, pVar.f457p, pVar.f455m);
    }

    @Override // A7.B
    public final H7.r g(E.m mVar) {
        return new Z(m(mVar), mVar.o, mVar.f457p, mVar.f455m);
    }

    @Override // A7.B
    public final H7.t h(A7.s sVar) {
        return new c0(m(sVar), sVar.o, sVar.f457p, sVar.f455m);
    }

    @Override // A7.B
    public final H7.v i(A7.t tVar) {
        return new f0(m(tVar), tVar.o, tVar.f457p);
    }

    @Override // A7.B
    public final String j(InterfaceC0060h interfaceC0060h) throws C2142q {
        G gB;
        A7.m.f(interfaceC0060h, "<this>");
        Metadata metadata = (Metadata) interfaceC0060h.getClass().getAnnotation(Metadata.class);
        G g7 = null;
        if (metadata != null) {
            String[] strArrD1 = metadata.d1();
            if (strArrD1.length == 0) {
                strArrD1 = null;
            }
            if (strArrD1 != null) {
                String[] strArrD2 = metadata.d2();
                C2132g c2132g = n8.h.f20171a;
                A7.m.f(strArrD2, "strings");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(n8.a.a(strArrD1));
                C2132g c2132g2 = n8.h.f20171a;
                n8.g gVarG = n8.h.g(byteArrayInputStream, strArrD2);
                C1556a c1556a = C1579y.f17483G;
                C2132g c2132g3 = n8.h.f20171a;
                c1556a.getClass();
                C2130e c2130e = new C2130e(byteArrayInputStream);
                AbstractC2126a abstractC2126a = (AbstractC2126a) c1556a.a(c2130e, c2132g3);
                try {
                    c2130e.a(0);
                    if (!abstractC2126a.b()) {
                        C2142q c2142q = new C2142q(new A9.k().getMessage());
                        c2142q.l = abstractC2126a;
                        throw c2142q;
                    }
                    C1579y c1579y = (C1579y) abstractC2126a;
                    n8.f fVar = new n8.f(metadata.mv(), (metadata.xi() & 8) != 0);
                    Class<?> cls = interfaceC0060h.getClass();
                    j8.X x8 = c1579y.f17484A;
                    A7.m.e(x8, "proto.typeTable");
                    g7 = new G(C0380d.f3781m, (T7.K) A0.f(cls, c1579y, gVarG, new C0746i0(x8), fVar, J7.a.f3110t));
                } catch (C2142q e10) {
                    e10.l = abstractC2126a;
                    throw e10;
                }
            }
        }
        if (g7 == null || (gB = A0.b(g7)) == null) {
            return super.j(interfaceC0060h);
        }
        C2203g c2203g = x0.f3852a;
        InterfaceC0666t interfaceC0666tT = gB.n();
        StringBuilder sb = new StringBuilder();
        x0.a(interfaceC0666tT, sb);
        List listD0 = interfaceC0666tT.D0();
        A7.m.e(listD0, "invoke.valueParameters");
        AbstractC1998m.d0(listD0, sb, ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) != 0 ? "" : ")", -1, "...", (124 & 64) != 0 ? null : C0377b.f3772x);
        sb.append(" -> ");
        AbstractC0287w abstractC0287wP = interfaceC0666tT.p();
        A7.m.c(abstractC0287wP);
        sb.append(x0.d(abstractC0287wP));
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @Override // A7.B
    public final String k(A7.o oVar) {
        return j(oVar);
    }

    @Override // A7.B
    public final H7.x l(InterfaceC0318c interfaceC0318c, List list, boolean z6) {
        if (!(interfaceC0318c instanceof InterfaceC0057e)) {
            return io.sentry.config.a.W(interfaceC0318c, list, z6, Collections.emptyList());
        }
        Class clsD = ((InterfaceC0057e) interfaceC0318c).d();
        M.t tVar = AbstractC0379c.f3777a;
        A7.m.f(clsD, "jClass");
        A7.m.f(list, "arguments");
        if (list.isEmpty()) {
            return z6 ? (H7.x) AbstractC0379c.d.D(clsD) : (H7.x) AbstractC0379c.c.D(clsD);
        }
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) AbstractC0379c.f3779e.D(clsD);
        m7.i iVar = new m7.i(list, Boolean.valueOf(z6));
        Object obj = concurrentHashMap.get(iVar);
        if (obj == null) {
            o0 o0VarW = io.sentry.config.a.W(AbstractC0379c.a(clsD), list, z6, n7.u.l);
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(iVar, o0VarW);
            obj = objPutIfAbsent == null ? o0VarW : objPutIfAbsent;
        }
        return (H7.x) obj;
    }
}
