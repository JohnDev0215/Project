package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class TypeCapabilitiesKt {
    @Nullable
    public static final CustomTypeVariable getCustomTypeVariable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Object objUnwrap = kotlinType.unwrap();
        CustomTypeVariable customTypeVariable = objUnwrap instanceof CustomTypeVariable ? (CustomTypeVariable) objUnwrap : null;
        if (customTypeVariable != null && customTypeVariable.isTypeVariable()) {
            return customTypeVariable;
        }
        return null;
    }

    public static final boolean isCustomTypeVariable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Object objUnwrap = kotlinType.unwrap();
        CustomTypeVariable customTypeVariable = objUnwrap instanceof CustomTypeVariable ? (CustomTypeVariable) objUnwrap : null;
        if (customTypeVariable == null) {
            return false;
        }
        return customTypeVariable.isTypeVariable();
    }
}
