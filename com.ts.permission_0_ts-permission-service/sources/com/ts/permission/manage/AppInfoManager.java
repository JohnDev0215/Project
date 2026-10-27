package com.ts.permission.manage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ts.car.utils.CarGson;

/* JADX INFO: loaded from: classes.dex */
public class AppInfoManager {
    private static final String PACKAGE_INSTALLER_NAME = "com.android.packageinstaller";
    private static final String PRESET_DIR_SYSTEM_APP = "/system/app/";
    private static final String PRESET_DIR_SYSTEM_PRIVAPP = "/system/priv-app/";
    private static final String PRESET_DIR_VENDOR_APP = "/vendor/app/";
    private static final String TAG = "TSPS_AppInfoManager";
    private static final String TS_INSTALL_PACKAGES = "/data/system/ts_install_packages.json";
    private static volatile AppInfoManager mInstance;
    private PackageManager mPm;
    private List<String> mPresetDirs;
    private List<String> mPresetShops;
    private Map<String, String> mInstallPackageMap = new HashMap();
    private Map<String, String> mTempInstallMap = new HashMap();
    private Map<String, String> mOldInstallMap = new HashMap();

    public static AppInfoManager getInstance(PackageManager pm) {
        if (mInstance == null) {
            synchronized (AppInfoManager.class) {
                if (mInstance == null) {
                    mInstance = new AppInfoManager(pm);
                }
            }
        }
        return mInstance;
    }

    private AppInfoManager(PackageManager pm) {
        this.mPm = pm;
        initPresetDirs();
        readInstallPackages();
    }

    private void initPresetDirs() {
        ArrayList arrayList = new ArrayList();
        this.mPresetDirs = arrayList;
        arrayList.add(PRESET_DIR_VENDOR_APP);
        this.mPresetDirs.add(PRESET_DIR_SYSTEM_APP);
        this.mPresetDirs.add(PRESET_DIR_SYSTEM_PRIVAPP);
    }

    public void setPresetDirs(List<String> presetDirs) {
        if (presetDirs != null) {
            this.mPresetDirs = presetDirs;
        }
    }

    public void setPresetShops(List<String> presetShops) {
        this.mPresetShops = presetShops;
    }

    public boolean isSignatureApp(String packageName) {
        return false;
    }

    public boolean isPresetApp(String packageName) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(packageName)) {
            Log.w(TAG, "packageName cannot be null when check preset app");
            return false;
        }
        PackageManager packageManager = this.mPm;
        if (packageManager == null) {
            Log.w(TAG, "PackageManager is null");
            return false;
        }
        ApplicationInfo appInfo = null;
        try {
            appInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException exception) {
            Log.e(TAG, "cannot file the app with the packageName : " + packageName, exception);
        }
        if (appInfo != null) {
            if (TextUtils.isEmpty(appInfo.sourceDir)) {
                Log.w(TAG, "cannot found the apk path with the packageName : " + packageName);
            } else {
                return checkPresetApp(appInfo.sourceDir);
            }
        }
        return false;
    }

    public boolean isPresetApp(ApplicationInfo appInfo) {
        if (appInfo != null) {
            if (TextUtils.isEmpty(appInfo.sourceDir)) {
                Log.w(TAG, "cannot found the apk path with the packageName : " + appInfo);
                return false;
            }
            return checkPresetApp(appInfo.sourceDir);
        }
        return false;
    }

    public boolean isShopApp(String packageName) {
        List<String> list = this.mPresetShops;
        if (list == null || list.size() == 0 || TextUtils.isEmpty(packageName)) {
            return false;
        }
        boolean checkInstaller = checkInstallPackage(packageName);
        if (checkInstaller) {
            return true;
        }
        readInstallPackages();
        String installPackageName = this.mInstallPackageMap.get(packageName);
        if (installPackageName != null) {
            return isPackageInPresetShops(installPackageName);
        }
        return false;
    }

    private boolean checkInstallPackage(String packageName) {
        PackageManager packageManager = this.mPm;
        if (packageManager == null) {
            Log.w(TAG, "PackageManager is null");
            return false;
        }
        String installerPackageName = packageManager.getInstallerPackageName(packageName);
        return isPackageInPresetShops(installerPackageName);
    }

    public void installPackageByPi(String packageName, String installerPackageName) {
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(installerPackageName)) {
            return;
        }
        this.mTempInstallMap.put(packageName, installerPackageName);
    }

    public void installStageByPms(String packageName, String installerPackageName) {
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(installerPackageName)) {
            return;
        }
        if (TextUtils.equals(installerPackageName, PACKAGE_INSTALLER_NAME)) {
            String tempInstallName = this.mTempInstallMap.get(packageName);
            if (tempInstallName != null) {
                refreshInstallMap(packageName, tempInstallName);
                writeInstallPackagesIntoJsonFile();
            }
            this.mTempInstallMap.remove(packageName);
            return;
        }
        refreshInstallMap(packageName, installerPackageName);
        writeInstallPackagesIntoJsonFile();
    }

    private void refreshInstallMap(String packageName, String tempInstallName) {
        if (isPackageInPresetShops(tempInstallName)) {
            if (this.mInstallPackageMap.containsKey(packageName)) {
                this.mOldInstallMap.put(packageName, this.mInstallPackageMap.get(packageName));
            }
            this.mInstallPackageMap.put(packageName, tempInstallName);
        } else {
            if (this.mInstallPackageMap.containsKey(packageName)) {
                this.mOldInstallMap.put(packageName, this.mInstallPackageMap.get(packageName));
            }
            this.mInstallPackageMap.remove(packageName);
        }
    }

    public void handlePackagePostInstall(String packageName, boolean success) {
        if (packageName == null) {
            return;
        }
        if (!success) {
            if (this.mOldInstallMap.containsKey(packageName)) {
                this.mInstallPackageMap.put(packageName, this.mOldInstallMap.remove(packageName));
                writeInstallPackagesIntoJsonFile();
                return;
            }
            return;
        }
        this.mOldInstallMap.remove(packageName);
    }

    private void writeInstallPackagesIntoJsonFile() {
        CarGson carGson = new CarGson();
        boolean writeFlag = carGson.writeObjectToJsonFile(TS_INSTALL_PACKAGES, this.mInstallPackageMap);
        if (writeFlag) {
            Log.i(TAG, "write install packages success");
        } else {
            Log.i(TAG, "write install packages failed");
        }
    }

    private boolean isPackageInPresetShops(String packageName) {
        List<String> list;
        if (packageName != null && (list = this.mPresetShops) != null && list.size() > 0) {
            return this.mPresetShops.contains(packageName);
        }
        return false;
    }

    private void readInstallPackages() {
        this.mInstallPackageMap.clear();
        CarGson carGson = new CarGson();
        Map<String, String> map = (Map) carGson.getObjectFromJsonFile(TS_INSTALL_PACKAGES, this.mInstallPackageMap.getClass());
        if (map != null) {
            this.mInstallPackageMap.putAll(map);
        }
    }

    private boolean checkPresetApp(String apkPath) {
        if (apkPath == null) {
            return false;
        }
        for (String presetDir : this.mPresetDirs) {
            if (apkPath.contains(presetDir)) {
                return true;
            }
        }
        return false;
    }
}
