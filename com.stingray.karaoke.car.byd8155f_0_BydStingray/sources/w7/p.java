package W7;

import f8.InterfaceC1268d;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class p extends A implements InterfaceC1268d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f9893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f9894b;

    public p(Type type) {
        r nVar;
        A7.m.f(type, "reflectType");
        this.f9893a = type;
        if (type instanceof Class) {
            nVar = new n((Class) type);
        } else if (type instanceof TypeVariable) {
            nVar = new B((TypeVariable) type);
        } else {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalStateException("Not a classifier type (" + type.getClass() + "): " + type);
            }
            Type rawType = ((ParameterizedType) type).getRawType();
            A7.m.d(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
            nVar = new n((Class) rawType);
        }
        this.f9894b = nVar;
    }

    @Override // W7.A, f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        return null;
    }

    @Override // W7.A
    public final Type b() {
        return this.f9893a;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList c() {
        /*
            r5 = this;
            java.lang.reflect.Type r5 = r5.f9893a
            java.util.List r5 = W7.AbstractC0839c.c(r5)
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = n7.AbstractC2000o.J(r5, r1)
            r0.<init>(r1)
            java.util.Iterator r5 = r5.iterator()
        L15:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L66
            java.lang.Object r1 = r5.next()
            java.lang.reflect.Type r1 = (java.lang.reflect.Type) r1
            java.lang.String r2 = "type"
            A7.m.f(r1, r2)
            boolean r2 = r1 instanceof java.lang.Class
            if (r2 == 0) goto L39
            r3 = r1
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r4 = r3.isPrimitive()
            if (r4 == 0) goto L39
            W7.y r1 = new W7.y
            r1.<init>(r3)
            goto L62
        L39:
            boolean r3 = r1 instanceof java.lang.reflect.GenericArrayType
            if (r3 != 0) goto L5c
            if (r2 == 0) goto L49
            r2 = r1
            java.lang.Class r2 = (java.lang.Class) r2
            boolean r2 = r2.isArray()
            if (r2 == 0) goto L49
            goto L5c
        L49:
            boolean r2 = r1 instanceof java.lang.reflect.WildcardType
            if (r2 == 0) goto L56
            W7.D r2 = new W7.D
            java.lang.reflect.WildcardType r1 = (java.lang.reflect.WildcardType) r1
            r2.<init>(r1)
        L54:
            r1 = r2
            goto L62
        L56:
            W7.p r2 = new W7.p
            r2.<init>(r1)
            goto L54
        L5c:
            W7.h r2 = new W7.h
            r2.<init>(r1)
            goto L54
        L62:
            r0.add(r1)
            goto L15
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: W7.p.c():java.util.ArrayList");
    }

    public final boolean d() {
        Type type = this.f9893a;
        if (!(type instanceof Class)) {
            return false;
        }
        TypeVariable[] typeParameters = ((Class) type).getTypeParameters();
        A7.m.e(typeParameters, "getTypeParameters()");
        return !(typeParameters.length == 0);
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        return n7.u.l;
    }
}
