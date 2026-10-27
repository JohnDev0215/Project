package android.hardware.bydauto.vehicledata;

import android.hardware.bydauto.BYDAutoEventValue;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleDataListener implements d {
    protected static final String TAG = "AbsBYDAutoVehicleDataListener";

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i, String str) {
    }

    public void onEventDataAcqReceived(byte[] bArr) {
    }

    public void onPeriodDataAcqReceived(byte[] bArr) {
    }

    public void onVehicleDataAcqReceived(byte[] bArr) {
    }
}
