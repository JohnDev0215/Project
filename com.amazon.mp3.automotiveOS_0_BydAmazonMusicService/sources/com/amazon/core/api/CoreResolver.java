package com.amazon.core.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/* JADX INFO: loaded from: classes2.dex */
public final class CoreResolver extends TypeIdResolverBase {
    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String idFromValue(Object obj) {
        return idFromValueAndType(obj, obj.getClass());
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String idFromValueAndType(Object obj, Class<?> cls) {
        return obj.getClass().getSuperclass().getCanonicalName();
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase, com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public JavaType typeFromId(DatabindContext databindContext, String str) {
        try {
            return databindContext.constructType(Class.forName(str));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
