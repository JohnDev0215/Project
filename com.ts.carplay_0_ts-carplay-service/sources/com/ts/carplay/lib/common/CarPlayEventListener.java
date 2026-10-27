package com.ts.carplay.lib.common;

import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.resourcemanager.CarPlayResourceManager;
import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayEventListener {
    private CarPlayResourceManager mCarPlayResourceManager;
    private CarPlayManager mManager;

    public CarPlayEventListener(CarPlayManager manager) {
        this.mManager = null;
        this.mManager = manager;
        if (manager != null) {
            this.mCarPlayResourceManager = new CarPlayResourceManager(this.mManager);
        }
    }

    public void onConnected(LinkDevice device) {
    }

    public void onDisconnected(LinkDevice device) {
    }

    public void onSessionEstablished(LinkDevice device) {
    }

    public void onSessionDestoryed(LinkDevice device) {
        CarPlayResourceManager carPlayResourceManager;
        if (this.mManager != null && (carPlayResourceManager = this.mCarPlayResourceManager) != null) {
            carPlayResourceManager.onSessionDestoryed(device);
            this.mManager.onSessionDestoryed(device);
        }
    }
}
