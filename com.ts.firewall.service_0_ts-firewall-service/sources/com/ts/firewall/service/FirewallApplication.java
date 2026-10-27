package com.ts.firewall.service;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class FirewallApplication extends Application {
    public static String TAG = "FIREWALLAPPLICATION";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.d("FirewallApplication", "onCreate");
        Intent intent = new Intent(getApplicationContext(), (Class<?>) FirewallService.class);
        startService(intent);
    }
}
