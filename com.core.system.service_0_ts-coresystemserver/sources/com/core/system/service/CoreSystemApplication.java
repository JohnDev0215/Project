package com.core.system.service;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.core.system.service.common.CoreSystemCrashHandler;
import com.core.system.service.utils.AutoBootOnUserHelper;
import com.ts.car.core.ServiceProvider;
import com.ts.car.sdk.core.Logger;
import ts.car.service.core.ICoreSystemService;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemApplication extends Application implements ServiceConnection {
    private AutoBootOnUserHelper autoBootOnUser;
    private Context mContext;
    private ICoreSystemService mCoreSystemService;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        CoreSystemCrashHandler.getInstance();
        startLocalService();
        AutoBootOnUserHelper autoBootOnUserHelper = new AutoBootOnUserHelper(getApplicationContext(), 1, true);
        this.autoBootOnUser = autoBootOnUserHelper;
        autoBootOnUserHelper.startAutoBoot(getPackageName(), CoreSystemServer.class.getName(), null);
    }

    private void startLocalService() {
        Intent startIntent = new Intent(this, (Class<?>) CoreSystemServer.class);
        bindService(startIntent, this, 1);
        Logger.info("onCreate startService startIntent : " + startIntent.toString());
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder binder) {
        Logger.info("onServiceConnected:" + name + ", binder:" + binder);
        ICoreSystemService iCoreSystemService = (ICoreSystemService) ServiceProvider.get(ICoreSystemService.class, this.mContext);
        this.mCoreSystemService = iCoreSystemService;
        if (iCoreSystemService != null) {
            iCoreSystemService.checkAndReconnect();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        Logger.warning("onServiceDisconnected Restart");
        startLocalService();
    }
}
