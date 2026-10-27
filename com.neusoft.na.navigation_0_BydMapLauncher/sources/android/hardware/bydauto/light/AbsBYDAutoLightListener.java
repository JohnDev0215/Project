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

    public void onLightOn(int i) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightOff(int i) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightAutoSwitchOn() {
        Log.v(TAG, "onLightAutoSwitchOn");
    }

    public void onLightAutoSwitchOff() {
        Log.v(TAG, "onLightAutoSwitchOff");
    }

    public void onAFSSwitchStateChange(int i) {
        Log.v(TAG, "onAFSSwitchStateChange");
    }

    public void onDayTimeRunningLitghtStateChanged(int i) {
        Log.v(TAG, "onDayTimeRunningLitghtStateChanged");
    }

    public void onIlluminationIntensityLevelChange(int i) {
        Log.v(TAG, "onIlluminationIntensityLevelChange");
    }

    public void onGroupHeadlightStateChanged(int i, int i2) {
        Log.v(TAG, "onGroupHeadlightStateChanged");
    }

    public void onStopLightStateChanged(int i) {
        Log.v(TAG, "onStopLightStateChanged");
    }

    public void onDayRunningLightStateChanged(int i) {
        Log.v(TAG, "onDayRunningLightStateChanged");
    }

    public void onSequentialLightStateChanged(int i) {
        Log.v(TAG, "onSequentialLightStateChanged");
    }

    public void onDoubleFlashLightStateChanged(int i) {
        Log.v(TAG, "onDoubleFlashLightStateChanged");
    }

    public void onReversingLightStateChanged(int i) {
        Log.v(TAG, "onReversingLightStateChanged");
    }

    public void onLightAdbStateChanged(int i, int i2) {
        Log.v(TAG, "onLightAdbStateChanged");
    }

    public void onTurnLightStateChanged(int i, int i2) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void onTurnLightStateChanged(int i) {
        Log.v(TAG, "onTurnLightStateChanged");
    }

    public void onTurnLightFlashStateChanged(int i) {
        Log.v(TAG, "onTurnLightFlashStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModeState area = " + i + " state = " + i2);
    }

    public void onAmbientMusicModechanged(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModechanged area = " + i + " value = " + i2);
    }

    public void onAmbientMulticolorStateChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorStateChanged area = " + i + " value = " + i2);
    }

    public void onAmbientMulticolorModeChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorModeChanged area = " + i + " value = " + i2);
    }

    public void onDayTimeLightChanged(int i) {
        Log.v(TAG, "onDayTimeLightChanged");
    }

    public void onDoorLightStateChanged(int i) {
        Log.v(TAG, "onDoorLightStateChanged");
    }

    public void getTurnLightFlashState() {
        Log.v(TAG, "getTurnLightFlashState");
    }

    public void setTurnLightsFlashState(int i) {
        Log.v(TAG, TestLightManagerImplCallback.SETTURNLIGHTSFLASHSTATE);
    }

    public void onTurnLightFlashState(int i) {
        Log.v(TAG, "onTurnLightFlashState");
    }

    public void onAmbientFeedBackChanged(int i) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onIALColorChanged(int i, int i2) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIALBrightnessChanged(int i, int i2) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onAmbRingColorChanged(int i) {
        Log.v(TAG, "onAmbRingColorChanged");
    }

    public void onAmbRingBrightnessChanged(int i) {
        Log.v(TAG, "onAmbRingBrightnessChanged");
    }

    public void onBacklightLevelsChanged(int i) {
        Log.v(TAG, "onBacklightLevelsChanged");
    }

    public void onLeftFrontReadingLampStatusChanged(int i) {
        Log.v(TAG, "onLeftFrontReadingLampStatusChanged");
    }

    public void onRightFrontReadingLampStatusChanged(int i) {
        Log.v(TAG, "onRightFrontReadingLampStatusChanged");
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i) {
        Log.v(TAG, "onLeftMiddleRowSideRLightSwitchChanged");
    }

    public void onRightMiddleRowRLightSwitch(int i) {
        Log.v(TAG, "onRightMiddleRowRLightSwitch");
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i) {
        Log.v(TAG, "onLeftMiddleRowRLightRotaryStateChanged");
    }

    public void onRightMiddleRowRLightRotaryChanged(int i) {
        Log.v(TAG, "onRightMiddleRowRLightRotaryChanged");
    }

    public void onLeftRearRLightSwitchChanged(int i) {
        Log.v(TAG, "onLeftRearRLightSwitchChanged");
    }

    public void onRightRearRLightSwitchChanged(int i) {
        Log.v(TAG, "onRightRearRLightSwitchChanged");
    }

    public void onCargoLightSwitchStateChanged(int i) {
        Log.v(TAG, "onCargoLightSwitchStateChanged");
    }

    public void onLeftCornerLightStatusChanged(int i) {
        Log.v(TAG, "onLeftCornerLightStatusChanged");
    }

    public void onRightCornerLightStatusChanged(int i) {
        Log.v(TAG, "onRightCornerLightStatusChanged");
    }

    public void onFrontFogLampStatusChanged(int i) {
        Log.v(TAG, "onFrontFogLampStatusChanged");
    }

    public void onFrontCenterLightStatusChanged(int i) {
        Log.v(TAG, "onFrontCenterLightStatusChanged");
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i) {
        Log.v(TAG, "onPositionLightDisplayFeedCallbackChanged");
    }

    public void onRightDaytimeLightStateChanged(int i) {
        Log.v(TAG, "onRightDaytimeLightStateChanged");
    }

    public void onLeftDaytimeLightStateChanged(int i) {
        Log.v(TAG, "onLeftDaytimeLightStateChanged");
    }

    public void onHightlightPositionChanged(int i) {
        Log.v(TAG, "onHightlightPositionChanged");
    }

    public void onLeftFrontPositionSWLStatusChanged(int i) {
        Log.v(TAG, "onLeftFrontPositionSWLStatusChanged");
    }

    public void onRightFrontPositionSWLStatusChanged(int i) {
        Log.v(TAG, "onRightFrontPositionSWLStatusChanged");
    }

    public void onLeftRemoteLightStateChanged(int i) {
        Log.v(TAG, "onLeftRemoteLightStateChanged");
    }

    public void onRightRemoteLightStateChanged(int i) {
        Log.v(TAG, "onRightRemoteLightStateChanged");
    }

    public void onLeftDaytimeRunLampStateChanged(int i) {
        Log.v(TAG, "onLeftDaytimeRunLampStateChanged state: " + i);
    }

    public void onRightDaytimeRunLampStateChanged(int i) {
        Log.v(TAG, "onRightDaytimeRunLampStateChanged");
    }

    public void onAllWeatherLightSwitchStateChanged(int i) {
        Log.v(TAG, "onAllWeatherLightSwitchStateChanged state is " + i);
    }

    public void onRearFogLightSwitchStateChanged(int i) {
        Log.v(TAG, "onRearFogLightSwitchStateChanged state is " + i);
    }

    public void onHeadlightControlModeChanged(int i) {
        Log.v(TAG, "onHeadlightControlModeChanged state is " + i);
    }

    public void onFrontFogLightSwitchStateChanged(int i) {
        Log.v(TAG, "onFrontFogLightSwitchStateChanged state is " + i);
    }
}
