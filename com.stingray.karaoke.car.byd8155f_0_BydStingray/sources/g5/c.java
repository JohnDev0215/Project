package G5;

import E5.M;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class c implements GenericArrayType {
    public final Type l;

    public c(Type type) {
        this.l = f.a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && M.c(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.l;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return f.k(this.l) + "[]";
    }
}
