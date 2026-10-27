package p;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class gs2 implements ParameterizedType {
    public final Type c;
    public final Type e;
    public final Type[] k;

    public gs2(Type type, Type type2, Type... typeArr) {
        if (type2 instanceof Class) {
            if ((type == null) != (((Class) type2).getEnclosingClass() == null)) {
                throw new IllegalArgumentException();
            }
        }
        for (Type type3 : typeArr) {
            Objects.requireNonNull(type3, "typeArgument == null");
            bk0.i(type3);
        }
        this.c = type;
        this.e = type2;
        this.k = (Type[]) typeArr.clone();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && bk0.s(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.k.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.c;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.e;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.k) ^ this.e.hashCode();
        Type type = this.c;
        return iHashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        Type[] typeArr = this.k;
        int length = typeArr.length;
        Type type = this.e;
        if (length == 0) {
            return bk0.U(type);
        }
        StringBuilder sb = new StringBuilder((typeArr.length + 1) * 30);
        sb.append(bk0.U(type));
        sb.append("<");
        sb.append(bk0.U(typeArr[0]));
        for (int i = 1; i < typeArr.length; i++) {
            sb.append(", ");
            sb.append(bk0.U(typeArr[i]));
        }
        sb.append(">");
        return sb.toString();
    }
}
