package android.hardware.bydauto.light;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements d {
    public static final String TAG = "AbsBYDAutoLightListener";

    public AbsBYDAutoLightListener() {
        Log.v(TAG, TAG);
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAFSSwitchStateChange(int i3) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAllWeatherLightSwitchStateChanged(int i3) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + i3);
    }

    public void onAmbRingBrightnessChanged(int i3) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onAmbRingColorChanged(int i3) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbientFeedBackChanged(int i3) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i3 + " value = " + i4);
    }

    public void onAmbientMulticolorStateChanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i3 + " value = " + i4);
    }

    public void onAmbientMusicModeState(int i3, int i4) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i3 + " state = " + i4);
    }

    public void onAmbientMusicModechanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i3 + " value = " + i4);
    }

    public void onBacklightLevelsChanged(int i3) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onCargoLightSwitchStateChanged(int i3) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayRunningLightStateChanged(int i3) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onDayTimeLightChanged(int i3) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDayTimeRunningLitghtStateChanged(int i3) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onDoorLightStateChanged(int i3) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i3) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onFrontCenterLightStatusChanged(int i3) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int i3) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onFrontFogLightSwitchStateChanged(int i3) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + i3);
    }

    public void onGroupHeadlightStateChanged(int i3, int i4) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onHeadlightControlModeChanged(int i3) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + i3);
    }

    public void onHightlightPositionChanged(int i3) {
        Log.v(TAG, "onHightlightPositionChanged");
    }

    public void onIALBrightnessChanged(int i3, int i4) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i3, int i4) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIlluminationIntensityLevelChange(int i3) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onLeftCornerLightStatusChanged(int i3) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onLeftDaytimeLightStateChanged(int i3) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onLeftDaytimeRunLampStateChanged(int i3) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + i3);
    }

    public void onLeftFrontPositionSWLStatusChanged(int i3) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int i3) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i3) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i3) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onLeftRearRLightSwitchChanged(int i3) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onLeftRemoteLightStateChanged(int i3) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
    }

    public void onLightAdbStateChanged(int i3, int i4) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightOff(int i3) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i3) {
        Log.v(TAG, "onLightOn");
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i3) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRearFogLightSwitchStateChanged(int i3) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + i3);
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i3) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i3) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onRightCornerLightStatusChanged(int i3) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onRightDaytimeLightStateChanged(int i3) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onRightDaytimeRunLampStateChanged(int i3) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int i3) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int i3) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int i3) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onRightMiddleRowRLightSwitch(int i3) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onRightRearRLightSwitchChanged(int i3) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onRightRemoteLightStateChanged(int i3) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i3) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onStopLightStateChanged(int i3) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onTurnLightFlashState(int i3) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onTurnLightFlashStateChanged(int i3) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    public void onTurnLightStateChanged(int i3, int i4) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void setTurnLightsFlashState(int i3) {
        Log.v(TAG, "setTurnLightsFlashState");
    }

    public void onTurnLightStateChanged(int i3) {
        Log.v(TAG, "onTurnLightStateChanged");
    }
}
