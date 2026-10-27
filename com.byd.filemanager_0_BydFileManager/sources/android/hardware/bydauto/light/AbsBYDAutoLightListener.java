package android.hardware.bydauto.light;

import a.b.c;
import a.b.d;
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

    @Override // a.b.d
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
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i + " value = " + i2);
    }

    public void onAmbientMulticolorStateChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i + " value = " + i2);
    }

    public void onAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i + " state = " + i2);
    }

    public void onAmbientMusicModechanged(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i + " value = " + i2);
    }

    public void onBacklightLevelsChanged(int i) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onCargoLightSwitchStateChanged(int i) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
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

    public void onFrontCenterLightStatusChanged(int i) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int i) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onGroupHeadlightStateChanged(int i, int i2) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onHightlightPositionChanged(int i) {
        Log.v(TAG, "onHightlightPositionChanged");
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

    public void onLeftCornerLightStatusChanged(int i) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onLeftDaytimeLightStateChanged(int i) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onLeftFrontPositionSWLStatusChanged(int i) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int i) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onLeftRearRLightSwitchChanged(int i) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onLeftRemoteLightStateChanged(int i) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
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

    public void onPositionLightDisplayFeedCallbackChanged(int i) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onRightCornerLightStatusChanged(int i) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onRightDaytimeLightStateChanged(int i) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int i) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int i) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int i) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onRightMiddleRowRLightSwitch(int i) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onRightRearRLightSwitchChanged(int i) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onRightRemoteLightStateChanged(int i) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
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
