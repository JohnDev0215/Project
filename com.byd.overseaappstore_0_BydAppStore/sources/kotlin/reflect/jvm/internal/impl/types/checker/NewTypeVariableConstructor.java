package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface NewTypeVariableConstructor {
    @Nullable
    TypeParameterDescriptor getOriginalTypeParameter();
}
