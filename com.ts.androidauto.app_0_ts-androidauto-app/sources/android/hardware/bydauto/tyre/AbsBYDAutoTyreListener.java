package android.hardware.bydauto.tyre;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    public void onTyreSystemStateChanged(int state) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int state) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreBatteryStateChanged(int state) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreAirLeakStateChanged(int area, int state) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreSignalStateChanged(int area, int state) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyrePressureStateChanged(int area, int state) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueChanged(int area, int value) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyreBatteryValueChanged(int area, float value) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyreTurnStateChanged(int state) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyreSystemStateChanged(int state) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onTyrePressureWarnChanged(int area, int value) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onIndirectTyrePressureStateChanged(int area, int value) {
        Log.v(TAG, "onIndirectTyrePressureStateChanged");
    }

    public void onTyrePressureValueByTypeChanged(int area, float value) {
        Log.v(TAG, "onTyrePressureValueByTypeChanged");
    }

    public void onTyreColorByTypeChanged(int area, int value) {
        Log.v(TAG, "onTyreColorByTypeChanged");
    }

    public void onTyreTemperatureValueChanged(int area, int value) {
        Log.v(TAG, "onTyreTemperatureValueChanged area: " + area + " value: " + value + AndroidAutoConst.DEFAULT_SCREEN_NAME);
    }

    public void onSystemUndervoltageStatusChanged(int state) {
        Log.v(TAG, "onSystemUndervoltageStatusChanged state: " + state);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }
}
