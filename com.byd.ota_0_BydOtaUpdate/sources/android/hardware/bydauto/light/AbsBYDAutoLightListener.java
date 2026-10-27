package android.hardware.bydauto.light;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements d {
    public static final String TAG = "AbsBYDAutoLightListener";

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

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAFSSwitchStateChange(int i2) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onAllWeatherLightSwitchStateChanged(int i2) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + i2);
    }

    public void onAmbRingBrightnessChanged(int i2) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onAmbRingColorChanged(int i2) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbientFeedBackChanged(int i2) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i2, int i3) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i2 + " value = " + i3);
    }

    public void onAmbientMulticolorStateChanged(int i2, int i3) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i2 + " value = " + i3);
    }

    public void onAmbientMusicModeState(int i2, int i3) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i2 + " state = " + i3);
    }

    public void onAmbientMusicModechanged(int i2, int i3) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i2 + " value = " + i3);
    }

    public void onAmbientStateChanged(int i2) {
        Log.v(TAG, "onAmbientStateChanged state is " + i2);
    }

    public void onAtmospereLampPopupPrompt(int i2) {
        Log.v(TAG, "onAtmospereLampPopupPrompt state is " + i2);
    }

    public void onAtmospereLampUiDisplayChanged(int i2) {
        Log.v(TAG, "onAtmospereLampUiDisplayChanged state is " + i2);
    }

    public void onAtmospereLightNightWeakenModeChanged(int i2) {
        Log.v(TAG, "onAtmospereLightNightWeakenModeChanged state is " + i2);
    }

    public void onBacklightLevelsChanged(int i2) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onCargoLightSwitchStateChanged(int i2) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayRunningLightStateChanged(int i2) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onDayTimeLightChanged(int i2) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDayTimeRunningLitghtStateChanged(int i2) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onDoorLightStateChanged(int i2) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i2) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onFrontCenterLightStatusChanged(int i2) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int i2) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onFrontFogLightSwitchStateChanged(int i2) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + i2);
    }

    public void onGroupHeadlightStateChanged(int i2, int i3) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onHeadlightControlModeChanged(int i2) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + i2);
    }

    public void onHightlightPositionChanged(int i2) {
        Log.v(TAG, "onHightlightPositionChanged");
    }

    public void onIALBrightnessChanged(int i2, int i3) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i2, int i3) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIlluminationIntensityLevelChange(int i2) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onLeftCornerLightStatusChanged(int i2) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onLeftDaytimeLightStateChanged(int i2) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onLeftDaytimeRunLampStateChanged(int i2) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + i2);
    }

    public void onLeftFrontPositionSWLStatusChanged(int i2) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int i2) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i2) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i2) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onLeftRearRLightSwitchChanged(int i2) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onLeftRemoteLightStateChanged(int i2) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
    }

    public void onLightAdasIndicatorStatus(int i2) {
        Log.v(TAG, "onLightAdasIndicatorStatus state is " + i2);
    }

    public void onLightAdbStateChanged(int i2, int i3) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightFrontGlowLogoWorkState(int i2) {
        Log.v(TAG, "onLightFrontGlowLogoWorkState state is " + i2);
    }

    public void onLightOff(int i2) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i2) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightRearGlowLogoWorkStateChanged(int i2) {
        Log.v(TAG, "onLightRearGlowLogoWorkStateChanged state is " + i2);
    }

    public void onMiddleSternLampStateChanged(int i2) {
        Log.v(TAG, "onMiddleSternLampStateChanged state is " + i2);
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i2) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRearFogLightSwitchStateChanged(int i2) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + i2);
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i2) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onReversingLightStateChanged(int i2) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onRightCornerLightStatusChanged(int i2) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onRightDaytimeLightStateChanged(int i2) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onRightDaytimeRunLampStateChanged(int i2) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int i2) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int i2) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int i2) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onRightMiddleRowRLightSwitch(int i2) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onRightRearRLightSwitchChanged(int i2) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onRightRemoteLightStateChanged(int i2) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i2) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onStopLightStateChanged(int i2) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onThemeColorFeedBackChanged(int i2) {
        Log.v(TAG, "onThemeColorFeedBackChanged state is " + i2);
    }

    public void onThemeColorSwitchState(int i2) {
        Log.v(TAG, "onThemeColorSwitchState state is " + i2);
    }

    public void onTurnLightFlashState(int i2) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onTurnLightFlashStateChanged(int i2) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    public void onTurnLightStateChanged(int i2, int i3) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void setTurnLightsFlashState(int i2) {
        Log.v(TAG, "setTurnLightsFlashState");
    }

    public void onTurnLightStateChanged(int i2) {
        Log.v(TAG, "onTurnLightStateChanged");
    }
}
