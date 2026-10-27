package p;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class fs2 implements GenericArrayType {
    public final Type c;

    public fs2(Type type) {
        this.c = type;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && bk0.s(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.c;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return bk0.U(this.c) + "[]";
    }
}
