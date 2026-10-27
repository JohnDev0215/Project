package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.permission.PermissionManager;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0003 !\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u0002J!\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013Jf\u0010\u0014\u001a \u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\t0\u00152\u0006\u0010\u0017\u001a\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\tH\u0002J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController;", "", "()V", "LATEST_VERSION", "", "LOG_TAG", "", "kotlin.jvm.PlatformType", "getWhitelistings", "", "Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$Whitelisting;", "permissions", "", "pkgs", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "onUpgradeLocked", "context", "Landroid/content/Context;", "currentVersion", "(Landroid/content/Context;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onUpgradeLockedDataLoaded", "Lkotlin/Triple;", "Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$Grant;", "currVersion", "restrictedPermissions", "bgApps", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "accessMediaApps", "upgradeIfNeeded", "", "onComplete", "Ljava/lang/Runnable;", "Grant", "UpgradeData", "Whitelisting", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class RuntimePermissionsUpgradeController {
    public static final RuntimePermissionsUpgradeController INSTANCE = new RuntimePermissionsUpgradeController();
    private static final String LOG_TAG = RuntimePermissionsUpgradeController.class.getSimpleName();
    private static final int LATEST_VERSION = 8;

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$onUpgradeLocked$1, reason: invalid class name */
    /* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0082@"}, d2 = {"onUpgradeLocked", "", "context", "Landroid/content/Context;", "currentVersion", "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController", f = "RuntimePermissionsUpgradeController.kt", i = {0, 0, 0, 0}, l = {290}, m = "onUpgradeLocked", n = {"this", "context", "currentVersion", "upgradeDataProvider"}, s = {"L$0", "L$1", "I$0", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RuntimePermissionsUpgradeController.this.onUpgradeLocked(null, 0, this);
        }
    }

    private RuntimePermissionsUpgradeController() {
    }

    public static final /* synthetic */ int access$getLATEST_VERSION$p(RuntimePermissionsUpgradeController $this) {
        return LATEST_VERSION;
    }

    public static final /* synthetic */ String access$getLOG_TAG$p(RuntimePermissionsUpgradeController $this) {
        return LOG_TAG;
    }

    public final void upgradeIfNeeded(Context context, Runnable onComplete) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(onComplete, "onComplete");
        PermissionManager permissionManager = (PermissionManager) context.getSystemService(PermissionManager.class);
        if (permissionManager == null) {
            Intrinsics.throwNpe();
        }
        int currentVersion = permissionManager.getRuntimePermissionsVersion();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new C00541(context, currentVersion, permissionManager, onComplete, null), 2, null);
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$upgradeIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$upgradeIfNeeded$1", f = "RuntimePermissionsUpgradeController.kt", i = {0}, l = {63}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C00541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ int $currentVersion;
        final /* synthetic */ Runnable $onComplete;
        final /* synthetic */ PermissionManager $permissionManager;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00541(Context context, int i, PermissionManager permissionManager, Runnable runnable, Continuation continuation) {
            super(2, continuation);
            this.$context = context;
            this.$currentVersion = i;
            this.$permissionManager = permissionManager;
            this.$onComplete = runnable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C00541 c00541 = new C00541(this.$context, this.$currentVersion, this.$permissionManager, this.$onComplete, completion);
            c00541.p$ = (CoroutineScope) obj;
            return c00541;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objOnUpgradeLocked;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                RuntimePermissionsUpgradeController runtimePermissionsUpgradeController = RuntimePermissionsUpgradeController.INSTANCE;
                Context context = this.$context;
                int i2 = this.$currentVersion;
                this.L$0 = $this$launch;
                this.label = 1;
                objOnUpgradeLocked = runtimePermissionsUpgradeController.onUpgradeLocked(context, i2, this);
                if (objOnUpgradeLocked == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
                objOnUpgradeLocked = $result;
            }
            int upgradedVersion = ((Number) objOnUpgradeLocked).intValue();
            if (upgradedVersion == RuntimePermissionsUpgradeController.access$getLATEST_VERSION$p(RuntimePermissionsUpgradeController.INSTANCE)) {
                if (this.$currentVersion != upgradedVersion) {
                    PermissionManager permissionManager = this.$permissionManager;
                    if (permissionManager == null) {
                        Intrinsics.throwNpe();
                    }
                    permissionManager.setRuntimePermissionsVersion(RuntimePermissionsUpgradeController.access$getLATEST_VERSION$p(RuntimePermissionsUpgradeController.INSTANCE));
                }
                this.$onComplete.run();
                return Unit.INSTANCE;
            }
            Log.wtf("PermissionControllerService", "warning: upgrading permission database to version " + RuntimePermissionsUpgradeController.access$getLATEST_VERSION$p(RuntimePermissionsUpgradeController.INSTANCE) + " left it at " + this.$currentVersion + " instead; this is probably a bug. Did you update LATEST_VERSION?", new Throwable());
            throw new RuntimeException("db upgrade error");
        }
    }

    private final List<Whitelisting> getWhitelistings(Set<String> permissions, List<LightPackageInfo> pkgs) {
        List whitelistings = new ArrayList();
        for (LightPackageInfo pkg : pkgs) {
            for (String permission : CollectionsKt.intersect(permissions, pkg.getRequestedPermissions())) {
                whitelistings.add(new Whitelisting(pkg.getPackageName(), permission));
            }
        }
        return whitelistings;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object onUpgradeLocked(android.content.Context r25, int r26, kotlin.coroutines.Continuation<? super java.lang.Integer> r27) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController.onUpgradeLocked(android.content.Context, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Triple<Integer, List<Whitelisting>, List<Grant>> onUpgradeLockedDataLoaded(int currVersion, List<LightPackageInfo> pkgs, Set<String> restrictedPermissions, List<LightAppPermGroup> bgApps, List<LightAppPermGroup> accessMediaApps) {
        LightPermission perm;
        List whitelistings = new ArrayList();
        List grants = new ArrayList();
        int currentVersion = currVersion;
        boolean sdkUpgradedFromP = false;
        boolean isNewUser = false;
        List<LightAppPermGroup> $this$map$iv = bgApps;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            LightAppPermGroup it = (LightAppPermGroup) item$iv$iv;
            destination$iv$iv.add(TuplesKt.to(it.getPackageName(), it));
            sdkUpgradedFromP = sdkUpgradedFromP;
        }
        boolean sdkUpgradedFromP2 = sdkUpgradedFromP;
        Map bgAppsWithWhitelisting = MapsKt.toMutableMap(MapsKt.toMap((List) destination$iv$iv));
        if (currentVersion <= -1) {
            Log.i(LOG_TAG, "Upgrading from Android P");
            currentVersion = 0;
            sdkUpgradedFromP2 = true;
        } else if (currentVersion == 0) {
            isNewUser = true;
        }
        if (currentVersion == 0) {
            Log.i(LOG_TAG, "Grandfathering SMS and CallLog permissions");
            List<String> platformPermissionNamesOfGroup = Utils.getPlatformPermissionNamesOfGroup("android.permission-group.SMS");
            Intrinsics.checkExpressionValueIsNotNull(platformPermissionNamesOfGroup, "getPlatformPermissionNam…oup(permission_group.SMS)");
            List<String> platformPermissionNamesOfGroup2 = Utils.getPlatformPermissionNamesOfGroup("android.permission-group.CALL_LOG");
            Intrinsics.checkExpressionValueIsNotNull(platformPermissionNamesOfGroup2, "getPlatformPermissionNam…ermission_group.CALL_LOG)");
            whitelistings.addAll(getWhitelistings(CollectionsKt.intersect(restrictedPermissions, CollectionsKt.plus((Collection) platformPermissionNamesOfGroup, (Iterable) platformPermissionNamesOfGroup2)), pkgs));
            currentVersion = 1;
        }
        if (currentVersion == 1) {
            currentVersion = 2;
        }
        if (currentVersion == 2) {
            currentVersion = 3;
        }
        if (currentVersion == 3) {
            Log.i(LOG_TAG, "Grandfathering location background permissions");
            List<Whitelisting> whitelistings2 = getWhitelistings(SetsKt.setOf("android.permission.ACCESS_BACKGROUND_LOCATION"), pkgs);
            Iterator<Whitelisting> it2 = whitelistings2.iterator();
            while (it2.hasNext()) {
                String pkgName = it2.next().getPkgName();
                LightAppPermGroup bgApp = (LightAppPermGroup) bgAppsWithWhitelisting.get(pkgName);
                if (bgApp != null && (perm = bgApp.getAllPermissions().get("android.permission.ACCESS_BACKGROUND_LOCATION")) != null) {
                    Map allPermissionsWithWhitelisting = MapsKt.toMutableMap(bgApp.getAllPermissions());
                    allPermissionsWithWhitelisting.put("android.permission.ACCESS_BACKGROUND_LOCATION", new LightPermission(perm.getPkgInfo(), perm.getPermInfo(), perm.isGrantedIncludingAppOp(), perm.getFlags() | 8192, perm.getForegroundPerms()));
                    bgAppsWithWhitelisting.put(pkgName, new LightAppPermGroup(bgApp.getPackageInfo(), bgApp.getPermGroupInfo(), allPermissionsWithWhitelisting, bgApp.getHasInstallToRuntimeSplit(), bgApp.getSpecialLocationGrant()));
                }
            }
            whitelistings.addAll(whitelistings2);
            currentVersion = 4;
        }
        if (currentVersion == 4) {
            currentVersion = 5;
        }
        if (currentVersion == 5) {
            Log.i(LOG_TAG, "Grandfathering Storage permissions");
            List<String> platformPermissionNamesOfGroup3 = Utils.getPlatformPermissionNamesOfGroup("android.permission-group.STORAGE");
            Intrinsics.checkExpressionValueIsNotNull(platformPermissionNamesOfGroup3, "getPlatformPermissionNam…permission_group.STORAGE)");
            whitelistings.addAll(getWhitelistings(CollectionsKt.intersect(restrictedPermissions, platformPermissionNamesOfGroup3), pkgs));
            currentVersion = 6;
        }
        if (currentVersion == 6) {
            if (sdkUpgradedFromP2) {
                Log.i(LOG_TAG, "Expanding location permissions");
                for (LightAppPermGroup appPermGroup : bgAppsWithWhitelisting.values()) {
                    if (appPermGroup.getForeground().getIsGranted() && appPermGroup.getHasBackgroundGroup() && !appPermGroup.getBackground().getIsUserSet() && !appPermGroup.getBackground().getIsSystemFixed() && !appPermGroup.getBackground().getIsPolicyFixed() && !appPermGroup.getBackground().getIsUserFixed()) {
                        grants.add(new Grant(true, appPermGroup, null, 4, null));
                    }
                }
            } else {
                Log.i(LOG_TAG, "Not expanding location permissions as this is not an upgrade from Android P");
            }
            currentVersion = 7;
        }
        if (currentVersion == 7) {
            if (!isNewUser) {
                Log.i(LOG_TAG, "Expanding read storage to access media location");
                for (LightAppPermGroup appPermGroup2 : accessMediaApps) {
                    LightPermission perm2 = appPermGroup2.getPermissions().get("android.permission.ACCESS_MEDIA_LOCATION");
                    if (perm2 != null && !perm2.getIsUserSet() && !perm2.getIsSystemFixed() && !perm2.getIsPolicyFixed() && !perm2.isGrantedIncludingAppOp()) {
                        grants.add(new Grant(false, appPermGroup2, CollectionsKt.listOf("android.permission.ACCESS_MEDIA_LOCATION")));
                    }
                }
            } else {
                Log.i(LOG_TAG, "Not expanding read storage to access media location as this is a new user");
            }
            currentVersion = 8;
        }
        return new Triple<>(Integer.valueOf(currentVersion), whitelistings, grants);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0002\u0010\fJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003JY\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006 "}, d2 = {"Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$UpgradeData;", "", "preinstalledPkgs", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "restrictedPermissions", "", "", "pkgs", "bgGroups", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "storageGroups", "(Ljava/util/List;Ljava/util/Set;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getBgGroups", "()Ljava/util/List;", "getPkgs", "getPreinstalledPkgs", "getRestrictedPermissions", "()Ljava/util/Set;", "getStorageGroups", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    static final /* data */ class UpgradeData {
        private final List<LightAppPermGroup> bgGroups;
        private final List<LightPackageInfo> pkgs;
        private final List<LightPackageInfo> preinstalledPkgs;
        private final Set<String> restrictedPermissions;
        private final List<LightAppPermGroup> storageGroups;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UpgradeData copy$default(UpgradeData upgradeData, List list, Set set, List list2, List list3, List list4, int i, Object obj) {
            if ((i & 1) != 0) {
                list = upgradeData.preinstalledPkgs;
            }
            if ((i & 2) != 0) {
                set = upgradeData.restrictedPermissions;
            }
            Set set2 = set;
            if ((i & 4) != 0) {
                list2 = upgradeData.pkgs;
            }
            List list5 = list2;
            if ((i & 8) != 0) {
                list3 = upgradeData.bgGroups;
            }
            List list6 = list3;
            if ((i & 16) != 0) {
                list4 = upgradeData.storageGroups;
            }
            return upgradeData.copy(list, set2, list5, list6, list4);
        }

        public final List<LightPackageInfo> component1() {
            return this.preinstalledPkgs;
        }

        public final Set<String> component2() {
            return this.restrictedPermissions;
        }

        public final List<LightPackageInfo> component3() {
            return this.pkgs;
        }

        public final List<LightAppPermGroup> component4() {
            return this.bgGroups;
        }

        public final List<LightAppPermGroup> component5() {
            return this.storageGroups;
        }

        public final UpgradeData copy(List<LightPackageInfo> preinstalledPkgs, Set<String> restrictedPermissions, List<LightPackageInfo> pkgs, List<LightAppPermGroup> bgGroups, List<LightAppPermGroup> storageGroups) {
            Intrinsics.checkParameterIsNotNull(preinstalledPkgs, "preinstalledPkgs");
            Intrinsics.checkParameterIsNotNull(restrictedPermissions, "restrictedPermissions");
            Intrinsics.checkParameterIsNotNull(pkgs, "pkgs");
            Intrinsics.checkParameterIsNotNull(bgGroups, "bgGroups");
            Intrinsics.checkParameterIsNotNull(storageGroups, "storageGroups");
            return new UpgradeData(preinstalledPkgs, restrictedPermissions, pkgs, bgGroups, storageGroups);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpgradeData)) {
                return false;
            }
            UpgradeData upgradeData = (UpgradeData) other;
            return Intrinsics.areEqual(this.preinstalledPkgs, upgradeData.preinstalledPkgs) && Intrinsics.areEqual(this.restrictedPermissions, upgradeData.restrictedPermissions) && Intrinsics.areEqual(this.pkgs, upgradeData.pkgs) && Intrinsics.areEqual(this.bgGroups, upgradeData.bgGroups) && Intrinsics.areEqual(this.storageGroups, upgradeData.storageGroups);
        }

        public int hashCode() {
            List<LightPackageInfo> list = this.preinstalledPkgs;
            int iHashCode = (list != null ? list.hashCode() : 0) * 31;
            Set<String> set = this.restrictedPermissions;
            int iHashCode2 = (iHashCode + (set != null ? set.hashCode() : 0)) * 31;
            List<LightPackageInfo> list2 = this.pkgs;
            int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
            List<LightAppPermGroup> list3 = this.bgGroups;
            int iHashCode4 = (iHashCode3 + (list3 != null ? list3.hashCode() : 0)) * 31;
            List<LightAppPermGroup> list4 = this.storageGroups;
            return iHashCode4 + (list4 != null ? list4.hashCode() : 0);
        }

        public String toString() {
            return "UpgradeData(preinstalledPkgs=" + this.preinstalledPkgs + ", restrictedPermissions=" + this.restrictedPermissions + ", pkgs=" + this.pkgs + ", bgGroups=" + this.bgGroups + ", storageGroups=" + this.storageGroups + ")";
        }

        public UpgradeData(List<LightPackageInfo> preinstalledPkgs, Set<String> restrictedPermissions, List<LightPackageInfo> pkgs, List<LightAppPermGroup> bgGroups, List<LightAppPermGroup> storageGroups) {
            Intrinsics.checkParameterIsNotNull(preinstalledPkgs, "preinstalledPkgs");
            Intrinsics.checkParameterIsNotNull(restrictedPermissions, "restrictedPermissions");
            Intrinsics.checkParameterIsNotNull(pkgs, "pkgs");
            Intrinsics.checkParameterIsNotNull(bgGroups, "bgGroups");
            Intrinsics.checkParameterIsNotNull(storageGroups, "storageGroups");
            this.preinstalledPkgs = preinstalledPkgs;
            this.restrictedPermissions = restrictedPermissions;
            this.pkgs = pkgs;
            this.bgGroups = bgGroups;
            this.storageGroups = storageGroups;
        }

        public final List<LightPackageInfo> getPreinstalledPkgs() {
            return this.preinstalledPkgs;
        }

        public final Set<String> getRestrictedPermissions() {
            return this.restrictedPermissions;
        }

        public final List<LightPackageInfo> getPkgs() {
            return this.pkgs;
        }

        public final List<LightAppPermGroup> getBgGroups() {
            return this.bgGroups;
        }

        public final List<LightAppPermGroup> getStorageGroups() {
            return this.storageGroups;
        }
    }

    /* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$Whitelisting;", "", "pkgName", "", "permission", "(Ljava/lang/String;Ljava/lang/String;)V", "getPermission", "()Ljava/lang/String;", "getPkgName", "applyToPlatform", "", "context", "Landroid/content/Context;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class Whitelisting {
        private final String permission;
        private final String pkgName;

        public static /* synthetic */ Whitelisting copy$default(Whitelisting whitelisting, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = whitelisting.pkgName;
            }
            if ((i & 2) != 0) {
                str2 = whitelisting.permission;
            }
            return whitelisting.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPkgName() {
            return this.pkgName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPermission() {
            return this.permission;
        }

        public final Whitelisting copy(String pkgName, String permission) {
            Intrinsics.checkParameterIsNotNull(pkgName, "pkgName");
            Intrinsics.checkParameterIsNotNull(permission, "permission");
            return new Whitelisting(pkgName, permission);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Whitelisting)) {
                return false;
            }
            Whitelisting whitelisting = (Whitelisting) other;
            return Intrinsics.areEqual(this.pkgName, whitelisting.pkgName) && Intrinsics.areEqual(this.permission, whitelisting.permission);
        }

        public int hashCode() {
            String str = this.pkgName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.permission;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Whitelisting(pkgName=" + this.pkgName + ", permission=" + this.permission + ")";
        }

        public Whitelisting(String pkgName, String permission) {
            Intrinsics.checkParameterIsNotNull(pkgName, "pkgName");
            Intrinsics.checkParameterIsNotNull(permission, "permission");
            this.pkgName = pkgName;
            this.permission = permission;
        }

        public final String getPkgName() {
            return this.pkgName;
        }

        public final String getPermission() {
            return this.permission;
        }

        public final void applyToPlatform(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            context.getPackageManager().addWhitelistedRestrictedPermission(this.pkgName, this.permission, 4);
        }
    }

    /* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\t\u0010\u000e\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÂ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÂ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u001e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0019H\u0002J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$Grant;", "", "isBackground", "", "group", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "permissions", "", "", "(ZLcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;Ljava/util/List;)V", "applyToPlatform", "", "context", "Landroid/content/Context;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "logRuntimePermissionUpgradeResult", "permissionGroup", "filterPermissions", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class Grant {
        private final LightAppPermGroup group;
        private final boolean isBackground;
        private final List<String> permissions;

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final boolean getIsBackground() {
            return this.isBackground;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final LightAppPermGroup getGroup() {
            return this.group;
        }

        private final List<String> component3() {
            return this.permissions;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Grant copy$default(Grant grant, boolean z, LightAppPermGroup lightAppPermGroup, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = grant.isBackground;
            }
            if ((i & 2) != 0) {
                lightAppPermGroup = grant.group;
            }
            if ((i & 4) != 0) {
                list = grant.permissions;
            }
            return grant.copy(z, lightAppPermGroup, list);
        }

        public final Grant copy(boolean isBackground, LightAppPermGroup group, List<String> permissions) {
            Intrinsics.checkParameterIsNotNull(group, "group");
            Intrinsics.checkParameterIsNotNull(permissions, "permissions");
            return new Grant(isBackground, group, permissions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Grant)) {
                return false;
            }
            Grant grant = (Grant) other;
            return this.isBackground == grant.isBackground && Intrinsics.areEqual(this.group, grant.group) && Intrinsics.areEqual(this.permissions, grant.permissions);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        public int hashCode() {
            boolean z = this.isBackground;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            LightAppPermGroup lightAppPermGroup = this.group;
            int iHashCode = (i + (lightAppPermGroup != null ? lightAppPermGroup.hashCode() : 0)) * 31;
            List<String> list = this.permissions;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "Grant(isBackground=" + this.isBackground + ", group=" + this.group + ", permissions=" + this.permissions + ")";
        }

        public Grant(boolean isBackground, LightAppPermGroup group, List<String> permissions) {
            Intrinsics.checkParameterIsNotNull(group, "group");
            Intrinsics.checkParameterIsNotNull(permissions, "permissions");
            this.isBackground = isBackground;
            this.group = group;
            this.permissions = permissions;
        }

        public /* synthetic */ Grant(boolean z, LightAppPermGroup lightAppPermGroup, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, lightAppPermGroup, (i & 4) != 0 ? CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet()) : list);
        }

        public final void applyToPlatform(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (this.isBackground) {
                LightAppPermGroup newGroup = KotlinUtils.INSTANCE.grantBackgroundRuntimePermissions(AndroidUtilsKt.getApplication(context), this.group, this.permissions);
                logRuntimePermissionUpgradeResult(newGroup, CollectionsKt.intersect(this.permissions, newGroup.getBackgroundPermNames()));
            } else {
                LightAppPermGroup newGroup2 = KotlinUtils.INSTANCE.grantForegroundRuntimePermissions(AndroidUtilsKt.getApplication(context), this.group, this.permissions);
                logRuntimePermissionUpgradeResult(newGroup2, CollectionsKt.intersect(this.permissions, newGroup2.getForegroundPermNames()));
            }
        }

        private final void logRuntimePermissionUpgradeResult(LightAppPermGroup permissionGroup, Iterable<String> filterPermissions) {
            int uid = permissionGroup.getPackageInfo().getUid();
            String packageName = permissionGroup.getPackageName();
            for (String permName : filterPermissions) {
                LightPermission permission = permissionGroup.getPermissions().get(permName);
                if (permission != null) {
                    PermissionControllerStatsLog.write(PermissionControllerStatsLog.RUNTIME_PERMISSIONS_UPGRADE_RESULT, permission.getName(), uid, packageName);
                    Log.v(RuntimePermissionsUpgradeController.access$getLOG_TAG$p(RuntimePermissionsUpgradeController.INSTANCE), "Runtime permission upgrade logged for permissionName=" + permission.getName() + " uid=" + uid + " packageName=" + packageName);
                }
            }
        }
    }
}
