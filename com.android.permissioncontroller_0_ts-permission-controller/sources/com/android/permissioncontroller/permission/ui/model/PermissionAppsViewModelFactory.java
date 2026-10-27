package com.android.permissioncontroller.permission.ui.model;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermissionAppsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ7\u0010\u000b\u001a\u0002H\f\"\n\b\u0000\u0010\f*\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\f0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/PermissionAppsViewModelFactory;", "Landroidx/lifecycle/AbstractSavedStateViewModelFactory;", "app", "Landroid/app/Application;", "groupName", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "defaultArgs", "Landroid/os/Bundle;", "(Landroid/app/Application;Ljava/lang/String;Landroidx/savedstate/SavedStateRegistryOwner;Landroid/os/Bundle;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "p1", "Ljava/lang/Class;", "state", "Landroidx/lifecycle/SavedStateHandle;", "(Ljava/lang/String;Ljava/lang/Class;Landroidx/lifecycle/SavedStateHandle;)Landroidx/lifecycle/ViewModel;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermissionAppsViewModelFactory extends AbstractSavedStateViewModelFactory {
    private final Application app;
    private final String groupName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionAppsViewModelFactory(Application app, String groupName, SavedStateRegistryOwner owner, Bundle defaultArgs) {
        super(owner, defaultArgs);
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(defaultArgs, "defaultArgs");
        this.app = app;
        this.groupName = groupName;
    }

    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
    protected <T extends ViewModel> T create(String p0, Class<T> p1, SavedStateHandle state) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Boolean bool = (Boolean) state.get(PermissionAppsViewModel.SHOULD_SHOW_SYSTEM_KEY);
        if (bool == null) {
            bool = bool;
        }
        state.set(PermissionAppsViewModel.SHOULD_SHOW_SYSTEM_KEY, bool);
        boolean z = (Boolean) state.get(PermissionAppsViewModel.HAS_SYSTEM_APPS_KEY);
        if (z == null) {
            z = true;
        }
        state.set(PermissionAppsViewModel.HAS_SYSTEM_APPS_KEY, z);
        Boolean bool2 = (Boolean) state.get(PermissionAppsViewModel.SHOW_ALWAYS_ALLOWED);
        if (bool2 == null) {
            bool2 = bool;
        }
        state.set(PermissionAppsViewModel.SHOW_ALWAYS_ALLOWED, bool2);
        Boolean bool3 = (Boolean) state.get(PermissionAppsViewModel.CREATION_LOGGED_KEY);
        state.set(PermissionAppsViewModel.CREATION_LOGGED_KEY, bool3 != null ? bool3 : false);
        return new PermissionAppsViewModel(state, this.app, this.groupName);
    }
}
