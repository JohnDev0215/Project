package a8;

import A7.m;
import N0.q;
import N7.n;
import W7.AbstractC0839c;
import W7.C0840d;
import Z7.x;
import b8.InterfaceC1013h;
import d8.C1119f;
import f8.InterfaceC1266b;
import n7.z;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2061f f11205a = C2061f.e("message");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2061f f11206b = C2061f.e("allowedTargets");
    public static final C2061f c = C2061f.e("value");
    public static final Object d = z.s(new m7.i(n.f4861t, x.c), new m7.i(n.f4864w, x.d), new m7.i(n.f4865x, x.f10966f));

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, java.util.Map] */
    public static InterfaceC1013h a(C2058c c2058c, InterfaceC1266b interfaceC1266b, q qVar) {
        C0840d c0840dA;
        m.f(c2058c, "kotlinName");
        m.f(interfaceC1266b, "annotationOwner");
        m.f(qVar, "c");
        if (c2058c.equals(n.f4855m)) {
            C2058c c2058c2 = x.f10965e;
            m.e(c2058c2, "DEPRECATED_ANNOTATION");
            C0840d c0840dA2 = interfaceC1266b.a(c2058c2);
            if (c0840dA2 != null) {
                return new g(c0840dA2, qVar);
            }
        }
        C2058c c2058c3 = (C2058c) d.get(c2058c);
        if (c2058c3 == null || (c0840dA = interfaceC1266b.a(c2058c3)) == null) {
            return null;
        }
        return b(qVar, c0840dA, false);
    }

    public static InterfaceC1013h b(q qVar, C0840d c0840d, boolean z6) {
        m.f(c0840d, "annotation");
        m.f(qVar, "c");
        C2057b c2057bA = AbstractC0839c.a(z.i(z.g(c0840d.f9880a)));
        if (c2057bA.equals(C2057b.j(x.c))) {
            return new j(c0840d, qVar);
        }
        if (c2057bA.equals(C2057b.j(x.d))) {
            return new i(c0840d, qVar);
        }
        if (c2057bA.equals(C2057b.j(x.f10966f))) {
            return new b(qVar, c0840d, n.f4865x);
        }
        if (c2057bA.equals(C2057b.j(x.f10965e))) {
            return null;
        }
        return new C1119f(qVar, c0840d, z6);
    }
}
