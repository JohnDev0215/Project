package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.android.car.developeroptions.applications.AppStateAppOpsBridge;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class AppStateChangeWifiStateBridge extends AppStateAppOpsBridge {
    private static final String[] PM_PERMISSIONS = {"android.permission.CHANGE_WIFI_STATE"};
    public static final ApplicationsState.AppFilter FILTER_CHANGE_WIFI_STATE = new ApplicationsState.AppFilter() { // from class: com.android.car.developeroptions.wifi.AppStateChangeWifiStateBridge.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            Object obj;
            if (appEntry == null || (obj = appEntry.extraInfo) == null) {
                return false;
            }
            WifiSettingsState wifiSettingsState = (WifiSettingsState) obj;
            PackageInfo packageInfo = wifiSettingsState.packageInfo;
            if (packageInfo == null || !ArrayUtils.contains(packageInfo.requestedPermissions, "android.permission.NETWORK_SETTINGS")) {
                return wifiSettingsState.permissionDeclared;
            }
            return false;
        }
    };

    public AppStateChangeWifiStateBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback) {
        super(context, applicationsState, callback, 71, PM_PERMISSIONS);
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        appEntry.extraInfo = getWifiSettingsInfo(str, i);
    }

    @Override // com.android.car.developeroptions.applications.AppStateAppOpsBridge, com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void loadAllExtraInfo() {
        for (ApplicationsState.AppEntry appEntry : this.mAppSession.getAllApps()) {
            ApplicationInfo applicationInfo = appEntry.info;
            updateExtraInfo(appEntry, applicationInfo.packageName, applicationInfo.uid);
        }
    }

    public WifiSettingsState getWifiSettingsInfo(String str, int i) {
        return new WifiSettingsState(super.getPermissionInfo(str, i));
    }

    public static class WifiSettingsState extends AppStateAppOpsBridge.PermissionState {
        public WifiSettingsState(AppStateAppOpsBridge.PermissionState permissionState) {
            super(permissionState.packageName, permissionState.userHandle);
            this.packageInfo = permissionState.packageInfo;
            this.appOpMode = permissionState.appOpMode;
            this.permissionDeclared = permissionState.permissionDeclared;
            this.staticPermissionGranted = permissionState.staticPermissionGranted;
        }
    }
}
