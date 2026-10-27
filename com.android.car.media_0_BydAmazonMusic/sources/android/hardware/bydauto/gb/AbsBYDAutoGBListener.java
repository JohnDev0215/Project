package android.hardware.bydauto.gb;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGBListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoGBListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onNeedRendRegisterTable(int i) {
    }

    public void onTempMeterPercentAlarmChanged(int i) {
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
