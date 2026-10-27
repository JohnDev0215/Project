package K7;

import A7.C0055c;
import A7.InterfaceC0060h;
import F8.AbstractC0287w;
import H7.InterfaceC0321f;
import Q7.AbstractC0662o;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0666t;
import T7.AbstractC0794m;
import T7.C0790i;
import h8.AbstractC1387d;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1996k;
import q8.C2203g;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class G extends r implements InterfaceC0060h, InterfaceC0321f, InterfaceC0381e {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f3726s;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final E f3727m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f3728n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final t0 f3729p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f3730q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Object f3731r;

    static {
        A7.B b10 = A7.A.f443a;
        f3726s = new H7.w[]{b10.h(new A7.s(b10.b(G.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;"))};
    }

    public G(E e10, String str, String str2, InterfaceC0666t interfaceC0666t, Object obj) {
        this.f3727m = e10;
        this.f3728n = str2;
        this.o = obj;
        this.f3729p = v0.n(interfaceC0666t, new B8.A(this, 15, str));
        m7.g gVar = m7.g.l;
        this.f3730q = h6.r.n(gVar, new F(this, 0));
        this.f3731r = h6.r.n(gVar, new F(this, 1));
    }

    public static final L7.t s(G g7, Constructor constructor, InterfaceC0666t interfaceC0666t, boolean z6) {
        Class<?> cls = null;
        if (!z6) {
            g7.getClass();
            C0790i c0790i = interfaceC0666t instanceof C0790i ? (C0790i) interfaceC0666t : null;
            if (c0790i != null) {
                C0790i c0790i2 = c0790i;
                if (!AbstractC0662o.e(c0790i2.e())) {
                    InterfaceC0652e interfaceC0652eJ = c0790i.J();
                    A7.m.e(interfaceC0652eJ, "constructorDescriptor.constructedClass");
                    if (!r8.h.b(interfaceC0652eJ) && !r8.d.q(c0790i.J())) {
                        List listD0 = c0790i2.D0();
                        A7.m.e(listD0, "constructorDescriptor.valueParameters");
                        if (!listD0.isEmpty()) {
                            Iterator it = listD0.iterator();
                            while (it.hasNext()) {
                                AbstractC0287w abstractC0287wB = ((T7.Q) it.next()).b();
                                A7.m.e(abstractC0287wB, "it.type");
                                if (AbstractC1387d.p(abstractC0287wB)) {
                                    if (g7.r()) {
                                        return new L7.f(constructor, w0.c.G(g7.o, g7.n()), 0);
                                    }
                                    A7.m.f(constructor, "constructor");
                                    Class declaringClass = constructor.getDeclaringClass();
                                    A7.m.e(declaringClass, "constructor.declaringClass");
                                    Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                                    A7.m.e(genericParameterTypes, "constructor.genericParameterTypes");
                                    return new L7.g(constructor, declaringClass, cls, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : AbstractC1996k.I(genericParameterTypes, 0, genericParameterTypes.length - 1)), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (g7.r()) {
            return new L7.f(constructor, w0.c.G(g7.o, g7.n()), 1);
        }
        A7.m.f(constructor, "constructor");
        Class declaringClass2 = constructor.getDeclaringClass();
        A7.m.e(declaringClass2, "constructor.declaringClass");
        Class declaringClass3 = constructor.getDeclaringClass();
        Class<?> declaringClass4 = declaringClass3.getDeclaringClass();
        if (declaringClass4 != null && !Modifier.isStatic(declaringClass3.getModifiers())) {
            cls = declaringClass4;
        }
        Type[] genericParameterTypes2 = constructor.getGenericParameterTypes();
        A7.m.e(genericParameterTypes2, "constructor.genericParameterTypes");
        return new L7.g(constructor, declaringClass2, cls, genericParameterTypes2, 1);
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return h(new Object[0]);
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return h(obj);
    }

    @Override // z7.InterfaceC2788q
    public final Object e(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        return h(obj, obj2, obj3, obj4, serializable);
    }

    public final boolean equals(Object obj) {
        G gB = A0.b(obj);
        return gB != null && A7.m.a(this.f3727m, gB.f3727m) && getName().equals(gB.getName()) && A7.m.a(this.f3728n, gB.f3728n) && A7.m.a(this.o, gB.o);
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        return h(obj, obj2, obj3);
    }

    @Override // A7.InterfaceC0060h
    public final int getArity() {
        return l9.l.M(i());
    }

    @Override // H7.InterfaceC0317b
    public final String getName() {
        String strB = ((AbstractC0794m) n()).getName().b();
        A7.m.e(strB, "descriptor.name.asString()");
        return strB;
    }

    public final int hashCode() {
        return this.f3728n.hashCode() + ((getName().hashCode() + (this.f3727m.hashCode() * 31)) * 31);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.r
    public final L7.e i() {
        return (L7.e) this.f3730q.getValue();
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return h(obj, obj2);
    }

    @Override // K7.r
    public final E j() {
        return this.f3727m;
    }

    @Override // z7.InterfaceC2789r
    public final Object k(g0.o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, Object obj, Integer num2) {
        return h(oVar, num, bool, interfaceC2772a, obj, num2);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.r
    public final L7.e l() {
        return (L7.e) this.f3731r.getValue();
    }

    @Override // H7.InterfaceC0317b
    public final boolean m() {
        return n().m();
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        return h(obj, obj2, obj3, obj4);
    }

    @Override // K7.r
    public final boolean r() {
        return !A7.m.a(this.o, C0055c.l);
    }

    @Override // K7.r
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0666t n() {
        H7.w wVar = f3726s[0];
        Object objA = this.f3729p.a();
        A7.m.e(objA, "<get-descriptor>(...)");
        return (InterfaceC0666t) objA;
    }

    public final String toString() {
        C2203g c2203g = x0.f3852a;
        return x0.b(n());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public G(E e10, InterfaceC0666t interfaceC0666t) {
        A7.m.f(e10, "container");
        A7.m.f(interfaceC0666t, "descriptor");
        String strB = ((AbstractC0794m) interfaceC0666t).getName().b();
        A7.m.e(strB, "descriptor.name.asString()");
        this(e10, strB, y0.c(interfaceC0666t).i(), interfaceC0666t, C0055c.l);
    }
}
