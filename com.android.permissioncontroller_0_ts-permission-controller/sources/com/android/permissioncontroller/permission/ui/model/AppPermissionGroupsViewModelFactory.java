package com.android.permissioncontroller.permission.ui.model;

import android.os.UserHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "packageName", "", "user", "Landroid/os/UserHandle;", "sessionId", "", "(Ljava/lang/String;Landroid/os/UserHandle;J)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppPermissionGroupsViewModelFactory implements ViewModelProvider.Factory {
    private final String packageName;
    private final long sessionId;
    private final UserHandle user;

    public AppPermissionGroupsViewModelFactory(String packageName, UserHandle user, long sessionId) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.packageName = packageName;
        this.user = user;
        this.sessionId = sessionId;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
        return new AppPermissionGroupsViewModel(this.packageName, this.user, this.sessionId);
    }
}
