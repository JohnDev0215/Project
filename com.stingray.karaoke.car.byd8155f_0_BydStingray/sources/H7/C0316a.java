package H7;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: renamed from: H7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0316a implements GenericArrayType, Type {
    public final Type l;

    public C0316a(Type type) {
        A7.m.f(type, "elementType");
        this.l = type;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            if (A7.m.a(this.l, ((GenericArrayType) obj).getGenericComponentType())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.l;
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        return H.o(this.l) + "[]";
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return getTypeName();
    }
}
