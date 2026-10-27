package C8;

import A7.m;
import Q8.o;
import k8.AbstractC1709b;
import o8.C2058c;
import p8.C2132g;
import p8.C2138m;

/* JADX INFO: loaded from: classes.dex */
public final class a extends A8.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f1016m;

    static {
        C2132g c2132g = new C2132g();
        AbstractC1709b.a(c2132g);
        C2138m c2138m = AbstractC1709b.f18313a;
        m.e(c2138m, "packageFqName");
        C2138m c2138m2 = AbstractC1709b.c;
        m.e(c2138m2, "constructorAnnotation");
        C2138m c2138m3 = AbstractC1709b.f18314b;
        m.e(c2138m3, "classAnnotation");
        C2138m c2138m4 = AbstractC1709b.d;
        m.e(c2138m4, "functionAnnotation");
        C2138m c2138m5 = AbstractC1709b.f18315e;
        m.e(c2138m5, "propertyAnnotation");
        C2138m c2138m6 = AbstractC1709b.f18316f;
        m.e(c2138m6, "propertyGetterAnnotation");
        C2138m c2138m7 = AbstractC1709b.f18317g;
        m.e(c2138m7, "propertySetterAnnotation");
        C2138m c2138m8 = AbstractC1709b.f18319i;
        m.e(c2138m8, "enumEntryAnnotation");
        C2138m c2138m9 = AbstractC1709b.f18318h;
        m.e(c2138m9, "compileTimeValue");
        C2138m c2138m10 = AbstractC1709b.f18320j;
        m.e(c2138m10, "parameterAnnotation");
        C2138m c2138m11 = AbstractC1709b.f18321k;
        m.e(c2138m11, "typeAnnotation");
        C2138m c2138m12 = AbstractC1709b.l;
        m.e(c2138m12, "typeParameterAnnotation");
        f1016m = new a(c2132g, c2138m, c2138m2, c2138m3, c2138m4, c2138m5, c2138m6, c2138m7, c2138m8, c2138m9, c2138m10, c2138m11, c2138m12);
    }

    public static String a(C2058c c2058c) {
        String strB;
        m.f(c2058c, "fqName");
        StringBuilder sb = new StringBuilder();
        sb.append(o.w0(c2058c.b(), '.', '/'));
        sb.append('/');
        if (c2058c.d()) {
            strB = "default-package";
        } else {
            strB = c2058c.f().b();
            m.e(strB, "fqName.shortName().asString()");
        }
        sb.append(strB.concat(".kotlin_builtins"));
        return sb.toString();
    }
}
