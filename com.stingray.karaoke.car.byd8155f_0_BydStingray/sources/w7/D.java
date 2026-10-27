package W7;

import f8.InterfaceC1268d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class D extends A implements InterfaceC1268d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WildcardType f9871a;

    public D(WildcardType wildcardType) {
        this.f9871a = wildcardType;
    }

    @Override // W7.A
    public final Type b() {
        return this.f9871a;
    }

    public final A c() {
        A hVar;
        WildcardType wildcardType = this.f9871a;
        Type[] upperBounds = wildcardType.getUpperBounds();
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + wildcardType);
        }
        if (lowerBounds.length != 1) {
            if (upperBounds.length == 1) {
                Type type = (Type) AbstractC1996k.Y(upperBounds);
                if (!A7.m.a(type, Object.class)) {
                    A7.m.e(type, "ub");
                    boolean z6 = type instanceof Class;
                    if (z6) {
                        Class cls = (Class) type;
                        if (cls.isPrimitive()) {
                            return new y(cls);
                        }
                    }
                    hVar = ((type instanceof GenericArrayType) || (z6 && ((Class) type).isArray())) ? new h(type) : type instanceof WildcardType ? new D((WildcardType) type) : new p(type);
                }
            }
            return null;
        }
        Object objY = AbstractC1996k.Y(lowerBounds);
        A7.m.e(objY, "lowerBounds.single()");
        Type type2 = (Type) objY;
        boolean z10 = type2 instanceof Class;
        if (z10) {
            Class cls2 = (Class) type2;
            if (cls2.isPrimitive()) {
                return new y(cls2);
            }
        }
        hVar = ((type2 instanceof GenericArrayType) || (z10 && ((Class) type2).isArray())) ? new h(type2) : type2 instanceof WildcardType ? new D((WildcardType) type2) : new p(type2);
        return hVar;
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        return n7.u.l;
    }
}
