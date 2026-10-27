package com.ts.carplay;

import android.os.RemoteException;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.lib.interfaces.ICarPlayCapabilityListener;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayCapabilityListenerImpl implements ICarPlayCapabilityListener {
    private IWirelessCarPlayAvailabilityCallback callback;

    public CarPlayCapabilityListenerImpl(IWirelessCarPlayAvailabilityCallback callback) {
        this.callback = callback;
    }

    @Override // com.ts.carplay.lib.interfaces.ICarPlayCapabilityListener
    public void onWirelessCarPlayAvailabilityChanged(String macAddress, boolean enabled) {
        try {
            this.callback.onWirelessCarPlayAvailabilityChanged(macAddress, enabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
