package com.ts.car.ncm.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class ExtConnectivityService extends Service {
    public static final String TAG = ExtConnectivityService.class.getSimpleName();
    private ExtConnectivityServiceImpl mImpl;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ExtConnectivityServiceImpl extConnectivityServiceImpl = new ExtConnectivityServiceImpl(this);
        this.mImpl = extConnectivityServiceImpl;
        extConnectivityServiceImpl.start();
        Log.i(TAG, TAG + " onCreate() and ExtConnectivityServiceImpl start");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mImpl;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mImpl.stop();
    }
}
