package com.android.permissioncontroller.permission.model;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.util.ArrayMap;
import android.util.Log;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.service.LocationAccessCheck;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.SoftRestrictedPermissionPolicy;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes8.dex */
public final class AppPermissionGroup implements Comparable<AppPermissionGroup> {
    private static final String KILL_REASON_APP_OP_CHANGE = "Permission related app op changed";
    private static final String LOG_TAG = AppPermissionGroup.class.getSimpleName();
    private static final int ONE_TIME_PACKAGE_IMPORTANCE_LEVEL_TO_KEEP_SESSION_ALIVE = 125;
    private static final int ONE_TIME_PACKAGE_IMPORTANCE_LEVEL_TO_RESET_TIMER = 100;
    private static final String PLATFORM_PACKAGE_NAME = "android";
    private final ActivityManager mActivityManager;
    private final AppOpsManager mAppOps;
    private final boolean mAppSupportsRuntimePermissions;
    private AppPermissionGroup mBackgroundPermissions;
    private final int mBackgroundRequest;
    private final int mBackgroundRequestDetail;
    private final Collator mCollator;
    private boolean mContainsEphemeralPermission;
    private boolean mContainsPreRuntimePermission;
    private final Context mContext;
    private final String mDeclaringPackage;
    private final boolean mDelayChanges;
    private final CharSequence mDescription;
    private final CharSequence mFullLabel;
    private boolean mHasPermissionWithBackgroundMode;
    private final String mIconPkg;
    private final int mIconResId;
    private final boolean mIsEphemeralApp;
    private final boolean mIsNonIsolatedStorage;
    private final CharSequence mLabel;
    private final String mName;
    private final PackageInfo mPackageInfo;
    private final PackageManager mPackageManager;
    private final ArrayMap<String, Permission> mPermissions = new ArrayMap<>();
    private final int mRequest;
    private final int mRequestDetail;
    private boolean mTriggerLocationAccessCheckOnPersist;
    private final int mUpgradeRequest;
    private final int mUpgradeRequestDetail;
    private final UserHandle mUserHandle;

    public static AppPermissionGroup create(Context context, PackageInfo packageInfo, String permissionName, boolean delayChanges) throws PackageManager.NameNotFoundException {
        try {
            PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo(permissionName, 0);
            if ((permissionInfo.protectionLevel & 15) != 1 || (permissionInfo.flags & BasicMeasure.EXACTLY) == 0 || (permissionInfo.flags & 2) != 0) {
                return null;
            }
            String group = Utils.getGroupOfPermission(permissionInfo);
            PackageItemInfo groupInfo = permissionInfo;
            if (group != null) {
                try {
                    groupInfo = context.getPackageManager().getPermissionGroupInfo(group, 0);
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
            List<PermissionInfo> permissionInfos = null;
            if (groupInfo instanceof PermissionGroupInfo) {
                try {
                    permissionInfos = Utils.getPermissionInfosForGroup(context.getPackageManager(), groupInfo.name);
                } catch (PackageManager.NameNotFoundException e2) {
                }
            }
            return create(context, packageInfo, groupInfo, permissionInfos, delayChanges);
        } catch (PackageManager.NameNotFoundException e3) {
            return null;
        }
    }

    public static AppPermissionGroup create(Application app, String packageName, String permissionGroupName, UserHandle user, boolean delayChanges) {
        try {
            PackageInfo packageInfo = Utils.getUserContext(app, user).getPackageManager().getPackageInfo(packageName, 4096);
            PackageItemInfo groupInfo = Utils.getGroupInfo(permissionGroupName, app);
            if (groupInfo == null) {
                return null;
            }
            List<PermissionInfo> permissionInfos = null;
            if (groupInfo instanceof PermissionGroupInfo) {
                permissionInfos = Utils.getPermissionInfosForGroup(app.getPackageManager(), groupInfo.name);
            }
            return create(app, packageInfo, groupInfo, permissionInfos, delayChanges);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static AppPermissionGroup create(Context context, PackageInfo packageInfo, PackageItemInfo groupInfo, List<PermissionInfo> permissionInfos, boolean delayChanges) {
        PackageManager packageManager = context.getPackageManager();
        CharSequence groupLabel = groupInfo.loadLabel(packageManager);
        CharSequence fullGroupLabel = groupInfo.loadSafeLabel(packageManager, 0.0f, 5);
        return create(context, packageInfo, groupInfo, permissionInfos, groupLabel, fullGroupLabel, delayChanges);
    }

    public static AppPermissionGroup create(Context context, PackageInfo packageInfo, PackageItemInfo groupInfo, List<PermissionInfo> permissionInfos, CharSequence groupLabel, CharSequence fullGroupLabel, boolean delayChanges) {
        List<PermissionInfo> permissionInfos2;
        Permission backgroundPermission;
        PermissionInfo requestedPermissionInfo;
        String strPermissionToOp;
        AppOpsManager appOpsManager;
        boolean appOpAllowed;
        UserHandle userHandle;
        PackageManager packageManager;
        AppOpsManager appOpsManager2;
        int permissionCount;
        PackageManager packageManager2 = context.getPackageManager();
        UserHandle userHandle2 = UserHandle.getUserHandleForUid(packageInfo.applicationInfo.uid);
        if (!(groupInfo instanceof PermissionInfo)) {
            permissionInfos2 = permissionInfos;
        } else {
            List<PermissionInfo> permissionInfos3 = new ArrayList<>();
            permissionInfos3.add((PermissionInfo) groupInfo);
            permissionInfos2 = permissionInfos3;
        }
        if (permissionInfos2 != null && !permissionInfos2.isEmpty()) {
            AppOpsManager appOpsManager3 = (AppOpsManager) context.getSystemService(AppOpsManager.class);
            AppOpsManager appOpsManager4 = appOpsManager3;
            UserHandle userHandle3 = userHandle2;
            PackageManager packageManager3 = packageManager2;
            AppPermissionGroup group = new AppPermissionGroup(context, packageInfo, groupInfo.name, groupInfo.packageName, groupLabel, fullGroupLabel, loadGroupDescription(context, groupInfo, packageManager2), getRequest(groupInfo), getRequestDetail(groupInfo), getBackgroundRequest(groupInfo), getBackgroundRequestDetail(groupInfo), getUpgradeRequest(groupInfo), getUpgradeRequestDetail(groupInfo), groupInfo.packageName, groupInfo.icon, userHandle3, delayChanges, appOpsManager3);
            Set<String> whitelistedRestrictedPermissions = context.getPackageManager().getWhitelistedRestrictedPermissions(packageInfo.packageName, 7);
            ArrayMap<String, Permission> allPermissions = new ArrayMap<>();
            int permissionCount2 = packageInfo.requestedPermissions == null ? 0 : packageInfo.requestedPermissions.length;
            String packageName = packageInfo.packageName;
            int i = 0;
            while (i < permissionCount2) {
                String requestedPermission = packageInfo.requestedPermissions[i];
                Iterator<PermissionInfo> it = permissionInfos2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        requestedPermissionInfo = null;
                        break;
                    }
                    PermissionInfo permissionInfo = it.next();
                    if (requestedPermission.equals(permissionInfo.name)) {
                        requestedPermissionInfo = permissionInfo;
                        break;
                    }
                }
                if (requestedPermissionInfo == null) {
                    userHandle = userHandle3;
                    permissionCount = permissionCount2;
                    PackageManager packageManager4 = packageManager3;
                    appOpsManager2 = appOpsManager4;
                    packageManager = packageManager4;
                } else if ((requestedPermissionInfo.protectionLevel & 15) != 1) {
                    userHandle = userHandle3;
                    permissionCount = permissionCount2;
                    PackageManager packageManager5 = packageManager3;
                    appOpsManager2 = appOpsManager4;
                    packageManager = packageManager5;
                } else if (packageInfo.applicationInfo.targetSdkVersion <= 22 && !"android".equals(groupInfo.packageName)) {
                    userHandle = userHandle3;
                    permissionCount = permissionCount2;
                    PackageManager packageManager6 = packageManager3;
                    appOpsManager2 = appOpsManager4;
                    packageManager = packageManager6;
                } else {
                    boolean granted = (packageInfo.requestedPermissionsFlags[i] & 2) != 0;
                    if (!"android".equals(requestedPermissionInfo.packageName)) {
                        strPermissionToOp = null;
                    } else {
                        strPermissionToOp = AppOpsManager.permissionToOp(requestedPermissionInfo.name);
                    }
                    String appOp = strPermissionToOp;
                    if (appOp == null) {
                        appOpAllowed = false;
                        appOpsManager = appOpsManager4;
                    } else {
                        appOpsManager = appOpsManager4;
                        int appOpsMode = appOpsManager.unsafeCheckOpRaw(appOp, packageInfo.applicationInfo.uid, packageName);
                        appOpAllowed = appOpsMode == 0 || appOpsMode == 4;
                    }
                    userHandle = userHandle3;
                    PackageManager packageManager7 = packageManager3;
                    int flags = packageManager7.getPermissionFlags(requestedPermission, packageName, userHandle);
                    packageManager = packageManager7;
                    appOpsManager2 = appOpsManager;
                    permissionCount = permissionCount2;
                    PermissionInfo requestedPermissionInfo2 = requestedPermissionInfo;
                    Permission permission = new Permission(requestedPermission, requestedPermissionInfo, granted, appOp, appOpAllowed, flags);
                    if (requestedPermissionInfo2.backgroundPermission != null) {
                        group.mHasPermissionWithBackgroundMode = true;
                    }
                    allPermissions.put(requestedPermission, permission);
                }
                i++;
                userHandle3 = userHandle;
                permissionCount2 = permissionCount;
                AppOpsManager appOpsManager5 = appOpsManager2;
                packageManager3 = packageManager;
                appOpsManager4 = appOpsManager5;
            }
            AppOpsManager appOpsManager6 = appOpsManager4;
            int numPermissions = allPermissions.size();
            if (numPermissions == 0) {
                return null;
            }
            for (int i2 = 0; i2 < allPermissions.size(); i2++) {
                Permission permission2 = allPermissions.valueAt(i2);
                if (permission2.getBackgroundPermissionName() != null && (backgroundPermission = allPermissions.get(permission2.getBackgroundPermissionName())) != null) {
                    backgroundPermission.addForegroundPermissions(permission2);
                    permission2.setBackgroundPermission(backgroundPermission);
                    if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).unsafeCheckOpRaw(permission2.getAppOp(), packageInfo.applicationInfo.uid, packageInfo.packageName) == 0) {
                        backgroundPermission.setAppOpAllowed(true);
                    }
                }
            }
            for (int i3 = 0; i3 < numPermissions; i3++) {
                Permission permission3 = allPermissions.valueAt(i3);
                if (permission3.isBackgroundPermission()) {
                    if (group.getBackgroundPermissions() == null) {
                        group.mBackgroundPermissions = new AppPermissionGroup(group.mContext, group.getApp(), group.getName(), group.getDeclaringPackage(), group.getLabel(), group.getFullLabel(), group.getDescription(), group.getRequest(), group.getRequestDetail(), group.getBackgroundRequest(), group.getBackgroundRequestDetail(), group.getUpgradeRequest(), group.getUpgradeRequestDetail(), group.getIconPkg(), group.getIconResId(), group.getUser(), delayChanges, appOpsManager6);
                    }
                    group.getBackgroundPermissions().addPermission(permission3);
                } else if ((!permission3.isHardRestricted() || whitelistedRestrictedPermissions.contains(permission3.getName())) && (!permission3.isSoftRestricted() || SoftRestrictedPermissionPolicy.shouldShow(packageInfo, permission3))) {
                    group.addPermission(permission3);
                }
            }
            if (group.getPermissions().isEmpty()) {
                return null;
            }
            return group;
        }
        return null;
    }

    private static int getRequest(PackageItemInfo group) {
        return Utils.getRequest(group.name);
    }

    private static CharSequence loadGroupDescription(Context context, PackageItemInfo group, PackageManager packageManager) {
        CharSequence description = null;
        if (group instanceof PermissionGroupInfo) {
            description = ((PermissionGroupInfo) group).loadDescription(packageManager);
        } else if (group instanceof PermissionInfo) {
            description = ((PermissionInfo) group).loadDescription(packageManager);
        }
        if (description == null || description.length() <= 0) {
            CharSequence description2 = context.getString(R.string.default_permission_description);
            return description2;
        }
        return description;
    }

    private AppPermissionGroup(Context context, PackageInfo packageInfo, String name, String declaringPackage, CharSequence label, CharSequence fullLabel, CharSequence description, int request, int requestDetail, int backgroundRequest, int backgroundRequestDetail, int upgradeRequest, int upgradeRequestDetail, String iconPkg, int iconResId, UserHandle userHandle, boolean delayChanges, AppOpsManager appOpsManager) {
        int targetSDK = packageInfo.applicationInfo.targetSdkVersion;
        this.mContext = context;
        this.mUserHandle = userHandle;
        this.mPackageManager = context.getPackageManager();
        this.mPackageInfo = packageInfo;
        this.mAppSupportsRuntimePermissions = targetSDK > 22;
        this.mIsEphemeralApp = packageInfo.applicationInfo.isInstantApp();
        this.mAppOps = appOpsManager;
        this.mActivityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
        this.mDeclaringPackage = declaringPackage;
        this.mName = name;
        this.mLabel = label;
        this.mFullLabel = fullLabel;
        this.mDescription = description;
        this.mCollator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        this.mRequest = request;
        this.mRequestDetail = requestDetail;
        this.mBackgroundRequest = backgroundRequest;
        this.mBackgroundRequestDetail = backgroundRequestDetail;
        this.mUpgradeRequest = upgradeRequest;
        this.mUpgradeRequestDetail = upgradeRequestDetail;
        this.mDelayChanges = delayChanges;
        if (iconResId != 0) {
            this.mIconPkg = iconPkg;
            this.mIconResId = iconResId;
        } else {
            this.mIconPkg = context.getPackageName();
            this.mIconResId = R.drawable.ic_perm_device_info;
        }
        this.mIsNonIsolatedStorage = targetSDK < 28 || (targetSDK < 30 && this.mAppOps.unsafeCheckOpNoThrow("android:legacy_storage", packageInfo.applicationInfo.uid, packageInfo.packageName) == 0);
    }

    public boolean doesSupportRuntimePermissions() {
        return this.mAppSupportsRuntimePermissions;
    }

    public boolean isGrantingAllowed() {
        return (!this.mIsEphemeralApp || this.mContainsEphemeralPermission) && (this.mAppSupportsRuntimePermissions || this.mContainsPreRuntimePermission);
    }

    public boolean isReviewRequired() {
        if (this.mAppSupportsRuntimePermissions) {
            return false;
        }
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isReviewRequired()) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserSensitive() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isUserSensitive()) {
                return true;
            }
        }
        return false;
    }

    public void unsetReviewRequired() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isReviewRequired()) {
                permission.unsetReviewRequired();
            }
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
        }
    }

    public boolean hasGrantedByDefaultPermission() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isGrantedByDefault()) {
                return true;
            }
        }
        return false;
    }

    public PackageInfo getApp() {
        return this.mPackageInfo;
    }

    public String getName() {
        return this.mName;
    }

    public String getDeclaringPackage() {
        return this.mDeclaringPackage;
    }

    public String getIconPkg() {
        return this.mIconPkg;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public CharSequence getFullLabel() {
        return this.mFullLabel;
    }

    public int getRequest() {
        return this.mRequest;
    }

    private static int getRequestDetail(PackageItemInfo info) {
        return Utils.getRequestDetail(info.name);
    }

    public int getRequestDetail() {
        return this.mRequestDetail;
    }

    private static int getBackgroundRequest(PackageItemInfo info) {
        return Utils.getBackgroundRequest(info.name);
    }

    public int getBackgroundRequest() {
        return this.mBackgroundRequest;
    }

    private static int getBackgroundRequestDetail(PackageItemInfo info) {
        return Utils.getBackgroundRequestDetail(info.name);
    }

    public int getBackgroundRequestDetail() {
        return this.mBackgroundRequestDetail;
    }

    private static int getUpgradeRequest(PackageItemInfo info) {
        return Utils.getUpgradeRequest(info.name);
    }

    public int getUpgradeRequest() {
        return this.mUpgradeRequest;
    }

    private static int getUpgradeRequestDetail(PackageItemInfo info) {
        return Utils.getUpgradeRequestDetail(info.name);
    }

    public int getUpgradeRequestDetail() {
        return this.mUpgradeRequestDetail;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public UserHandle getUser() {
        return this.mUserHandle;
    }

    public boolean hasPermission(String permission) {
        return this.mPermissions.get(permission) != null;
    }

    public Permission getPermission(String permissionName) {
        return this.mPermissions.get(permissionName);
    }

    public boolean areRuntimePermissionsGranted() {
        return areRuntimePermissionsGranted(null);
    }

    public boolean areRuntimePermissionsGranted(String[] filterPermissions) {
        if (LocationUtils.isLocationGroupAndProvider(this.mContext, this.mName, this.mPackageInfo.packageName)) {
            return LocationUtils.isLocationEnabled(this.mContext);
        }
        if (LocationUtils.isLocationGroupAndControllerExtraPackage(this.mContext, this.mName, this.mPackageInfo.packageName)) {
            return LocationUtils.isExtraLocationControllerPackageEnabled(this.mContext);
        }
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if ((filterPermissions == null || ArrayUtils.contains(filterPermissions, permission.getName())) && permission.isGrantedIncludingAppOp()) {
                return true;
            }
        }
        return false;
    }

    public boolean grantRuntimePermissions(boolean setByTheUser, boolean fixedByTheUser) {
        return grantRuntimePermissions(setByTheUser, fixedByTheUser, null);
    }

    private boolean setAppOpMode(String op, int uid, int mode) {
        int currentMode = this.mAppOps.unsafeCheckOpRaw(op, uid, this.mPackageInfo.packageName);
        if (currentMode == mode) {
            return false;
        }
        this.mAppOps.setUidMode(op, uid, mode);
        return true;
    }

    private boolean allowAppOp(Permission permission, int uid) {
        boolean wasChanged = false;
        if (permission.isBackgroundPermission()) {
            ArrayList<Permission> foregroundPermissions = permission.getForegroundPermissions();
            int numForegroundPermissions = foregroundPermissions.size();
            for (int i = 0; i < numForegroundPermissions; i++) {
                Permission foregroundPermission = foregroundPermissions.get(i);
                if (foregroundPermission.isAppOpAllowed()) {
                    wasChanged |= setAppOpMode(foregroundPermission.getAppOp(), uid, 0);
                }
            }
            return wasChanged;
        }
        if (permission.hasBackgroundPermission()) {
            Permission backgroundPermission = permission.getBackgroundPermission();
            if (backgroundPermission == null) {
                boolean wasChanged2 = setAppOpMode(permission.getAppOp(), uid, 4);
                return wasChanged2;
            }
            if (!backgroundPermission.isAppOpAllowed()) {
                boolean wasChanged3 = setAppOpMode(permission.getAppOp(), uid, 4);
                return wasChanged3;
            }
            boolean wasChanged4 = setAppOpMode(permission.getAppOp(), uid, 0);
            return wasChanged4;
        }
        boolean wasChanged5 = setAppOpMode(permission.getAppOp(), uid, 0);
        return wasChanged5;
    }

    private void killApp(String reason) {
        this.mActivityManager.killUid(this.mPackageInfo.applicationInfo.uid, reason);
    }

    public boolean grantRuntimePermissions(boolean setByTheUser, boolean fixedByTheUser, String[] filterPermissions) {
        ArrayList<Permission> fgPerms;
        boolean killApp = false;
        boolean wasAllGranted = true;
        Iterator<Permission> it = this.mPermissions.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Permission permission = it.next();
            if (filterPermissions == null || ArrayUtils.contains(filterPermissions, permission.getName())) {
                if (permission.isGrantingAllowed(this.mIsEphemeralApp, this.mAppSupportsRuntimePermissions)) {
                    boolean wasGranted = permission.isGrantedIncludingAppOp();
                    if (this.mAppSupportsRuntimePermissions) {
                        if (permission.isSystemFixed()) {
                            wasAllGranted = false;
                            break;
                        }
                        if (permission.affectsAppOp() && !permission.isAppOpAllowed()) {
                            permission.setAppOpAllowed(true);
                        }
                        if (!permission.isGranted()) {
                            permission.setGranted(true);
                        }
                        if (!fixedByTheUser) {
                            if (permission.isUserFixed()) {
                                permission.setUserFixed(false);
                            }
                            if (setByTheUser && !permission.isUserSet()) {
                                permission.setUserSet(true);
                            }
                        } else {
                            if (!permission.isUserFixed()) {
                                permission.setUserFixed(true);
                            }
                            if (permission.isUserSet()) {
                                permission.setUserSet(false);
                            }
                        }
                    } else if (permission.isGranted()) {
                        if (permission.affectsAppOp()) {
                            if (!permission.isAppOpAllowed()) {
                                permission.setAppOpAllowed(true);
                                killApp = true;
                            }
                            if (permission.isRevokedCompat()) {
                                permission.setRevokedCompat(false);
                            }
                        }
                        if (permission.isReviewRequired()) {
                            permission.unsetReviewRequired();
                        }
                    }
                    if (!wasGranted && permission.isGrantedIncludingAppOp()) {
                        if (permission.getName().equals("android.permission.ACCESS_FINE_LOCATION")) {
                            Permission bgPerm = permission.getBackgroundPermission();
                            if (bgPerm != null && bgPerm.isGrantedIncludingAppOp()) {
                                this.mTriggerLocationAccessCheckOnPersist = true;
                            }
                        } else if (permission.getName().equals("android.permission.ACCESS_BACKGROUND_LOCATION") && (fgPerms = permission.getForegroundPermissions()) != null) {
                            int numFgPerms = fgPerms.size();
                            int fgPermNum = 0;
                            while (true) {
                                if (fgPermNum < numFgPerms) {
                                    Permission fgPerm = fgPerms.get(fgPermNum);
                                    if (!fgPerm.getName().equals("android.permission.ACCESS_FINE_LOCATION")) {
                                        fgPermNum++;
                                    } else if (fgPerm.isGrantedIncludingAppOp()) {
                                        this.mTriggerLocationAccessCheckOnPersist = true;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
            if (killApp) {
                killApp(KILL_REASON_APP_OP_CHANGE);
            }
        }
        return wasAllGranted;
    }

    public boolean revokeRuntimePermissions(boolean fixedByTheUser) {
        return revokeRuntimePermissions(fixedByTheUser, null);
    }

    private boolean disallowAppOp(Permission permission, int uid) {
        boolean wasChanged = false;
        if (permission.isBackgroundPermission()) {
            ArrayList<Permission> foregroundPermissions = permission.getForegroundPermissions();
            int numForegroundPermissions = foregroundPermissions.size();
            for (int i = 0; i < numForegroundPermissions; i++) {
                Permission foregroundPermission = foregroundPermissions.get(i);
                if (foregroundPermission.isAppOpAllowed()) {
                    wasChanged |= setAppOpMode(foregroundPermission.getAppOp(), uid, 4);
                }
            }
            return wasChanged;
        }
        boolean wasChanged2 = setAppOpMode(permission.getAppOp(), uid, 1);
        return wasChanged2;
    }

    public boolean revokeRuntimePermissions(boolean fixedByTheUser, String[] filterPermissions) {
        boolean killApp = false;
        boolean wasAllRevoked = true;
        Iterator<Permission> it = this.mPermissions.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Permission permission = it.next();
            if (filterPermissions == null || ArrayUtils.contains(filterPermissions, permission.getName())) {
                if (permission.isSystemFixed()) {
                    wasAllRevoked = false;
                    break;
                }
                if (this.mAppSupportsRuntimePermissions) {
                    if (permission.isGranted()) {
                        permission.setGranted(false);
                    }
                    if (fixedByTheUser) {
                        if (permission.isUserSet() || !permission.isUserFixed()) {
                            permission.setUserSet(false);
                            permission.setUserFixed(true);
                        }
                    } else if (!permission.isUserSet() || permission.isUserFixed()) {
                        permission.setUserSet(true);
                        permission.setUserFixed(false);
                    }
                    if (permission.affectsAppOp()) {
                        permission.setAppOpAllowed(false);
                    }
                } else if (permission.isGranted() && permission.affectsAppOp()) {
                    if (permission.isAppOpAllowed()) {
                        permission.setAppOpAllowed(false);
                        killApp = true;
                    }
                    if (!permission.isRevokedCompat()) {
                        permission.setRevokedCompat(true);
                    }
                }
            }
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
            if (killApp) {
                killApp(KILL_REASON_APP_OP_CHANGE);
            }
        }
        return wasAllRevoked;
    }

    public void setPolicyFixed(String[] filterPermissions) {
        for (String permissionName : filterPermissions) {
            Permission permission = this.mPermissions.get(permissionName);
            if (permission != null) {
                permission.setPolicyFixed(true);
            }
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
        }
    }

    public void setUserFixed(boolean isUsedFixed) {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            permission.setUserFixed(isUsedFixed);
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
        }
    }

    public void setOneTime(boolean isOneTime) {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (!permission.isBackgroundPermission()) {
                permission.setOneTime(isOneTime);
            }
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
        }
    }

    public void setUserSet(boolean isUserSet) {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            permission.setUserSet(isUserSet);
        }
        if (!this.mDelayChanges) {
            persistChanges(false);
        }
    }

    public ArrayList<Permission> getPermissions() {
        return new ArrayList<>(this.mPermissions.values());
    }

    public AppPermissionGroup getBackgroundPermissions() {
        return this.mBackgroundPermissions;
    }

    public boolean hasPermissionWithBackgroundMode() {
        return this.mHasPermissionWithBackgroundMode;
    }

    public boolean isNonIsolatedStorage() {
        return this.mIsNonIsolatedStorage;
    }

    public boolean isBackgroundGroup() {
        return this.mPermissions.valueAt(0).isBackgroundPermission();
    }

    public boolean supportsOneTimeGrant() {
        return Utils.supportsOneTimeGrant(getName());
    }

    public int getFlags() {
        int flags = 0;
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            flags |= permission.getFlags();
        }
        return flags;
    }

    public boolean isUserFixed() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isUserFixed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPolicyFixed() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isPolicyFixed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserSet() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isUserSet()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSystemFixed() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isSystemFixed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isOneTime() {
        int permissionCount = this.mPermissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = this.mPermissions.valueAt(i);
            if (permission.isOneTime()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(AppPermissionGroup another) {
        int result = this.mCollator.compare(this.mLabel.toString(), another.mLabel.toString());
        if (result == 0) {
            return this.mPackageInfo.applicationInfo.uid - another.mPackageInfo.applicationInfo.uid;
        }
        return result;
    }

    public boolean equals(Object o) {
        if (!(o instanceof AppPermissionGroup)) {
            return false;
        }
        AppPermissionGroup other = (AppPermissionGroup) o;
        boolean equal = this.mName.equals(other.mName) && this.mPackageInfo.packageName.equals(other.mPackageInfo.packageName) && this.mUserHandle.equals(other.mUserHandle) && this.mPermissions.equals(other.mPermissions);
        if (!equal) {
            return false;
        }
        if (this.mBackgroundPermissions == null || other.getBackgroundPermissions() == null) {
            return this.mBackgroundPermissions == other.getBackgroundPermissions();
        }
        return this.mBackgroundPermissions.getPermissions().equals(other.getBackgroundPermissions().getPermissions());
    }

    public int hashCode() {
        ArrayList<Permission> backgroundPermissions = new ArrayList<>();
        AppPermissionGroup appPermissionGroup = this.mBackgroundPermissions;
        if (appPermissionGroup != null) {
            backgroundPermissions = appPermissionGroup.getPermissions();
        }
        return Objects.hash(this.mName, this.mPackageInfo.packageName, this.mUserHandle, this.mPermissions, backgroundPermissions);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append("{name=");
        builder.append(this.mName);
        if (this.mBackgroundPermissions != null) {
            builder.append(", <has background permissions>}");
        }
        if (!this.mPermissions.isEmpty()) {
            builder.append(", <has permissions>}");
        } else {
            builder.append('}');
        }
        return builder.toString();
    }

    private void addPermission(Permission permission) {
        this.mPermissions.put(permission.getName(), permission);
        if (permission.isEphemeral()) {
            this.mContainsEphemeralPermission = true;
        }
        if (!permission.isRuntimeOnly()) {
            this.mContainsPreRuntimePermission = true;
        }
    }

    public void persistChanges(boolean mayKillBecauseOfAppOpsChange) {
        persistChanges(mayKillBecauseOfAppOpsChange, null);
    }

    public void persistChanges(boolean z, String str) {
        int i = this.mPackageInfo.applicationInfo.uid;
        int size = this.mPermissions.size();
        boolean zDisallowAppOp = false;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            Permission permissionValueAt = this.mPermissions.valueAt(i2);
            if (!permissionValueAt.isSystemFixed()) {
                if (permissionValueAt.isGranted()) {
                    this.mPackageManager.grantRuntimePermission(this.mPackageInfo.packageName, permissionValueAt.getName(), this.mUserHandle);
                } else {
                    if (this.mContext.checkPermission(permissionValueAt.getName(), -1, i) == 0) {
                        if (str == null) {
                            this.mPackageManager.revokeRuntimePermission(this.mPackageInfo.packageName, permissionValueAt.getName(), this.mUserHandle);
                        } else {
                            this.mPackageManager.revokeRuntimePermission(this.mPackageInfo.packageName, permissionValueAt.getName(), this.mUserHandle, str);
                        }
                    }
                }
            }
            this.mPackageManager.updatePermissionFlags(permissionValueAt.getName(), this.mPackageInfo.packageName, (permissionValueAt.isReviewRequired() ? 0 : 64) | 15 | 65536 | 131072, (permissionValueAt.isUserSet() ? 1 : 0) | (permissionValueAt.isUserFixed() ? 2 : 0) | (permissionValueAt.isRevokedCompat() ? 8 : 0) | (permissionValueAt.isPolicyFixed() ? 4 : 0) | (permissionValueAt.isReviewRequired() ? 64 : 0) | (permissionValueAt.isOneTime() ? 65536 : 0), this.mUserHandle);
            if (permissionValueAt.affectsAppOp() && !permissionValueAt.isSystemFixed()) {
                if (permissionValueAt.isAppOpAllowed()) {
                    zDisallowAppOp |= allowAppOp(permissionValueAt, i);
                } else {
                    zDisallowAppOp |= disallowAppOp(permissionValueAt, i);
                }
            }
            i2++;
        }
        if (z && zDisallowAppOp) {
            killApp(KILL_REASON_APP_OP_CHANGE);
        }
        if (this.mTriggerLocationAccessCheckOnPersist) {
            new LocationAccessCheck(this.mContext, null).checkLocationAccessSoon();
            this.mTriggerLocationAccessCheckOnPersist = false;
        }
        String str2 = this.mPackageInfo.packageName;
        if (isOneTime() && areRuntimePermissionsGranted()) {
            ((PermissionManager) this.mContext.getSystemService(PermissionManager.class)).startOneTimePermissionSession(str2, Utils.getOneTimePermissionsTimeout(), 100, 125);
        } else if (!Utils.hasOneTimePermissions(this.mContext, str2)) {
            ((PermissionManager) this.mContext.getSystemService(PermissionManager.class)).stopOneTimePermissionSession(str2);
        }
    }

    public boolean hasInstallToRuntimeSplit() {
        PermissionManager permissionManager = (PermissionManager) this.mContext.getSystemService(PermissionManager.class);
        int numSplitPerms = permissionManager.getSplitPermissions().size();
        for (int splitPermNum = 0; splitPermNum < numSplitPerms; splitPermNum++) {
            PermissionManager.SplitPermissionInfo spi = (PermissionManager.SplitPermissionInfo) permissionManager.getSplitPermissions().get(splitPermNum);
            String splitPerm = spi.getSplitPermission();
            try {
                PermissionInfo pi = this.mPackageManager.getPermissionInfo(splitPerm, 0);
                if (pi.getProtection() != 0) {
                    continue;
                } else {
                    List<String> newPerms = spi.getNewPermissions();
                    int numNewPerms = newPerms.size();
                    for (int newPermNum = 0; newPermNum < numNewPerms; newPermNum++) {
                        String newPerm = newPerms.get(newPermNum);
                        if (hasPermission(newPerm)) {
                            try {
                                PermissionInfo pi2 = this.mPackageManager.getPermissionInfo(newPerm, 0);
                                if (pi2.getProtection() == 1 && this.mPackageInfo.applicationInfo.targetSdkVersion < spi.getTargetSdk()) {
                                    return true;
                                }
                            } catch (PackageManager.NameNotFoundException e) {
                                Log.w(LOG_TAG, "No such permission: " + newPerm, e);
                            }
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.w(LOG_TAG, "No such permission: " + splitPerm, e2);
            }
        }
        return false;
    }
}
