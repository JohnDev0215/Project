package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: KotlinNamesAnnotationIntrospector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"isInlineClass", "", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "jackson-module-kotlin"}, k = 2, mv = {1, 4, 1})
public final class KotlinNamesAnnotationIntrospectorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInlineClass(AnnotatedMethod annotatedMethod) {
        Class<?> declaringClass = annotatedMethod.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
        Method[] declaredMethods = declaringClass.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaringClass.declaredMethods");
        for (Method it : declaredMethods) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            String name = it.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            if (StringsKt__StringsKt.contains$default((CharSequence) name, '-', false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
