package com.ts.car.timesync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.core.system.foundationinterface.ICoreAdminService;
import com.ts.car.core.FoundationProvider;

/* JADX INFO: loaded from: classes.dex */
public class TimeSyncService extends Service {
    private TimeSyncServiceImpl mImpl;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        TimeSyncServiceImpl timeSyncServiceImpl = new TimeSyncServiceImpl();
        this.mImpl = timeSyncServiceImpl;
        timeSyncServiceImpl.init(getApplicationContext(), (ICoreAdminService) FoundationProvider.create(ICoreAdminService.class, getApplicationContext()));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mImpl.release(getApplicationContext(), null);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mImpl.getServiceBinder();
    }
}
