package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArraySet;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class BrowserRoleBehavior implements RoleBehavior {
    private static final Intent BROWSER_INTENT = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.fromParts("http", "", null));

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public String getFallbackHolder(Role role, Context context) {
        List<String> packageNames = role.getQualifyingPackagesAsUser(Process.myUserHandle(), context);
        if (packageNames.size() == 1) {
            return packageNames.get(0);
        }
        return null;
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public List<String> getQualifyingPackagesAsUser(Role role, UserHandle user, Context context) {
        return getQualifyingPackagesAsUserInternal(null, user, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public Boolean isPackageQualified(Role role, String packageName, Context context) {
        List<String> packageNames = getQualifyingPackagesAsUserInternal(packageName, Process.myUserHandle(), context);
        return Boolean.valueOf(!packageNames.isEmpty());
    }

    private List<String> getQualifyingPackagesAsUserInternal(String packageName, UserHandle user, Context context) {
        Context userContext = UserUtils.getUserContext(context, user);
        PackageManager userPackageManager = userContext.getPackageManager();
        Intent intent = BROWSER_INTENT;
        if (packageName != null) {
            intent = new Intent(intent).setPackage(packageName);
        }
        List<ResolveInfo> resolveInfos = userPackageManager.queryIntentActivities(intent, 917504);
        ArraySet<String> packageNames = new ArraySet<>();
        int resolveInfosSize = resolveInfos.size();
        for (int i = 0; i < resolveInfosSize; i++) {
            ResolveInfo resolveInfo = resolveInfos.get(i);
            if (resolveInfo.handleAllWebDataURI) {
                packageNames.add(resolveInfo.activityInfo.packageName);
            }
        }
        return new ArrayList(packageNames);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return context.getResources().getBoolean(R.bool.config_showBrowserRole);
    }
}
