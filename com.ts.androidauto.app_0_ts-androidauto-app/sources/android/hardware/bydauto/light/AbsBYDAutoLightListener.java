package android.hardware.bydauto.light;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto_test.TestLightManagerImplCallback;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoLightListener";

    public AbsBYDAutoLightListener() {
        Log.v(TAG, TAG);
    }

    public void onLightOn(int type) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightOff(int type) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onAFSSwitchStateChange(int state) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onDayTimeRunningLitghtStateChanged(int state) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onIlluminationIntensityLevelChange(int value) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onGroupHeadlightStateChanged(int area, int state) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onStopLightStateChanged(int state) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onDayRunningLightStateChanged(int state) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onSequentialLightStateChanged(int state) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int state) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onReversingLightStateChanged(int state) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onLightAdbStateChanged(int id, int state) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onTurnLightStateChanged(int id, int value) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void onTurnLightStateChanged(int value) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void onTurnLightFlashStateChanged(int value) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
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

    public void onAmbientMusicModeState(int area, int state) {
        Log.v(TAG, "onAmbientMusicModeState area = " + area + " state = " + state);
    }

    public void onAmbientMusicModechanged(int area, int value) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + area + " value = " + value);
    }

    public void onAmbientMulticolorStateChanged(int area, int value) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + area + " value = " + value);
    }

    public void onAmbientMulticolorModeChanged(int area, int value) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + area + " value = " + value);
    }

    public void onDayTimeLightChanged(int state) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDoorLightStateChanged(int state) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    public void setTurnLightsFlashState(int state) {
        Log.v(TAG, TestLightManagerImplCallback.SETTURNLIGHTSFLASHSTATE);
    }

    public void onTurnLightFlashState(int state) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onAmbientFeedBackChanged(int state) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onIALColorChanged(int area, int source) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIALBrightnessChanged(int area, int source) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    @Deprecated
    public void onRearLampLightStateChanged(int state) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onAmbRingColorChanged(int color) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbRingBrightnessChanged(int color) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onBacklightLevelsChanged(int level) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int state) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int state) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int state) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onRightMiddleRowRLightSwitch(int state) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int state) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int state) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onLeftRearRLightSwitchChanged(int state) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onRightRearRLightSwitchChanged(int state) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onCargoLightSwitchStateChanged(int state) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public void onLeftCornerLightStatusChanged(int state) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onRightCornerLightStatusChanged(int state) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int state) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onFrontCenterLightStatusChanged(int state) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onPositionLightDisplayFeedCallbackChanged(int state) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRightDaytimeLightStateChanged(int state) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onLeftDaytimeLightStateChanged(int state) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onHightlightPositionChanged(int state) {
        Log.v(TAG, "onHightlightPositionChanged");
    }

    public void onLeftFrontPositionSWLStatusChanged(int state) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int state) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onLeftRemoteLightStateChanged(int state) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
    }

    public void onRightRemoteLightStateChanged(int state) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
    }

    public void onLeftDaytimeRunLampStateChanged(int state) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + state);
    }

    public void onRightDaytimeRunLampStateChanged(int state) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
    }

    public void onAllWeatherLightSwitchStateChanged(int state) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + state);
    }

    public void onRearFogLightSwitchStateChanged(int state) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + state);
    }

    public void onHeadlightControlModeChanged(int mode) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + mode);
    }

    public void onFrontFogLightSwitchStateChanged(int state) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + state);
    }

    public void onMiddleSternLampStateChanged(int state) {
        Log.v(TAG, "onMiddleSternLampStateChanged state is " + state);
    }

    public void onAmbientStateChanged(int state) {
        Log.v(TAG, "onAmbientStateChanged state is " + state);
    }

    public void onLightRearGlowLogoWorkStateChanged(int state) {
        Log.v(TAG, "onLightRearGlowLogoWorkStateChanged state is " + state);
    }

    public void onLightAdasIndicatorStatus(int status) {
        Log.v(TAG, "onLightAdasIndicatorStatus state is " + status);
    }

    public void onLightFrontGlowLogoWorkState(int state) {
        Log.v(TAG, "onLightFrontGlowLogoWorkState state is " + state);
    }

    public void onAtmospereLightNightWeakenModeChanged(int mode) {
        Log.v(TAG, "onAtmospereLightNightWeakenModeChanged state is " + mode);
    }

    public void onAtmospereLampPopupPrompt(int state) {
        Log.v(TAG, "onAtmospereLampPopupPrompt state is " + state);
    }

    public void onThemeColorSwitchState(int state) {
        Log.v(TAG, "onThemeColorSwitchState state is " + state);
    }

    public void onAtmospereLampUiDisplayChanged(int state) {
        Log.v(TAG, "onAtmospereLampUiDisplayChanged state is " + state);
    }

    public void onThemeColorFeedBackChanged(int color) {
        Log.v(TAG, "onThemeColorFeedBackChanged state is " + color);
    }
}
