package android.hardware.bydauto.whud;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsBYDAutoWhudListener implements IBYDAutoListener {
    private static final String TAG = "AbsBYDAutoWhudListener";

    public AbsBYDAutoWhudListener() {
        Log.v(TAG, TAG);
    }

    public void onAdasWorkChanged(int i) {
        Log.v(TAG, "onAdasWorkChanged");
    }

    public void onVehicleSpaceChanged(int i) {
        Log.v(TAG, "onVehicleSpaceChanged");
    }

    public void onAheadVehicleSpaceChanged(int i) {
        Log.v(TAG, "onAheadVehicleSpaceChanged");
    }

    public void onTextHintChanged(int i) {
        Log.v(TAG, "onTextHintChanged");
    }

    public void onVehicleBodyPositionChanged(int i) {
        Log.v(TAG, "onVehicleBodyPositionChanged");
    }

    public void onLeftLineChanged(int i) {
        Log.v(TAG, "onLeftLineChanged");
    }

    public void onRightLineChanged(int i) {
        Log.v(TAG, "onRightLineChanged");
    }

    public void onAccCruisingSpeedValueChanged(int i) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onTrafficSignRecognitionValueChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognitionValueChanged");
    }

    public void onAccCruisingLampChanged(int i) {
        Log.v(TAG, "onAccCruisingLampChanged");
    }

    public void onAccCruisingLampColorChanged(int i) {
        Log.v(TAG, "onAccCruisingLampColorChanged");
    }

    public void onTextMessageHintChanged(int i) {
        Log.v(TAG, "onTextMessageHintChanged");
    }
}
