package G5;

import E5.M;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class d implements ParameterizedType {
    public final Type l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f2682m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Type[] f2683n;

    public d(Type type, Type type2, Type... typeArr) {
        if (type2 instanceof Class) {
            Class<?> enclosingClass = ((Class) type2).getEnclosingClass();
            if (type != null) {
                if (enclosingClass == null || M.e(type) != enclosingClass) {
                    throw new IllegalArgumentException("unexpected owner type for " + type2 + ": " + type);
                }
            } else if (enclosingClass != null) {
                throw new IllegalArgumentException("unexpected owner type for " + type2 + ": null");
            }
        }
        this.l = type == null ? null : f.a(type);
        this.f2682m = f.a(type2);
        this.f2683n = (Type[]) typeArr.clone();
        int i7 = 0;
        while (true) {
            Type[] typeArr2 = this.f2683n;
            if (i7 >= typeArr2.length) {
                return;
            }
            typeArr2[i7].getClass();
            f.b(this.f2683n[i7]);
            Type[] typeArr3 = this.f2683n;
            typeArr3[i7] = f.a(typeArr3[i7]);
            i7++;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && M.c(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f2683n.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.l;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f2682m;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.f2683n) ^ this.f2682m.hashCode();
        Set set = f.f2685a;
        Type type = this.l;
        return (type != null ? type.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        Type[] typeArr = this.f2683n;
        StringBuilder sb = new StringBuilder((typeArr.length + 1) * 30);
        sb.append(f.k(this.f2682m));
        if (typeArr.length == 0) {
            return sb.toString();
        }
        sb.append("<");
        sb.append(f.k(typeArr[0]));
        for (int i7 = 1; i7 < typeArr.length; i7++) {
            sb.append(", ");
            sb.append(f.k(typeArr[i7]));
        }
        sb.append(">");
        return sb.toString();
    }
}
