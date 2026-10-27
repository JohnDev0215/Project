package com.sun.xml.bind.v2.model.nav;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes3.dex */
abstract class TypeVisitor<T, P> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    protected abstract T onClass(Class cls, P p);

    protected abstract T onGenericArray(GenericArrayType genericArrayType, P p);

    protected abstract T onParameterizdType(ParameterizedType parameterizedType, P p);

    protected abstract T onVariable(TypeVariable typeVariable, P p);

    protected abstract T onWildcard(WildcardType wildcardType, P p);

    TypeVisitor() {
    }

    public final T visit(Type type, P p) {
        if (type instanceof Class) {
            return onClass((Class) type, p);
        }
        if (type instanceof ParameterizedType) {
            return onParameterizdType((ParameterizedType) type, p);
        }
        if (type instanceof GenericArrayType) {
            return onGenericArray((GenericArrayType) type, p);
        }
        if (type instanceof WildcardType) {
            return onWildcard((WildcardType) type, p);
        }
        if (type instanceof TypeVariable) {
            return onVariable((TypeVariable) type, p);
        }
        throw new IllegalArgumentException();
    }
}
