package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.telephony.SmsManager;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    public void onRadioStateChanged(int i) {
        Log.d(TAG, "onRadioStateChanged Listener " + i);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onRadioParamChanged Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + i2 + SmsManager.REGEX_PREFIX_DELIMITER + i3 + SmsManager.REGEX_PREFIX_DELIMITER + i4);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        Log.d(TAG, "onCurFreqChanged Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + i2 + SmsManager.REGEX_PREFIX_DELIMITER + i3);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + i2 + SmsManager.REGEX_PREFIX_DELIMITER + i3 + SmsManager.REGEX_PREFIX_DELIMITER + i4);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onSearchResultChanged Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + i3 + SmsManager.REGEX_PREFIX_DELIMITER + i3 + SmsManager.REGEX_PREFIX_DELIMITER + i4);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + str);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged Listener" + iBYDAutoEvent);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + SmsManager.REGEX_PREFIX_DELIMITER + i);
    }
}
