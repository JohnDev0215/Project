package com.android.server.telecom.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.server.telecom.TelecomSystem;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothPhoneService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        IBinder binder;
        synchronized (getTelecomSystem().getLock()) {
            binder = getTelecomSystem().getBluetoothPhoneServiceImpl().getBinder();
        }
        return binder;
    }

    public TelecomSystem getTelecomSystem() {
        return TelecomSystem.getInstance();
    }
}
