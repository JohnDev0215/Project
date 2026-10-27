package android.hardware.bydauto.gb;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGBListener implements d {
    protected static final String TAG = "AbsBYDAutoGBListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onNeedRendRegisterTable(int i7) {
    }

    public void onTempMeterPercentAlarmChanged(int i7) {
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
