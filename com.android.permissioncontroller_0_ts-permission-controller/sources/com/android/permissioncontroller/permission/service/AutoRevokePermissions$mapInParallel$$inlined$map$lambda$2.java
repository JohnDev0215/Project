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

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$mapInParallel$2$1"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2", f = "KotlinUtils.kt", i = {0}, l = {901}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
public final class AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Object $it;
    final /* synthetic */ CoroutineScope $scope$inlined;
    final /* synthetic */ Function3 $transform$inlined;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2(Object obj, Continuation continuation, CoroutineScope coroutineScope, CoroutineContext coroutineContext, Function3 function3) {
        super(2, continuation);
        this.$it = obj;
        this.$scope$inlined = coroutineScope;
        this.$context$inlined = coroutineContext;
        this.$transform$inlined = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2 autoRevokePermissions$mapInParallel$$inlined$map$lambda$2 = new AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2(this.$it, completion, this.$scope$inlined, this.$context$inlined, this.$transform$inlined);
        autoRevokePermissions$mapInParallel$$inlined$map$lambda$2.p$ = (CoroutineScope) obj;
        return autoRevokePermissions$mapInParallel$$inlined$map$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Object obj) {
        return ((AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2) create(coroutineScope, (Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure($result);
            return $result;
        }
        ResultKt.throwOnFailure($result);
        CoroutineScope $this$async = this.p$;
        Function3 function3 = this.$transform$inlined;
        Object obj = this.$it;
        this.L$0 = $this$async;
        this.label = 1;
        Object objInvoke = function3.invoke($this$async, obj, this);
        return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
    }

    public final Object invokeSuspend$$forInline(Object $result) {
        CoroutineScope $this$async = this.p$;
        return this.$transform$inlined.invoke($this$async, this.$it, this);
    }
}
