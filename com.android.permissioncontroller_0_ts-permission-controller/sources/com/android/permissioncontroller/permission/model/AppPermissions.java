package com.android.permissioncontroller.permission.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.ArrayMap;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class AppPermissions {
    private final CharSequence mAppLabel;
    private final Context mContext;
    private final boolean mDelayChanges;
    private final ArrayMap<String, AppPermissionGroup> mGroupNameToGroup;
    private final ArrayList<AppPermissionGroup> mGroups;
    private final Runnable mOnErrorCallback;
    private PackageInfo mPackageInfo;
    private final ArrayMap<String, AppPermissionGroup> mPermissionNameToGroup;
    private final boolean mSortGroups;

    public AppPermissions(Context context, PackageInfo packageInfo, boolean sortGroups, Runnable onErrorCallback) {
        this(context, packageInfo, sortGroups, false, onErrorCallback);
    }

    public AppPermissions(Context context, PackageInfo packageInfo, boolean sortGroups, boolean delayChanges, Runnable onErrorCallback) {
        this.mGroups = new ArrayList<>();
        this.mGroupNameToGroup = new ArrayMap<>();
        this.mPermissionNameToGroup = new ArrayMap<>();
        this.mContext = context;
        this.mPackageInfo = packageInfo;
        this.mAppLabel = Utils.getAppLabel(packageInfo.applicationInfo, context);
        this.mSortGroups = sortGroups;
        this.mDelayChanges = delayChanges;
        this.mOnErrorCallback = onErrorCallback;
        loadPermissionGroups();
    }

    public PackageInfo getPackageInfo() {
        return this.mPackageInfo;
    }

    public void refresh() {
        loadPackageInfo();
        loadPermissionGroups();
    }

    public CharSequence getAppLabel() {
        return this.mAppLabel;
    }

    public AppPermissionGroup getPermissionGroup(String name) {
        return this.mGroupNameToGroup.get(name);
    }

    public List<AppPermissionGroup> getPermissionGroups() {
        return this.mGroups;
    }

    public boolean isReviewRequired() {
        int groupCount = this.mGroups.size();
        for (int i = 0; i < groupCount; i++) {
            AppPermissionGroup group = this.mGroups.get(i);
            if (group.isReviewRequired()) {
                return true;
            }
        }
        return false;
    }

    private void loadPackageInfo() {
        try {
            this.mPackageInfo = this.mContext.createPackageContextAsUser(this.mPackageInfo.packageName, 0, UserHandle.getUserHandleForUid(this.mPackageInfo.applicationInfo.uid)).getPackageManager().getPackageInfo(this.mPackageInfo.packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Runnable runnable = this.mOnErrorCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    private void addAllPermissions(AppPermissionGroup group) {
        ArrayList<Permission> perms = group.getPermissions();
        int numPerms = perms.size();
        for (int permNum = 0; permNum < numPerms; permNum++) {
            this.mPermissionNameToGroup.put(perms.get(permNum).getName(), group);
        }
    }

    private void loadPermissionGroups() {
        AppPermissionGroup group;
        this.mGroups.clear();
        this.mGroupNameToGroup.clear();
        this.mPermissionNameToGroup.clear();
        if (this.mPackageInfo.requestedPermissions != null) {
            for (String requestedPerm : this.mPackageInfo.requestedPermissions) {
                if (getGroupForPermission(requestedPerm) == null && (group = AppPermissionGroup.create(this.mContext, this.mPackageInfo, requestedPerm, this.mDelayChanges)) != null) {
                    this.mGroups.add(group);
                    this.mGroupNameToGroup.put(group.getName(), group);
                    addAllPermissions(group);
                    AppPermissionGroup backgroundGroup = group.getBackgroundPermissions();
                    if (backgroundGroup != null) {
                        addAllPermissions(backgroundGroup);
                    }
                }
            }
            if (this.mSortGroups) {
                Collections.sort(this.mGroups);
            }
        }
    }

    public AppPermissionGroup getGroupForPermission(String permission) {
        return this.mPermissionNameToGroup.get(permission);
    }

    public void persistChanges(boolean mayKillBecauseOfAppOpsChange) {
        if (this.mDelayChanges) {
            int numGroups = this.mGroups.size();
            for (int i = 0; i < numGroups; i++) {
                AppPermissionGroup group = this.mGroups.get(i);
                group.persistChanges(mayKillBecauseOfAppOpsChange);
                AppPermissionGroup backgroundGroup = group.getBackgroundPermissions();
                if (backgroundGroup != null) {
                    backgroundGroup.persistChanges(mayKillBecauseOfAppOpsChange);
                }
            }
        }
    }
}
