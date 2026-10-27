package com.sun.xml.bind.v2.model.nav;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
final class GenericArrayTypeImpl implements GenericArrayType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Type genericComponentType;

    GenericArrayTypeImpl(Type type) {
        this.genericComponentType = type;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.genericComponentType;
    }

    public String toString() {
        Type genericComponentType = getGenericComponentType();
        StringBuilder sb = new StringBuilder();
        if (genericComponentType instanceof Class) {
            sb.append(((Class) genericComponentType).getName());
        } else {
            sb.append(genericComponentType.toString());
        }
        sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType)) {
            return false;
        }
        return this.genericComponentType.equals(((GenericArrayType) obj).getGenericComponentType());
    }

    public int hashCode() {
        return this.genericComponentType.hashCode();
    }
}
