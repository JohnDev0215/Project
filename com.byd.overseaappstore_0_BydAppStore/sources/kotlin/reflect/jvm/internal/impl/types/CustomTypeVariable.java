package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface CustomTypeVariable {
    boolean isTypeVariable();

    @NotNull
    KotlinType substitutionResult(@NotNull KotlinType kotlinType);
}
