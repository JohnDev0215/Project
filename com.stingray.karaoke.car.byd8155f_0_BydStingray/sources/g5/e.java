package G5;

import E5.M;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes.dex */
public final class e implements WildcardType {
    public final Type l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f2684m;

    public e(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr.length != 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            f.b(typeArr[0]);
            this.f2684m = null;
            this.l = f.a(typeArr[0]);
            return;
        }
        typeArr2[0].getClass();
        f.b(typeArr2[0]);
        if (typeArr[0] != Object.class) {
            throw new IllegalArgumentException();
        }
        this.f2684m = f.a(typeArr2[0]);
        this.l = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && M.c(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f2684m;
        return type != null ? new Type[]{type} : f.f2686b;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.l};
    }

    public final int hashCode() {
        Type type = this.f2684m;
        return (this.l.hashCode() + 31) ^ (type != null ? type.hashCode() + 31 : 1);
    }

    public final String toString() {
        Type type = this.f2684m;
        if (type != null) {
            return "? super " + f.k(type);
        }
        Type type2 = this.l;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + f.k(type2);
    }
}
