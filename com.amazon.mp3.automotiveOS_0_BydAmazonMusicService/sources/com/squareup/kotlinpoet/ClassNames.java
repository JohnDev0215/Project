package com.squareup.kotlinpoet;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ClassName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0005H\u0007¢\u0006\u0004\b\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Ljava/lang/Class;", "Lcom/squareup/kotlinpoet/ClassName;", "get", "(Ljava/lang/Class;)Lcom/squareup/kotlinpoet/ClassName;", "asClassName", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Lcom/squareup/kotlinpoet/ClassName;", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "ClassNames")
public final class ClassNames {
    @JvmName(name = "get")
    public static final ClassName get(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        if (!(!cls.isPrimitive())) {
            throw new IllegalArgumentException("primitive types cannot be represented as a ClassName".toString());
        }
        if (!(!Intrinsics.areEqual(Void.TYPE, cls))) {
            throw new IllegalArgumentException("'void' type cannot be represented as a ClassName".toString());
        }
        if (!(!cls.isArray())) {
            throw new IllegalArgumentException("array types cannot be represented as a ClassName".toString());
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(cls.getSimpleName());
            Class<?> enclosingClass = cls.getEnclosingClass();
            if (enclosingClass == null) {
                break;
            }
            cls = enclosingClass;
        }
        String name = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name, "c.name");
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, '.', 0, false, 6, (Object) null);
        if (iLastIndexOf$default != -1) {
            String name2 = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "c.name");
            String strSubstring = name2.substring(0, iLastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
        }
        CollectionsKt___CollectionsJvmKt.reverse(arrayList);
        return new ClassName(arrayList, false, null, null, 14, null);
    }

    @JvmName(name = "get")
    public static final ClassName get(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        String qualifiedName = kClass.getQualifiedName();
        if (qualifiedName != null) {
            return ClassName.INSTANCE.bestGuess(qualifiedName);
        }
        throw new IllegalArgumentException(kClass + " cannot be represented as a ClassName");
    }
}
