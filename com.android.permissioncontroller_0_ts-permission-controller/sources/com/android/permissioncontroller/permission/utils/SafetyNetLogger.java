package com.android.permissioncontroller.permission.utils;

import android.content.pm.PackageInfo;
import android.os.Process;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.EventLog;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class SafetyNetLogger {
    private static final String PERMISSIONS_REQUESTED = "individual_permissions_requested";
    private static final String PERMISSIONS_TOGGLED = "individual_permissions_toggled";
    private static final int SNET_NET_EVENT_LOG_TAG = 1397638484;

    private SafetyNetLogger() {
    }

    public static void logPermissionsRequested(PackageInfo packageInfo, List<AppPermissionGroup> groups) {
        EventLog.writeEvent(SNET_NET_EVENT_LOG_TAG, PERMISSIONS_REQUESTED, Integer.valueOf(packageInfo.applicationInfo.uid), buildChangedPermissionForPackageMessage(packageInfo.packageName, groups));
    }

    public static void logPermissionsToggled(ArraySet<AppPermissionGroup> groups) {
        ArrayMap<String, ArrayList<AppPermissionGroup>> groupsByPackage = new ArrayMap<>();
        int numGroups = groups.size();
        for (int i = 0; i < numGroups; i++) {
            AppPermissionGroup group = groups.valueAt(i);
            ArrayList<AppPermissionGroup> groupsForThisPackage = groupsByPackage.get(group.getApp().packageName);
            if (groupsForThisPackage == null) {
                groupsForThisPackage = new ArrayList<>();
                groupsByPackage.put(group.getApp().packageName, groupsForThisPackage);
            }
            groupsForThisPackage.add(group);
            if (group.getBackgroundPermissions() != null) {
                groupsForThisPackage.add(group.getBackgroundPermissions());
            }
        }
        int numPackages = groupsByPackage.size();
        for (int i2 = 0; i2 < numPackages; i2++) {
            EventLog.writeEvent(SNET_NET_EVENT_LOG_TAG, PERMISSIONS_TOGGLED, Integer.valueOf(Process.myUid()), buildChangedPermissionForPackageMessage(groupsByPackage.keyAt(i2), groupsByPackage.valueAt(i2)));
        }
    }

    public static void logPermissionToggled(AppPermissionGroup group) {
        ArraySet groups = new ArraySet(1);
        groups.add(group);
        logPermissionsToggled(groups);
    }

    public static void logPermissionToggled(LightAppPermGroup group, boolean logOnlyBackground) {
        EventLog.writeEvent(SNET_NET_EVENT_LOG_TAG, PERMISSIONS_TOGGLED, Integer.valueOf(Process.myUid()), buildChangedPermissionForPackageMessage(group, logOnlyBackground));
    }

    public static void logPermissionToggled(LightAppPermGroup group) {
        logPermissionToggled(group, false);
    }

    private static String buildChangedPermissionForPackageMessage(LightAppPermGroup group, boolean logOnlyBackground) {
        StringBuilder builder = new StringBuilder();
        builder.append(group.getPackageInfo().getPackageName());
        builder.append(':');
        for (LightPermission permission : group.getPermissions().values()) {
            if (!logOnlyBackground || group.getBackgroundPermNames().contains(permission.getName())) {
                if (builder.length() > 0) {
                    builder.append(';');
                }
                builder.append(permission.getName());
                builder.append('|');
                builder.append(permission.isGrantedIncludingAppOp());
                builder.append('|');
                builder.append(permission.getFlags());
            }
        }
        return builder.toString();
    }

    private static void buildChangedPermissionForGroup(AppPermissionGroup group, StringBuilder builder) {
        int permissionCount = group.getPermissions().size();
        for (int permissionNum = 0; permissionNum < permissionCount; permissionNum++) {
            Permission permission = group.getPermissions().get(permissionNum);
            if (builder.length() > 0) {
                builder.append(';');
            }
            builder.append(permission.getName());
            builder.append('|');
            builder.append(permission.isGrantedIncludingAppOp());
            builder.append('|');
            builder.append(permission.getFlags());
        }
    }

    private static String buildChangedPermissionForPackageMessage(String packageName, List<AppPermissionGroup> groups) {
        StringBuilder builder = new StringBuilder();
        builder.append(packageName);
        builder.append(':');
        int groupCount = groups.size();
        for (int groupNum = 0; groupNum < groupCount; groupNum++) {
            AppPermissionGroup group = groups.get(groupNum);
            buildChangedPermissionForGroup(group, builder);
            if (group.getBackgroundPermissions() != null) {
                buildChangedPermissionForGroup(group.getBackgroundPermissions(), builder);
            }
        }
        return builder.toString();
    }
}
