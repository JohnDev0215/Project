package com.android.permissioncontroller.permission.service;

import android.content.Context;
import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
import com.android.permissioncontroller.permission.service.PermissionControllerServiceModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PermissionControllerServiceModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$AutoRevokePermissionsDumpProto;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.android.permissioncontroller.permission.service.PermissionControllerServiceModel$onDump$2$autoRevokeDump$1", f = "PermissionControllerServiceModel.kt", i = {0}, l = {285}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
final class PermissionControllerServiceModel$onDump$2$autoRevokeDump$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ PermissionControllerServiceModel.AnonymousClass2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PermissionControllerServiceModel$onDump$2$autoRevokeDump$1(PermissionControllerServiceModel.AnonymousClass2 anonymousClass2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = anonymousClass2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PermissionControllerServiceModel$onDump$2$autoRevokeDump$1 permissionControllerServiceModel$onDump$2$autoRevokeDump$1 = new PermissionControllerServiceModel$onDump$2$autoRevokeDump$1(this.this$0, completion);
        permissionControllerServiceModel$onDump$2$autoRevokeDump$1.p$ = (CoroutineScope) obj;
        return permissionControllerServiceModel$onDump$2$autoRevokeDump$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto> continuation) {
        return ((PermissionControllerServiceModel$onDump$2$autoRevokeDump$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        Context context = (Context) PermissionControllerServiceModel.this.service;
        this.L$0 = $this$async;
        this.label = 1;
        Object objDumpAutoRevokePermissions = AutoRevokePermissions.dumpAutoRevokePermissions(context, this);
        return objDumpAutoRevokePermissions == coroutine_suspended ? coroutine_suspended : objDumpAutoRevokePermissions;
    }
}
