package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import p.f60;

/* JADX INFO: renamed from: com.google.gson.internal.$Gson$Types$WildcardTypeImpl, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
final class C$Gson$Types$WildcardTypeImpl implements WildcardType, Serializable {
    private static final long serialVersionUID = 0;
    private final Type lowerBound;
    private final Type upperBound;

    public C$Gson$Types$WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
        f60.e(typeArr2.length <= 1);
        f60.e(typeArr.length == 1);
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            a.b(typeArr[0]);
            this.lowerBound = null;
            this.upperBound = a.a(typeArr[0]);
            return;
        }
        typeArr2[0].getClass();
        a.b(typeArr2[0]);
        f60.e(typeArr[0] == Object.class);
        this.lowerBound = a.a(typeArr2[0]);
        this.upperBound = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && a.d(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.lowerBound;
        return type != null ? new Type[]{type} : a.a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.upperBound};
    }

    public final int hashCode() {
        Type type = this.lowerBound;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
    }

    public final String toString() {
        if (this.lowerBound != null) {
            return "? super " + a.h(this.lowerBound);
        }
        if (this.upperBound == Object.class) {
            return "?";
        }
        return "? extends " + a.h(this.upperBound);
    }
}
