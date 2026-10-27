package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Objects.toString(iBYDAutoEvent);
    }

    public void onRadioStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
    }
}
