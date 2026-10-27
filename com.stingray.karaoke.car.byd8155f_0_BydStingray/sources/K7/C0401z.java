package K7;

import H7.InterfaceC0318c;
import Q7.InterfaceC0652e;
import T.C0746i0;
import W7.AbstractC0839c;
import h8.AbstractC1386c;
import java.util.Collection;
import java.util.List;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import p8.C2138m;
import w8.EnumC2609d;

/* JADX INFO: renamed from: K7.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0401z extends E implements InterfaceC0318c, A, q0 {
    public static final /* synthetic */ int o = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Class f3855m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u0 f3856n;

    public C0401z(Class cls) {
        A7.m.f(cls, "jClass");
        this.f3855m = cls;
        this.f3856n = new u0(new C0395t(this, 7));
    }

    @Override // H7.InterfaceC0318c
    public final String a() {
        C0398w c0398w = (C0398w) this.f3856n.a();
        c0398w.getClass();
        H7.w wVar = C0398w.l[3];
        return (String) c0398w.f3844e.a();
    }

    @Override // H7.InterfaceC0318c
    public final String b() {
        C0398w c0398w = (C0398w) this.f3856n.a();
        c0398w.getClass();
        H7.w wVar = C0398w.l[2];
        return (String) c0398w.d.a();
    }

    @Override // H7.InterfaceC0318c
    public final boolean c(androidx.lifecycle.f0 f0Var) {
        List list = AbstractC0839c.f9878a;
        Class cls = this.f3855m;
        A7.m.f(cls, "<this>");
        Integer num = (Integer) AbstractC0839c.d.get(cls);
        if (num != null) {
            return A7.E.f(num.intValue(), f0Var);
        }
        Class cls2 = (Class) AbstractC0839c.c.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        return cls.isInstance(f0Var);
    }

    @Override // A7.InterfaceC0057e
    public final Class d() {
        return this.f3855m;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0401z) && n7.z.j(this).equals(n7.z.j((InterfaceC0318c) obj));
    }

    @Override // K7.E
    public final Collection h() {
        InterfaceC0652e interfaceC0652eE = e();
        if (interfaceC0652eE.E() == 2 || interfaceC0652eE.E() == 6) {
            return n7.u.l;
        }
        Collection collectionH = interfaceC0652eE.H();
        A7.m.e(collectionH, "descriptor.constructors");
        return collectionH;
    }

    public final int hashCode() {
        return n7.z.j(this).hashCode();
    }

    @Override // K7.E
    public final Collection i(C2061f c2061f) {
        y8.n nVarZ0 = e().j().z0();
        Y7.b bVar = Y7.b.f10798m;
        Collection collectionA = nVarZ0.a(c2061f, bVar);
        y8.n nVarQ0 = e().q0();
        A7.m.e(nVarQ0, "descriptor.staticScope");
        return AbstractC1998m.n0(collectionA, nVarQ0.a(c2061f, bVar));
    }

    @Override // K7.E
    public final Q7.L j(int i7) {
        Class<?> declaringClass;
        Class cls = this.f3855m;
        if (cls.getSimpleName().equals("DefaultImpls") && (declaringClass = cls.getDeclaringClass()) != null && declaringClass.isInterface()) {
            return ((C0401z) n7.z.l(declaringClass)).j(i7);
        }
        InterfaceC0652e interfaceC0652eE = e();
        D8.k kVar = interfaceC0652eE instanceof D8.k ? (D8.k) interfaceC0652eE : null;
        if (kVar == null) {
            return null;
        }
        C2138m c2138m = m8.k.f19334j;
        A7.m.e(c2138m, "classLocalVariable");
        j8.G g7 = (j8.G) AbstractC1386c.i(kVar.f1241p, c2138m, i7);
        if (g7 == null) {
            return null;
        }
        B8.k kVar2 = kVar.f1248w;
        return (Q7.L) A0.f(this.f3855m, g7, (InterfaceC1788f) kVar2.f780b, (C0746i0) kVar2.d, kVar.f1242q, C0400y.f3853t);
    }

    @Override // K7.E
    public final Collection m(C2061f c2061f) {
        y8.n nVarZ0 = e().j().z0();
        Y7.b bVar = Y7.b.f10798m;
        Collection collectionB = nVarZ0.b(c2061f, bVar);
        y8.n nVarQ0 = e().q0();
        A7.m.e(nVarQ0, "descriptor.staticScope");
        return AbstractC1998m.n0(collectionB, nVarQ0.b(c2061f, bVar));
    }

    public final C2057b s() {
        N7.k kVarD;
        C2057b c2057b = y0.f3854a;
        Class cls = this.f3855m;
        A7.m.f(cls, "klass");
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            A7.m.e(componentType, "klass.componentType");
            kVarD = componentType.isPrimitive() ? EnumC2609d.b(componentType.getSimpleName()).d() : null;
            return kVarD != null ? new C2057b(N7.o.f4875j, kVarD.f4811m) : C2057b.j(N7.n.f4850g.g());
        }
        if (cls.equals(Void.TYPE)) {
            return y0.f3854a;
        }
        kVarD = cls.isPrimitive() ? EnumC2609d.b(cls.getSimpleName()).d() : null;
        if (kVarD != null) {
            return new C2057b(N7.o.f4875j, kVarD.l);
        }
        C2057b c2057bA = AbstractC0839c.a(cls);
        if (c2057bA.c) {
            return c2057bA;
        }
        String str = P7.d.f5417a;
        C2057b c2057b2 = (C2057b) P7.d.f5422h.get(c2057bA.b().i());
        return c2057b2 != null ? c2057b2 : c2057bA;
    }

    @Override // K7.A
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0652e e() {
        return ((C0398w) this.f3856n.a()).a();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("class ");
        C2057b c2057bS = s();
        C2058c c2058cG = c2057bS.g();
        A7.m.e(c2058cG, "classId.packageFqName");
        String strConcat = c2058cG.d() ? "" : c2058cG.b().concat(".");
        sb.append(strConcat + Q8.o.w0(c2057bS.h().b(), '.', '$'));
        return sb.toString();
    }
}
