package com.byd.data.common.base.gson;

import com.byd.data.common.b.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class ParamType implements ParameterizedType {
    private final Type[] types;

    public ParamType(Type[] typeArr) {
        this.types = typeArr;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.types;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return b.class;
    }
}
