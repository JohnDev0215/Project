package com.ts.car.sdk.time;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.time.ITimeSyncManager;
import ts.car.service.time.ITimeUpdateCallBack;
import ts.car.timesync.ITimeSyncManager;
import ts.car.timesync.ITimeUpdateListener;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformTimeSyncManager extends SimpleServiceBase implements ITimeSyncManager {
    private static final String REMOTE_SERVICE_NAME = "time_sync_service";
    private static final String SERVICE_COMPONENT = "com.ts.car.timesync/.TimeSyncService";
    private static final String TAG = PlatformTimeSyncManager.class.getSimpleName();
    private List<ITimeUpdateCallBack> mCallBack;
    private ts.car.timesync.ITimeSyncManager mService;
    private ITimeUpdateListener mTimeUpdateListener;

    @PlatformConstruction
    public PlatformTimeSyncManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mCallBack = new ArrayList();
        this.mTimeUpdateListener = new ITimeUpdateListener.Stub() { // from class: com.ts.car.sdk.time.PlatformTimeSyncManager.1
            @Override // ts.car.timesync.ITimeUpdateListener
            public void onTimeUpdate(long millis, String timeZone) throws RemoteException {
                List<ITimeUpdateCallBack> internalCallBack;
                synchronized (PlatformTimeSyncManager.this.mCallBack) {
                    internalCallBack = new ArrayList<>(PlatformTimeSyncManager.this.mCallBack);
                }
                for (ITimeUpdateCallBack callBack : internalCallBack) {
                    callBack.onTimeUpdate(millis, timeZone);
                }
            }
        };
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-TimeSyncService_4.5.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        ts.car.timesync.ITimeSyncManager iTimeSyncManagerAsInterface = ITimeSyncManager.Stub.asInterface(iBinder);
        this.mService = iTimeSyncManagerAsInterface;
        if (iTimeSyncManagerAsInterface != null) {
            try {
                iTimeSyncManagerAsInterface.registerTimeUpdate(this.mTimeUpdateListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        ts.car.timesync.ITimeSyncManager iTimeSyncManager = this.mService;
        if (iTimeSyncManager != null) {
            try {
                iTimeSyncManager.unregisterTimeUpdate(this.mTimeUpdateListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        this.mService = null;
    }

    @Override // ts.car.service.time.ITimeSyncManager
    public void setTime(long millis, String timeZone, int type) throws RemoteException {
        Log.d(TAG, "setTime");
        if (this.mService == null) {
            throw new IllegalStateException("ITimeSyncManager is not Ready!");
        }
        if (!checkSourceType(type)) {
            throw new IllegalArgumentException("Type is not available!");
        }
        this.mService.setTime(millis, timeZone, type);
    }

    private boolean checkSourceType(int type) {
        if (type < 0 || type > 7) {
            return false;
        }
        return true;
    }

    @Override // ts.car.service.time.ITimeSyncManager
    public void registerTimeUpdate(ITimeUpdateCallBack callBack) throws RemoteException {
        Log.d(TAG, "registerTimeUpdate");
        if (callBack == null) {
            throw new IllegalArgumentException("listener can't be null!");
        }
        synchronized (this.mCallBack) {
            if (!this.mCallBack.contains(callBack)) {
                this.mCallBack.add(callBack);
            }
        }
    }

    @Override // ts.car.service.time.ITimeSyncManager
    public void unregisterTimeUpdate(ITimeUpdateCallBack callBack) throws RemoteException {
        if (callBack == null) {
            throw new IllegalArgumentException("callBack can't be null!");
        }
        synchronized (this.mCallBack) {
            if (this.mCallBack.contains(callBack)) {
                this.mCallBack.remove(callBack);
            }
        }
    }
}
