package android.hardware.bydauto.light;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements d {
    protected static final String TAG = "AbsBYDAutoLightListener";

    public AbsBYDAutoLightListener() {
        Log.v(TAG, TAG);
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAFSSwitchStateChange(int i7) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAmbRingBrightnessChanged(int i7) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onAmbRingColorChanged(int i7) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbientFeedBackChanged(int i7) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i7, int i10) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i7 + " value = " + i10);
    }

    public void onAmbientMulticolorStateChanged(int i7, int i10) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i7 + " value = " + i10);
    }

    public void onAmbientMusicModeState(int i7, int i10) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i7 + " state = " + i10);
    }

    public void onAmbientMusicModechanged(int i7, int i10) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i7 + " value = " + i10);
    }

    public void onBacklightLevelsChanged(int i7) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayRunningLightStateChanged(int i7) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onDayTimeLightChanged(int i7) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDayTimeRunningLitghtStateChanged(int i7) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onDoorLightStateChanged(int i7) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i7) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onGroupHeadlightStateChanged(int i7, int i10) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onIALBrightnessChanged(int i7, int i10) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i7, int i10) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIlluminationIntensityLevelChange(int i7) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onLightAdbStateChanged(int i7, int i10) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightOff(int i7) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i7) {
        Log.v(TAG, "onLightOn");
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i7) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i7) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i7) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onStopLightStateChanged(int i7) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onTurnLightFlashState(int i7) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onTurnLightFlashStateChanged(int i7) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    public void onTurnLightStateChanged(int i7, int i10) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void setTurnLightsFlashState(int i7) {
        Log.v(TAG, "setTurnLightsFlashState");
    }

    public void onTurnLightStateChanged(int i7) {
        Log.v(TAG, "onTurnLightStateChanged");
    }
}
