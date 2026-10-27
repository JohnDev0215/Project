package com.ts.permission;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.permission.ICarPermissionManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPermissionManagerImpl extends CarPermissionManager {
    private static final String SERVICE = "com.ts.permission/.CoreService";
    private ICarPermissionManager iTSpm;

    protected CarPermissionManagerImpl(Context context) {
        initService(context);
    }

    @Override // com.ts.permission.CarPermissionManager
    public int checkPermission(String packageName, String permissionName) {
        ICarPermissionManager iCarPermissionManager = this.iTSpm;
        if (iCarPermissionManager != null) {
            try {
                return iCarPermissionManager.checkPermission(packageName, permissionName);
            } catch (RemoteException remoteException) {
                Log.e("TSPS_TSPermissionManager", "checkPermission failed", remoteException);
                return 4;
            }
        }
        return 4;
    }

    @Override // com.ts.permission.CarPermissionManager
    public void installPackageByPi(String packageName, String installerPackageName) {
        ICarPermissionManager iCarPermissionManager = this.iTSpm;
        if (iCarPermissionManager == null) {
            return;
        }
        try {
            iCarPermissionManager.installPackageByPi(packageName, installerPackageName);
        } catch (RemoteException remoteException) {
            Log.e("TSPS_TSPermissionManager", "installPackageByPi failed", remoteException);
        }
    }

    @Override // com.ts.permission.CarPermissionManager
    public void installStageByPms(String packageName, String installerPackageName) {
        ICarPermissionManager iCarPermissionManager = this.iTSpm;
        if (iCarPermissionManager == null) {
            return;
        }
        try {
            iCarPermissionManager.installStageByPms(packageName, installerPackageName);
        } catch (RemoteException remoteException) {
            Log.e("TSPS_TSPermissionManager", "installStageByPms failed", remoteException);
        }
    }

    @Override // com.ts.permission.CarPermissionManager
    public void handlePackagePostInstall(String packageName, boolean success) {
        ICarPermissionManager iCarPermissionManager = this.iTSpm;
        if (iCarPermissionManager == null) {
            return;
        }
        try {
            iCarPermissionManager.handlePackagePostInstall(packageName, success);
        } catch (RemoteException remoteException) {
            Log.e("TSPS_TSPermissionManager", "handlePackagePostInstall failed", remoteException);
        }
    }

    private void initService(Context context) {
        IActivityManager am = ActivityManager.getService();
        Intent intent = new Intent();
        intent.setComponent(ComponentName.unflattenFromString(SERVICE));
        try {
            IBinder binder = am.peekService(intent, (String) null, context.getPackageName());
            if (binder == null) {
                Log.i("TSPS_TSPermissionManager", "peek service failed");
            } else {
                this.iTSpm = ICarPermissionManager.Stub.asInterface(binder);
                Log.i("TSPS_TSPermissionManager", "peek service success");
            }
        } catch (RemoteException exception) {
            Log.e("TSPS_TSPermissionManager", "cannnot peek service", exception);
        }
    }
}
