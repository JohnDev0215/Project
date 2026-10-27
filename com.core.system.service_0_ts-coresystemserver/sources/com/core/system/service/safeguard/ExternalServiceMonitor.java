package com.core.system.service.safeguard;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Parcel;
import android.os.Process;
import com.core.system.service.safeguard.Watchdog;
import com.core.system.service.utils.ServiceDataManager;
import com.ts.car.sdk.core.Logger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExternalServiceMonitor implements Watchdog.ExServiceMonitor {
    private static final int WATCHDOG_MONITOR = 10001;
    private ActivityManager mActivityManager;
    private Context mContext;
    private String mPackageName;
    private int mPid;
    private String mProcessName;
    private ServiceDataManager mServiceDataManager;
    private HashMap<String, ServiceDataManager> mServiceDataManagerMap;
    private int mUid;

    public ExternalServiceMonitor(Context context, HashMap<String, ServiceDataManager> serviceDataManagerHashMap) {
        this.mContext = context;
        this.mServiceDataManagerMap = serviceDataManagerHashMap;
        this.mActivityManager = (ActivityManager) context.getSystemService("activity");
    }

    @Override // com.core.system.service.safeguard.Watchdog.Monitor
    public void monitor() {
        HashMap<String, ServiceDataManager> serviceDataManagerMap = new HashMap<>();
        synchronized (this.mServiceDataManagerMap) {
            serviceDataManagerMap.putAll(this.mServiceDataManagerMap);
        }
        for (ServiceDataManager serviceDataManager : serviceDataManagerMap.values()) {
            this.mServiceDataManager = serviceDataManager;
            if (serviceDataManager.getServiceBinder() != null) {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    if (serviceDataManager.getServiceData().isExternalService() && serviceDataManager.getServiceBinder().isBinderAlive()) {
                        boolean result = serviceDataManager.getServiceBinder().transact(10001, data, reply, 0);
                        Logger.debug("watchdog monitor " + serviceDataManager.getServiceData().getServiceName() + " transact result: " + result);
                    }
                    if (data != null) {
                        data.recycle();
                    }
                } finally {
                    try {
                    } catch (Throwable th) {
                    }
                }
                if (reply != null) {
                    reply.recycle();
                }
            }
        }
        this.mServiceDataManager = null;
    }

    @Override // com.core.system.service.safeguard.Watchdog.ExServiceMonitor
    public void killSelf() {
        Logger.debug(" killSelf tid is " + Thread.currentThread().getId());
        ServiceDataManager serviceDataManager = this.mServiceDataManager;
        if (serviceDataManager == null || serviceDataManager.getComponentName() == null) {
            return;
        }
        ComponentName componentName = this.mServiceDataManager.getComponentName();
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> it = serviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningServiceInfo info = it.next();
            Logger.debug("run: pid " + info.pid + "  -uid  " + info.uid + " - " + info.service);
            if (info.service != null && info.service.getClassName().equals(componentName.getClassName()) && info.service.getPackageName().equals(componentName.getPackageName())) {
                this.mPid = info.pid;
                this.mUid = info.uid;
                this.mProcessName = info.process;
                this.mPackageName = componentName.getPackageName();
                break;
            }
        }
        if (this.mPid == 0 || this.mUid == 0) {
            this.mPid = 0;
            this.mUid = 0;
            return;
        }
        ServiceDataManager serviceDataManager2 = this.mServiceDataManager;
        if (serviceDataManager2 != null) {
            serviceDataManager2.setServiceState(8);
        }
        Logger.warning("kill ExternalService mUid: " + this.mUid + ", mPid:" + this.mPid);
        if (this.mUid == 1000) {
            Logger.warning("kill ExternalService mPid: " + this.mPid);
            Process.killProcess(this.mPid);
        } else if (this.mProcessName != null) {
            Logger.warning("kill ExternalService : ProcessName " + this.mProcessName + " PackageName " + this.mPackageName);
            this.mActivityManager.forceStopPackage(this.mPackageName);
        }
        this.mProcessName = null;
    }
}
