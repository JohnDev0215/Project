package android.hardware.bydauto.vehicledata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoVehicleDataListener";

    public void onVehicleDataAcqReceived(byte[] vehicleData) {
    }

    public void onEventDataAcqReceived(byte[] eventData) {
    }

    public void onPeriodDataAcqReceived(byte[] periodData) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }
}
