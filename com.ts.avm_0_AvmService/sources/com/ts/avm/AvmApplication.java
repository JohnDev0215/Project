package com.ts.avm;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.ts.avm.utils.LogManager;

/* JADX INFO: loaded from: classes.dex */
public class AvmApplication extends Application {
    private static final String DEAMON_SERVICE_FULL_PACKAGE_NAME = "com.ts.avm.AvmAndroidService";
    private static final String PACKAGE_NAME = "com.ts.avm";
    private static final String TAG = AvmApplication.class.getSimpleName();
    private static int index = 0;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ts.avm.AvmApplication.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogManager.logd(AvmApplication.TAG, "onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        if (index == 0) {
            bindService();
            index++;
        }
    }

    private void bindService() {
        LogManager.logd(TAG, "bindService: ");
        Intent service = new Intent(DEAMON_SERVICE_FULL_PACKAGE_NAME);
        service.setComponent(new ComponentName(PACKAGE_NAME, DEAMON_SERVICE_FULL_PACKAGE_NAME));
        boolean isok = bindService(service, this.mServiceConnection, 1);
        LogManager.logv(TAG, " service bind is ok " + isok);
    }
}
