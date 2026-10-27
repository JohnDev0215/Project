package android.hardware.bydauto.light;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements d {
    protected static final String TAG = "AbsBYDAutoLightListener";

    public AbsBYDAutoLightListener() {
        Log.v(TAG, TAG);
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onAFSSwitchStateChange(int i) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAmbRingBrightnessChanged(int i) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onAmbRingColorChanged(int i) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbientFeedBackChanged(int i) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorModeChanged");
    }

    public void onAmbientMulticolorStateChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorStateChanged");
    }

    public void onAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModeState");
    }

    public void onAmbientMusicModechanged(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModechanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayRunningLightStateChanged(int i) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onDayTimeLightChanged(int i) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDayTimeRunningLitghtStateChanged(int i) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onDoorLightStateChanged(int i) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onGroupHeadlightStateChanged(int i, int i2) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onIALBrightnessChanged(int i, int i2) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i, int i2) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIlluminationIntensityLevelChange(int i) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onLightAdbStateChanged(int i, int i2) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightOff(int i) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i) {
        Log.v(TAG, "onLightOn");
    }

    public void onRearLampLightStateChanged(int i) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onStopLightStateChanged(int i) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onTurnLightFlashState(int i) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onTurnLightFlashStateChanged(int i) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    public void onTurnLightStateChanged(int i, int i2) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void setTurnLightsFlashState(int i) {
        Log.v(TAG, "setTurnLightsFlashState");
    }

    public void onTurnLightStateChanged(int i) {
        Log.v(TAG, "onTurnLightStateChanged");
    }
}
