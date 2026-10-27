package H7;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class D implements ParameterizedType, Type {
    public final Class l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f2844m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Type[] f2845n;

    public D(Class cls, Type type, ArrayList arrayList) {
        this.l = cls;
        this.f2844m = type;
        this.f2845n = (Type[]) arrayList.toArray(new Type[0]);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            if (A7.m.a(this.l, parameterizedType.getRawType()) && A7.m.a(this.f2844m, parameterizedType.getOwnerType())) {
                if (Arrays.equals(this.f2845n, parameterizedType.getActualTypeArguments())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return this.f2845n;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.f2844m;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.l;
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        StringBuilder sb = new StringBuilder();
        Class cls = this.l;
        Type type = this.f2844m;
        if (type != null) {
            sb.append(H.o(type));
            sb.append("$");
            sb.append(cls.getSimpleName());
        } else {
            sb.append(H.o(cls));
        }
        Type[] typeArr = this.f2845n;
        if (typeArr.length != 0) {
            AbstractC1996k.U(typeArr, sb, ", ", "<", ">", -1, "...", C.f2843t);
        }
        return sb.toString();
    }

    public final int hashCode() {
        int iHashCode = this.l.hashCode();
        Type type = this.f2844m;
        return Arrays.hashCode(this.f2845n) ^ (iHashCode ^ (type != null ? type.hashCode() : 0));
    }

    public final String toString() {
        return getTypeName();
    }
}
