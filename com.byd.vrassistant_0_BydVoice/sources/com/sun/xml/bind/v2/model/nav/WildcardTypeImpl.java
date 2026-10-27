package com.sun.xml.bind.v2.model.nav;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class WildcardTypeImpl implements WildcardType {
    private final Type[] lb;
    private final Type[] ub;

    public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
        this.ub = typeArr;
        this.lb = typeArr2;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() {
        return this.ub;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() {
        return this.lb;
    }

    public int hashCode() {
        return Arrays.hashCode(this.lb) ^ Arrays.hashCode(this.ub);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) obj;
        return Arrays.equals(wildcardType.getLowerBounds(), this.lb) && Arrays.equals(wildcardType.getUpperBounds(), this.ub);
    }
}
