package com.amazon.music.skyfire.ui.utils;

import com.amazon.music.skyfire.ui.ktx.AnyKt;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Reflection.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003\u001a\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"declaredInheritedFields", "", "Ljava/lang/reflect/Field;", "Ljava/lang/Class;", "getDeclaredFieldOrNull", "name", "", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ReflectionKt {
    public static final List<Field> declaredInheritedFields(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            Field[] declaredFields = cls.getDeclaredFields();
            Intrinsics.checkNotNullExpressionValue(declaredFields, "c.declaredFields");
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, declaredFields);
            cls = cls.getSuperclass();
        }
        return AnyKt.unmodifiable(arrayList);
    }

    public static final Field getDeclaredFieldOrNull(Class<?> cls, String name) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            return cls.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
