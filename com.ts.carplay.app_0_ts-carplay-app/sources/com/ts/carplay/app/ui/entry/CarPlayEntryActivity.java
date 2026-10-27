package com.ts.carplay.app.ui.entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.app.service.CarPlayRemoteService;
import com.ts.carplay.utils.LogUtil;
import ts.car.service.carplay.ICarPlayAppManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayEntryActivity extends Activity {
    private static final String TAG = CarPlayEntryActivity.class.getSimpleName();
    private ICarPlayAppManager mCarPlayAppManager = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this);
        Intent intent = new Intent();
        intent.setClass(this, CarPlayRemoteService.class);
        startService(intent);
        try {
            this.mCarPlayAppManager.requestUi(0);
        } catch (RemoteException e) {
        }
        LogUtil.debug(TAG, "onCreate succeed");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
