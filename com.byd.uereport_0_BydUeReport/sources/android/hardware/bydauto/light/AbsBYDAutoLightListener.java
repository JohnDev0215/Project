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
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAFSSwitchStateChange(int i8) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAllWeatherLightSwitchStateChanged(int i8) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + i8);
    }

    public void onAmbRingBrightnessChanged(int i8) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onAmbRingColorChanged(int i8) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbientFeedBackChanged(int i8) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i8 + " value = " + i9);
    }

    public void onAmbientMulticolorStateChanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i8 + " value = " + i9);
    }

    public void onAmbientMusicModeState(int i8, int i9) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i8 + " state = " + i9);
    }

    public void onAmbientMusicModechanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i8 + " value = " + i9);
    }

    public void onAmbientStateChanged(int i8) {
        Log.v(TAG, "onAmbientStateChanged state is " + i8);
    }

    public void onAtmospereLampPopupPrompt(int i8) {
        Log.v(TAG, "onAtmospereLampPopupPrompt state is " + i8);
    }

    public void onAtmospereLampUiDisplayChanged(int i8) {
        Log.v(TAG, "onAtmospereLampUiDisplayChanged state is " + i8);
    }

    public void onAtmospereLightNightWeakenModeChanged(int i8) {
        Log.v(TAG, "onAtmospereLightNightWeakenModeChanged state is " + i8);
    }

    public void onBacklightLevelsChanged(int i8) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onCargoLightSwitchStateChanged(int i8) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayRunningLightStateChanged(int i8) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onDayTimeLightChanged(int i8) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDayTimeRunningLitghtStateChanged(int i8) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onDoorLightStateChanged(int i8) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i8) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onFrontCenterLightStatusChanged(int i8) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int i8) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onFrontFogLightSwitchStateChanged(int i8) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + i8);
    }

    public void onGroupHeadlightStateChanged(int i8, int i9) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onHeadlightControlModeChanged(int i8) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + i8);
    }

    public void onHightlightPositionChanged(int i8) {
        Log.v(TAG, "onHightlightPositionChanged");
    }

    public void onIALBrightnessChanged(int i8, int i9) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i8, int i9) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIlluminationIntensityLevelChange(int i8) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onLeftCornerLightStatusChanged(int i8) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onLeftDaytimeLightStateChanged(int i8) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onLeftDaytimeRunLampStateChanged(int i8) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + i8);
    }

    public void onLeftFrontPositionSWLStatusChanged(int i8) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int i8) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i8) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i8) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onLeftRearRLightSwitchChanged(int i8) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onLeftRemoteLightStateChanged(int i8) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
    }

    public void onLightAdasIndicatorStatus(int i8) {
        Log.v(TAG, "onLightAdasIndicatorStatus state is " + i8);
    }

    public void onLightAdbStateChanged(int i8, int i9) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightFrontGlowLogoWorkState(int i8) {
        Log.v(TAG, "onLightFrontGlowLogoWorkState state is " + i8);
    }

    public void onLightOff(int i8) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i8) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightRearGlowLogoWorkStateChanged(int i8) {
        Log.v(TAG, "onLightRearGlowLogoWorkStateChanged state is " + i8);
    }

    public void onMiddleSternLampStateChanged(int i8) {
        Log.v(TAG, "onMiddleSternLampStateChanged state is " + i8);
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i8) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRearFogLightSwitchStateChanged(int i8) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + i8);
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i8) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i8) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onRightCornerLightStatusChanged(int i8) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onRightDaytimeLightStateChanged(int i8) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onRightDaytimeRunLampStateChanged(int i8) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int i8) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int i8) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int i8) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onRightMiddleRowRLightSwitch(int i8) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onRightRearRLightSwitchChanged(int i8) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onRightRemoteLightStateChanged(int i8) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i8) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onStopLightStateChanged(int i8) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onThemeColorFeedBackChanged(int i8) {
        Log.v(TAG, "onThemeColorFeedBackChanged state is " + i8);
    }

    public void onThemeColorSwitchState(int i8) {
        Log.v(TAG, "onThemeColorSwitchState state is " + i8);
    }

    public void onTurnLightFlashState(int i8) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onTurnLightFlashStateChanged(int i8) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    public void onTurnLightStateChanged(int i8, int i9) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void setTurnLightsFlashState(int i8) {
        Log.v(TAG, "setTurnLightsFlashState");
    }

    public void onTurnLightStateChanged(int i8) {
        Log.v(TAG, "onTurnLightStateChanged");
    }
}
