package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    public void onRadioStateChanged(int state) {
        Log.d(TAG, "onRadioStateChanged Listener " + state);
    }

    public void onRadioParamChanged(int band, int start, int end, int step) {
        Log.d(TAG, "onRadioParamChanged Listener" + band + "," + start + "," + end + "," + step);
    }

    public void onCurFreqChanged(int radioState, int band, int frequency) {
        Log.d(TAG, "onCurFreqChanged Listener" + radioState + "," + band + "," + frequency);
    }

    public void onEffectiveFreqSearched(int radioState, int band, int searchProcessState, int frequency) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + radioState + "," + band + "," + searchProcessState + "," + frequency);
    }

    public void onSearchResultChanged(int band, int frequency, int isEffective, int searchProcessState) {
        Log.d(TAG, "onSearchResultChanged Listener" + band + "," + isEffective + "," + isEffective + "," + searchProcessState);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.d(TAG, "onError Listener" + errCode + "," + errMessage);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.d(TAG, "onDataChanged Listener" + event);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + eventType + "," + eventType);
    }
}
