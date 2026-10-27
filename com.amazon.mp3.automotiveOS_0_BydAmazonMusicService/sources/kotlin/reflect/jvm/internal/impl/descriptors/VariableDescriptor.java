package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;

/* JADX INFO: loaded from: classes7.dex */
public interface VariableDescriptor extends ValueDescriptor {
    /* JADX INFO: renamed from: getCompileTimeInitializer */
    ConstantValue<?> mo4079getCompileTimeInitializer();

    boolean isConst();

    boolean isLateInit();

    boolean isVar();
}
