package com.ts.lib.settings.boot;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.boot.IAppBootSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppBootSettingsManager implements SettingsAdapterBase {
    private static final String TAG = AppBootSettingsManager.class.getSimpleName();
    private IAppBootSettingsService mAppBootSettingsService;

    public AppBootSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init appBoot Manager");
        this.mAppBootSettingsService = IAppBootSettingsService.Stub.asInterface(binder);
    }

    public List<BootApp> getStartupAppList() {
        LogUtil.debug(TAG, "getStartupAppList");
        if (this.mAppBootSettingsService == null) {
            LogUtil.error(TAG, "mAppBootSettingsService is null");
        }
        try {
            return this.mAppBootSettingsService.getStartupAppList();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getStartupAppList failed ", ex);
            return null;
        }
    }

    public boolean getStartupAppEnable(String pkg) {
        LogUtil.debug(TAG, "getStartupAppEnable pkg : " + pkg);
        if (this.mAppBootSettingsService == null) {
            LogUtil.error(TAG, "mAppBootSettingsService is null");
        }
        try {
            return this.mAppBootSettingsService.getStartupAppEnable(pkg);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getStartupAppEnable failed ", ex);
            return false;
        }
    }

    public int setStartupAppEnable(String pkg, boolean enable) {
        LogUtil.debug(TAG, "setStartupAppEnable pkg : " + pkg + " enable : " + enable);
        if (this.mAppBootSettingsService == null) {
            LogUtil.error(TAG, "mAppBootSettingsService is null");
        }
        try {
            return this.mAppBootSettingsService.setStartupAppEnable(pkg, enable);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setStartupAppEnable failed ", ex);
            return -1;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
    }
}
