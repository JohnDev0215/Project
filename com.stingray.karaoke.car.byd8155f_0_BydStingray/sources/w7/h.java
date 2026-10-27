package W7;

import f8.InterfaceC1268d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A implements InterfaceC1268d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f9884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A f9885b;
    public final n7.u c;

    /* JADX WARN: Multi-variable type inference failed */
    public h(Type type) {
        A yVar;
        A yVar2;
        this.f9884a = type;
        if (!(type instanceof GenericArrayType)) {
            if (type instanceof Class) {
                Class cls = (Class) type;
                if (cls.isArray()) {
                    Class<?> componentType = cls.getComponentType();
                    A7.m.e(componentType, "getComponentType()");
                    yVar = componentType.isPrimitive() ? new y(componentType) : ((componentType instanceof GenericArrayType) || componentType.isArray()) ? new h(componentType) : componentType instanceof WildcardType ? new D((WildcardType) componentType) : new p(componentType);
                }
            }
            throw new IllegalArgumentException("Not an array type (" + type.getClass() + "): " + type);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        A7.m.e(genericComponentType, "genericComponentType");
        boolean z6 = genericComponentType instanceof Class;
        if (z6) {
            Class cls2 = (Class) genericComponentType;
            if (cls2.isPrimitive()) {
                yVar2 = new y(cls2);
                this.f9885b = yVar2;
                this.c = n7.u.l;
            }
        }
        yVar = ((genericComponentType instanceof GenericArrayType) || (z6 && ((Class) genericComponentType).isArray())) ? new h(genericComponentType) : genericComponentType instanceof WildcardType ? new D((WildcardType) genericComponentType) : new p(genericComponentType);
        yVar2 = yVar;
        this.f9885b = yVar2;
        this.c = n7.u.l;
    }

    @Override // W7.A
    public final Type b() {
        return this.f9884a;
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        return this.c;
    }
}
