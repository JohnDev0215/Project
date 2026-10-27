package android.hardware.bydauto.light;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements e {
    public static final String TAG = "AbsBYDAutoLightListener";

    public AbsBYDAutoLightListener() {
        Log.v(TAG, TAG);
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAFSSwitchStateChange(int i) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAllWeatherLightSwitchStateChanged(int i) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + i);
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

    public void onAmbientStateChanged(int i) {
        Log.v(TAG, "onAmbientStateChanged state is " + i);
    }

    public void onAtmospereLampPopupPrompt(int i) {
        Log.v(TAG, "onAtmospereLampPopupPrompt state is " + i);
    }

    public void onAtmospereLampUiDisplayChanged(int i) {
        Log.v(TAG, "onAtmospereLampUiDisplayChanged state is " + i);
    }

    public void onAtmospereLightNightWeakenModeChanged(int i) {
        Log.v(TAG, "onAtmospereLightNightWeakenModeChanged state is " + i);
    }

    public void onBacklightLevelsChanged(int i) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onCargoLightSwitchStateChanged(int i) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public final void onDataChanged(d dVar) {
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

    public void onFrontFogLightSwitchStateChanged(int i) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + i);
    }

    public void onGroupHeadlightStateChanged(int i, int i2) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onHeadlightControlModeChanged(int i) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + i);
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

    public void onLeftDaytimeRunLampStateChanged(int i) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + i);
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

    public void onLightAdasIndicatorStatus(int i) {
        Log.v(TAG, "onLightAdasIndicatorStatus state is " + i);
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

    public void onLightFrontGlowLogoWorkState(int i) {
        Log.v(TAG, "onLightFrontGlowLogoWorkState state is " + i);
    }

    public void onLightOff(int i) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightRearGlowLogoWorkStateChanged(int i) {
        Log.v(TAG, "onLightRearGlowLogoWorkStateChanged state is " + i);
    }

    public void onMiddleSternLampStateChanged(int i) {
        Log.v(TAG, "onMiddleSternLampStateChanged state is " + i);
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRearFogLightSwitchStateChanged(int i) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + i);
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

    public void onRightDaytimeRunLampStateChanged(int i) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
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

    public void onThemeColorFeedBackChanged(int i) {
        Log.v(TAG, "onThemeColorFeedBackChanged state is " + i);
    }

    public void onThemeColorSwitchState(int i) {
        Log.v(TAG, "onThemeColorSwitchState state is " + i);
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
