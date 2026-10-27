package com.ts.appservice.settings.impl.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.location.ILocationSettingsCallback;
import com.ts.lib.settings.location.ILocationSettingsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ILocationServiceBinder extends ILocationSettingsService.Stub {
    private static final String TAG = ILocationServiceBinder.class.getSimpleName();
    private Context mContext;
    private LocationCallback mLocationCallback;
    List<String> mPackageList = new ArrayList();

    public ILocationServiceBinder(Context context) {
        this.mContext = context;
        this.mLocationCallback = new LocationCallback(this.mContext);
        registerBroadcast();
    }

    private void registerBroadcast() {
        BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ts.appservice.settings.impl.location.ILocationServiceBinder.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (((action.hashCode() == -1873462329 && action.equals("com.ts.test.get.location")) ? (byte) 0 : (byte) -1) == 0) {
                    String[] locationUsePackageName = ILocationServiceBinder.this.getLocationUsePackageName();
                    LogUtil.error(ILocationServiceBinder.TAG, "getLocationUsePackageName value is :" + Arrays.toString(locationUsePackageName));
                    ILocationServiceBinder.this.mLocationCallback.onLocUseChange();
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ts.test.get.location");
        this.mContext.registerReceiver(receiver, filter);
    }

    public String[] getLocUsePackageName() {
        LogUtil.debug(TAG, "getLocUsePackageName value ");
        return getLocationUsePackageName();
    }

    public void subscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLocationCallback.register(callback);
        }
    }

    public void unsubscribeLocationCallback(ILocationSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLocationCallback.unregister(callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getLocationUsePackageName() {
        PackageManager pm = this.mContext.getPackageManager();
        List<ApplicationInfo> allApps = pm.getInstalledApplications(0);
        for (ApplicationInfo appInfo : allApps) {
            String packageName = appInfo.packageName;
            LogUtil.info(TAG, "getLocationUsePackageName packageName : " + packageName);
            if ((appInfo.flags & 1) != 0) {
                LogUtil.error(TAG, "getLocationUsePackageName ignored packageName : " + packageName);
            } else {
                try {
                    String[] permissions = pm.getPackageInfo(packageName, 4096).requestedPermissions;
                    if (permissions != null) {
                        for (String permission : permissions) {
                            if (!permission.equals("android.permission.ACCESS_COARSE_LOCATION") && !permission.equals("android.permission.ACCESS_FINE_LOCATION")) {
                            }
                            LogUtil.debug(TAG, "getLocationUsePackageName has location package name is :" + packageName);
                            this.mPackageList.add(packageName);
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        String[] locationPackageName = new String[this.mPackageList.size()];
        for (int i = 0; i < this.mPackageList.size(); i++) {
            locationPackageName[i] = this.mPackageList.get(i);
            this.mPackageList.remove(i);
        }
        return locationPackageName;
    }

    public void destoryLocationBinder() {
        LocationCallback locationCallback = this.mLocationCallback;
        if (locationCallback != null) {
            locationCallback.kill();
        }
    }
}
