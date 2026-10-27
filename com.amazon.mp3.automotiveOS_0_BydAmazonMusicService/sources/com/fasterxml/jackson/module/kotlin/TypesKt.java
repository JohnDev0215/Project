package com.fasterxml.jackson.module.kotlin;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.KTypesJvm;

/* JADX INFO: compiled from: Types.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a\u0014\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001*\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"erasedType", "Ljava/lang/Class;", "", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "jackson-module-kotlin"}, k = 2, mv = {1, 4, 1})
public final class TypesKt {
    public static final Class<Object> erasedType(Type erasedType) {
        Intrinsics.checkNotNullParameter(erasedType, "$this$erasedType");
        if (erasedType instanceof Class) {
            return (Class) erasedType;
        }
        if (erasedType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) erasedType).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "this.getRawType()");
            return erasedType(rawType);
        }
        if (erasedType instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) erasedType).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "this.getGenericComponentType()");
            Object testArray = Array.newInstance((Class<?>) erasedType(genericComponentType), 0);
            Intrinsics.checkNotNullExpressionValue(testArray, "testArray");
            return testArray.getClass();
        }
        if (erasedType instanceof TypeVariable) {
            throw new IllegalStateException("Not sure what to do here yet");
        }
        if (erasedType instanceof WildcardType) {
            Type type = ((WildcardType) erasedType).getUpperBounds()[0];
            Intrinsics.checkNotNullExpressionValue(type, "this.getUpperBounds()[0]");
            return erasedType(type);
        }
        throw new IllegalStateException("Should not get here.");
    }

    public static final Class<? extends Object> erasedType(KType erasedType) {
        Intrinsics.checkNotNullParameter(erasedType, "$this$erasedType");
        return JvmClassMappingKt.getJavaClass((KClass) KTypesJvm.getJvmErasure(erasedType));
    }
}
