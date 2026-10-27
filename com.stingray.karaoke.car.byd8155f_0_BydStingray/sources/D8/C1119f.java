package d8;

import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.O;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.N;
import Q7.Q;
import W7.AbstractC0839c;
import W7.C0840d;
import a.AbstractC0904a;
import b8.InterfaceC1013h;
import c8.C1036a;
import f8.InterfaceC1265a;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import l3.P;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import q8.C2203g;
import t8.C2448a;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: d8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1119f implements R7.b, InterfaceC1013h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f13571h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N0.q f13572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0840d f13573b;
    public final E8.h c;
    public final E8.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V7.g f13574e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final E8.i f13575f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f13576g;

    static {
        A7.B b10 = A7.A.f443a;
        f13571h = new H7.w[]{b10.h(new A7.s(b10.b(C1119f.class), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), b10.h(new A7.s(b10.b(C1119f.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), b10.h(new A7.s(b10.b(C1119f.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public C1119f(N0.q qVar, C0840d c0840d, boolean z6) {
        A7.m.f(qVar, "c");
        A7.m.f(c0840d, "javaAnnotation");
        this.f13572a = qVar;
        this.f13573b = c0840d;
        C1036a c1036a = (C1036a) qVar.f4399m;
        E8.l lVar = c1036a.f12411a;
        C1118e c1118e = new C1118e(this, 1);
        lVar.getClass();
        this.c = new E8.h(lVar, c1118e);
        C1118e c1118e2 = new C1118e(this, 2);
        lVar.getClass();
        this.d = new E8.i(lVar, c1118e2);
        this.f13574e = c1036a.f12418j.b(c0840d);
        C1118e c1118e3 = new C1118e(this, 0);
        lVar.getClass();
        this.f13575f = new E8.i(lVar, c1118e3);
        this.f13576g = z6;
    }

    @Override // R7.b
    public final C2058c a() {
        E8.h hVar = this.c;
        H7.w wVar = f13571h[0];
        A7.m.f(hVar, "<this>");
        A7.m.f(wVar, "p");
        return (C2058c) hVar.a();
    }

    @Override // R7.b
    public final AbstractC0287w b() {
        return (F8.A) C7.a.N(this.d, f13571h[1]);
    }

    @Override // R7.b
    public final Map c() {
        return (Map) C7.a.N(this.f13575f, f13571h[2]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final t8.g d(InterfaceC1265a interfaceC1265a) {
        AbstractC0287w abstractC0287wH;
        if (interfaceC1265a instanceof W7.u) {
            return t8.h.f22500a.b(((W7.u) interfaceC1265a).f9898b, null);
        }
        if (interfaceC1265a instanceof W7.s) {
            W7.s sVar = (W7.s) interfaceC1265a;
            Class<?> enclosingClass = sVar.f9896b.getClass();
            if (!enclosingClass.isEnum()) {
                enclosingClass = enclosingClass.getEnclosingClass();
            }
            A7.m.e(enclosingClass, "enumClass");
            return new t8.i(AbstractC0839c.a(enclosingClass), C2061f.e(sVar.f9896b.name()));
        }
        boolean z6 = interfaceC1265a instanceof W7.g;
        N0.q qVar = this.f13572a;
        if (!z6) {
            if (interfaceC1265a instanceof W7.f) {
                return new C2448a((Object) new C1119f(qVar, new C0840d(((W7.f) interfaceC1265a).f9882b), false));
            }
            if (!(interfaceC1265a instanceof W7.o)) {
                return null;
            }
            Class cls = ((W7.o) interfaceC1265a).f9892b;
            AbstractC0287w abstractC0287wG = ((P) qVar.l).G(cls.isPrimitive() ? new W7.y(cls) : ((cls instanceof GenericArrayType) || cls.isArray()) ? new W7.h(cls) : cls instanceof WildcardType ? new W7.D((WildcardType) cls) : new W7.p(cls), AbstractC0904a.S(2, false, false, null, 7));
            if (AbstractC0268c.i(abstractC0287wG)) {
                return null;
            }
            AbstractC0287w abstractC0287wB = abstractC0287wG;
            int i7 = 0;
            while (N7.i.x(abstractC0287wB)) {
                abstractC0287wB = ((O) AbstractC1998m.r0(abstractC0287wB.o0())).b();
                A7.m.e(abstractC0287wB, "type.arguments.single().type");
                i7++;
            }
            InterfaceC0654g interfaceC0654gN = abstractC0287wB.B0().n();
            if (interfaceC0654gN instanceof InterfaceC0652e) {
                C2057b c2057bF = AbstractC2564d.f(interfaceC0654gN);
                return c2057bF == null ? new t8.r(new t8.o(abstractC0287wG)) : new t8.r(c2057bF, i7);
            }
            if (interfaceC0654gN instanceof Q) {
                return new t8.r(C2057b.j(N7.n.f4843a.g()), 0);
            }
            return null;
        }
        W7.g gVar = (W7.g) interfaceC1265a;
        C2061f c2061f = gVar.f9881a;
        if (c2061f == null) {
            c2061f = Z7.x.f10964b;
        }
        A7.m.e(c2061f, "argument.name ?: DEFAULT_ANNOTATION_MEMBER_NAME");
        ArrayList arrayListA = gVar.a();
        F8.A a10 = (F8.A) C7.a.N(this.d, f13571h[1]);
        A7.m.e(a10, "type");
        if (AbstractC0268c.i(a10)) {
            return null;
        }
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d(this);
        A7.m.c(interfaceC0652eD);
        T7.Q qV = l9.d.V(c2061f, interfaceC0652eD);
        if (qV == null || (abstractC0287wH = qV.b()) == null) {
            abstractC0287wH = ((C1036a) qVar.f4399m).o.f8921p.h(H8.i.c(H8.h.f2879O, new String[0]));
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(arrayListA, 10));
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            t8.g gVarD = d((InterfaceC1265a) it.next());
            if (gVarD == null) {
                gVarD = new t8.t(null);
            }
            arrayList.add(gVarD);
        }
        return new t8.w(arrayList, abstractC0287wH);
    }

    @Override // R7.b
    public final N g() {
        return this.f13574e;
    }

    public final String toString() {
        return C2203g.c.x(this, null);
    }
}
