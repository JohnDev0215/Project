package com.android.permissioncontroller.permission.service;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"<anonymous>", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$mapInParallel$2$1", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$mapInParallel$$inlined$map$lambda$1"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1", f = "KotlinUtils.kt", i = {0, 0, 0, 0}, l = {931}, m = "invokeSuspend", n = {"$this$async", "continuation", "it", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3"})
public final class AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Object $it;
    final /* synthetic */ CoroutineScope $scope$inlined;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1(Object obj, Continuation continuation, CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function3 function3) {
        super(2, continuation);
        this.$it = obj;
        this.$scope$inlined = coroutineScope;
        this.$context$inlined = coroutineContext;
        this.$action$inlined = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1 autoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1 = new AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1(this.$it, completion, this.$scope$inlined, this.$context$inlined, this.$action$inlined);
        autoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1.p$ = (CoroutineScope) obj;
        return autoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend$$forInline(Object $result) {
        CoroutineScope $this$async = this.p$;
        Object it = this.$it;
        this.$action$inlined.invoke($this$async, it, this);
        Object it2 = Unit.INSTANCE;
        return it2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure($result);
            CoroutineScope $this$async = this.p$;
            Object it = this.$it;
            AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1 continuation = this;
            Function3 function3 = this.$action$inlined;
            this.L$0 = $this$async;
            this.L$1 = continuation;
            this.L$2 = it;
            this.L$3 = $this$async;
            this.label = 1;
            if (function3.invoke($this$async, it, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object it2 = this.L$2;
            ResultKt.throwOnFailure($result);
        }
        return Unit.INSTANCE;
    }
}
