package com.android.permissioncontroller.permission.ui.model;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.FragmentKt;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData;
import com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.RestrictedLockUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPermissionViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 W2\u00020\u0001:\u0005TUVWXB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJH\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u0012H\u0002J\u0018\u00100\u001a\u00020\u00192\u0006\u0010(\u001a\u00020#2\u0006\u00101\u001a\u00020 H\u0002J\u0010\u00102\u001a\u00020\u00192\u0006\u00103\u001a\u00020\fH\u0002J\u001c\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010(\u001a\u00020#H\u0002J\u0010\u00105\u001a\u00020 2\u0006\u00106\u001a\u00020\u0005H\u0002J\u0010\u00107\u001a\u00020 2\u0006\u00106\u001a\u00020\u0005H\u0002J \u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0019H\u0002J\u0006\u0010>\u001a\u000209J \u0010?\u001a\u0002092\u0006\u0010@\u001a\u00020#2\u0006\u0010A\u001a\u00020#2\u0006\u0010=\u001a\u00020\u0019H\u0002J\u001e\u0010B\u001a\u0002092\u0006\u0010C\u001a\u00020D2\u0006\u0010=\u001a\u00020\u00192\u0006\u0010E\u001a\u00020 J.\u0010F\u001a\u0002092\u0006\u0010G\u001a\u00020 2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010C\u001a\u00020D2\u0006\u0010L\u001a\u00020\u0019J\u000e\u0010M\u001a\u0002092\u0006\u0010N\u001a\u00020 J\u0016\u0010O\u001a\u0002092\u0006\u0010H\u001a\u00020I2\u0006\u0010P\u001a\u00020QJ\u001e\u0010R\u001a\u0002092\u0006\u0010H\u001a\u00020I2\u0006\u0010S\u001a\u00020\u00052\u0006\u0010P\u001a\u00020QR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u00130\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R%\u0010\u0016\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0017¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel;", "Landroidx/lifecycle/ViewModel;", "app", "Landroid/app/Application;", "packageName", "", "permGroupName", "user", "Landroid/os/UserHandle;", "sessionId", "", "foregroundCapableType", "Lcom/android/permissioncontroller/permission/utils/Utils$ForegroundCapableType;", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;JLcom/android/permissioncontroller/permission/utils/Utils$ForegroundCapableType;)V", "buttonStateLiveData", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ButtonType;", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ButtonState;", "Lkotlin/jvm/JvmSuppressWildcards;", "getButtonStateLiveData", "()Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "detailResIdLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "", "getDetailResIdLiveData", "()Landroidx/lifecycle/MutableLiveData;", "fullStorageStateLiveData", "Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData$FullStoragePackageState;", "getFullStorageStateLiveData", "hasConfirmedRevoke", "", "isStorage", "lightAppPermGroup", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "showAdminSupportLiveData", "Lcom/android/settingslib/RestrictedLockUtils$EnforcedAdmin;", "getShowAdminSupportLiveData", "applyFixToForegroundBackground", "group", "isForegroundFixed", "isBackgroundFixed", "allowedAlwaysState", "allowedForegroundState", "askState", "deniedState", "deniedForegroundState", "getDetailResIdForFixedByPolicyPermissionGroup", "hasAdmin", "getForegroundCapableDetailResId", "type", "getIndividualPermissionDetailResId", "isForegroundGroupSpecialCase", "permissionGroupName", "isMicrophone", "logAppPermissionFragmentActionReported", "", "changeId", "permission", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "buttonPressed", "logAppPermissionFragmentViewed", "logPermissionChanges", "oldGroup", "newGroup", "onDenyAnyWay", "changeRequest", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;", "oneTime", "requestChange", "setOneTime", "fragment", "Landroidx/fragment/app/Fragment;", "defaultDeny", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ConfirmDialogShowingFragment;", "buttonClicked", "setAllFilesAccess", "granted", "showAllPermissions", "args", "Landroid/os/Bundle;", "showBottomLinkPage", "action", "ButtonState", "ButtonType", "ChangeRequest", "Companion", "ConfirmDialogShowingFragment", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppPermissionViewModel extends ViewModel {
    private static final String LOG_TAG = AppPermissionViewModel.class.getSimpleName();
    private final Application app;
    private final SmartUpdateMediatorLiveData<Map<ButtonType, ButtonState>> buttonStateLiveData;
    private final MutableLiveData<Pair<Integer, Integer>> detailResIdLiveData;
    private final Utils.ForegroundCapableType foregroundCapableType;
    private final SmartUpdateMediatorLiveData<FullStoragePermissionAppsLiveData.FullStoragePackageState> fullStorageStateLiveData;
    private boolean hasConfirmedRevoke;
    private final boolean isStorage;
    private LightAppPermGroup lightAppPermGroup;
    private final String packageName;
    private final String permGroupName;
    private final long sessionId;
    private final MutableLiveData<RestrictedLockUtils.EnforcedAdmin> showAdminSupportLiveData;
    private final UserHandle user;

    /* JADX INFO: compiled from: AppPermissionViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ConfirmDialogShowingFragment;", "", "showConfirmDialog", "", "changeRequest", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;", "messageId", "", "buttonPressed", "oneTime", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public interface ConfirmDialogShowingFragment {
        void showConfirmDialog(ChangeRequest changeRequest, int messageId, int buttonPressed, boolean oneTime);
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Utils.ForegroundCapableType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Utils.ForegroundCapableType.SOUND_TRIGGER.ordinal()] = 1;
            $EnumSwitchMapping$0[Utils.ForegroundCapableType.ASSISTANT.ordinal()] = 2;
            $EnumSwitchMapping$0[Utils.ForegroundCapableType.VOICE_INTERACTION.ordinal()] = 3;
            $EnumSwitchMapping$0[Utils.ForegroundCapableType.CARRIER_SERVICE.ordinal()] = 4;
        }
    }

    public AppPermissionViewModel(Application app, String packageName, String permGroupName, UserHandle user, long sessionId, Utils.ForegroundCapableType foregroundCapableType) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(permGroupName, "permGroupName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(foregroundCapableType, "foregroundCapableType");
        this.app = app;
        this.packageName = packageName;
        this.permGroupName = permGroupName;
        this.user = user;
        this.sessionId = sessionId;
        this.foregroundCapableType = foregroundCapableType;
        this.isStorage = Intrinsics.areEqual(permGroupName, "android.permission-group.STORAGE");
        this.detailResIdLiveData = new MutableLiveData<>();
        this.showAdminSupportLiveData = new MutableLiveData<>();
        this.fullStorageStateLiveData = new SmartUpdateMediatorLiveData<FullStoragePermissionAppsLiveData.FullStoragePackageState>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$fullStorageStateLiveData$1
            {
                if (this.this$0.isStorage) {
                    addSource(FullStoragePermissionAppsLiveData.INSTANCE, new Observer<List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState>>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$fullStorageStateLiveData$1.1
                        @Override // androidx.lifecycle.Observer
                        public /* bridge */ /* synthetic */ void onChanged(List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState> list) {
                            onChanged2((List<FullStoragePermissionAppsLiveData.FullStoragePackageState>) list);
                        }

                        /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                        public final void onChanged2(List<FullStoragePermissionAppsLiveData.FullStoragePackageState> list) {
                            updateIfActive();
                        }
                    });
                } else {
                    setValue(null);
                }
            }

            @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
            protected void onUpdate() {
                List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState> value = FullStoragePermissionAppsLiveData.INSTANCE.getValue();
                if (value == null) {
                    return;
                }
                for (FullStoragePermissionAppsLiveData.FullStoragePackageState state : value) {
                    if (Intrinsics.areEqual(state.getPackageName(), this.this$0.packageName) && Intrinsics.areEqual(state.getUser(), this.this$0.user)) {
                        setValue(state);
                        return;
                    }
                }
                setValue(null);
            }
        };
        this.buttonStateLiveData = new SmartUpdateMediatorLiveData<Map<ButtonType, ButtonState>>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$buttonStateLiveData$1
            private final LightAppPermGroupLiveData appPermGroupLiveData;

            {
                LightAppPermGroupLiveData lightAppPermGroupLiveData = (LightAppPermGroupLiveData) DataRepositoryKt.get(LightAppPermGroupLiveData.Companion, this.this$0.packageName, this.this$0.permGroupName, this.this$0.user);
                this.appPermGroupLiveData = lightAppPermGroupLiveData;
                addSource(lightAppPermGroupLiveData, new Observer<LightAppPermGroup>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$buttonStateLiveData$1.1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(LightAppPermGroup appPermGroup) {
                        AppPermissionViewModel$buttonStateLiveData$1.this.this$0.lightAppPermGroup = appPermGroup;
                        if (AppPermissionViewModel$buttonStateLiveData$1.this.appPermGroupLiveData.getIsInitialized() && appPermGroup == null) {
                            setValue(null);
                            return;
                        }
                        if (appPermGroup != null) {
                            if (AppPermissionViewModel$buttonStateLiveData$1.this.this$0.isStorage && !AppPermissionViewModel$buttonStateLiveData$1.this.this$0.getFullStorageStateLiveData().getIsInitialized()) {
                                return;
                            }
                            if (getValue() == null) {
                                AppPermissionViewModel$buttonStateLiveData$1.this.this$0.logAppPermissionFragmentViewed();
                            }
                            updateIfActive();
                        }
                    }
                });
                if (this.this$0.isStorage) {
                    addSource(this.this$0.getFullStorageStateLiveData(), new Observer<FullStoragePermissionAppsLiveData.FullStoragePackageState>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$buttonStateLiveData$1.2
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(FullStoragePermissionAppsLiveData.FullStoragePackageState it) {
                            updateIfActive();
                        }
                    });
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:145:0x0380  */
            @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            protected void onUpdate() {
                /*
                    Method dump skipped, instruction units count: 1217
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel$buttonStateLiveData$1.onUpdate():void");
            }
        };
    }

    /* JADX INFO: compiled from: AppPermissionViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0000H\u0086\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "andValue", "other", "GRANT_FOREGROUND", "REVOKE_FOREGROUND", "GRANT_BACKGROUND", "REVOKE_BACKGROUND", "GRANT_BOTH", "REVOKE_BOTH", "GRANT_FOREGROUND_ONLY", "GRANT_All_FILE_ACCESS", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public enum ChangeRequest {
        GRANT_FOREGROUND(1),
        REVOKE_FOREGROUND(2),
        GRANT_BACKGROUND(4),
        REVOKE_BACKGROUND(8),
        GRANT_BOTH(GRANT_FOREGROUND.value | GRANT_BACKGROUND.value),
        REVOKE_BOTH(REVOKE_FOREGROUND.value | REVOKE_BACKGROUND.value),
        GRANT_FOREGROUND_ONLY(GRANT_FOREGROUND.value | REVOKE_BACKGROUND.value),
        GRANT_All_FILE_ACCESS(16);

        private final int value;

        ChangeRequest(int value) {
            this.value = value;
        }

        public final int getValue() {
            return this.value;
        }

        public final int andValue(ChangeRequest other) {
            Intrinsics.checkParameterIsNotNull(other, "other");
            return this.value & other.value;
        }
    }

    /* JADX INFO: compiled from: AppPermissionViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ButtonType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "ALLOW", "ALLOW_ALWAYS", "ALLOW_FOREGROUND", "ASK_ONCE", "ASK", "DENY", "DENY_FOREGROUND", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public enum ButtonType {
        ALLOW(0),
        ALLOW_ALWAYS(1),
        ALLOW_FOREGROUND(2),
        ASK_ONCE(3),
        ASK(4),
        DENY(5),
        DENY_FOREGROUND(6);

        private final int type;

        ButtonType(int type) {
            this.type = type;
        }

        public final int getType() {
            return this.type;
        }
    }

    public final MutableLiveData<Pair<Integer, Integer>> getDetailResIdLiveData() {
        return this.detailResIdLiveData;
    }

    public final MutableLiveData<RestrictedLockUtils.EnforcedAdmin> getShowAdminSupportLiveData() {
        return this.showAdminSupportLiveData;
    }

    public final SmartUpdateMediatorLiveData<FullStoragePermissionAppsLiveData.FullStoragePackageState> getFullStorageStateLiveData() {
        return this.fullStorageStateLiveData;
    }

    /* JADX INFO: compiled from: AppPermissionViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u000e\"\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000e\"\u0004\b\u0012\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ButtonState;", "", "()V", "isChecked", "", "isEnabled", "isShown", "customRequest", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;", "(ZZZLcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;)V", "getCustomRequest", "()Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;", "setCustomRequest", "(Lcom/android/permissioncontroller/permission/ui/model/AppPermissionViewModel$ChangeRequest;)V", "()Z", "setChecked", "(Z)V", "setEnabled", "setShown", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ButtonState {
        private ChangeRequest customRequest;
        private boolean isChecked;
        private boolean isEnabled;
        private boolean isShown;

        public static /* synthetic */ ButtonState copy$default(ButtonState buttonState, boolean z, boolean z2, boolean z3, ChangeRequest changeRequest, int i, Object obj) {
            if ((i & 1) != 0) {
                z = buttonState.isChecked;
            }
            if ((i & 2) != 0) {
                z2 = buttonState.isEnabled;
            }
            if ((i & 4) != 0) {
                z3 = buttonState.isShown;
            }
            if ((i & 8) != 0) {
                changeRequest = buttonState.customRequest;
            }
            return buttonState.copy(z, z2, z3, changeRequest);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsChecked() {
            return this.isChecked;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsEnabled() {
            return this.isEnabled;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsShown() {
            return this.isShown;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final ChangeRequest getCustomRequest() {
            return this.customRequest;
        }

        public final ButtonState copy(boolean isChecked, boolean isEnabled, boolean isShown, ChangeRequest customRequest) {
            return new ButtonState(isChecked, isEnabled, isShown, customRequest);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ButtonState)) {
                return false;
            }
            ButtonState buttonState = (ButtonState) other;
            return this.isChecked == buttonState.isChecked && this.isEnabled == buttonState.isEnabled && this.isShown == buttonState.isShown && Intrinsics.areEqual(this.customRequest, buttonState.customRequest);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public int hashCode() {
            boolean z = this.isChecked;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.isEnabled;
            ?? r2 = z2;
            if (z2) {
                r2 = 1;
            }
            int i2 = (i + r2) * 31;
            boolean z3 = this.isShown;
            int i3 = (i2 + (z3 ? 1 : z3)) * 31;
            ChangeRequest changeRequest = this.customRequest;
            return i3 + (changeRequest != null ? changeRequest.hashCode() : 0);
        }

        public String toString() {
            return "ButtonState(isChecked=" + this.isChecked + ", isEnabled=" + this.isEnabled + ", isShown=" + this.isShown + ", customRequest=" + this.customRequest + ")";
        }

        public ButtonState(boolean isChecked, boolean isEnabled, boolean isShown, ChangeRequest customRequest) {
            this.isChecked = isChecked;
            this.isEnabled = isEnabled;
            this.isShown = isShown;
            this.customRequest = customRequest;
        }

        public final boolean isChecked() {
            return this.isChecked;
        }

        public final void setChecked(boolean z) {
            this.isChecked = z;
        }

        public final boolean isEnabled() {
            return this.isEnabled;
        }

        public final void setEnabled(boolean z) {
            this.isEnabled = z;
        }

        public final boolean isShown() {
            return this.isShown;
        }

        public final void setShown(boolean z) {
            this.isShown = z;
        }

        public final ChangeRequest getCustomRequest() {
            return this.customRequest;
        }

        public final void setCustomRequest(ChangeRequest changeRequest) {
            this.customRequest = changeRequest;
        }

        public ButtonState() {
            this(false, true, false, null);
        }
    }

    public final SmartUpdateMediatorLiveData<Map<ButtonType, ButtonState>> getButtonStateLiveData() {
        return this.buttonStateLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isForegroundGroupSpecialCase(String permissionGroupName) {
        return permissionGroupName.equals("android.permission-group.CAMERA") || permissionGroupName.equals("android.permission-group.MICROPHONE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMicrophone(String permissionGroupName) {
        return permissionGroupName.equals("android.permission-group.MICROPHONE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean applyFixToForegroundBackground(LightAppPermGroup group, boolean isForegroundFixed, boolean isBackgroundFixed, ButtonState allowedAlwaysState, ButtonState allowedForegroundState, ButtonState askState, ButtonState deniedState, ButtonState deniedForegroundState) {
        if (isBackgroundFixed && isForegroundFixed) {
            allowedAlwaysState.setEnabled(false);
            allowedForegroundState.setEnabled(false);
            askState.setEnabled(false);
            deniedState.setEnabled(false);
            if (askState.isChecked()) {
                askState.setChecked(false);
                deniedState.setChecked(true);
            }
        } else if (isBackgroundFixed && !isForegroundFixed) {
            if (group.getBackground().getIsGranted()) {
                allowedForegroundState.setEnabled(false);
                askState.setEnabled(false);
                deniedState.setShown(false);
                deniedForegroundState.setShown(true);
                deniedForegroundState.setChecked(deniedState.isChecked());
                if (askState.isChecked()) {
                    askState.setChecked(false);
                    deniedState.setChecked(true);
                }
            } else {
                allowedAlwaysState.setEnabled(false);
            }
        } else {
            if (isBackgroundFixed || !isForegroundFixed) {
                return false;
            }
            if (group.getForeground().getIsGranted()) {
                allowedForegroundState.setEnabled(allowedAlwaysState.isShown());
                askState.setEnabled(false);
                deniedState.setEnabled(false);
            } else {
                allowedAlwaysState.setEnabled(false);
                allowedForegroundState.setEnabled(false);
                askState.setEnabled(false);
                deniedState.setEnabled(false);
                if (askState.isChecked()) {
                    askState.setChecked(false);
                    deniedState.setChecked(true);
                }
            }
        }
        return true;
    }

    public final void showBottomLinkPage(Fragment fragment, String action, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(args, "args");
        int actionId = R.id.app_to_perm_groups;
        if (Intrinsics.areEqual(action, "android.intent.action.MANAGE_PERMISSION_APPS")) {
            actionId = R.id.app_to_perm_apps;
        }
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), actionId, args);
    }

    public final void requestChange(boolean setOneTime, Fragment fragment, ConfirmDialogShowingFragment defaultDeny, ChangeRequest changeRequest, int buttonClicked) {
        boolean showDefaultDenyDialog;
        boolean showGrantedByDefaultWarning;
        LightAppPermGroup oldGroup;
        boolean z;
        LightAppPermGroup newGroup;
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(defaultDeny, "defaultDeny");
        Intrinsics.checkParameterIsNotNull(changeRequest, "changeRequest");
        Context context = fragment.getContext();
        if (context == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "fragment.context ?: return");
        LightAppPermGroup group = this.lightAppPermGroup;
        if (group == null) {
            return;
        }
        boolean wasForegroundGranted = group.getForeground().getIsGranted();
        boolean wasBackgroundGranted = group.getBackground().getIsGranted();
        if (LocationUtils.isLocationGroupAndProvider(context, this.permGroupName, this.packageName)) {
            String packageLabel = KotlinUtils.INSTANCE.getPackageLabel(this.app, this.packageName, this.user);
            LocationUtils.showLocationDialog(context, packageLabel);
        }
        boolean shouldGrantForeground = (group.getIsForegroundFixed() || changeRequest.andValue(ChangeRequest.GRANT_FOREGROUND) == 0) ? false : true;
        boolean shouldGrantBackground = (group.getIsBackgroundFixed() || changeRequest.andValue(ChangeRequest.GRANT_BACKGROUND) == 0) ? false : true;
        boolean shouldRevokeForeground = (group.getIsForegroundFixed() || changeRequest.andValue(ChangeRequest.REVOKE_FOREGROUND) == 0) ? false : true;
        boolean shouldRevokeBackground = (group.getIsBackgroundFixed() || changeRequest.andValue(ChangeRequest.REVOKE_BACKGROUND) == 0) ? false : true;
        boolean showDefaultDenyDialog2 = false;
        boolean showGrantedByDefaultWarning2 = false;
        if (shouldRevokeForeground && wasForegroundGranted) {
            showDefaultDenyDialog2 = group.getForeground().getIsGrantedByDefault() || !group.getSupportsRuntimePerms() || group.getHasInstallToRuntimeSplit();
            showGrantedByDefaultWarning2 = group.getForeground().getIsGrantedByDefault();
        }
        if (shouldRevokeBackground && wasBackgroundGranted) {
            showDefaultDenyDialog = showDefaultDenyDialog2 || group.getBackground().getIsGrantedByDefault() || !group.getSupportsRuntimePerms() || group.getHasInstallToRuntimeSplit();
            showGrantedByDefaultWarning = showGrantedByDefaultWarning2 || group.getBackground().getIsGrantedByDefault();
        } else {
            showDefaultDenyDialog = showDefaultDenyDialog2;
            showGrantedByDefaultWarning = showGrantedByDefaultWarning2;
        }
        if (showDefaultDenyDialog && !this.hasConfirmedRevoke && showGrantedByDefaultWarning) {
            defaultDeny.showConfirmDialog(changeRequest, R.string.system_warning, buttonClicked, setOneTime);
            return;
        }
        if (showDefaultDenyDialog && !this.hasConfirmedRevoke) {
            defaultDeny.showConfirmDialog(changeRequest, R.string.old_sdk_deny_warning, buttonClicked, setOneTime);
            return;
        }
        LightAppPermGroup newGroup2 = group;
        if (shouldRevokeBackground && group.getHasBackgroundGroup() && (wasBackgroundGranted || group.getBackground().getIsUserFixed() || group.getIsOneTime() != setOneTime)) {
            LightAppPermGroup newGroup3 = KotlinUtils.revokeBackgroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, newGroup2, false, false, null, 28, null);
            if (wasBackgroundGranted) {
                SafetyNetLogger.logPermissionToggled(newGroup3, true);
            }
            newGroup2 = newGroup3;
        }
        if (!shouldRevokeForeground) {
            oldGroup = group;
            z = true;
        } else if (wasForegroundGranted || group.getIsOneTime() != setOneTime) {
            oldGroup = group;
            z = true;
            newGroup2 = KotlinUtils.revokeForegroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, newGroup2, false, setOneTime, null, 16, null);
            if (wasForegroundGranted) {
                SafetyNetLogger.logPermissionToggled(newGroup2);
            }
        } else {
            oldGroup = group;
            z = true;
        }
        if (shouldGrantForeground) {
            newGroup2 = KotlinUtils.grantForegroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, newGroup2, null, 4, null);
            if (!wasForegroundGranted) {
                SafetyNetLogger.logPermissionToggled(newGroup2);
            }
        }
        if (shouldGrantBackground && group.getHasBackgroundGroup()) {
            newGroup = KotlinUtils.grantBackgroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, newGroup2, null, 4, null);
            if (!wasBackgroundGranted) {
                SafetyNetLogger.logPermissionToggled(newGroup, z);
            }
        } else {
            newGroup = newGroup2;
        }
        logPermissionChanges(oldGroup, newGroup, buttonClicked);
        if (this.fullStorageStateLiveData.getValue() != null) {
            FullStoragePermissionAppsLiveData.INSTANCE.recalculate();
        }
    }

    public final void onDenyAnyWay(ChangeRequest changeRequest, int buttonPressed, boolean oneTime) {
        LightAppPermGroup newGroup;
        Intrinsics.checkParameterIsNotNull(changeRequest, "changeRequest");
        LightAppPermGroup group = this.lightAppPermGroup;
        if (group != null) {
            boolean wasForegroundGranted = group.getForeground().getIsGranted();
            boolean wasBackgroundGranted = group.getBackground().getIsGranted();
            boolean hasDefaultPermissions = false;
            if (changeRequest.andValue(ChangeRequest.REVOKE_BACKGROUND) != 0 && group.getHasBackgroundGroup()) {
                newGroup = KotlinUtils.revokeBackgroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, group, false, oneTime, null, 16, null);
                if (wasBackgroundGranted) {
                    SafetyNetLogger.logPermissionToggled(newGroup);
                }
                hasDefaultPermissions = group.getBackground().getIsGrantedByDefault();
            } else {
                newGroup = group;
            }
            if (changeRequest.andValue(ChangeRequest.REVOKE_FOREGROUND) != 0) {
                newGroup = KotlinUtils.revokeForegroundRuntimePermissions$default(KotlinUtils.INSTANCE, this.app, newGroup, false, oneTime, null, 16, null);
                if (wasForegroundGranted) {
                    SafetyNetLogger.logPermissionToggled(newGroup);
                }
                hasDefaultPermissions = group.getForeground().getIsGrantedByDefault();
            }
            logPermissionChanges(group, newGroup, buttonPressed);
            if (hasDefaultPermissions || !group.getSupportsRuntimePerms()) {
                this.hasConfirmedRevoke = true;
            }
            if (this.fullStorageStateLiveData.getValue() != null) {
                FullStoragePermissionAppsLiveData.INSTANCE.recalculate();
            }
        }
    }

    public final void setAllFilesAccess(boolean granted) {
        LightPackageInfo packageInfo;
        int mode;
        Object systemService = this.app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        AppOpsManager aom = (AppOpsManager) systemService;
        LightAppPermGroup lightAppPermGroup = this.lightAppPermGroup;
        if (lightAppPermGroup == null || (packageInfo = lightAppPermGroup.getPackageInfo()) == null) {
            return;
        }
        int uid = packageInfo.getUid();
        if (granted) {
            mode = 0;
        } else {
            mode = 2;
        }
        FullStoragePermissionAppsLiveData.FullStoragePackageState value = this.fullStorageStateLiveData.getValue();
        Boolean fullStorageGrant = value != null ? Boolean.valueOf(value.isGranted()) : null;
        if (fullStorageGrant != null && (!Intrinsics.areEqual(fullStorageGrant, Boolean.valueOf(granted)))) {
            aom.setUidMode("android:manage_external_storage", uid, mode);
            FullStoragePermissionAppsLiveData.INSTANCE.recalculate();
        }
    }

    public final void showAllPermissions(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.app_to_all_perms, args);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, Integer> getIndividualPermissionDetailResId(LightAppPermGroup group) {
        Map<String, LightPermission> permissions = group.getPermissions();
        Map destination$iv$iv = new LinkedHashMap();
        for (Map.Entry<String, LightPermission> entry : permissions.entrySet()) {
            if (!entry.getValue().isGrantedIncludingAppOp()) {
                destination$iv$iv.put(entry.getKey(), entry.getValue());
            }
        }
        int numRevoked = destination$iv$iv.size();
        if (numRevoked == 0) {
            return TuplesKt.to(Integer.valueOf(R.string.permission_revoked_none), Integer.valueOf(numRevoked));
        }
        return numRevoked == group.getPermissions().size() ? TuplesKt.to(Integer.valueOf(R.string.permission_revoked_all), Integer.valueOf(numRevoked)) : TuplesKt.to(Integer.valueOf(R.string.permission_revoked_count), Integer.valueOf(numRevoked));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDetailResIdForFixedByPolicyPermissionGroup(LightAppPermGroup group, boolean hasAdmin) {
        boolean isPolicyFullyFixedWithGrantedOrNoBkg = true;
        boolean isForegroundPolicyDenied = group.getForeground().getIsPolicyFixed() && !group.getForeground().getIsGranted();
        if (!group.getIsPolicyFullyFixed() || (!group.getBackground().getIsGranted() && group.getHasBackgroundGroup())) {
            isPolicyFullyFixedWithGrantedOrNoBkg = false;
        }
        if (group.getForeground().getIsSystemFixed() || group.getBackground().getIsSystemFixed()) {
            return R.string.permission_summary_enabled_system_fixed;
        }
        if (hasAdmin) {
            if (isForegroundPolicyDenied) {
                return R.string.disabled_by_admin;
            }
            if (isPolicyFullyFixedWithGrantedOrNoBkg) {
                return R.string.enabled_by_admin;
            }
            if (group.getIsPolicyFullyFixed()) {
                return R.string.permission_summary_enabled_by_admin_foreground_only;
            }
            if (group.getBackground().getIsPolicyFixed() && group.getBackground().getIsGranted()) {
                return R.string.permission_summary_enabled_by_admin_background_only;
            }
            if (group.getBackground().getIsPolicyFixed()) {
                return R.string.permission_summary_disabled_by_admin_background_only;
            }
            if (group.getForeground().getIsPolicyFixed()) {
                return R.string.permission_summary_enabled_by_admin_foreground_only;
            }
        } else {
            if (isForegroundPolicyDenied || isPolicyFullyFixedWithGrantedOrNoBkg) {
                return R.string.permission_summary_enforced_by_policy;
            }
            if (group.getIsPolicyFullyFixed()) {
                return R.string.permission_summary_enabled_by_policy_foreground_only;
            }
            if (group.getBackground().getIsPolicyFixed() && group.getBackground().getIsGranted()) {
                return R.string.permission_summary_enabled_by_policy_background_only;
            }
            if (group.getBackground().getIsPolicyFixed()) {
                return R.string.permission_summary_disabled_by_policy_background_only;
            }
            if (group.getForeground().getIsPolicyFixed()) {
                return R.string.permission_summary_enabled_by_policy_foreground_only;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getForegroundCapableDetailResId(Utils.ForegroundCapableType type) {
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            return R.string.fg_capabilities_sound_trigger;
        }
        if (i == 2) {
            return R.string.fg_capabilities_assistant;
        }
        if (i == 3) {
            return R.string.fg_capabilities_voice_interaction;
        }
        if (i == 4) {
            return R.string.fg_capabilities_carrier;
        }
        return 0;
    }

    private final void logPermissionChanges(LightAppPermGroup oldGroup, LightAppPermGroup newGroup, int buttonPressed) {
        long changeId = new Random().nextLong();
        for (Map.Entry<String, LightPermission> entry : oldGroup.getPermissions().entrySet()) {
            String permName = entry.getKey();
            LightPermission permission = entry.getValue();
            LightPermission newPermission = newGroup.getPermissions().get(permName);
            if (newPermission != null && (permission.isGrantedIncludingAppOp() != newPermission.isGrantedIncludingAppOp() || permission.getFlags() != newPermission.getFlags())) {
                logAppPermissionFragmentActionReported(changeId, newPermission, buttonPressed);
            }
        }
    }

    private final void logAppPermissionFragmentActionReported(long changeId, LightPermission permission, int buttonPressed) {
        Integer packageUid = KotlinUtils.INSTANCE.getPackageUid(this.app, this.packageName, this.user);
        if (packageUid != null) {
            int uid = packageUid.intValue();
            PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSION_FRAGMENT_ACTION_REPORTED, this.sessionId, changeId, uid, this.packageName, permission.getPermInfo().getName(), permission.isGrantedIncludingAppOp(), permission.getFlags(), buttonPressed);
            Log.v(LOG_TAG, "Permission changed via UI with sessionId=" + this.sessionId + " changeId=" + changeId + " uid=" + uid + " packageName=" + this.packageName + " permission=" + permission.getPermInfo().getName() + " isGranted=" + permission.isGrantedIncludingAppOp() + " permissionFlags=" + permission.getFlags() + " buttonPressed=" + buttonPressed);
        }
    }

    public final void logAppPermissionFragmentViewed() {
        Integer packageUid = KotlinUtils.INSTANCE.getPackageUid(this.app, this.packageName, this.user);
        if (packageUid == null) {
            return;
        }
        int uid = packageUid.intValue();
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSION_FRAGMENT_VIEWED, this.sessionId, uid, this.packageName, this.permGroupName);
        Log.v(LOG_TAG, "AppPermission fragment viewed with sessionId=" + this.sessionId + " uid=" + uid + " packageName=" + this.packageName + "permGroupName=" + this.permGroupName);
    }
}
