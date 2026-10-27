package android.hardware.bydauto.gb;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGBListener implements d {
    protected static final String TAG = "AbsBYDAutoGBListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i2, String str) {
    }

    public void onNeedRendRegisterTable(int i2) {
    }

    public void onTempMeterPercentAlarmChanged(int i2) {
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
