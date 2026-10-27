package android.hardware.bydauto.whud;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWhudListener implements IBYDAutoListener {
    private static final String TAG = "AbsBYDAutoWhudListener";

    public AbsBYDAutoWhudListener() {
        Log.v(TAG, TAG);
    }

    public void onAdasWorkChanged(int state) {
        Log.v(TAG, "onAdasWorkChanged");
    }

    public void onVehicleSpaceChanged(int value) {
        Log.v(TAG, "onVehicleSpaceChanged");
    }

    public void onAheadVehicleSpaceChanged(int value) {
        Log.v(TAG, "onAheadVehicleSpaceChanged");
    }

    public void onTextHintChanged(int value) {
        Log.v(TAG, "onTextHintChanged");
    }

    public void onVehicleBodyPositionChanged(int value) {
        Log.v(TAG, "onVehicleBodyPositionChanged");
    }

    public void onLeftLineChanged(int value) {
        Log.v(TAG, "onLeftLineChanged");
    }

    public void onRightLineChanged(int value) {
        Log.v(TAG, "onRightLineChanged");
    }

    public void onAccCruisingSpeedValueChanged(int value) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onTrafficSignRecognitionValueChanged(int value) {
        Log.v(TAG, "onTrafficSignRecognitionValueChanged");
    }

    public void onAccCruisingLampChanged(int value) {
        Log.v(TAG, "onAccCruisingLampChanged");
    }

    public void onAccCruisingLampColorChanged(int value) {
        Log.v(TAG, "onAccCruisingLampColorChanged");
    }

    public void onTextMessageHintChanged(int value) {
        Log.v(TAG, "onTextMessageHintChanged");
    }
}
