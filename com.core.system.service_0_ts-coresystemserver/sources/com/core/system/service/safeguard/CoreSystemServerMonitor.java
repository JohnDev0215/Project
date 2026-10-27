package com.core.system.service.safeguard;

import android.content.Context;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteCallbackList;
import com.core.lib.common.ICoreSystemServerListener;
import com.core.system.service.safeguard.Watchdog;
import com.core.system.service.utils.ServiceDataManager;
import com.ts.car.sdk.core.Logger;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemServerMonitor implements Watchdog.Monitor {
    private static final int WATCHDOG_MONITOR = 10001;
    private Context mContext;
    private RemoteCallbackList<ICoreSystemServerListener> mListenerList;
    private HashMap<String, ServiceDataManager> mServiceDataMap;

    public CoreSystemServerMonitor(Context context, RemoteCallbackList<ICoreSystemServerListener> listenerList, HashMap<String, ServiceDataManager> serviceDataMap) {
        this.mContext = context;
        this.mServiceDataMap = serviceDataMap;
        this.mListenerList = listenerList;
    }

    @Override // com.core.system.service.safeguard.Watchdog.Monitor
    public void monitor() {
        Logger.debug(" monitor start, pid:" + Process.myPid() + ", tid is " + Thread.currentThread().getId());
        HashMap<String, ServiceDataManager> serviceDataMap = new HashMap<>();
        synchronized (this.mServiceDataMap) {
            serviceDataMap.putAll(this.mServiceDataMap);
        }
        for (ServiceDataManager serviceDataManager : serviceDataMap.values()) {
            if (serviceDataManager.getServiceBinder() != null) {
                try {
                    if (!serviceDataManager.getServiceData().isExternalService()) {
                        Parcel data = Parcel.obtain();
                        Parcel reply = Parcel.obtain();
                        boolean result = serviceDataManager.getServiceBinder().transact(10001, data, reply, 0);
                        Logger.debug("watchdog monitor, serviceName:" + serviceDataManager.getServiceData().getServiceName() + "transact result: " + result);
                        if (data != null) {
                            data.recycle();
                        }
                        if (reply != null) {
                            reply.recycle();
                        }
                    }
                } catch (Throwable exception) {
                    Logger.warning("CoreSystemServer monitor error", exception);
                }
            }
        }
        synchronized (this.mListenerList) {
        }
        synchronized (this) {
        }
        Logger.debug(" monitor end, pid:" + Process.myPid() + ", tid is " + Thread.currentThread().getId());
    }
}
