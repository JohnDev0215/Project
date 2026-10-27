package p;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes.dex */
public final class hs2 implements WildcardType {
    public final Type c;
    public final Type e;

    public hs2(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr.length != 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            bk0.i(typeArr[0]);
            this.e = null;
            this.c = typeArr[0];
            return;
        }
        typeArr2[0].getClass();
        bk0.i(typeArr2[0]);
        if (typeArr[0] != Object.class) {
            throw new IllegalArgumentException();
        }
        this.e = typeArr2[0];
        this.c = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && bk0.s(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.e;
        return type != null ? new Type[]{type} : bk0.f108p;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.c};
    }

    public final int hashCode() {
        Type type = this.e;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.c.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.e;
        if (type != null) {
            return "? super " + bk0.U(type);
        }
        Type type2 = this.c;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + bk0.U(type2);
    }
}
