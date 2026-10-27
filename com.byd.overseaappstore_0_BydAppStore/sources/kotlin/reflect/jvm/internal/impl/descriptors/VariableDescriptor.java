package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VariableDescriptor extends ValueDescriptor {
    @Nullable
    /* JADX INFO: renamed from: getCompileTimeInitializer */
    ConstantValue<?> mo1340getCompileTimeInitializer();

    boolean isConst();

    boolean isLateInit();

    boolean isVar();
}
