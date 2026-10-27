package l5;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: l5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1774b implements ParameterizedType, Serializable {
    public final Type l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f18633m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Type[] f18634n;

    public C1774b(Type type, Type type2, Type... typeArr) {
        Objects.requireNonNull(type2);
        if (type == null && (type2 instanceof Class)) {
            Class cls = (Class) type2;
            if (!Modifier.isStatic(cls.getModifiers()) && cls.getDeclaringClass() != null) {
                throw new IllegalArgumentException("Must specify owner type for " + type2);
            }
        }
        this.l = type == null ? null : AbstractC1776d.a(type);
        this.f18633m = AbstractC1776d.a(type2);
        Type[] typeArr2 = (Type[]) typeArr.clone();
        this.f18634n = typeArr2;
        int length = typeArr2.length;
        for (int i7 = 0; i7 < length; i7++) {
            Objects.requireNonNull(this.f18634n[i7]);
            AbstractC1776d.c(this.f18634n[i7]);
            Type[] typeArr3 = this.f18634n;
            typeArr3[i7] = AbstractC1776d.a(typeArr3[i7]);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && AbstractC1776d.e(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f18634n.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.l;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f18633m;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.f18634n) ^ this.f18633m.hashCode();
        Type type = this.l;
        return (type != null ? type.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        Type[] typeArr = this.f18634n;
        int length = typeArr.length;
        Type type = this.f18633m;
        if (length == 0) {
            return AbstractC1776d.k(type);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(AbstractC1776d.k(type));
        sb.append("<");
        sb.append(AbstractC1776d.k(typeArr[0]));
        for (int i7 = 1; i7 < length; i7++) {
            sb.append(", ");
            sb.append(AbstractC1776d.k(typeArr[i7]));
        }
        sb.append(">");
        return sb.toString();
    }
}
