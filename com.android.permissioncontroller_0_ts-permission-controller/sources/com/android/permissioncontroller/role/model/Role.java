package com.android.permissioncontroller.role.model;

import android.app.ActivityManager;
import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import androidx.preference.Preference;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.utils.PackageUtils;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class Role {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = Role.class.getSimpleName();
    private static final String PACKAGE_NAME_ANDROID_SYSTEM = "android";
    private static final String PACKAGE_NAME_SEPARATOR = ";";
    private final List<String> mAppOpPermissions;
    private final List<AppOp> mAppOps;
    private final RoleBehavior mBehavior;
    private final String mDefaultHoldersResourceName;
    private final int mDescriptionResource;
    private final boolean mExclusive;
    private final boolean mFallBackToDefaultHolder;
    private final int mLabelResource;
    private final String mName;
    private final List<String> mPermissions;
    private final List<PreferredActivity> mPreferredActivities;
    private final int mRequestDescriptionResource;
    private final int mRequestTitleResource;
    private final boolean mRequestable;
    private final List<RequiredComponent> mRequiredComponents;
    private final int mSearchKeywordsResource;
    private final int mShortLabelResource;
    private final boolean mShowNone;
    private final boolean mSystemOnly;
    private final boolean mVisible;

    public Role(String name, RoleBehavior behavior, String defaultHoldersResourceName, int descriptionResource, boolean exclusive, boolean fallBackToDefaultHolder, int labelResource, int requestDescriptionResource, int requestTitleResource, boolean requestable, int searchKeywordsResource, int shortLabelResource, boolean showNone, boolean systemOnly, boolean visible, List<RequiredComponent> requiredComponents, List<String> permissions, List<String> appOpPermissions, List<AppOp> appOps, List<PreferredActivity> preferredActivities) {
        this.mName = name;
        this.mBehavior = behavior;
        this.mDefaultHoldersResourceName = defaultHoldersResourceName;
        this.mDescriptionResource = descriptionResource;
        this.mExclusive = exclusive;
        this.mFallBackToDefaultHolder = fallBackToDefaultHolder;
        this.mLabelResource = labelResource;
        this.mRequestDescriptionResource = requestDescriptionResource;
        this.mRequestTitleResource = requestTitleResource;
        this.mRequestable = requestable;
        this.mSearchKeywordsResource = searchKeywordsResource;
        this.mShortLabelResource = shortLabelResource;
        this.mShowNone = showNone;
        this.mSystemOnly = systemOnly;
        this.mVisible = visible;
        this.mRequiredComponents = requiredComponents;
        this.mPermissions = permissions;
        this.mAppOpPermissions = appOpPermissions;
        this.mAppOps = appOps;
        this.mPreferredActivities = preferredActivities;
    }

    public String getName() {
        return this.mName;
    }

    public RoleBehavior getBehavior() {
        return this.mBehavior;
    }

    public int getDescriptionResource() {
        return this.mDescriptionResource;
    }

    public boolean isExclusive() {
        return this.mExclusive;
    }

    public int getLabelResource() {
        return this.mLabelResource;
    }

    public int getRequestDescriptionResource() {
        return this.mRequestDescriptionResource;
    }

    public int getRequestTitleResource() {
        return this.mRequestTitleResource;
    }

    public boolean isRequestable() {
        return this.mRequestable;
    }

    public int getSearchKeywordsResource() {
        return this.mSearchKeywordsResource;
    }

    public int getShortLabelResource() {
        return this.mShortLabelResource;
    }

    public boolean shouldShowNone() {
        return this.mShowNone;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public List<RequiredComponent> getRequiredComponents() {
        return this.mRequiredComponents;
    }

    public List<String> getPermissions() {
        return this.mPermissions;
    }

    public List<String> getAppOpPermissions() {
        return this.mAppOpPermissions;
    }

    public List<AppOp> getAppOps() {
        return this.mAppOps;
    }

    public List<PreferredActivity> getPreferredActivities() {
        return this.mPreferredActivities;
    }

    public void onRoleAdded(Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.onRoleAdded(this, context);
        }
    }

    public boolean isAvailableAsUser(UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            return roleBehavior.isAvailableAsUser(this, user, context);
        }
        return true;
    }

    public boolean isAvailable(Context context) {
        return isAvailableAsUser(Process.myUserHandle(), context);
    }

    public List<String> getDefaultHolders(Context context) {
        if (this.mDefaultHoldersResourceName == null) {
            RoleBehavior roleBehavior = this.mBehavior;
            if (roleBehavior != null) {
                return roleBehavior.getDefaultHolders(this, context);
            }
            return Collections.emptyList();
        }
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(this.mDefaultHoldersResourceName, "string", "android");
        if (resourceId == 0) {
            Log.w(LOG_TAG, "Cannot find resource for default holder: " + this.mDefaultHoldersResourceName);
            return Collections.emptyList();
        }
        try {
            String resourceValue = resources.getString(resourceId);
            if (TextUtils.isEmpty(resourceValue)) {
                return Collections.emptyList();
            }
            if (isExclusive()) {
                if (!isDefaultHolderQualified(resourceValue, context)) {
                    return Collections.emptyList();
                }
                return Collections.singletonList(resourceValue);
            }
            String[] resourcePackageNames = resourceValue.split(PACKAGE_NAME_SEPARATOR);
            List<String> packageNames = new ArrayList<>();
            for (String packageName : resourcePackageNames) {
                if (isDefaultHolderQualified(packageName, context)) {
                    packageNames.add(packageName);
                }
            }
            return packageNames;
        } catch (Resources.NotFoundException e) {
            Log.w(LOG_TAG, "Cannot get resource for default holder: " + this.mDefaultHoldersResourceName, e);
            return Collections.emptyList();
        }
    }

    private boolean isDefaultHolderQualified(String packageName, Context context) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Cannot get ApplicationInfo for default holder: " + packageName);
            return false;
        }
        if ((applicationInfo.flags & 1) != 0) {
            return true;
        }
        Log.w(LOG_TAG, "Default holder is not a system app: " + packageName);
        return false;
    }

    public String getFallbackHolder(Context context) {
        if (isNoneHolderSelected(context)) {
            return null;
        }
        if (this.mFallBackToDefaultHolder) {
            return (String) CollectionUtils.firstOrNull(getDefaultHolders(context));
        }
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            return roleBehavior.getFallbackHolder(this, context);
        }
        return null;
    }

    public boolean isVisibleAsUser(UserHandle user, Context context) {
        RoleBehavior roleBehavior;
        return this.mVisible && ((roleBehavior = this.mBehavior) == null || roleBehavior.isVisibleAsUser(this, user, context));
    }

    public boolean isVisible(Context context) {
        return isVisibleAsUser(Process.myUserHandle(), context);
    }

    public Intent getManageIntentAsUser(UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            return roleBehavior.getManageIntentAsUser(this, user, context);
        }
        return null;
    }

    public void preparePreferenceAsUser(TwoTargetPreference preference, UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.preparePreferenceAsUser(this, preference, user, context);
        }
    }

    public boolean isApplicationVisibleAsUser(ApplicationInfo applicationInfo, UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            return roleBehavior.isApplicationVisibleAsUser(this, applicationInfo, user, context);
        }
        return true;
    }

    public void prepareApplicationPreferenceAsUser(Preference preference, ApplicationInfo applicationInfo, UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.prepareApplicationPreferenceAsUser(this, preference, applicationInfo, user, context);
        }
    }

    public CharSequence getConfirmationMessage(String packageName, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            return roleBehavior.getConfirmationMessage(this, packageName, context);
        }
        return null;
    }

    public boolean isPackageQualified(String packageName, Context context) {
        Boolean isPackageQualified;
        if (!isPackageMinimallyQualifiedAsUser(packageName, Process.myUserHandle(), context)) {
            return false;
        }
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null && (isPackageQualified = roleBehavior.isPackageQualified(this, packageName, context)) != null) {
            return isPackageQualified.booleanValue();
        }
        int requiredComponentsSize = this.mRequiredComponents.size();
        for (int i = 0; i < requiredComponentsSize; i++) {
            RequiredComponent requiredComponent = this.mRequiredComponents.get(i);
            if (requiredComponent.getQualifyingComponentForPackage(packageName, context) == null) {
                Log.w(LOG_TAG, packageName + " not qualified for " + this.mName + " due to missing " + requiredComponent);
                return false;
            }
        }
        return true;
    }

    public List<String> getQualifyingPackagesAsUser(UserHandle user, Context context) {
        List<String> qualifyingPackages = null;
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            qualifyingPackages = roleBehavior.getQualifyingPackagesAsUser(this, user, context);
        }
        if (qualifyingPackages == null) {
            ArrayMap<String, Integer> packageComponentCountMap = new ArrayMap<>();
            int requiredComponentsSize = this.mRequiredComponents.size();
            for (int requiredComponentsIndex = 0; requiredComponentsIndex < requiredComponentsSize; requiredComponentsIndex++) {
                RequiredComponent requiredComponent = this.mRequiredComponents.get(requiredComponentsIndex);
                List<ComponentName> qualifyingComponents = requiredComponent.getQualifyingComponentsAsUser(user, context);
                int qualifyingComponentsSize = qualifyingComponents.size();
                for (int qualifyingComponentsIndex = 0; qualifyingComponentsIndex < qualifyingComponentsSize; qualifyingComponentsIndex++) {
                    ComponentName componentName = qualifyingComponents.get(qualifyingComponentsIndex);
                    String packageName = componentName.getPackageName();
                    Integer componentCount = packageComponentCountMap.get(packageName);
                    int iIntValue = 1;
                    if (componentCount != null) {
                        iIntValue = 1 + componentCount.intValue();
                    }
                    packageComponentCountMap.put(packageName, Integer.valueOf(iIntValue));
                }
            }
            qualifyingPackages = new ArrayList();
            int packageComponentCountMapSize = packageComponentCountMap.size();
            for (int i = 0; i < packageComponentCountMapSize; i++) {
                if (packageComponentCountMap.valueAt(i).intValue() == requiredComponentsSize) {
                    String packageName2 = packageComponentCountMap.keyAt(i);
                    qualifyingPackages.add(packageName2);
                }
            }
        }
        int qualifyingPackagesSize = qualifyingPackages.size();
        int i2 = 0;
        while (i2 < qualifyingPackagesSize) {
            String packageName3 = qualifyingPackages.get(i2);
            if (!isPackageMinimallyQualifiedAsUser(packageName3, user, context)) {
                qualifyingPackages.remove(i2);
                qualifyingPackagesSize--;
            } else {
                i2++;
            }
        }
        return qualifyingPackages;
    }

    private boolean isPackageMinimallyQualifiedAsUser(String packageName, UserHandle user, Context context) {
        if (Objects.equals(packageName, "android")) {
            return false;
        }
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfoAsUser(packageName, user, context);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Cannot get ApplicationInfo for package: " + packageName + ", user: " + user.getIdentifier());
            return false;
        }
        if ((this.mSystemOnly && (applicationInfo.flags & 1) == 0) || !applicationInfo.enabled || applicationInfo.isInstantApp()) {
            return false;
        }
        PackageManager userPackageManager = UserUtils.getUserContext(context, user).getPackageManager();
        return userPackageManager.getDeclaredSharedLibraries(packageName, 0).isEmpty();
    }

    public void grant(String packageName, boolean dontKillApp, boolean overrideUserSetAndFixedPermissions, Context context) {
        boolean permissionOrAppOpChanged = Permissions.grant(packageName, this.mPermissions, true, overrideUserSetAndFixedPermissions, true, false, false, context);
        int appOpPermissionsSize = this.mAppOpPermissions.size();
        for (int i = 0; i < appOpPermissionsSize; i++) {
            String appOpPermissions = this.mAppOpPermissions.get(i);
            AppOpPermissions.grant(packageName, appOpPermissions, context);
        }
        int appOpsSize = this.mAppOps.size();
        for (int i2 = 0; i2 < appOpsSize; i2++) {
            AppOp appOp = this.mAppOps.get(i2);
            appOp.grant(packageName, context);
        }
        int preferredActivitiesSize = this.mPreferredActivities.size();
        for (int i3 = 0; i3 < preferredActivitiesSize; i3++) {
            PreferredActivity preferredActivity = this.mPreferredActivities.get(i3);
            preferredActivity.configure(packageName, context);
        }
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.grant(this, packageName, context);
        }
        if (!dontKillApp && permissionOrAppOpChanged && !Permissions.isRuntimePermissionsSupported(packageName, context)) {
            killApp(packageName, context);
        }
    }

    public void revoke(String packageName, boolean dontKillApp, boolean overrideSystemFixedPermissions, Context context) {
        RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
        List<String> otherRoleNames = roleManager.getHeldRolesFromController(packageName);
        otherRoleNames.remove(this.mName);
        List<String> permissionsToRevoke = new ArrayList<>(this.mPermissions);
        ArrayMap<String, Role> roles = Roles.get(context);
        int otherRoleNamesSize = otherRoleNames.size();
        for (int i = 0; i < otherRoleNamesSize; i++) {
            String roleName = otherRoleNames.get(i);
            Role role = roles.get(roleName);
            permissionsToRevoke.removeAll(role.mPermissions);
        }
        boolean permissionOrAppOpChanged = Permissions.revoke(packageName, permissionsToRevoke, true, false, overrideSystemFixedPermissions, context);
        List<String> appOpPermissionsToRevoke = new ArrayList<>(this.mAppOpPermissions);
        for (int i2 = 0; i2 < otherRoleNamesSize; i2++) {
            String roleName2 = otherRoleNames.get(i2);
            Role role2 = roles.get(roleName2);
            appOpPermissionsToRevoke.removeAll(role2.mAppOpPermissions);
        }
        int appOpPermissionsSize = appOpPermissionsToRevoke.size();
        for (int i3 = 0; i3 < appOpPermissionsSize; i3++) {
            String appOpPermission = appOpPermissionsToRevoke.get(i3);
            AppOpPermissions.revoke(packageName, appOpPermission, context);
        }
        List<AppOp> appOpsToRevoke = new ArrayList<>(this.mAppOps);
        for (int i4 = 0; i4 < otherRoleNamesSize; i4++) {
            String roleName3 = otherRoleNames.get(i4);
            Role role3 = roles.get(roleName3);
            appOpsToRevoke.removeAll(role3.mAppOps);
        }
        int appOpsSize = appOpsToRevoke.size();
        for (int i5 = 0; i5 < appOpsSize; i5++) {
            AppOp appOp = appOpsToRevoke.get(i5);
            appOp.revoke(packageName, context);
        }
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.revoke(this, packageName, context);
        }
        if (!dontKillApp && permissionOrAppOpChanged) {
            killApp(packageName, context);
        }
    }

    private void killApp(String packageName, Context context) {
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Cannot get ApplicationInfo for package: " + packageName);
            return;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
        activityManager.killUid(applicationInfo.uid, "Permission or app op changed");
    }

    private boolean isNoneHolderSelected(Context context) {
        return Utils.getDeviceProtectedSharedPreferences(context).getBoolean(Constants.IS_NONE_ROLE_HOLDER_SELECTED_KEY + this.mName, false);
    }

    public void onHolderAddedAsUser(String packageName, UserHandle user, Context context) {
        Utils.getDeviceProtectedSharedPreferences(UserUtils.getUserContext(context, user)).edit().remove(Constants.IS_NONE_ROLE_HOLDER_SELECTED_KEY + this.mName).apply();
    }

    public void onHolderSelectedAsUser(String packageName, UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.onHolderSelectedAsUser(this, packageName, user, context);
        }
    }

    public void onHolderChangedAsUser(UserHandle user, Context context) {
        RoleBehavior roleBehavior = this.mBehavior;
        if (roleBehavior != null) {
            roleBehavior.onHolderChangedAsUser(this, user, context);
        }
    }

    public void onNoneHolderSelectedAsUser(UserHandle user, Context context) {
        Utils.getDeviceProtectedSharedPreferences(UserUtils.getUserContext(context, user)).edit().putBoolean(Constants.IS_NONE_ROLE_HOLDER_SELECTED_KEY + this.mName, true).apply();
    }

    public String toString() {
        return "Role{mName='" + this.mName + "', mBehavior=" + this.mBehavior + ", mDefaultHoldersResourceName=" + this.mDefaultHoldersResourceName + ", mDescriptionResource=" + this.mDescriptionResource + ", mExclusive=" + this.mExclusive + ", mFallBackToDefaultHolder=" + this.mFallBackToDefaultHolder + ", mLabelResource=" + this.mLabelResource + ", mRequestDescriptionResource=" + this.mRequestDescriptionResource + ", mRequestTitleResource=" + this.mRequestTitleResource + ", mRequestable=" + this.mRequestable + ", mSearchKeywordsResource=" + this.mSearchKeywordsResource + ", mShortLabelResource=" + this.mShortLabelResource + ", mShowNone=" + this.mShowNone + ", mSystemOnly=" + this.mSystemOnly + ", mVisible=" + this.mVisible + ", mRequiredComponents=" + this.mRequiredComponents + ", mPermissions=" + this.mPermissions + ", mAppOpPermissions=" + this.mAppOpPermissions + ", mAppOps=" + this.mAppOps + ", mPreferredActivities=" + this.mPreferredActivities + '}';
    }
}
