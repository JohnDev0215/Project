package o8;

import A7.m;
import io.sentry.protocol.DebugImage;
import java.util.LinkedHashMap;
import java.util.Set;
import n7.AbstractC1985B;
import n7.AbstractC1996k;
import n7.AbstractC2000o;
import n7.z;

/* JADX INFO: renamed from: o8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2064i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2058c f20580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2058c f20581b;
    public static final C2058c c;
    public static final C2058c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2058c f20582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2058c f20583f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2058c f20584g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C2057b f20585h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C2057b f20586i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C2057b f20587j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C2057b f20588k;
    public static final C2057b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2057b f20589m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2057b f20590n;
    public static final Set o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Set f20591p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C2057b f20592q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C2057b f20593r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C2057b f20594s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C2057b f20595t;

    static {
        C2058c c2058c = new C2058c("kotlin");
        f20580a = c2058c;
        C2058c c2058cC = c2058c.c(C2061f.e("reflect"));
        f20581b = c2058cC;
        C2058c c2058cC2 = c2058c.c(C2061f.e("collections"));
        c = c2058cC2;
        C2058c c2058cC3 = c2058c.c(C2061f.e("ranges"));
        d = c2058cC3;
        c2058c.c(C2061f.e(DebugImage.JVM)).c(C2061f.e("internal"));
        C2058c c2058cC4 = c2058c.c(C2061f.e("annotation"));
        f20582e = c2058cC4;
        C2058c c2058cC5 = c2058c.c(C2061f.e("internal"));
        c2058cC5.c(C2061f.e("ir"));
        C2058c c2058cC6 = c2058c.c(C2061f.e("coroutines"));
        f20583f = c2058cC6;
        f20584g = c2058c.c(C2061f.e("enums"));
        AbstractC1996k.c0(new C2058c[]{c2058c, c2058cC2, c2058cC3, c2058cC4, c2058cC, c2058cC5, c2058cC6});
        AbstractC2065j.a("Nothing");
        AbstractC2065j.a("Unit");
        AbstractC2065j.a("Any");
        AbstractC2065j.a("Enum");
        AbstractC2065j.a("Annotation");
        f20585h = AbstractC2065j.a("Array");
        C2057b c2057bA = AbstractC2065j.a("Boolean");
        C2057b c2057bA2 = AbstractC2065j.a("Char");
        C2057b c2057bA3 = AbstractC2065j.a("Byte");
        C2057b c2057bA4 = AbstractC2065j.a("Short");
        C2057b c2057bA5 = AbstractC2065j.a("Int");
        C2057b c2057bA6 = AbstractC2065j.a("Long");
        C2057b c2057bA7 = AbstractC2065j.a("Float");
        C2057b c2057bA8 = AbstractC2065j.a("Double");
        f20586i = AbstractC2065j.f(c2057bA3);
        f20587j = AbstractC2065j.f(c2057bA4);
        f20588k = AbstractC2065j.f(c2057bA5);
        l = AbstractC2065j.f(c2057bA6);
        AbstractC2065j.a("CharSequence");
        f20589m = AbstractC2065j.a("String");
        AbstractC2065j.a("Throwable");
        AbstractC2065j.a("Cloneable");
        AbstractC2065j.e("KProperty");
        AbstractC2065j.e("KMutableProperty");
        AbstractC2065j.e("KProperty0");
        AbstractC2065j.e("KMutableProperty0");
        AbstractC2065j.e("KProperty1");
        AbstractC2065j.e("KMutableProperty1");
        AbstractC2065j.e("KProperty2");
        AbstractC2065j.e("KMutableProperty2");
        f20590n = AbstractC2065j.e("KFunction");
        AbstractC2065j.e("KClass");
        AbstractC2065j.e("KCallable");
        AbstractC2065j.a("Comparable");
        AbstractC2065j.a("Number");
        AbstractC2065j.a("Function");
        Set setC0 = AbstractC1996k.c0(new C2057b[]{c2057bA, c2057bA2, c2057bA3, c2057bA4, c2057bA5, c2057bA6, c2057bA7, c2057bA8});
        o = setC0;
        Set set = setC0;
        int iQ = z.q(AbstractC2000o.J(set, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        for (Object obj : set) {
            C2061f c2061fI = ((C2057b) obj).i();
            m.e(c2061fI, "id.shortClassName");
            linkedHashMap.put(obj, AbstractC2065j.d(c2061fI));
        }
        AbstractC2065j.c(linkedHashMap);
        Set setC02 = AbstractC1996k.c0(new C2057b[]{f20586i, f20587j, f20588k, l});
        f20591p = setC02;
        Set set2 = setC02;
        int iQ2 = z.q(AbstractC2000o.J(set2, 10));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(iQ2 >= 16 ? iQ2 : 16);
        for (Object obj2 : set2) {
            C2061f c2061fI2 = ((C2057b) obj2).i();
            m.e(c2061fI2, "id.shortClassName");
            linkedHashMap2.put(obj2, AbstractC2065j.d(c2061fI2));
        }
        AbstractC2065j.c(linkedHashMap2);
        AbstractC1985B.r(AbstractC1985B.q(o, f20591p), f20589m);
        C2058c c2058c2 = f20583f;
        C2061f c2061fE = C2061f.e("Continuation");
        if (c2058c2 == null) {
            C2057b.a(3);
            throw null;
        }
        C2058c.j(c2061fE);
        AbstractC2065j.b("Iterator");
        AbstractC2065j.b("Iterable");
        AbstractC2065j.b("Collection");
        AbstractC2065j.b("List");
        AbstractC2065j.b("ListIterator");
        AbstractC2065j.b("Set");
        C2057b c2057bB = AbstractC2065j.b("Map");
        AbstractC2065j.b("MutableIterator");
        AbstractC2065j.b("CharIterator");
        AbstractC2065j.b("MutableIterable");
        AbstractC2065j.b("MutableCollection");
        f20592q = AbstractC2065j.b("MutableList");
        AbstractC2065j.b("MutableListIterator");
        f20593r = AbstractC2065j.b("MutableSet");
        C2057b c2057bB2 = AbstractC2065j.b("MutableMap");
        f20594s = c2057bB2;
        c2057bB.d(C2061f.e("Entry"));
        c2057bB2.d(C2061f.e("MutableEntry"));
        AbstractC2065j.a("Result");
        C2058c c2058c3 = d;
        C2061f c2061fE2 = C2061f.e("IntRange");
        if (c2058c3 == null) {
            C2057b.a(3);
            throw null;
        }
        C2058c.j(c2061fE2);
        C2058c.j(C2061f.e("LongRange"));
        C2058c.j(C2061f.e("CharRange"));
        C2058c c2058c4 = f20582e;
        C2061f c2061fE3 = C2061f.e("AnnotationRetention");
        if (c2058c4 == null) {
            C2057b.a(3);
            throw null;
        }
        C2058c.j(c2061fE3);
        C2058c.j(C2061f.e("AnnotationTarget"));
        f20595t = new C2057b(f20584g, C2061f.e("EnumEntries"));
    }
}
