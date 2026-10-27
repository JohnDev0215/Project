package mu.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KLoggerNameResolver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u000e\b\b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0080\b¢\u0006\u0002\b\bJ&\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\t*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0080\b¢\u0006\u0002\b\bJ%\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b\"\b\b\u0000\u0010\t*\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0082\b¨\u0006\u000e"}, d2 = {"Lmu/internal/KLoggerNameResolver;", "", "()V", "name", "", "func", "Lkotlin/Function0;", "", "name$kotlin_logging", "T", "forClass", "Ljava/lang/Class;", "unwrapCompanionClass", "clazz", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public final class KLoggerNameResolver {
    public static final KLoggerNameResolver INSTANCE = new KLoggerNameResolver();

    private KLoggerNameResolver() {
    }

    public final String name$kotlin_logging(Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        String slicedName = func.getClass().getName();
        String str = slicedName;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Kt$", false, 2, (Object) null)) {
            slicedName = StringsKt.substringBefore$default(slicedName, "Kt$", (String) null, 2, (Object) null);
        } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "$", false, 2, (Object) null)) {
            slicedName = StringsKt.substringBefore$default(slicedName, "$", (String) null, 2, (Object) null);
        }
        Intrinsics.checkExpressionValueIsNotNull(slicedName, "slicedName");
        return slicedName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Class<?> unwrapCompanionClass(Class<T> clazz) {
        if (clazz.getEnclosingClass() != null) {
            try {
                Field field = clazz.getEnclosingClass().getField(clazz.getSimpleName());
                if (Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), clazz)) {
                    Class<?> enclosingClass = clazz.getEnclosingClass();
                    Intrinsics.checkExpressionValueIsNotNull(enclosingClass, "clazz.enclosingClass");
                    return enclosingClass;
                }
            } catch (Exception unused) {
            }
        }
        return clazz;
    }

    public final <T> String name$kotlin_logging(Class<T> forClass) {
        Intrinsics.checkParameterIsNotNull(forClass, "forClass");
        if (forClass.getEnclosingClass() != null) {
            try {
                Field field = forClass.getEnclosingClass().getField(forClass.getSimpleName());
                if (Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), forClass)) {
                    Class<?> enclosingClass = forClass.getEnclosingClass();
                    Intrinsics.checkExpressionValueIsNotNull(enclosingClass, "clazz.enclosingClass");
                    forClass = enclosingClass;
                }
            } catch (Exception unused) {
            }
        }
        String name = forClass.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "unwrapCompanionClass(forClass).name");
        return name;
    }
}
