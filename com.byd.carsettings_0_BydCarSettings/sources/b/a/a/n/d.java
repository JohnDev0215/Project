package b.a.a.n;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: GenericArrayTypeImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements GenericArrayType {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f435a;

    public d(Type type) {
        this.f435a = type;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            return this.f435a.equals(((GenericArrayType) obj).getGenericComponentType());
        }
        return false;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.f435a;
    }

    public int hashCode() {
        return this.f435a.hashCode();
    }

    public String toString() {
        Type genericComponentType = getGenericComponentType();
        StringBuilder sb = new StringBuilder();
        if (genericComponentType instanceof Class) {
            sb.append(((Class) genericComponentType).getName());
        } else {
            sb.append(genericComponentType.toString());
        }
        sb.append("[]");
        return sb.toString();
    }
}
