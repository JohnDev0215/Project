package H7;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class I implements WildcardType, Type {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final I f2850n = new I(null, null);
    public final Type l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type f2851m;

    public I(Type type, Type type2) {
        this.l = type;
        this.f2851m = type2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) obj;
            if (Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f2851m;
        return type == null ? new Type[0] : new Type[]{type};
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        Type type = this.f2851m;
        if (type != null) {
            return "? super " + H.o(type);
        }
        Type type2 = this.l;
        if (type2 == null || A7.m.a(type2, Object.class)) {
            return "?";
        }
        return "? extends " + H.o(type2);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        Type type = this.l;
        if (type == null) {
            type = Object.class;
        }
        return new Type[]{type};
    }

    public final int hashCode() {
        return Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
    }

    public final String toString() {
        return getTypeName();
    }
}
