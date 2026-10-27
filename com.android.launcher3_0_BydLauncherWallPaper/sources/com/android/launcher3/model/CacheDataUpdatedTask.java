package com.android.launcher3.model;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.icons.IconCache;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class CacheDataUpdatedTask extends BaseModelUpdateTask {
    public static final int OP_CACHE_UPDATE = 1;
    public static final int OP_SESSION_UPDATE = 2;
    private final int mOp;
    private final HashSet<String> mPackages;
    private final UserHandle mUser;

    public CacheDataUpdatedTask(int op, UserHandle user, HashSet<String> packages) {
        this.mOp = op;
        this.mUser = user;
        this.mPackages = packages;
    }

    @Override // com.android.launcher3.model.BaseModelUpdateTask
    public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
        IconCache iconCache = app.getIconCache();
        final ArrayList<AppInfo> arrayList = new ArrayList<>();
        ArrayList<WorkspaceItemInfo> arrayList2 = new ArrayList<>();
        synchronized (dataModel) {
            for (ItemInfo itemInfo : dataModel.itemsIdMap) {
                if ((itemInfo instanceof WorkspaceItemInfo) && this.mUser.equals(itemInfo.user)) {
                    WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemInfo;
                    ComponentName targetComponent = workspaceItemInfo.getTargetComponent();
                    if (workspaceItemInfo.itemType == 0 && isValidShortcut(workspaceItemInfo) && targetComponent != null && this.mPackages.contains(targetComponent.getPackageName())) {
                        iconCache.getTitleAndIcon(workspaceItemInfo, workspaceItemInfo.usingLowResIcon());
                        arrayList2.add(workspaceItemInfo);
                    }
                }
            }
            apps.updateIconsAndLabels(this.mPackages, this.mUser, arrayList);
        }
        bindUpdatedWorkspaceItems(arrayList2);
        if (arrayList.isEmpty()) {
            return;
        }
        scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.CacheDataUpdatedTask.1
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindAppsAddedOrUpdated(arrayList);
            }
        });
    }

    public boolean isValidShortcut(WorkspaceItemInfo si) {
        int i = this.mOp;
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            return false;
        }
        return si.hasPromiseIconUi();
    }
}
