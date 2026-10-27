package com.android.permissioncontroller.role.model;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Process;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes8.dex */
public class Permissions {
    private static final boolean DEBUG = false;
    private static ArrayMap<String, List<String>> sBackgroundToForegroundPermissions;
    private static ArrayMap<String, String> sForegroundToBackgroundPermission;
    private static final String LOG_TAG = Permissions.class.getSimpleName();
    private static final Object sForegroundBackgroundPermissionMappingsLock = new Object();

    public static boolean grant(String packageName, List<String> permissions, boolean overrideDisabledSystemPackage, boolean overrideUserSetAndFixed, boolean setGrantedByRole, boolean setGrantedByDefault, boolean setSystemFixed, Context context) {
        List<String> callLogPermissions;
        PackageInfo disabledSystemPackageInfo;
        if (setGrantedByRole == setGrantedByDefault) {
            throw new IllegalArgumentException("Permission must be either granted by role, or granted by default, but not both");
        }
        PackageInfo packageInfo = getPackageInfo(packageName, context);
        if (packageInfo == null || ArrayUtils.isEmpty(packageInfo.requestedPermissions)) {
            return false;
        }
        PermissionManager permissionManager = (PermissionManager) context.getSystemService(PermissionManager.class);
        List<PermissionManager.SplitPermissionInfo> splitPermissions = permissionManager.getSplitPermissions();
        ArraySet<String> permissionsWithoutSplits = new ArraySet<>(permissions);
        ArraySet<String> permissionsToGrant = new ArraySet<>(permissionsWithoutSplits);
        int splitPermissionsSize = splitPermissions.size();
        for (int i = 0; i < splitPermissionsSize; i++) {
            PermissionManager.SplitPermissionInfo splitPermission = splitPermissions.get(i);
            if (packageInfo.applicationInfo.targetSdkVersion < splitPermission.getTargetSdk() && permissionsWithoutSplits.contains(splitPermission.getSplitPermission())) {
                permissionsToGrant.addAll(splitPermission.getNewPermissions());
            }
        }
        CollectionUtils.retainAll(permissionsToGrant, packageInfo.requestedPermissions);
        if (permissionsToGrant.isEmpty()) {
            return false;
        }
        if (!overrideDisabledSystemPackage && isUpdatedSystemApp(packageInfo) && (disabledSystemPackageInfo = getFactoryPackageInfo(packageName, context)) != null) {
            if (ArrayUtils.isEmpty(disabledSystemPackageInfo.requestedPermissions)) {
                return false;
            }
            CollectionUtils.retainAll(permissionsToGrant, disabledSystemPackageInfo.requestedPermissions);
            if (permissionsToGrant.isEmpty()) {
                return false;
            }
        }
        int permissionsToGrantSize = permissionsToGrant.size();
        String[] sortedPermissionsToGrant = new String[permissionsToGrantSize];
        int foregroundPermissionCount = 0;
        int nonForegroundPermissionCount = 0;
        for (int i2 = 0; i2 < permissionsToGrantSize; i2++) {
            String permission = permissionsToGrant.valueAt(i2);
            if (isForegroundPermission(permission, context)) {
                sortedPermissionsToGrant[foregroundPermissionCount] = permission;
                foregroundPermissionCount++;
            } else {
                int index = (permissionsToGrantSize - 1) - nonForegroundPermissionCount;
                sortedPermissionsToGrant[index] = permission;
                nonForegroundPermissionCount++;
            }
        }
        PackageManager packageManager = context.getPackageManager();
        boolean permissionOrAppOpChanged = false;
        Set<String> whitelistedRestrictedPermissions = new ArraySet<>(packageManager.getWhitelistedRestrictedPermissions(packageName, 1));
        List<String> smsPermissions = Utils.getPlatformPermissionNamesOfGroup("android.permission-group.SMS");
        List<String> callLogPermissions2 = Utils.getPlatformPermissionNamesOfGroup("android.permission-group.CALL_LOG");
        int sortedPermissionsToGrantLength = sortedPermissionsToGrant.length;
        int i3 = 0;
        while (i3 < sortedPermissionsToGrantLength) {
            int sortedPermissionsToGrantLength2 = sortedPermissionsToGrantLength;
            String permission2 = sortedPermissionsToGrant[i3];
            if (!smsPermissions.contains(permission2) && !callLogPermissions2.contains(permission2)) {
                callLogPermissions = callLogPermissions2;
            } else if (!whitelistedRestrictedPermissions.add(permission2)) {
                callLogPermissions = callLogPermissions2;
            } else {
                callLogPermissions = callLogPermissions2;
                packageManager.addWhitelistedRestrictedPermission(packageName, permission2, 1);
            }
            permissionOrAppOpChanged |= grantSingle(packageName, permission2, overrideUserSetAndFixed, setGrantedByRole, setGrantedByDefault, setSystemFixed, context);
            i3++;
            sortedPermissionsToGrantLength = sortedPermissionsToGrantLength2;
            smsPermissions = smsPermissions;
            callLogPermissions2 = callLogPermissions;
            whitelistedRestrictedPermissions = whitelistedRestrictedPermissions;
            packageManager = packageManager;
            sortedPermissionsToGrant = sortedPermissionsToGrant;
            permissionsToGrantSize = permissionsToGrantSize;
            splitPermissionsSize = splitPermissionsSize;
            permissionsToGrant = permissionsToGrant;
        }
        return permissionOrAppOpChanged;
    }

    private static boolean grantSingle(String packageName, String permission, boolean overrideUserSetAndFixed, boolean setGrantedByRole, boolean setGrantedByDefault, boolean setSystemFixed, Context context) {
        boolean wasPermissionOrAppOpGranted = isPermissionAndAppOpGranted(packageName, permission, context);
        if (isPermissionFixed(packageName, permission, false, overrideUserSetAndFixed, context) && !wasPermissionOrAppOpGranted) {
            return false;
        }
        if (isBackgroundPermission(permission, context)) {
            List<String> foregroundPermissions = getForegroundPermissions(permission, context);
            boolean isAnyForegroundPermissionGranted = false;
            int foregroundPermissionsSize = foregroundPermissions.size();
            int i = 0;
            while (true) {
                if (i >= foregroundPermissionsSize) {
                    break;
                }
                String foregroundPermission = foregroundPermissions.get(i);
                if (!isPermissionAndAppOpGranted(packageName, foregroundPermission, context)) {
                    i++;
                } else {
                    isAnyForegroundPermissionGranted = true;
                    break;
                }
            }
            if (!isAnyForegroundPermissionGranted) {
                return false;
            }
        }
        boolean permissionOrAppOpChanged = grantPermissionAndAppOp(packageName, permission, context);
        int newFlags = 0;
        if (!wasPermissionOrAppOpGranted && setGrantedByRole) {
            newFlags = 0 | 32768;
        }
        if (setGrantedByDefault) {
            newFlags |= 32;
        }
        if (setSystemFixed) {
            newFlags |= 16;
        }
        int newMask = newFlags | 64;
        if (!wasPermissionOrAppOpGranted) {
            newMask |= 3;
        }
        if (setGrantedByDefault && !setSystemFixed) {
            int oldFlags = getPermissionFlags(packageName, permission, context);
            if ((oldFlags & 32) != 0 && (oldFlags & 16) != 0) {
                newMask |= 16;
            }
        }
        setPermissionFlags(packageName, permission, newFlags, newMask, context);
        return permissionOrAppOpChanged;
    }

    private static boolean isPermissionAndAppOpGranted(String packageName, String permission, Context context) {
        Integer foregroundAppOpMode;
        if (!isPermissionGrantedWithoutCheckingAppOp(packageName, permission, context) || isPermissionReviewRequired(packageName, permission, context)) {
            return false;
        }
        if (!isBackgroundPermission(permission, context)) {
            String appOp = getPermissionAppOp(permission);
            if (appOp == null) {
                return true;
            }
            Integer appOpMode = getAppOpMode(packageName, appOp, context);
            if (appOpMode == null) {
                return false;
            }
            return !isForegroundPermission(permission, context) ? appOpMode.intValue() == 0 : appOpMode.intValue() == 4 || appOpMode.intValue() == 0;
        }
        List<String> foregroundPermissions = getForegroundPermissions(permission, context);
        int foregroundPermissionsSize = foregroundPermissions.size();
        for (int i = 0; i < foregroundPermissionsSize; i++) {
            String foregroundPermission = foregroundPermissions.get(i);
            String foregroundAppOp = getPermissionAppOp(foregroundPermission);
            if (foregroundAppOp != null && (foregroundAppOpMode = getAppOpMode(packageName, foregroundAppOp, context)) != null && foregroundAppOpMode.intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean grantPermissionAndAppOp(String packageName, String permission, Context context) {
        int appOpMode;
        boolean permissionOrAppOpChanged = grantPermissionWithoutAppOp(packageName, permission, context);
        if (!isBackgroundPermission(permission, context)) {
            String appOp = getPermissionAppOp(permission);
            if (appOp == null) {
                return false;
            }
            if (!isForegroundPermission(permission, context)) {
                appOpMode = 0;
            } else {
                String backgroundPermission = getBackgroundPermission(permission, context);
                if (!isPermissionAndAppOpGranted(packageName, backgroundPermission, context)) {
                    appOpMode = 4;
                } else {
                    appOpMode = 0;
                }
            }
            boolean permissionOrAppOpChanged2 = setAppOpUidMode(packageName, appOp, appOpMode, context);
            return permissionOrAppOpChanged2;
        }
        List<String> foregroundPermissions = getForegroundPermissions(permission, context);
        int foregroundPermissionsSize = foregroundPermissions.size();
        for (int i = 0; i < foregroundPermissionsSize; i++) {
            String foregroundPermission = foregroundPermissions.get(i);
            String foregroundAppOp = getPermissionAppOp(foregroundPermission);
            if (foregroundAppOp != null) {
                permissionOrAppOpChanged |= setAppOpUidMode(packageName, foregroundAppOp, 0, context);
            }
        }
        return permissionOrAppOpChanged;
    }

    public static boolean revoke(String packageName, List<String> permissions, boolean onlyIfGrantedByRole, boolean onlyIfGrantedByDefault, boolean overrideSystemFixed, Context context) {
        PackageInfo packageInfo = getPackageInfo(packageName, context);
        if (packageInfo == null || ArrayUtils.isEmpty(packageInfo.requestedPermissions)) {
            return false;
        }
        ArraySet<String> permissionsToRevoke = new ArraySet<>(permissions);
        CollectionUtils.retainAll(permissionsToRevoke, packageInfo.requestedPermissions);
        if (permissionsToRevoke.isEmpty()) {
            return false;
        }
        int permissionsToRevokeSize = permissionsToRevoke.size();
        String[] sortedPermissionsToRevoke = new String[permissionsToRevokeSize];
        int backgroundPermissionCount = 0;
        int nonBackgroundPermissionCount = 0;
        for (int i = 0; i < permissionsToRevokeSize; i++) {
            String permission = permissionsToRevoke.valueAt(i);
            if (isBackgroundPermission(permission, context)) {
                sortedPermissionsToRevoke[backgroundPermissionCount] = permission;
                backgroundPermissionCount++;
            } else {
                int index = (permissionsToRevokeSize - 1) - nonBackgroundPermissionCount;
                sortedPermissionsToRevoke[index] = permission;
                nonBackgroundPermissionCount++;
            }
        }
        PackageManager packageManager = context.getPackageManager();
        Set<String> whitelistedRestrictedPermissions = packageManager.getWhitelistedRestrictedPermissions(packageName, 7);
        int sortedPermissionsToRevokeLength = sortedPermissionsToRevoke.length;
        boolean permissionOrAppOpChanged = false;
        int i2 = 0;
        while (i2 < sortedPermissionsToRevokeLength) {
            String permission2 = sortedPermissionsToRevoke[i2];
            PackageInfo packageInfo2 = packageInfo;
            int i3 = i2;
            int sortedPermissionsToRevokeLength2 = sortedPermissionsToRevokeLength;
            Set<String> whitelistedRestrictedPermissions2 = whitelistedRestrictedPermissions;
            permissionOrAppOpChanged |= revokeSingle(packageName, permission2, onlyIfGrantedByRole, onlyIfGrantedByDefault, overrideSystemFixed, context);
            if (!isPermissionGrantedByDefault(packageName, permission2, context) && whitelistedRestrictedPermissions2.remove(permission2)) {
                packageManager.removeWhitelistedRestrictedPermission(packageName, permission2, 1);
            }
            i2 = i3 + 1;
            whitelistedRestrictedPermissions = whitelistedRestrictedPermissions2;
            packageInfo = packageInfo2;
            sortedPermissionsToRevokeLength = sortedPermissionsToRevokeLength2;
        }
        return permissionOrAppOpChanged;
    }

    private static boolean revokeSingle(String packageName, String permission, boolean onlyIfGrantedByRole, boolean onlyIfGrantedByDefault, boolean overrideSystemFixed, Context context) {
        if (onlyIfGrantedByRole == onlyIfGrantedByDefault) {
            throw new IllegalArgumentException("Permission can be revoked only if either granted by role, or granted by default, but not both");
        }
        if (onlyIfGrantedByRole) {
            if (!isPermissionGrantedByRole(packageName, permission, context)) {
                return false;
            }
            setPermissionFlags(packageName, permission, 0, 32768, context);
        }
        if (onlyIfGrantedByDefault) {
            if (!isPermissionGrantedByDefault(packageName, permission, context)) {
                return false;
            }
            setPermissionFlags(packageName, permission, 0, 32, context);
        }
        if (isPermissionFixed(packageName, permission, overrideSystemFixed, false, context) && isPermissionAndAppOpGranted(packageName, permission, context)) {
            return false;
        }
        if (isForegroundPermission(permission, context)) {
            String backgroundPermission = getBackgroundPermission(permission, context);
            if (isPermissionAndAppOpGranted(packageName, backgroundPermission, context)) {
                return false;
            }
        }
        return revokePermissionAndAppOp(packageName, permission, context);
    }

    private static boolean revokePermissionAndAppOp(String packageName, String permission, Context context) {
        String foregroundAppOp;
        boolean isRuntimePermissionsSupported = isRuntimePermissionsSupported(packageName, context);
        boolean permissionOrAppOpChanged = isRuntimePermissionsSupported ? false | revokePermissionWithoutAppOp(packageName, permission, context) : false;
        if (!isBackgroundPermission(permission, context)) {
            String appOp = getPermissionAppOp(permission);
            if (appOp == null) {
                return false;
            }
            int appOpMode = getDefaultAppOpMode(appOp);
            boolean appOpModeChanged = setAppOpUidMode(packageName, appOp, appOpMode, context);
            permissionOrAppOpChanged |= appOpModeChanged;
            if (appOpModeChanged && !isRuntimePermissionsSupported && (appOpMode == 4 || appOpMode == 0)) {
                setPermissionFlags(packageName, permission, 64, 64, context);
            }
        } else {
            List<String> foregroundPermissions = getForegroundPermissions(permission, context);
            int foregroundPermissionsSize = foregroundPermissions.size();
            for (int i = 0; i < foregroundPermissionsSize; i++) {
                String foregroundPermission = foregroundPermissions.get(i);
                if (isPermissionAndAppOpGranted(packageName, foregroundPermission, context) && (foregroundAppOp = getPermissionAppOp(foregroundPermission)) != null) {
                    permissionOrAppOpChanged |= setAppOpUidMode(packageName, foregroundAppOp, 4, context);
                }
            }
        }
        return permissionOrAppOpChanged;
    }

    private static PackageInfo getPackageInfo(String packageName, Context context) {
        return getPackageInfo(packageName, 0, context);
    }

    private static PackageInfo getFactoryPackageInfo(String packageName, Context context) {
        return getPackageInfo(packageName, 2097152, context);
    }

    private static PackageInfo getPackageInfo(String packageName, int extraFlags, Context context) {
        return PackageUtils.getPackageInfo(packageName, extraFlags | 8192 | 4096, context);
    }

    private static boolean isUpdatedSystemApp(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 128) == 0) ? false : true;
    }

    static boolean isRuntimePermissionsSupported(String packageName, Context context) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        return applicationInfo != null && applicationInfo.targetSdkVersion >= 23;
    }

    private static int getPermissionFlags(String packageName, String permission, Context context) {
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        return packageManager.getPermissionFlags(permission, packageName, user);
    }

    private static boolean isPermissionFixed(String packageName, String permission, boolean overrideSystemFixed, boolean overrideUserSetAndFixed, Context context) {
        int flags = getPermissionFlags(packageName, permission, context);
        int fixedFlags = 4;
        if (!overrideSystemFixed) {
            fixedFlags = 4 | 16;
        }
        if (!overrideUserSetAndFixed) {
            fixedFlags |= 3;
        }
        return (flags & fixedFlags) != 0;
    }

    private static boolean isPermissionGrantedByDefault(String packageName, String permission, Context context) {
        int flags = getPermissionFlags(packageName, permission, context);
        return (flags & 32) != 0;
    }

    private static boolean isPermissionGrantedByRole(String packageName, String permission, Context context) {
        int flags = getPermissionFlags(packageName, permission, context);
        return (32768 & flags) != 0;
    }

    private static boolean isPermissionReviewRequired(String packageName, String permission, Context context) {
        int flags = getPermissionFlags(packageName, permission, context);
        return (flags & 64) != 0;
    }

    private static void setPermissionFlags(String packageName, String permission, int flags, int mask, Context context) {
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        packageManager.updatePermissionFlags(permission, packageName, mask, flags, user);
    }

    private static boolean isPermissionGrantedWithoutCheckingAppOp(String packageName, String permission, Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.checkPermission(permission, packageName) == 0;
    }

    private static boolean grantPermissionWithoutAppOp(String packageName, String permission, Context context) {
        if (isPermissionGrantedWithoutCheckingAppOp(packageName, permission, context)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        packageManager.grantRuntimePermission(packageName, permission, user);
        return true;
    }

    private static boolean revokePermissionWithoutAppOp(String packageName, String permission, Context context) {
        if (!isPermissionGrantedWithoutCheckingAppOp(packageName, permission, context)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        packageManager.revokeRuntimePermission(packageName, permission, user);
        return true;
    }

    private static boolean isForegroundPermission(String permission, Context context) {
        ensureForegroundBackgroundPermissionMappings(context);
        return sForegroundToBackgroundPermission.containsKey(permission);
    }

    private static String getBackgroundPermission(String foregroundPermission, Context context) {
        ensureForegroundBackgroundPermissionMappings(context);
        return sForegroundToBackgroundPermission.get(foregroundPermission);
    }

    private static boolean isBackgroundPermission(String permission, Context context) {
        ensureForegroundBackgroundPermissionMappings(context);
        return sBackgroundToForegroundPermissions.containsKey(permission);
    }

    private static List<String> getForegroundPermissions(String backgroundPermission, Context context) {
        ensureForegroundBackgroundPermissionMappings(context);
        return sBackgroundToForegroundPermissions.get(backgroundPermission);
    }

    private static void ensureForegroundBackgroundPermissionMappings(Context context) {
        synchronized (sForegroundBackgroundPermissionMappingsLock) {
            if (sForegroundToBackgroundPermission == null && sBackgroundToForegroundPermissions == null) {
                createForegroundBackgroundPermissionMappings(context);
            }
        }
    }

    private static void createForegroundBackgroundPermissionMappings(Context context) {
        List<String> permissions = new ArrayList<>();
        sBackgroundToForegroundPermissions = new ArrayMap<>();
        PackageManager packageManager = context.getPackageManager();
        List<PermissionGroupInfo> permissionGroupInfos = packageManager.getAllPermissionGroups(0);
        int permissionGroupInfosSize = permissionGroupInfos.size();
        for (int permissionGroupInfosIndex = 0; permissionGroupInfosIndex < permissionGroupInfosSize; permissionGroupInfosIndex++) {
            PermissionGroupInfo permissionGroupInfo = permissionGroupInfos.get(permissionGroupInfosIndex);
            try {
                List<PermissionInfo> permissionInfos = Utils.getPermissionInfosForGroup(packageManager, permissionGroupInfo.name);
                int permissionInfosSize = permissionInfos.size();
                for (int permissionInfosIndex = 0; permissionInfosIndex < permissionInfosSize; permissionInfosIndex++) {
                    PermissionInfo permissionInfo = permissionInfos.get(permissionInfosIndex);
                    String permission = permissionInfo.name;
                    permissions.add(permission);
                    String backgroundPermission = permissionInfo.backgroundPermission;
                    if (backgroundPermission != null) {
                        List<String> foregroundPermissions = sBackgroundToForegroundPermissions.get(backgroundPermission);
                        if (foregroundPermissions == null) {
                            foregroundPermissions = new ArrayList();
                            sBackgroundToForegroundPermissions.put(backgroundPermission, foregroundPermissions);
                        }
                        foregroundPermissions.add(permission);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(LOG_TAG, "Cannot get permissions for group: " + permissionGroupInfo.name);
            }
        }
        sBackgroundToForegroundPermissions.retainAll(permissions);
        sForegroundToBackgroundPermission = new ArrayMap<>();
        int backgroundToForegroundPermissionsSize = sBackgroundToForegroundPermissions.size();
        for (int backgroundToForegroundPermissionsIndex = 0; backgroundToForegroundPermissionsIndex < backgroundToForegroundPermissionsSize; backgroundToForegroundPermissionsIndex++) {
            String backgroundPerimssion = sBackgroundToForegroundPermissions.keyAt(backgroundToForegroundPermissionsIndex);
            List<String> foregroundPermissions2 = sBackgroundToForegroundPermissions.valueAt(backgroundToForegroundPermissionsIndex);
            int foregroundPermissionsSize = foregroundPermissions2.size();
            for (int foregroundPermissionsIndex = 0; foregroundPermissionsIndex < foregroundPermissionsSize; foregroundPermissionsIndex++) {
                String foregroundPermission = foregroundPermissions2.get(foregroundPermissionsIndex);
                sForegroundToBackgroundPermission.put(foregroundPermission, backgroundPerimssion);
            }
        }
    }

    private static String getPermissionAppOp(String permission) {
        return AppOpsManager.permissionToOp(permission);
    }

    private static Integer getAppOpMode(String packageName, String appOp, Context context) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        if (applicationInfo == null) {
            return null;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        return Integer.valueOf(appOpsManager.unsafeCheckOpRaw(appOp, applicationInfo.uid, packageName));
    }

    static int getDefaultAppOpMode(String appOp) {
        return AppOpsManager.opToDefaultMode(appOp);
    }

    static boolean setAppOpUidMode(String packageName, String appOp, int mode, Context context) {
        return setAppOpMode(packageName, appOp, mode, true, context);
    }

    static boolean setAppOpPackageMode(String packageName, String appOp, int mode, Context context) {
        return setAppOpMode(packageName, appOp, mode, false, context);
    }

    private static boolean setAppOpMode(String packageName, String appOp, int mode, boolean setUidMode, Context context) {
        Integer currentMode = getAppOpMode(packageName, appOp, context);
        if (currentMode != null && currentMode.intValue() == mode) {
            return false;
        }
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        if (applicationInfo == null) {
            Log.e(LOG_TAG, "Cannot get ApplicationInfo for package to set app op mode: " + packageName);
            return false;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        if (setUidMode) {
            appOpsManager.setUidMode(appOp, applicationInfo.uid, mode);
            return true;
        }
        appOpsManager.setMode(appOp, applicationInfo.uid, packageName, mode);
        return true;
    }
}
