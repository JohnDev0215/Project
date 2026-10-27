package com.android.launcher3.compat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.os.Parcelable;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.util.LooperExecutor;
import com.android.launcher3.util.PackageUserKey;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppsCompatVO extends LauncherAppsCompatVL {
    LauncherAppsCompatVO(Context context) {
        super(context);
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompatVL, com.android.launcher3.compat.LauncherAppsCompat
    public ApplicationInfo getApplicationInfo(String packageName, int flags, UserHandle user) {
        try {
            ApplicationInfo applicationInfo = this.mLauncherApps.getApplicationInfo(packageName, flags, user);
            if ((applicationInfo.flags & 8388608) == 0) {
                return null;
            }
            if (applicationInfo.enabled) {
                return applicationInfo;
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompatVL, com.android.launcher3.compat.LauncherAppsCompat
    public List<ShortcutConfigActivityInfo> getCustomShortcutActivityList(PackageUserKey packageUser) {
        String str;
        List<UserHandle> userProfiles;
        ArrayList arrayList = new ArrayList();
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        if (packageUser == null) {
            userProfiles = UserManagerCompat.getInstance(this.mContext).getUserProfiles();
            str = null;
        } else {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(packageUser.mUser);
            str = packageUser.mPackageName;
            userProfiles = arrayList2;
        }
        for (UserHandle userHandle : userProfiles) {
            boolean zEquals = userHandleMyUserHandle.equals(userHandle);
            for (LauncherActivityInfo launcherActivityInfo : this.mLauncherApps.getShortcutConfigActivityList(str, userHandle)) {
                if (zEquals || launcherActivityInfo.getApplicationInfo().targetSdkVersion >= 26) {
                    arrayList.add(new ShortcutConfigActivityInfo.ShortcutConfigActivityInfoVO(launcherActivityInfo));
                }
            }
        }
        return arrayList;
    }

    public static WorkspaceItemInfo createWorkspaceItemFromPinItemRequest(Context context, final LauncherApps.PinItemRequest request, final long acceptDelay) {
        if (request == null || request.getRequestType() != 1 || !request.isValid()) {
            return null;
        }
        if (acceptDelay <= 0) {
            if (!request.accept()) {
                return null;
            }
        } else {
            new LooperExecutor(LauncherModel.getWorkerLooper()).execute(new Runnable() { // from class: com.android.launcher3.compat.LauncherAppsCompatVO.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(acceptDelay);
                    } catch (InterruptedException unused) {
                    }
                    if (request.isValid()) {
                        request.accept();
                    }
                }
            });
        }
        ShortcutInfo shortcutInfo = request.getShortcutInfo();
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(shortcutInfo, context);
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(context);
        workspaceItemInfo.applyFrom(launcherIconsObtain.createShortcutIcon(shortcutInfo, false));
        launcherIconsObtain.recycle();
        LauncherAppState.getInstance(context).getModel().updateAndBindWorkspaceItem(workspaceItemInfo, shortcutInfo);
        return workspaceItemInfo;
    }

    public static LauncherApps.PinItemRequest getPinItemRequest(Intent intent) {
        Parcelable parcelableExtra = intent.getParcelableExtra(LauncherApps.EXTRA_PIN_ITEM_REQUEST);
        if (parcelableExtra instanceof LauncherApps.PinItemRequest) {
            return (LauncherApps.PinItemRequest) parcelableExtra;
        }
        return null;
    }
}
