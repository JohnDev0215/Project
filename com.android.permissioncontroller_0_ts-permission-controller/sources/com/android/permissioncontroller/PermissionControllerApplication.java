package com.android.permissioncontroller;

import android.app.Application;
import android.content.ComponentName;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.SpecialAppAccessListActivity;

/* JADX INFO: loaded from: classes8.dex */
public final class PermissionControllerApplication extends Application {
    private static PermissionControllerApplication sInstance;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        PackageItemInfo.forceSafeLabels();
        updateSpecialAppAccessListActivityEnabledState();
    }

    public static PermissionControllerApplication get() {
        return sInstance;
    }

    private void updateSpecialAppAccessListActivityEnabledState() {
        int enabledState;
        ArrayMap<String, Role> roles = Roles.get(this);
        boolean hasVisibleSpecialAppAccess = false;
        int rolesSize = roles.size();
        int i = 0;
        while (true) {
            if (i >= rolesSize) {
                break;
            }
            Role role = roles.valueAt(i);
            if (!role.isAvailable(this) || !role.isVisible(this) || role.isExclusive()) {
                i++;
            } else {
                hasVisibleSpecialAppAccess = true;
                break;
            }
        }
        PackageManager packageManager = getPackageManager();
        ComponentName componentName = new ComponentName(this, (Class<?>) SpecialAppAccessListActivity.class);
        if (hasVisibleSpecialAppAccess) {
            enabledState = 0;
        } else {
            enabledState = 2;
        }
        packageManager.setComponentEnabledSetting(componentName, enabledState, 1);
    }
}
