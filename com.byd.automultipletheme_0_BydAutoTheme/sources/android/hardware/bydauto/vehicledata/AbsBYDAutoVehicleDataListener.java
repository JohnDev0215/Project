package android.hardware.bydauto.vehicledata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoVehicleDataListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onEventDataAcqReceived(byte[] bArr) {
    }

    public void onPeriodDataAcqReceived(byte[] bArr) {
    }

    public void onVehicleDataAcqReceived(byte[] bArr) {
    }
}
