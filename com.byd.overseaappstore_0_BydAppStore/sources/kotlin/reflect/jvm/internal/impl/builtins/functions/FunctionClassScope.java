package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class FunctionClassScope extends GivenFunctionsMemberScope {

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FunctionClassKind.values().length];
            iArr[FunctionClassKind.Function.ordinal()] = 1;
            iArr[FunctionClassKind.SuspendFunction.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionClassScope(@NotNull StorageManager storageManager, @NotNull FunctionClassDescriptor containingClass) {
        super(storageManager, containingClass);
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(containingClass, "containingClass");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope
    @NotNull
    public List<FunctionDescriptor> computeDeclaredFunctions() {
        FunctionInvokeDescriptor functionInvokeDescriptorCreate;
        int i = WhenMappings.$EnumSwitchMapping$0[((FunctionClassDescriptor) getContainingClass()).getFunctionKind().ordinal()];
        if (i == 1) {
            functionInvokeDescriptorCreate = FunctionInvokeDescriptor.Factory.create((FunctionClassDescriptor) getContainingClass(), false);
        } else {
            if (i != 2) {
                return CollectionsKt.emptyList();
            }
            functionInvokeDescriptorCreate = FunctionInvokeDescriptor.Factory.create((FunctionClassDescriptor) getContainingClass(), true);
        }
        return CollectionsKt.listOf(functionInvokeDescriptorCreate);
    }
}
