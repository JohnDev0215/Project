package com.ts.car.ncm;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.ts.car.ncm.network.ExtConnectivityService;

/* JADX INFO: loaded from: classes.dex */
public class ExtConnectivityServiceApp extends Application {
    private static String TAG = ExtConnectivityServiceApp.class.getSimpleName();

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ExtConnectivityService.class);
        startService(intent);
    }
}
