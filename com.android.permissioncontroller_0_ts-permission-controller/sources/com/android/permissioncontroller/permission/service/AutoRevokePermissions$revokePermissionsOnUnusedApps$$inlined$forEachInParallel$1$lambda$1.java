package com.android.permissioncontroller.permission.service;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.content.pm.PackageManager;
import com.android.permissioncontroller.DumpableLog;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ô\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\t"}, d2 = {"<anonymous>", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$mapInParallel$2$1", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$mapInParallel$$inlined$map$lambda$2", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$forEachInParallel$$inlined$mapInParallel$2", "com/android/permissioncontroller/permission/service/AutoRevokePermissions$$special$$inlined$forEachInParallel$1"}, k = 3, mv = {1, 1, 16})
public final class AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AtomicBoolean $anyPermsRevoked$inlined;
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Object $it;
    final /* synthetic */ String $packageName$inlined;
    final /* synthetic */ CoroutineScope $scope$inlined;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1(Object obj, Continuation continuation, CoroutineScope coroutineScope, CoroutineContext coroutineContext, String str, AtomicBoolean atomicBoolean, AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1 autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1) {
        super(2, continuation);
        this.$it = obj;
        this.$scope$inlined = coroutineScope;
        this.$context$inlined = coroutineContext;
        this.$packageName$inlined = str;
        this.$anyPermsRevoked$inlined = atomicBoolean;
        this.this$0 = autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1 autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1 = new AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1(this.$it, completion, this.$scope$inlined, this.$context$inlined, this.$packageName$inlined, this.$anyPermsRevoked$inlined, this.this$0);
        autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1.p$ = (CoroutineScope) obj;
        return autoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        Object initializedValue$default;
        CoroutineScope $receiver;
        Continuation continuation;
        Continuation continuation2;
        int i;
        Continuation continuation3;
        String groupName;
        Map.Entry $dstr$groupName$_u24__u24;
        Continuation continuation4;
        Date date;
        Long lBoxLong;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        boolean z = false;
        if (i2 == 0) {
            ResultKt.throwOnFailure($result);
            CoroutineScope $this$async = this.p$;
            Object it = this.$it;
            AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1 continuation5 = this;
            AutoRevokePermissions$revokePermissionsOnUnusedApps$$inlined$forEachInParallel$1$lambda$1 continuation6 = this;
            Map.Entry $dstr$groupName$_u24__u242 = (Map.Entry) it;
            String groupName2 = (String) $dstr$groupName$_u24__u242.getKey();
            if (!Intrinsics.areEqual(groupName2, PackagePermissionsLiveData.NON_RUNTIME_NORMAL_PERMS)) {
                LightAppPermGroupLiveData lightAppPermGroupLiveData = (LightAppPermGroupLiveData) DataRepositoryKt.get(LightAppPermGroupLiveData.INSTANCE, this.$packageName$inlined, groupName2, this.this$0.$user$inlined);
                this.L$0 = $this$async;
                this.L$1 = continuation5;
                this.L$2 = it;
                this.L$3 = $this$async;
                this.L$4 = continuation6;
                this.L$5 = $dstr$groupName$_u24__u242;
                this.L$6 = $this$async;
                this.L$7 = groupName2;
                this.label = 1;
                initializedValue$default = SmartUpdateMediatorLiveData.getInitializedValue$default(lightAppPermGroupLiveData, false, false, this, 3, null);
                if (initializedValue$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $receiver = $this$async;
                continuation = continuation5;
                continuation2 = null;
                i = 0;
                continuation3 = continuation6;
                groupName = groupName2;
                $dstr$groupName$_u24__u24 = $dstr$groupName$_u24__u242;
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        i = 0;
        continuation2 = null;
        groupName = (String) this.L$7;
        $receiver = (CoroutineScope) this.L$6;
        $dstr$groupName$_u24__u24 = (Map.Entry) this.L$5;
        continuation3 = (Continuation) this.L$4;
        Object it2 = this.L$2;
        continuation = (Continuation) this.L$1;
        ResultKt.throwOnFailure($result);
        initializedValue$default = $result;
        LightAppPermGroup group = (LightAppPermGroup) initializedValue$default;
        if (group != null) {
            boolean fixed = group.getIsBackgroundFixed() || group.getIsForegroundFixed();
            Map<String, LightPermission> permissions = group.getPermissions();
            if (!permissions.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it3 = permissions.entrySet().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z = false;
                        break;
                    }
                    LightPermission perm = it3.next().getValue();
                    if (Boxing.boxBoolean(perm.isGrantedIncludingAppOp() && !AutoRevokePermissions.EXEMPT_PERMISSIONS.contains(perm.getName())).booleanValue()) {
                        z = true;
                        break;
                    }
                }
            }
            boolean granted = z;
            if (fixed || !granted || group.getIsGrantedByDefault() || group.getIsGrantedByRole() || !group.getIsUserSensitive()) {
                continuation4 = continuation;
            } else {
                List<String> list = CollectionsKt.toList(group.getPermissions().keySet());
                if (list.isEmpty()) {
                    continuation4 = continuation;
                } else {
                    DumpableLog dumpableLog = DumpableLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("revokeUnused ");
                    sb.append(this.$packageName$inlined);
                    sb.append(" - ");
                    sb.append(list);
                    sb.append(" - lastVisible on ");
                    continuation4 = continuation;
                    List list2 = (List) this.this$0.$userStats$inlined.get(this.this$0.$user$inlined);
                    if (list2 == null || (lBoxLong = Boxing.boxLong(AutoRevokePermissions.lastTimeVisible((List<UsageStats>) list2, this.$packageName$inlined))) == null) {
                        date = null;
                    } else {
                        long p1 = lBoxLong.longValue();
                        date = new Date(p1);
                    }
                    sb.append(date);
                    DumpableLog.i$default(dumpableLog, "AutoRevokePermissions", sb.toString(), null, 4, null);
                    int uid = group.getPackageInfo().getUid();
                    for (String permName : list) {
                        PermissionControllerStatsLog.write(PermissionControllerStatsLog.PERMISSION_GRANT_REQUEST_RESULT_REPORTED, this.this$0.$sessionId$inlined, uid, this.$packageName$inlined, permName, false, AutoRevokePermissions.SERVER_LOG_ID);
                        continuation3 = continuation3;
                        $dstr$groupName$_u24__u24 = $dstr$groupName$_u24__u24;
                    }
                    Object systemService = this.this$0.$context$inlined$1.getSystemService((Class<Object>) ActivityManager.class);
                    if (systemService == null) {
                        Intrinsics.throwNpe();
                    }
                    int packageImportance = ((ActivityManager) systemService).getPackageImportance(this.$packageName$inlined);
                    if (packageImportance > 325) {
                        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "revoking " + this.$packageName$inlined + " - " + list, null, 4, null);
                        DumpableLog dumpableLog2 = DumpableLog.INSTANCE;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("State pre revocation: ");
                        sb2.append(group.getAllPermissions());
                        DumpableLog.i$default(dumpableLog2, "AutoRevokePermissions", sb2.toString(), null, 4, null);
                        this.$anyPermsRevoked$inlined.compareAndSet(false, true);
                        LightAppPermGroup bgRevokedState = KotlinUtils.INSTANCE.revokeBackgroundRuntimePermissions(AndroidUtilsKt.getApplication(this.this$0.$context$inlined$1), group, false, false, list);
                        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "Bg state post revocation: " + bgRevokedState.getAllPermissions(), null, 4, null);
                        LightAppPermGroup fgRevokedState = KotlinUtils.INSTANCE.revokeForegroundRuntimePermissions(AndroidUtilsKt.getApplication(this.this$0.$context$inlined$1), group, false, false, list);
                        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "Fg state post revocation: " + fgRevokedState.getAllPermissions(), null, 4, null);
                        Iterator<String> it4 = list.iterator();
                        while (it4.hasNext()) {
                            String permission = it4.next();
                            PackageManager packageManager = this.this$0.$context$inlined$1.getPackageManager();
                            Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
                            AndroidUtilsKt.updatePermissionFlags(packageManager, permission, this.$packageName$inlined, this.this$0.$user$inlined, TuplesKt.to(Boxing.boxInt(131072), Boxing.boxBoolean(true)), TuplesKt.to(Boxing.boxInt(1), Boxing.boxBoolean(false)));
                            uid = uid;
                            bgRevokedState = bgRevokedState;
                            fgRevokedState = fgRevokedState;
                            it4 = it4;
                            i = i;
                            $receiver = $receiver;
                        }
                    } else {
                        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "Skipping auto-revoke - " + this.$packageName$inlined + " running with importance " + packageImportance, null, 4, null);
                    }
                }
            }
        } else {
            continuation4 = continuation;
        }
        return Unit.INSTANCE;
    }
}
