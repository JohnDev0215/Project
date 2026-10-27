package android.hardware.bydauto.key;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onIdlerWheel(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onKeyClick(int i, int i2) {
    }
}
