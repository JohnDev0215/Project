package android.hardware.bydauto.light;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoLightListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoLightListener";

    public void onTurnLightStateChanged(int i) {
    }

    public void onTurnLightStateChanged(int i, int i2) {
    }

    public void getTurnLightFlashState() {
    }

    public void onLightAutoSwitchOff() {
    }

    public void onLightAutoSwitchOn() {
    }

    public void onAFSSwitchStateChange(int i) {
    }

    public void onAmbRingBrightnessChanged(int i) {
    }

    public void onAmbRingColorChanged(int i) {
    }

    public void onAmbientFeedBackChanged(int i) {
    }

    public void onBacklightLevelsChanged(int i) {
    }

    public void onCargoLightSwitchStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDayRunningLightStateChanged(int i) {
    }

    public void onDayTimeLightChanged(int i) {
    }

    public void onDayTimeRunningLitghtStateChanged(int i) {
    }

    public void onDoorLightStateChanged(int i) {
    }

    public void onDoubleFlashLightStateChanged(int i) {
    }

    public void onFrontCenterLightStatusChanged(int i) {
    }

    public void onFrontFogLampStatusChanged(int i) {
    }

    public void onHightlightPositionChanged(int i) {
    }

    public void onIlluminationIntensityLevelChange(int i) {
    }

    public void onLeftCornerLightStatusChanged(int i) {
    }

    public void onLeftDaytimeLightStateChanged(int i) {
    }

    public void onLeftFrontPositionSWLStatusChanged(int i) {
    }

    public void onLeftFrontReadingLampStatusChanged(int i) {
    }

    public void onLeftMiddleRowRLightRotaryStateChanged(int i) {
    }

    public void onLeftMiddleRowSideRLightSwitchChanged(int i) {
    }

    public void onLeftRearRLightSwitchChanged(int i) {
    }

    public void onLeftRemoteLightStateChanged(int i) {
    }

    public void onLightOff(int i) {
    }

    public void onLightOn(int i) {
    }

    public void onPositionLightDisplayFeedCallbackChanged(int i) {
    }

    @Deprecated
    public void onRearLampLightStateChanged(int i) {
    }

    public void onReversingLightStateChanged(int i) {
    }

    public void onRightCornerLightStatusChanged(int i) {
    }

    public void onRightDaytimeLightStateChanged(int i) {
    }

    public void onRightFrontPositionSWLStatusChanged(int i) {
    }

    public void onRightFrontReadingLampStatusChanged(int i) {
    }

    public void onRightMiddleRowRLightRotaryChanged(int i) {
    }

    public void onRightMiddleRowRLightSwitch(int i) {
    }

    public void onRightRearRLightSwitchChanged(int i) {
    }

    public void onRightRemoteLightStateChanged(int i) {
    }

    public void onSequentialLightStateChanged(int i) {
    }

    public void onStopLightStateChanged(int i) {
    }

    public void onTurnLightFlashState(int i) {
    }

    public void onTurnLightFlashStateChanged(int i) {
    }

    public void setTurnLightsFlashState(int i) {
    }

    public void onAmbientMulticolorModeChanged(int i, int i2) {
    }

    public void onAmbientMulticolorStateChanged(int i, int i2) {
    }

    public void onAmbientMusicModeState(int i, int i2) {
    }

    public void onAmbientMusicModechanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onGroupHeadlightStateChanged(int i, int i2) {
    }

    public void onIALBrightnessChanged(int i, int i2) {
    }

    public void onIALColorChanged(int i, int i2) {
    }

    public void onLightAdbStateChanged(int i, int i2) {
    }
}
