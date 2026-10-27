package android.hardware.bydauto.vehicledata;

import android.hardware.bydauto.BYDAutoEventValue;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleDataListener implements e {
    public static final String TAG = "AbsBYDAutoVehicleDataListener";

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(d dVar) {
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
