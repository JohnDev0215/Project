package com.android.permissioncontroller.permission.ui.model;

import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.FragmentKt;
import com.android.car.ui.core.SearchResultsProvider;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.LightPackageInfoLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.ui.Category;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0003 !\"B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\r\u001a\u001f\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000f¢\u0006\u0002\b\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel;", "Landroidx/lifecycle/ViewModel;", "packageName", "", "user", "Landroid/os/UserHandle;", "sessionId", "", "(Ljava/lang/String;Landroid/os/UserHandle;J)V", "autoRevokeLiveData", "Lcom/android/permissioncontroller/permission/data/AutoRevokeStateLiveData;", "getAutoRevokeLiveData", "()Lcom/android/permissioncontroller/permission/data/AutoRevokeStateLiveData;", "packagePermGroupsLiveData", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/ui/Category;", "", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$GroupUiInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "getPackagePermGroupsLiveData", "()Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "setAutoRevoke", "", "enabled", "", "showAllPermissions", "fragment", "Landroidx/fragment/app/Fragment;", "args", "Landroid/os/Bundle;", "showExtraPerms", "Companion", "GroupUiInfo", "PermSubtitle", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppPermissionGroupsViewModel extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOG_TAG;
    private final AutoRevokeStateLiveData autoRevokeLiveData;
    private final String packageName;
    private final SmartUpdateMediatorLiveData<Map<Category, List<GroupUiInfo>>> packagePermGroupsLiveData;
    private final long sessionId;
    private final UserHandle user;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppPermGroupUiInfo.PermGrantState.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED.ordinal()] = 1;
            $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_ALWAYS.ordinal()] = 2;
            $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_FOREGROUND_ONLY.ordinal()] = 3;
            $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_DENIED.ordinal()] = 4;
            $EnumSwitchMapping$0[AppPermGroupUiInfo.PermGrantState.PERMS_ASK.ordinal()] = 5;
        }
    }

    public AppPermissionGroupsViewModel(String packageName, UserHandle user, long sessionId) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.packageName = packageName;
        this.user = user;
        this.sessionId = sessionId;
        this.autoRevokeLiveData = (AutoRevokeStateLiveData) DataRepositoryKt.get(AutoRevokeStateLiveData.INSTANCE, this.packageName, this.user);
        this.packagePermGroupsLiveData = new AppPermissionGroupsViewModel$packagePermGroupsLiveData$1(this);
    }

    /* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$Companion;", "", "()V", "LOG_TAG", "", "getLOG_TAG", "()Ljava/lang/String;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final String getLOG_TAG() {
            return AppPermissionGroupsViewModel.LOG_TAG;
        }
    }

    static {
        String simpleName = AppPermissionGroupsViewModel.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "AppPermissionGroupsViewM…el::class.java.simpleName");
        LOG_TAG = simpleName;
    }

    /* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$PermSubtitle;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "NONE", "MEDIA_ONLY", "ALL_FILES", "FOREGROUND_ONLY", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public enum PermSubtitle {
        NONE(0),
        MEDIA_ONLY(1),
        ALL_FILES(2),
        FOREGROUND_ONLY(3);

        private final int value;

        PermSubtitle(int value) {
            this.value = value;
        }

        public final int getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\bHÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$GroupUiInfo;", "", "groupName", "", "isSystem", "", "(Ljava/lang/String;Z)V", SearchResultsProvider.SUBTITLE, "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$PermSubtitle;", "(Ljava/lang/String;ZLcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$PermSubtitle;)V", "getGroupName", "()Ljava/lang/String;", "()Z", "getSubtitle", "()Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$PermSubtitle;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class GroupUiInfo {
        private final String groupName;
        private final boolean isSystem;
        private final PermSubtitle subtitle;

        public static /* synthetic */ GroupUiInfo copy$default(GroupUiInfo groupUiInfo, String str, boolean z, PermSubtitle permSubtitle, int i, Object obj) {
            if ((i & 1) != 0) {
                str = groupUiInfo.groupName;
            }
            if ((i & 2) != 0) {
                z = groupUiInfo.isSystem;
            }
            if ((i & 4) != 0) {
                permSubtitle = groupUiInfo.subtitle;
            }
            return groupUiInfo.copy(str, z, permSubtitle);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getGroupName() {
            return this.groupName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsSystem() {
            return this.isSystem;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final PermSubtitle getSubtitle() {
            return this.subtitle;
        }

        public final GroupUiInfo copy(String groupName, boolean isSystem, PermSubtitle subtitle) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            Intrinsics.checkParameterIsNotNull(subtitle, "subtitle");
            return new GroupUiInfo(groupName, isSystem, subtitle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GroupUiInfo)) {
                return false;
            }
            GroupUiInfo groupUiInfo = (GroupUiInfo) other;
            return Intrinsics.areEqual(this.groupName, groupUiInfo.groupName) && this.isSystem == groupUiInfo.isSystem && Intrinsics.areEqual(this.subtitle, groupUiInfo.subtitle);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public int hashCode() {
            String str = this.groupName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.isSystem;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode + r2) * 31;
            PermSubtitle permSubtitle = this.subtitle;
            return i + (permSubtitle != null ? permSubtitle.hashCode() : 0);
        }

        public String toString() {
            return "GroupUiInfo(groupName=" + this.groupName + ", isSystem=" + this.isSystem + ", subtitle=" + this.subtitle + ")";
        }

        public GroupUiInfo(String groupName, boolean isSystem, PermSubtitle subtitle) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            Intrinsics.checkParameterIsNotNull(subtitle, "subtitle");
            this.groupName = groupName;
            this.isSystem = isSystem;
            this.subtitle = subtitle;
        }

        public final String getGroupName() {
            return this.groupName;
        }

        public /* synthetic */ GroupUiInfo(String str, boolean z, PermSubtitle permSubtitle, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, permSubtitle);
        }

        public final boolean isSystem() {
            return this.isSystem;
        }

        public final PermSubtitle getSubtitle() {
            return this.subtitle;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public GroupUiInfo(String groupName, boolean isSystem) {
            this(groupName, isSystem, PermSubtitle.NONE);
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        }
    }

    public final AutoRevokeStateLiveData getAutoRevokeLiveData() {
        return this.autoRevokeLiveData;
    }

    public final SmartUpdateMediatorLiveData<Map<Category, List<GroupUiInfo>>> getPackagePermGroupsLiveData() {
        return this.packagePermGroupsLiveData;
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$setAutoRevoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$setAutoRevoke$1", f = "AppPermissionGroupsViewModel.kt", i = {0, 0}, l = {181}, m = "invokeSuspend", n = {"$this$launch", "aom"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $enabled;
        Object L$0;
        Object L$1;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = AppPermissionGroupsViewModel.this.new AnonymousClass1(this.$enabled, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object initializedValue$default;
            AppOpsManager aom;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                Object systemService = PermissionControllerApplication.get().getSystemService(AppOpsManager.class);
                if (systemService == null) {
                    Intrinsics.throwNpe();
                }
                AppOpsManager aom2 = (AppOpsManager) systemService;
                LightPackageInfoLiveData lightPackageInfoLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, AppPermissionGroupsViewModel.this.packageName, AppPermissionGroupsViewModel.this.user);
                this.L$0 = $this$launch;
                this.L$1 = aom2;
                this.label = 1;
                initializedValue$default = SmartUpdateMediatorLiveData.getInitializedValue$default(lightPackageInfoLiveData, false, false, this, 3, null);
                if (initializedValue$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                aom = aom2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                aom = (AppOpsManager) this.L$1;
                ResultKt.throwOnFailure($result);
                initializedValue$default = $result;
            }
            LightPackageInfo lightPackageInfo = (LightPackageInfo) initializedValue$default;
            int uid = (lightPackageInfo != null ? Boxing.boxInt(lightPackageInfo.getUid()) : null).intValue();
            if (Boxing.boxInt(uid) != null) {
                Log.i(AppPermissionGroupsViewModel.INSTANCE.getLOG_TAG(), "sessionId " + AppPermissionGroupsViewModel.this.sessionId + " setting auto revoke enabled to " + this.$enabled + " for" + AppPermissionGroupsViewModel.this.packageName + ' ' + AppPermissionGroupsViewModel.this.user);
                int tag = this.$enabled ? 3 : 4;
                PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION, AppPermissionGroupsViewModel.this.sessionId, uid, AppPermissionGroupsViewModel.this.packageName, tag);
                int mode = this.$enabled ? 0 : 1;
                aom.setUidMode("android:auto_revoke_permissions_if_unused", uid, mode);
            }
            return Unit.INSTANCE;
        }
    }

    public final void setAutoRevoke(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new AnonymousClass1(enabled, null), 2, null);
    }

    public final void showExtraPerms(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.perm_groups_to_custom, args);
    }

    public final void showAllPermissions(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.perm_groups_to_all_perms, args);
    }
}
