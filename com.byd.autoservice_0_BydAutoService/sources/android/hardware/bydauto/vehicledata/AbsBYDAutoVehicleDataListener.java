package android.hardware.bydauto.vehicledata;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleDataListener implements d {
    public static final String TAG = "AbsBYDAutoVehicleDataListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i3, String str) {
    }

    public void onEventDataAcqReceived(byte[] bArr) {
    }

    public void onPeriodDataAcqReceived(byte[] bArr) {
    }

    public void onVehicleDataAcqReceived(byte[] bArr) {
    }
}
