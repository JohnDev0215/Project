package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.os.UserHandle;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"<anonymous>", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$mapInParallel$2$1", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$mapInParallel$$inlined$map$lambda$1", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$1"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1", f = "AutoRevokePermissions.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {936, 941, 947, 955}, m = "invokeSuspend", n = {"$this$async", "continuation", "it", "$receiver", "continuation", "pkg", "$receiver", "$this$async", "continuation", "it", "$receiver", "continuation", "pkg", "$receiver", "packageName", "$this$async", "continuation", "it", "$receiver", "continuation", "pkg", "$receiver", "packageName", "anyPermsRevoked", "$this$async", "continuation", "it", "$receiver", "continuation", "pkg", "$receiver", "packageName", "anyPermsRevoked", "pkgPermGroups", "$this$forEachInParallel$iv", "context$iv", "scope$iv", "$this$mapInParallel$iv$iv", "$this$map$iv$iv$iv", "$this$mapTo$iv$iv$iv$iv", "destination$iv$iv$iv$iv", "item$iv$iv$iv$iv", "it$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "L$16", "L$17", "L$19", "L$20"})
public final class AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Context $context$inlined$1;
    final /* synthetic */ Object $it;
    final /* synthetic */ List $revokedApps$inlined;
    final /* synthetic */ CoroutineScope $scope$inlined;
    final /* synthetic */ long $sessionId$inlined;
    final /* synthetic */ UserHandle $user$inlined;
    final /* synthetic */ Map $userStats$inlined;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$16;
    Object L$17;
    Object L$18;
    Object L$19;
    Object L$2;
    Object L$20;
    Object L$21;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1(Object obj, Continuation continuation, CoroutineScope coroutineScope, CoroutineContext coroutineContext, UserHandle userHandle, Context context, Map map, long j, List list) {
        super(2, continuation);
        this.$it = obj;
        this.$scope$inlined = coroutineScope;
        this.$context$inlined = coroutineContext;
        this.$user$inlined = userHandle;
        this.$context$inlined$1 = context;
        this.$userStats$inlined = map;
        this.$sessionId$inlined = j;
        this.$revokedApps$inlined = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1 autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1 = new AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1(this.$it, completion, this.$scope$inlined, this.$context$inlined, this.$user$inlined, this.$context$inlined$1, this.$userStats$inlined, this.$sessionId$inlined, this.$revokedApps$inlined);
        autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1.p$ = (CoroutineScope) obj;
        return autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x041c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x03a5 -> B:51:0x03cd). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r43) {
        /*
            Method dump skipped, instruction units count: 1087
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
