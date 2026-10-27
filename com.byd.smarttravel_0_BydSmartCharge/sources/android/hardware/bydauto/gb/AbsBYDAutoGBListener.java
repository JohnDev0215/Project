package android.hardware.bydauto.gb;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGBListener implements d {
    public static final String TAG = "AbsBYDAutoGBListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i, String str) {
    }

    public void onNeedRendRegisterTable(int i) {
    }

    public void onTempMeterPercentAlarmChanged(int i) {
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
