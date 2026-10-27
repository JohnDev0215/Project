package com.ts.permission;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class CoreApplication extends Application implements ServiceConnection {
    private static final String TAG = "TSPS_CoreApplication";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CoreApplication start");
        Intent intent = new Intent(this, (Class<?>) CoreService.class);
        bindService(intent, this, 1);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
