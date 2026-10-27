package l2;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: GenericArrayTypeImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements GenericArrayType {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Type f6191d;

    public d(Type type) {
        this.f6191d = type;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            return this.f6191d.equals(((GenericArrayType) obj).getGenericComponentType());
        }
        return false;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.f6191d;
    }

    public int hashCode() {
        return this.f6191d.hashCode();
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
