package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageManagerHelper;

/* JADX INFO: loaded from: classes4.dex */
public class AppInfo extends ItemInfoWithIcon {
    public ComponentName componentName;
    public Intent intent;

    public AppInfo() {
        this.itemType = 0;
    }

    @Override // com.android.launcher3.ItemInfo
    public Intent getIntent() {
        return this.intent;
    }

    public AppInfo(Context context, LauncherActivityInfo info, UserHandle user) {
        this(info, user, UserManagerCompat.getInstance(context).isQuietModeEnabled(user));
    }

    public AppInfo(LauncherActivityInfo info, UserHandle user, boolean quietModeEnabled) {
        this.componentName = info.getComponentName();
        this.container = -1;
        this.user = user;
        this.intent = makeLaunchIntent(info);
        if (quietModeEnabled) {
            this.runtimeStatusFlags |= 8;
        }
        updateRuntimeFlagsForActivityTarget(this, info);
    }

    public AppInfo(AppInfo info) {
        super(info);
        this.componentName = info.componentName;
        this.title = Utilities.trim(info.title);
        this.intent = new Intent(info.intent);
    }

    @Override // com.android.launcher3.ItemInfo
    protected String dumpProperties() {
        return super.dumpProperties() + " componentName=" + this.componentName;
    }

    public WorkspaceItemInfo makeWorkspaceItem() {
        return new WorkspaceItemInfo(this);
    }

    public ComponentKey toComponentKey() {
        return new ComponentKey(this.componentName, this.user);
    }

    public static Intent makeLaunchIntent(LauncherActivityInfo info) {
        return makeLaunchIntent(info.getComponentName());
    }

    public static Intent makeLaunchIntent(ComponentName cn) {
        return new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(cn).setFlags(270532608);
    }

    public static void updateRuntimeFlagsForActivityTarget(ItemInfoWithIcon info, LauncherActivityInfo lai) {
        ApplicationInfo applicationInfo = lai.getApplicationInfo();
        if (PackageManagerHelper.isAppSuspended(applicationInfo)) {
            info.runtimeStatusFlags |= 4;
        }
        info.runtimeStatusFlags |= (applicationInfo.flags & 1) == 0 ? 128 : 64;
        if (Utilities.ATLEAST_OREO && applicationInfo.targetSdkVersion >= 26 && Process.myUserHandle().equals(lai.getUser())) {
            info.runtimeStatusFlags |= 256;
        }
    }
}
