package P7;

import h8.AbstractC1387d;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1999n;
import o8.AbstractC2063h;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import o8.C2064i;
import w8.EnumC2609d;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5417a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f5418b;
    public static final String c;
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2057b f5419e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2058c f5420f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2057b f5421g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashMap f5422h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final HashMap f5423i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final HashMap f5424j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final HashMap f5425k;
    public static final HashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final HashMap f5426m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final List f5427n;

    static {
        StringBuilder sb = new StringBuilder();
        O7.e eVar = O7.e.o;
        sb.append(eVar.l.f20566a.toString());
        sb.append('.');
        sb.append(eVar.f5186m);
        f5417a = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        O7.e eVar2 = O7.e.f5183q;
        sb2.append(eVar2.l.f20566a.toString());
        sb2.append('.');
        sb2.append(eVar2.f5186m);
        f5418b = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        O7.e eVar3 = O7.e.f5182p;
        sb3.append(eVar3.l.f20566a.toString());
        sb3.append('.');
        sb3.append(eVar3.f5186m);
        c = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        O7.e eVar4 = O7.e.f5184r;
        sb4.append(eVar4.l.f20566a.toString());
        sb4.append('.');
        sb4.append(eVar4.f5186m);
        d = sb4.toString();
        C2057b c2057bJ = C2057b.j(new C2058c("kotlin.jvm.functions.FunctionN"));
        f5419e = c2057bJ;
        f5420f = c2057bJ.b();
        f5421g = C2064i.f20590n;
        d(Class.class);
        f5422h = new HashMap();
        f5423i = new HashMap();
        f5424j = new HashMap();
        f5425k = new HashMap();
        l = new HashMap();
        f5426m = new HashMap();
        C2057b c2057bJ2 = C2057b.j(N7.n.f4818A);
        C2058c c2058c = N7.n.f4825I;
        C2058c c2058cG = c2057bJ2.g();
        C2058c c2058cG2 = c2057bJ2.g();
        A7.m.e(c2058cG2, "kotlinReadOnly.packageFqName");
        C2058c c2058cU = AbstractC1387d.u(c2058c, c2058cG2);
        c cVar = new c(d(Iterable.class), c2057bJ2, new C2057b(c2058cG, c2058cU, false));
        C2057b c2057bJ3 = C2057b.j(N7.n.f4867z);
        C2058c c2058c2 = N7.n.f4824H;
        C2058c c2058cG3 = c2057bJ3.g();
        C2058c c2058cG4 = c2057bJ3.g();
        A7.m.e(c2058cG4, "kotlinReadOnly.packageFqName");
        c cVar2 = new c(d(Iterator.class), c2057bJ3, new C2057b(c2058cG3, AbstractC1387d.u(c2058c2, c2058cG4), false));
        C2057b c2057bJ4 = C2057b.j(N7.n.B);
        C2058c c2058c3 = N7.n.f4826J;
        C2058c c2058cG5 = c2057bJ4.g();
        C2058c c2058cG6 = c2057bJ4.g();
        A7.m.e(c2058cG6, "kotlinReadOnly.packageFqName");
        c cVar3 = new c(d(Collection.class), c2057bJ4, new C2057b(c2058cG5, AbstractC1387d.u(c2058c3, c2058cG6), false));
        C2057b c2057bJ5 = C2057b.j(N7.n.f4819C);
        C2058c c2058c4 = N7.n.f4827K;
        C2058c c2058cG7 = c2057bJ5.g();
        C2058c c2058cG8 = c2057bJ5.g();
        A7.m.e(c2058cG8, "kotlinReadOnly.packageFqName");
        c cVar4 = new c(d(List.class), c2057bJ5, new C2057b(c2058cG7, AbstractC1387d.u(c2058c4, c2058cG8), false));
        C2057b c2057bJ6 = C2057b.j(N7.n.f4821E);
        C2058c c2058c5 = N7.n.f4829M;
        C2058c c2058cG9 = c2057bJ6.g();
        C2058c c2058cG10 = c2057bJ6.g();
        A7.m.e(c2058cG10, "kotlinReadOnly.packageFqName");
        c cVar5 = new c(d(Set.class), c2057bJ6, new C2057b(c2058cG9, AbstractC1387d.u(c2058c5, c2058cG10), false));
        C2057b c2057bJ7 = C2057b.j(N7.n.f4820D);
        C2058c c2058c6 = N7.n.f4828L;
        C2058c c2058cG11 = c2057bJ7.g();
        C2058c c2058cG12 = c2057bJ7.g();
        A7.m.e(c2058cG12, "kotlinReadOnly.packageFqName");
        c cVar6 = new c(d(ListIterator.class), c2057bJ7, new C2057b(c2058cG11, AbstractC1387d.u(c2058c6, c2058cG12), false));
        C2058c c2058c7 = N7.n.f4822F;
        C2057b c2057bJ8 = C2057b.j(c2058c7);
        C2058c c2058c8 = N7.n.f4830N;
        C2058c c2058cG13 = c2057bJ8.g();
        C2058c c2058cG14 = c2057bJ8.g();
        A7.m.e(c2058cG14, "kotlinReadOnly.packageFqName");
        c cVar7 = new c(d(Map.class), c2057bJ8, new C2057b(c2058cG13, AbstractC1387d.u(c2058c8, c2058cG14), false));
        C2057b c2057bD = C2057b.j(c2058c7).d(N7.n.f4823G.f());
        C2058c c2058c9 = N7.n.f4831O;
        C2058c c2058cG15 = c2057bD.g();
        C2058c c2058cG16 = c2057bD.g();
        A7.m.e(c2058cG16, "kotlinReadOnly.packageFqName");
        List<c> listD = AbstractC1999n.D(cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7, new c(d(Map.Entry.class), c2057bD, new C2057b(c2058cG15, AbstractC1387d.u(c2058c9, c2058cG16), false)));
        f5427n = listD;
        c(Object.class, N7.n.f4843a);
        c(String.class, N7.n.f4849f);
        c(CharSequence.class, N7.n.f4848e);
        a(d(Throwable.class), C2057b.j(N7.n.f4854k));
        c(Cloneable.class, N7.n.c);
        c(Number.class, N7.n.f4852i);
        a(d(Comparable.class), C2057b.j(N7.n.l));
        c(Enum.class, N7.n.f4853j);
        a(d(Annotation.class), C2057b.j(N7.n.f4860s));
        for (c cVar8 : listD) {
            C2057b c2057b = cVar8.f5415a;
            C2057b c2057b2 = cVar8.f5416b;
            a(c2057b, c2057b2);
            C2057b c2057b3 = cVar8.c;
            b(c2057b3.b(), c2057b);
            l.put(c2057b3, c2057b2);
            f5426m.put(c2057b2, c2057b3);
            C2058c c2058cB = c2057b2.b();
            C2058c c2058cB2 = c2057b3.b();
            C2060e c2060eI = c2057b3.b().i();
            A7.m.e(c2060eI, "mutableClassId.asSingleFqName().toUnsafe()");
            f5424j.put(c2060eI, c2058cB);
            C2060e c2060eI2 = c2058cB.i();
            A7.m.e(c2060eI2, "readOnlyFqName.toUnsafe()");
            f5425k.put(c2060eI2, c2058cB2);
        }
        for (EnumC2609d enumC2609d : EnumC2609d.values()) {
            C2057b c2057bJ9 = C2057b.j(enumC2609d.e());
            N7.k kVarD = enumC2609d.d();
            A7.m.e(kVarD, "jvmType.primitiveType");
            a(c2057bJ9, C2057b.j(N7.o.f4875j.c(kVarD.l)));
        }
        for (C2057b c2057b4 : N7.d.f4790a) {
            a(C2057b.j(new C2058c("kotlin.jvm.internal." + c2057b4.i().b() + "CompanionObject")), c2057b4.d(AbstractC2063h.f20576b));
        }
        for (int i7 = 0; i7 < 23; i7++) {
            a(C2057b.j(new C2058c(android.hardware.bydauto.ac.tsimpl.a.e(i7, "kotlin.jvm.functions.Function"))), new C2057b(N7.o.f4875j, C2061f.e("Function" + i7)));
            b(new C2058c(f5418b + i7), f5421g);
        }
        for (int i10 = 0; i10 < 22; i10++) {
            O7.e eVar5 = O7.e.f5184r;
            b(new C2058c((eVar5.l.f20566a.toString() + '.' + eVar5.f5186m) + i10), f5421g);
        }
        C2058c c2058cG17 = N7.n.f4845b.g();
        A7.m.e(c2058cG17, "nothing.toSafe()");
        b(c2058cG17, d(Void.class));
    }

    public static void a(C2057b c2057b, C2057b c2057b2) {
        C2060e c2060eI = c2057b.b().i();
        A7.m.e(c2060eI, "javaClassId.asSingleFqName().toUnsafe()");
        f5422h.put(c2060eI, c2057b2);
        b(c2057b2.b(), c2057b);
    }

    public static void b(C2058c c2058c, C2057b c2057b) {
        C2060e c2060eI = c2058c.i();
        A7.m.e(c2060eI, "kotlinFqNameUnsafe.toUnsafe()");
        f5423i.put(c2060eI, c2057b);
    }

    public static void c(Class cls, C2060e c2060e) {
        C2058c c2058cG = c2060e.g();
        A7.m.e(c2058cG, "kotlinFqName.toSafe()");
        a(d(cls), C2057b.j(c2058cG));
    }

    public static C2057b d(Class cls) {
        if (!cls.isPrimitive()) {
            cls.isArray();
        }
        Class<?> declaringClass = cls.getDeclaringClass();
        return declaringClass == null ? C2057b.j(new C2058c(cls.getCanonicalName())) : d(declaringClass).d(C2061f.e(cls.getSimpleName()));
    }

    public static boolean e(C2060e c2060e, String str) {
        Integer numA0;
        String str2 = c2060e.f20571a;
        if (str2 != null) {
            String strY0 = Q8.h.Y0(str2, str, "");
            return strY0.length() > 0 && !Q8.h.X0(strY0, '0') && (numA0 = Q8.o.A0(strY0)) != null && numA0.intValue() >= 23;
        }
        C2060e.a(4);
        throw null;
    }

    public static C2057b f(C2060e c2060e) {
        boolean zE = e(c2060e, f5417a);
        C2057b c2057b = f5419e;
        if (zE || e(c2060e, c)) {
            return c2057b;
        }
        boolean zE2 = e(c2060e, f5418b);
        C2057b c2057b2 = f5421g;
        return (zE2 || e(c2060e, d)) ? c2057b2 : (C2057b) f5423i.get(c2060e);
    }
}
