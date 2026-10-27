package com.android.permissioncontroller.role.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class RequiredService extends RequiredComponent {
    public RequiredService(IntentFilterData intentFilterData, String permission) {
        super(intentFilterData, permission);
    }

    @Override // com.android.permissioncontroller.role.model.RequiredComponent
    protected List<ResolveInfo> queryIntentComponentsAsUser(Intent intent, int flags, UserHandle user, Context context) {
        Context userContext = UserUtils.getUserContext(context, user);
        PackageManager userPackageManager = userContext.getPackageManager();
        return userPackageManager.queryIntentServices(intent, flags);
    }

    @Override // com.android.permissioncontroller.role.model.RequiredComponent
    protected ComponentName getComponentComponentName(ResolveInfo resolveInfo) {
        return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    @Override // com.android.permissioncontroller.role.model.RequiredComponent
    protected String getComponentPermission(ResolveInfo resolveInfo) {
        return resolveInfo.serviceInfo.permission;
    }
}
