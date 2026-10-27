package com.android.permissioncontroller.role.service;

import android.app.role.RoleControllerService;
import android.app.role.RoleManager;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class RoleControllerServiceImpl extends RoleControllerService {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = RoleControllerServiceImpl.class.getSimpleName();
    private RoleManager mRoleManager;

    public void onCreate() {
        super.onCreate();
        this.mRoleManager = (RoleManager) getSystemService(RoleManager.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onGrantDefaultRoles() {
        int currentPackageNamesSize;
        List<String> packageNamesToAdd;
        int packageNamesToAddSize;
        int currentPackageNamesIndex;
        int currentPackageNamesSize2;
        List<String> currentPackageNames;
        ArrayMap<String, Role> roleMap;
        String roleName;
        Role role;
        ArrayMap<String, Role> roleMap2 = Roles.get(this);
        List<Role> roles = new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        ArraySet<String> addedRoleNames = new ArraySet<>();
        int roleMapSize = roleMap2.size();
        for (int i = 0; i < roleMapSize; i++) {
            Role role2 = roleMap2.valueAt(i);
            if (role2.isAvailable(this)) {
                roles.add(role2);
                String roleName2 = role2.getName();
                roleNames.add(roleName2);
                if (!this.mRoleManager.isRoleAvailable(roleName2)) {
                    addedRoleNames.add(roleName2);
                }
            }
        }
        this.mRoleManager.setRoleNamesFromController(roleNames);
        int addedRoleNamesSize = addedRoleNames.size();
        for (int i2 = 0; i2 < addedRoleNamesSize; i2++) {
            roleMap2.get(addedRoleNames.valueAt(i2)).onRoleAdded(this);
        }
        int rolesSize = roles.size();
        int rolesIndex = 0;
        while (rolesIndex < rolesSize) {
            Role role3 = roles.get(rolesIndex);
            String roleName3 = role3.getName();
            List<String> currentPackageNames2 = this.mRoleManager.getRoleHolders(roleName3);
            int currentPackageNamesSize3 = currentPackageNames2.size();
            int i3 = 0;
            while (true) {
                int currentPackageNamesIndex2 = i3;
                if (currentPackageNamesIndex2 >= currentPackageNamesSize3) {
                    break;
                }
                String roleName4 = roleName3;
                String packageName = currentPackageNames2.get(currentPackageNamesIndex2);
                if (role3.isPackageQualified(packageName, this)) {
                    currentPackageNamesIndex = currentPackageNamesIndex2;
                    currentPackageNamesSize2 = currentPackageNamesSize3;
                    currentPackageNames = currentPackageNames2;
                    roleMap = roleMap2;
                    role = role3;
                    addRoleHolderInternal(role3, packageName, false, false, true);
                    roleName = roleName4;
                } else {
                    currentPackageNamesIndex = currentPackageNamesIndex2;
                    currentPackageNamesSize2 = currentPackageNamesSize3;
                    currentPackageNames = currentPackageNames2;
                    roleMap = roleMap2;
                    roleName = roleName4;
                    role = role3;
                    Log.i(LOG_TAG, "Removing package that no longer qualifies for the role, package: " + packageName + ", role: " + roleName);
                    removeRoleHolderInternal(role, packageName, false);
                }
                i3 = currentPackageNamesIndex + 1;
                roleName3 = roleName;
                role3 = role;
                roleMap2 = roleMap;
                currentPackageNamesSize3 = currentPackageNamesSize2;
                currentPackageNames2 = currentPackageNames;
            }
            String roleName5 = roleName3;
            ArrayMap<String, Role> roleMap3 = roleMap2;
            Role role4 = role3;
            List<String> currentPackageNames3 = this.mRoleManager.getRoleHolders(roleName5);
            int currentPackageNamesSize4 = currentPackageNames3.size();
            if (currentPackageNamesSize4 == 0) {
                List<String> packageNamesToAdd2 = null;
                if (addedRoleNames.contains(roleName5)) {
                    packageNamesToAdd2 = role4.getDefaultHolders(this);
                }
                if (packageNamesToAdd2 == null || packageNamesToAdd2.isEmpty()) {
                    packageNamesToAdd2 = CollectionUtils.singletonOrEmpty(role4.getFallbackHolder(this));
                }
                int packageNamesToAddSize2 = packageNamesToAdd2.size();
                int packageNamesToAddIndex = 0;
                while (packageNamesToAddIndex < packageNamesToAddSize2) {
                    List<String> currentPackageNames4 = currentPackageNames3;
                    String packageName2 = packageNamesToAdd2.get(packageNamesToAddIndex);
                    if (!role4.isPackageQualified(packageName2, this)) {
                        currentPackageNamesSize = currentPackageNamesSize4;
                        String str = LOG_TAG;
                        packageNamesToAdd = packageNamesToAdd2;
                        StringBuilder sb = new StringBuilder();
                        packageNamesToAddSize = packageNamesToAddSize2;
                        sb.append("Default/fallback role holder package doesn't qualify for the role, package: ");
                        sb.append(packageName2);
                        sb.append(", role: ");
                        sb.append(roleName5);
                        Log.e(str, sb.toString());
                    } else {
                        currentPackageNamesSize = currentPackageNamesSize4;
                        packageNamesToAdd = packageNamesToAdd2;
                        packageNamesToAddSize = packageNamesToAddSize2;
                        Log.i(LOG_TAG, "Adding package as default/fallback role holder, package: " + packageName2 + ", role: " + roleName5);
                        addRoleHolderInternal(role4, packageName2, true);
                    }
                    packageNamesToAddIndex++;
                    currentPackageNamesSize4 = currentPackageNamesSize;
                    currentPackageNames3 = currentPackageNames4;
                    packageNamesToAdd2 = packageNamesToAdd;
                    packageNamesToAddSize2 = packageNamesToAddSize;
                }
            }
            List<String> currentPackageNames5 = this.mRoleManager.getRoleHolders(roleName5);
            int currentPackageNamesSize5 = currentPackageNames5.size();
            if (role4.isExclusive() && currentPackageNamesSize5 > 1) {
                Log.w(LOG_TAG, "Multiple packages holding an exclusive role, role: " + roleName5);
                int currentPackageNamesIndex3 = 1;
                while (currentPackageNamesIndex3 < currentPackageNamesSize5) {
                    String packageName3 = currentPackageNames5.get(currentPackageNamesIndex3);
                    Log.i(LOG_TAG, "Removing extraneous package for an exclusive role, package: " + packageName3 + ", role: " + roleName5);
                    removeRoleHolderInternal(role4, packageName3, false);
                    currentPackageNamesIndex3++;
                    currentPackageNames5 = currentPackageNames5;
                    currentPackageNamesSize5 = currentPackageNamesSize5;
                }
            }
            rolesIndex++;
            roleMap2 = roleMap3;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onAddRoleHolder(String roleName, String packageName, int flags) {
        boolean added;
        if (!checkFlags(flags, 1)) {
            return false;
        }
        Role role = Roles.get(this).get(roleName);
        if (role == null) {
            Log.e(LOG_TAG, "Unknown role: " + roleName);
            return false;
        }
        if (!role.isAvailable(this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + roleName);
            return false;
        }
        if (!role.isPackageQualified(packageName, this)) {
            Log.e(LOG_TAG, "Package does not qualify for the role, package: " + packageName + ", role: " + roleName);
            return false;
        }
        boolean added2 = false;
        if (!role.isExclusive()) {
            added = false;
        } else {
            List<String> currentPackageNames = this.mRoleManager.getRoleHolders(roleName);
            int currentPackageNamesSize = currentPackageNames.size();
            for (int i = 0; i < currentPackageNamesSize; i++) {
                String currentPackageName = currentPackageNames.get(i);
                if (Objects.equals(currentPackageName, packageName)) {
                    Log.i(LOG_TAG, "Package is already a role holder, package: " + packageName + ", role: " + roleName);
                    added2 = true;
                } else {
                    boolean removed = removeRoleHolderInternal(role, currentPackageName, false);
                    if (!removed) {
                        return false;
                    }
                }
            }
            added = added2;
        }
        boolean dontKillApp = hasFlag(flags, 1);
        if (!addRoleHolderInternal(role, packageName, dontKillApp, true, added)) {
            return false;
        }
        role.onHolderAddedAsUser(packageName, Process.myUserHandle(), this);
        role.onHolderChangedAsUser(Process.myUserHandle(), this);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onRemoveRoleHolder(String roleName, String packageName, int flags) {
        if (!checkFlags(flags, 1)) {
            return false;
        }
        Role role = Roles.get(this).get(roleName);
        if (role == null) {
            Log.e(LOG_TAG, "Unknown role: " + roleName);
            return false;
        }
        if (!role.isAvailable(this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + roleName);
            return false;
        }
        boolean dontKillApp = hasFlag(flags, 1);
        boolean removed = removeRoleHolderInternal(role, packageName, dontKillApp);
        if (!removed) {
            return false;
        }
        boolean fallbackSuccessful = addFallbackRoleHolderMaybe(role);
        if (!fallbackSuccessful) {
            return false;
        }
        role.onHolderChangedAsUser(Process.myUserHandle(), this);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onClearRoleHolders(String roleName, int flags) {
        if (!checkFlags(flags, 1)) {
            return false;
        }
        Role role = Roles.get(this).get(roleName);
        if (role == null) {
            Log.e(LOG_TAG, "Unknown role: " + roleName);
            return false;
        }
        if (!role.isAvailable(this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + roleName);
            return false;
        }
        boolean dontKillApp = hasFlag(flags, 1);
        boolean cleared = clearRoleHoldersInternal(role, dontKillApp);
        if (!cleared) {
            return false;
        }
        boolean fallbackSuccessful = addFallbackRoleHolderMaybe(role);
        if (!fallbackSuccessful) {
            return false;
        }
        role.onHolderChangedAsUser(Process.myUserHandle(), this);
        return true;
    }

    private boolean addRoleHolderInternal(Role role, String packageName, boolean overrideUserSetAndFixedPermissions) {
        return addRoleHolderInternal(role, packageName, false, overrideUserSetAndFixedPermissions, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean addRoleHolderInternal(Role role, String packageName, boolean dontKillApp, boolean overrideUserSetAndFixedPermissions, boolean added) {
        role.grant(packageName, dontKillApp, overrideUserSetAndFixedPermissions, this);
        String roleName = role.getName();
        if (!added) {
            added = this.mRoleManager.addRoleHolderFromController(roleName, packageName);
        }
        if (!added) {
            Log.e(LOG_TAG, "Failed to add role holder in RoleManager, package: " + packageName + ", role: " + roleName);
        }
        return added;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean removeRoleHolderInternal(Role role, String packageName, boolean dontKillApp) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, this);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Cannot get ApplicationInfo for package: " + packageName);
        }
        if (applicationInfo != null) {
            role.revoke(packageName, dontKillApp, false, this);
        }
        String roleName = role.getName();
        boolean removed = this.mRoleManager.removeRoleHolderFromController(roleName, packageName);
        if (!removed) {
            Log.e(LOG_TAG, "Failed to remove role holder in RoleManager, package: " + packageName + ", role: " + roleName);
        }
        return removed;
    }

    private boolean clearRoleHoldersInternal(Role role, boolean dontKillApp) {
        String roleName = role.getName();
        List<String> packageNames = this.mRoleManager.getRoleHolders(roleName);
        boolean cleared = true;
        int packageNamesSize = packageNames.size();
        for (int i = 0; i < packageNamesSize; i++) {
            String packageName = packageNames.get(i);
            boolean removed = removeRoleHolderInternal(role, packageName, dontKillApp);
            if (!removed) {
                cleared = false;
            }
        }
        if (!cleared) {
            Log.e(LOG_TAG, "Failed to clear role holders, role: " + roleName);
        }
        return cleared;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean addFallbackRoleHolderMaybe(Role role) {
        String fallbackPackageName;
        String roleName = role.getName();
        List<String> currentPackageNames = this.mRoleManager.getRoleHolders(roleName);
        if (!currentPackageNames.isEmpty() || (fallbackPackageName = role.getFallbackHolder(this)) == null) {
            return true;
        }
        if (!role.isPackageQualified(fallbackPackageName, this)) {
            Log.e(LOG_TAG, "Fallback role holder package doesn't qualify for the role, package: " + fallbackPackageName + ", role: " + roleName);
            return false;
        }
        Log.i(LOG_TAG, "Adding package as fallback role holder, package: " + fallbackPackageName + ", role: " + roleName);
        return addRoleHolderInternal(role, fallbackPackageName, true);
    }

    public boolean onIsApplicationQualifiedForRole(String roleName, String packageName) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onIsApplicationVisibleForRole(String roleName, String packageName) {
        ApplicationInfo applicationInfo;
        Role role = Roles.get(this).get(roleName);
        if (role == null || !role.isAvailable(this) || !role.isPackageQualified(packageName, this) || (applicationInfo = PackageUtils.getApplicationInfo(packageName, this)) == null || !role.isApplicationVisibleAsUser(applicationInfo, Process.myUserHandle(), this)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onIsRoleVisible(String roleName) {
        Role role = Roles.get(this).get(roleName);
        if (role == null || !role.isAvailable(this)) {
            return false;
        }
        return role.isVisibleAsUser(Process.myUserHandle(), this);
    }

    private static boolean checkFlags(int flags, int allowedFlags) {
        if ((flags & allowedFlags) != flags) {
            Log.e(LOG_TAG, "flags is invalid, flags: 0x" + Integer.toHexString(flags) + ", allowed flags: 0x" + Integer.toHexString(allowedFlags));
            return false;
        }
        return true;
    }

    private static boolean hasFlag(int flags, int flag) {
        return (flags & flag) == flag;
    }
}
