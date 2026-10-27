package com.ts.car.dcm.dccontroller;

import android.app.Application;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionManagerServiceApp extends Application {
    private static final String TAG = ConnectionManagerServiceApp.class.getSimpleName();
    private DcController mDcController;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.mDcController = DcController.getInstance(getApplicationContext());
    }
}
