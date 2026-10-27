package android.hardware.bydauto.ac;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAcListener implements d {
    protected static final String TAG = "AbsBYDAutoAcListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void on3daOnlineStateChanged(int i7) {
        Log.v(TAG, "on3daOnlineStateChanged");
    }

    public void onACAssistModeTips(int i7) {
        Log.v(TAG, "onACAssistModeTips");
    }

    public void onACBTWindTips(int i7) {
        Log.v(TAG, "onACBTWindTips");
    }

    public void onACDefrostRearPanelNotAvailableTips(int i7) {
        Log.v(TAG, "onACDefrostRearPanelNotAvailableTips");
    }

    public void onACKeepEngineRunTips(int i7) {
        Log.v(TAG, "onACKeepEngineRunTips");
    }

    public void onACMaxEVModeTips(int i7) {
        Log.v(TAG, "onACMaxEVModeTips");
    }

    public void onACPreheatTips(int i7) {
        Log.v(TAG, "onACPreheatTips");
    }

    public void onACSystemStateChange(int i7) {
        Log.v(TAG, "onACSystemStateChange");
    }

    public void onACWorkModeChanged(int i7, int i8) {
        Log.v(TAG, "onACWorkModeChanged");
    }

    @Deprecated
    public void onAcAirQualityCtrlMenuStateChanged(int i7) {
        Log.v(TAG, "onAcAirQualityCtrlMenuStateChanged");
    }

    public void onAcCompressorManualSignChanged(int i7) {
        Log.v(TAG, "onAcCompressorManualSignChanged");
    }

    public void onAcCompressorModeChanged(int i7) {
        Log.v(TAG, "onAcCompressorModeChanged");
    }

    public void onAcCtrlModeChanged(int i7) {
        Log.v(TAG, "onAcCtrlModeChanged");
    }

    public void onAcCycleModeChanged(int i7) {
        Log.v(TAG, "onAcCycleModeChanged");
    }

    public void onAcDefrostOnlineStateChanged(int i7) {
        Log.v(TAG, "onAcDefrostOnlineStateChanged");
    }

    public void onAcDefrostStateChanged(int i7, int i8) {
        Log.v(TAG, "onAcDefrostStateChanged");
    }

    public void onAcFaultNumShownStateChanged(int i7) {
        Log.v(TAG, "onAcFaultNumShownStateChanged");
    }

    public void onAcKeyActionStateChanged(int i7) {
        Log.v(TAG, "onAcKeyActionStateChanged");
    }

    public void onAcMaxCoolingStateChanged(int i7) {
        Log.v(TAG, "onAcMaxCoolingStateChanged");
    }

    public void onAcOnlineStateChanged(int i7) {
        Log.v(TAG, "message onAcOnlineStateChanged = " + i7);
    }

    public void onAcPanelLockPromptStateChanged(int i7) {
        Log.v(TAG, "onAcPanelLockPromptStateChanged");
    }

    public void onAcPromptBoxShownStateChanged(int i7) {
        Log.v(TAG, "onAcPromptBoxShownStateChanged");
    }

    public void onAcPtcPreheatSignalChanged(int i7) {
        Log.v(TAG, "onAcPtcPreheatSignalChanged");
    }

    public void onAcRearPaneNotAvailableMenuStateChanged(int i7) {
        Log.v(TAG, "onAcRearPaneNotAvailableMenuStateChanged");
    }

    public void onAcRearPanelLockStateChanged(int i7) {
        Log.v(TAG, "onAcRearPanelLockStateChanged");
    }

    public void onAcRearStarted() {
        Log.v(TAG, "onAcRearStarted");
    }

    public void onAcRearStoped() {
        Log.v(TAG, "onAcRearStoped");
    }

    public void onAcRemoteCtrlTimeChanged(int i7) {
        Log.v(TAG, "onAcRemoteCtrlTimeChanged");
    }

    public void onAcStarted() {
        Log.v(TAG, "onAcStarted");
    }

    public void onAcStoped() {
        Log.v(TAG, "onAcStoped");
    }

    public void onAcTemperatureControlModeChanaged(int i7) {
        Log.v(TAG, "onAcTemperatureControlModeChanaged");
    }

    public void onAcTemperatureControlModeChanged(int i7) {
        Log.v(TAG, "onAcTemperatureControlModeChanged");
    }

    public void onAcVentilationStateChanged(int i7) {
        Log.v(TAG, "onAcVentilationStateChanged");
    }

    public void onAcWarmStateChanged(int i7) {
        Log.v(TAG, "onAcWarmStateChanged");
    }

    public void onAcWarmTypeOnlineStateChanged(int i7) {
        Log.v(TAG, "onAcWarmTypeOnlineStateChanged");
    }

    public void onAcWindLevelChanged(int i7) {
        Log.v(TAG, "onAcWindLevelChanged");
    }

    public void onAcWindLevelManualSignChanged(int i7) {
        Log.v(TAG, "onAcWindLevelManualSignChanged");
    }

    public void onAcWindModeChanged(int i7) {
        Log.v(TAG, "onAcWindModeChanged");
    }

    public void onAcWindModeManualSignChanged(int i7) {
        Log.v(TAG, "onAcWindModeManualSignChanged");
    }

    @Deprecated
    public void onAcWindModeShownStateChanged(int i7) {
        Log.v(TAG, "onAcWindModeShownStateChanged");
    }

    public void onAirOutleOnlineStateChanged(int i7) {
        Log.v(TAG, "onAirOutleOnlineStateChanged");
    }

    public void onAirOutletStateChanged(int i7, int i8) {
        Log.v(TAG, "onAirOutletStateChanged");
    }

    public void onAirOutletValueChanged(int i7, float f7) {
        Log.v(TAG, "onAirOutletValueChanged");
    }

    public void onAutoCleanAirStateChanged(int i7) {
        Log.v(TAG, "onAutoCleanAirStateChanged");
    }

    public void onAvoidBlowPeopleStateChanged(int i7, int i8) {
        Log.v(TAG, "onAvoidBlowPeopleStateChanged");
    }

    public void onBlowPeopleState(int i7, int i8) {
        Log.v(TAG, "onBlowPeopleState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Deprecated
    public void onElectricDefrostStateChanged(int i7) {
        Log.v(TAG, "onElectricDefrostStateChanged");
    }

    public void onElectricTuyereControlSysStateChange() {
        Log.v(TAG, "onElectricTuyereControlSysStateChange");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i7) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onFreeWindBTNStateChanged(int i7, int i8) {
        Log.v(TAG, "onFreeWindBTNStateChanged");
    }

    public void onFrontRowAirOutletStateChanged(int i7) {
        Log.v(TAG, "onFrontRowAirOutletStateChanged");
    }

    public void onHeatPumpCompressorWorkModeChanged(int i7) {
        Log.v(TAG, "onHeatPumpCompressorWorkModeChanged");
    }

    public void onHighTempAntivirusCountDownChanged(int i7) {
        Log.v(TAG, "onHighTempAntivirusCountDownChanged");
    }

    public void onHighTempAntivirusStateChanged(int i7) {
        Log.v(TAG, "onHighTempAntivirusStateChanged");
    }

    public void onHighTempSterilizeTips(int i7) {
        Log.v(TAG, "onHighTempSterilizeTips");
    }

    public void onIntelligentControlStateChanged(int i7) {
        Log.v(TAG, "onIntelligentControlStateChanged");
    }

    public void onMaxAcStateChanged(int i7) {
        Log.v(TAG, "onMaxAcStateChanged");
    }

    public void onOtaSubBatteryTemperatureChanged(int i7) {
        Log.v(TAG, "onOtaSubBatteryTemperatureChanged");
    }

    public void onQuickCleanAirStateChanged(int i7) {
        Log.v(TAG, "onQuickCleanAirStateChanged");
    }

    public void onQuickCleanTipChanged(int i7) {
        Log.v(TAG, "onQuickCleanTipChanged");
    }

    public void onRearAcCtrlModeChanged(int i7) {
        Log.v(TAG, "onRearAcCtrlModeChanged");
    }

    public void onRearAcKeyLockStateChanged(int i7) {
        Log.v(TAG, "onRearAcKeyLockStateChanged");
    }

    public void onRearAcLockStateChanged(int i7) {
        Log.v(TAG, "onRearAcLockStateChanged");
    }

    public void onRearAcMaxWindLevelChanged(int i7) {
        Log.v(TAG, "onRearAcMaxWindLevelChanged");
    }

    public void onRearAcWindLevelChanged(int i7) {
        Log.v(TAG, "onRearAcWindLevelChanged");
    }

    public void onRearAcWindModeChanged(int i7) {
        Log.v(TAG, "onRearAcWindModeChanged");
    }

    public void onSweepBlowStateChanged(int i7, int i8) {
        Log.v(TAG, "onSweepBlowStateChanged");
    }

    public void onTemperatureChanged(int i7, int i8) {
        Log.v(TAG, "onTemperatureChanged");
    }

    public void onTemperatureUnitChanged(int i7) {
        Log.v(TAG, "onTemperatureUnitChanged");
    }

    public void onVoiceCmdResultChanged(int i7) {
        Log.v(TAG, "onVoiceCmdResultChanged");
    }
}
