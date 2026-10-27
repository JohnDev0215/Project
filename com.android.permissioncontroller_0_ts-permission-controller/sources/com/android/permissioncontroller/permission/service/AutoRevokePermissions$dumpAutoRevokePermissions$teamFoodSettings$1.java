package com.android.permissioncontroller.permission.service;

import android.content.Context;
import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
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

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$TeamFoodSettingsProto;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1", f = "AutoRevokePermissions.kt", i = {0}, l = {173}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
final class AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AutoRevokePermissionsProto.TeamFoodSettingsProto>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1 autoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1 = new AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1(this.$context, completion);
        autoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1.p$ = (CoroutineScope) obj;
        return autoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AutoRevokePermissionsProto.TeamFoodSettingsProto> continuation) {
        return ((AutoRevokePermissions$dumpAutoRevokePermissions$teamFoodSettings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$async;
        Object objDump;
        CoroutineScope $this$async2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure($result);
            $this$async = this.p$;
            TeamfoodSettings teamfoodSettings = TeamfoodSettings.INSTANCE.get(this.$context);
            if (teamfoodSettings != null) {
                this.L$0 = $this$async;
                this.label = 1;
                objDump = teamfoodSettings.dump(this);
                if (objDump == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this$async2 = $this$async;
            }
            return AutoRevokePermissionsProto.TeamFoodSettingsProto.newBuilder().build();
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        $this$async2 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure($result);
        objDump = $result;
        AutoRevokePermissionsProto.TeamFoodSettingsProto teamFoodSettingsProto = (AutoRevokePermissionsProto.TeamFoodSettingsProto) objDump;
        if (teamFoodSettingsProto != null) {
            return teamFoodSettingsProto;
        }
        $this$async = $this$async2;
        return AutoRevokePermissionsProto.TeamFoodSettingsProto.newBuilder().build();
    }
}
