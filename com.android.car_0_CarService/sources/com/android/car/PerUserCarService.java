package com.android.car;

import android.app.Service;
import android.car.ICarBluetoothUserService;
import android.car.ILocationManagerProxy;
import android.car.IPerUserCarService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class PerUserCarService extends Service {
    private static final boolean DBG = true;
    private static final String TAG = "PerUserCarService";
    private volatile CarBluetoothUserService mCarBluetoothUserService;
    private volatile LocationManagerProxy mLocationManagerProxy;
    private PerUserCarServiceBinder mPerUserCarServiceBinder;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        if (this.mPerUserCarServiceBinder == null) {
            Log.e(TAG, "UserSvcBinder null");
        }
        return this.mPerUserCarServiceBinder;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart()");
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        this.mPerUserCarServiceBinder = new PerUserCarServiceBinder();
        this.mCarBluetoothUserService = new CarBluetoothUserService(this);
        this.mLocationManagerProxy = new LocationManagerProxy(this);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        this.mPerUserCarServiceBinder = null;
    }

    private final class PerUserCarServiceBinder extends IPerUserCarService.Stub {
        private PerUserCarServiceBinder() {
        }

        public ICarBluetoothUserService getBluetoothUserService() {
            return PerUserCarService.this.mCarBluetoothUserService;
        }

        public ILocationManagerProxy getLocationManagerProxy() {
            return PerUserCarService.this.mLocationManagerProxy;
        }
    }
}
