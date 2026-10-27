package com.ts.car.input;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.ts.car.core.FoundationProvider;

/* JADX INFO: loaded from: classes.dex */
public class InputCoreService extends Service {
    private static final String TAG = "InputCoreService";
    private ICoreService coreService;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HardKeyInputPolicyManagerService hardKeyInputPolicyManagerService = new HardKeyInputPolicyManagerService();
        this.coreService = hardKeyInputPolicyManagerService;
        hardKeyInputPolicyManagerService.init(getApplicationContext(), (ICoreAdminService) FoundationProvider.create(ICoreAdminService.class, getApplicationContext()));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtils.log(TAG, "onBind, intent: " + intent);
        return this.coreService.getServiceBinder();
    }
}
