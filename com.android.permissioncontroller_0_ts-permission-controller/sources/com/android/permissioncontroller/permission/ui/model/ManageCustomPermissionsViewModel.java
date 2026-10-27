package com.android.permissioncontroller.permission.ui.model;

import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.fragment.FragmentKt;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.PermGroupsPackagesUiInfoLiveData;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ManageCustomPermissionsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/ManageCustomPermissionsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "uiDataLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesUiInfoLiveData;", "getUiDataLiveData", "()Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesUiInfoLiveData;", "showPermissionApps", "", "fragment", "Landroidx/fragment/app/Fragment;", "args", "Landroid/os/Bundle;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class ManageCustomPermissionsViewModel extends AndroidViewModel {
    private final Application app;
    private final PermGroupsPackagesUiInfoLiveData uiDataLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageCustomPermissionsViewModel(Application app) {
        super(app);
        Intrinsics.checkParameterIsNotNull(app, "app");
        this.app = app;
        this.uiDataLiveData = new PermGroupsPackagesUiInfoLiveData(this.app, new UsedCustomPermGroupNamesLiveData());
    }

    public final PermGroupsPackagesUiInfoLiveData getUiDataLiveData() {
        return this.uiDataLiveData;
    }

    public final void showPermissionApps(Fragment fragment, Bundle args) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(args, "args");
        KotlinUtilsKt.navigateSafe(FragmentKt.findNavController(fragment), R.id.manage_to_perm_apps, args);
    }
}
