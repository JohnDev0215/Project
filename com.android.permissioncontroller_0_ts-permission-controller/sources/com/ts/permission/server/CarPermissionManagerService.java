package com.ts.permission.server;

import android.app.ActivityThread;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.UserInfo;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.permission.IPermissionManager;
import android.text.TextUtils;
import android.util.Log;
import com.ts.permission.ICarPermissionManager;
import com.ts.permission.bean.App;
import com.ts.permission.bean.Permission;
import com.ts.permission.bean.PermissionConfig;
import com.ts.permission.bean.Rule;
import com.ts.permission.manage.AppInfoManager;
import com.ts.permission.manage.ConfigManager;
import java.util.List;
import ts.car.utils.LogUtil;

/* JADX INFO: loaded from: classes8.dex */
public class CarPermissionManagerService extends ICarPermissionManager.Stub {
    private static final String PLATFORM_PACKAGE_NAME = "android";
    protected static final String TAG = "TSPS_TSPermissionManagerService";
    private AppInfoManager mAppInfoManager;
    private AppOpsManager mAppOps;
    private Context mContext;
    private PackageManager mPackageManager;
    private PermissionConfig mPermissionConfig;
    private IPermissionManager mPermissionManager;
    private UserManager mUserManager;

    public CarPermissionManagerService(Context context) {
        this.mContext = context;
        this.mAppOps = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mPackageManager = context.getPackageManager();
        ConfigManager configManager = ConfigManager.getInstance();
        this.mAppInfoManager = AppInfoManager.getInstance(this.mPackageManager);
        PermissionConfig permissionConfig = configManager.readPermissionConfig();
        this.mPermissionConfig = permissionConfig;
        if (permissionConfig != null) {
            permissionConfig.checkConfig();
            initAppInfoManagerDatas();
        }
    }

    private void initAppInfoManagerDatas() {
        if (this.mPermissionConfig.getPresetPaths() != null) {
            this.mAppInfoManager.setPresetDirs(this.mPermissionConfig.getPresetPaths());
        }
        this.mAppInfoManager.setPresetShops(this.mPermissionConfig.getPresetShops());
    }

    @Override // com.ts.permission.ICarPermissionManager
    public int checkPermission(String packageName, String permissionName) {
        LogUtil.info("checkPermission -- packageName : " + packageName + " | permissionName : " + permissionName);
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(permissionName)) {
            Log.w(TAG, "checkPermission -- packageName or permissionName is null!");
            return 4;
        }
        Rule rule = getRuleByPackageName(packageName);
        if (rule != null) {
            LogUtil.info("checkPermission -- rule majorType : " + rule.getMajorType());
            if (rule.getMajorType() == 1) {
                App whiteApp = getWhiteApp(rule, packageName);
                if (whiteApp != null) {
                    return checkPermissionFromApp(whiteApp, permissionName);
                }
            } else {
                return checkPermissionFromRule(rule, permissionName);
            }
        }
        return 4;
    }

    public int checkSignaturePermission(ApplicationInfo applicationInfo, String permissionName) {
        if (applicationInfo == null || TextUtils.isEmpty(permissionName)) {
            return 4;
        }
        LogUtil.info("checkSignaturePermission -- packageName : " + applicationInfo.packageName + " | permissionName : " + permissionName);
        Rule rule = getRuleByPackageName(applicationInfo);
        if (rule != null) {
            LogUtil.info("checkSignaturePermission -- rule majorType : " + rule.getMajorType());
            if (rule.getMajorType() == 1) {
                App whiteApp = getWhiteApp(rule, applicationInfo.packageName);
                if (whiteApp != null) {
                    return checkPermissionFromApp(whiteApp, permissionName);
                }
            } else {
                return checkPermissionFromRule(rule, permissionName);
            }
        }
        return 4;
    }

    @Override // com.ts.permission.ICarPermissionManager
    public void installPackageByPi(String packageName, String installerPackageName) {
        LogUtil.info("installPackageByPi -- packageName : " + packageName + " | installerPackageName : " + installerPackageName);
        this.mAppInfoManager.installPackageByPi(packageName, installerPackageName);
    }

    @Override // com.ts.permission.ICarPermissionManager
    public void installStageByPms(String packageName, String installerPackageName) {
        LogUtil.info("installStageByPms -- packageName : " + packageName + " | installerPackageName : " + installerPackageName);
        this.mAppInfoManager.installStageByPms(packageName, installerPackageName);
    }

    @Override // com.ts.permission.ICarPermissionManager
    public void handlePackagePostInstall(String packageName, boolean success) {
        LogUtil.info("handlePackagePostInstall -- packageName : " + packageName + " | success : " + success);
        this.mAppInfoManager.handlePackagePostInstall(packageName, success);
    }

    public void grantPermissions(List<PackageInfo> packageInfos) {
        LogUtil.info("packageInfos : " + packageInfos);
        if (packageInfos == null || packageInfos.size() == 0) {
            return;
        }
        for (PackageInfo packageInfo : packageInfos) {
            grantPackagePermissions(packageInfo, packageInfo.requestedPermissions);
        }
    }

    private void grantPackagePermissions(PackageInfo packageInfo, String[] permissionInfos) {
        if (packageInfo == null || permissionInfos == null || permissionInfos.length == 0 || packageInfo.applicationInfo.isInstantApp()) {
            return;
        }
        Rule rule = getRuleByPackageName(packageInfo.packageName);
        LogUtil.info("packageName : " + packageInfo.packageName);
        if (rule != null) {
            LogUtil.info("majorType : " + rule.getMajorType());
            if (rule.getMajorType() == 1) {
                App whiteApp = getWhiteApp(rule, packageInfo.packageName);
                if (whiteApp != null) {
                    grantOrRevokePermissionsByConfig(packageInfo, whiteApp.getAuthorizationType(), permissionInfos, whiteApp.getPermissions());
                    return;
                }
                return;
            }
            grantOrRevokePermissionsByConfig(packageInfo, rule.getAuthorizationType(), permissionInfos, rule.getPermissions());
        }
    }

    private Rule getRuleByPackageName(String packageName) {
        List<Rule> rules;
        PermissionConfig permissionConfig = this.mPermissionConfig;
        if (permissionConfig != null && (rules = permissionConfig.getRules()) != null && rules.size() > 0) {
            for (Rule rule : rules) {
                if (checkRule(rule, packageName)) {
                    return rule;
                }
            }
            return null;
        }
        return null;
    }

    private Rule getRuleByPackageName(ApplicationInfo applicationInfo) {
        List<Rule> rules;
        PermissionConfig permissionConfig = this.mPermissionConfig;
        if (permissionConfig != null && (rules = permissionConfig.getRules()) != null && rules.size() > 0) {
            for (Rule rule : rules) {
                if (checkRule(rule, applicationInfo)) {
                    return rule;
                }
            }
            return null;
        }
        return null;
    }

    private boolean checkRule(Rule rule, String packageName) {
        int majorType = rule.getMajorType();
        if (majorType != 1) {
            return majorType != 2 ? majorType != 3 ? majorType != 4 ? majorType == 5 : this.mAppInfoManager.isShopApp(packageName) : this.mAppInfoManager.isPresetApp(packageName) : this.mAppInfoManager.isSignatureApp(packageName);
        }
        App whiteApp = getWhiteApp(rule, packageName);
        return whiteApp != null;
    }

    private boolean checkRule(Rule rule, ApplicationInfo applicationInfo) {
        int majorType = rule.getMajorType();
        if (majorType != 1) {
            return majorType != 2 ? majorType != 3 ? majorType != 4 ? majorType == 5 : this.mAppInfoManager.isShopApp(applicationInfo.packageName) : this.mAppInfoManager.isPresetApp(applicationInfo) : this.mAppInfoManager.isSignatureApp(applicationInfo.packageName);
        }
        App whiteApp = getWhiteApp(rule, applicationInfo.packageName);
        return whiteApp != null;
    }

    private void grantRuntimePermissionByPms(String packageName, String permission) {
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(permission)) {
            return;
        }
        if (!isUserManagerPrepared()) {
            Log.w(TAG, "userManager not prepared, something is wrong");
            return;
        }
        LogUtil.info("package name (" + packageName + ") runtime permission (" + permission + ") is GRANTED");
        for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
            int userId = userInfo.id;
            LogUtil.info("grantRuntimePermissionByPms,  userId: " + userId);
            if (checkAppExitAsUser(packageName, userId)) {
                addWhitelistedRestrictedPermissionIfNeeded(packageName, permission, userId);
                this.mPackageManager.grantRuntimePermission(packageName, permission, UserHandle.of(userId));
            }
        }
    }

    private void addWhitelistedRestrictedPermissionIfNeeded(String packageName, String permissionName, int userId) {
        if (!isPermissionManagerPrepared()) {
            return;
        }
        try {
            PermissionInfo permissionInfo = this.mPackageManager.getPermissionInfo(permissionName, 0);
            if ((permissionInfo.flags & 4) != 0) {
                LogUtil.info("addWhitelistedRestrictedPermissionIfNeeded,  packageName:" + packageName + ", permissionName:" + permissionName);
                this.mPermissionManager.addWhitelistedRestrictedPermission(packageName, permissionName, 1, userId);
            }
        } catch (PackageManager.NameNotFoundException ex) {
            Log.wtf(TAG, ex);
        } catch (RemoteException ex2) {
            Log.wtf(TAG, ex2);
        }
    }

    private boolean isPermissionManagerPrepared() {
        if (this.mPermissionManager == null) {
            this.mPermissionManager = ActivityThread.getPermissionManager();
        }
        if (this.mPermissionManager == null) {
            Log.w(TAG, "permissionmanager not prepared, something is wrong!");
            return false;
        }
        return true;
    }

    private boolean checkAppExitAsUser(String packageName, int userId) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.mPackageManager.getApplicationInfoAsUser(packageName, 131072, userId);
        } catch (Throwable th) {
            Log.w(TAG, "checkAppExitAsUser error, package not exit, packageName:" + packageName + ", userId:" + userId);
        }
        if (applicationInfo == null) {
            Log.w(TAG, "checkAppExitAsUser, applicationInfo is null, maybe app not exit in current user, packageName:" + packageName + ", userId:" + userId);
            return false;
        }
        return true;
    }

    private void grantRuntimePermissionByAppOp(PackageInfo packageInfo, String appOp) {
        if (appOp == null) {
            return;
        }
        boolean appOpAllowed = this.mAppOps.checkOpNoThrow(appOp, packageInfo.applicationInfo.uid, packageInfo.packageName) == 0;
        if (!appOpAllowed) {
            LogUtil.info("grantRuntimePermissionByPms -- packageName : " + packageInfo.packageName + " | appOp : " + appOp);
            this.mAppOps.setUidMode(appOp, packageInfo.applicationInfo.uid, 0);
        }
    }

    private void grantRuntimePermission(PackageInfo packageInfo, PermissionInfo permissionInfo) {
        if (packageInfo == null || permissionInfo == null) {
            return;
        }
        String appOp = "android".equals(permissionInfo.packageName) ? AppOpsManager.permissionToOp(permissionInfo.name) : null;
        grantRuntimePermissionByAppOp(packageInfo, appOp);
        grantRuntimePermissionByPms(packageInfo.packageName, permissionInfo.name);
    }

    private void revokeRuntimePermissionByPms(String packageName, String permission) {
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(permission)) {
            return;
        }
        if (!isUserManagerPrepared()) {
            Log.w(TAG, "userManager not prepared, something is wrong");
            return;
        }
        for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
            int userId = userInfo.id;
            LogUtil.info("revokeRuntimePermissionByPms,  userId: " + userId + ", packageName:" + packageName);
            if (checkAppExitAsUser(packageName, userId)) {
                this.mPackageManager.revokeRuntimePermission(packageName, permission, UserHandle.of(userId));
            }
        }
    }

    private boolean isUserManagerPrepared() {
        if (this.mUserManager != null) {
            return true;
        }
        try {
            UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
            this.mUserManager = userManager;
            return userManager != null;
        } catch (Throwable ex) {
            Log.w(TAG, "initUserManager error", ex);
            return false;
        }
    }

    private App getWhiteApp(Rule rule, String packageName) {
        List<App> apps = rule.getApps();
        if (apps != null && apps.size() > 0) {
            for (App app : apps) {
                List<String> packageNames = app.getPackageNames();
                if (packageNames != null && packageNames.contains(packageName)) {
                    return app;
                }
            }
            return null;
        }
        return null;
    }

    private int checkPermissionFromApp(App app, String permissionName) {
        int authorizationType = app.getAuthorizationType();
        if (authorizationType == 1) {
            return 1;
        }
        if (authorizationType != 2) {
            return authorizationType != 3 ? 4 : 2;
        }
        List<Permission> permissions = app.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (TextUtils.equals(permissionName, permission.getName())) {
                    return permission.getStatus();
                }
            }
        }
        if (app.getDefaultState() == 2) {
            return 2;
        }
        return app.getDefaultState() == 1 ? 1 : 4;
    }

    private int checkPermissionFromRule(Rule rule, String permissionName) {
        int authorizationType = rule.getAuthorizationType();
        if (authorizationType == 1) {
            return 1;
        }
        if (authorizationType != 2) {
            return authorizationType != 3 ? 4 : 2;
        }
        List<Permission> permissions = rule.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (TextUtils.equals(permissionName, permission.getName())) {
                    return permission.getStatus();
                }
            }
        }
        if (rule.getDefaultState() == 2) {
            return 2;
        }
        return rule.getDefaultState() == 1 ? 1 : 4;
    }

    private void grantOrRevokePermissionsByConfig(PackageInfo packageInfo, int authorizationType, String[] permissionInfos, List<Permission> permissions) {
        if (permissionInfos == null || permissionInfos.length == 0) {
            Log.w(TAG, "package " + packageInfo.packageName + "has no permissions");
            return;
        }
        for (String permissionName : permissionInfos) {
            try {
                PermissionInfo permissionInfo = this.mPackageManager.getPermissionInfo(permissionName, 0);
                if (permissionInfo != null && (permissionInfo.protectionLevel & 1) != 0) {
                    int flags = this.mPackageManager.getPermissionFlags(permissionInfo.name, packageInfo.packageName, Process.myUserHandle());
                    if ((flags & 16) == 0 && (flags & 4) == 0) {
                        grantOrRevokePermissionByType(packageInfo, authorizationType, permissions, permissionInfo);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }

    private void grantOrRevokePermissionByType(PackageInfo packageInfo, int authorizationType, List<Permission> permissions, PermissionInfo permissionInfo) {
        if (authorizationType == 1) {
            grantRuntimePermission(packageInfo, permissionInfo);
            return;
        }
        if (authorizationType != 2) {
            if (authorizationType == 3) {
                revokeRuntimePermissionByPms(packageInfo.packageName, permissionInfo.name);
            }
        } else if (permissions != null && permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (TextUtils.equals(permissionInfo.name, permission.getName())) {
                    if (permission.getStatus() == 1) {
                        grantRuntimePermission(packageInfo, permissionInfo);
                        return;
                    } else {
                        if (permission.getStatus() == 2) {
                            revokeRuntimePermissionByPms(packageInfo.packageName, permissionInfo.name);
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }
}
