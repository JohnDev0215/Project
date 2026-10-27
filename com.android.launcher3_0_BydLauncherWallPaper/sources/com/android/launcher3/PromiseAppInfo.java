package com.android.launcher3;

import android.content.Context;
import android.content.Intent;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.util.PackageManagerHelper;

/* JADX INFO: loaded from: classes4.dex */
public class PromiseAppInfo extends AppInfo {
    public int level = 0;

    public PromiseAppInfo(PackageInstallerCompat.PackageInstallInfo installInfo) {
        this.componentName = installInfo.componentName;
        this.intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(this.componentName).setFlags(270532608);
    }

    @Override // com.android.launcher3.AppInfo
    public WorkspaceItemInfo makeWorkspaceItem() {
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(this);
        workspaceItemInfo.setInstallProgress(this.level);
        workspaceItemInfo.status |= 2;
        workspaceItemInfo.status |= 8;
        return workspaceItemInfo;
    }

    public Intent getMarketIntent(Context context) {
        return new PackageManagerHelper(context).getMarketIntent(this.componentName.getPackageName());
    }
}
