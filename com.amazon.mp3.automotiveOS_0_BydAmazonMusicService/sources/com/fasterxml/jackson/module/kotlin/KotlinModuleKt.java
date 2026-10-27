package com.fasterxml.jackson.module.kotlin;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinModule.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0002\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"metadataFqName", "", "isKotlinClass", "", "Ljava/lang/Class;", "jackson-module-kotlin"}, k = 2, mv = {1, 4, 1})
public final class KotlinModuleKt {
    private static final String metadataFqName = "kotlin.Metadata";

    public static final boolean isKotlinClass(Class<?> isKotlinClass) {
        Intrinsics.checkNotNullParameter(isKotlinClass, "$this$isKotlinClass");
        Annotation[] declaredAnnotations = isKotlinClass.getDeclaredAnnotations();
        Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "declaredAnnotations");
        for (Annotation annotation : declaredAnnotations) {
            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)).getName(), metadataFqName)) {
                return true;
            }
        }
        return false;
    }
}
