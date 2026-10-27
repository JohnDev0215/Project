package com.android.permissioncontroller.permission.ui.model;

import android.app.Application;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.arch.core.util.Function;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.navigation.fragment.FragmentKt;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.PermGroupsPackagesUiInfoLiveData;
import com.android.permissioncontroller.permission.data.StandardPermGroupNamesLiveData;
import com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData;
import com.android.permissioncontroller.permission.service.AutoRevokeService;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ManageStandardPermissionsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/ManageStandardPermissionsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "numAutoRevoked", "Landroidx/lifecycle/LiveData;", "", "kotlin.jvm.PlatformType", "getNumAutoRevoked", "()Landroidx/lifecycle/LiveData;", "numCustomPermGroups", "Lcom/android/permissioncontroller/permission/ui/model/NumCustomPermGroupsWithPackagesLiveData;", "getNumCustomPermGroups", "()Lcom/android/permissioncontroller/permission/ui/model/NumCustomPermGroupsWithPackagesLiveData;", "uiDataLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesUiInfoLiveData;", "getUiDataLiveData", "()Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesUiInfoLiveData;", AutoRevokeService.SHOW_AUTO_REVOKE, "", "fragment", "Landroidx/fragment/app/Fragment;", "args", "Landroid/os/Bundle;", "showCustomPermissions", "showPermissionApps", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class ManageStandardPermissionsViewModel extends AndroidViewModel {
    private final Application app;
    private final LiveData<Integer> numAutoRevoked;
    private final NumCustomPermGroupsWithPackagesLiveData numCustomPermGroups;
    private final PermGroupsPackagesUiInfoLiveData uiDataLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageStandardPermissionsViewModel(Application app) {
        super(app);
        Intrinsics.checkParameterIsNotNull(app, "app");
        this.app = app;
        this.uiDataLiveData = new PermGroupsPackagesUiInfoLiveData(this.app, StandardPermGroupNamesLiveData.INSTANCE);
        this.numCustomPermGroups = new NumCustomPermGroupsWithPackagesLiveData();
        LiveData<Integer> map = Transformations.map(UnusedAutoRevokedPackagesLiveData.INSTANCE, new Function<X, Y>() { // from class: com.android.permissioncontroller.permission.ui.model.ManageStandardPermissionsViewModel$numAutoRevoked$1
            @Override // androidx.arch.core.util.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return Integer.valueOf(apply((Map<Pair<String, UserHandle>, ? extends Set<String>>) obj));
            }

            public final int apply(Map<Pair<String, UserHandle>, ? extends Set<String>> map2) {
                if (map2 != null) {
                    return map2.size();
                }
                return 0;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(Unus…      it?.size ?: 0\n    }");
        this.numAutoRevoked = map;
    }

    public final PermGroupsPackagesUiInfoLiveData getUiDataLiveData() {
        return this.uiDataLiveData;
    }

    public final NumCustomPermGroupsWithPackagesLiveData getNumCustomPermGroups() {
        return this.numCustomPermGroups;
    }

    public final LiveData<Integer> getNumAutoRevoked() {
        return this.numAutoRevoked;
    }

    public final void showCustomPermissions(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.standard_to_custom, args);
    }

    public final void showPermissionApps(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.manage_to_perm_apps, args);
    }

    public final void showAutoRevoke(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.manage_to_auto_revoke, args);
    }
}
