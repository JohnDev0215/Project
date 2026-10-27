package v8;

import A7.m;
import A7.z;
import B2.P;
import G8.g;
import N7.i;
import O8.l;
import Q7.D;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0671y;
import Q7.K;
import Q7.L;
import T7.B;
import T7.F;
import T7.Q;
import h8.AbstractC1387d;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: v8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2564d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f23040a = 0;

    static {
        C2061f.e("value");
    }

    public static final boolean a(Q q4) {
        Boolean boolG = l.g(AbstractC1387d.j(q4), C2561a.l, C2562b.f23038t);
        m.e(boolG, "ifAny(\n        listOf(th…eclaresDefaultValue\n    )");
        return boolG.booleanValue();
    }

    public static InterfaceC0650c b(InterfaceC0650c interfaceC0650c, InterfaceC2782k interfaceC2782k) {
        m.f(interfaceC0650c, "<this>");
        return (InterfaceC0650c) l.e(AbstractC1387d.j(interfaceC0650c), new P(false), new O8.a(new z(), interfaceC2782k));
    }

    public static final C2058c c(InterfaceC0658k interfaceC0658k) {
        m.f(interfaceC0658k, "<this>");
        C2060e c2060eH = h(interfaceC0658k);
        if (!c2060eH.d()) {
            c2060eH = null;
        }
        if (c2060eH != null) {
            return c2060eH.g();
        }
        return null;
    }

    public static final InterfaceC0652e d(R7.b bVar) {
        m.f(bVar, "<this>");
        InterfaceC0654g interfaceC0654gN = bVar.b().B0().n();
        if (interfaceC0654gN instanceof InterfaceC0652e) {
            return (InterfaceC0652e) interfaceC0654gN;
        }
        return null;
    }

    public static final i e(InterfaceC0657j interfaceC0657j) {
        m.f(interfaceC0657j, "<this>");
        return j(interfaceC0657j).l();
    }

    public static final C2057b f(InterfaceC0654g interfaceC0654g) {
        InterfaceC0657j interfaceC0657jO;
        C2057b c2057bF;
        if (interfaceC0654g == null || (interfaceC0657jO = interfaceC0654g.o()) == null) {
            return null;
        }
        if (interfaceC0657jO instanceof D) {
            return new C2057b(((B) ((D) interfaceC0657jO)).f8767q, interfaceC0654g.getName());
        }
        if (!(interfaceC0657jO instanceof InterfaceC0655h) || (c2057bF = f((InterfaceC0654g) interfaceC0657jO)) == null) {
            return null;
        }
        return c2057bF.d(interfaceC0654g.getName());
    }

    public static final C2058c g(InterfaceC0657j interfaceC0657j) {
        m.f(interfaceC0657j, "<this>");
        C2058c c2058cH = r8.d.h(interfaceC0657j);
        if (c2058cH == null) {
            c2058cH = r8.d.g(interfaceC0657j.o()).b(interfaceC0657j.getName()).g();
        }
        if (c2058cH != null) {
            return c2058cH;
        }
        r8.d.a(4);
        throw null;
    }

    public static final C2060e h(InterfaceC0657j interfaceC0657j) {
        m.f(interfaceC0657j, "<this>");
        C2060e c2060eG = r8.d.g(interfaceC0657j);
        m.e(c2060eG, "getFqName(this)");
        return c2060eG;
    }

    public static final void i(InterfaceC0671y interfaceC0671y) {
        m.f(interfaceC0671y, "<this>");
        if (interfaceC0671y.P(g.f2725a) != null) {
            throw new ClassCastException();
        }
    }

    public static final InterfaceC0671y j(InterfaceC0657j interfaceC0657j) {
        m.f(interfaceC0657j, "<this>");
        InterfaceC0671y interfaceC0671yD = r8.d.d(interfaceC0657j);
        m.e(interfaceC0671yD, "getContainingModule(this)");
        return interfaceC0671yD;
    }

    public static final InterfaceC0650c k(InterfaceC0650c interfaceC0650c) {
        m.f(interfaceC0650c, "<this>");
        if (!(interfaceC0650c instanceof K)) {
            return interfaceC0650c;
        }
        L lC1 = ((F) ((K) interfaceC0650c)).c1();
        m.e(lC1, "correspondingProperty");
        return lC1;
    }
}
