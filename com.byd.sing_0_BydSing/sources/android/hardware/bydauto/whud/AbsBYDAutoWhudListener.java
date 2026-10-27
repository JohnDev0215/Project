package android.hardware.bydauto.whud;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWhudListener implements d {
    private static final String TAG = "AbsBYDAutoWhudListener";

    public AbsBYDAutoWhudListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAccCruisingLampChanged(int i) {
        Log.v(TAG, "onAccCruisingLampChanged");
    }

    public void onAccCruisingLampColorChanged(int i) {
        Log.v(TAG, "onAccCruisingLampColorChanged");
    }

    public void onAccCruisingSpeedValueChanged(int i) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onAdasWorkChanged(int i) {
        Log.v(TAG, "onAdasWorkChanged");
    }

    public void onAheadVehicleSpaceChanged(int i) {
        Log.v(TAG, "onAheadVehicleSpaceChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    public abstract /* synthetic */ void onError(int i, String str);

    public void onLeftLineChanged(int i) {
        Log.v(TAG, "onLeftLineChanged");
    }

    public void onRightLineChanged(int i) {
        Log.v(TAG, "onRightLineChanged");
    }

    public void onTextHintChanged(int i) {
        Log.v(TAG, "onTextHintChanged");
    }

    public void onTextMessageHintChanged(int i) {
        Log.v(TAG, "onTextMessageHintChanged");
    }

    public void onTrafficSignRecognitionValueChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognitionValueChanged");
    }

    public void onVehicleBodyPositionChanged(int i) {
        Log.v(TAG, "onVehicleBodyPositionChanged");
    }

    public void onVehicleSpaceChanged(int i) {
        Log.v(TAG, "onVehicleSpaceChanged");
    }
}
