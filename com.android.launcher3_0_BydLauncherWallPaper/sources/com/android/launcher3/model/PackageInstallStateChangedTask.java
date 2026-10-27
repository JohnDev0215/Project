package com.android.launcher3.model;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.PromiseAppInfo;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.util.InstantAppResolver;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class PackageInstallStateChangedTask extends BaseModelUpdateTask {
    private final PackageInstallerCompat.PackageInstallInfo mInstallInfo;

    public PackageInstallStateChangedTask(PackageInstallerCompat.PackageInstallInfo installInfo) {
        this.mInstallInfo = installInfo;
    }

    @Override // com.android.launcher3.model.BaseModelUpdateTask
    public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
        if (this.mInstallInfo.state == 0) {
            try {
                ApplicationInfo applicationInfo = app.getContext().getPackageManager().getApplicationInfo(this.mInstallInfo.packageName, 0);
                if (InstantAppResolver.newInstance(app.getContext()).isInstantApp(applicationInfo)) {
                    app.getModel().onPackageAdded(applicationInfo.packageName, Process.myUserHandle());
                    return;
                }
                return;
            } catch (PackageManager.NameNotFoundException unused) {
                return;
            }
        }
        synchronized (apps) {
            final PromiseAppInfo promiseAppInfo = null;
            final ArrayList arrayList = new ArrayList();
            for (int i = 0; i < apps.size(); i++) {
                AppInfo appInfo = apps.get(i);
                ComponentName targetComponent = appInfo.getTargetComponent();
                if (targetComponent != null && targetComponent.getPackageName().equals(this.mInstallInfo.packageName) && (appInfo instanceof PromiseAppInfo)) {
                    PromiseAppInfo promiseAppInfo2 = (PromiseAppInfo) appInfo;
                    if (this.mInstallInfo.state == 1) {
                        promiseAppInfo2.level = this.mInstallInfo.progress;
                        promiseAppInfo = promiseAppInfo2;
                    } else if (this.mInstallInfo.state == 2) {
                        apps.removePromiseApp(appInfo);
                        arrayList.add(appInfo);
                    }
                }
            }
            if (promiseAppInfo != null) {
                scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.PackageInstallStateChangedTask.1
                    @Override // com.android.launcher3.LauncherModel.CallbackTask
                    public void execute(LauncherModel.Callbacks callbacks) {
                        callbacks.bindPromiseAppProgressUpdated(promiseAppInfo);
                    }
                });
            }
            if (!arrayList.isEmpty()) {
                scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.PackageInstallStateChangedTask.2
                    @Override // com.android.launcher3.LauncherModel.CallbackTask
                    public void execute(LauncherModel.Callbacks callbacks) {
                        callbacks.bindAppInfosRemoved(arrayList);
                    }
                });
            }
        }
        synchronized (dataModel) {
            final HashSet hashSet = new HashSet();
            for (ItemInfo itemInfo : dataModel.itemsIdMap) {
                if (itemInfo instanceof WorkspaceItemInfo) {
                    WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemInfo;
                    ComponentName targetComponent2 = workspaceItemInfo.getTargetComponent();
                    if (workspaceItemInfo.hasPromiseIconUi() && targetComponent2 != null && this.mInstallInfo.packageName.equals(targetComponent2.getPackageName())) {
                        workspaceItemInfo.setInstallProgress(this.mInstallInfo.progress);
                        if (this.mInstallInfo.state == 2) {
                            workspaceItemInfo.status &= -5;
                        }
                        hashSet.add(workspaceItemInfo);
                    }
                }
            }
            for (LauncherAppWidgetInfo launcherAppWidgetInfo : dataModel.appWidgets) {
                if (launcherAppWidgetInfo.providerName.getPackageName().equals(this.mInstallInfo.packageName)) {
                    launcherAppWidgetInfo.installProgress = this.mInstallInfo.progress;
                    hashSet.add(launcherAppWidgetInfo);
                }
            }
            if (!hashSet.isEmpty()) {
                scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.PackageInstallStateChangedTask.3
                    @Override // com.android.launcher3.LauncherModel.CallbackTask
                    public void execute(LauncherModel.Callbacks callbacks) {
                        callbacks.bindRestoreItemsChange(hashSet);
                    }
                });
            }
        }
    }
}
