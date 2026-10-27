package android.hardware.bydauto.whud;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWhudListener implements d {
    private static final String TAG = "AbsBYDAutoWhudListener";

    public AbsBYDAutoWhudListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAccCruisingLampChanged(int i7) {
        Log.v(TAG, "onAccCruisingLampChanged");
    }

    public void onAccCruisingLampColorChanged(int i7) {
        Log.v(TAG, "onAccCruisingLampColorChanged");
    }

    public void onAccCruisingSpeedValueChanged(int i7) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onAdasWorkChanged(int i7) {
        Log.v(TAG, "onAdasWorkChanged");
    }

    public void onAheadVehicleSpaceChanged(int i7) {
        Log.v(TAG, "onAheadVehicleSpaceChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue);

    public abstract /* synthetic */ void onError(int i7, String str);

    public void onLeftLineChanged(int i7) {
        Log.v(TAG, "onLeftLineChanged");
    }

    public void onRightLineChanged(int i7) {
        Log.v(TAG, "onRightLineChanged");
    }

    public void onTextHintChanged(int i7) {
        Log.v(TAG, "onTextHintChanged");
    }

    public void onTextMessageHintChanged(int i7) {
        Log.v(TAG, "onTextMessageHintChanged");
    }

    public void onTrafficSignRecognitionValueChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognitionValueChanged");
    }

    public void onVehicleBodyPositionChanged(int i7) {
        Log.v(TAG, "onVehicleBodyPositionChanged");
    }

    public void onVehicleSpaceChanged(int i7) {
        Log.v(TAG, "onVehicleSpaceChanged");
    }
}
