package l5;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: renamed from: l5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1773a implements GenericArrayType, Serializable {
    public final Type l;

    public C1773a(Type type) {
        Objects.requireNonNull(type);
        this.l = AbstractC1776d.a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && AbstractC1776d.e(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.l;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return AbstractC1776d.k(this.l) + "[]";
    }
}
